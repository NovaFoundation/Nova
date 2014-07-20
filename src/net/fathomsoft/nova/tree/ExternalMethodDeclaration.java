package net.fathomsoft.nova.tree;

import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.util.Bounds;
import net.fathomsoft.nova.util.Location;
import net.fathomsoft.nova.util.Patterns;
import net.fathomsoft.nova.util.Regex;
import net.fathomsoft.nova.util.StringUtils;
import net.fathomsoft.nova.util.SyntaxUtils;

/**
 * MethodDeclaration extension that represents the declaration of an
 * external method node type. See {@link #decodeStatement(Node, String, Location, boolean)}
 * for more details on what correct inputs look like.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Jan 5, 2014 at 9:10:53 PM
 * @version	v0.2.14 Jul 19, 2014 at 7:33:13 PM
 */
public class ExternalMethodDeclaration extends MethodDeclaration
{
	private String	alias;
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#Node(Node, Location)
	 */
	public ExternalMethodDeclaration(Node temporaryParent, Location locationIn)
	{
		super(temporaryParent, locationIn);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.MethodDeclaration#isExternal()
	 */
	@Override
	public boolean isExternal()
	{
		return true;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#getScope()
	 */
	@Override
	public Scope getScope()
	{
		return null;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.MethodDeclaration#generateCSourceName(java.lang.StringBuilder)
	 */
	@Override
	public StringBuilder generateCSourceName(StringBuilder builder)
	{
		return builder.append(alias);
	}
	
	/**
	 * Decode the given statement into a ExternalMethodDeclaration instance, if
	 * possible. If it is not possible, this method returns null.
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>external int getAge(String name, int age)</li>
	 * 	<li>external int calculateArea(int width, int height)</li>
	 * 	<li>external void doNothing() as pointlessFunction</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		ExternalMethodDeclaration instance.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes
	 * 		wrong.
	 * @return The generated node, if it was possible to translated it
	 * 		into a Method.
	 */
	public static ExternalMethodDeclaration decodeStatement(Node parent, String statement, Location location, boolean require)
	{
		String methodSignature = findMethodSignature(statement);
		
		if (methodSignature != null && methodSignature.length() > 0)
		{
			ExternalMethodDeclaration n      = new ExternalMethodDeclaration(parent, location);
			MethodDeclaration         method = MethodDeclaration.decodeStatement(n, methodSignature, location, require);
			
			if (method != null)
			{
				method.cloneTo(n);
				
				n.setExternal(true);
				n.alias = n.getName();
				
				if (n.decodeAlias(statement, methodSignature, require))
				{
					return n;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Find the String representing the signature of the external
	 * method that is currently being decoded from the given
	 * statement String.
	 * 
	 * @param statement The String containing the method signature.
	 * @return The signature for the external method to decode.
	 */
	private static String findMethodSignature(String statement)
	{
		if (!Regex.startsWith(statement, Patterns.EXTERNAL))
		{
			return null;
		}
		
		int start = StringUtils.findNextNonWhitespaceIndex(statement, 9);
		int paren = statement.indexOf('(', start);
		
		if (paren < 0)
		{
			return null;
		}
		
		int end = StringUtils.findEndingMatch(statement, paren, '(', ')') + 1;
		
		return statement.substring(start, end);
	}
	
	/**
	 * Decode the alias name for the external method.<br>
	 * For example:
	 * <blockquote><pre>
	 * external int externalMethod() as myAlias</pre></blockquote>
	 * The above declaration declares an external method that can be
	 * called as "myAlias()" and returns an integer result.
	 * 
	 * @param statement The statement containing the method declaration.
	 * @param methodSignature The signature of the external method.
	 * @param require Whether or not to throw an error if anything goes
	 * 		wrong.
	 * @return Whether or not the alias was successfully decoded.
	 */
	private boolean decodeAlias(String statement, String methodSignature, boolean require)
	{
		int start = StringUtils.findNextNonWhitespaceIndex(statement, 9) + methodSignature.length();
		
		String nameChange = statement.substring(start);
		
		nameChange = StringUtils.trimSurroundingWhitespace(nameChange);
		
		if (nameChange.length() <= 0)
		{
			return true;
		}
		
		iterateWords(nameChange);
		
		return true;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#interactWord(java.lang.String, int, net.fathomsoft.nova.util.Bounds, int, java.lang.String, java.lang.String, net.fathomsoft.nova.tree.Node.ExtraData)
	 */
	@Override
	public void interactWord(String word, int wordNumber, Bounds bounds, int numWords, String leftDelimiter, String rightDelimiter, ExtraData extra)
	{
		if (wordNumber == 0)
		{
			if (!word.equals("as"))
			{
				fail(word, bounds);
			}
		}
		else if (wordNumber == 1 && wordNumber == numWords - 1)
		{
			if (SyntaxUtils.isValidIdentifier(word))
			{
				setName(word);
			}
		}
		else
		{
			fail(word, bounds);
		}
	}
	
	/**
	 * Output an error because the word iteration failed.
	 * 
	 * @param word The word that failed to be decoded.
	 * @param bounds The Bounds of the word.
	 */
	private void fail(String word, Bounds bounds)
	{
		Location newLoc = new Location(getLocationIn());
		newLoc.setBounds(bounds.getStart(), bounds.getEnd());
		
		SyntaxMessage.error("Unknown text '" + word + "'", this, newLoc);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#clone(Node, Location)
	 */
	@Override
	public ExternalMethodDeclaration clone(Node temporaryParent, Location locationIn)
	{
		ExternalMethodDeclaration node = new ExternalMethodDeclaration(temporaryParent, locationIn);
		
		return cloneTo(node);
	}
	
	/**
	 * Fill the given ExternalMethodDeclaration with the data that is in
	 * the specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public ExternalMethodDeclaration cloneTo(ExternalMethodDeclaration node)
	{
		super.cloneTo(node);
		
		return node;
	}
	
	/**
	 * Test the ExternalMethodDeclaration class type to make sure everything
	 * is working properly.
	 * 
	 * @return The error output, if there was an error. If the test was
	 * 		successful, null is returned.
	 */
	public static String test()
	{
		
		
		return null;
	}
}