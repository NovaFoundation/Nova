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

import net.fathomsoft.fathom.Fathom;
import net.fathomsoft.fathom.tree.exceptionhandling.ExceptionNode;

/**
 * TreeNode extension that represents a list of parameters for a method.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Jan 5, 2014 at 9:56:34 PM
 * @version	v0.2 Apr 5, 2014 at 10:17:44 PM
 */
public class ParameterListNode extends TreeNode
{
	/**
	 * Instantiate and initialize default data. Generates the
	 * two default parameters for every method: Exception data.
	 */
	public ParameterListNode()
	{
		ParameterNode exceptionData = new ParameterNode();
		exceptionData.setName(ExceptionNode.EXCEPTION_DATA_IDENTIFIER);
		exceptionData.setType("ExceptionData");
		
		addChild(exceptionData);
	}
	
	/**
	 * Identifier for the calling object of a method call.<br>
	 * <br>
	 * For example:
	 * <blockquote><pre>
	 * person.getName();</pre></blockquote>
	 * "person" is the calling object, so this translates to the
	 * following in C:
	 * <blockquote><pre>
	 * person->getName(person);</pre></blockquote>
	 * And this means that the method header in C must include a Person
	 * type as the first parameter for the getName() method. Therefore,
	 * the method header looks like this:
	 * <blockquote><pre>
	 * static String getName(Person __o__);</pre></blockquote>
	 * And "__o__" is the chosen OBJECT_REFERENCE_IDENTIFIER.
	 */
	public static final String OBJECT_REFERENCE_IDENTIFIER = "__o__";
	
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
			
			if (i < getChildren().size() - 1)
			{
				builder.append(", ");
			}
		}
		
		return builder.toString();
	}

	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateCHeaderOutput()
	 */
	@Override
	public String generateCHeaderOutput()
	{
		return generateCSourceOutput();
	}

	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateCSourceOutput()
	 */
	@Override
	public String generateCSourceOutput()
	{
		StringBuilder builder = new StringBuilder();
		
		ClassNode classNode = (ClassNode)getAncestorOfType(ClassNode.class);
		
		if (getParent() instanceof ConstructorNode == false)
		{
			builder.append(classNode.getName());
			
			if (getParent() instanceof DestructorNode)
			{
				builder.append('*');
			}
			
			builder.append("* ").append(MethodNode.getObjectReferenceIdentifier());
		}
		
		//builder.append("ExceptionData* __").append(Fathom.LANGUAGE_NAME.toUpperCase()).append("__exception_data");
		
		for (int i = 0; i < getChildren().size(); i++)
		{
			TreeNode child = getChild(i);
			
			if (i > 0 || getParent() instanceof ConstructorNode == false)
			{
				builder.append(", ");
			}
			
			builder.append(child.generateCHeaderOutput());
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
	 * @see net.fathomsoft.fathom.tree.TreeNode#clone()
	 */
	@Override
	public ParameterListNode clone()
	{
		ParameterListNode node = new ParameterListNode();
		
		return clone(node);
	}
	
	/**
	 * Fill the given ParameterListNode with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public ParameterListNode clone(ParameterListNode node)
	{
		for (int i = 0; i < getChildren().size(); i++)
		{
			TreeNode child = getChild(i);
			
			node.addChild(child.clone());
		}
		
		return node;
	}
}
