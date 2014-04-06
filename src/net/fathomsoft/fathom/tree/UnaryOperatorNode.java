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

/**
 * TreeNode extension that represents a unary operator node type.
 * See {@link #decodeStatement(TreeNode, String, Location)} for more
 * details on what correct inputs look like.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Jan 5, 2014 at 10:00:11 PM
 * @version	v0.2 Apr 6, 2014 at 2:46:52 PM
 */
public class UnaryOperatorNode extends TreeNode
{
	/**
	 * The the TreeNode that represents the variable in the operation.<br>
	 * For example:<br>
	 * <blockquote><pre>
	 * var++;</pre></blockquote>
	 * In the previous statement, 'var' is the variable.
	 * 
	 * @return The TreeNode that represents the parameters of the method.
	 */
	public TreeNode getVariableNode()
	{
		for (int i = 0; i < getChildren().size(); i++)
		{
			if (getChild(i) instanceof OperatorNode == false)
			{
				return getChild(i);
			}
		}
		
		return null;
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateJavaSourceOutput()
	 */
	@Override
	public String generateJavaSourceOutput()
	{
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < getChildren().size(); i++)
		{
			builder.append(getChild(i).generateJavaSourceOutput());
		}
		
		builder.append(';').append('\n');
		
		return builder.toString();
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
		return generateCSourceFragment() + ";\n";
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateCSourceFragment()
	 */
	@Override
	public String generateCSourceFragment()
	{
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < getChildren().size(); i++)
		{
			TreeNode child = getChild(i);
			
			builder.append(child.generateCSourceFragment());
		}
		
		return builder.toString();
	}
	
	/**
	 * Decode the given statement into a UnaryOperatorNode instance, if
	 * possible. If it is not possible, this method returns null.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>asdf.var++</li>
	 * 	<li>++asdf.var</li>
	 * 	<li>asdf.var--</li>
	 * 	<li>--asdf.var</li>
	 * 	<li>var--</li>
	 * 	<li>--var</li>
	 * 	<li>var++</li>
	 * 	<li>++var</li>
	 * 	<li>++array[5]</li>
	 * 	<li>array[5]--</li>
	 * 	<li>!asdf.var</li>
	 * 	<li>!var</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		UnaryOperatorNode instance.
	 * @param location The location of the statement in the source code.
	 * @return The generated node, if it was possible to translated it
	 * 		into a UnaryOperatorNode.
	 */
	public static UnaryOperatorNode decodeStatement(TreeNode parent, String statement, Location location)
	{
		Bounds bounds = Regex.boundsOf(statement, Patterns.UNARY_ARITH_OPERATORS);
		
		if (bounds.getStart() >= 0)
		{
			UnaryOperatorNode n = new UnaryOperatorNode();
				
			String operatorVal = statement.substring(bounds.getStart(), bounds.getEnd());
			
			OperatorNode operator = new OperatorNode();
			operator.setOperator(operatorVal);
			
			int varStart = 0;
			int varEnd   = 0;
			
			if (bounds.getStart() == 0)
			{
				varStart = bounds.getEnd();
				varEnd   = statement.length();
				
				n.addChild(operator);
			}
			else
			{
				varStart = 0;
				varEnd   = bounds.getStart();
			}
			
			String variableName = statement.substring(varStart, varEnd);
			
			IdentifierNode variable = TreeNode.getExistingNode(parent, variableName);
			
			if (variable != null)
			{
				variable = variable.clone();
				
				n.addChild(variable);
				
				if (bounds.getStart() > 0)
				{
					n.addChild(operator);
				}
				
				return n;
			}
			
			SyntaxMessage.error("Undefined variable '" + variable + "'", location);
		}
		
		return null;
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#clone()
	 */
	@Override
	public UnaryOperatorNode clone()
	{
		UnaryOperatorNode node = new UnaryOperatorNode();
		
		return clone(node);
	}
	
	/**
	 * Fill the given UnaryOperatorNode with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public UnaryOperatorNode clone(UnaryOperatorNode node)
	{
		for (int i = 0; i < getChildren().size(); i++)
		{
			TreeNode child = getChild(i);
			
			node.addChild(child.clone());
		}
		
		return node;
	}
}