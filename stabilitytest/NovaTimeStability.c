#include <precompiled.h>
#include "NovaTimeStability.h"


nova_VTable_TimeStability nova_VTable_TimeStability_val =
{
	nova_0_Object_toString,
	nova_0_Object_equals,
};

TimeStability* nova_0_TimeStability_construct(TimeStability* this, ExceptionData* exceptionData)
{
	CCLASS_NEW(TimeStability, this,);
	this->vtable = &nova_VTable_TimeStability_val;
	nova_Object_super((Object*)this, 0);
	nova_Object_this((Object*)(this), exceptionData);
	nova_TimeStability_super(this, 0);
	
	{
		nova_TimeStability_this(this, exceptionData);
	}
	
	return this;
}

void nova_del_TimeStability(TimeStability** this, ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	{
	}
	NOVA_FREE(*this);
}

void nova_static_TimeStability_test(TimeStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program)
{
	long_long nova_1_start;
	long_long nova_1_time;
	
	nova_static_0_Console_write(0, exceptionData, nova_String_construct(0, exceptionData, "Checking Time.nova... "));
	nova_1_start = nova_static_Time_currentTimeMillis(0, exceptionData);
	nova_static_Thread_sleep(0, exceptionData, (long_long)(100));
	nova_1_time = (long_long)(nova_static_Time_currentTimeMillis(0, exceptionData) - nova_1_start);
	if (nova_1_time >= 100 && nova_1_time < 130)
	{
		nova_static_0_Console_writeLine(0, exceptionData, nova_String_construct(0, exceptionData, "OK"));
	}
	else
	{
		String* nova_local_0;
		
		nova_local_0 = nova_3_Long_toString(nova_Long_construct(0, exceptionData, nova_1_time), exceptionData);
		nova_1_StabilityTest_fail(nova_0_program, exceptionData, nova_0_String_concat(nova_String_construct(0, exceptionData, "Failed; expected 100ms, found "), exceptionData, nova_local_0->vtable->nova_virtual_0_concat(nova_local_0, exceptionData, nova_String_construct(0, exceptionData, "ms"))));
	}
}

void nova_TimeStability_this(TimeStability* this, ExceptionData* exceptionData)
{
}

void nova_TimeStability_super(TimeStability* this, ExceptionData* exceptionData)
{
}
