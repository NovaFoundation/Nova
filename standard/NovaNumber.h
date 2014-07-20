#pragma once
#ifndef FILE_Number_NOVA
#define FILE_Number_NOVA

typedef struct Number Number;

#include <Nova.h>
#include <ExceptionHandler.h>
#include <NovaExceptionData.h>
#include <NovaObject.h>
#include <NovaString.h>
#include <NovaSystem.h>
#include <NovaException.h>
#include <NovaMath.h>
#include <NovaConsole.h>
#include <NovaGC.h>
#include "NovaNumber.h"
#include <NovaInteger.h>
#include <NovaLong.h>
#include <NovaDouble.h>
#include <NovaChar.h>
#include <NovaDivideByZeroException.h>

typedef struct nova_VTable_Number
{
	int (*nova_virtual_numDigits)(Number*, ExceptionData*, int);
	String* (*nova_virtual_toAString)(Number*, ExceptionData*, int);
} nova_VTable_Number;

CCLASS_CLASS
(
	Number, 
	
	nova_VTable_Number* vtable;
)

Number* nova_Number_Number(ExceptionData* exceptionData);
void nova_del_Number(Number** this, ExceptionData* exceptionData);
int nova_static_Number_numDigits(Number* this, ExceptionData* exceptionData, int nova_0_number);
String* nova_static_Number_toAString(Number* this, ExceptionData* exceptionData, int nova_0_value);

#endif