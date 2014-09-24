package net.fathomsoft.nova.tree;

import java.io.File;

import net.fathomsoft.nova.TestContext;
import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.util.Location;
import net.fathomsoft.nova.util.StringUtils;

/**
 * {@link Node} extension that represents
 * 
 * @author	Braden Steffaniak
 * @since	v0.2.31 Sep 24, 2014 at 11:13:15 AM
 * @version	v0.2.31 Sep 24, 2014 at 4:41:04 PM
 */
public class Package extends Node
{
	private String	location;
	
	public static final String	PACKAGE_KEYWORD = "package";
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#Node(Node, Location)
	 */
	public Package(Node temporaryParent, Location locationIn)
	{
		super(temporaryParent, locationIn);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#generateCHeader(StringBuilder)
	 */
	@Override
	public StringBuilder generateCHeader(StringBuilder builder)
	{
		return builder;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#generateCSource(StringBuilder)
	 */
	@Override
	public StringBuilder generateCSource(StringBuilder builder)
	{
		return builder;
	}
	
	/**
	 * Decode the given statement into a {@link Package} instance, if
	 * possible. If it is not possible, this method returns null.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>package "some/package/name"</li>
	 * 	<li>package "nova/standard"</li>
	 * 	<li>package "com/example"</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		{@link Package} instance.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @return The generated node, if it was possible to translated it
	 * 		into a {@link Package}.
	 */
	public static Package decodeStatement(Node parent, String statement, Location location, boolean require)
	{
		if (statement.startsWith(PACKAGE_KEYWORD))
		{
			Package n = new Package(parent, location);
			
			statement = StringUtils.trimFirstWord(statement, true);
			
			if (StringUtils.isSurroundedByQuotes(statement))
			{
				statement  = StringUtils.removeSurroundingQuotes(statement);
				
				n.location = statement;
				
				if (n.validatePackageExists(require))
				{
					return n;
				}
			}
		}
		
		return null;
	}
	
	public boolean validatePackageExists(boolean require)
	{
		String directories[] = location.split("/");
		
		if (directories.length == 0 || directories.length == 1 && directories[0].length() <= 0)
		{
			throwIncorrectPackageException("Package location cannot be empty. Perhaps try using the default package");
		}
		
		File current = getFileDeclaration().getFile().getParentFile();
		
		for (int i = directories.length - 1; i >= 0; i--)
		{
			String directory = directories[i];
			
			if (!current.isDirectory())
			{
				throwIncorrectPackageException();
			}
			else if (!directory.equals(current.getName()))
			{
				throwIncorrectPackageException();
			}
			
			current = current.getParentFile();
		}
		
		return true;
	}

	private void throwIncorrectPackageException()
	{
		throwIncorrectPackageException("The package location of \"" + location + "\" is not valid");
	}
	
	private void throwIncorrectPackageException(String message)
	{
		SyntaxMessage.error(message, this);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#clone(Node, Location, boolean)
	 */
	@Override
	public Package clone(Node temporaryParent, Location locationIn, boolean cloneChildren)
	{
		Package node = new Package(temporaryParent, locationIn);
		
		return cloneTo(node);
	}
	
	/**
	 * Fill the given {@link Package} with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public Package cloneTo(Package node)
	{
		super.cloneTo(node);
		
		return node;
	}
	
	/**
	 * Test the {@link Package} class type to make sure everything
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