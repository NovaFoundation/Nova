#include <precompiled.h>
#include <example/example_Nova_BodyBuilder.h>



example_Extension_VTable_BodyBuilder example_Extension_VTable_BodyBuilder_val =
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
	example_Nova_BodyBuilder_Nova_sayHello,
};


void example_Nova_BodyBuilder_Nova_init_static(nova_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}

example_Nova_BodyBuilder* example_Nova_BodyBuilder_Nova_construct(example_Nova_BodyBuilder* this, nova_exception_Nova_ExceptionData* exceptionData, int example_Nova_BodyBuilder_Nova_weightClass, nova_Nova_String* example_Nova_BodyBuilder_Nova_name)
{
	CCLASS_NEW(example_Nova_BodyBuilder, this,);
	this->vtable = &example_Extension_VTable_BodyBuilder_val;
	nova_Nova_Object_Nova_super((nova_Nova_Object*)this, exceptionData);
	example_Nova_Person_Nova_super((example_Nova_Person*)this, exceptionData);
	example_Nova_BodyBuilder_0_Nova_super(this, exceptionData);
	
	{
		example_Nova_BodyBuilder_1_Nova_this(this, exceptionData, example_Nova_BodyBuilder_Nova_weightClass, example_Nova_BodyBuilder_Nova_name);
	}
	
	return this;
}

void example_Nova_BodyBuilder_Nova_destroy(example_Nova_BodyBuilder** this, nova_exception_Nova_ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	
	NOVA_FREE(*this);
}

void example_Nova_BodyBuilder_1_Nova_this(example_Nova_BodyBuilder* this, nova_exception_Nova_ExceptionData* exceptionData, int example_Nova_BodyBuilder_Nova_weightClass, nova_Nova_String* example_Nova_BodyBuilder_Nova_name)
{
	this->example_Nova_Person_Nova_age = (int)(5);
	this->example_Nova_BodyBuilder_Nova_weightClass = example_Nova_BodyBuilder_Nova_weightClass;
	this->example_Nova_Person_Nova_name = example_Nova_BodyBuilder_Nova_name;
}

void example_Nova_BodyBuilder_Nova_sayHello(example_Nova_BodyBuilder* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	nova_io_Nova_Console_1_Nova_writeLine(0, exceptionData, nova_Nova_String_0_Nova_concat(nova_Nova_String_1_Nova_construct(0, exceptionData, (char*)("Hello from ")), exceptionData, nova_Nova_String_virtual1_Nova_concat((nova_Nova_String*)(this->example_Nova_Person_Nova_name), exceptionData, nova_Nova_String_1_Nova_construct(0, exceptionData, (char*)(" the BodyBuilder")))));
}

void example_Nova_BodyBuilder_0_Nova_super(example_Nova_BodyBuilder* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	this->example_Nova_BodyBuilder_Nova_weightClass = 0;
}
