package net.fathomsoft.nova.tree.match;

import net.fathomsoft.nova.TestContext;
import net.fathomsoft.nova.ValidationResult;
import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.util.Bounds;
import net.fathomsoft.nova.util.Location;
import net.fathomsoft.nova.util.StringUtils;
import net.fathomsoft.nova.util.SyntaxUtils;

/**
 * {@link MatchCase} extension that represents
 * 
 * @author	Braden Steffaniak
 * @since	v0.2.37 Oct 17, 2014 at 7:25:10 PM
 * @version	v0.2.38 Dec 6, 2014 at 5:19:17 PM
 */
public class Case extends MatchCase
{
	public static final String IDENTIFIER = "case";
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#Node(Node, Location)
	 */
	public Case(Node temporaryParent, Location locationIn)
	{
		super(temporaryParent, locationIn);
	}
	
	@Override
	public String getIdentifier()
	{
		return IDENTIFIER;
	}
	
	@Override
	public int getNumDefaultChildren()
	{
		return super.getNumDefaultChildren() + 2;
	}
	
	/**
	 * Get the {@link net.fathomsoft.nova.tree.match.Fallthrough Fallthrough}
	 * instance that is paired with this switch case (if a fallthrough exists)
	 * 
	 * @return The Fallthrough instance.
	 */
	public Fallthrough getFallthrough()
	{
		if (getScope().getLastVisibleChild() instanceof Fallthrough)
		{
			return (Fallthrough)getScope().getLastVisibleChild();
		}
		
		return null;
	}
	
	/**
	 * Get whether or not the specified switch case contains a fallthrough.
	 * 
	 * @return Whether or not it contains a fallthrough.
	 */
	public boolean containsFallthrough()
	{
		return getFallthrough() != null;
	}
	
	/**
	 * Get whether or not the specified switch case requires a break
	 * statement in order to function properly. The only cases when this
	 * method returns false is when the case contains a fallthrough or 
	 * return statement.
	 * 
	 * @return Whether or not the case requires a break statement to
	 * 		function correctly.
	 */
	public boolean requiresBreak()
	{
		return !containsFallthrough() && !(getScope().getLastChild() instanceof Return);
	}
	
	@Override
	public void addChild(Node node)
	{
		if (containsFallthrough())
		{
			SyntaxMessage.error("Fallthrough statement must be the last statement within a '" + IDENTIFIER + "' statement", getFallthrough());
		}
		
		super.addChild(node);
	}
	
	/**
	 * Get the value that the specified switch case occurs on.<br>
	 * <br>
	 * For example:<br>
	 * <blockquote><pre>
	 * switch (num)
	 * 	case (value) ...
	 * 	default ...</pre></blockquote>
	 * On the line '<code>case (value)</code>' in the above switch statement, the
	 * '<code><i>value</i></code>' component of the case statement is the
	 * {@link net.fathomsoft.nova.tree.Value Value} Node that is returned from
	 * this method.
	 * 
	 * @return The value that the specified case occurs on.
	 */
	public Value getValue()
	{
		return (Value)getChild(super.getNumDefaultChildren() + 0);
	}
	
	public Value getCondition()
	{
		return (Value)getChild(super.getNumDefaultChildren() + 1);
	}
	
	@Override
	public StringBuilder generateNovaInput(StringBuilder builder, boolean outputChildren)
	{
		builder.append(IDENTIFIER + " " + getValue().generateNovaInput(true)).append('\n');
		
		if (outputChildren)
		{
			getScope().generateNovaInput(builder, outputChildren);
		}
		
		return builder;
	}
	
	/**
	 * Decode the given statement into a {@link Case} instance, if
	 * possible. If it is not possible, this method returns null.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>case 43</li>
	 * 	<li>case "56" num++</li>
	 * 	<li>case person.name</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		{@link Case} instance.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @return The generated node, if it was possible to translated it
	 * 		into a {@link Case}.
	 */
	public static Case decodeStatement(Node parent, String statement, Location location, boolean require)
	{
		if (StringUtils.findNextWord(statement).equals(IDENTIFIER))
		{
			Case n = new Case(parent, location);
			
			int valueStartIndex = StringUtils.findNextNonWhitespaceIndex(statement, IDENTIFIER.length() + 1);
			
			Bounds bounds = SyntaxUtils.findValueBounds(statement, valueStartIndex);
			
			if (!bounds.isValid())
			{
				if (!SyntaxMessage.queryError("Unable to decode " + IDENTIFIER + " statement", n, require))
				{
					return null;
				}
			}
			
			String contents = bounds.extractString(statement);
			
			if (n.decodeContents(contents, require) && n.decodeScopeFragment(statement, bounds))
			{
				return n;
			}
		}
		
		return null;
	}
	
	/**
	 * Decode the Value that the switch case occurs on. For more
	 * information on what the Value is, see {@link #getValue()}
	 * 
	 * @param contents The String containing the value to decode.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @return Whether or not the contents were decoded successfully.
	 */
	public boolean decodeContents(String contents, boolean require)
	{
		Value value = SyntaxTree.decodeValue(getParent(), contents, getLocationIn().asNew(), require);
		
		if (value == null)
		{
			return SyntaxMessage.queryError("Unable to decode " + IDENTIFIER + " statement value", this, require);
		}
		
		addChild(value, this);
		
		return true;
	}
	
	public boolean decodeCondition(boolean require)
	{
		Value control = getParentMatch().getControlValue();
		
		Value value = (Value)getParentMatch().getControlValue().clone(control.getParent(), control.getLocationIn(), true);
		Value clone = (Value)getValue().clone(getValue().getParent(), getValue().getLocationIn(), true);
		
		if (value.getReturnedNode().getTypeClass().isOfType("nova/String"))
		{
			CallableMethod stringEquals = getProgram().getClassDeclaration("nova/String").getMethodList().getMethods("equals", MethodList.SearchFilter.getDefault())[0];
			
			MethodCall call = MethodCall.decodeStatement(value.getReturnedNode(), "equals(null)", value.getLocationIn(), require, false, stringEquals);
			
			call.getArgumentList().getVisibleChild(0).replaceWith(clone);
			
			((Accessible)value.getReturnedNode()).setAccessedNode(call);
			
			addChild(value, this);
		}
		else
		{
			BinaryOperation operation = BinaryOperation.generateDefault(getValue().getParent(), getValue().getLocationIn());
			
			operation.getLeftOperand().replaceWith(value);
			operation.getOperator().setOperator("==");
			operation.getRightOperand().replaceWith(clone);
			
			addChild(operation, this);
		}
		
		return true;
	}
	
	@Override
	public ValidationResult validate(int phase)
	{
		ValidationResult result = super.validate(phase);
		
		if (result.skipValidation())
		{
			return result;
		}
		
		if (phase == SyntaxTree.PHASE_METHOD_CONTENTS)
		{
			if (!decodeCondition(true))
			{
				SyntaxMessage.error("Unable to decode case condition", this);
			}
		}
		
		return result;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#clone(Node, Location, boolean)
	 */
	@Override
	public Case clone(Node temporaryParent, Location locationIn, boolean cloneChildren)
	{
		Case node = new Case(temporaryParent, locationIn);
		
		return cloneTo(node, cloneChildren);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#cloneTo(Node)
	 */
	public Case cloneTo(Case node)
	{
		return cloneTo(node, true);
	}
	
	/**
	 * Fill the given {@link Case} with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public Case cloneTo(Case node, boolean cloneChildren)
	{
		super.cloneTo(node, cloneChildren);
		
		return node;
	}
	
	/**
	 * Test the {@link Case} class type to make sure everything
	 * is working properly.
	 * 
	 * @return The error output, if there was an error. If the test was
	 * 		successful, null is returned.
	 */
	public static String test(TestContext context)
	{
		
		
		return null;
	}
}