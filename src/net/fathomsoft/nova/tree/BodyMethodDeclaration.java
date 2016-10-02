package net.fathomsoft.nova.tree;

import net.fathomsoft.nova.Nova;
import net.fathomsoft.nova.TargetC;
import net.fathomsoft.nova.TestContext;
import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.util.Location;

/**
 * Declaration extension that represents the declaration of a method
 * node type. See {@link #decodeStatement(Node, String, Location, boolean)}
 * for more details on what correct inputs look like.
 * 
 * @author	Braden Steffaniak
 * @since	v0.2.21 Jul 30, 2014 at 1:45:00 PM
 * @version	v0.2.38 Dec 6, 2014 at 5:19:17 PM
 */
public class BodyMethodDeclaration extends NovaMethodDeclaration
{
	/**
	 * Instantiate and initialize default data.
	 * 
	 * @see net.fathomsoft.nova.tree.Node#Node(Node, Location)
	 */
	public BodyMethodDeclaration(Node temporaryParent, Location locationIn)
	{
		super(temporaryParent, locationIn);
		
		Scope scope = new Scope(this, locationIn.asNew());
		
		setScope(scope);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#getNumDefaultChildren()
	 */
	@Override
	public int getNumDefaultChildren()
	{
		return super.getNumDefaultChildren() + 1;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#getScope()
	 */
	@Override
	public Scope getScope()
	{
		return (Scope)getChild(super.getNumDefaultChildren() + 0);
	}
	
	/**
	 * Get whether or not the specified MethodDeclaration contains a Body.
	 * 
	 * @return Whether or not the specified MethodDeclaration contains a
	 * 		Body.
	 */
	public boolean containsBody()
	{
		return true;
	}
	
	/**
	 * Decode the given statement into a Method instance, if
	 * possible. If it is not possible, this method returns null.
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>public findPerson(String name, Int age) -&gt; Person</li>
	 * 	<li>private calculateArea(Int width, Int height) -&gt; Int</li>
	 * 	<li>public doNothing()</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		Method instance.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes
	 * 		wrong.
	 * @return The generated node, if it was possible to translated it
	 * 		into a Method.
	 */
	public static BodyMethodDeclaration decodeStatement(Node parent, String statement, Location location, boolean require)
	{
		NovaMethodDeclaration method = NovaMethodDeclaration.decodeStatement(parent, statement, location, false);
		
		if (method != null)
		{
			BodyMethodDeclaration n = new BodyMethodDeclaration(parent, location);
			
			method.cloneTo(n);
			n.setLocationIn(location);
			
			for (Parameter p : n.getParameterList().getOptionalParameters())
			{
				DefaultParameterInitialization init = new DefaultParameterInitialization(n, location, p);
				
				n.addChild(init);
			}
			
			if (n.getParentClass() instanceof Interface)
			{
				return new InterfaceMethodDeclaration(parent, location, n);
			}
			
			return n;
		}
		
		return null;
	}
	
	/**
	 * Generate a Method with the given parent and location for
	 * temporary use.
	 * 
	 * @param parent The node to set as the Method's parent.
	 * @param locationIn The location to set as the Method's location.
	 * @return The generated temporary Method.
	 */
	public static BodyMethodDeclaration generateTemporaryMethod(Node parent, Location locationIn)
	{
		String name = parent.getParentClass(true).generateTemporaryMethodName();
		
		BodyMethodDeclaration methodDeclaration = decodeStatement(parent, name + "()", locationIn, true);
		
		return methodDeclaration;
	}
	
	public static BodyMethodDeclaration generateTemporaryHierarchy(Nova controller)
	{
		ClassDeclaration c = ClassDeclaration.generateTemporaryHierarchy(controller);
		
		BodyMethodDeclaration method = generateTemporaryMethod(c, Location.INVALID);
		c.addChild(method);
		
		return method;
	}
	
	/**
	 * Test the MethodDeclaration class type to make sure everything
	 * is working properly.
	 * 
	 * @return The error output, if there was an error. If the test was
	 * 		successful, null is returned.
	 */
	public static String test(TestContext context)
	{
		
		
		return null;
	}
	
	@Override
	public TargetC.TargetBodyMethodDeclaration getTarget()
	{
		final BodyMethodDeclaration self = this;
		
		return new TargetC.TargetBodyMethodDeclaration()
		{
			public BodyMethodDeclaration node()
			{
				return self;
			}
		};
	}
}