#pragma once
#ifndef FILE_nova_standard_datastruct_list_Nova_CharArray_NOVA
#define FILE_nova_standard_datastruct_list_Nova_CharArray_NOVA

typedef struct nova_standard_datastruct_list_Nova_CharArray nova_standard_datastruct_list_Nova_CharArray;

typedef struct nova_standard_exception_Nova_ExceptionData nova_standard_exception_Nova_ExceptionData;
typedef struct nova_standard_datastruct_list_Nova_CharArray nova_standard_datastruct_list_Nova_CharArray;
typedef struct nova_standard_Nova_Object nova_standard_Nova_Object;

typedef nova_standard_Nova_Object* (*nova_standard_datastruct_list_Nova_CharArray_closure1_Nova_mapFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char, int, nova_standard_datastruct_list_Nova_CharArray*);
typedef nova_standard_Nova_Object* (*nova_standard_datastruct_list_Nova_CharArray_closure2_Nova_mapFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char, int, nova_standard_datastruct_list_Nova_CharArray*);
typedef nova_standard_Nova_Object* (*nova_standard_datastruct_list_Nova_CharArray_closure3_Nova_mapFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char, int, nova_standard_datastruct_list_Nova_CharArray*);
typedef void (*nova_standard_datastruct_list_Nova_CharArray_closure4_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, char, int, nova_standard_datastruct_list_Nova_CharArray*);
typedef void (*nova_standard_datastruct_list_Nova_CharArray_closure5_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, char, int, nova_standard_datastruct_list_Nova_CharArray*);
typedef void (*nova_standard_datastruct_list_Nova_CharArray_closure6_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, char, int, nova_standard_datastruct_list_Nova_CharArray*);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure7_Nova_anyFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure8_Nova_anyFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure9_Nova_anyFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure10_Nova_allFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure11_Nova_allFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure12_Nova_allFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure13_Nova_filterFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char, int, nova_standard_datastruct_list_Nova_CharArray*);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure14_Nova_filterFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char, int, nova_standard_datastruct_list_Nova_CharArray*);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure15_Nova_filterFunc)(void*, nova_standard_exception_Nova_ExceptionData*, char, int, nova_standard_datastruct_list_Nova_CharArray*);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure16_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, char);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure17_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, char);
typedef char (*nova_standard_datastruct_list_Nova_CharArray_closure18_Nova_func)(void*, nova_standard_exception_Nova_ExceptionData*, char);

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
#include <nova/standard/datastruct/list/nova_standard_datastruct_list_Nova_CharArrayIterator.h>


typedef struct nova_standard_datastruct_list_Extension_VTable_CharArray nova_standard_datastruct_list_Extension_VTable_CharArray;
struct nova_standard_datastruct_list_Extension_VTable_CharArray
{
	nova_Interface_VTable itable;
	long_long (*nova_standard_Nova_Object_virtual1_Nova_getHashCodeLong)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_Nova_Object_virtual1_Nova_toString)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_operators_Nova_Equals_virtual0_Nova_equals)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
	char (*nova_standard_datastruct_list_Nova_Array_virtual1_Nova_get)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*, int);
	nova_standard_datastruct_list_Nova_Array* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_toArray)(nova_standard_datastruct_list_Nova_Array*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_datastruct_list_Nova_Array* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_map)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_CharArray_closure3_Nova_mapFunc nova_standard_datastruct_list_Nova_CharArray_Nova_mapFunc, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_mapFunc);
	void (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_forEach)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_CharArray_closure6_Nova_func nova_standard_datastruct_list_Nova_CharArray_Nova_func, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_func);
	char (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_any)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_CharArray_closure9_Nova_anyFunc nova_standard_datastruct_list_Nova_CharArray_Nova_anyFunc, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_anyFunc);
	char (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_all)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_CharArray_closure12_Nova_allFunc nova_standard_datastruct_list_Nova_CharArray_Nova_allFunc, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_allFunc);
	nova_standard_datastruct_list_Nova_CharArray* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_filter)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_CharArray_closure15_Nova_filterFunc nova_standard_datastruct_list_Nova_CharArray_Nova_filterFunc, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_filterFunc);
	nova_standard_datastruct_list_Nova_CharArray* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_take)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*, int);
	nova_standard_datastruct_list_Nova_CharArray* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_skip)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*, int);
	char (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_firstWhere)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*, nova_standard_datastruct_list_Nova_CharArray_closure18_Nova_func nova_standard_datastruct_list_Nova_CharArray_Nova_func, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_func);
	nova_standard_datastruct_list_Nova_CharArray* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_reverse)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_datastruct_list_Nova_List_virtual0_Nova_join)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_String*);
	nova_standard_datastruct_list_Nova_CharArrayIterator* (*nova_standard_datastruct_list_Nova_Iterable_virtual_Accessor1_Nova_iterator)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_datastruct_list_Nova_Array_virtual_Accessor_Nova_first)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_datastruct_list_Nova_Array_virtual_Accessor_Nova_last)(nova_standard_datastruct_list_Nova_CharArray*, nova_standard_exception_Nova_ExceptionData*);
};

extern nova_standard_datastruct_list_Extension_VTable_CharArray nova_standard_datastruct_list_Extension_VTable_CharArray_val;


CCLASS_CLASS
(
	nova_standard_datastruct_list_Nova_CharArray, 
	
	nova_standard_datastruct_list_Extension_VTable_CharArray* vtable;
	int nova_standard_datastruct_list_Nova_Array_Nova_capacity;
	int nova_standard_datastruct_list_Nova_Array_Nova_count;
	int nova_standard_datastruct_list_Nova_Array_Nova_position;
	nova_standard_Nova_Object** nova_standard_datastruct_list_Nova_Array_Nova_data;
)

void nova_standard_datastruct_list_Nova_CharArrayNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_datastruct_list_Nova_CharArray* nova_standard_datastruct_list_Nova_CharArray_0_Nova_CharArray(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_datastruct_list_Nova_CharArray* nova_standard_datastruct_list_Nova_CharArray_1_Nova_CharArray(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_CharArray_Nova_count);
nova_standard_datastruct_list_Nova_CharArray* nova_standard_datastruct_list_Nova_CharArray_2_Nova_CharArray(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, char* nova_standard_datastruct_list_Nova_CharArray_Nova_data, int nova_standard_datastruct_list_Nova_CharArray_Nova_count);
void nova_standard_datastruct_list_Nova_CharArray_Nova_destroy(nova_standard_datastruct_list_Nova_CharArray** this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_datastruct_list_Nova_CharArray_0_Nova_this(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_datastruct_list_Nova_CharArray_1_Nova_this(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_CharArray_Nova_count);
char nova_standard_datastruct_list_Nova_CharArray_Nova_get(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_CharArray_Nova_index);
void nova_standard_datastruct_list_Nova_CharArray_2_Nova_this(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, char* nova_standard_datastruct_list_Nova_CharArray_Nova_data, int nova_standard_datastruct_list_Nova_CharArray_Nova_count);
nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_CharArray_Nova_map(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_CharArray_closure3_Nova_mapFunc nova_standard_datastruct_list_Nova_CharArray_Nova_mapFunc, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_mapFunc);
void nova_standard_datastruct_list_Nova_CharArray_Nova_forEach(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_CharArray_closure6_Nova_func nova_standard_datastruct_list_Nova_CharArray_Nova_func, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_func);
char nova_standard_datastruct_list_Nova_CharArray_Nova_any(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_CharArray_closure9_Nova_anyFunc nova_standard_datastruct_list_Nova_CharArray_Nova_anyFunc, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_anyFunc);
char nova_standard_datastruct_list_Nova_CharArray_Nova_all(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_CharArray_closure12_Nova_allFunc nova_standard_datastruct_list_Nova_CharArray_Nova_allFunc, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_allFunc);
nova_standard_datastruct_list_Nova_CharArray* nova_standard_datastruct_list_Nova_CharArray_Nova_filter(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_CharArray_closure15_Nova_filterFunc nova_standard_datastruct_list_Nova_CharArray_Nova_filterFunc, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_filterFunc);
nova_standard_datastruct_list_Nova_CharArray* nova_standard_datastruct_list_Nova_CharArray_Nova_take(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_CharArray_Nova_howMany);
nova_standard_datastruct_list_Nova_CharArray* nova_standard_datastruct_list_Nova_CharArray_Nova_skip(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_CharArray_Nova_howMany);
char nova_standard_datastruct_list_Nova_CharArray_Nova_firstWhere(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_CharArray_closure18_Nova_func nova_standard_datastruct_list_Nova_CharArray_Nova_func, void* nova_standard_datastruct_list_Nova_CharArray_ref_Nova_func);
nova_standard_datastruct_list_Nova_CharArray* nova_standard_datastruct_list_Nova_CharArray_Nova_reverse(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_String* nova_standard_datastruct_list_Nova_CharArray_Nova_join(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_datastruct_list_Nova_CharArray_Nova_delimiter);
nova_standard_datastruct_list_Nova_CharArrayIterator* nova_standard_datastruct_list_Nova_CharArray_Accessor_Nova_iterator(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
char nova_standard_datastruct_list_Nova_CharArray_Accessor_Nova_first(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
char nova_standard_datastruct_list_Nova_CharArray_Accessor_Nova_last(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_datastruct_list_Nova_CharArray_0_Nova_super(nova_standard_datastruct_list_Nova_CharArray* this, nova_standard_exception_Nova_ExceptionData* exceptionData);

#endif
