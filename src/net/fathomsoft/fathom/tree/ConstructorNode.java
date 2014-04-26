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
import net.fathomsoft.fathom.error.SyntaxMessage;
import net.fathomsoft.fathom.tree.variables.PrivateFieldListNode;
import net.fathomsoft.fathom.tree.variables.PublicFieldListNode;
import net.fathomsoft.fathom.tree.variables.VariableNode;
import net.fathomsoft.fathom.util.Bounds;
import net.fathomsoft.fathom.util.Location;
import net.fathomsoft.fathom.util.Regex;
import net.fathomsoft.fathom.util.StringUtils;

/**
 * MethodNode extension that represents the declaration of a Constructor
 * node type. See {@link #decodeStatement(TreeNode, String, Location)}
 * for more details on what correct inputs look like.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Jan 5, 2014 at 9:50:47 PM
 * @version	v0.2.1 Apr 24, 2014 at 4:50:05 PM
 */
public class ConstructorNode extends MethodNode
{
	/**
	 * Create a ConstructorNode and initialize default values.
	 */
	public ConstructorNode()
	{
		setPointer(true);
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateJavaSource()
	 */
	@Override
	public String generateJavaSource()
	{
		StringBuilder builder = new StringBuilder();
		
		if (isVisibilityValid())
		{
			builder.append(getVisibilityText()).append(' ');
		}
		if (isStatic())
		{
			SyntaxMessage.error("Constructor cannot be static", getFileNode(), getLocationIn(), getController());
			
			return null;
		}
		if (isConstant())
		{
			SyntaxMessage.error("Constructor cannot be const", getFileNode(), getLocationIn(), getController());
			
			return null;
		}
		
		if (isReference())
		{
			SyntaxMessage.error("Constructor cannot return a reference", getFileNode(), getLocationIn(), getController());
			
			return null;
		}
		else if (isPointer())
		{
			SyntaxMessage.error("Constructor cannot return a pointer", getFileNode(), getLocationIn(), getController());
			
			return null;
		}
		
		builder.append(getName()).append('(');
		
		builder.append(getParameterListNode().generateJavaSource());
		
		builder.append(')').append('\n').append('{').append('\n');
		
		for (int i = 0; i < getChildren().size(); i++)
		{
			TreeNode child = getChild(i);
			
			if (child instanceof ParameterListNode == false)
			{
				builder.append(child.generateJavaSource());
			}
		}
		
		builder.append('}').append('\n');
		
		return builder.toString();
	}

	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateCHeader()
	 */
	@Override
	public String generateCHeader()
	{
		StringBuilder builder = new StringBuilder();
		
		if (isVisibilityValid())
		{
			if (getVisibility() == DeclarationNode.PRIVATE)
			{
				return "";
			}
		}
//		if (isStatic())
//		{
//			SyntaxError.outputNewError("Constructor cannot be static", getLocationIn());
//			
//			return null;
//		}
		if (isConstant())
		{
			SyntaxMessage.error("Constructor cannot be const", getFileNode(), getLocationIn(), getController());
			
			return null;
		}
		
		if (isReference())
		{
			SyntaxMessage.error("Constructor cannot return a reference", getFileNode(), getLocationIn(), getController());
			
			return null;
		}
//		else if (isPointer())
//		{
//			SyntaxError.outputNewError("Constructor cannot return a pointer", getLocationIn());
//			
//			return null;
//		}
		
		ClassNode classNode = (ClassNode)getAncestorOfType(ClassNode.class, true);
		
		builder.append(generateCSourcePrototype()).append('\n');
		
		return builder.toString();
	}

	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#generateCSource()
	 */
	@Override
	public String generateCSource()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(generateCSourceSignature()).append('\n');
		
		builder.append('{').append('\n');
		
		builder.append("CCLASS_NEW(").append(getName()).append(", ").append(MethodNode.getObjectReferenceIdentifier());

		ClassNode classNode = (ClassNode)getAncestorOfType(ClassNode.class, true);
		
		if (!classNode.containsPrivateData())
		{
			builder.append(",");
		}
		
		builder.append(");").append('\n').append('\n');
		
//		builder.append(generateMethodAssignments()).append('\n');
		
		builder.append(generateFieldDefaultAssignments());
		
		for (int i = 0; i < getChildren().size(); i++)
		{
			TreeNode child = getChild(i);
			
			if (child != getParameterListNode())
			{
				builder.append(child.generateCSource());
			}
		}
		
		builder.append('\n').append("return ").append(MethodNode.getObjectReferenceIdentifier()).append(';').append('\n');
		
		builder.append('}').append('\n');
		
		return builder.toString();
	}
	
	/**
	 * This method returns a String that contains the code needed to
	 * assign the default null value to each uninitialized/uninstantiated
	 * field variables.
	 * 
	 * @return A String containing the code needed to assign default values
	 * 		to each uninitialized/uninstantiated field.
	 */
	private String generateFieldDefaultAssignments()
	{
		StringBuilder builder = new StringBuilder();
		
		ClassNode classNode = (ClassNode)getAncestorOfType(ClassNode.class);
		
		PublicFieldListNode fields = classNode.getFieldListNode().getPublicFieldListNode();
		
		for (int i = 0; i < fields.getChildren().size(); i++)
		{
			VariableNode child = (VariableNode)fields.getChild(i);
			
			builder.append(child.generateVariableUseOutput()).append(" = ").append(VariableNode.getNullText()).append(';').append('\n');
		}
		
		PrivateFieldListNode privateFields = classNode.getFieldListNode().getPrivateFieldListNode();
		
		for (int i = 0; i < privateFields.getChildren().size(); i++)
		{
			VariableNode child = (VariableNode)privateFields.getChild(i);
			
			builder.append(child.generateVariableUseOutput()).append(" = ").append(VariableNode.getNullText()).append(';').append('\n');
		}
		
		return builder.toString();
	}
	
	/**
	 * This method returns a String that contains the code needed to
	 * assign the c method pointers to the respective methods in the
	 * class.
	 * 
	 * @return A String containing the code needed to assign the methods.
	 */
	private String generateMethodAssignments()
	{
		StringBuilder builder = new StringBuilder();
		
		ClassNode classNode = (ClassNode)getAncestorOfType(ClassNode.class);
		
		MethodListNode methods = classNode.getMethodListNode();
		
		for (int i = 0; i < methods.getChildren().size(); i++)
		{
			MethodNode method = (MethodNode)methods.getChild(i);
			
			builder.append(MethodNode.getObjectReferenceIdentifier()).append("->").append(method.getName()).append(" = ").append(method.generateMethodName()).append(';').append('\n');
		}
		
		return builder.toString();
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.MethodNode#generateCSourcePrototype()
	 */
	public String generateCSourcePrototype()
	{
		return generateCSourceSignature().concat(";");
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.MethodNode#generateCSourceSignature()
	 */
	public String generateCSourceSignature()
	{
		StringBuilder builder = new StringBuilder();

		ClassNode classNode = (ClassNode)getAncestorOfType(ClassNode.class);
		
		if (isConstant())
		{
			builder.append(getConstantText()).append(' ');
		}
		
		builder.append(getType()).append('*');
		
		builder.append(' ');
		
		builder.append(Fathom.LANGUAGE_NAME.toLowerCase()).append('_').append(classNode.getName()).append('_');
		
		builder.append(classNode.getName()).append('(');
		
		builder.append(getParameterListNode().generateCSource());
		
		builder.append(')');
		
		return builder.toString();
	}
	
	/**
	 * Decode the given statement into a ConstructorNode instance, if
	 * possible. If it is not possible, this method returns null. A
	 * constructor must have the same name as the class that it is
	 * within. Constructors also do not have a return value.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>public ClassName()</li>
	 * 	<li>private ClassName(int numChildren, String name)</li>
	 * 	<li>public ClassName(TreeNode parent, Location location)</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		ConstructorNode instance.
	 * @param location The location of the statement in the source code.
	 * @return The generated node, if it was possible to translated it
	 * 		into a ConstructorNode.
	 */
	public static ConstructorNode decodeStatement(TreeNode parent, String statement, Location location)
	{
		int firstParenthIndex = statement.indexOf('(');
		int lastParenthIndex  = statement.lastIndexOf(')');
		
		if (firstParenthIndex >= 0)
		{
			// TODO: make better check for last parenth. Take a count of each of the starting parenthesis and
			// subtract the ending ones from the number.
			if (lastParenthIndex < 0)
			{
				SyntaxMessage.error("Expected a ')' ending parenthesis", parent.getFileNode(), location, parent.getController());
				
				return null;
			}
			
			String parameterList = statement.substring(firstParenthIndex + 1, lastParenthIndex);
			
			String parameters[]  = StringUtils.splitCommas(parameterList);
			
			statement = statement.substring(0, firstParenthIndex);
			
			ConstructorNode n = new ConstructorNode()
			{
				public void interactWord(String word, int wordNumber, Bounds bounds, int numWords)
				{
					setAttribute(word, wordNumber);
					
					setName(word);
				}
			};
			
			for (int i = 0; i < parameters.length; i++)
			{
				if (parameters[i].length() > 0)
				{
					ParameterNode param = ParameterNode.decodeStatement(parent, parameters[i], location);
					
					if (param == null)
					{
						SyntaxMessage.error("Incorrect parameter definition", parent.getFileNode(), location, parent.getController());
						
						return null;
					}
					
					n.getParameterListNode().addChild(param);
				}
			}
			
			n.iterateWords(statement);
			
			ClassNode classNode = (ClassNode)parent.getAncestorOfType(ClassNode.class, true);
			
			if (classNode.getName().equals(n.getName()))
			{
				n.setType(n.getName());
				
				return n;
			}
		}
		
		return null;
	}
	
	/**
	 * @see net.fathomsoft.fathom.tree.TreeNode#clone()
	 */
	@Override
	public ConstructorNode clone()
	{
		ConstructorNode node = new ConstructorNode();
		
		return clone(node);
	}
	
	/**
	 * Fill the given ConstructorNode with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public ConstructorNode clone(ConstructorNode node)
	{
		super.clone(node);
		
		return node;
	}
}
