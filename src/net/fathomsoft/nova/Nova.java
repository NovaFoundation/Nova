package net.fathomsoft.nova;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.fathomsoft.nova.error.SyntaxErrorException;
import net.fathomsoft.nova.error.SyntaxMessage;
import net.fathomsoft.nova.tree.ArgumentList;
import net.fathomsoft.nova.tree.Assignment;
import net.fathomsoft.nova.tree.BinaryOperation;
import net.fathomsoft.nova.tree.Cast;
import net.fathomsoft.nova.tree.ClassDeclaration;
import net.fathomsoft.nova.tree.Closure;
import net.fathomsoft.nova.tree.ClosureDeclaration;
import net.fathomsoft.nova.tree.Condition;
import net.fathomsoft.nova.tree.Constructor;
import net.fathomsoft.nova.tree.Destructor;
import net.fathomsoft.nova.tree.Dimensions;
import net.fathomsoft.nova.tree.ElseStatement;
import net.fathomsoft.nova.tree.ExternalMethodDeclaration;
import net.fathomsoft.nova.tree.ExternalType;
import net.fathomsoft.nova.tree.ExternalTypeList;
import net.fathomsoft.nova.tree.FileDeclaration;
import net.fathomsoft.nova.tree.ForLoop;
import net.fathomsoft.nova.tree.GenericCompatible;
import net.fathomsoft.nova.tree.IIdentifier;
import net.fathomsoft.nova.tree.IValue;
import net.fathomsoft.nova.tree.Identifier;
import net.fathomsoft.nova.tree.IfStatement;
import net.fathomsoft.nova.tree.Import;
import net.fathomsoft.nova.tree.ImportList;
import net.fathomsoft.nova.tree.InstanceDeclaration;
import net.fathomsoft.nova.tree.Instantiation;
import net.fathomsoft.nova.tree.Interface;
import net.fathomsoft.nova.tree.InterfaceVTable;
import net.fathomsoft.nova.tree.Literal;
import net.fathomsoft.nova.tree.LocalDeclaration;
import net.fathomsoft.nova.tree.Loop;
import net.fathomsoft.nova.tree.LoopInitialization;
import net.fathomsoft.nova.tree.LoopUpdate;
import net.fathomsoft.nova.tree.MethodCall;
import net.fathomsoft.nova.tree.MethodCallArgumentList;
import net.fathomsoft.nova.tree.MethodDeclaration;
import net.fathomsoft.nova.tree.MethodList;
import net.fathomsoft.nova.tree.Node;
import net.fathomsoft.nova.tree.NovaMethodDeclaration;
import net.fathomsoft.nova.tree.Operator;
import net.fathomsoft.nova.tree.Parameter;
import net.fathomsoft.nova.tree.ParameterList;
import net.fathomsoft.nova.tree.Priority;
import net.fathomsoft.nova.tree.Program;
import net.fathomsoft.nova.tree.Return;
import net.fathomsoft.nova.tree.Scope;
import net.fathomsoft.nova.tree.StaticBlock;
import net.fathomsoft.nova.tree.SyntaxTree;
import net.fathomsoft.nova.tree.TreeGenerator;
import net.fathomsoft.nova.tree.TypeList;
import net.fathomsoft.nova.tree.UnaryOperation;
import net.fathomsoft.nova.tree.Until;
import net.fathomsoft.nova.tree.VTable;
import net.fathomsoft.nova.tree.Value;
import net.fathomsoft.nova.tree.WhileLoop;
import net.fathomsoft.nova.tree.exceptionhandling.Exception;
import net.fathomsoft.nova.tree.switches.Switch;
import net.fathomsoft.nova.util.*;

import static java.util.Arrays.stream;

/**
 * Class used for the compilation process.
 * 
 * @author	Braden Steffaniak
 * @since	v0.1 Jan 5, 2014 at 9:00:04 PM
 * @version	v0.2.44 Jul 13, 2015 at 1:28:17 AM
 */
public class Nova
{
	private boolean				testClasses;
	
	private int					compiler;
	
	private long				flags;
	private long				startTime, endTime;

	private File				outputFile, workingDir;
	private File				nativeInterfaceSource, nativeInterfaceHeader;
	private File				interfaceVTableHeader;
	
	private SyntaxTree			tree;
	
	private ArrayList<String>	includeDirectories, externalImports, errors, messages;
	
	private ArrayList<File>		inputFiles, cSourceFiles, cHeaderFiles;
	
	private List<File>			lingeringFiles;

	private String[]            visibleCompilerMessages;
	
	private static final int	OS;
	
	private static final String	OUTPUT_EXTENSION, DYNAMIC_LIB_EXT;
	private static final String NATIVE_INTERFACE_FILE_NAME = "NovaNativeInterface";
	private static final String INTERFACE_VTABLE_FILE_NAME = "InterfaceVTable";
	private static final String ENVIRONMENT_VAR            = "novaEnv";
	
	private static final HashMap<String, String>	CLASS_LOCATIONS = new HashMap<String, String>();
	
	public static final boolean	ANDROID_DEBUG = false;
	public static final boolean	DEBUG         = true;
	
	// Set to 0 to not benchmark.
	public static final int		BENCHMARK     = 0;

	public static final long	SINGLE_THREAD = 0x1000000000000000l;
	public static final long	SMALL_BIN     = 0x0100000000000000l;
	public static final long	NO_GC         = 0x0010000000000000l;
	public static final long	LIBRARY       = 0x0001000000000000l;
	public static final long	RUNTIME       = 0x0000100000000000l;
	public static final long	C_ARGS        = 0x0000010000000000l;
	public static final long	KEEP_C        = 0x0000001000000000l;
	public static final long	DRY_RUN       = 0x0000000100000000l;
	public static final long	VERBOSE       = 0x0000000010000000l;
	public static final long	FORMATC       = 0x0000000001000000l;
	public static final long	CSOURCE       = 0x0000000000100000l;
	public static final long	NO_C_OUTPUT   = 0x0000000000010000l;
	public static final long	NO_OPTIMIZE   = 0x0000000000001000l;
	public static final long	NO_ERRORS     = 0x0000000000000100l;
	public static final long	NO_WARNINGS   = 0x0000000000000010l;
	public static final long	NO_NOTES      = 0x0000000000000001l;
	
	public static final int		GCC           = 1;
	public static final int		TCC           = 2;
	public static final int		CLANG         = 3;
	
	public static final int		WINDOWS       = 1;
	public static final int		MACOSX        = 2;
	public static final int		LINUX         = 3;
	
	public static final String	LANGUAGE_NAME = "Nova";
	public static final String	VERSION       = "v0.2.44";
	
	/**
	 * Find out which operating system the compiler is running on.
	 */
	static
	{
		String osName = System.getProperty("os.name").toLowerCase();
		
		if (osName.startsWith("win"))
		{
			OS = WINDOWS;
			OUTPUT_EXTENSION = ".exe";
			DYNAMIC_LIB_EXT  = ".dll";
		}
		else if (osName.startsWith("mac"))
		{
			OS = MACOSX;
			OUTPUT_EXTENSION = "";
			DYNAMIC_LIB_EXT  = ".dylib";
		}
		else if (osName.startsWith("lin"))
		{
			OS = LINUX;
			OUTPUT_EXTENSION = "";
			DYNAMIC_LIB_EXT  = ".so";
		}
		else
		{
			OS = 0;
			OUTPUT_EXTENSION = "";
			DYNAMIC_LIB_EXT  = "";
		}
	}
	
	/**
	 * Method called whenever the compiler is invoked. Supplies the
	 * needed information for compiling the given files.
	 * 
	 * @param args The String array containing the locations of the files
	 * 		to compile, as well as other compiler arguments.
	 */
	public static void main(String args[])
	{
		Nova nova = new Nova();
		
		nova.compile(args, true);
		
		nova.formatOutput();
		nova.generateNativeInterface();
		nova.generateInterfaceVTable();
		nova.writeFiles();
		
		nova.compileC();
	}
	
	/**
	 * Method used to initialize the compiler data and start the
	 * compilation process.
	 */
	public Nova()
	{
		if (BENCHMARK > 0)
		{
			try
			{
				System.out.println("Preparing Benchmark...");
				
				Thread.sleep(100);
				
				System.out.println("Starting...");
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			enableFlag(DRY_RUN);
		}
		
		lingeringFiles     = new LinkedList<File>();
		inputFiles         = new ArrayList<File>();
		cSourceFiles       = new ArrayList<File>();
		cHeaderFiles       = new ArrayList<File>();
		includeDirectories = new ArrayList<String>();
		externalImports    = new ArrayList<String>();
		errors             = new ArrayList<String>();
		messages           = new ArrayList<String>();
		
		testClasses = BENCHMARK <= 0;
	}
	
	/**
	 * Compile the input files given within the args.
	 * 
	 * @param args The String array containing the locations of the files
	 * 		to compile, as well as other compiler arguments.
	 */
	public void compile(String args[], boolean generateCode)
	{
		String root      = getWorkingDirectoryPath();
		String directory = getWorkingDirectoryPath() + "example/";
		String stability = getWorkingDirectoryPath() + "stabilitytest/";
		String standard  = getWorkingDirectoryPath() + "nova/standard/";
		
		if (OS == WINDOWS)
		{
			compiler = GCC;
		}
		else
		{
			compiler = GCC;
		}
		
		if (DEBUG)
		{
			testClasses();
			
			args = new String[]
			{
				formatPath(stability + "StabilityTest.nova"),
				formatPath(stability + "TimeStability.nova"),
				formatPath(stability + "FileStability.nova"),
				formatPath(stability + "ThreadStability.nova"),
				formatPath(stability + "ExceptionStability.nova"),
				formatPath(stability + "SyntaxStability.nova"),
				formatPath(stability + "ClosureStability.nova"),
				formatPath(stability + "PolymorphismStability.nova"),
				formatPath(stability + "PolymorphicSuperClass.nova"),
				formatPath(stability + "PolymorphicSubClass.nova"),
				formatPath(stability + "StabilityTestException.nova"),
				formatPath(stability + "StabilityExceptionHandler.nova"),
				formatPath(stability + "ThreadImplementation.nova"),
				formatPath(stability + "UnstableException.nova"),
				formatPath(stability + "NetworkStability.nova"),
				formatPath(stability + "ClientThread.nova"),
				formatPath(stability + "StabilityTestCase.nova"),
//				formatPath(directory + "network/OutputThread.nova"),
//				formatPath(directory + "network/ConnectionThread.nova"),
//				formatPath(directory + "network/ServerDemo.nova"),
//				formatPath(directory + "network/ClientDemo.nova"),
//				formatPath(directory + "GenericDemo.nova"),
//				formatPath(directory + "database/DatabaseDemo.nova"),
//				formatPath(root      + "bank/BankQueryException.nova"),
//				formatPath(root      + "bank/Bank.nova"),
//				formatPath(root      + "bank/ConnectionThread.nova"),
//				formatPath(root      + "bank/ServerOutputThread.nova"),
//				formatPath(root      + "bank/BankClient.nova"),
//				formatPath(root      + "bank/ClientConnectionThread.nova"),
//				formatPath(root      + "bank/ClientInputThread.nova"),
//				formatPath(directory + "ackermann/Ackermann.nova"),
//				formatPath(directory + "Lab.nova"),
//				formatPath(directory + "copy/Dog.nova"),
//				formatPath(directory + "T1.nova"),
//				formatPath(directory + "T2.nova"),
//				formatPath(directory + "MathDemo.nova"),
//				formatPath(directory + "ThreadDemo.nova"),
//				formatPath(directory + "ThreadDemoImplementation.nova"),
//				formatPath(directory + "PolymorphismDemo.nova"),
//				formatPath(directory + "Animal.nova"),
//				formatPath(directory + "Spider.nova"),
//				formatPath(directory + "Dog.nova"),
//				formatPath(directory + "ArrayListDemo.nova"),
//				formatPath(directory + "QueueDemo.nova"),
//				formatPath(directory + "IntegerTest.nova"),
//				formatPath(directory + "FileTest.nova"),
//				formatPath(directory + "SVGTest.nova"),
//				formatPath(directory + "ExceptionHandlingDemo.nova"),
//				formatPath(directory + "NonWholeDivisionException.nova"),
//				formatPath(directory + "ClosureDemo.nova"),
//				formatPath(directory + "Person.nova"),
//				formatPath(directory + "BodyBuilder.nova"),
				formatPath(directory + "Polygon.nova"),
				formatPath(directory + "Square.nova"),
//				formatPath(directory + "Test.nova"),
				"-o",   formatPath(directory + "bin/Executable" + OUTPUT_EXTENSION),
				"-dir", formatPath(directory + "../example"),
				"-dir", formatPath(directory + "../stabilitytest"),
				"-run",
//				"-csource",
				"-formatc",
				testClasses ? "-v" : "",
//				"-gcc",
//				"-small",
//				"-cargs",
				"-keepc",
				"-single-thread",
//				"-nogc",
//				"-no-c-output",
//				"-dry"
//				"-no-notes",
//				"-no-warnings",
//				"-no-errors",
				"-no-optimize",
				"-library",
			};
		}
		if (ANDROID_DEBUG)
		{
			enableFlag(DRY_RUN);
		}
		
		String standardFiles[] = new String[]
		{
			formatPath(standard  + "String.nova"),
			formatPath(standard  + "Object.nova"),
			formatPath(standard  + "System.nova"),
			
			formatPath(standard  + "database/DBConnector.nova"),
			formatPath(standard  + "database/ResultSet.nova"),
			
			formatPath(standard  + "network/Socket.nova"),
			formatPath(standard  + "network/ServerSocket.nova"),
			formatPath(standard  + "network/ClientSocket.nova"),
			formatPath(standard  + "network/ConnectionSocket.nova"),
			formatPath(standard  + "network/NetworkInputStream.nova"),
			formatPath(standard  + "network/NetworkOutputStream.nova"),
			
			formatPath(standard  + "math/Math.nova"),
			formatPath(standard  + "math/Statement.nova"),
			formatPath(standard  + "math/NumericStatement.nova"),
			formatPath(standard  + "math/NumericTree.nova"),
			formatPath(standard  + "math/NumericOperation.nova"),
			formatPath(standard  + "math/NumericOperand.nova"),
			formatPath(standard  + "math/StatementComponent.nova"),
			formatPath(standard  + "math/InvalidNumericStatementException.nova"),
			
			formatPath(standard  + "math/logic/Conclusion.nova"),
			formatPath(standard  + "math/logic/Hypothesis.nova"),
			formatPath(standard  + "math/logic/LogicalConnective.nova"),
			formatPath(standard  + "math/logic/StatementComponent.nova"),
			formatPath(standard  + "math/logic/StatementLetter.nova"),
			formatPath(standard  + "math/logic/WFF.nova"),
			formatPath(standard  + "math/logic/StatementGroup.nova"),
			formatPath(standard  + "math/logic/LogicalStatement.nova"),
			formatPath(standard  + "math/logic/InvalidFormulaException.nova"),
			
			formatPath(standard  + "process/Process.nova"),
			
			formatPath(standard  + "primitive/Bool.nova"),
			formatPath(standard  + "primitive/Null.nova"),
			formatPath(standard  + "primitive/Primitive.nova"),
			
			formatPath(standard  + "primitive/number/Char.nova"),
			formatPath(standard  + "primitive/number/Byte.nova"),
			formatPath(standard  + "primitive/number/Short.nova"),
			formatPath(standard  + "primitive/number/Int.nova"),
			formatPath(standard  + "primitive/number/Long.nova"),
			formatPath(standard  + "primitive/number/Float.nova"),
			formatPath(standard  + "primitive/number/Double.nova"),
			formatPath(standard  + "primitive/number/Number.nova"),

			formatPath(standard  + "operators/Multiply.nova"),
			formatPath(standard  + "operators/Equals.nova"),
			
			formatPath(standard  + "time/Time.nova"),
			formatPath(standard  + "time/Timer.nova"),
			formatPath(standard  + "time/Date.nova"),
			
			formatPath(standard  + "thread/Thread.nova"),
			formatPath(standard  + "thread/UncaughtExceptionHandler.nova"),
			
			formatPath(standard  + "io/InputStream.nova"),
			formatPath(standard  + "io/OutputStream.nova"),
			formatPath(standard  + "io/StreamReader.nova"),
			formatPath(standard  + "io/File.nova"),
			formatPath(standard  + "io/Console.nova"),
			
			formatPath(standard  + "svg/SVG.nova"),
			formatPath(standard  + "svg/SVGComponent.nova"),
			formatPath(standard  + "svg/SVGComponentList.nova"),
			formatPath(standard  + "svg/SVGComponentNode.nova"),
			formatPath(standard  + "svg/SVGMainComponent.nova"),
			formatPath(standard  + "svg/SVGCircle.nova"),
			
			formatPath(standard  + "exception/ExceptionData.nova"),
			formatPath(standard  + "exception/DivideByZeroException.nova"),
			formatPath(standard  + "exception/UnimplementedOperationException.nova"),
			formatPath(standard  + "exception/Exception.nova"),

			formatPath(standard  + "datastruct/list/List.nova"),
			formatPath(standard  + "datastruct/list/LinkedList.nova"),
			formatPath(standard  + "datastruct/list/ListNode.nova"),
			formatPath(standard  + "datastruct/list/Array.nova"),
			formatPath(standard  + "datastruct/list/Iterable.nova"),
			formatPath(standard  + "datastruct/list/Iterator.nova"),
			formatPath(standard  + "datastruct/list/ArrayIterator.nova"),
			formatPath(standard  + "datastruct/list/NoSuchElementException.nova"),
			formatPath(standard  + "datastruct/list/Queue.nova"),
			formatPath(standard  + "datastruct/list/Stack.nova"),
			formatPath(standard  + "datastruct/list/EmptyStackException.nova"),
			
			formatPath(standard  + "datastruct/HashMap.nova"),
			formatPath(standard  + "datastruct/Bounds.nova"),
			formatPath(standard  + "datastruct/Tree.nova"),
			formatPath(standard  + "datastruct/BinaryTree.nova"),
			formatPath(standard  + "datastruct/Node.nova"),
			formatPath(standard  + "datastruct/BinaryNode.nova"),
			formatPath(standard  + "datastruct/Comparable.nova"),
//			formatPath(standard  + "datastruct/Vector2D.nova"),
//			formatPath(standard  + "datastruct/Vector.nova"),

			formatPath(standard  + "security/MD5.nova"),

			formatPath(standard  + "star/Window.nova"),
			formatPath(standard  + "star/WindowThread.nova"),
			
			formatPath(standard  + "gc/GC.nova"),
		};
		
		String postArgs[] = new String[]
		{
			"-dir", formatPath(directory + "../include"),
			"-dir", formatPath(directory + "../include/gc"),
			"-dir", formatPath(directory + "../include/nova_mysql"),
			"-dir", formatPath(directory + "../include/nova_openssl"),
			"-dir", formatPath(directory + ".."),
		};
		
//		for (String location : standardFiles)
//		{
//			location = removeSurroundingQuotes(location);
//			
//			inputFiles.add(new File(location));
//		}
		
		for (String classLocation : standardFiles)
		{
			if (classLocation.length() <= 0)
			{
				continue;
			}
			
			String dir = getWorkingDirectoryPath();
			
			int offset = classLocation.indexOf(dir);
			int slash  = dir.replace('\\', '/').endsWith("/") ? 0 : 1;
			
			if (offset < 0)
			{
				error("Unable to find location " + classLocation);
				
				completed();
			}
			
			String location = classLocation.substring(dir.length() + offset + slash, classLocation.lastIndexOf('.'));
			String name     = SyntaxUtils.getClassName(location);
			
			CLASS_LOCATIONS.put(name, location);
		}
		
		args = prependArguments(args, standardFiles);
		args = appendArguments(args, postArgs);
		
		parseArguments(args);

		ArrayList<String> compileMessages = new ArrayList<>();

		if (!isFlagEnabled(NO_NOTES))
		{
			compileMessages.add("note");
		}
		if (!isFlagEnabled(NO_WARNINGS))
		{
			compileMessages.add("warning");
		}
		if (!isFlagEnabled(NO_ERRORS))
		{
			compileMessages.add("error");
			compileMessages.add("fatal error");
		}

		visibleCompilerMessages = compileMessages.toArray(new String[0]);
		
//		log("Nova " + VERSION + " Copyright (C) 2014  Braden Steffaniak <BradenSteffaniak@gmail.com>\n" +
//				"This program comes with ABSOLUTELY NO WARRANTY\n" + //; for details type show w." +
//				"This is free software, and you are welcome to redistribute it\n" +
//				"under certain conditions");//; type show c for details.");
		
		workingDir = new File(directory);
		
		startTimer();
		
		createSyntaxTree(generateCode);
	}
	
	private void generateInterfaceVTable()
	{
		String header = generateInterfaceVTableHeader();
		
		try
		{
			File include = new File(StringUtils.removeSurroundingQuotes(getIncludeDir()));
			
			interfaceVTableHeader = FileUtils.writeFile(INTERFACE_VTABLE_FILE_NAME + ".h", include, header);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private String generateInterfaceVTableHeader()
	{
		StringBuilder builder = new StringBuilder();

		NovaMethodDeclaration[] methods = tree.getRoot().getProgram().getInterfaceMethods();
		ClosureDeclaration[] closures = tree.getRoot().getProgram().getPublicClosures();
		
		builder.append("#ifndef NOVA_INTERFACE_VTABLE\n");
		builder.append("#define NOVA_INTERFACE_VTABLE\n\n");
		
		ArrayList<String> types = new ArrayList<String>();
		
		for (NovaMethodDeclaration method : methods)
		{
			SyntaxUtils.generateTypeDefinition(builder, method.getParentClass(), types);
			SyntaxUtils.generateTypeDefinition(builder, method, types);
			
			SyntaxUtils.addParametersToTypeList(builder, method.getParameterList(), types);
		}
		
		for (ClosureDeclaration c : closures)
		{
			SyntaxUtils.addParametersToTypeList(builder, c.getParameterList(), types);
			
			c.generateCClosureDefinition(builder);
		}
		
		builder.append("\n");
		builder.append("typedef struct ").append(InterfaceVTable.TYPE).append("\n");
		builder.append("{\n");
		
		for (NovaMethodDeclaration method : methods)
		{
			method.generateCType(builder).append(" (*").append(method.generateCVirtualMethodName()).append(")(").append(method.getParameterList().generateCHeader()).append(");\n");
		}
		
		builder.append("} ").append(InterfaceVTable.TYPE).append(";\n");
		
		builder.append("\n");
		builder.append("#endif\n");
		
		return builder.toString();
	}
	
	private void generateNativeInterface()
	{
		generateNativeInterfaceHeader();
		generateNativeInterfaceSource();
	}
	
	private void generateNativeInterfaceHeader()
	{
		StringBuilder nativeInterface = new StringBuilder();
		
		nativeInterface.append("#ifndef NOVA_NATIVE_INTERFACE\n");
		nativeInterface.append("#define NOVA_NATIVE_INTERFACE\n\n");
		
		for (FileDeclaration file : tree.getFiles())
		{
			nativeInterface.append("#include <" + file.generateCHeaderName() + ">\n");
		}
		
		nativeInterface.append('\n');

		for (FileDeclaration file : tree.getFiles())
		{
			file.generateCHeaderNativeInterface(nativeInterface).append("\n");
		}
		
		nativeInterface.append("\ntypedef struct nova_env\n");
		nativeInterface.append("{\n");
		
		for (FileDeclaration file : tree.getFiles())
		{
			for (ClassDeclaration clazz : file.getClassDeclarations())
			{
				clazz.generateCSourceName(nativeInterface, "native").append(" ").append(clazz.getNativeLocation()).append(";\n");
			}
		}
		
		nativeInterface.append("} nova_env;\n\n");
		nativeInterface.append("extern nova_env " + ENVIRONMENT_VAR + ";\n\n");
		nativeInterface.append("\n#endif\n");
		
		try
		{
			File include = new File(StringUtils.removeSurroundingQuotes(getIncludeDir()));
			
			nativeInterfaceHeader = FileUtils.writeFile(NATIVE_INTERFACE_FILE_NAME + ".h", include, nativeInterface.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
//		lingeringFiles.add(nativeInterfaceHeader);
	}
	
	private void generateNativeInterfaceSource()
	{
		StringBuilder nativeInterface = new StringBuilder();

		nativeInterface.append("#include <precompiled.h>\n");
		nativeInterface.append("#include \"" + NATIVE_INTERFACE_FILE_NAME + ".h\"\n\n");
		
		nativeInterface.append("nova_env " + ENVIRONMENT_VAR + " = {\n");
		
		for (FileDeclaration file : tree.getFiles())
		{
			file.generateCSourceNativeInterface(nativeInterface).append('\n');
		}
		
		nativeInterface.append("};\n");
		
		try
		{
			File include = new File(StringUtils.removeSurroundingQuotes(getIncludeDir()));
			
			nativeInterfaceSource = FileUtils.writeFile(NATIVE_INTERFACE_FILE_NAME + ".c", include, nativeInterface.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
//		lingeringFiles.add(nativeInterfaceSource);
	}
	
	public static final String getClassLocation(String className)
	{
//		if(className.equals("GC"))
//		{
//			return "";
//		}
//		if (CLASS_LOCATIONS.containsKey(className))
//		{
//			return "";
//		}
		
		return CLASS_LOCATIONS.get(className);
	}
	
	private String[] prependArguments(String args[], String ... newData)
	{
		String temp[] = new String[args.length + newData.length];
		
		System.arraycopy(newData, 0, temp, 0, newData.length);
		System.arraycopy(args, 0, temp, newData.length, args.length);
		
		return temp;
	}
	
	private String[] appendArguments(String args[], String ... newData)
	{
		String temp[] = new String[args.length + newData.length];
		
		System.arraycopy(args, 0, temp, 0, args.length);
		System.arraycopy(newData, 0, temp, args.length, newData.length);
		
		return temp;
	}
	
	private void createSyntaxTree(boolean generateCode)
	{
		// Try to create a Syntax Tree for the given file.
		try
		{
			int times = 1;
			
			if (BENCHMARK > 0)
			{
				times = BENCHMARK;
			}
			
			try
			{
				for (int i = 0; i < times; i++)
				{
					long before = System.currentTimeMillis();
					
					tree = new SyntaxTree(inputFiles.toArray(new File[0]), this);
					
					if (containsErrors())
					{
						enableFlag(DRY_RUN);
						completed();
					}
					if (generateCode)
					{
						tree.generateCOutput();
						
						insertMainMethod();
					}
					
					long time = System.currentTimeMillis() - before;
					
					if (BENCHMARK > 1)
					{
						System.out.println("Benchmark " + (i + 1) + ": " + time + "ms");
					}
				}
			}
			catch (SyntaxErrorException e)
			{
				enableFlag(DRY_RUN);
				completed();
			}
		}
		catch (IOException e1)
		{
			error("Could not generate the syntax tree for the file '");// + file.getName() + "'.");
			
			e1.printStackTrace();
			
			completed();
		}
		
		long   time = System.currentTimeMillis() - startTime;
		
		String str  = LANGUAGE_NAME + " compile time: " + time + "ms";
		
		if (BENCHMARK > 0)
		{
			if (BENCHMARK > 1)
			{
				str += " (Average of " + String.format("%.3f", time / (float)BENCHMARK) + "ms)";
			}
			
			System.out.println(str);
		}
		else
		{
			log(str);
		}
	}
	
	public void formatOutput()
	{
		if (isFlagEnabled(FORMATC))
		{
			log("Formatting the output c output code...");
			
			tree.formatCOutput();
		}
	}
	
	public void writeFiles()
	{
		String headers[] = tree.getCHeaderOutput();
		String sources[] = tree.getCSourceOutput();
		FileDeclaration files[] = tree.getFiles();
		
		if (isFlagEnabled(CSOURCE))
		{
			for (int i = 0; i < headers.length; i++)
			{
				log(headers[i]);
				log(sources[i]);
			}
		}
		
		if (!isFlagEnabled(NO_C_OUTPUT))
		{
			log("Writing files...");
		}
		
		StringBuilder allHeaders = new StringBuilder();
		StringBuilder includes   = new StringBuilder();
		StringBuilder types      = new StringBuilder();

		allHeaders.append("#pragma once\n");
		allHeaders.append("#ifndef NOVA_ALL_HEADERS\n");
		allHeaders.append("#define NOVA_ALL_HEADERS\n\n");
		allHeaders.append("#include <Nova.h>\n");
		allHeaders.append("#include <math.h>\n");
		allHeaders.append("#include <ExceptionHandler.h>\n");
		allHeaders.append("#include <setjmp.h>\n").append('\n');
		
		for (int i = 0; i < files.length; i++)
		{
			FileDeclaration file   = files[i];
			String          header = headers[i];
			String          source = sources[i];
			File            parent = files[i].getPackage().getParentFile();
			
			types.append("typedef struct ").append(file.getName()).append(' ').append(file.getName()).append(';').append('\n');
			includes.append("#include <").append(file.generateCHeaderName()).append('>').append('\n');
			
			try
			{
				if (!isFlagEnabled(NO_C_OUTPUT))
				{
					File headerFile = FileUtils.writeFile(file.generateCHeaderName(), parent, header);
					File sourceFile = FileUtils.writeFile(file.generateCSourceName(), parent, source);
				
					cHeaderFiles.add(headerFile);
					cSourceFiles.add(sourceFile);
					
					if (!isFlagEnabled(KEEP_C))
					{
						lingeringFiles.add(headerFile);
						lingeringFiles.add(sourceFile);
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				
				completed();
			}
		}
		
		allHeaders.append(types).append('\n');
		allHeaders.append(includes).append('\n');
		
		allHeaders.append("#endif");
		
		if (!isFlagEnabled(NO_C_OUTPUT))
		{
			log("Done writing files.");
		}
	}
	
	private StringBuilder generateNativeVirtualMethodAssignments()
	{
		StringBuilder builder = new StringBuilder();
		
		Program root = tree.getRoot();
		
		for (int i = 0; i < root.getNumVisibleChildren(); i++)
		{
			FileDeclaration  file  = (FileDeclaration)root.getVisibleChild(i);
			
			for (ClassDeclaration clazz : file.getClassDeclarations())
			{
				MethodDeclaration[] methods = clazz.getVisibleNativeMethods();
				
				for (MethodDeclaration method : methods)
				{
					if (method instanceof NovaMethodDeclaration)
					{
						NovaMethodDeclaration n = (NovaMethodDeclaration)method;
						
						if (n.isOverridden() && !(n instanceof Constructor))
						{
							String itable = "";
							
							if (n.getParentClass() instanceof Interface)
							{
								itable = InterfaceVTable.IDENTIFIER + ".";
							}
							
							builder.append(ENVIRONMENT_VAR + "." + clazz.getNativeLocation() + "." + n.generateCSourceNativeName(new StringBuilder(), false) + " = " + clazz.getVTableNodes().getExtensionVTable().getName() + "." + itable + n.generateCVirtualMethodName() + ";\n");
						}
					}
				}
			}
		}
		
		return builder;
	}
	
	/**
	 * Insert the main method into the correct file. Also set up the
	 * initialization for the program within the main method.
	 */
	private void insertMainMethod()
	{
		MethodDeclaration mainMethod = tree.getMainMethod();
		
		if (mainMethod == null)
		{
			if (!isFlagEnabled(LIBRARY))
			{
				SyntaxMessage.error("No main method found in program", this);
				
				completed();
			}
			
			return;
		}
		
		StringBuilder staticBlockCalls  = generateStaticBlockCalls();
		StringBuilder nativeAssignments = generateNativeVirtualMethodAssignments();
		
		FileDeclaration fileDeclaration = mainMethod.getFileDeclaration();
		
		if (mainMethod != null)
		{
//			FileDeclaration file = mainMethod.getFileDeclaration();
//			file.addChild(Import.decodeStatement(file, "import \"GC\"", file.getLocationIn(), true, false));
			Value gcInit = (Value)SyntaxTree.decodeIdentifierAccess(mainMethod, "GC.init()", mainMethod.getLocationIn(), true);
			Value gcColl = (Value)SyntaxTree.decodeIdentifierAccess(mainMethod, "GC.collect()", mainMethod.getLocationIn(), true);
			Value enter  = (Value)SyntaxTree.decodeIdentifierAccess(mainMethod, "Console.waitForEnter()", mainMethod.getLocationIn(), true);
			
			Instantiation nullConstructor = Instantiation.decodeStatement(mainMethod, "new Null()", mainMethod.getLocationIn(), true);
			Constructor   strConstructor  = (Constructor)((MethodCall)Instantiation.decodeStatement(mainMethod, "new String(new Char[0])", mainMethod.getLocationIn(), true).getIdentifier()).getDeclaration();
			
			StringBuilder mainMethodText = new StringBuilder();
			
			mainMethodText.append('\n').append('\n');
			mainMethodText.append("nova_standard_primitive_Nova_Null* nova_null;").append('\n');
			mainMethodText.append("void* ").append(Literal.GARBAGE_IDENTIFIER).append(';').append('\n');
			mainMethodText.append('\n');
			mainMethodText.append("int main(int argc, char** argvs)").append('\n');
			mainMethodText.append("{").append('\n');
			mainMethodText.append	("nova_standard_Nova_String** args;").append('\n');
			mainMethodText.append	("int      i;").append('\n').append('\n');
			mainMethodText.append	("nova_standard_exception_Nova_ExceptionData* ").append(Exception.EXCEPTION_DATA_IDENTIFIER).append(" = 0;").append('\n');
//			mainMethodText.append	("ShowWindow(FindWindowA(\"ConsoleWindowClass\", NULL), 0);").append('\n');
//			mainMethodText.append	("FreeConsole();").append('\n');
//			mainMethodText.append	("AllocConsole();").append('\n');
			mainMethodText.append	("srand(currentTimeMillis());").append('\n');
			mainMethodText.append	(Literal.GARBAGE_IDENTIFIER).append(" = malloc(sizeof(void*));").append('\n');
			mainMethodText.append	(gcInit.generateCSource()).append('\n');
			mainMethodText.append	("nova_null = ").append(nullConstructor.generateCSourceFragment()).append(';').append('\n');
			mainMethodText.append	(nativeAssignments).append('\n');
			mainMethodText.append	(staticBlockCalls).append('\n');
			mainMethodText.append	("args = (nova_standard_Nova_String**)NOVA_MALLOC(argc * sizeof(nova_standard_Nova_String));").append('\n');
			mainMethodText.append	('\n');
			mainMethodText.append	("for (i = 0; i < argc; i++)").append('\n');
			mainMethodText.append	("{").append('\n');
			mainMethodText.append		("char* str = (char*)NOVA_MALLOC(sizeof(char) * strlen(argvs[i]) + 1);").append('\n');
			mainMethodText.append		("copy_string(str, argvs[i]);").append('\n');
			mainMethodText.append		("args[i] = ").append(strConstructor.generateCSourceName()).append("(0, 0, str);").append('\n');
			mainMethodText.append	("}").append('\n');
			mainMethodText.append	('\n');
			mainMethodText.append	("TRY").append('\n');
			mainMethodText.append	('{').append('\n');
			mainMethodText.append		(mainMethod.generateCSourceName()).append("(0, ").append(Exception.EXCEPTION_DATA_IDENTIFIER).append(", args);").append('\n');
			mainMethodText.append	('}').append('\n');
			mainMethodText.append	("CATCH (1)").append('\n');
			mainMethodText.append	('{').append('\n');
			mainMethodText.append		("nova_standard_exception_Nova_Exception* base = (nova_standard_exception_Nova_Exception*)").append(Exception.EXCEPTION_DATA_IDENTIFIER).append("->nova_standard_exception_Nova_ExceptionData_Nova_thrownException;").append('\n');
			mainMethodText.append		("printf(\"Exception in Thread 'main': %s\", base->nova_standard_exception_Nova_Exception_Nova_message->nova_standard_Nova_String_Nova_chars);").append('\n');
			mainMethodText.append		(enter.generateCSource()).append('\n');
			mainMethodText.append	('}').append('\n');
			mainMethodText.append	("FINALLY").append('\n');
			mainMethodText.append	('{').append('\n');
			mainMethodText.append		('\n');
			mainMethodText.append	('}').append('\n');
			mainMethodText.append	("END_TRY;").append('\n');

			if (OS == WINDOWS)
			{
				mainMethodText.append("FreeConsole();").append('\n');
			}

			mainMethodText.append   ("NOVA_FREE(args);").append('\n');
			mainMethodText.append	(gcColl.generateCSource()).append('\n');
			mainMethodText.append	('\n');
			mainMethodText.append	("return 0;").append('\n');
			mainMethodText.append("}\n");
			
			String newSource = fileDeclaration.generateCSource() + mainMethodText.toString();
			
			newSource = SyntaxUtils.formatText(newSource);
			
			fileDeclaration.setSource(newSource);
		}
	}
	
	private StringBuilder generateStaticBlockCalls()
	{
		StringBuilder builder = new StringBuilder();
		
		Program root = tree.getRoot();
		
		for (int i = 0; i < root.getNumVisibleChildren(); i++)
		{
			FileDeclaration  file  = (FileDeclaration)root.getVisibleChild(i);
			
			for (ClassDeclaration clazz : file.getClassDeclarations())
			{
				TypeList<StaticBlock> blocks = clazz.getStaticBlockList();
				
				for (int j = 0; j < blocks.getNumVisibleChildren(); j++)
				{
					StaticBlock.generateCMethodCall(builder, clazz).append(';').append('\n');
				}
			}
		}
		
		return builder;
	}
	
	/**
	 * Compile the generated c code into an executable file.
	 */
	private void compileC()
	{
		StringBuilder cmd = new StringBuilder();
		
		File compilerDir  = null;
		
		if (compiler == GCC)
		{
			compilerDir = workingDir;
			
			cmd.append("gcc -pipe -mwindows -mconsole ");
			
			if (OS == WINDOWS)
			{
				cmd.append("-Wl,--enable-stdcall-fixup ");
			}
			
			if (!isFlagEnabled(NO_OPTIMIZE))
			{
				if (isFlagEnabled(SMALL_BIN))
				{
					cmd.append("-ffast-math ");
				}
				else
				{
					cmd.append("-march=native -fomit-frame-pointer ");
				}
			}
		}
		else if (compiler == TCC)
		{
			compilerDir = new File(StringUtils.removeSurroundingQuotes(formatPath(workingDir + "/../compiler/tcc")));
			
			cmd.append("compiler/tcc/tcc.exe ");
		}
		else if (compiler == CLANG)
		{
			cmd.append("clang ");
		}
		
		if (!isFlagEnabled(NO_GC))
		{
			cmd.append("-DUSE_GC ");
		}
		
		if (isFlagEnabled(SMALL_BIN))
		{
			cmd.append("-Os -s ");
		}
		else if (!isFlagEnabled(NO_OPTIMIZE))
		{
			cmd.append("-O2 ");
		}
		
		for (int i = 0; i < includeDirectories.size(); i++)
		{
			String dir = includeDirectories.get(i);
			
			cmd.append("-I").append(formatPath(dir)).append(' ');
		}

		String libDir    = workingDir + "/bin";
		String incDir    = workingDir + "../include/";
		
//		String libNova   = formatPath(libDir + "libNova" + DYNAMIC_LIB_EXT);
//		String libThread = formatPath(libDir + "libThread" + DYNAMIC_LIB_EXT);
//		String libGC     = formatPath(libDir + "gc" + DYNAMIC_LIB_EXT);
		
//		cmd.append(libNova).append(' ');
//		cmd.append(libThread).append(' ');
//		cmd.append(libGC).append(' ');
		
		cmd.append(formatPath(nativeInterfaceSource.getAbsolutePath())).append(' ');
		cmd.append(formatPath(incDir + "Nova.c")).append(' ');
//		cmd.append(formatPath(incDir + "LibNovaThread.c")).append(' ');
		
		FileDeclaration[] files = tree.getFiles();
		
		for (FileDeclaration sourceFile : files)
		{
			cmd.append(formatPath(sourceFile.getPackage().getParentFile() + "/" + sourceFile.generateCSourceName())).append(' ');
		}
		
		for (String external : externalImports)
		{
			cmd.append(formatPath(external)).append(' ');
		}
		
		cmd.append("-o ").append('"').append(outputFile.getAbsolutePath()).append('"').append(' ');

		if (OS == LINUX)
		{
			cmd.append("-L/usr/include/openssl ");
		}
		
		cmd.append("-L" + formatPath(libDir) + " -lcrypto ");
		
//		cmd.append("-Ofast ");
//		cmd.append("-s ");

		if (!isFlagEnabled(NO_GC))
		{
			cmd.append("-lgc -Wl,-L" + formatPath(libDir) + " ");

			if (OS != MACOSX)
			{
				cmd.append("-Wl,-R ");
			}
		}
		
		if (OS == LINUX)// || OS == MACOSX)
		{
			cmd.append("-lm -lpthread -ldl -lc -lmysqlclient ");
		}
		else if (OS == WINDOWS)
		{
			cmd.append("-lws2_32 -lmysql ");
		}
		
		if (isFlagEnabled(C_ARGS))
		{
			System.out.println(cmd);
		}
		
		if (isFlagEnabled(DRY_RUN))
		{
			completed();
		}
		
		log("Compiling C sources...");
		
		final Command command = new Command(cmd.toString(), compilerDir);
		
		command.addCommandListener(new CommandListener()
		{
			boolean failed = false;

			String currentMessage = "";
			
			@Override
			public void resultReceived(int result)
			{
				if (stream(visibleCompilerMessages).anyMatch(x -> currentMessage.contains(x + ":")))
				{
					System.err.println(currentMessage.trim());
				}

				if (!failed)
				{
					log("Compilation succeeded.");
				}
				else
				{
					System.err.println("Compilation failed.");
				}
			}
			
			@Override
			public void messageReceived(String message)
			{
				System.out.println(message);
			}
			
			@Override
			public void errorReceived(String message)
			{
				if (compiler == TCC)
				{
					if (message.contains("error: "))
					{
						failed = true;
					}
				}
				else if (compiler == GCC)
				{
					if (message.contains("\nerror: ") || message.contains(": error: ") || message.contains(": fatal error: "))
					{
						failed = true;
					}
				}
				else
				{
					failed = true;
				}

				currentMessage += message;

				if (message.trim().equals("^"))
				{
					//"(.+?(:\\s*?(\\d+:[\\n\\r]|((warning|error):[^^]+))))+"
					if (stream(visibleCompilerMessages).anyMatch(x -> currentMessage.contains(x + ":")))
					{
						System.err.println(currentMessage);
					}

					currentMessage = "";
				}
				else
				{
					currentMessage += "\n";
				}
			}
			
			@Override
			public void commandExecuted()
			{
				try
				{
					command.terminate();
					
					completed();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		try
		{
			command.execute();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove all of the relative syntax from the given path.<br>
	 * <br>
	 * For example: Passing a path of "C:/folder/../dir1/dir2" would
	 * return a path of "C:/dir1/dir2"
	 * 
	 * @param path The path to remove the relative syntax from.
	 * @return The newly formatted path.
	 */
	private String formAbsolutePath(String path)
	{
		StringBuilder absolute = new StringBuilder(path);
		
		int index = absolute.indexOf("..");
		
		while (index >= 0)
		{
			int index2 = absolute.lastIndexOf("/", index - 2);
			
			absolute.delete(index2, index + 2);
			
			index = absolute.indexOf("..");
		}
		
		return absolute.toString();
	}
	
	/**
	 * Format a path according to how the specified OS needs it.
	 * 
	 * @param path The path to format for the OS standards.
	 * @return The formatted path.
	 */
	private String formatPath(String path)
	{
		path = path.replace("\\", "/");
		
		path = formAbsolutePath(path);
		
		if (OS == WINDOWS)
		{
			path = StringUtils.removeSurroundingQuotes(path);
			
			return '"' + path + '"';
		}
		else
		{
			return StringUtils.escapeSpaces(path);
		}
	}
	
	/**
	 * Get the directory that holds the Nova library.
	 * 
	 * @return The location of the directory that holds the library.
	 */
	private String getLibraryDir()
	{
		return formatPath(workingDir + "/../lib");
	}
	
	/**
	 * Get the directory that holds the Nova include files.
	 * 
	 * @return The location of the directory that holds the include files.
	 */
	private String getIncludeDir()
	{
		return formatPath(workingDir + "/../include");
	}
	
	/**
	 * Output the log message from the compiler.
	 * 
	 * @param message The message describing what happened.
	 */
	public void log(String message)
	{
		log(flags, message);
	}
	
	/**
	 * Output the log message from the compiler.
	 * 
	 * @param flags The flags that verify whether the compiler is verbose.
	 * @param message The message describing what happened.
	 */
	public static void log(long flags, String message)
	{
		if (isFlagEnabled(flags, VERBOSE))
		{
			System.out.println(message);
		}
	}
	
	/**
	 * Output a warning message from the compiler.
	 * 
	 * @param message The message describing the warning.
	 */
	public void warning(String message)
	{
		errors.add("Warning: " + message);
	}
	
	/**
	 * Output an error message from the compiler.
	 * 
	 * @param message The message describing the error.
	 */
	public void error(String message)
	{
		if (!isFlagEnabled(DRY_RUN))
		{
			enableFlag(DRY_RUN);
		}
		
		String error = "Error: " + message;
		
		errors.add(error);
	}
	
	/**
	 * Get whether or not the compilation has accumulated any errors.
	 * 
	 * @return Whether or not there are any errors currently.
	 */
	public boolean containsErrors()
	{
		return errors.size() > 0;
	}
	
	/**
	 * Add the given external import location to be added to the
	 * compilation list.
	 * 
	 * @param file The File that is importing the location.
	 * @param location The location that is being imported.
	 */
	public void addExternalImport(FileDeclaration file, String location)
	{
		if (!StringUtils.containsString(location, FileDeclaration.DEFAULT_IMPORTS))
		{
//			location = file.getFile().getParent() + "/" + location;
			location = location.substring(0, location.length() - 1) + "c"; 
			location = findFileLocation(location);
			
			if (location != null && !StringUtils.containsString(externalImports, location))
			{
				externalImports.add(location);
			}
		}
	}
	
	/**
	 * Find the location that the given filename is located within the
	 * compilation's library directories.
	 * 
	 * @param filename The name of the file to search for.
	 * @return The location of the file with the given filename. If the
	 * 		location was not found, null is returned.
	 */
	private String findFileLocation(String filename)
	{
		for (String dir : includeDirectories)
		{
			String location = StringUtils.removeSurroundingQuotes(dir) + "/" + filename;
			
			File f = new File(location);
			
			if (f.isFile())
			{
				return location;
			}
		}
		
		return null;
	}
	
	/**
	 * Parse the arguments passed to the compiler.
	 * 
	 * @param args The list of arguments to parse.
	 */
	private void parseArguments(String args[])
	{
		// Start off the lastInput index to -1 because it will start
		// checking for (index - 1).
		// (index starts at 0, therefore 0 - 1 = -1)
		int lastInput = -1;
		
		// Declare and initialize two booleans used to keep track of
		// whether or not the argument parser is expecting a certain
		// type of input at the current argument.
		boolean expectingOutputFile       = false;
		boolean expectingIncludeDirectory = false;
		
		// Iterate through all of the arguments.
		for (int i = 0; i < args.length; i++)
		{
			// Lowercase the argument for easier non-case-sensitive String
			// matching.
			String arg = args[i].toLowerCase();
			
			if (arg.length() <= 0)
			{
				if (lastInput == i - 1)
				{
					lastInput = i;
				}
				
				continue;
			}
			
			// Create temporary variables holding the current values.
			boolean expectingIncludeDirectoryTemp = expectingIncludeDirectory;
			
			// Set the variables to false in the expectation of a
			// different type of argument.
			expectingIncludeDirectory = false;
			
			// Check all other types of arguments.
			
			// If the user is trying to set the output location.
			if (arg.equals("-o"))
			{
				expectingOutputFile = true;
			}
			// If the user is trying to set the source include directory.
			else if (arg.equals("-dir"))
			{
				expectingIncludeDirectory = true;
			}
			// If the user wants to run the application after compilation.
			else if (arg.equals("-run"))
			{
				enableFlag(RUNTIME);
			}
			// Do not use bdw garbage collection.
			else if (arg.equals("-nogc"))
			{
				enableFlag(NO_GC);
			}
			else if (arg.equals("-no-c-output"))
			{
				enableFlag(NO_C_OUTPUT);
			}
			else if (arg.equals("-no-optimize"))
			{
				enableFlag(NO_OPTIMIZE);
			}
			else if (arg.equals("-no-notes"))
			{
				enableFlag(NO_NOTES);
			}
			else if (arg.equals("-no-warnings"))
			{
				enableFlag(NO_WARNINGS);
			}
			else if (arg.equals("-no-errors"))
			{
				enableFlag(NO_ERRORS);
			}
			// If the user wants to view the c source output.
			else if (arg.equals("-csource"))
			{
				enableFlag(CSOURCE);
			}
			// If the user wants to format the c source output.
			else if (arg.equals("-formatc"))
			{
				enableFlag(FORMATC);
			}
			// If the user wants a more verbose compilation output,
			// explaining each step.
			else if (arg.equals("-verbose") || arg.equals("-v"))
			{
				if (BENCHMARK <= 0)
				{
					enableFlag(VERBOSE);
				}
			}
			// If the user wants to use the GCC c compiler.
			else if (arg.equals("-gcc"))
			{
				compiler = GCC;
			}
			// If the user wants to use the TCC c compiler.
			else if (arg.equals("-tcc"))
			{
				compiler = TCC;
			}
			// If the user wants to use the CLANG LLVM compiler.
			else if (arg.equals("-clang"))
			{
				compiler = CLANG;
			}
			// If the user wants to run a single threaded compilation
			else if (arg.equals("-single-thread"))
			{
				enableFlag(SINGLE_THREAD);
			}
			// If the user wants to perform a dry run of the compilation
			// process.
			else if (arg.equals("-dry"))
			{
				enableFlag(DRY_RUN);
			}
			// If the user wants to keep the files that hold the c output.
			else if (arg.equals("-keepc"))
			{
				enableFlag(KEEP_C);
			}
			// If the user wants to obtain the c compiler arguments.
			else if (arg.equals("-cargs"))
			{
				enableFlag(C_ARGS);
			}
			// If the user wants to generate a smaller executable output.
			else if (arg.equals("-small"))
			{
				enableFlag(SMALL_BIN);
			}
			// If the user wants to output a library instead of an
			// executable.
			else if (arg.equals("-library"))
			{
				enableFlag(LIBRARY);
			}
			// If none of the arguments were matched, check these:
			else
			{
				expectingIncludeDirectory = expectingIncludeDirectoryTemp;
				
				if (args[i].startsWith("\""))
				{
					args[i] = StringUtils.removeSurroundingQuotes(args[i]);
				}
				
				// If the argument is one of the first arguments passed
				// (If it is one of the sources to compile)
				if (lastInput == i - 1)
				{
					File file = new File(args[i]);
					
					inputFiles.add(file);
					
					lastInput = i;
				}
				// Check if we are still dealing with any  ongoing arguments
				// still.
				else if (expectingOutputFile)
				{
					outputFile = new File(args[i]);
					
					expectingOutputFile = false;
				}
				else if (expectingIncludeDirectory)
				{
					includeDirectories.add(formatPath(args[i]));
				}
				else
				{
					error("Unknown argument '" + args[i] + "'");
					completed();
				}
			}
		}
		
		validateInputFiles();
		
//		if (outputFile == null)
//		{
//			enableFlag(RUNTIME);
//			
//			outputFile = new File(workingDir, "bin/Executa.exe");
//		}
	}
	
	/**
	 * Validate that the input files end with .fat. If any of them
	 * do not, an error will be output. Also outputs an error if the
	 * input file does not exist or is a directory.
	 */
	private void validateInputFiles()
	{
		boolean working = true;
		
		for (File f : inputFiles)
		{
			if (!f.getName().toLowerCase().endsWith(".nova"))
			{
				working = false;
				
				error("Input file '" + f.getName() + "' must have an extension of .nova");
			}
			else if (!f.exists())
			{
				working = false;
				
				error("Input file '" + f.getAbsolutePath() + "' does not exist.");
			}
			else if (!f.isFile())
			{
				working = false;
				
				error("Input file '" + f.getAbsolutePath() + "' is not a file.");
			}
		}
		
		if (!working)
		{
			startTimer();
			stopTimer();
			
			completed();
		}
	}
	
	/**
	 * Enable the specified flag.
	 * 
	 * @param flag The flag to set enable.
	 */
	private void enableFlag(long flag)
	{
		flags |= flag;
	}
	
	/**
	 * Disable the specified flag.
	 * 
	 * @param flag The flag to disable.
	 */
	private void disableFlag(long flag)
	{
		flags = flags & (~flag);
	}
	
	/**
	 * Check if the specific flag is enabled for the compiler.
	 * 
	 * @param flag The flag to check if is enabled.
	 * @return Whether or not the flag is enabled.
	 */
	public boolean isFlagEnabled(long flag)
	{
		return isFlagEnabled(flags, flag);
	}
	
	/**
	 * Check if the specific flag is enabled for the given set of flags.
	 * 
	 * @param flags The flags to verify the flag with.
	 * @param flag The flag to check if is enabled.
	 * @return Whether or not the flag is enabled.
	 */
	public static boolean isFlagEnabled(long flags, long flag)
	{
		return (flags & flag) != 0;
	}
	
	/**
	 * Get the working directory of the compiler.
	 * 
	 * @return The working directory of the compiler.
	 */
	private static String getWorkingDirectoryPath()
	{
		if (ANDROID_DEBUG)
		{
			return "/mnt/sdcard/AppProjects/Nova/";
		}
			
		return System.getProperty("user.dir").replace('\\', '/') + "/";
	}
	
	/**
	 * Start the compilation timer.
	 */
	private void startTimer()
	{
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * Stop the compilation timer.
	 */
	private void stopTimer()
	{
		endTime = System.currentTimeMillis();
	}
	 
	/**
	 * Get the time the compiler took to compile the input files.
	 * 
	 * @return The time in milliseconds it took to compile.
	 */
	private long getCompileTime()
	{
		return endTime - startTime;
	}
	
	public void setTestClasses(boolean testClasses)
	{
		this.testClasses = testClasses;
	}
	
	public SyntaxTree getTree()
	{
		return tree;
	}
	
	/**
	 * Delete the files that are left over from the compilation process.
	 */
	private void deleteLingeringFiles()
	{
		Iterator<File> files = lingeringFiles.iterator();
		
		while (files.hasNext())
		{
			File file = files.next();
			
			if (!file.delete())
			{
				System.err.println("Error: Was not able to delete file: " + file.getAbsolutePath());
			}
		}
	}
	
	/**
	 * Output all of the stored errors, warnings, and other messages.
	 */
	private void outputMessages()
	{
		for (String s : messages)
		{
			System.out.println(s);
		}
		
		if (errors.size() > 0)
		{
			System.err.println("Compilation failed with " + errors.size() + " error" + (errors.size() > 1 ? "s" : "") + ".");
		}
		
		for (String s : errors)
		{
			System.err.println(s);
		}
	}
	
	/**
	 * Method called whenever the compilation sequence has completed.
	 */
	public void completed()
	{
		stopTimer();
		
		log("Compile time: " + getCompileTime() + "ms");
		
		deleteLingeringFiles();
		
		outputMessages();
		
		if (isFlagEnabled(RUNTIME))
		{
//			final Command c = new Command("start bin/Executa.exe", workingDir);
//			
//			c.addCommandListener(new CommandListener()
//			{
//				
//				@Override
//				public void resultReceived(int result)
//				{
//					if (result != 0)
//					{
//						System.err.println("error.");
//					}
//				}
//				
//				@Override
//				public void messageReceived(String message)
//				{
//					System.out.println(message);
//				}
//				
//				@Override
//				public void errorReceived(String message)
//				{
//					System.err.println(message);
//				}
//				
//				@Override
//				public void commandExecuted()
//				{
//					try
//					{
//						c.terminate();
//					}
//					catch (InterruptedException e)
//					{
//						e.printStackTrace();
//					}
//				}
//			});
//			try
//			{
//				c.execute();
//			}
//			catch (IOException e)
//			{
//				e.printStackTrace();
//			}
		}
		
		if (!ANDROID_DEBUG)
		{
			System.exit(0);
		}
	}
	
	/**
	 * Used to represent a debugging breakpoint...
	 * 
	 * @param condition Whether or not to break.
	 */
	public static void debuggingBreakpoint(boolean condition)
	{
		if (condition)
		{
			System.out.println("Breakpoint");
		}
	}
	
	public static Nova generateTemporaryController()
	{
		Nova controller = new Nova();
		controller.setTestClasses(false);
		controller.compile(new String[0], false);
		
		return controller;
	}
	
	/**
	 * Call the test case methods for all of the classes to make sure they
	 * are working correctly.
	 */
	private void testClasses()
	{
		if (testClasses)
		{
			System.out.println("Testing classes");
			
			TestContext context = new TestContext();
			
			String error = null;
			
			error = ArgumentList.test(context);
			
			if (error == null)
			{
				error = Assignment.test(context);
				
				if (error == null)
				{
					error = BinaryOperation.test(context);
					
					if (error == null)
					{
						error = Cast.test(context);
						
						if (error == null)
						{
							error = ClassDeclaration.test(context);
							
							if (error == null)
							{
								error = Closure.test(context);
								
								if (error == null)
								{
									error = ClosureDeclaration.test(context);
									
									if (error == null)
									{
										error = Condition.test(context);
										
										if (error == null)
										{
											error = Constructor.test(context);
											
											if (error == null)
											{
												error = Destructor.test(context);
												
												if (error == null)
												{
													error = Dimensions.test(context);
													
													if (error == null)
													{
														error = ElseStatement.test(context);
														
														if (error == null)
														{
															error = ExternalMethodDeclaration.test(context);
															
															if (error == null)
															{
																error = ExternalType.test(context);
																
																if (error == null)
																{
																	error = ExternalTypeList.test(context);
																	
																	if (error == null)
																	{
																		error = FileDeclaration.test(context);
																		
																		if (error == null)
																		{
																			error = ForLoop.test(context);
																			
																			if (error == null)
																			{
																				error = Identifier.test(context);
																				
																				if (error == null)
																				{
																					error = IfStatement.test(context);
																					
																					if (error == null)
																					{
																						error = IIdentifier.test(context);
																						
																						if (error == null)
																						{
																							error = Import.test(context);
																							
																							if (error == null)
																							{
																								error = ImportList.test(context);
																								
																								if (error == null)
																								{
																									error = InstanceDeclaration.test(context);
																									
																									if (error == null)
																									{
																										error = Instantiation.test(context);
																										
																										if (error == null)
																										{
																											error = IValue.test(context);
																											
																											if (error == null)
																											{
																												error = Literal.test(context);
																												
																												if (error == null)
																												{
																													error = LocalDeclaration.test(context);
																													
																													if (error == null)
																													{
																														error = Loop.test(context);
																														
																														if (error == null)
																														{
																															error = LoopInitialization.test(context);
																															
																															if (error == null)
																															{
																																error = LoopUpdate.test(context);
																																
																																if (error == null)
																																{
																																	error = MethodCall.test(context);
																																	
																																	if (error == null)
																																	{
																																		error = MethodCallArgumentList.test(context);
																																		
																																		if (error == null)
																																		{
																																			error = MethodDeclaration.test(context);
																																			
																																			if (error == null)
																																			{
																																				error = MethodList.test(context);
																																				
																																				if (error == null)
																																				{
																																					error = Node.test(context);
																																					
																																					if (error == null)
																																					{
																																						error = Operator.test(context);
																																						
																																						if (error == null)
																																						{
																																							error = Parameter.test(context);
																																							
																																							if (error == null)
																																							{
																																								error = ParameterList.test(context);
																																								
																																								if (error == null)
																																								{
																																									error = Priority.test(context);
																																									
																																									if (error == null)
																																									{
																																										error = Program.test(context);
																																										
																																										if (error == null)
																																										{
																																											error = Return.test(context);
																																											
																																											if (error == null)
																																											{
																																												error = Scope.test(context);
																																												
																																												if (error == null)
																																												{
																																													error = SyntaxTree.test(context);
																																													
																																													if (error == null)
																																													{
																																														error = TreeGenerator.test(context);
																																														
																																														if (error == null)
																																														{
																																															error = UnaryOperation.test(context);
																																															
																																															if (error == null)
																																															{
																																																error = Until.test(context);
																																																
																																																if (error == null)
																																																{
																																																	error = Value.test(context);
																																																	
																																																	if (error == null)
																																																	{
																																																		error = VTable.test(context);
																																																		
																																																		if (error == null)
																																																		{
																																																			error = GenericCompatible.test(context);

																																																			if (error == null)
																																																			{
																																																				error = WhileLoop.test(context);
																																																				
																																																				if (error == null)
																																																				{
																																																					error = Switch.test(context);
																																																				}
																																																			}
																																																		}
																																																	}
																																																}
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			if (error != null)
			{
				System.err.println("Pre-compilation class tests failed:");
				System.err.println(error);
				
				completed();
			}
			
			System.out.println("Done testing classes");
		}
	}
}
