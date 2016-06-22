#pragma once
#ifndef FILE_nova_standard_math_Nova_Polynomial_NOVA
#define FILE_nova_standard_math_Nova_Polynomial_NOVA

typedef struct nova_standard_math_Nova_Polynomial nova_standard_math_Nova_Polynomial;


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


typedef struct nova_standard_math_Extension_VTable_Polynomial nova_standard_math_Extension_VTable_Polynomial;
struct nova_standard_math_Extension_VTable_Polynomial
{
	nova_Interface_VTable itable;
	long_long (*nova_standard_Nova_Object_virtual1_Nova_getHashCodeLong)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_Nova_Object_virtual1_Nova_toString)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_operators_Nova_Equals_virtual0_Nova_equals)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
};

extern nova_standard_math_Extension_VTable_Polynomial nova_standard_math_Extension_VTable_Polynomial_val;


CCLASS_CLASS
(
	nova_standard_math_Nova_Polynomial, 
	
	nova_standard_math_Extension_VTable_Polynomial* vtable;
	double* nova_standard_math_Nova_Polynomial_Nova_coefficients;
	double* nova_standard_math_Nova_Polynomial_Nova_degrees;
	char* nova_standard_math_Nova_Polynomial_Nova_signs;
)

void nova_standard_math_Nova_PolynomialNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_math_Nova_Polynomial* nova_standard_math_Nova_Polynomial_Nova_construct(nova_standard_math_Nova_Polynomial* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_math_Nova_Polynomial_Nova_polynomial);
void nova_standard_math_Nova_Polynomial_Nova_destroy(nova_standard_math_Nova_Polynomial** this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_math_Nova_Polynomial_Nova_this(nova_standard_math_Nova_Polynomial* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_math_Nova_Polynomial_Nova_polynomial);
void nova_standard_math_Nova_Polynomial_Nova_super(nova_standard_math_Nova_Polynomial* this, nova_standard_exception_Nova_ExceptionData* exceptionData);

#endif
