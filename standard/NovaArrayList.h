#pragma once
#ifndef FILE_ArrayList_NOVA
#define FILE_ArrayList_NOVA

typedef struct ArrayList ArrayList;

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
#include <NovaNumber.h>
#include <NovaInteger.h>
#include <NovaLong.h>
#include <NovaDouble.h>
#include <NovaChar.h>
#include <NovaDivideByZeroException.h>


CCLASS_CLASS
(
	ArrayList, 
	
	int nova_ArrayList_size;
	struct Private* prv;
)

ArrayList* nova_ArrayList_ArrayList(ExceptionData* exceptionData);
void nova_del_ArrayList(ArrayList** this, ExceptionData* exceptionData);
void nova_ArrayList_add(ArrayList* this, ExceptionData* exceptionData, int nova_0_var);
void nova_ArrayList_increaseSize(ArrayList* this, ExceptionData* exceptionData);
int nova_ArrayList_get(ArrayList* this, ExceptionData* exceptionData, int nova_0_index);

#endif