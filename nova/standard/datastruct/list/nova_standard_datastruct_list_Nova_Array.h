#pragma once
#ifndef FILE_nova_standard_datastruct_list_Nova_Array_NOVA
#define FILE_nova_standard_datastruct_list_Nova_Array_NOVA

typedef struct nova_standard_datastruct_list_Nova_Array nova_standard_datastruct_list_Nova_Array;

typedef struct nova_standard_exception_Nova_ExceptionData nova_standard_exception_Nova_ExceptionData;
typedef struct nova_standard_Nova_Object nova_standard_Nova_Object;
typedef struct nova_standard_datastruct_list_Nova_Array nova_standard_datastruct_list_Nova_Array;

typedef nova_standard_Nova_Object* (*nova_standard_datastruct_list_Nova_Array_closure1_Nova_mapFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_list_Nova_Array*);
typedef nova_standard_Nova_Object* (*nova_standard_datastruct_list_Nova_Array_closure2_Nova_mapFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_list_Nova_Array*);
typedef nova_standard_Nova_Object* (*nova_standard_datastruct_list_Nova_Array_closure3_Nova_mapFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_list_Nova_Array*);
typedef void (*nova_standard_datastruct_list_Nova_Array_closure4_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_list_Nova_Array*);
typedef void (*nova_standard_datastruct_list_Nova_Array_closure5_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_list_Nova_Array*);
typedef void (*nova_standard_datastruct_list_Nova_Array_closure6_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_list_Nova_Array*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure7_Nova_anyFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure8_Nova_anyFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure9_Nova_anyFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure10_Nova_allFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure11_Nova_allFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure12_Nova_allFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure13_Nova_filterFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_list_Nova_Array*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure14_Nova_filterFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_list_Nova_Array*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure15_Nova_filterFunc)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*, int, nova_standard_datastruct_list_Nova_Array*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure16_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure17_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
typedef char (*nova_standard_datastruct_list_Nova_Array_closure18_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);

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
#include <nova/standard/datastruct/list/nova_standard_datastruct_list_Nova_IntArray.h>
#include <nova/standard/datastruct/list/nova_standard_datastruct_list_Nova_CharArray.h>
#include <nova/standard/datastruct/list/nova_standard_datastruct_list_Nova_DoubleArray.h>
#include <nova/standard/datastruct/list/nova_standard_datastruct_list_Nova_IntRange.h>
#include <nova/standard/gc/nova_standard_gc_Nova_GC.h>
#include <nova/standard/nova_standard_Nova_Object.h>
#include <nova/standard/nova_standard_Nova_String.h>
#include <nova/standard/nova_standard_Nova_System.h>
#include <nova/standard/math/nova_standard_math_Nova_Math.h>
#include <nova/standard/datastruct/list/nova_standard_datastruct_list_Nova_ArrayIterator.h>
#include <nova/standard/datastruct/list/nova_standard_datastruct_list_Nova_List.h>


typedef struct nova_standard_datastruct_list_Extension_VTable_Array nova_standard_datastruct_list_Extension_VTable_Array;
struct nova_standard_datastruct_list_Extension_VTable_Array
{
	nova_Interface_VTable itable;
	long_long (*nova_standard_Nova_Object_virtual1_Nova_getHashCodeLong)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_Nova_Object_virtual1_Nova_toString)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_operators_Nova_Equals_virtual0_Nova_equals)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
	nova_standard_Nova_Object* (*nova_standard_datastruct_list_Nova_Array_virtual1_Nova_get)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, int);
	nova_standard_datastruct_list_Nova_Array* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_toArray)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_datastruct_list_Nova_Array* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_map)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_Array_closure3_Nova_mapFunc nova_standard_datastruct_list_Nova_Array_Nova_mapFunc, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_mapFunc);
	void (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_forEach)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_Array_closure6_Nova_func nova_standard_datastruct_list_Nova_Array_Nova_func, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_func);
	char (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_any)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_Array_closure9_Nova_anyFunc nova_standard_datastruct_list_Nova_Array_Nova_anyFunc, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_anyFunc);
	char (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_all)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_Array_closure12_Nova_allFunc nova_standard_datastruct_list_Nova_Array_Nova_allFunc, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_allFunc);
	nova_standard_datastruct_list_Nova_Array* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_filter)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_Array_closure15_Nova_filterFunc nova_standard_datastruct_list_Nova_Array_Nova_filterFunc, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_filterFunc);
	nova_standard_datastruct_list_Nova_Array* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_take)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, int);
	nova_standard_datastruct_list_Nova_Array* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_skip)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, int);
	nova_standard_Nova_Object* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_firstWhere)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_Array_closure18_Nova_func nova_standard_datastruct_list_Nova_Array_Nova_func, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_func);
	nova_standard_datastruct_list_Nova_Array* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_reverse)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_join)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_String*);
	nova_standard_datastruct_list_Nova_ArrayIterator* (*nova_standard_datastruct_list_Nova_Iterable_virtual_Accessor1_Nova_iterator)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_Object* (*nova_standard_datastruct_list_Nova_Array_virtual_Accessor_Nova_first)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_Object* (*nova_standard_datastruct_list_Nova_Array_virtual_Accessor_Nova_last)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*);
};

extern nova_standard_datastruct_list_Extension_VTable_Array nova_standard_datastruct_list_Extension_VTable_Array_val;


CCLASS_CLASS
(
	nova_standard_datastruct_list_Nova_Array, 
	
	nova_standard_datastruct_list_Extension_VTable_Array* vtable;
	int nova_standard_datastruct_list_Nova_Array_Nova_capacity;
	int nova_standard_datastruct_list_Nova_Array_Nova_count;
	int nova_standard_datastruct_list_Nova_Array_Nova_position;
	nova_standard_Nova_Object** nova_standard_datastruct_list_Nova_Array_Nova_data;
)

void nova_standard_datastruct_list_Nova_ArrayNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_0_Nova_Array(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_1_Nova_Array(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_Array_Nova_count);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_2_Nova_Array(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object** nova_standard_datastruct_list_Nova_Array_Nova_data, int nova_standard_datastruct_list_Nova_Array_Nova_count);
void nova_standard_datastruct_list_Nova_Array_Nova_destroy(nova_standard_datastruct_list_Nova_Array** this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_datastruct_list_Nova_Array_0_Nova_this(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_datastruct_list_Nova_Array_1_Nova_this(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_Array_Nova_count);
void nova_standard_datastruct_list_Nova_Array_2_Nova_this(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object** nova_standard_datastruct_list_Nova_Array_Nova_data, int nova_standard_datastruct_list_Nova_Array_Nova_count);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_Nova_addAll(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_Nova_data);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_0_Nova_add(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_Nova_element);
void nova_standard_datastruct_list_Nova_Array_1_Nova_add(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_Array_Nova_index, nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_Nova_element);
nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_Nova_remove(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_Array_Nova_index);
void nova_standard_datastruct_list_Nova_Array_Nova_swap(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_Array_Nova_index1, int nova_standard_datastruct_list_Nova_Array_Nova_index2);
nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_0_Nova_get(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_Array_Nova_index);
void nova_standard_datastruct_list_Nova_Array_Nova_set(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_Array_Nova_index, nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_Nova_value);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_Nova_toArray(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_0_Nova_map(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_Array_closure3_Nova_mapFunc nova_standard_datastruct_list_Nova_Array_Nova_mapFunc, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_mapFunc);
void nova_standard_datastruct_list_Nova_Array_0_Nova_forEach(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_Array_closure6_Nova_func nova_standard_datastruct_list_Nova_Array_Nova_func, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_func);
char nova_standard_datastruct_list_Nova_Array_0_Nova_any(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_Array_closure9_Nova_anyFunc nova_standard_datastruct_list_Nova_Array_Nova_anyFunc, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_anyFunc);
char nova_standard_datastruct_list_Nova_Array_0_Nova_all(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_Array_closure12_Nova_allFunc nova_standard_datastruct_list_Nova_Array_Nova_allFunc, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_allFunc);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_0_Nova_filter(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_Array_closure15_Nova_filterFunc nova_standard_datastruct_list_Nova_Array_Nova_filterFunc, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_filterFunc);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_0_Nova_take(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_Array_Nova_howMany);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_0_Nova_skip(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_Array_Nova_howMany);
nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_0_Nova_firstWhere(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_Array_closure18_Nova_func nova_standard_datastruct_list_Nova_Array_Nova_func, void* nova_standard_datastruct_list_Nova_Array_ref_Nova_func);
long_long nova_standard_datastruct_list_Nova_Array_Nova_sumSize(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_Array_0_Nova_reverse(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_String* nova_standard_datastruct_list_Nova_Array_0_Nova_join(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_datastruct_list_Nova_Array_Nova_delimiter);
nova_standard_Nova_String* nova_standard_datastruct_list_Nova_Array_0_Nova_toString(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
char nova_standard_datastruct_list_Nova_Array_Accessor_Nova_empty(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_datastruct_list_Nova_ArrayIterator* nova_standard_datastruct_list_Nova_Array_Accessor_Nova_iterator(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_Accessor_Nova_first(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_Accessor_Nova_last(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_datastruct_list_Nova_Array_Nova_super(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_virtual1_Nova_get(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_Array_Nova_index);
nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_virtual_Accessor_Nova_first(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_Array_virtual_Accessor_Nova_last(nova_standard_datastruct_list_Nova_Array* this, nova_standard_exception_Nova_ExceptionData* exceptionData);

#endif
