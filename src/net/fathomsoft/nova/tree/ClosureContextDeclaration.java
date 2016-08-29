package net.fathomsoft.nova.tree;

import net.fathomsoft.nova.TestContext;
import net.fathomsoft.nova.tree.variables.Variable;
import net.fathomsoft.nova.tree.variables.VariableDeclaration;
import net.fathomsoft.nova.util.Location;

/**
 * {@link Node} extension that represents
 *
 * @author	Braden Steffaniak
 */
public class ClosureContextDeclaration extends LocalDeclaration
{
	public ClosureContext context;
	
	private int id;
	
	private static int idCounter = 1;
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#Node(Node, Location)
	 */
	public ClosureContextDeclaration(Node temporaryParent, Location locationIn, ClosureContext context)
	{
		super(temporaryParent, locationIn);
		
		this.context = context;
		id = idCounter++;
	}
	
	@Override
	public StringBuilder generateCDeclarationFragment(StringBuilder builder)
	{
		return builder.append(context.getName()).append(' ').append(getName());
	}
	
	@Override
	public StringBuilder generateCDefaultValue(StringBuilder builder)
	{
		builder.append("\n{\n");
		
		for (ClosureVariableDeclaration var : context)
		{
			generateCDeclarationValue(builder, var);
		}
		
		return builder.append("}");
	}
	
	public StringBuilder generateCDeclarationValue(StringBuilder builder, ClosureVariableDeclaration var)
	{
		//Variable v = var.generateUsableVariable(this, Location.INVALID);
		
		if (var.originalDeclaration instanceof ClosureVariableDeclaration)
		{
			builder.append("context->");
		}
		else
		{
			builder.append('&');
		}
		
		var.originalDeclaration.generateCSourceName(builder);
		
		return builder.append(",\n");
	}
	
	public String getName()
	{
		return "contextArg" + id;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#clone(Node, Location, boolean)
	 */
	@Override
	public ClosureContextDeclaration clone(Node temporaryParent, Location locationIn, boolean cloneChildren)
	{
		ClosureContextDeclaration node = new ClosureContextDeclaration(temporaryParent, locationIn, context);
		
		return cloneTo(node, cloneChildren);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#cloneTo(Node)
	 */
	public ClosureContextDeclaration cloneTo(ClosureContextDeclaration node)
	{
		return cloneTo(node, true);
	}
	
	/**
	 * Fill the given {@link ClosureContextDeclaration} with the data that is in the
	 * specified node.
	 *
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public ClosureContextDeclaration cloneTo(ClosureContextDeclaration node, boolean cloneChildren)
	{
		super.cloneTo(node, cloneChildren);
		
		return node;
	}
	
	/**
	 * Test the {@link ClosureContextDeclaration} class type to make sure everything
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