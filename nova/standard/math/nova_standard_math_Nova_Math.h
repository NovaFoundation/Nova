#pragma once
#ifndef FILE_nova_standard_math_Nova_Math_NOVA
#define FILE_nova_standard_math_Nova_Math_NOVA

typedef struct nova_standard_math_Nova_Math nova_standard_math_Nova_Math;


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
#include <nova/standard/datastruct/nova_standard_datastruct_Nova_Array.h>
#include <nova/standard/gc/nova_standard_gc_Nova_GC.h>
#include <nova/standard/nova_standard_Nova_Object.h>
#include <nova/standard/nova_standard_Nova_String.h>
#include <nova/standard/nova_standard_Nova_System.h>
#include <nova/standard/math/nova_standard_math_Nova_Math.h>
#include <math.h>


typedef struct nova_standard_math_Extension_VTable_Math nova_standard_math_Extension_VTable_Math;
struct nova_standard_math_Extension_VTable_Math
{
	nova_Interface_VTable itable;
	long (*nova_standard_Nova_Object_virtual0_Nova_getHashCodeLong)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_Nova_Object_virtual0_Nova_toString)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_Nova_Object_virtual0_Nova_equals)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
};

extern nova_standard_math_Extension_VTable_Math nova_standard_math_Extension_VTable_Math_val;


CCLASS_CLASS
(
	nova_standard_math_Nova_Math, 
	
	nova_standard_math_Extension_VTable_Math* vtable;
)
extern double nova_standard_math_Nova_Math_Nova_PI;

void nova_standard_math_Nova_MathNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_math_Nova_Math* nova_standard_math_Nova_Math_2_Nova_construct(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_math_Nova_Math_Nova_destroy(nova_standard_math_Nova_Math** this, nova_standard_exception_Nova_ExceptionData* exceptionData);
int nova_standard_math_Nova_Math_Nova_random(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, long l0_Nova_range);
double nova_standard_math_Nova_Math_Nova_abs(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
double nova_standard_math_Nova_Math_Nova_sqrt(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
double nova_standard_math_Nova_Math_Nova_pow(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_base, double l0_Nova_power);
double nova_standard_math_Nova_Math_Nova_sin(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
double nova_standard_math_Nova_Math_Nova_cos(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
double nova_standard_math_Nova_Math_Nova_tan(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
double nova_standard_math_Nova_Math_Nova_asin(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
double nova_standard_math_Nova_Math_Nova_acos(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
double nova_standard_math_Nova_Math_Nova_atan(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
long nova_standard_math_Nova_Math_Nova_round(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
long nova_standard_math_Nova_Math_Nova_floor(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
long nova_standard_math_Nova_Math_Nova_ceil(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData, double l0_Nova_number);
void nova_standard_math_Nova_Math_2_Nova_this(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_math_Nova_Math_Nova_super(nova_standard_math_Nova_Math* this, nova_standard_exception_Nova_ExceptionData* exceptionData);

#endif
