package net.fathomsoft.nova;

import net.fathomsoft.nova.tree.SyntaxTree;

public abstract class CompileEngine
{
	public Nova controller;
	
	public SyntaxTree tree;
	
	public CompileEngine(Nova controller)
	{
		this.controller = controller;
		this.tree = controller.getTree();
	}
	
	public void init() {}
	
	/**
	 * Compile the generated code into an executable file.
	 */
	public abstract void compile();
	
	public boolean checkArgument(String arg)
	{
		return false;
	}
	
	public boolean checkArgument(String arg, String[] args, int index)
	{
		return checkArgument(arg);
	}
}