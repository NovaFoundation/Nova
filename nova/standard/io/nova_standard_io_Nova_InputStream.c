#include <precompiled.h>
#include <nova/standard/io/nova_standard_io_Nova_InputStream.h>


nova_VTable_nova_standard_io_Nova_InputStream nova_VTable_nova_standard_io_Nova_InputStream_val =
{
	nova_standard_Nova_Object_1_Nova_getHashCodeLong,
	nova_standard_Nova_Object_0_Nova_toString,
	nova_standard_Nova_Object_0_Nova_equals,
	nova_standard_io_Nova_InputStream_0_Nova_readString,
	nova_standard_io_Nova_InputStream_0_Nova_readBytes,
};
void nova_standard_io_Nova_InputStreamNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}

nova_standard_io_Nova_InputStream* nova_standard_io_Nova_InputStream_4_Nova_construct(nova_standard_io_Nova_InputStream* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	CCLASS_NEW(nova_standard_io_Nova_InputStream, this,);
	this->vtable = &nova_VTable_nova_standard_io_Nova_InputStream_val;
	nova_standard_Nova_Object_Nova_super((nova_standard_Nova_Object*)this, exceptionData);
	nova_standard_Nova_Object_2_Nova_this((nova_standard_Nova_Object*)(this), exceptionData);
	nova_standard_io_Nova_InputStream_Nova_super(this, exceptionData);
	
	{
		nova_standard_io_Nova_InputStream_4_Nova_this(this, exceptionData);
	}
	
	return this;
}

void nova_standard_io_Nova_InputStream_Nova_destroy(nova_standard_io_Nova_InputStream** this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	NOVA_FREE(*this);
}

nova_standard_Nova_String* nova_standard_io_Nova_InputStream_0_Nova_readString(nova_standard_io_Nova_InputStream* this, nova_standard_exception_Nova_ExceptionData* exceptionData){}
char* nova_standard_io_Nova_InputStream_0_Nova_readBytes(nova_standard_io_Nova_InputStream* this, nova_standard_exception_Nova_ExceptionData* exceptionData){}
void nova_standard_io_Nova_InputStream_4_Nova_this(nova_standard_io_Nova_InputStream* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
}

void nova_standard_io_Nova_InputStream_Nova_super(nova_standard_io_Nova_InputStream* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
}

