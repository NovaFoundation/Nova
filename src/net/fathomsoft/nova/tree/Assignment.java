package net.fathomsoft.nova.tree;

import net.fathomsoft.nova.TestContext;
import net.fathomsoft.nova.ValidationResult;
import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.error.UnimplementedOperationException;
import net.fathomsoft.nova.tree.generics.GenericTypeParameter;
import net.fathomsoft.nova.tree.variables.FieldDeclaration;
import net.fathomsoft.nova.tree.variables.Variable;
import net.fathomsoft.nova.tree.variables.VariableDeclaration;
import net.fathomsoft.nova.tree.variables.VariableDeclarationList;
import net.fathomsoft.nova.util.Location;
import net.fathomsoft.nova.util.StringUtils;
import net.fathomsoft.nova.util.SyntaxUtils;

import java.util.ArrayList;

/**
 * Node extension that contains information describing a variable
 * assignment.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Jan 5, 2014 at 9:19:44 PM
 * @version	v0.2.41 Dec 17, 2014 at 7:48:17 PM
 */
public class Assignment extends Value
{
	public boolean wasDeclaration;
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#Node(Node, Location)
	 */
	public Assignment(Node temporaryParent, Location locationIn)
	{
		super(temporaryParent, locationIn);
		
		ArgumentList assignees = new ArgumentList(this, locationIn.asNew());
		
		addChild(assignees);
	}
	
	@Override
	public int getNumDefaultChildren()
	{
		return super.getNumDefaultChildren() + 1;
	}
	
	public ArgumentList getAssigneeNodes()
	{
		return (ArgumentList)getChild(super.getNumDefaultChildren() + 0);
	}
	
	public int getNumAssignees()
	{
		return getAssigneeNodes().getNumVisibleChildren();
	}
	
	public boolean containsMultipleAssignees()
	{
		return getNumAssignees() > 1;
	}
	
	/**
	 * Get the node that is having its value modified. In other words,
	 * the left hand value of the equation. For instance, in the
	 * statement: "<code>Int j = 35</code>" <u><code>Int j</code></u> is
	 * the left hand value of the equation.
	 * 
	 * @return The node that represents the variable that is being
	 * 		assigned.
	 */
	public Value getAssigneeNode()
	{
		return (Value)getAssigneeNodes().getVisibleChild(0);
	}
	
	public void setAssigneeNode(Value assignee)
	{
		if (getNumAssignees() <= 0)
		{
			getAssigneeNodes().addChild(assignee);
		}
		else
		{
			getAssigneeNodes().replace(getAssigneeNode(), assignee);
		}
	}
	
	/**
	 * Get the node that is having its value modified. In other words,
	 * the left hand value of the equation. For instance, in the
	 * statement: "<code>int j = 35</code>" <u><code>int j</code></u> is
	 * the left hand value of the equation.
	 * 
	 * @return The node that represents the variable that is being
	 * 		assigned.
	 */
	public Accessible getAssignee()
	{
		return getAssigneeNode() instanceof Accessible ? (Accessible)getAssigneeNode() : null;
	}
	
	public Value getAssignedNodeValue()
	{
		Accessible last = getAssignee().getLastAccessedNode();
		
		if (last == null)
		{
			last = (Accessible)getAssigneeNode();
		}
		
		return (Value)last;
	}
	
	public Variable getAssignedNode()
	{
		return (Variable)getAssignedNodeValue();
	}
	
	@Override
	public boolean setType(String type, boolean require, boolean checkType, boolean checkDataType)
	{
		return getAssignmentNode().setType(type, require, checkType, checkDataType);
	}
	
	@Override
	public Value[] getTypes()
	{
		return getAssignmentNode().getTypes();
	}
	
	@Override
	public String getType(boolean checkCast)
	{
		return getAssignmentNode().getReturnedNode().getType(checkCast);
	}
	
	@Override
	public Type getTypeObject()
	{
		return getAssignmentNode().getReturnedNode().getTypeObject();
	}
	
	
	@Override
	public GenericTypeParameter getGenericTypeParameter(boolean checkArray)
	{
		return getAssignmentNode().getReturnedNode().getGenericTypeParameter(checkArray);
	}
	
	@Override
	public Value getReturnedNode()
	{
		return getAssignmentNode().getReturnedNode();
	}
	
	@Override
	public Value getNovaTypeValue(Value context)
	{
		return getAssignmentNode().getReturnedNode().getNovaTypeValue(getAssignmentNode().getReturnedNode());
	}
	
	@Override
	public String getTypeStringValue()
	{
		return getAssignmentNode().getTypeStringValue();
	}
	
	@Override
	public void setTypeValue(String type)
	{
		getAssignmentNode().setTypeValue(type);
	}
	
	@Override
	public int getArrayDimensions()
	{
		return getAssignmentNode().getArrayDimensions() - getArrayAccessDimensions();
	}
	
	@Override
	public void setArrayDimensions(int arrayDimensions)
	{
		getAssignmentNode().setArrayDimensions(arrayDimensions);
	}
	
	@Override
	public byte getDataType(boolean checkGeneric)
	{
		return getAssignmentNode().getDataType(checkGeneric);
	}
	
	@Override
	public void setDataType(byte type)
	{
		getAssignmentNode().setDataType(type);
	}
	
	/**
	 * Get the node that is being used to set the value of the assignee
	 * node. In other words, the right hand value of the equation. For
	 * instance, in the statement: "<code>int j = 35</code>"
	 * <u><code>35</code></u> is the right hand value of the equation.
	 * 
	 * @return The node that represents the value that the assignee
	 * 		variable is being assigned to.
	 */
	public Value getAssignmentNode()
	{
		return (Value)getChild(super.getNumDefaultChildren() + 1);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#generateNovaInput(StringBuilder, boolean)
	 */
	public StringBuilder generateNovaInput(StringBuilder builder, boolean outputChildren)
	{
		return getAssigneeNode().generateNovaInput(builder, outputChildren).append(" = ").append(getAssignmentNode().generateNovaInput(outputChildren));
	}
	
	public static Assignment generateDefault(Node parent, Location location)
	{
		Assignment n = new Assignment(parent, location);
		
		
		
		return n;
	}
	
	@Override
	public Node getDecodedParent()
	{
		return getReturnedNode();
	}
	
	/**
	 * Decode the given statement into an Assignment if possible. If
	 * it is not possible, then null is returned.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>Int variableName = 45</li>
	 * 	<li>personsName = "Bob"</li>
	 * 	<li>Person myPeep = new Person(54)</li>
	 * 	<li>area = width * height / 2</li>
	 * 	<li>Int newSize = array.getSize() + 5</li>
	 * </ul>
	 * 
	 * @param parent The parent of the current statement.
	 * @param statement The statement to decode into an Assignment.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @return The new Assignment if it decodes properly. If not,
	 * 		it returns null.
	 */
	public static Assignment decodeStatement(Node parent, String statement, Location location, boolean require)
	{
		return decodeStatement(parent, statement, location, require, true);
	}
	
	/**
	 * Decode the given statement into an Assignment if possible. If
	 * it is not possible, then null is returned.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>Int variableName = 45</li>
	 * 	<li>personsName = "Bob"</li>
	 * 	<li>Person myPeep = new Person(54)</li>
	 * 	<li>area = width * height / 2</li>
	 * 	<li>Int newSize = array.getSize() + 5</li>
	 * </ul>
	 * 
	 * @param parent The parent of the current statement.
	 * @param statement The statement to decode into an Assignment.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @param addDeclaration Whether or not to add the declaration to the
	 * 		nearest scope, if the left hand value of the equation is a
	 * 		variable declaration.
	 * @return The new Assignment if it decodes properly. If not,
	 * 		it returns null.
	 */
	public static Assignment decodeStatement(Node parent, String statement, Location location, boolean require, boolean addDeclaration)
	{
		return decodeStatement(parent, statement, location, require, addDeclaration, null, null);
	}
	
	/**
	 * Decode the given statement into an Assignment if possible. If
	 * it is not possible, then null is returned.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>Int variableName = 45</li>
	 * 	<li>personsName = "Bob"</li>
	 * 	<li>Person myPeep = new Person(54)</li>
	 * 	<li>area = width * height / 2</li>
	 * 	<li>Int newSize = array.getSize() + 5</li>
	 * </ul>
	 * 
	 * @param parent The parent of the current statement.
	 * @param statement The statement to decode into an Assignment.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @param addDeclaration Whether or not to add the declaration to the
	 * 		nearest scope, if the left hand value of the equation is a
	 * 		variable declaration.
	 * @return The new Assignment if it decodes properly. If not,
	 * 		it returns null.
	 */
	public static Assignment decodeStatement(Node parent, String statement, Location location, boolean require, boolean addDeclaration, Value[] assignees, Node assignment)
	{
		return decodeStatement(parent, statement, location, require, addDeclaration, assignees, assignment, true);
	}
	
	public static Assignment decodeStatement(Node parent, String statement, Location location, boolean require, boolean addDeclaration, Value[] assignees, Node assignment, boolean checkType)
	{
		if (!SyntaxUtils.isVariableAssignment(statement))
		{
			return null;
		}
		
		Assignment n    = new Assignment(parent, location);
		
		int equalsIndex = SyntaxUtils.findCharInBaseScope(statement, '=');
		int endIndex    = StringUtils.findNextNonWhitespaceIndex(statement, equalsIndex - 1, -1) + 1;
		
		String variable = statement.substring(0, endIndex);
		
		Location varLoc = location.asNew();
		varLoc.getBounds().setEnd(varLoc.getStart() + endIndex);
		
		String[] assigneesStr = StringUtils.splitCommas(variable, 1);
		
//		if (!n.decodeAssignee(assignees[0], varLoc, require, addDeclaration))
//		{
//			return null;
//		}
		
		for (int i = 0; i < assigneesStr.length; i++)
		{
			if (!n.decodeAssignee(assigneesStr[i], varLoc, require, addDeclaration, assignees, checkType))
			{
				SyntaxMessage.queryError("Could not parse assigned value", n, require);
				
				return null;
			}
		}
		
		int rhsIndex = StringUtils.findNextNonWhitespaceIndex(statement, equalsIndex + 1);
		
		if (rhsIndex > 0)
		{
			// Right-hand side of the equation.
			String rhs = statement.substring(rhsIndex);
			
			Location newLoc = location.asNew();
			newLoc.setBounds(location.getStart() + rhsIndex, location.getStart() + statement.length());
			
			if (assignment == null)
			{
				assignment = n.decodeRightHandSide(n, rhs, newLoc, require);
			}
			
			if (assignment == null)
			{
				if (addDeclaration)
				{
					n.removeDeclaration();
				}
				
				return null;
			}
			
			n.addChild(assignment);
			
			return n;
		}
		
		return null;
	}
	
	@Override
	public boolean onAfterDecoded()
	{
		if (super.onAfterDecoded())
		{
			getAssignmentNode().convertToPrimitiveType();
			
			if (getAssignedNodeValue() instanceof Variable)
			{
				getAssignedNode().getDeclaration().convertToPrimitiveType();
				
				if (getAssignedNode().getDeclaration() instanceof ArrayAccessorMethod)
				{
					return true;
				}
			}
			
			if (getAssignedNodeValue() instanceof Variable)
			{
				getAssignedNode().getDeclaration().onAfterDecoded();
			}
			
			if (getAssignedNodeValue().getType() != null && getAssignedNodeValue().isImmutable() && getAssignedNodeValue() instanceof Variable)
			{
				if (getAssignedNode().getImmutableAnnotation() != null)
				{
					getAssignedNode().getImmutableAnnotation().convertAssignment(getAssignmentNode());
				}
			}
			
			validateCompatible();
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onAdded(Node parent)
	{
		if (getAssignedNodeValue() instanceof Variable && getAssignedNode().getDeclaration() instanceof ArrayAccessorMethod)
		{
			Variable assigned = getAssignedNode();
			
			SyntaxUtils.replaceArrayAccessWithSetter(assigned, getAssignmentNode());
			
			replaceWith(getAssigneeNode());
		}
		else
		{
			super.onAdded(parent);
		}
	}
	
	private boolean decodeAssignee(String assignee, Location loc, boolean require, boolean addDeclaration, Value[] assignees, boolean checkType)
	{
		if (assignees == null || assignees.length <= 0)
		{
			Value varNode = LocalDeclaration.decodeStatement(this, assignee, loc, false);
			
			if (varNode == null)
			{
				varNode = SyntaxTree.decodeValue(this, assignee, loc, require, checkType);
				
				if (varNode == null)
				{
					return SyntaxMessage.queryError("Undeclared variable '" + assignee + "'", this, loc, require);
				}
			}
			
			if (varNode.getReturnedNode() instanceof VariableDeclaration)
			{
				if (addDeclaration)
				{
					varNode = addDeclaration((VariableDeclaration)varNode);
				}
				
				wasDeclaration = true;
			}
			else if (varNode.getReturnedNode() instanceof Variable)
			{
				validateAuthorization((Variable)varNode.getReturnedNode());
			}
			
			if (varNode instanceof Cast)
			{
				return false;
			}
			
			getAssigneeNodes().addChild(varNode);
		}
		else
		{
			for (Value assign : assignees)
			{
				getAssigneeNodes().addChild(assign);
				
				assign.onAfterDecoded();
			}
		}
		
		return true;
	}
	
	private boolean checkImplicitType(Value assigned, Value assignment, boolean require)
	{
		LocalDeclaration declaration = null;
		
		if (assigned instanceof Variable)
		{
			if (((Variable)assigned).getDeclaration() instanceof LocalDeclaration)
			{
				declaration = (LocalDeclaration)((Variable)assigned).getDeclaration();
			}
		}
		else if (assigned instanceof LocalDeclaration)
		{
			declaration = (LocalDeclaration)assigned;
		}
		
		if (declaration != null)
		{
			if (declaration.isImplicit())
			{
				Value previousType = declaration.getTypeValue();
				
				if (declaration.getImplicitType() == null)
				{
					/*IValue value = new IValue(getParent(), getLocationIn());
					value.setType(assignment.getNovaType());*/
					
					declaration.setImplicitType(assignment);
				}
				else if (previousType.getArrayDimensions() != assignment.getArrayDimensions())
				{
					return SyntaxMessage.queryError("Incompatible array assignment. Assigned node has " + declaration.getArrayDimensions() + " dimensions, when assignment has " + assignment.getArrayDimensions() + " dimensions", this, require);
				}
				else if (previousType.getTypeClass().hasCommonAncestor(assignment.getTypeClass()))
				{
					ClassDeclaration base = previousType.getTypeClass().getCommonAncestor(assignment.getTypeClass());
					
//					Value value = declaration.getTypeValue();
//					value.setType(base.generateNovaType().toString());
					
					// Add the common ancestor type to the scope
					getAncestorWithScope().getScope().addImplicitVariableAssignment(declaration, base, assignment);
				}
				else
				{
					previousType.getTypeClass();
					assignment.getTypeClass();
					return SyntaxMessage.queryError("Incompatible implicit type '" + declaration.getType() + "' being assigned to type '" + assignment.getType() + "'", this, require);
				}
				
				//Value assignmentType = assignment.getNovaTypeValue(assignment);
				declaration.setType(assignment);//assignment.getNovaType());
				//declaration.setType(assignmentType.getType(), true, false, false);
				declaration.correspondingImplicit = assignment;
				
				if (assignment.getType() != null && (assignment.isPrimitive() && assignment.getType().equals("Byte") || assignment.getType().equals("Short")))
				{
					declaration.setType("Int");
				}
				
//				GenericTypeArgumentList args = assignment.getGenericTypeArgumentList();
//				
//				declaration.getGenericTypeArgumentList().slaughterEveryLastChild();
//				
//				if (args != null && args.getNumVisibleChildren() > 0)
//				{
//					for (GenericTypeArgument arg : assignment.getGenericTypeArgumentList())
//					{
//						declaration.getGenericTypeArgumentList().addChild(arg.clone(null, declaration.getLocationIn()));
//					}
//				}
//				else
//				{
//					declaration.addDefaultGenericTypeArguments();
//				}
			}
		}
		
		return true;
	}
	
	private boolean validateCompatible()
	{
		Value returnedLeft  = getAssigneeNode().getReturnedNode();
		Value returnedRight = getAssignmentNode().getReturnedNode();
		
		if (!checkImplicitType(returnedLeft, returnedRight, true))
		{
			return false;
		}
		if (returnedLeft.getTypeObject() instanceof FunctionType)
		{
			return true;
		}
		
		String leftType  = returnedLeft.getNovaType();
		String rightType = returnedRight.getNovaType();
		
		if (!Literal.isNullLiteral(returnedRight))
		{
			if (rightType != null && !rightType.equals(leftType))
			{
				ClassDeclaration left  = returnedLeft.getTypeClass();
				ClassDeclaration right = returnedRight.getTypeClass();
				
				if (!returnedLeft.isExternalType() && !returnedRight.isExternalType() &&
						(left == null || right == null ||
						(returnedLeft.isPrimitive() && (!left.isOfType(right) && !SyntaxUtils.arePrimitiveTypesCompatible(leftType, rightType)) ||
						!returnedLeft.isPrimitive() && !right.isOfType(left))) &&
						!(left instanceof Trait && right.getClassLocation().equals("nova/Object")))
				{
					if (right != null)
					{
						right.isOfType(left);
					}
					SyntaxUtils.arePrimitiveTypesCompatible(leftType, rightType);
					returnedLeft.getNovaType();
					returnedRight.getNovaType();
					returnedLeft.getTypeClass();
					returnedRight.getTypeClass();
					SyntaxMessage.error("Type '" + returnedRight.getType() + "' is not compatible with type '" + returnedLeft.getType() + "'", this);
				}
			}
			
			/*if (!SyntaxUtils.isTypeCompatible(returnedRight.getContext(), returnedLeft, returnedRight, true))
			{
				SyntaxUtils.isTypeCompatible(returnedRight.getContext(), returnedLeft, returnedRight, true);
			}
			
			return SyntaxUtils.isTypeCompatible(returnedRight.getContext(), returnedLeft, returnedRight, true);*/
		}
		
		return true;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#validate(int)
	 */
	@Override
	public ValidationResult validate(int phase)
	{
		ValidationResult result = super.validate(phase);
		
		if (result.skipValidation())
		{
			return result;
		}
		
		if (phase == SyntaxTree.PHASE_METHOD_CONTENTS)
		{
			if (containsMultipleAssignees())
			{
				Value assignment = getAssignmentNode().getReturnedNode();
				
				if (!(assignment instanceof MethodCall))
				{
					SyntaxMessage.error("Multiple assignments to single value is not implemented yet", this, false);
					
					return result.errorOccurred(this);
				}
				
				ArrayList<GenericCompatible> contexts = new ArrayList<GenericCompatible>();
				
				for (int i = 0; i < getAssigneeNodes().getNumVisibleChildren(); i++)
				{
					Node node = getAssigneeNodes().getVisibleChild(i);
					
					if (node instanceof Variable)
					{
						contexts.add(((Variable)node).getDeclaration());
					}
					else if (node instanceof GenericCompatible)
					{
						contexts.add((GenericCompatible)node);
					}
					else
					{
						throw new UnimplementedOperationException("type not implemented.");
					}
				}
				
				if (!SyntaxUtils.areTypesCompatible(contexts.toArray(new GenericCompatible[0]), assignment.getTypes(), getAssigneeNodes().getTypes()))
				{
					SyntaxMessage.error("Invalid types to be assigned from method call", this, false);

					return result.errorOccurred(this);
				}
			}
			
			Value returned = getAssigneeNode().getReturnedNode();
			
			if (returned instanceof Variable)
			{
				Variable var = (Variable)returned;
				
				if (var.getDeclaration() instanceof FieldDeclaration)
				{
					FieldDeclaration field   = (FieldDeclaration)var.getDeclaration();
					MutatorMethod    mutator = field.getMutatorMethod();
					
					if (mutator != null && getParentMethod() != mutator)
					{
						if (!mutator.isDisabled())
						{
							String text = field.getName() + "(" + getAssignmentNode().generateNovaInput() + ")";
							
							MethodCall call = MethodCall.decodeStatement(getParent(), text, getLocationIn(), true, false, mutator);
							
							call.getArgumentList().getVisibleChild(0).replaceWith(getAssignmentNode());
							
							Value replace = call;
							
							if (getAssignedNode().isAccessed())
							{
								getAssignedNode().toValue().replaceWith(call);
								
								replace = getAssignee().toValue();
							}
							
							replaceWith(replace);
							
							result.returnedNode = call;
							
							return result;
						}
						else
						{
							SyntaxMessage.error("The field '" + field.getName() + "' is not settable", this, false);
							
							getParent().removeChild(this);
							
							result.skipCycle = true;
							
							return result.errorOccurred();
						}
					}
				}
			}
			
			if (getAssigneeNode() instanceof Variable)
			{
				SyntaxUtils.checkVolatile((Variable)getAssigneeNode());
			}
		}
		
		return result;
	}
	
	@Override
	public void onChildReplaced(Node old, Node replacement)
	{
		if (getAssigneeNode() instanceof Variable)
		{
			Variable v = getAssignedNode();
			
			if (v.declaration instanceof LocalDeclaration)
			{
				LocalDeclaration local = (LocalDeclaration)v.declaration;
				
				if (local.isImplicit() && !replacement.containsProperty("methodCall"))
				{
					local.implicitType = ((Value)replacement).getReturnedNode();
					local.setType(local.implicitType);
				}
			}
		}
		
		super.onChildReplaced(old, replacement);
	}
	
	@Override
	public void rollback()
	{
		removeDeclaration();
		
		super.rollback();
	}
	
	/**
	 * Validate that the assignment is authorized to assign a value
	 * to the given variable in the assignments location. The variable
	 * is not authorized to be modified under the following condition:<br>
	 * <u>The variable cannot be modified if it is <b>private</b> or
	 * <b>visible</b> and is not contained within the same class as the
	 * assignment.</u>
	 * 
	 * @param var The Variable to validate.
	 */
	private void validateAuthorization(Variable var)
	{
		Identifier id = var.getLastAccessedNode();
		
		if (id instanceof Variable)
		{
			VariableDeclaration declaration = ((Variable)id).getDeclaration();
			
			if (id.isAccessed() && declaration instanceof FieldDeclaration)
			{
				FieldDeclaration field = (FieldDeclaration)declaration;
				
				if (field.getVisibility() == FieldDeclaration.VISIBLE)
				{
					ClassDeclaration declaringClass = field.getParentClass();
					ClassDeclaration thisClass      = getParentClass();
					
					if (declaringClass != thisClass)// && !(id instanceof ArrayAccess))
					{
						SyntaxMessage.error("The value of the field '" + field.getName() + "' cannot be modified", id);
					}
				}
			}
		}
	}
	
	/**
	 * Add the variable declaration to the parent scope if the assignment
	 * was also a declaration.<br>
	 * <br>
	 * For example:
	 * <blockquote><pre>
	 * // Scenario 1
	 * int i = 42;
	 * 
	 * // Scenario 2
	 * i = 43;</pre></blockquote>
	 * Scenario 1 includes a declaration and therefore, this method would
	 * add that declaration to its parent scope and return a clone of the
	 * declared Variable. On the other hand, scenario 2 does not
	 * include a declaration and therefore simply does nothing in this
	 * method (Returns the given Variable).
	 * 
	 * @param var The Variable to check whether or not declares a
	 * 		variable.
	 * @return If a variable is declared, this returns a clone of the
	 * 		declared variable. If not, this simply returns the given
	 * 		Variable instance.
	 */
	private Variable addDeclaration(VariableDeclaration var)
	{
		Node scopeNode = getParent().getAncestorWithScope();
		
		Node original = scopeNode;
		
		if ((scopeNode instanceof ControlStatement || scopeNode instanceof WhileLoop) && scopeNode.isDecoding())
		{
			scopeNode = scopeNode.getParent().getAncestorWithScope();
		}
		
		scopeNode.getScope().addChild(var);
		
		Location newLoc = new Location(getLocationIn());
		Variable newVar = var.generateUsableVariable(this, newLoc);
		
		var.setLocationIn(getLocationIn());
		
		if (scopeNode != original)
		{
			((LocalDeclaration)var).setScopeID(original.getScope().id);
		}
		
		return newVar;
	}
	
	private void removeDeclaration()
	{
		if (!(getAssigneeNode() instanceof Identifier))
		{
			return;
		}
		
		VariableDeclarationList list = getParent().getAncestorWithScope().getScope().getVariableList();
		
		list.removeChildWithName(((Identifier)getAssigneeNode()).getName());
	}
	
	/**
	 * Decode the right hand side of an assignment into an Node if
	 * possible. If it is not possible, then null is returned. The right
	 * hand side of an assignment must represent a value.<br>
	 * <br>
	 * Example inputs include:<br>
	 * <ul>
	 * 	<li>45</li>
	 * 	<li>"Bob"</li>
	 * 	<li>new Person(54)</li>
	 * 	<li>width * height / 2</li>
	 * 	<li>array.getSize() + 5</li>
	 * </ul>
	 * 
	 * @param parent The parent of the current statement.
	 * @param rhs The right hand side to decode into an Assignment.
	 * @param location The location of the statement in the source code.
	 * @param require Whether or not to throw an error if anything goes wrong.
	 * @return The new Node if it decodes properly. If not,
	 * 		it returns null.
	 */
	public Value decodeRightHandSide(Node parent, String rhs, Location location, boolean require)
	{
		Node child = SyntaxTree.decodeScopeContents(parent, rhs, location, require);
		
		if (child == null)
		{
			child = SyntaxTree.decodeValue(parent, rhs, location, require);
		}
		
		if (!(child instanceof Value))
		{
			SyntaxMessage.queryError("Could not decode assignment right-hand side '" + rhs + "'", parent, location, require);
			
			return null;
		}
		
		Value value = (Value)child;
		
		if (getAssignedNodeValue().getReturnedNode().getType() != null)
		{
			if (getAssignedNodeValue().getReturnedNode().isPrimitive())
			{
				if (!value.getReturnedNode().isPrimitive())
				{
					value = SyntaxUtils.unboxPrimitive(value);
				}
			}
			else if (value.getReturnedNode().isPrimitive())
			{
				value = SyntaxUtils.autoboxPrimitive(value);
			}
		}
		
		return value;
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#clone(Node, Location, boolean)
	 */
	@Override
	public Assignment clone(Node temporaryParent, Location locationIn, boolean cloneChildren, boolean cloneAnnotations)
	{
		Assignment node = new Assignment(temporaryParent, locationIn);
		
		return cloneTo(node, cloneChildren, cloneAnnotations);
	}
	
	/**
	 * @see net.fathomsoft.nova.tree.Node#cloneTo(Node)
	 */
	public Assignment cloneTo(Assignment node)
	{
		return cloneTo(node, true, true);
	}
	
	/**
	 * Fill the given {@link Assignment} with the data that is in the
	 * specified node.
	 * 
	 * @param node The node to copy the data into.
	 * @return The cloned node.
	 */
	public Assignment cloneTo(Assignment node, boolean cloneChildren, boolean cloneAnnotations)
	{
		super.cloneTo(node, cloneChildren, cloneAnnotations);
		
		return node;
	}
	
	/**
	 * Test the Assignment class type to make sure everything
	 * is working properly.
	 * 
	 * @return The error output, if there was an error. If the test was
	 * 		successful, null is returned.
	 */
	public static String test(TestContext context)
	{
		
		
		return null;
	}
}