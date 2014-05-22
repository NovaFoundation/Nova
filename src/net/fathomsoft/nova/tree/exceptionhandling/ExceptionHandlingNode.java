package net.fathomsoft.nova.tree.exceptionhandling;

import net.fathomsoft.nova.tree.ScopeNode;
import net.fathomsoft.nova.tree.TreeNode;
import net.fathomsoft.nova.util.Location;

/**
 * TreeNode extension that represents the declaration of an exception
 * handling node type. See {@link #decodeStatement(TreeNode, String, Location)}
 * for more details on what correct inputs look like.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Mar 21, 2014 at 10:50:26 PM
 * @version	v0.2 Apr 7, 2014 at 8:08:51 PM
 */
public class ExceptionHandlingNode extends TreeNode
{
	/**
	 * Instantiate and initialize default values.
	 */
	public ExceptionHandlingNode()
	{
		ScopeNode scopeNode = new ScopeNode();
		
		addChild(scopeNode);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#getScopeNode()
	 */
	@Override
	public ScopeNode getScopeNode()
	{
		return (ScopeNode)getChild(0);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#addChild(TreeNode)
	 */
	@Override
	public void addChild(TreeNode child)
	{
		if (child instanceof ScopeNode)
		{
			super.addChild(child);
		}
		else
		{
			getScopeNode().addChild(child);
		}
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateJavaSource()
	 */
	@Override
	public String generateJavaSource()
	{
		return null;
	}

	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateCHeader()
	 */
	@Override
	public String generateCHeader()
	{
		return null;
	}

	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateCSource()
	 */
	@Override
	public String generateCSource()
	{
		return null;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateCSourceFragment()
	 */
	@Override
	public String generateCSourceFragment()
	{
		return null;
	}
	
	/**
	 * Decode the given statement into a ExceptionHandlingNode instance,
	 * if possible. If it is not possible, this method returns null.
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>try</li>
	 * 	<li>catch (Exception e)</li>
	 * 	<li>finally</li>
	 * 	<li>throw new IOException()</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		ExceptionHandlingNode instance.
	 * @param location The location of the statement in the source code.
	 * @return The generated node, if it was possible to translated it
	 * 		into a ExceptionHandlingNode.
	 */
	public static ExceptionHandlingNode decodeStatement(TreeNode parent, String statement, Location location)
	{
		ExceptionHandlingNode node = null;
		
		if ((node = TryNode.decodeStatement(parent, statement, location)) != null)
		{
			return node;
		}
		else if ((node = CatchNode.decodeStatement(parent, statement, location)) != null)
		{
			return node;
		}
		else if ((node = FinallyNode.decodeStatement(parent, statement, location)) != null)
		{
			return node;
		}
		else if ((node = ThrowNode.decodeStatement(parent, statement, location)) != null)
		{
			return node;
		}
		
		return null;
	}

	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#clone()
	 */
	@Override
	public ExceptionHandlingNode clone()
	{
		ExceptionHandlingNode node = new ExceptionHandlingNode();
		
		return cloneTo(node);
	}
	
	/**
	 * Fill the given ExceptionHandlingNode with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public ExceptionHandlingNode cloneTo(ExceptionHandlingNode node)
	{
		super.cloneTo(node);
		
		return node;
	}
}