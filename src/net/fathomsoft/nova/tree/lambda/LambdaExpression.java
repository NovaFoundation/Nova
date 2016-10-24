package net.fathomsoft.nova.tree.lambda;

import net.fathomsoft.nova.TestContext;
import net.fathomsoft.nova.ValidationResult;
import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.util.Location;
import net.fathomsoft.nova.util.StringUtils;
import net.fathomsoft.nova.util.SyntaxUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * {@link Node} extension that represents a lambda expression.
 * 
 * @author	Braden Steffaniak
 * @since	v0.2.45 Jun 5, 2014 at 9:00:04 PM
 * @version	v0.2.45 Jun 5, 2016 at 11:43:17 PM
 */
public class LambdaExpression extends Value
{
	public String[] variables; 
	
	public static final String OPERATOR = "->";
	public static final String UNNAMED_PARAMETER = "_";
	
	private static int id = 1;
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#Node(Node, Location)
	 */
	public LambdaExpression(Node temporaryParent, Location locationIn)
	{
		super(temporaryParent, locationIn);
	}
	
	/**
	 * Decode the given statement into a {@link LambdaExpression} instance, if
	 * possible. If it is not possible, this method returns null.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>x -> x + 1</li>
	 * 	<li>(x, i) -> Console.writeLine(x * i)</li>
	 * 	<li>asdf -> asdf.doSomething()</li>
	 * 	<li>{ Console.writeLine("In parameterless lambda") }</li>
	 * </ul>
	 * 
	 * @param parent The parent node of the statement.
	 * @param statement The statement to try to decode into a
	 * 		{@link LambdaExpression} instance.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @return The generated node, if it was possible to translated it
	 * 		into a {@link LambdaExpression}.
	 */
	public static Value decodeStatement(Node parent, String statement, Location location, boolean require)
	{
		String[] variables = null;
		int endingIndex = 0;
		boolean block = false;
		
		if (statement.startsWith("("))
		{
			endingIndex = StringUtils.findEndingMatch(statement, 0, '(', ')') + 1;
			
			variables = StringUtils.splitCommas(statement.substring(1, endingIndex - 1));
		}
		else if (statement.startsWith("{"))
		{
			variables = new String[0];
			
			endingIndex = StringUtils.findEndingMatch(statement, 0, '{', '}');

			block = true;
		}
		else
		{
			variables = new String[] { StringUtils.findNextWord(statement) };
			
			endingIndex = variables[0].length();
		}
		
		if (endingIndex > 0)
		{
			String operation = null;
			
			if (variables.length > 0)
			{
				operation = statement.substring(endingIndex).trim();
			}
			else
			{
				operation = statement.substring(1, endingIndex).trim();
			}
			
			if (operation.startsWith(OPERATOR) || variables.length == 0)
			{
				if (operation.startsWith(OPERATOR))
				{
					operation = operation.substring(OPERATOR.length()).trim();
				}
				
				if (operation.startsWith("{"))
				{
					block = true;
					
					operation = operation.substring(1, operation.length() - 1).trim();
				}
				
				MethodCall call = (MethodCall)parent.getAncestorOfType(MethodCall.class);
				
				ClassDeclaration[] classes = call.getDeclaringClasses();//.getReferenceNode().toValue().getTypeClass(false);
				
				if (classes == null || classes.length == 0)
				{
					return null;
				}
				
				ArrayList<MethodDeclaration> temp = new ArrayList<>();
				
				for (ClassDeclaration c : classes)
				{
					MethodDeclaration[] methods = c.getMethods(call.getName());
					
					for (MethodDeclaration m : methods)
					{
						temp.add(m);
					}
				}
				
				MethodDeclaration[] methods = temp.toArray(new MethodDeclaration[0]);
				
				if (methods.length > 0)
				{
					final String[] finalVars = variables;
					final int index = call.getArgumentList().getNumVisibleChildren();
					
					ArrayList<NovaMethodDeclaration> tempMethods = new ArrayList<>();
					
					Arrays.stream(methods)
							.filter(x -> {
								if (x instanceof NovaMethodDeclaration &&
										x.getParameterList().getNumVisibleChildren() > index &&
										x.getParameter(index) instanceof ClosureDeclaration)
								{
									ClosureDeclaration closure = (ClosureDeclaration)x.getParameter(index);
									
									if (closure.getParameterList().getNumVisibleChildren() >= finalVars.length)
									{
										return true;
									}
								}
								
								return false;
							})
							.forEach(x -> tempMethods.add((NovaMethodDeclaration)x));
					
					ArrayList<NovaMethodDeclaration> clone = new ArrayList<>();
					
					for (NovaMethodDeclaration m : tempMethods)
					{
						clone.add(m);
					}
					for (NovaMethodDeclaration x : clone)
					{
						if (x.doesOverride() && tempMethods.contains(x.getOverriddenMethod()))
						{
							tempMethods.remove(x.getOverriddenMethod());
						}
					}
					
					NovaMethodDeclaration[] validMethods = tempMethods.toArray(new NovaMethodDeclaration[0]);
					
					int maxD = 0;
					int maxI = 0;
					
					for (int i = 0; i < validMethods.length; i++)
					{
						for (int j = 0; j < validMethods.length; j++)
						{
							if (i != j)
							{
								int[] distances = validMethods[i].getDistancesFrom(validMethods[j].getParameterList());
								
								int distance = Arrays.stream(distances).sum();
								
								if (distance > maxD)
								{
									maxI = i;
									maxD = distance;
								}
							}
						}
					}
					
					NovaMethodDeclaration validMethod = validMethods[maxI];
					
					call.setDeclaration(validMethod);
					
					if (validMethods.length > 0)
					{
						final int[] i = new int[] { 0 };
						
						ClosureDeclaration closure = (ClosureDeclaration)((NovaMethodDeclaration)call.getDeclaration()).getParameter(index);
						
						final StringBuilder builder = new StringBuilder();
						
						closure.getParameterList().forEach(x ->
						{
							int id = i[0]++;
							
							Value value = closure.getParameterList().getParameter(id).getNovaTypeValue(call);
							
							String type = value.getNovaType();
							String name = "";
							
							if (finalVars.length > id)
							{
								name = finalVars[id];
							}
							else
							{
								name = "_" + (id + 1);
							}
							
							builder.append(builder.length() > 0 ? ", " : "").append(type).append(" ").append(name);
						});
						
						String parameters = builder.toString();
						
						String methodDeclaration = "static testLambda" + id++ + "(" + parameters + ")";
						
						if (closure.getType() != null)
						{
							methodDeclaration += " -> " + closure.getNovaTypeValue(call).getNovaType(call);
						}
						
						BodyMethodDeclaration bodyMethod = BodyMethodDeclaration.decodeStatement(parent.getParentClass(true), methodDeclaration, location.asNew(), require);
						
						if (bodyMethod != null)
						{
							LambdaMethodDeclaration method = new LambdaMethodDeclaration(bodyMethod.getParent(), bodyMethod.getLocationIn(), parent.getAncestorWithScopeOrClass().getScope());
							
							NovaMethodDeclaration parentMethod = parent.getParentMethod();
							
							int scopeId = bodyMethod.getScope().getID();
							bodyMethod.cloneTo(method);
							method.getScope().id = scopeId;
							method.isInstance = parentMethod.isInstance();//parentMethod.isStatic();
							method.objectReference = parentMethod.objectReference;
							method.methodCall = call;
							method.getParameterList().getReferenceParameter().setType(parent.getParentMethod(true).getParameterList().getReferenceParameter());
							
							method.getParentClass().addChild(method);
							
							boolean requiresReturn = method.getType() != null && (!block || !operation.contains("\n")) && SyntaxUtils.findCharInBaseScope(operation, ';') < 0;
							
							if (requiresReturn)
							{
								operation = "return " + operation;
							}
							
							if (block)
							{
								TreeGenerator generator = new TreeGenerator(null, operation, parent.getProgram().getTree());
								
								generator.traverseCode(method, 0, null, false);
							}
							else
							{
								Node node = SyntaxTree.decodeScopeContents(method, operation, location.asNew());
								method.addChild(node);
							}
							
							ClosureContextDeclaration declaration = new ClosureContextDeclaration(parent, location, method.context);
							
							if (method.getScope().getLastChild() instanceof Return)
							{
								if (call.isGenericType())
								{
									method.setType(((Return)method.getScope().getLastChild()).getReturnedNode());
								}
							}
							
							Closure methodReference = Closure.decodeStatement(parent, method.generateNovaClosureReference(method.getParentClass()), location.asNew(), require, method.getParentClass());
							
							if (methodReference != null)
							{
								methodReference.findDeclaration(method.getParentClass());
								
								method.contextDeclaration = declaration;
								method.closure = methodReference;
								
								Node root = parent.getStatementRootNode();
								
								parent.getStatementRootNode();
								
								if (root != null)
								{
									root.getNearestScopeAncestor().addChild(declaration);
									
									return methodReference;
								}
							}
						}
					}
				}
			}
		}
		
		return null;
	}
	
	@Override
	public ValidationResult validate(int phase)
	{
		ValidationResult result = super.validate(phase);
		
		if (result.skipValidation())
		{
			return result;
		}
		
		return result;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#clone(Node, Location, boolean)
	 */
	@Override
	public LambdaExpression clone(Node temporaryParent, Location locationIn, boolean cloneChildren)
	{
		LambdaExpression node = new LambdaExpression(temporaryParent, locationIn);
		
		return cloneTo(node, cloneChildren);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#cloneTo(Node)
	 */
	public LambdaExpression cloneTo(LambdaExpression node)
	{
		return cloneTo(node, true);
	}
	
	/**
	 * Fill the given {@link LambdaExpression} with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public LambdaExpression cloneTo(LambdaExpression node, boolean cloneChildren)
	{
		super.cloneTo(node, cloneChildren);
		
		node.id = id;
		
		return node;
	}
	
	/**
	 * Test the {@link LambdaExpression} class type to make sure everything
	 * is working properly.
	 * 
	 * @return The error output, if there was an error. If the test was
	 * 		successful, null is returned.
	 */
	public static String test(TestContext context)
	{
		
		
		return null;
	}
	
	@Override
	public Value getReturnedNode()
	{
		return null;
	}
	
	@Override
	public String getType(boolean checkCast)
	{
		Value returned = getReturnedNode();
		
		if (returned == null)
		{
			return null;
		}
		
		return getReturnedNode().getType(checkCast);
	}
	
	@Override
	public String getTypeStringValue()
	{
		Value returned = getReturnedNode();
		
		if (returned == null)
		{
			return null;
		}
		
		return getReturnedNode().getTypeStringValue();
	}
	
	@Override
	public void setTypeValue(String type)
	{
		Value returned = getReturnedNode();
		
		if (returned == null)
		{
			return;
		}
		
		getReturnedNode().setTypeValue(type);
	}

	@Override
	public int getArrayDimensions()
	{
		Value returned = getReturnedNode();
		
		if (returned == null)
		{
			return 0;
		}
		
		return getReturnedNode().getArrayDimensions() - getReturnedNode().getArrayAccessDimensions();
	}

	@Override
	public void setArrayDimensions(int arrayDimensions)
	{
		Value returned = getReturnedNode();
		
		if (returned == null)
		{
			return;
		}
		
		returned.setArrayDimensions(returned.getArrayDimensions());
	}

	@Override
	public byte getDataType(boolean checkGeneric)
	{
		Value returned = getReturnedNode();
		
		if (returned == null)
		{
			return 0;
		}
		
		return getReturnedNode().getDataType();
	}

	@Override
	public void setDataType(byte type)
	{
		Value returned = getReturnedNode();
		
		if (returned == null)
		{
			return;
		}
		
		getReturnedNode().setDataType(type);
	}
}