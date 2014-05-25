/**
 * The Nova Programming Language. Write Explosive Code.
 * Copyright (C) 2014  Braden Steffaniak <BradenSteffaniak@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * The Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.fathomsoft.nova.tree;

import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.tree.variables.ArrayNode;
import net.fathomsoft.nova.util.Location;
import net.fathomsoft.nova.util.Patterns;
import net.fathomsoft.nova.util.Regex;
import net.fathomsoft.nova.util.SyntaxUtils;

/**
 * ValueNode extension that represents the declaration of an
 * instantiation node type. See {@link #decodeStatement(TreeNode, String, Location)}
 * for more details on what correct inputs look like.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Apr 3, 2014 at 7:53:35 PM
 * @version	v0.2.6 May 24, 2014 at 6:06:20 PM
 */
public class InstantiationNode extends IdentifierNode
{
	/**
	 * Get the node represents the type of instance that is being
	 * instantiated. For example, a method call or an array
	 * initialization node.
	 * 
	 * @return The node that represents the type of instance that is
	 * 		being instantiated.
	 */
	public IdentifierNode getIdentifierNode()
	{
		return (IdentifierNode)getChild(0);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#addChild(TreeNode)
	 */
	@Override
	public void addChild(TreeNode child)
	{
		if (getChildren().size() == 0)
		{
			super.addChild(child);
		}
		else
		{
			getIdentifierNode().addChild(child);
		}
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateCSource()
	 */
	@Override
	public String generateCSource()
	{
		return generateCSourceFragment() + ";\n";
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateCSourceFragment()
	 */
	@Override
	public String generateCSourceFragment()
	{
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < getChildren().size(); i++)
		{
			builder.append(getChild(i).generateCSourceFragment());
		}
		
		return builder.toString();
	}
	
	/**
	 * Decode the given statement into an InstantiationNode instance, if
	 * possible. If it is not possible, this method returns null.<br>
	 * Instantiations always begin with the 'new' keyword.
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>new Person("Joe")</li>
	 * 	<li>new Armadillo()</li>
	 * 	<li>new String("asdf")</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		InstantiationNode instance.
	 * @param location The location of the statement in the source code.
	 * @return The generated node, if it was possible to translated it
	 * 		into a InstantiationNode.
	 */
	public static InstantiationNode decodeStatement(TreeNode parent, String statement, Location location)
	{
		if (SyntaxUtils.isInstantiation(statement))
		{
			InstantiationNode n = new InstantiationNode();
			
			int startIndex  = Regex.indexOf(statement, Patterns.POST_INSTANTIATION);
			
			String action   = statement.substring(startIndex);
			
			Location newLoc = new Location();
			newLoc.setLineNumber(location.getLineNumber());
			newLoc.setBounds(location.getStart() + startIndex, location.getStart() + statement.length());
			
			IdentifierNode child = null;
			
			if (SyntaxUtils.isMethodCall(action))
			{
				MethodCallNode methodCall = MethodCallNode.decodeStatement(parent, action, newLoc);//, false);
				
				if (methodCall == null)
				{
					return null;
				}
				
				child = methodCall;
			}
			else if (SyntaxUtils.isArrayInitialization(action))
			{
				child = ArrayNode.decodeStatement(parent, action, newLoc);
			}
			
			if (child == null)
			{
				SyntaxMessage.error("Unable to parse instantiation of '" + action + "'", parent.getFileNode(), newLoc, parent.getController());
				
				return null;
			}
			
			n.setName(child.getName());
			n.setType(child.getType());
			n.addChild(child);
			
			return n;
		}
		
		return null;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateNovaInput()
	 */
	@Override
	public String generateNovaInput()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("new ").append(getIdentifierNode().generateNovaInput());
		
		return builder.toString();
	}

	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#clone()
	 */
	@Override
	public InstantiationNode clone()
	{
		InstantiationNode node = new InstantiationNode();
		
		return cloneTo(node);
	}
	
	/**
	 * Fill the given InstantiationNode with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public InstantiationNode cloneTo(InstantiationNode node)
	{
		super.cloneTo(node);
		
		return node;
	}
}