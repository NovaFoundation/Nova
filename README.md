Nova Programming Language
===========================

The Nova programming language is a C-based language that compiles into portable C code. The Java-like syntax makes Nova a great choice for those who are not looking to spend countless hours learning new concepts. The main idea behind Nova is to make cross-platform code run natively on a multitude of devices.



__Aim__

  * Manage abstraction at a high level without a large performance hit.
  * Engineer an easy-to-integrate architecture for using C code within a project.
  * Ensure a smooth transition between running code on various operating systems.
  * Deliver a uniform exception handling environment.
  * Simplify common tasks and concepts.
  * Restore the intuitive nature of Object Oriented Programming by reducing the use of conventions.
  * Include support for clear and concise documentation of code.
  * Increase the ability to reuse code in future instances.



__Pre-requisites__

  * The early version of the compiler uses Java 1.8 or later.
  * Pretty much any machine that has a GCC C compiler.
  * Patience. (There will be bugs)



__Instructions of Use__

  * To run the compiler, first locate the Nova.jar file.
  * In command line or terminal run the jar file with the required arguments.
  * The required arguments include: The input files and the output destination.



__An Example command to compile a source__

`java -jar Nova.jar SourceFile1.nova SourceFile2.nova -o OutputExecutable.exe`

  * The `java -jar Nova.jar` component of the command is needed to run the compiler program.
  * The `SourceFile.nova SourceFile2.nova` components of the command are the input files to compile. There is no limit to how many source files can be compiled at a time, however at least one is required.
  * The `-o OutputExecutable.exe` component of the command specifies the name of the executable to produce. The extension of the executable will vary depending on the operating system you are compiling for.



__Optional arguments to the Compiler Include__

  * `-csource` Output the compiled C source files during compilation.
  * `-verbose` or `-v` Output debugging messages during compilation.
  * `-dir` Used to specify the directory in which to include in the compilation.
  * `-dry` Perform a dry-run of the compilation. Do not compile into an executable.
  * `-gcc` Compile the program with the GCC (GNU Compiler Collection) compiler.
  * `-tcc` Compile the program with the TCC (Tiny C Compiler) compiler.
  * `-keepc` Keep the C output files that were generated during the compilation process.
  * `-cargs` Display the arguments that were passed to the C compiler.
  * `-nogc` Compile the program without garbage collection. (Not recommended)
  * `-small` Generate the smallest possible executable output.
  * `-single-thread` Compile the program with only a single thread.

The default C compiler for Windows is GCC because it is more stable.



© 2014 Fathomsoft