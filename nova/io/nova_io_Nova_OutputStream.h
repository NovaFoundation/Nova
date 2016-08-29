#pragma once
#ifndef FILE_nova_io_Nova_OutputStream_NOVA
#define FILE_nova_io_Nova_OutputStream_NOVA

typedef struct nova_io_Nova_OutputStream nova_io_Nova_OutputStream;


#include <Nova.h>
#include <ExceptionHandler.h>
#include <InterfaceVTable.h>
#include <nova/exception/nova_exception_Nova_ExceptionData.h>
#include <nova/exception/nova_exception_Nova_Exception.h>
#include <nova/exception/nova_exception_Nova_DivideByZeroException.h>
#include <nova/io/nova_io_Nova_Console.h>
#include <nova/primitive/number/nova_primitive_number_Nova_Number.h>
#include <nova/primitive/number/nova_primitive_number_Nova_Byte.h>
#include <nova/primitive/number/nova_primitive_number_Nova_Short.h>
#include <nova/primitive/number/nova_primitive_number_Nova_Int.h>
#include <nova/primitive/number/nova_primitive_number_Nova_Long.h>
#include <nova/primitive/number/nova_primitive_number_Nova_Float.h>
#include <nova/primitive/number/nova_primitive_number_Nova_Double.h>
#include <nova/primitive/nova_primitive_Nova_Null.h>
#include <nova/primitive/number/nova_primitive_number_Nova_Char.h>
#include <nova/primitive/nova_primitive_Nova_Bool.h>
#include <nova/datastruct/list/nova_datastruct_list_Nova_Array.h>
#include <nova/datastruct/list/nova_datastruct_list_Nova_IntArray.h>
#include <nova/datastruct/list/nova_datastruct_list_Nova_CharArray.h>
#include <nova/datastruct/list/nova_datastruct_list_Nova_DoubleArray.h>
#include <nova/datastruct/list/nova_datastruct_list_Nova_IntRange.h>
#include <nova/thread/nova_thread_Nova_Thread.h>
#include <nova/thread/async/nova_thread_async_Nova_Async.h>
#include <nova/gc/nova_gc_Nova_GC.h>
#include <nova/nova_Nova_Object.h>
#include <nova/nova_Nova_String.h>
#include <nova/nova_Nova_System.h>
#include <nova/math/nova_math_Nova_Math.h>
#include <nova/io/nova_io_Nova_File.h>


typedef struct nova_io_Extension_VTable_OutputStream nova_io_Extension_VTable_OutputStream;
struct nova_io_Extension_VTable_OutputStream
{
	nova_Interface_VTable itable;
	nova_Nova_String* (*nova_Nova_Object_virtual1_Nova_toString)(nova_Nova_Object*, nova_exception_Nova_ExceptionData*);
	char (*nova_operators_Nova_Equals_virtual0_Nova_equals)(nova_Nova_Object*, nova_exception_Nova_ExceptionData*, nova_Nova_Object*);
	long_long (*nova_Nova_Object_virtual_Accessor_Nova_hashCodeLong)(nova_Nova_Object*, nova_exception_Nova_ExceptionData*);
	char (*nova_io_Nova_OutputStream_virtual0_Nova_write)(nova_io_Nova_OutputStream*, nova_exception_Nova_ExceptionData*, nova_Nova_String*);
	char (*nova_io_Nova_OutputStream_virtual1_Nova_write)(nova_io_Nova_OutputStream*, nova_exception_Nova_ExceptionData*, nova_Nova_Object*);
};

extern nova_io_Extension_VTable_OutputStream nova_io_Extension_VTable_OutputStream_val;


CCLASS_CLASS
(
	nova_io_Nova_OutputStream, 
	
	nova_io_Extension_VTable_OutputStream* vtable;
	struct Private* prv;
)

void nova_io_Nova_OutputStream_Nova_init_static(nova_exception_Nova_ExceptionData* exceptionData);
nova_io_Nova_OutputStream* nova_io_Nova_OutputStream_Nova_construct(nova_io_Nova_OutputStream* this, nova_exception_Nova_ExceptionData* exceptionData);
void nova_io_Nova_OutputStream_Nova_destroy(nova_io_Nova_OutputStream** this, nova_exception_Nova_ExceptionData* exceptionData);
char nova_io_Nova_OutputStream_0_Nova_write(nova_io_Nova_OutputStream* this, nova_exception_Nova_ExceptionData* exceptionData, nova_Nova_String* nova_io_Nova_OutputStream_Nova_data);char nova_io_Nova_OutputStream_1_Nova_write(nova_io_Nova_OutputStream* this, nova_exception_Nova_ExceptionData* exceptionData, nova_Nova_Object* nova_io_Nova_OutputStream_Nova_data);void nova_io_Nova_OutputStream_0_Nova_this(nova_io_Nova_OutputStream* this, nova_exception_Nova_ExceptionData* exceptionData);
void nova_io_Nova_OutputStream_Nova_super(nova_io_Nova_OutputStream* this, nova_exception_Nova_ExceptionData* exceptionData);
char nova_io_Nova_OutputStream_virtual0_Nova_write(nova_io_Nova_OutputStream* this, nova_exception_Nova_ExceptionData* exceptionData, nova_Nova_String* nova_io_Nova_OutputStream_Nova_data);
char nova_io_Nova_OutputStream_virtual1_Nova_write(nova_io_Nova_OutputStream* this, nova_exception_Nova_ExceptionData* exceptionData, nova_Nova_Object* nova_io_Nova_OutputStream_Nova_data);

#endif