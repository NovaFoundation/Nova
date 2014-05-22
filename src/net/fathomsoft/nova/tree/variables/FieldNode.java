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
package net.fathomsoft.nova.tree.variables;

import net.fathomsoft.nova.tree.InstanceDeclarationNode;
import net.fathomsoft.nova.tree.LocalDeclarationNode;
import net.fathomsoft.nova.tree.TreeNode;
import net.fathomsoft.nova.util.Bounds;
import net.fathomsoft.nova.util.Location;
import net.fathomsoft.nova.util.Patterns;

/**
 * DeclarationNode extension that represents the declaration of a field
 * node type. See {@link #decodeStatement(TreeNode, String, Location)}
 * for more details on what correct inputs look like.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Jan 5, 2014 at 9:12:04 PM
 * @version	v0.2.4 May 17, 2014 at 9:55:04 PM
 */
public class FieldNode extends InstanceDeclarationNode
{
	/**
	 * Declares that a variable can be viewed from anywhere, but not
	 * modified.
	 */
	public static final int	VISIBLE	= 4;
	
	/**
	 * @see net.fathomsoft.nova.tree.InstanceDeclarationNode#isVisibilityValid()
	 */
	@Override
	public boolean isVisibilityValid()
	{
		int visibility = getVisibility();
		
		return super.isVisibilityValid() || visibility == VISIBLE;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.InstanceDeclarationNode#getVisibilityText()
	 */
	@Override
	public String getVisibilityText()
	{
		int visibility = getVisibility();
		
		if (visibility == VISIBLE)
		{
			return "visible";
		}
		
		return super.getVisibilityText();
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.InstanceDeclarationNode#setAttribute(java.lang.String, int)
	 */
	@Override
	public boolean setAttribute(String attribute, int argNum)
	{
		if (super.setAttribute(attribute, argNum))
		{
			return true;
		}
		
		if (argNum == 0)
		{
			if (attribute.equals("visible"))
			{
				setVisibility(VISIBLE);
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}

	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateJavaSource()
	 */
	@Override
	public String generateJavaSource()
	{
		return super.generateJavaSource();
	}

	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateCHeader()
	 */
	@Override
	public String generateCHeader()
	{
		return generateCSource();
	}

	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateCSource()
	 */
	@Override
	public String generateCSource()
	{
		StringBuilder builder = new StringBuilder();
		
//		if (isStatic())
//		{
//			builder.append(getStaticText()).append(' ');
//		}
		if (isConstant())
		{
			builder.append(getConstantText()).append(' ');
		}
		
		builder.append(getType());
		
		if (isReference())
		{
			builder.append('&');
		}
		else if (isPointer())
		{
			builder.append('*');
		}
		if (isArray())
		{
			builder.append(getArrayText());
		}
		if (!isPrimitiveType() && !isExternal())
		{
			builder.append('*');
		}
		
		builder.append(' ').append(generateCSourceName());
		
//		if (!isPrimitiveType())
//		{
//			builder.append(" = 0");
//		}
		
		builder.append(';').append('\n');
		
		return builder.toString();
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#generateCSourceFragment()
	 */
	@Override
	public String generateCSourceFragment()
	{
		if (isSpecialFragment())
		{
			return generateSpecialFragment();
		}
		
		return generateUseOutput() + generateChildrenCSourceFragment();
	}
	
	/**
	 * Decode the given statement into a FieldNode instance, if
	 * possible. If it is not possible, this method returns null.
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>public static constant int uuid</li>
	 * 	<li>private Person p</li>
	 * 	<li>private String name</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		FieldNode instance.
	 * @param location The location of the statement in the source code.
	 * @return The generated node, if it was possible to translated it
	 * 		into a FieldNode.
	 */
	public static FieldNode decodeStatement(TreeNode parent, final String statement, Location location)
	{
		// The field declaration without the field specific modifiers.
		final Bounds localDeclaration = new Bounds(-1, -1);
		
		FieldNode n = new FieldNode()
		{
			public void interactWord(String word, int wordNumber, Bounds bounds, int numWords)
			{
				if (!setAttribute(word, wordNumber))
				{
					if (localDeclaration.getStart() == -1)
					{
						localDeclaration.setStart(bounds.getStart());
					}
					if (wordNumber == numWords - 1)
					{
						localDeclaration.setEnd(statement.length());
					}
				}
			}
		};
		
		n.iterateWords(statement, Patterns.IDENTIFIER_BOUNDARIES);
		
		if (localDeclaration.getStart() < 0)
		{
			return null;
		}
		
		String preStatement      = statement.substring(0, localDeclaration.getStart());
		String localStatement    = statement.substring(localDeclaration.getStart(), localDeclaration.getEnd());
		
		LocalDeclarationNode var = LocalDeclarationNode.decodeStatement(parent, localStatement, location);
		
		if (var == null)
		{
			return null;
		}
		
		var.cloneTo(n);
		
		n.iterateWords(preStatement);
		
		return n;
	}

//	/**
//	 * @see net.fathomsoft.fathom.tree.TreeNode#validate()
//	 */
//	@Override
//	public void validate()
//	{
//		TreeNode parent = getParent();
//		
//		if (parent instanceof ParameterNode)
//		{
//			ParameterNode param = (ParameterNode)parent;
//			
//			if (param.getName().equals("this"))
//			{
//				param.getParent().replace(param, this);
//			}
//		}
//	}
	
	/**
	 * @see net.fathomsoft.nova.tree.TreeNode#clone()
	 */
	@Override
	public FieldNode clone()
	{
		FieldNode node = new FieldNode();
		
		return cloneTo(node);
	}
	
	/**
	 * Fill the given FieldNode with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public FieldNode cloneTo(FieldNode node)
	{
		super.cloneTo(node);
		
		return node;
	}
}
