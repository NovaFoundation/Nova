package net.fathomsoft.nova.tree;

import net.fathomsoft.nova.Nova;
import net.fathomsoft.nova.TestContext;
import net.fathomsoft.nova.ValidationResult;
import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.tree.variables.ArrayAccess;
import net.fathomsoft.nova.tree.variables.FieldDeclaration;
import net.fathomsoft.nova.tree.variables.Super;
import net.fathomsoft.nova.tree.variables.Variable;
import net.fathomsoft.nova.tree.variables.VariableDeclaration;
import net.fathomsoft.nova.tree.variables.VariableDeclarationList;
import net.fathomsoft.nova.util.Location;
import net.fathomsoft.nova.util.StringUtils;
import net.fathomsoft.nova.util.SyntaxUtils;

/**
 * Node extension that contains information describing a variable
 * assignment.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Jan 5, 2014 at 9:19:44 PM
 * @version	v0.2.41 Dec 17, 2014 at 7:48:17 PM
 */
public class Assignment extends Node
{
	/**
	 * @see net.fathomsoft.nova.tree.Node#Node(Node, Location)
	 */
	public Assignment(Node temporaryParent, Location locationIn)
	{
		super(temporaryParent, locationIn);
		
		ArgumentList assignees = new ArgumentList(this, locationIn.asNew());
		
		addChild(assignees);
	}
	
	@Override
	public int getNumDefaultChildren()
	{
		return super.getNumDefaultChildren() + 1;
	}
	
	public ArgumentList getAssigneeNodes()
	{
		return (ArgumentList)getChild(super.getNumDefaultChildren() + 0);
	}
	
	public int getNumAssignees()
	{
		return getAssigneeNodes().getNumVisibleChildren();
	}
	
	public boolean containsMultipleAssignees()
	{
		return getNumAssignees() > 1;
	}
	
	/**
	 * Get the node that is having its value modified. In other words,
	 * the left hand value of the equation. For instance, in the
	 * statement: "<code>int j = 35</code>" <u><code>int j</code></u> is
	 * the left hand value of the equation.
	 * 
	 * @return The node that represents the variable that is being
	 * 		assigned.
	 */
	public Value getAssigneeNode()
	{
		return (Value)getAssigneeNodes().getVisibleChild(0);
	}
	
	public void setAssigneeNode(Value assignee)
	{
		if (getNumAssignees() <= 0)
		{
			getAssigneeNodes().addChild(assignee);
		}
		else
		{
			getAssigneeNodes().replace(getAssigneeNode(), assignee);
		}
	}
	
	/**
	 * Get the node that is having its value modified. In other words,
	 * the left hand value of the equation. For instance, in the
	 * statement: "<code>int j = 35</code>" <u><code>int j</code></u> is
	 * the left hand value of the equation.
	 * 
	 * @return The node that represents the variable that is being
	 * 		assigned.
	 */
	public Accessible getAssignee()
	{
		return (Accessible)getAssigneeNode();
	}
	
	public Variable getAssignedNode()
	{
		Identifier last = getAssignee().getLastAccessedNode();
		
		if (last == null)
		{
			last = (Identifier)getAssigneeNode();
		}
		
		return (Variable)last;
	}
	
	/**
	 * Get the node that is being used to set the value of the assignee
	 * node. In other words, the right hand value of the equation. For
	 * instance, in the statement: "<code>int j = 35</code>"
	 * <u><code>35</code></u> is the right hand value of the equation.
	 * 
	 * @return The node that represents the value that the assignee
	 * 		variable is being assigned to.
	 */
	public Value getAssignmentNode()
	{
		return (Value)getChild(super.getNumDefaultChildren() + 1);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#generateCSource(StringBuilder)
	 */
	@Override
	public StringBuilder generateCSource(StringBuilder builder)
	{
		return generateCSourceFragment(builder).append(";\n");
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#generateCSourceFragment(StringBuilder)
	 */
	@Override
	public StringBuilder generateCSourceFragment(StringBuilder builder)
	{
		if (getAssignedNode().getDataType() == Value.POINTER &&
				getAssignmentNode().getReturnedNode().getDataType() == Value.VALUE ||
				getAssignedNode().getDataType() == Value.DOUBLE_POINTER &&
				getAssignmentNode().getReturnedNode().getDataType() == Value.POINTER)
		{
			builder.append('*');
		}
		
		return getAssigneeNode().generateCSourceFragment(builder).append(" = ").append(generateCAssignmentSource());
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#generateNovaInput(StringBuilder, boolean)
	 */
	public StringBuilder generateNovaInput(StringBuilder builder, boolean outputChildren)
	{
		return getAssigneeNode().generateNovaInput(builder, outputChildren).append(" = ").append(getAssignmentNode().generateNovaInput(outputChildren));
	}
	
	/**
	 * Generate the assignment's right hand value C output.
	 * 
	 * @return The assignment's right hand value C output.
	 */
	private StringBuilder generateCAssignmentSource()
	{
		return generateCAssignmentSource(new StringBuilder());
	}
	
	/**
	 * Generate the assignment's right hand value C output.
	 * 
	 * @param builder The StringBuilder to append the data to.
	 * @return The assignment's right hand value C output.
	 */
	private StringBuilder generateCAssignmentSource(StringBuilder builder)
	{
		boolean sameType = getAssignmentNode().getReturnedNode().getType().equals(getAssignedNode().getType());
		
		if (!sameType)
		{
			getAssignedNode().generateCTypeCast(builder).append('(');
		}
		
		builder.append(getAssignmentNode().generateDataTypeOutput(getAssignedNode().getDataType())).append(getAssignmentNode().generateCSourceFragment());
		
		if (!sameType)
		{
			builder.append(')');
		}
		
		return builder;
	}
	
	/**
	 * Decode the given statement into an Assignment if possible. If
	 * it is not possible, then null is returned.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>Int variableName = 45</li>
	 * 	<li>personsName = "Bob"</li>
	 * 	<li>Person myPeep = new Person(54)</li>
	 * 	<li>area = width * height / 2</li>
	 * 	<li>Int newSize = array.getSize() + 5</li>
	 * </ul>
	 * 
	 * @param parent The parent of the current statement.
	 * @param statement The statement to decode into an Assignment.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @return The new Assignment if it decodes properly. If not,
	 * 		it returns null.
	 */
	public static Assignment decodeStatement(Node parent, String statement, Location location, boolean require)
	{
		return decodeStatement(parent, statement, location, require, true);
	}
	
	/**
	 * Decode the given statement into an Assignment if possible. If
	 * it is not possible, then null is returned.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>Int variableName = 45</li>
	 * 	<li>personsName = "Bob"</li>
	 * 	<li>Person myPeep = new Person(54)</li>
	 * 	<li>area = width * height / 2</li>
	 * 	<li>Int newSize = array.getSize() + 5</li>
	 * </ul>
	 * 
	 * @param parent The parent of the current statement.
	 * @param statement The statement to decode into an Assignment.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @param addDeclaration Whether or not to add the declaration to the
	 * 		nearest scope, if the left hand value of the equation is a
	 * 		variable declaration.
	 * @return The new Assignment if it decodes properly. If not,
	 * 		it returns null.
	 */
	public static Assignment decodeStatement(Node parent, String statement, Location location, boolean require, boolean addDeclaration)
	{
		return decodeStatement(parent, statement, location, require, addDeclaration, null, null);
	}
	
	/**
	 * Decode the given statement into an Assignment if possible. If
	 * it is not possible, then null is returned.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>Int variableName = 45</li>
	 * 	<li>personsName = "Bob"</li>
	 * 	<li>Person myPeep = new Person(54)</li>
	 * 	<li>area = width * height / 2</li>
	 * 	<li>Int newSize = array.getSize() + 5</li>
	 * </ul>
	 * 
	 * @param parent The parent of the current statement.
	 * @param statement The statement to decode into an Assignment.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @param addDeclaration Whether or not to add the declaration to the
	 * 		nearest scope, if the left hand value of the equation is a
	 * 		variable declaration.
	 * @return The new Assignment if it decodes properly. If not,
	 * 		it returns null.
	 */
	public static Assignment decodeStatement(Node parent, String statement, Location location, boolean require, boolean addDeclaration, Value[] assignees, Node assignment)
	{
		if (!SyntaxUtils.isVariableAssignment(statement))
		{
			return null;
		}
		
		Assignment n    = new Assignment(parent, location);
		
		int equalsIndex = SyntaxUtils.findCharInBaseScope(statement, '=');
		int endIndex    = StringUtils.findNextNonWhitespaceIndex(statement, equalsIndex - 1, -1) + 1;
		
		String variable = statement.substring(0, endIndex);
		
		Location varLoc = location.asNew();
		varLoc.getBounds().setEnd(varLoc.getStart() + endIndex);
		
		String[] assigneesStr = StringUtils.splitCommas(variable);
		
//		if (!n.decodeAssignee(assignees[0], varLoc, require, addDeclaration))
//		{
//			return null;
//		}
		
		for (int i = 0; i < assigneesStr.length; i++)
		{
			if (!n.decodeAssignee(assigneesStr[i], varLoc, require, addDeclaration, assignees))
			{
				return null;
			}
		}
		
		int rhsIndex = StringUtils.findNextNonWhitespaceIndex(statement, equalsIndex + 1);
		
		// Right-hand side of the equation.
		String rhs   = statement.substring(rhsIndex);
		
		Location newLoc = location.asNew();
		newLoc.setBounds(location.getStart() + rhsIndex, location.getStart() + statement.length());
		
		if (assignment == null)
		{
			assignment = n.decodeRightHandSide(n, rhs, newLoc, require);
		}
		
		if (assignment == null)
		{
			if (addDeclaration)
			{
				n.removeDeclaration();
			}
			
			return null;
		}
		
		n.addChild(assignment);
		
		n.validateCompatible(newLoc);
		
		return n;
	}
	
	private boolean decodeAssignee(String assignee, Location loc, boolean require, boolean addDeclaration, Value[] assignees)
	{
		if (assignees == null || assignees.length <= 0)
		{
			Value varNode = (Value)SyntaxTree.decodeScopeContents(this, assignee, loc, require);
			
			if (varNode == null)
			{
				return SyntaxMessage.queryError("Undeclared variable '" + assignee + "'", this, loc, require);
			}
			
			if (varNode.getReturnedNode() instanceof VariableDeclaration && addDeclaration)
			{
				varNode = addDeclaration((VariableDeclaration)varNode);
			}
			else if (varNode.getReturnedNode() instanceof Variable)
			{
				validateAuthorization((Variable)varNode.getReturnedNode());
			}
			
			getAssigneeNodes().addChild(varNode);
		}
		else
		{
			for (Value assign : assignees)
			{
				getAssigneeNodes().addChild(assign);
			}
		}
		
		return true;
	}
	
	private boolean validateCompatible(Location location)
	{
		Value returnedLeft  = getAssigneeNode().getReturnedNode();
		Value returnedRight = getAssignmentNode().getReturnedNode();
		
		String leftType  = returnedLeft.getType();
		String rightType = returnedRight.getType();
		
		if (!Literal.isNullLiteral(returnedRight) && rightType != null && !rightType.equals(leftType))
		{
			ClassDeclaration left  = returnedLeft.getTypeClass();
			ClassDeclaration right = returnedRight.getTypeClass();
			
			if (!returnedLeft.isExternalType() && !returnedRight.isExternalType() &&
					(left == null || right == null ||
					(returnedLeft.isPrimitive() && (!left.isOfType(right) && !SyntaxUtils.arePrimitiveTypesCompatible(leftType, rightType)) ||
					!returnedLeft.isPrimitive() && !right.isOfType(left))))
			{
				SyntaxMessage.error("Type '" + returnedRight.getType() + "' is not compatible with type '" + returnedLeft.getType() + "'", this, location);
			}
		}
		
		return true;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#validate(int)
	 */
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
			if (containsMultipleAssignees())
			{
				Value assignment = getAssignmentNode().getReturnedNode();
				
				if (!(assignment instanceof MethodCall))
				{
					SyntaxMessage.error("Multiple assignments to single value is not implemented yet", this, false);
					
					return result.errorOccurred(this);
				}
				
				if (!SyntaxUtils.areTypesCompatible(assignment.getTypes(), getAssigneeNodes().getTypes()))
				{
					SyntaxMessage.error("Invalid types to be assigned from method call", this, false);

					return result.errorOccurred(this);
				}
			}
			
			Value returned = getAssigneeNode().getReturnedNode();
			
			if (returned instanceof Variable)
			{
				Variable var = (Variable)returned;
				
				if (var.getDeclaration() instanceof FieldDeclaration)
				{
					FieldDeclaration field   = (FieldDeclaration)var.getDeclaration();
					MutatorMethod    mutator = field.getMutatorMethod();
					
					if (mutator != null)
					{
						if (!mutator.isDisabled())
						{
							String text = field.getName() + "(" + getAssignmentNode().generateNovaInput() + ")";
							
							MethodCall call = MethodCall.decodeStatement(getParent(), text, getLocationIn(), true, false, mutator);
							
							getParent().replace(this, call);
							
							result.returnedNode = call;
							
							return result;
						}
						else
						{
							SyntaxMessage.error("The field '" + field.getName() + "' is not settable", this, false);
							
							getParent().removeChild(this);
							
							result.skipCycle = true;
							
							return result.errorOccurred();
						}
					}
				}
			}
			
			if (getAssigneeNode() instanceof Variable)
			{
				SyntaxUtils.checkVolatile((Variable)getAssigneeNode());
			}
		}
		
		return result;
	}
	
	@Override
	public void rollback()
	{
		removeDeclaration();
		
		super.rollback();
	}
	
	/**
	 * Validate that the assignment is authorized to assign a value
	 * to the given variable in the assignments location. The variable
	 * is not authorized to be modified under the following condition:<br>
	 * <u>The variable cannot be modified if it is <b>private</b> or
	 * <b>visible</b> and is not contained within the same class as the
	 * assignment.</u>
	 * 
	 * @param var The Variable to validate.
	 */
	private void validateAuthorization(Variable var)
	{
		Identifier id = var.getLastAccessedNode();
		
		if (id instanceof Variable)
		{
			VariableDeclaration declaration = ((Variable)id).getDeclaration();
			
			if (id.isAccessed() && declaration instanceof FieldDeclaration)
			{
				FieldDeclaration field = (FieldDeclaration)declaration;
				
				if (field.getVisibility() == FieldDeclaration.VISIBLE)
				{
					ClassDeclaration declaringClass = field.getParentClass();
					ClassDeclaration thisClass      = getParentClass();
					
					if (declaringClass != thisClass && !(id instanceof ArrayAccess))
					{
						SyntaxMessage.error("The value of the field '" + field.getName() + "' cannot be modified", id);
					}
				}
			}
		}
	}
	
	/**
	 * Add the variable declaration to the parent scope if the assignment
	 * was also a declaration.<br>
	 * <br>
	 * For example:
	 * <blockquote><pre>
	 * // Scenario 1
	 * int i = 42;
	 * 
	 * // Scenario 2
	 * i = 43;</pre></blockquote>
	 * Scenario 1 includes a declaration and therefore, this method would
	 * add that declaration to its parent scope and return a clone of the
	 * declared Variable. On the other hand, scenario 2 does not
	 * include a declaration and therefore simply does nothing in this
	 * method (Returns the given Variable).
	 * 
	 * @param parent The parent of the assignment node.
	 * @param var The Variable to check whether or not declares a
	 * 		variable.
	 * @return If a variable is declared, this returns a clone of the
	 * 		declared variable. If not, this simply returns the given
	 * 		Variable instance.
	 */
	private Variable addDeclaration(VariableDeclaration var)
	{
		Node scopeNode = getParent().getAncestorWithScope();
		
		scopeNode.getScope().addChild(var);
		
		Location newLoc = new Location(getLocationIn());
		Variable newVar = var.generateUsableVariable(this, newLoc);
		
		var.setLocationIn(getLocationIn());
		
		return newVar;
	}
	
	private void removeDeclaration()
	{
		if (!(getAssigneeNode() instanceof Identifier))
		{
			return;
		}
		
		VariableDeclarationList list = getParent().getAncestorWithScope().getScope().getVariableList();
		
		list.removeChildWithName(((Identifier)getAssigneeNode()).getName());
	}
	
	/**
	 * Decode the right hand side of an assignment into an Node if
	 * possible. If it is not possible, then null is returned. The right
	 * hand side of an assignment must represent a value.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>45</li>
	 * 	<li>"Bob"</li>
	 * 	<li>new Person(54)</li>
	 * 	<li>width * height / 2</li>
	 * 	<li>array.getSize() + 5</li>
	 * </ul>
	 * 
	 * @param parent The parent of the current statement.
	 * @param rhs The right hand side to decode into an Assignment.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @return The new Node if it decodes properly. If not,
	 * 		it returns null.
	 */
	public Value decodeRightHandSide(Node parent, String rhs, Location location, boolean require)
	{
		Node child = SyntaxTree.decodeScopeContents(parent, rhs, location, require);
		
		if (child == null)
		{
			child = SyntaxTree.decodeValue(parent, rhs, location, require);
		}
		
		if (!(child instanceof Value))
		{
			SyntaxMessage.queryError("Could not decode assignment right-hand side '" + rhs + "'", parent, location, require);
			
			return null;
		}
		
		return (Value)child;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#clone(Node, Location, boolean)
	 */
	@Override
	public Assignment clone(Node temporaryParent, Location locationIn, boolean cloneChildren)
	{
		Assignment node = new Assignment(temporaryParent, locationIn);
		
		return cloneTo(node, cloneChildren);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#cloneTo(Node)
	 */
	public Assignment cloneTo(Assignment node)
	{
		return cloneTo(node, true);
	}
	
	/**
	 * Fill the given {@link Assignment} with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public Assignment cloneTo(Assignment node, boolean cloneChildren)
	{
		super.cloneTo(node, cloneChildren);
		
		return node;
	}
	
	/**
	 * Test the Assignment class type to make sure everything
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