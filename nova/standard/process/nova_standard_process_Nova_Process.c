#include <precompiled.h>
#include <nova/standard/process/nova_standard_process_Nova_Process.h>


nova_VTable_nova_standard_process_Nova_Process nova_VTable_nova_standard_process_Nova_Process_val =
{
	nova_standard_Nova_Object_1_Nova_getHashCodeLong,
	nova_standard_Nova_Object_0_Nova_toString,
	nova_standard_Nova_Object_0_Nova_equals,
};
void nova_standard_process_Nova_ProcessNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}

nova_standard_process_Nova_Process* nova_standard_process_Nova_Process_Nova_construct(nova_standard_process_Nova_Process* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_io_Nova_StreamReader* l0_Nova_reader)
{
	CCLASS_NEW(nova_standard_process_Nova_Process, this,);
	this->vtable = &nova_VTable_nova_standard_process_Nova_Process_val;
	nova_standard_Nova_Object_Nova_super((nova_standard_Nova_Object*)this, exceptionData);
	nova_standard_Nova_Object_2_Nova_this((nova_standard_Nova_Object*)(this), exceptionData);
	nova_standard_process_Nova_Process_Nova_super(this, exceptionData);
	
	{
		nova_standard_process_Nova_Process_Nova_this(this, exceptionData, l0_Nova_reader);
	}
	
	return this;
}

void nova_standard_process_Nova_Process_Nova_destroy(nova_standard_process_Nova_Process** this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	nova_standard_io_Nova_StreamReader_Nova_destroy(&(*this)->nova_standard_process_Nova_Process_Nova_reader, exceptionData);
	
	NOVA_FREE(*this);
}

void nova_standard_process_Nova_Process_Nova_this(nova_standard_process_Nova_Process* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_io_Nova_StreamReader* l0_Nova_reader)
{
	this->nova_standard_process_Nova_Process_Nova_reader = l0_Nova_reader;
}

void nova_standard_process_Nova_Process_Nova_super(nova_standard_process_Nova_Process* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	this->nova_standard_process_Nova_Process_Nova_reader = (nova_standard_io_Nova_StreamReader*)nova_null;
}

