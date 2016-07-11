#pragma once
#ifndef FILE_nova_standard_database_Nova_DBConnector_NOVA
#define FILE_nova_standard_database_Nova_DBConnector_NOVA

typedef struct nova_standard_database_Nova_DBConnector nova_standard_database_Nova_DBConnector;


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
#include <nova/standard/database/NativeDBConnector.h>
#include <nova/standard/database/nova_standard_database_Nova_ResultSet.h>


typedef struct nova_standard_database_Extension_VTable_DBConnector nova_standard_database_Extension_VTable_DBConnector;
struct nova_standard_database_Extension_VTable_DBConnector
{
	nova_Interface_VTable itable;
	long_long (*nova_standard_Nova_Object_virtual0_Nova_getHashCodeLong)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_Nova_Object_virtual2_Nova_toString)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_operators_Nova_Equals_virtual2_Nova_equals)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
};

extern nova_standard_database_Extension_VTable_DBConnector nova_standard_database_Extension_VTable_DBConnector_val;


CCLASS_CLASS
(
	nova_standard_database_Nova_DBConnector, 
	
	nova_standard_database_Extension_VTable_DBConnector* vtable;
	nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_error;
	struct Private* prv;
)

void nova_standard_database_Nova_DBConnectorNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_database_Nova_DBConnector* nova_standard_database_Nova_DBConnector_Nova_DBConnector(nova_standard_database_Nova_DBConnector* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_database_Nova_DBConnector_Nova_destroy(nova_standard_database_Nova_DBConnector** this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_database_Nova_DBConnector_0_Nova_this(nova_standard_database_Nova_DBConnector* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_database_Nova_DBConnector_0_Nova_connect(nova_standard_database_Nova_DBConnector* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_host, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_user, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_password);
void nova_standard_database_Nova_DBConnector_1_Nova_connect(nova_standard_database_Nova_DBConnector* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_host, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_user, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_password, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_database);
void nova_standard_database_Nova_DBConnector_2_Nova_connect(nova_standard_database_Nova_DBConnector* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_host, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_user, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_password, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_database, int nova_standard_database_Nova_DBConnector_Nova_port, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_unixSocket, int nova_standard_database_Nova_DBConnector_Nova_clientFlag);
void nova_standard_database_Nova_DBConnector_Nova_updateError(nova_standard_database_Nova_DBConnector* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_database_Nova_DBConnector_Nova_changeUser(nova_standard_database_Nova_DBConnector* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_username, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_password, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_database);
nova_standard_database_Nova_ResultSet* nova_standard_database_Nova_DBConnector_Nova_query(nova_standard_database_Nova_DBConnector* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_database_Nova_DBConnector_Nova_query);
void nova_standard_database_Nova_DBConnector_Nova_close(nova_standard_database_Nova_DBConnector* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_database_Nova_DBConnector_Nova_super(nova_standard_database_Nova_DBConnector* this, nova_standard_exception_Nova_ExceptionData* exceptionData);

#endif
