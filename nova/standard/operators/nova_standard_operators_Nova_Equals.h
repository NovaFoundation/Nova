#pragma once
#ifndef FILE_nova_standard_operators_Nova_Equals_NOVA
#define FILE_nova_standard_operators_Nova_Equals_NOVA

typedef struct nova_standard_operators_Nova_Equals nova_standard_operators_Nova_Equals;


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


typedef struct nova_standard_operators_Extension_VTable_Equals nova_standard_operators_Extension_VTable_Equals;
struct nova_standard_operators_Extension_VTable_Equals
{
	nova_Interface_VTable itable;
	char (*nova_standard_operators_Nova_Equals_virtual2_Nova_equals)(nova_standard_operators_Nova_Equals*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
};

extern nova_standard_operators_Extension_VTable_Equals nova_standard_operators_Extension_VTable_Equals_val;


CCLASS_CLASS
(
	nova_standard_operators_Nova_Equals, 
	
	nova_standard_operators_Extension_VTable_Equals* vtable;
)

void nova_standard_operators_Nova_EqualsNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
char nova_standard_operators_Nova_Equals_1_Nova_equals(nova_standard_operators_Nova_Equals* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object* nova_standard_operators_Nova_Equals_Nova_another);char nova_standard_operators_Nova_Equals_virtual2_Nova_equals(nova_standard_operators_Nova_Equals* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object* nova_standard_operators_Nova_Equals_Nova_another);

#endif
