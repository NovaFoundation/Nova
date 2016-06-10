#pragma once
#ifndef FILE_nova_standard_time_Nova_Timer_NOVA
#define FILE_nova_standard_time_Nova_Timer_NOVA

typedef struct nova_standard_time_Nova_Timer nova_standard_time_Nova_Timer;


#include <Nova.h>
#include <ExceptionHandler.h>
#include <InterfaceVTable.h>
#include <nova/standard/exception/nova_standard_exception_Nova_ExceptionData.h>
#include <nova/standard/exception/nova_standard_exception_Nova_Exception.h>
#include <nova/standard/exception/nova_standard_exception_Nova_DivideByZeroException.h>
#include <nova/standard/io/nova_standard_io_Nova_Console.h>
#include <nova/standard/primitive/number/nova_standard_primitive_number_Nova_Number.h>
#include <nova/standard/primitive/number/nova_standard_primitive_number_Nova_Byte.h>
#include <nova/standard/primitive/number/nova_standard_primitive_number_Nova_Short.h>
#include <nova/standard/primitive/number/nova_standard_primitive_number_Nova_Int.h>
#include <nova/standard/primitive/number/nova_standard_primitive_number_Nova_Long.h>
#include <nova/standard/primitive/number/nova_standard_primitive_number_Nova_Float.h>
#include <nova/standard/primitive/number/nova_standard_primitive_number_Nova_Double.h>
#include <nova/standard/primitive/nova_standard_primitive_Nova_Null.h>
#include <nova/standard/primitive/number/nova_standard_primitive_number_Nova_Char.h>
#include <nova/standard/primitive/nova_standard_primitive_Nova_Bool.h>
#include <nova/standard/datastruct/list/nova_standard_datastruct_list_Nova_Array.h>
#include <nova/standard/gc/nova_standard_gc_Nova_GC.h>
#include <nova/standard/nova_standard_Nova_Object.h>
#include <nova/standard/nova_standard_Nova_String.h>
#include <nova/standard/nova_standard_Nova_System.h>
#include <nova/standard/math/nova_standard_math_Nova_Math.h>
#include <nova/standard/time/nova_standard_time_Nova_Time.h>


typedef struct nova_standard_time_Extension_VTable_Timer nova_standard_time_Extension_VTable_Timer;
struct nova_standard_time_Extension_VTable_Timer
{
	nova_Interface_VTable itable;
	long (*nova_standard_Nova_Object_virtual0_Nova_getHashCodeLong)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_Nova_Object_virtual0_Nova_toString)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_Nova_Object_virtual0_Nova_equals)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
};

extern nova_standard_time_Extension_VTable_Timer nova_standard_time_Extension_VTable_Timer_val;


CCLASS_CLASS
(
	nova_standard_time_Nova_Timer, 
	
	nova_standard_time_Extension_VTable_Timer* vtable;
	long nova_standard_time_Nova_Timer_Nova_startTime;
	long nova_standard_time_Nova_Timer_Nova_endTime;
)

void nova_standard_time_Nova_TimerNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_time_Nova_Timer* nova_standard_time_Nova_Timer_2_Nova_construct(nova_standard_time_Nova_Timer* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_time_Nova_Timer_Nova_destroy(nova_standard_time_Nova_Timer** this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_time_Nova_Timer_2_Nova_this(nova_standard_time_Nova_Timer* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_time_Nova_Timer* nova_standard_time_Nova_Timer_Nova_start(nova_standard_time_Nova_Timer* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_time_Nova_Timer* nova_standard_time_Nova_Timer_Nova_stop(nova_standard_time_Nova_Timer* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
long nova_standard_time_Nova_Timer_Accessor_Nova_duration(nova_standard_time_Nova_Timer* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_time_Nova_Timer_Nova_super(nova_standard_time_Nova_Timer* this, nova_standard_exception_Nova_ExceptionData* exceptionData);

#endif