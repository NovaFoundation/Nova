#include <precompiled.h>
#include "NovaExceptionStability.h"




void nova_static_ExceptionStability_testException(ExceptionStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program);

ExceptionStability* nova_ExceptionStability_ExceptionStability(ExceptionData* exceptionData)
{
	ExceptionStability* this = (ExceptionStability*)1;
	
	{
	}
	
	return this;
}

void nova_del_ExceptionStability(ExceptionStability** this, ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	{
	}
	NOVA_FREE(*this);
}

void nova_static_ExceptionStability_test(ExceptionStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program)
{
	char nova_1_worked;
	
	nova_static_Console_write((Object*)0, exceptionData, nova_String_String(exceptionData, "Checking Exception handling... "));
	nova_1_worked = 0;
	TRY
	{
		nova_ExceptionData_addCode(exceptionData, exceptionData, 1);
		
		{
			nova_static_ExceptionStability_testException((Object*)0, exceptionData, nova_0_program);
		}
	}
	CATCH (1)
	{
		nova_1_worked = 1;
	}
	FINALLY
	{
	}
	END_TRY;
	if (!nova_1_worked)
	{
		nova_StabilityTest_fail(nova_0_program, exceptionData, nova_String_String(exceptionData, "Failed; uncaught Exception"));
	}
	nova_static_Console_writeLine((Object*)0, exceptionData, nova_String_String(exceptionData, "OK"));
}

void nova_static_ExceptionStability_testException(ExceptionStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program)
{
	volatile char nova_1_worked;
	
	nova_1_worked = 0;
	TRY
	{
		nova_ExceptionData_addCode(exceptionData, exceptionData, 4);
		
		{
			TRY
			{
				nova_ExceptionData_addCode(exceptionData, exceptionData, 3);
				
				{
					int nova_3_den;
					int nova_3_i;
					double nova_zero_check4;
					
					nova_3_den = 0;
					nova_zero_check4 = nova_3_den;
					if (nova_zero_check4 == 0)
					{
						THROW(3);
					}
					nova_3_i = 43 / nova_zero_check4;
				}
			}
			CATCH (3)
			{
				nova_1_worked = 1;
				THROW(4);
			}
			FINALLY
			{
			}
			END_TRY;
		}
	}
	CATCH (4)
	{
		if (!nova_1_worked)
		{
			nova_StabilityTest_fail(nova_0_program, exceptionData, nova_String_String(exceptionData, "Failed; uncaught DivideByZeroException"));
		}
		THROW(1);
	}
	FINALLY
	{
	}
	END_TRY;
	if (!nova_1_worked)
	{
		nova_StabilityTest_fail(nova_0_program, exceptionData, nova_String_String(exceptionData, "Failed; uncaught StabilityTestException"));
	}
}
