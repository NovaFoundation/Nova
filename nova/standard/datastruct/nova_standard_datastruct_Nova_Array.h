#pragma once
#ifndef FILE_nova_standard_datastruct_Nova_Array_NOVA
#define FILE_nova_standard_datastruct_Nova_Array_NOVA

typedef struct nova_standard_datastruct_Nova_Array nova_standard_datastruct_Nova_Array;

typedef struct nova_standard_exception_Nova_ExceptionData nova_standard_exception_Nova_ExceptionData;
typedef struct nova_standard_Nova_Object nova_standard_Nova_Object;
typedef struct nova_standard_datastruct_Nova_Array nova_standard_datastruct_Nova_Array;

typedef void (*nova_standard_datastruct_Nova_Array_closure1_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_Nova_Array*);
typedef void (*nova_standard_datastruct_Nova_Array_closure2_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_Nova_Array*);
typedef void (*nova_standard_datastruct_Nova_Array_closure3_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_Nova_Array*);
typedef char (*nova_standard_datastruct_Nova_Array_closure4_Nova_filterFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
typedef char (*nova_standard_datastruct_Nova_Array_closure5_Nova_filterFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
typedef char (*nova_standard_datastruct_Nova_Array_closure6_Nova_filterFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);

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
#include <nova/standard/datastruct/nova_standard_datastruct_Nova_List.h>


typedef struct nova_standard_datastruct_Extension_VTable_Array nova_standard_datastruct_Extension_VTable_Array;
struct nova_standard_datastruct_Extension_VTable_Array
{
	nova_Interface_VTable itable;
	long (*nova_standard_Nova_Object_virtual0_Nova_getHashCodeLong)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_Nova_Object_virtual0_Nova_toString)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_Nova_Object_virtual0_Nova_equals)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
	void (*nova_standard_datastruct_Nova_Array_virtual_Nova_forEach)(nova_standard_datastruct_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_Nova_Array_closure3_Nova_func nova_standard_datastruct_Nova_Array_Nova_func, void* nova_standard_datastruct_Nova_Array_ref_Nova_func);
	nova_standard_datastruct_Nova_Array* (*nova_standard_datastruct_Nova_Array_virtual_Nova_filter)(nova_standard_datastruct_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_Nova_Array_closure6_Nova_filterFunc nova_standard_datastruct_Nova_Array_Nova_filterFunc, void* nova_standard_datastruct_Nova_Array_ref_Nova_filterFunc);
	nova_standard_datastruct_Nova_Array* (*nova_standard_datastruct_Nova_Array_virtual_Nova_take)(nova_standard_datastruct_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, int);
	nova_standard_datastruct_Nova_Array* (*nova_standard_datastruct_Nova_Array_virtual_Nova_skip)(nova_standard_datastruct_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, int);
};

extern nova_standard_datastruct_Extension_VTable_Array nova_standard_datastruct_Extension_VTable_Array_val;


CCLASS_CLASS
(
	nova_standard_datastruct_Nova_Array, 
	
	nova_standard_datastruct_Extension_VTable_Array* vtable;
	int nova_standard_datastruct_Nova_Array_Nova_size;
	struct Private* prv;
)

void nova_standard_datastruct_Nova_ArrayNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_datastruct_Nova_Array* nova_standard_datastruct_Nova_Array_2_Nova_construct(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_datastruct_Nova_Array* nova_standard_datastruct_Nova_Array_3_Nova_construct(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_Nova_Array_Nova_size);
nova_standard_datastruct_Nova_Array* nova_standard_datastruct_Nova_Array_4_Nova_construct(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object** nova_standard_datastruct_Nova_Array_Nova_data, int nova_standard_datastruct_Nova_Array_Nova_size);
void nova_standard_datastruct_Nova_Array_Nova_destroy(nova_standard_datastruct_Nova_Array** this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_datastruct_Nova_Array_2_Nova_this(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_datastruct_Nova_Array_3_Nova_this(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_Nova_Array_Nova_size);
void nova_standard_datastruct_Nova_Array_4_Nova_this(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object** nova_standard_datastruct_Nova_Array_Nova_data, int nova_standard_datastruct_Nova_Array_Nova_size);
void nova_standard_datastruct_Nova_Array_0_Nova_add(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object* nova_standard_datastruct_Nova_Array_Nova_element);
void nova_standard_datastruct_Nova_Array_1_Nova_add(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_Nova_Array_Nova_index, nova_standard_Nova_Object* nova_standard_datastruct_Nova_Array_Nova_element);
nova_standard_Nova_Object* nova_standard_datastruct_Nova_Array_Nova_remove(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_Nova_Array_Nova_index);
void nova_standard_datastruct_Nova_Array_Nova_swap(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_Nova_Array_Nova_index1, int nova_standard_datastruct_Nova_Array_Nova_index2);
nova_standard_Nova_Object* nova_standard_datastruct_Nova_Array_Nova_get(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_Nova_Array_Nova_index);
void nova_standard_datastruct_Nova_Array_Nova_set(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_Nova_Array_Nova_index, nova_standard_Nova_Object* nova_standard_datastruct_Nova_Array_Nova_value);
nova_standard_Nova_Object** nova_standard_datastruct_Nova_Array_Nova_toArray(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_datastruct_Nova_Array_Nova_forEach(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_Nova_Array_closure3_Nova_func nova_standard_datastruct_Nova_Array_Nova_func, void* nova_standard_datastruct_Nova_Array_ref_Nova_func);
nova_standard_datastruct_Nova_Array* nova_standard_datastruct_Nova_Array_Nova_filter(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_Nova_Array_closure6_Nova_filterFunc nova_standard_datastruct_Nova_Array_Nova_filterFunc, void* nova_standard_datastruct_Nova_Array_ref_Nova_filterFunc);
nova_standard_datastruct_Nova_Array* nova_standard_datastruct_Nova_Array_Nova_take(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_Nova_Array_Nova_howMany);
nova_standard_datastruct_Nova_Array* nova_standard_datastruct_Nova_Array_Nova_skip(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_Nova_Array_Nova_howMany);
char nova_standard_datastruct_Nova_Array_Accessor_Nova_empty(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_datastruct_Nova_Array_Nova_super(nova_standard_datastruct_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);

#endif
