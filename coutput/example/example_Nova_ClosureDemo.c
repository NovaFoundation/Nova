#include <precompiled.h>
#include <example/example_Nova_ClosureDemo.h>


typedef struct nova_exception_Nova_ExceptionData nova_exception_Nova_ExceptionData;

typedef int (*example_Nova_ClosureDemo_closure1_Nova_closure)(void*, nova_exception_Nova_ExceptionData*, int, int, void*);
typedef int (*example_Nova_ClosureDemo_closure2_Nova_closure)(void*, nova_exception_Nova_ExceptionData*, int, int, void*);
typedef int (*example_Nova_ClosureDemo_closure3_Nova_closure)(void*, nova_exception_Nova_ExceptionData*, int, int, void*);

example_Extension_VTable_ClosureDemo example_Extension_VTable_ClosureDemo_val =
{
	{
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		(char(*)(nova_operators_Nova_Equals*, nova_exception_Nova_ExceptionData*, nova_Nova_Object*))nova_Nova_Object_0_Nova_equals,
		0,
		0,
		0,
		0,
		0,
	},
	nova_Nova_Object_0_Nova_toString,
	nova_Nova_Object_0_Nova_equals,
	nova_Nova_Object_Accessor_Nova_hashCodeLong,
};



void example_Nova_ClosureDemo_Nova_callClosure(example_Nova_ClosureDemo* this, nova_exception_Nova_ExceptionData* exceptionData, example_Nova_ClosureDemo_closure3_Nova_closure example_Nova_ClosureDemo_Nova_closure, void* example_Nova_ClosureDemo_ref_Nova_closure, void* closure_context);
int example_Nova_ClosureDemo_Nova_multiply(example_Nova_ClosureDemo* this, nova_exception_Nova_ExceptionData* exceptionData, int example_Nova_ClosureDemo_Nova_value1, int example_Nova_ClosureDemo_Nova_value2);
int example_Nova_ClosureDemo_Nova_pow(example_Nova_ClosureDemo* this, nova_exception_Nova_ExceptionData* exceptionData, int example_Nova_ClosureDemo_Nova_base, int example_Nova_ClosureDemo_Nova_pow);
void example_Nova_ClosureDemo_Nova_init_static(nova_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}

example_Nova_ClosureDemo* example_Nova_ClosureDemo_Nova_construct(example_Nova_ClosureDemo* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	CCLASS_NEW(example_Nova_ClosureDemo, this,);
	this->vtable = &example_Extension_VTable_ClosureDemo_val;
	nova_Nova_Object_Nova_super((nova_Nova_Object*)this, exceptionData);
	example_Nova_ClosureDemo_Nova_super(this, exceptionData);
	
	{
		example_Nova_ClosureDemo_0_Nova_this(this, exceptionData);
	}
	
	return this;
}

void example_Nova_ClosureDemo_Nova_destroy(example_Nova_ClosureDemo** this, nova_exception_Nova_ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	NOVA_FREE(*this);
}

void example_Nova_ClosureDemo_Nova_main(example_Nova_ClosureDemo* this, nova_exception_Nova_ExceptionData* exceptionData, nova_datastruct_list_Nova_Array* example_Nova_ClosureDemo_Nova_args)
{
	example_Nova_ClosureDemo* l1_Nova_demo = (example_Nova_ClosureDemo*)nova_null;
	
	l1_Nova_demo = example_Nova_ClosureDemo_Nova_construct(0, exceptionData);
	example_Nova_ClosureDemo_Nova_callClosure(l1_Nova_demo, exceptionData, (example_Nova_ClosureDemo_closure3_Nova_closure)&example_Nova_ClosureDemo_Nova_multiply, nova_null, nova_null);
	example_Nova_ClosureDemo_Nova_callClosure(l1_Nova_demo, exceptionData, (example_Nova_ClosureDemo_closure3_Nova_closure)&example_Nova_ClosureDemo_Nova_pow, nova_null, nova_null);
	nova_io_Nova_Console_Nova_waitForEnter(0, exceptionData);
}

void example_Nova_ClosureDemo_Nova_callClosure(example_Nova_ClosureDemo* this, nova_exception_Nova_ExceptionData* exceptionData, example_Nova_ClosureDemo_closure3_Nova_closure example_Nova_ClosureDemo_Nova_closure, void* example_Nova_ClosureDemo_ref_Nova_closure, void* closure_context)
{
	int l1_Nova_value = 0;
	
	l1_Nova_value = example_Nova_ClosureDemo_Nova_closure(example_Nova_ClosureDemo_ref_Nova_closure, exceptionData, 5, 3, closure_context);
	nova_io_Nova_Console_1_Nova_writeLine(0, exceptionData, nova_Nova_String_0_Nova_concat(nova_Nova_String_1_Nova_construct(0, exceptionData, (char*)("Closure returned ")), exceptionData, nova_primitive_number_Nova_Int_2_Nova_toString(0, exceptionData, l1_Nova_value)));
}

int example_Nova_ClosureDemo_Nova_multiply(example_Nova_ClosureDemo* this, nova_exception_Nova_ExceptionData* exceptionData, int example_Nova_ClosureDemo_Nova_value1, int example_Nova_ClosureDemo_Nova_value2)
{
	nova_io_Nova_Console_1_Nova_writeLine(0, exceptionData, nova_Nova_String_0_Nova_concat(nova_Nova_String_1_Nova_construct(0, exceptionData, (char*)("multiply was called with ")), exceptionData, nova_Nova_String_virtual1_Nova_concat((nova_Nova_String*)(nova_primitive_number_Nova_Int_2_Nova_toString(0, exceptionData, example_Nova_ClosureDemo_Nova_value1)), exceptionData, nova_Nova_String_0_Nova_concat(nova_Nova_String_1_Nova_construct(0, exceptionData, (char*)(" and ")), exceptionData, nova_primitive_number_Nova_Int_2_Nova_toString(0, exceptionData, example_Nova_ClosureDemo_Nova_value2)))));
	return example_Nova_ClosureDemo_Nova_value1 * example_Nova_ClosureDemo_Nova_value2;
}

int example_Nova_ClosureDemo_Nova_pow(example_Nova_ClosureDemo* this, nova_exception_Nova_ExceptionData* exceptionData, int example_Nova_ClosureDemo_Nova_base, int example_Nova_ClosureDemo_Nova_pow)
{
	int l1_Nova_value = 0;
	int l2_Nova_i = 0;
	
	nova_io_Nova_Console_1_Nova_writeLine(0, exceptionData, nova_Nova_String_0_Nova_concat(nova_Nova_String_1_Nova_construct(0, exceptionData, (char*)("pow was called with ")), exceptionData, nova_Nova_String_virtual1_Nova_concat((nova_Nova_String*)(nova_primitive_number_Nova_Int_2_Nova_toString(0, exceptionData, example_Nova_ClosureDemo_Nova_base)), exceptionData, nova_Nova_String_0_Nova_concat(nova_Nova_String_1_Nova_construct(0, exceptionData, (char*)(" and ")), exceptionData, nova_primitive_number_Nova_Int_2_Nova_toString(0, exceptionData, example_Nova_ClosureDemo_Nova_pow)))));
	l1_Nova_value = example_Nova_ClosureDemo_Nova_base;
	l2_Nova_i = (int)0;
	for (; l2_Nova_i < (int)(example_Nova_ClosureDemo_Nova_pow - 1); l2_Nova_i++)
	{
		l1_Nova_value = l1_Nova_value * example_Nova_ClosureDemo_Nova_base;
	}
	return l1_Nova_value;
}

void example_Nova_ClosureDemo_0_Nova_this(example_Nova_ClosureDemo* this, nova_exception_Nova_ExceptionData* exceptionData)
{
}

void example_Nova_ClosureDemo_Nova_super(example_Nova_ClosureDemo* this, nova_exception_Nova_ExceptionData* exceptionData)
{
}
