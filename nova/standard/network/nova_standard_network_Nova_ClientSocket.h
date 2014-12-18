#pragma once
#ifndef FILE_nova_standard_network_Nova_ClientSocket_NOVA
#define FILE_nova_standard_network_Nova_ClientSocket_NOVA

typedef struct nova_standard_network_Nova_ClientSocket nova_standard_network_Nova_ClientSocket;

#include <Nova.h>
#include <ExceptionHandler.h>
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
#include <nova/standard/network/NativeClientSocket.h>
#include <nova/standard/network/NativeSocket.h>
#include <nova/standard/network/nova_standard_network_Nova_Socket.h>
#include <nova/standard/network/nova_standard_network_Nova_ConnectionSocket.h>

typedef struct nova_VTable_nova_standard_network_Nova_ClientSocket
{
	long (*nova_standard_Nova_Object_virtual1_Nova_getHashCodeLong)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	nova_standard_Nova_String* (*nova_standard_Nova_Object_virtual0_Nova_toString)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*);
	char (*nova_standard_Nova_Object_virtual0_Nova_equals)(nova_standard_Nova_Object*, nova_standard_exception_Nova_ExceptionData*, nova_standard_Nova_Object*);
} nova_VTable_nova_standard_network_Nova_ClientSocket;

extern nova_VTable_nova_standard_network_Nova_ClientSocket nova_VTable_nova_standard_network_Nova_ClientSocket_val;

CCLASS_CLASS
(
	nova_standard_network_Nova_ClientSocket, 
	
	nova_VTable_nova_standard_network_Nova_ClientSocket* vtable;
	nova_standard_Nova_String* nova_standard_network_Nova_Socket_Nova_ip;
	int nova_standard_network_Nova_Socket_Nova_port;
	nova_standard_network_Nova_ConnectionSocket* nova_standard_network_Nova_ClientSocket_Nova_connection;
	struct Private* prv;
)

void nova_standard_network_Nova_ClientSocketNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData);
nova_standard_network_Nova_ClientSocket* nova_standard_network_Nova_ClientSocket_2_Nova_construct(nova_standard_network_Nova_ClientSocket* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_network_Nova_ClientSocket_Nova_destroy(nova_standard_network_Nova_ClientSocket** this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_network_Nova_ClientSocket_2_Nova_this(nova_standard_network_Nova_ClientSocket* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
char nova_standard_network_Nova_ClientSocket_Nova_connect(nova_standard_network_Nova_ClientSocket* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* l0_Nova_ipAddress, int l0_Nova_port);
char nova_standard_network_Nova_ClientSocket_Nova_close(nova_standard_network_Nova_ClientSocket* this, nova_standard_exception_Nova_ExceptionData* exceptionData);
void nova_standard_network_Nova_ClientSocket_2_Nova_super(nova_standard_network_Nova_ClientSocket* this, nova_standard_exception_Nova_ExceptionData* exceptionData);

#endif
