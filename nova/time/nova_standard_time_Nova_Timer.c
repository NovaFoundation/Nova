#include <precompiled.h>
#include <nova/time/nova_time_Nova_Timer.h>



nova_time_Extension_VTable_Timer nova_time_Extension_VTable_Timer_val =
{
	{
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		0,
		(char(*)(nova_operators_Nova_Equals*, nova_exception_Nova_ExceptionData*, nova_Nova_Object*))nova_Nova_Object_0_Nova_equals,
		0,
		0,
		0,
	},
	nova_Nova_Object_0_Nova_getHashCodeLong,
	nova_Nova_Object_0_Nova_toString,
	nova_Nova_Object_0_Nova_equals,
};




void nova_time_Nova_Timer_Nova_init_static(nova_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}

nova_time_Nova_Timer* nova_time_Nova_Timer_Nova_Timer(nova_time_Nova_Timer* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	CCLASS_NEW(nova_time_Nova_Timer, this,);
	this->vtable = &nova_time_Extension_VTable_Timer_val;
	nova_Nova_Object_Nova_super((nova_Nova_Object*)this, exceptionData);
	nova_time_Nova_Timer_Nova_super(this, exceptionData);
	
	{
		nova_time_Nova_Timer_0_Nova_this(this, exceptionData);
	}
	
	return this;
}

void nova_time_Nova_Timer_Nova_destroy(nova_time_Nova_Timer** this, nova_exception_Nova_ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	
	
	NOVA_FREE(*this);
}

void nova_time_Nova_Timer_0_Nova_this(nova_time_Nova_Timer* this, nova_exception_Nova_ExceptionData* exceptionData)
{
}

nova_time_Nova_Timer* nova_time_Nova_Timer_Nova_start(nova_time_Nova_Timer* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	this->nova_time_Nova_Timer_Nova_startTime = nova_time_Nova_Time_Accessor_Nova_currentTimeMillis(0, exceptionData);
	this->nova_time_Nova_Timer_Nova_endTime = (long_long)(0);
	return this;
}

nova_time_Nova_Timer* nova_time_Nova_Timer_Nova_stop(nova_time_Nova_Timer* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	this->nova_time_Nova_Timer_Nova_endTime = nova_time_Nova_Time_Accessor_Nova_currentTimeMillis(0, exceptionData);
	return this;
}

long_long nova_time_Nova_Timer_Accessor_Nova_duration(nova_time_Nova_Timer* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	return this->nova_time_Nova_Timer_Nova_endTime - this->nova_time_Nova_Timer_Nova_startTime;
}


void nova_time_Nova_Timer_Nova_super(nova_time_Nova_Timer* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	this->nova_time_Nova_Timer_Nova_startTime = 0;
	this->nova_time_Nova_Timer_Nova_endTime = 0;
}

