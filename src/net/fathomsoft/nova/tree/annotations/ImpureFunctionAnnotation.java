package net.fathomsoft.nova.tree.annotations;

import net.fathomsoft.nova.ValidationResult;
import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.variables.FieldDeclaration;
import net.fathomsoft.nova.util.Location;

public class ImpureFunctionAnnotation extends Annotation implements ModifierAnnotation
{
	public ImpureFunctionAnnotation(Node temporaryParent, Location locationIn)
	{
		super(temporaryParent, locationIn);
	}
	
	public static ImpureFunctionAnnotation decodeStatement(Node parent, String name, String parameters, Location location, boolean require)
	{
		if (name.equals("Impure"))
		{
			ImpureFunctionAnnotation n = new ImpureFunctionAnnotation(parent, location);
			
			return n;
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
		
		if (phase == SyntaxTree.PHASE_METHOD_CONTENTS)
		{
			if (getParent() instanceof FieldDeclaration)
			{
				FieldDeclaration field = (FieldDeclaration)getParent();
				
				if (field.getAccessorMethod() == null && field.getMutatorMethod() == null)
				{
					SyntaxMessage.error("Field '" + field.getDeclaringClass().getName() + "." + field.getName() + "' which does not contain an accessor or mutator function cannot contain impure annotation", field, false);
					
					result.errorOccurred = true;
					
					return result;
				}
			}
		}
		
		return result;
	}
	
	@Override
	public boolean onApplied(Node next, boolean throwError)
	{
		if (next instanceof MethodDeclaration ||
			next instanceof ArrayBracketOverload ||
			next instanceof FieldDeclaration)
		{
			// valid
		}
		else
		{
			return invalidAppliedTo(next, throwError);
		}
		
		return super.onApplied(next, throwError);
	}
	
	@Override
	public ImpureFunctionAnnotation clone(Node temporaryParent, Location locationIn, boolean cloneChildren, boolean cloneAnnotations)
	{
		ImpureFunctionAnnotation node = new ImpureFunctionAnnotation(temporaryParent, locationIn);
		
		return cloneTo(node, cloneChildren, cloneAnnotations);
	}
	
	public ImpureFunctionAnnotation cloneTo(ImpureFunctionAnnotation node)
	{
		return cloneTo(node, true, true);
	}
	
	public ImpureFunctionAnnotation cloneTo(ImpureFunctionAnnotation node, boolean cloneChildren, boolean cloneAnnotations)
	{
		super.cloneTo(node, cloneChildren, cloneAnnotations);
		
		return node;
	}
	
	@Override
	public String[] getAliases()
	{
		return new String[] { "impure" };
	}
}