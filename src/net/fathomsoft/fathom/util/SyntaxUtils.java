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
package net.fathomsoft.fathom.util;

import java.util.regex.Matcher;

import net.fathomsoft.fathom.error.SyntaxMessage;
import net.fathomsoft.fathom.tree.ClassNode;
import net.fathomsoft.fathom.tree.DeclarationNode;
import net.fathomsoft.fathom.tree.FileNode;
import net.fathomsoft.fathom.tree.MethodNode;
import net.fathomsoft.fathom.tree.ParameterListNode;
import net.fathomsoft.fathom.tree.ParameterNode;
import net.fathomsoft.fathom.tree.TreeNode;
import net.fathomsoft.fathom.tree.variables.FieldNode;

/**
 * Class used for getting information about the Syntax of Fathom.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Mar 15, 2014 at 7:55:00 PM
 * @version	v0.2.2 Apr 29, 2014 at 7:00:56 PM
 */
public class SyntaxUtils
{
	/**
	 * Get whether or not the given String value represents one of the
	 * following:
	 * <ul>
	 * 	<li>{@link #isStringLiteral(String) String literal}</li>
	 * 	<li>{@link #isCharLiteral(String) Character literal}</li>
	 * 	<li>{@link #isNumber(String) Number literal}</li>
	 * </ul>
	 * 
	 * @param value The String of text to validate.
	 * @return Whether or not the given String value represents a literal.
	 */
	public static boolean isLiteral(String value)
	{
		return isCharLiteral(value) || isStringLiteral(value) || isNumber(value);
	}
	
	/**
	 * Get whether or not the given String value represents a character
	 * literal. A character literal consists of a a single character
	 * surrounded by single quotes.<br>
	 * <br>
	 * Possible inputs:
	 * <ul>
	 * 	<li>'a'</li>
	 * 	<li>'2'</li>
	 * 	<li>'%'</li>
	 * 	<li>'/'</li>
	 * </ul>
	 * 
	 * @param value The String of text to validate.
	 * @return Whether or not the String represents a character literal.
	 */
	public static boolean isCharLiteral(String value)
	{
		if (value.length() != 3)
		{
			return false;
		}
		
		return value.charAt(0) == '\'' && value.charAt(value.length() - 1) == '\'';
	}
	
	/**
	 * Get whether or not the given String value represents a String
	 * literal. A String literal consists of a collection of characters
	 * surrounded by double quotes.<br>
	 * <br>
	 * Possible inputs:
	 * <ul>
	 * 	<li>"This is + asdf-523$#$%#4 a String input"</li>
	 * 	<li>"123123123"</li>
	 * 	<li>"More string inputs"</li>
	 * 	<li>"\"Fake quotes around it.\""</li>
	 * </ul>
	 * 
	 * @param value The String of text to validate.
	 * @return Whether or not the String represents a String literal.
	 */
	public static boolean isStringLiteral(String value)
	{
		if (value.length() < 2)
		{
			return false;
		}
		
		return value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"';
	}
	
	/**
	 * Get whether or not the given String value represents a number.<br>
	 * <br>
	 * Possible inputs:
	 * <ul>
	 * 	<li>-1231412</li>
	 * 	<li>1231412</li>
	 * 	<li>4141.12312</li>
	 * 	<li>-4141.12312</li>
	 * </ul>
	 * 
	 * @param value The String of text to validate.
	 * @return Whether or not the String represents a number.
	 */
	public static boolean isNumber(String value)
	{        
	    boolean seenDot     = false;
	    boolean seenExp     = false;
	    boolean justSeenExp = false;
	    boolean seenDigit   = false;
	    
	    for (int i = 0; i < value.length(); i++)
	    {
	        char c = value.charAt(i);
	        
	        if (c >= '0' && c <= '9')
	        {
	            seenDigit = true;
	            
	            continue;
	        }
	        
	        if ((c == '-' || c == '+') && (i == 0 || justSeenExp))
	        {
	            continue;
	        }
	        
	        if (c == '.' && !seenDot)
	        {
	            seenDot = true;
	            
	            continue;
	        }
	        
	        justSeenExp = false;
	        
	        if ((c == 'e' || c == 'E') && !seenExp)
	        {
	            seenExp     = true;
	            justSeenExp = true;
	            
	            continue;
	        }
	        
	        return false;
	    }
	    
	    if (!seenDigit)
	    {
	        return false;
	    }
	    
	    try
	    {
	        Double.parseDouble(value);
	        
	        return true;
	    }
	    catch (NumberFormatException e)
	    {
	        return false;
	    }
	}
	
	/**
	 * Get whether the specified type is primitive.<br>
	 * <br>
	 * Primitive types include:
	 * <ul>
	 * 	<li>int</li>
	 * 	<li>char</li>
	 * 	<li>long_long (aka long)</li>
	 * 	<li>boolean</li>
	 * 	<li>short</li>
	 * 	<li>float</li>
	 * 	<li>double</li>
	 * 	<li>void (lightly)</li>
	 * </ul>
	 * 
	 * @param type The type to check.
	 * @return Whether or not the type is primitive.
	 */
	public static boolean isPrimitiveType(String type)
	{
		return type.equals("int") || type.equals("char") || type.equals("long_long") || type.equals("boolean") || type.equals("short") || type.equals("float") || type.equals("double") || type.equals("void");
	}
	
	/**
	 * Get whether or not the given String is a valid variable assignment.
	 * Assignments contain an identifier or array access on the left
	 * hand side of the assignment, and anything that returns a value on
	 * the right hand side.<br>
	 * <br>
	 * For example:
	 * <blockqoute><pre>
	 * variable_NAME1 = (getSize() + 5) / array[index]</pre></blockquote>
	 * 
	 * @param statement The String of text to validate.
	 * @return Whether or not the given String is a valid variable
	 * 		assignment.
	 */
	public static boolean isVariableAssignment(String statement)
	{
		return Regex.indexOf(statement, Patterns.PRE_EQUALS_SIGN) == 0;
	}
	
	/**
	 * Get whether or not the given String is a valid identifier.
	 * Identifiers can contain characters of the following types:
	 * <ul>
	 * 	<li>A-Z</li>
	 * 	<li>a-z</li>
	 * 	<li>0-9</li>
	 * 	<li>The '_' character (underscore)</li>
	 * </ul>
	 * It should also be noted that numbers cannot start an identifier.
	 * 
	 * @param value The String of text to validate.
	 * @return Whether or not the given String is a valid identifier.
	 */
	public static boolean isValidIdentifier(String value)
	{
		if (Regex.matches(value, Patterns.IDENTIFIER))
		{
			return !SyntaxUtils.isNumber(value.charAt(0) + "");
		}
		
		return false;
	}
	
	/**
	 * Get whether or not the given String is a valid identifier access.<br>
	 * <br>
	 * For example: "<code>getName().publicVarName.methodName().var[72]</code>"
	 * 
	 * @param value The String of text to validate.
	 * @return Whether or not the given String is a valid identifier
	 * 		access.
	 */
	public static boolean isValidIdentifierAccess(String value)
	{
		if (value.length() <= 0)
		{
			return false;
		}
		
		String values[] = value.split("\\s*\\.\\s*");
		
		if (values.length <= 0)
		{
			return false;
		}
		
		for (int i = 0; i < values.length; i++)
		{
			String v = values[i];
			
			if (!isValidIdentifier(v) && !isMethodCall(v) && !isValidArrayAccess(v))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Get whether or not the given statement is an array access.<br>
	 * <br>
	 * For example:
	 * <blockquote><pre>
	 * variableName[index]</pre></blockquote>
	 * 
	 * @param value The statement to test.
	 * @return Whether or not the given statement is an array access.
	 */
	public static boolean isValidArrayAccess(String value)
	{
		Bounds bounds = Regex.boundsOf(value, Patterns.ARRAY_ACCESS);
		
		return bounds.getStart() == 0 && bounds.getEnd() == value.length();
	}
	
	/**
	 * Get whether or not the given statement is a method call.<br>
	 * <br>
	 * For example:
	 * <blockquote><pre>
	 * containingInstance.methodName(arguments, arguments, ..., arguments)</pre></blockquote>
	 * 
	 * @param statement The statement to test.
	 * @return Whether or not the given statement is a method call.
	 */
	public static boolean isMethodCall(String statement)
	{
		return Regex.startsWith(statement, Patterns.PRE_METHOD_CALL);
	}
	
	/**
	 * Get whether or not the given statement is an array
	 * initialization.<br>
	 * <br>
	 * For example:
	 * <blockquote><pre>
	 * new dataType[size]...[size]</pre></blockquote>
	 * 
	 * @param statement The statement to test.
	 * @return Whether or not the given statement is an array
	 * 		initialization.
	 */
	public static boolean isArrayInitialization(String statement)
	{
		return Regex.matches(statement, Patterns.ARRAY_INIT);
	}
	
	/**
	 * Calculate the number of dimensions that the given array has, if
	 * any.<br>
	 * <br>
	 * For example:
	 * <blockquote><pre>
	 * int array[][][] = new int[5][6][7];</pre></blockquote>
	 * The above array declaration has three dimensions. In essence, the
	 * number of dimensions is the amount of brackets that the array
	 * variable declaration contains.<br>
	 * <br>
	 * However, you need to pass only one part of the above code example.
	 * Either pass the declaration, or the instantiation, or else the
	 * number of dimensions will be counted twice.
	 * 
	 * @param statement The statement to find the number of dimensions
	 * 		from.
	 * @param contentPossible Whether or not content is allowed to exist
	 * 		within the set of brackets.
	 * @return The number of dimensions that the given array has, if any.
	 */
	public static int calculateArrayDimensions(String statement, boolean contentPossible)
	{
		int num   = 0;
		int index = statement.indexOf('[') + 1;
		
		while (index > 0)
		{
			int endIndex = statement.indexOf(']', index);
			
			if (endIndex < 0)
			{
				return -1;
			}
			
			String brackets = statement.substring(index - 1, endIndex + 1);
			
			if (!Regex.matches(brackets, Patterns.EMPTY_ARRAY_BRACKETS) && !(contentPossible && Regex.matches(brackets, Patterns.ARRAY_BRACKETS)))
			{
				return -1;
			}
			
			num++;
			
			index = statement.indexOf('[', endIndex + 1) + 1;
		}
		
		return num;
	}
	
	/**
	 * Get whether or not the given MethodNode is a valid main method.
	 * The main method has the method header as the following:
	 * <blockquote><pre>
	 * public static void main(String args[])
	 * {
	 * 	...
	 * }</pre></blockquote>
	 * 
	 * @param method The MethodNode instance to validate.
	 * @return Whether or not the given MethodNode is a valid main method.
	 */
	public static boolean isMainMethod(MethodNode method)
	{
		if (method.getName().equals("main") && method.isStatic() && method.getType().equals("void") && method.getVisibility() == FieldNode.PUBLIC)
		{
			ParameterListNode params = (ParameterListNode)method.getParameterListNode();
			
			if (params.getChildren().size() == 2)
			{
				ParameterNode param = (ParameterNode)params.getChild(1);
				
				if (param.getType().equals("String") && param.isArray())
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Get whether or not the given statement is an instantiation. For
	 * more details on what an instantiation consists of see
	 * {@link net.fathomsoft.fathom.tree.InstantiationNode#decodeStatement(TreeNode, String, Location)}.
	 * 
	 * @param statement The statement to test.
	 * @return Whether or not the given statement is an instantiation.
	 */
	public static boolean isInstantiation(String statement)
	{
		return Regex.indexOf(statement, Patterns.PRE_INSTANTIATION) == 0;
	}
	
	/**
	 * Get whether or not the given DeclarationNode is able to be accessed
	 * from the given ClassNode context.
	 * 
	 * @param accessedFrom The ClassNode context that the DeclarationNode
	 * 		was accessed from.
	 * @param declaration The DeclarationNode that was accessed from the
	 * 		given ClassNode context.
	 * @return Whether or not the given DeclarationNode is able to be
	 * 		accessed from the given ClassNode context.
	 */
	private static boolean isAccessibleFrom(ClassNode accessedFrom, DeclarationNode declaration)
	{
		if (accessedFrom.isAncestorOf(declaration))
		{
			return true;
		}
		
		int visibility = declaration.getVisibility();
		
		return visibility == DeclarationNode.PUBLIC || visibility == FieldNode.VISIBLE;
	}
	
	/**
	 * Get the ClassNode that contains the accessed identifier. For more
	 * information on what an identifierAccess looks like, see
	 * {@link #isValidIdentifierAccess(String)}.
	 * 
	 * @param reference The ClassNode context that the identifier was
	 * 		accessed from.
	 * @param identifierAccess The trace of the identifier that was
	 * 		accessed.
	 * @return The ClassNode that contains the accessed identifier.
	 */
	public static ClassNode getClassType(ClassNode reference, String identifierAccess)
	{
		if (!isValidIdentifierAccess(identifierAccess))
		{
			return null;
		}
		
		String values[] = identifierAccess.split("\\s*\\.\\s*");
		String output[] = new String[values.length - 1];
		
		FileNode file = reference.getFileNode();
		
		if (file.isExternalImport(values[0]))
		{
			return null;
		}
		
		System.arraycopy(values, 1, output, 0, output.length);
		
		return getClassType(reference, output);
	}
	
	/**
	 * Get the ClassNode that contains the accessed identifier.
	 * 
	 * @param reference The ClassNode context that the identifier was
	 * 		accessed from.
	 * @param identifiers A list of identifiers leading up to the
	 * 		identifier that is being accessed.
	 * @return The ClassNode that contains the accessed identifier.
	 */
	private static ClassNode getClassType(ClassNode reference, String identifiers[])
	{
		if (identifiers.length < 2)
		{
			return reference;
		}
		
		String identifier = identifiers[0];
		
		ClassNode current = null;
		
		if (!identifier.equals("this"))
		{
			identifier = getIdentifierName(identifier);
			
			FileNode f = (FileNode)reference.getAncestorOfType(FileNode.class);
			
			if (f.getImportListNode().containsImport(identifier))
			{
				return f.getProgramNode().getClass(identifier);
			}
			
			DeclarationNode dec = reference.getDeclaration(identifier);
			
			if (!isAccessibleFrom(reference, dec))
			{
				SyntaxMessage.error("Variable '" + dec.getName() + "' is not visible", reference.getController());
			}
			
			current = dec.getProgramNode().getClass(dec.getType());
			
			if (identifiers.length <= 2)
			{
				return current;
			}
		}
		
		String output[] = new String[identifiers.length - 1];
		
		System.arraycopy(identifiers, 1, output, 0, output.length);
		
		return getClassType(current, output);
	}
	
	/**
	 * Get the identifier name that is represented by the given access
	 * String variable.<br>
	 * <br>
	 * For example:
	 * <ul>
	 * 	<li><code>testName()</code> <i>returns</i> <code>testName</code></li>
	 * 	<li><code>arrayAccess[54]</code> <i>returns</i> <code>arrayAccess</code></li>
	 * 	<li><code>idName32</code> <i>returns</i> <code>idName32</code></li>
	 * 	<li><code>methodCall (String name, int args)</code> <i>returns</i> <code>methodCall</code></li>
	 * </ul>
	 * 
	 * @param access The identifier access to get the identifier name
	 * 		from.
	 * @return The name of the identifier from given access String.
	 */
	public static String getIdentifierName(String access)
	{
		if (isMethodCall(access))
		{
			int endIndex = StringUtils.findNextNonWhitespaceIndex(access, access.indexOf('(') - 1, -1) + 1;
			
			access       = access.substring(0, endIndex);
		}
		else if (isValidArrayAccess(access))
		{
			int endIndex = StringUtils.findNextNonWhitespaceIndex(access, access.indexOf('[') - 1, -1) + 1;
			
			access       = access.substring(0, endIndex);
		}
		
		return access;
	}
	
	/**
	 * Format the text to follow syntactical rules.
	 * 
	 * @param text The String to format.
	 * @return The formatted version of the String.
	 */
	public static String formatText(String text)
	{
		StringBuilder builder       = new StringBuilder();
		
		StringBuilder tabs          = new StringBuilder();
		
		Matcher       formatMatcher = Patterns.FORMATTER_PATTERN.matcher(text);
		
		int           lastIndex     = 0;
		boolean       sameLine      = false;
		
		while (formatMatcher.find())
		{
			char c = text.charAt(formatMatcher.start());
			
			if (c == '{' || c == '(')
			{
				tabs.append('\t');
				
				sameLine = true;
			}
			else if (c == '}' || c == ')')
			{
				if (tabs.length() > 0)
				{
					tabs.deleteCharAt(0);
				}
				
				if (builder.length() > 0 && !sameLine)
				{
					builder.deleteCharAt(builder.length() - 1);
				}
			}
			else if (c == '\n')
			{
				String substring = text.substring(lastIndex, formatMatcher.start());
				
				builder.append(substring).append('\n').append(tabs);
				
				lastIndex = formatMatcher.start() + 1;
				
				sameLine = false;
			}
		}
		
		builder.append(tabs).append(text.substring(lastIndex, text.length()));
		
		return builder.toString();
	}
	
	/**
	 * Get whether or not the given type String is a representation of
	 * an external type in the FileNode. Consider the following:
	 * <blockquote><pre>
	 * import "externalFile.h";
	 * 
	 * public class Example
	 * {
	 * 	public void exampleMethod()
	 * 	{
	 * 		externalFile.functionCall();
	 * 	}
	 * }</pre></blockquote>
	 * The <code>externalFile.functionCall()</code> is an external type
	 * because the "<code>externalFile</code>" is imported as an external
	 * header file at the top of the file.
	 * 
	 * @param file The FileNode that contains the possible external
	 * 		statement.
	 * @param statement The statement that is possibly an external type.
	 * @return Whether or not the statement is an external type.
	 */
	public static boolean isExternal(FileNode file, String statement)
	{
		int index = statement.indexOf('.');
		
		if (index < 0)
		{
			return false;
		}
		
		String externalName = statement.substring(0, index);
		
		return file.getImportListNode().isExternal(externalName);
	}
}