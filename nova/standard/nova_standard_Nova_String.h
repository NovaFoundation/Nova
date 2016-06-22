#pragma once
#ifndef FILE_nova_standard_Nova_String_NOVA
#define FILE_nova_standard_Nova_String_NOVA

typedef struct nova_standard_Nova_String nova_standard_Nova_String;

typedef struct nova_standard_exception_Nova_ExceptionData nova_standard_exception_Nova_ExceptionData;

typedef char (*nova_standard_Nova_String_closure1_Nova_transform)(void*, nova_standard_exception_Nova_ExceptionData*, char);
typedef char (*nova_standard_Nova_String_closure2_Nova_transform)(void*, nova_standard_exception_Nova_ExceptionData*, char);
typedef char (*nova_standard_Nova_String_closure3_Nova_transform)(void*, nova_standard_exception_Nova_ExceptionData*, char);

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
#include <nova/standard/datastruct/nova_standard_datastruct_Nova_Comparable.h>


typedef struct nova_standard_Extension_VTable_String nova_standard_Extension_VTable_String;
struct nova_standard_Extension_VTable_String
{
	nova_Interface_VTable itable;
	long_long (*nova_standard_Nova_Object_virtual1_Nova_getHashCodeLong)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_Nova_Object_virtual1_Nova_toString)(nova_standard_Nova_String*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_operators_Nova_Equals_virtual0_Nova_equals)(nova_standard_Nova_String*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_String*);
	nova_standard_Nova_String* (*nova_standard_Nova_String_virtual0_Nova_concat)(nova_standard_Nova_String*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_String*);
	int (*nova_standard_datastruct_Nova_Comparable_virtual0_Nova_compareTo)(nova_standard_Nova_String*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_String*);
};

extern nova_standard_Extension_VTable_String nova_standard_Extension_VTable_String_val;


CCLASS_CLASS
(
	nova_standard_Nova_String, 
	
	nova_standard_Extension_VTable_String* vtable;
	int nova_standard_Nova_String_Nova_size;
	char* nova_standard_Nova_String_Nova_chars;
)

void nova_standard_Nova_StringNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_String* nova_standard_Nova_String_1_Nova_construct(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, char nova_standard_Nova_String_Nova_c);
nova_standard_Nova_String* nova_standard_Nova_String_2_Nova_construct(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, char* nova_standard_Nova_String_Nova_chars);
void nova_standard_Nova_String_Nova_destroy(nova_standard_Nova_String** this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_Nova_String_1_Nova_this(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, char nova_standard_Nova_String_Nova_c);
void nova_standard_Nova_String_2_Nova_this(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, char* nova_standard_Nova_String_Nova_chars);
nova_standard_Nova_String* nova_standard_Nova_String_0_Nova_concat(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_Nova_String_Nova_str);
char nova_standard_Nova_String_Nova_equals(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_Nova_String_Nova_other);
int nova_standard_Nova_String_1_Nova_indexOf(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_Nova_String_Nova_search);
int nova_standard_Nova_String_2_Nova_indexOf(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_Nova_String_Nova_search, int nova_standard_Nova_String_Nova_start);
int nova_standard_Nova_String_Nova_lastIndexOf(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_Nova_String_Nova_search);
nova_standard_Nova_String* nova_standard_Nova_String_0_Nova_substring(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_Nova_String_Nova_start, int nova_standard_Nova_String_Nova_end);
nova_standard_Nova_String* nova_standard_Nova_String_1_Nova_substring(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_Nova_String_Nova_start);
char nova_standard_Nova_String_Nova_lastChar(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
char nova_standard_Nova_String_Nova_charAt(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_Nova_String_Nova_index);
nova_standard_Nova_String* nova_standard_Nova_String_Nova_trim(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_String* nova_standard_Nova_String_Nova_toLowerCase(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_String* nova_standard_Nova_String_Nova_toUpperCase(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_String* nova_standard_Nova_String_Nova_transform(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String_closure3_Nova_transform nova_standard_Nova_String_Nova_transform, void* nova_standard_Nova_String_ref_Nova_transform);
nova_standard_Nova_String* nova_standard_Nova_String_0_Nova_getDataBetween(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_Nova_String_Nova_before, nova_standard_Nova_String* nova_standard_Nova_String_Nova_after);
nova_standard_Nova_String* nova_standard_Nova_String_1_Nova_getDataBetween(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_Nova_String_Nova_before, nova_standard_Nova_String* nova_standard_Nova_String_Nova_after, int nova_standard_Nova_String_Nova_start);
int nova_standard_Nova_String_Nova_compareTo(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_Nova_String_Nova_other);
nova_standard_Nova_String* nova_standard_Nova_String_0_Nova_toString(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_Nova_String_Nova_super(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_Nova_String* nova_standard_Nova_String_virtual0_Nova_concat(nova_standard_Nova_String* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_Nova_String_Nova_str);

#endif
