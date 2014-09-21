#pragma once
#ifndef FILE_UnstableException_NOVA
#define FILE_UnstableException_NOVA

typedef struct UnstableException UnstableException;

#include <Nova.h>
#include <ExceptionHandler.h>
#include <NovaExceptionData.h>
#include <NovaNull.h>
#include <NovaObject.h>
#include <NovaString.h>
#include <NovaSystem.h>
#include <NovaException.h>
#include <NovaMath.h>
#include <NovaConsole.h>
#include <NovaGC.h>
#include <NovaNumber.h>
#include <NovaByte.h>
#include <NovaShort.h>
#include <NovaInt.h>
#include <NovaLong.h>
#include <NovaFloat.h>
#include <NovaDouble.h>
#include <NovaChar.h>
#include <NovaDivideByZeroException.h>

typedef struct nova_VTable_UnstableException
{
	String* (*nova_virtual_0_toString)(Object*, ExceptionData*);
	char (*nova_virtual_0_equals)(Object*, ExceptionData*, Object*);
} nova_VTable_UnstableException;

CCLASS_CLASS
(
	UnstableException, 
	
	nova_VTable_UnstableException* vtable;
)

UnstableException* nova_0_UnstableException_construct(UnstableException* this, ExceptionData* exceptionData);
void nova_del_UnstableException(UnstableException** this, ExceptionData* exceptionData);
void nova_UnstableException_this(UnstableException* this, ExceptionData* exceptionData);
void nova_UnstableException_super(UnstableException* this, ExceptionData* exceptionData);

#endif