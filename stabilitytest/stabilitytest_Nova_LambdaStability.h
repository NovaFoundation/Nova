#pragma once
#ifndef FILE_stabilitytest_Nova_LambdaStability_NOVA
#define FILE_stabilitytest_Nova_LambdaStability_NOVA

typedef struct stabilitytest_Nova_LambdaStability stabilitytest_Nova_LambdaStability;


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
#include <nova/standard/datastruct/list/nova_standard_datastruct_list_Nova_LinkedList.h>
#include <stabilitytest/stabilitytest_Nova_StabilityTest.h>
#include <stabilitytest/stabilitytest_Nova_StabilityTestCase.h>


typedef struct stabilitytest_Extension_VTable_LambdaStability stabilitytest_Extension_VTable_LambdaStability;
struct stabilitytest_Extension_VTable_LambdaStability
{
	nova_Interface_VTable itable;
	long (*nova_standard_Nova_Object_virtual1_Nova_getHashCodeLong)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_Nova_Object_virtual1_Nova_toString)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_operators_Nova_Equals_virtual0_Nova_equals)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
	void (*stabilitytest_Nova_StabilityTestCase_virtual0_Nova_test)(stabilitytest_Nova_LambdaStability*, nova_standard_exception_Nova_ExceptionData*);
};

extern stabilitytest_Extension_VTable_LambdaStability stabilitytest_Extension_VTable_LambdaStability_val;


CCLASS_CLASS
(
	stabilitytest_Nova_LambdaStability, 
	
	stabilitytest_Extension_VTable_LambdaStability* vtable;
	stabilitytest_Nova_StabilityTest* stabilitytest_Nova_StabilityTestCase_Nova_program;
)

void stabilitytest_Nova_LambdaStabilityNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
stabilitytest_Nova_LambdaStability* stabilitytest_Nova_LambdaStability_0_Nova_construct(stabilitytest_Nova_LambdaStability* this, nova_standard_exception_Nova_ExceptionData* exceptionData, stabilitytest_Nova_StabilityTest* stabilitytest_Nova_LambdaStability_Nova_program);
void stabilitytest_Nova_LambdaStability_Nova_destroy(stabilitytest_Nova_LambdaStability** this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void stabilitytest_Nova_LambdaStability_0_Nova_this(stabilitytest_Nova_LambdaStability* this, nova_standard_exception_Nova_ExceptionData* exceptionData, stabilitytest_Nova_StabilityTest* stabilitytest_Nova_LambdaStability_Nova_program);
void stabilitytest_Nova_LambdaStability_0_Nova_test(stabilitytest_Nova_LambdaStability* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void stabilitytest_Nova_LambdaStability_0_Nova_super(stabilitytest_Nova_LambdaStability* this, nova_standard_exception_Nova_ExceptionData* exceptionData);

#endif