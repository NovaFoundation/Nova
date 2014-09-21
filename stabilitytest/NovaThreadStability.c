#include <precompiled.h>
#include "NovaThreadStability.h"


nova_VTable_ThreadStability nova_VTable_ThreadStability_val =
{
	nova_0_Object_toString,
	nova_0_Object_equals,
};

void nova_static_ThreadStability_createThreads(ThreadStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program, ThreadImplementation** nova_0_threads, int nova_0_amount);
void nova_static_ThreadStability_checkMemoryAccess(ThreadStability* this, ExceptionData* exceptionData);
void nova_static_ThreadStability_joinThreads(ThreadStability* this, ExceptionData* exceptionData, ThreadImplementation** nova_0_threads, int nova_0_amount);

ThreadStability* nova_0_ThreadStability_construct(ThreadStability* this, ExceptionData* exceptionData)
{
	CCLASS_NEW(ThreadStability, this,);
	this->vtable = &nova_VTable_ThreadStability_val;
	nova_Object_super((Object*)this, 0);
	nova_Object_this((Object*)(this), exceptionData);
	nova_ThreadStability_super(this, 0);
	
	{
		nova_ThreadStability_this(this, exceptionData);
	}
	
	return this;
}

void nova_del_ThreadStability(ThreadStability** this, ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	{
	}
	NOVA_FREE(*this);
}

void nova_static_ThreadStability_test(ThreadStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program)
{
	ThreadImplementation** nova_1_threads;
	
	nova_static_0_Console_writeLine(0, exceptionData, nova_String_construct(0, exceptionData, "Checking Thread.nova with 20 Threads... "));
	nova_1_threads = (ThreadImplementation**)NOVA_MALLOC(sizeof(ThreadImplementation) * (20));
	nova_static_ThreadStability_createThreads((ThreadStability*)nova_null, exceptionData, nova_0_program, nova_1_threads, 20);
	nova_static_ThreadStability_checkMemoryAccess((ThreadStability*)nova_null, exceptionData);
	nova_static_ThreadStability_joinThreads((ThreadStability*)nova_null, exceptionData, nova_1_threads, 20);
}

void nova_static_ThreadStability_createThreads(ThreadStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program, ThreadImplementation** nova_0_threads, int nova_0_amount)
{
	StabilityExceptionHandler* nova_1_handler;
	int nova_1_i;
	
	nova_1_handler = nova_StabilityExceptionHandler_construct(0, exceptionData, nova_0_program);
	nova_1_i = 0;
	for (; nova_1_i < nova_0_amount; nova_1_i++)
	{
		nova_0_threads[nova_1_i] = nova_ThreadImplementation_construct(0, exceptionData, 10, 10);
		nova_Thread_start((Thread*)(nova_0_threads[nova_1_i]), exceptionData);
	}
}

void nova_static_ThreadStability_checkMemoryAccess(ThreadStability* this, ExceptionData* exceptionData)
{
	int nova_1_i;
	
	nova_static_Thread_sleep(0, exceptionData, (long_long)(30));
	nova_static_0_Console_write(0, exceptionData, nova_String_construct(0, exceptionData, "Checking memory access with multi-threading... "));
	nova_1_i = 0;
	for (; nova_1_i < 1000; nova_1_i++)
	{
		String* nova_2_s;
		
		nova_2_s = nova_static_1_Int_toString(0, exceptionData, nova_1_i);
	}
	nova_static_0_Console_writeLine(0, exceptionData, nova_String_construct(0, exceptionData, "OK"));
}

void nova_static_ThreadStability_joinThreads(ThreadStability* this, ExceptionData* exceptionData, ThreadImplementation** nova_0_threads, int nova_0_amount)
{
	int nova_1_i;
	
	nova_1_i = 0;
	for (; nova_1_i < nova_0_amount; nova_1_i++)
	{
		nova_Thread_join((Thread*)(nova_0_threads[nova_1_i]), exceptionData);
	}
}

void nova_ThreadStability_this(ThreadStability* this, ExceptionData* exceptionData)
{
}

void nova_ThreadStability_super(ThreadStability* this, ExceptionData* exceptionData)
{
}
