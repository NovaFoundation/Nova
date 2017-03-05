package net.fathomsoft.nova.tree.annotations;

import net.fathomsoft.nova.ValidationResult;
import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.tree.*;
import net.fathomsoft.nova.tree.variables.Variable;
import net.fathomsoft.nova.util.Location;

import java.util.ArrayList;

public class TestSuiteAnnotation extends Annotation
{
	public boolean generatedRunTestsMethod, writeMessage;
	
	public TestSuiteAnnotation(Node temporaryParent, Location locationIn)
	{
		super(temporaryParent, locationIn);
	}
	
	@Override
	public String[] defaultParameterNames()
	{
		return new String[] { "classes" };
	}
	
	@Override
	public String[][] defaultParameterTypes()
	{
		return new String[][] { { "list" } };
	}
	
	public static TestSuiteAnnotation decodeStatement(Node parent, String name, String parameters, Location location, boolean require)
	{
		if (name.equals("TestSuite"))
		{
			TestSuiteAnnotation n = new TestSuiteAnnotation(parent, location);
			
			n.parseParameters(parameters);
			
			return n;
		}
		
		return null;
	}
	
	public NovaMethodDeclaration getRunTestsMethod()
	{
		ClassDeclaration clazz = (ClassDeclaration)parent;
		
		MethodList.SearchFilter filter = new MethodList.SearchFilter();
		filter.checkAncestor = false;
		
		return (NovaMethodDeclaration)clazz.getMethod(clazz, "runTests", filter, new Value[0]);
	}
	
	public ArrayList<NovaMethodDeclaration> getMethodsWithTypeAnnotation(Class type)
	{
		final ArrayList<NovaMethodDeclaration> methods = new ArrayList<>();
		
		ClassDeclaration clazz = (ClassDeclaration)parent;
		
		clazz.getMethodList().forEachNovaMethod(method -> {
			if (method.containsAnnotationOfType(type))
			{
				methods.add(method);
			}
		});
		
		return methods;
	}
	
	public void callMethodsWithAnnotationOfType(Class type)
	{
		NovaMethodDeclaration runMethod = getRunTestsMethod();
		
		getMethodsWithTypeAnnotation(type).forEach(method -> {
			if (method.getParameterList().getNumParameters() != 0)
			{
				SyntaxMessage.error("Test method '" + method.getName() + "' cannot contain parameters", method);
			}
			
			MethodCall call = MethodCall.decodeStatement(runMethod, method.getName() + "()", Location.INVALID, true, false, method);
			
			runMethod.addChild(call);
		});
	}
	
	@Override
	public ValidationResult validate(int phase)
	{
		ValidationResult result = super.validate(phase);
		
		if (result.skipValidation())
		{
			return result;
		}
		
		if (phase == SyntaxTree.PHASE_CLASS_DECLARATION)
		{
			
		}
		else if (phase == SyntaxTree.PHASE_INSTANCE_DECLARATIONS)
		{
			NovaMethodDeclaration method = getRunTestsMethod();
			
			generatedRunTestsMethod = method == null || method.isPropertyTrue("generated");
			
			if (method == null)
			{
				method = BodyMethodDeclaration.decodeStatement(parent, "public runTests()", Location.INVALID, true);
				
				parent.addChild(method);
				
				method.setProperty("generated", true);
			}
		}
		else if (phase == SyntaxTree.PHASE_METHOD_CONTENTS)
		{
			insertMessage();
		}
		else if (phase == SyntaxTree.PHASE_PRE_GENERATION)
		{
			if (generatedRunTestsMethod)
			{
				addTestCalls();
			}
		}
		
		return result;
	}
	
	public void insertMessage()
	{
//		if (parameters.containsKey("message"))
//		{
//			Literal message = (Literal)parameters.get("message");
//			
//			if (!message.isNullLiteral() && !message.value.equals("false") && message.value.length() > 2)
//			{
//				message.value = "\"================== " + message.value.substring(1, message.value.length() - 1) + " ==================\"";
//				
//				writeMessage(message);
//			}
//		}
//		else
//		{
//			ClassDeclaration clazz = (ClassDeclaration)parent;
//			
//			writeMessage((Literal)Literal.decodeStatement(parent, "\"================== Testing " + clazz.getName() + " ==================\"", Location.INVALID, true, true));
//		}
	}
	
	public void writeMessage(Literal message)
	{
		NovaMethodDeclaration method = getRunTestsMethod();
		
		StaticClassReference write = (StaticClassReference)SyntaxTree.decodeIdentifierAccess(method, "Console.writeLine(\"\")", Location.INVALID, true);
		
		((MethodCall)write.getReturnedNode()).getArgumentList().getVisibleChild(0).replaceWith(message);
		
		Node first = method.getScope().getFirstStatement();
		
		if (first != null)
		{
			method.getScope().addChildBefore(first, write);
		}
		else
		{
			method.getScope().addChild(write);
		}
		
		writeMessage = true;
	}
	
	public void addTestCalls()
	{
		final NovaMethodDeclaration runMethod = getRunTestsMethod();
		
		callMethodsWithAnnotationOfType(InitTestClassAnnotation.class);
		
		String[] classNames = (String[])parameters.get("classes");
		
		for (String className : classNames)
		{
			ClassDeclaration c = getFileDeclaration().getImportedClass(this, className);
			
			SyntaxMessage.queryError("Class '" + className + "' is not imported", this, c == null);
			SyntaxMessage.queryError("Class '" + className + "' is does not contain Testable annotation", this, !c.containsAnnotationOfType(TestableAnnotation.class));
			SyntaxMessage.queryError("Testable class '" + className + "' requires a default constructor", this, !c.containsDefaultConstructor());
			
			Assignment call = Assignment.decodeStatement(runMethod, "let test" + className + " = new " + className + "()", getLocationIn(), true);
			
			runMethod.addChild(call);
			
			call.onAfterDecoded();
		}
		
		for (String className : classNames)
		{
			ClassDeclaration c = getFileDeclaration().getImportedClass(this, className);
			
			TestableAnnotation testable = (TestableAnnotation)c.getAnnotationOfType(TestableAnnotation.class);
			
			NovaMethodDeclaration method = testable.getRunTestsMethod();
			
			Variable call = (Variable)SyntaxTree.decodeIdentifierAccess(runMethod,  "test" + className + "." + method.getName() + "()", getLocationIn(), true);
			
			runMethod.addChild(call);
		}
	}
	
	@Override
	public boolean onApplied(Node next, boolean throwError)
	{
		if (!checkDuplicate(next, throwError))
		{
			if (next instanceof ClassDeclaration)
			{
				// valid
			}
			else
			{
				return invalidApplication(next, throwError);
			}
		}
		
		return super.onApplied(next, throwError);
	}
	
	@Override
	public TestSuiteAnnotation clone(Node temporaryParent, Location locationIn, boolean cloneChildren, boolean cloneAnnotations)
	{
		TestSuiteAnnotation node = new TestSuiteAnnotation(temporaryParent, locationIn);
		
		return cloneTo(node, cloneChildren, cloneAnnotations);
	}
	
	public TestSuiteAnnotation cloneTo(TestSuiteAnnotation node)
	{
		return cloneTo(node, true, true);
	}
	
	public TestSuiteAnnotation cloneTo(TestSuiteAnnotation node, boolean cloneChildren, boolean cloneAnnotations)
	{
		super.cloneTo(node, cloneChildren, cloneAnnotations);
		
		return node;
	}
}