#pragma once
#ifndef FILE_ThreadStability_NOVA
#define FILE_ThreadStability_NOVA

typedef struct ThreadStability ThreadStability;

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
#include <NovaThread.h>
#include <NovaStabilityExceptionHandler.h>
#include <NovaThreadImplementation.h>
#include <NovaStabilityTest.h>



ThreadStability* nova_ThreadStability_ThreadStability(ExceptionData* exceptionData);
void nova_del_ThreadStability(ThreadStability** this, ExceptionData* exceptionData);
void nova_static_ThreadStability_test(ThreadStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program);

#endif