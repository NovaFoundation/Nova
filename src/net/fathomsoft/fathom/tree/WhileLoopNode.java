/**
 * The Fathom Programming Language. Write Unbelievable Code.
 *  Copyright (C) 2014  Braden Steffaniak <BradenSteffaniak@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.fathomsoft.fathom.tree;

import net.fathomsoft.fathom.error.SyntaxMessage;
import net.fathomsoft.fathom.util.Bounds;
import net.fathomsoft.fathom.util.Location;
import net.fathomsoft.fathom.util.Patterns;
import net.fathomsoft.fathom.util.Regex;
import net.fathomsoft.fathom.util.SyntaxUtils;

/**
 * LoopNode extension that represents the declaration of a "while loop"
 * node type. See {@link #decodeStatement(TreeNode, String, Location)}
 * for more details on what correct inputs look like.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Jan 5, 2014 at 9:55:59 PM
 * @version	v0.2 Apr 6, 2014 at 5:06:44 PM
 */
public class WhileLoopNode extends LoopNode
{
	/**
	 * Get the TreeNode that describes the condition section of the while
	 * loop. For instance: "while (i < 10)" the contents between the
	 * parenthesis is the condition.
	 * 
	 * @return The TreeNode instance that describes the condition section
	 * 		of the while loop.
	 */
	public TreeNode getConditionNode()
	{
		return getChild(1);
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#addChild(TreeNode)
	 */
	@Override
	public void addChild(TreeNode child)
	{
		if (getChildren().size() <= 1)
		{
			getChildren().add(child);
		}
		else
		{
			super.addChild(child);
		}
	}

	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateJavaSourceOutput()
	 */
	@Override
	public String generateJavaSourceOutput()
	{
		return null;
	}

	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateCHeaderOutput()
	 */
	@Override
	public String generateCHeaderOutput()
	{
		return null;
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateCSourceOutput()
	 */
	@Override
	public String generateCSourceOutput()
	{
		StringBuilder builder = new StringBuilder();
		
		TreeNode condition    = getConditionNode();
		
		builder.append("while (").append(condition.generateCSourceOutput()).append(')').append('\n');
		
		for (int i = 0; i < getChildren().size(); i++)
		{
			TreeNode child = getChild(i);
			
			if (child != getConditionNode())
			{
				builder.append(child.generateCSourceOutput());
			}
		}
		
		return builder.toString();
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateCSourceFragment()
	 */
	@Override
	public String generateCSourceFragment()
	{
		return null;
	}
	
	/**
	 * Decode the given statement into a WhileLoopNode instance, if
	 * possible. If it is not possible, this method returns null.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>while (currentNode != null)</li>
	 * 	<li>while (true)</li>
	 * 	<li>while (number.isEven())</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		WhileLoopNode instance.
	 * @param location The location of the statement in the source code.
	 * @return The generated node, if it was possible to translated it
	 * 		into a WhileLoopNode.
	 */
	public static WhileLoopNode decodeStatement(TreeNode parent, String statement, Location location)
	{
		if (Regex.matches(statement, 0, Patterns.PRE_WHILE))
		{
			WhileLoopNode n = new WhileLoopNode();
			
			Bounds bounds = Regex.boundsOf(statement, Patterns.WHILE_CONTENTS);
			
			if (bounds.getStart() >= 0)
			{
				Location newLoc    = new Location();
				newLoc.setLineNumber(location.getLineNumber());
				newLoc.setOffset(location.getOffset() + bounds.getStart());
				
				String   contents  = statement.substring(bounds.getStart(), bounds.getEnd());
				
				TreeNode condition = BinaryOperatorNode.decodeStatement(parent, contents, newLoc);
				
				if (condition == null && SyntaxUtils.isLiteral(contents))
				{
					LiteralNode literal = new LiteralNode();
					literal.setValue(contents, parent.isWithinExternalContext());
					
					condition = literal;
				}
				
				n.addChild(condition);
					
				return n;
			}
			else
			{
				SyntaxMessage.error("While loop missing condition", location);
			}
		}
		
		return null;
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#clone()
	 */
	@Override
	public WhileLoopNode clone()
	{
		WhileLoopNode node = new WhileLoopNode();
		
		return clone(node);
	}
	
	/**
	 * Fill the given WhileLoopNode with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public WhileLoopNode clone(WhileLoopNode node)
	{
		for (int i = 0; i < getChildren().size(); i++)
		{
			TreeNode child = getChild(i);
			
			node.addChild(child.clone());
		}
		
		return node;
	}
}
