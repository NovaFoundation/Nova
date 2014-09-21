#include <precompiled.h>
#include "NovaClosureStability.h"

typedef int (*nova_1_0_closure)(void*, ExceptionData*, int, int);
typedef int (*nova_2_0_closure)(void*, ExceptionData*, int, int);
typedef double (*nova_3_0_closure)(void*, ExceptionData*, double);
typedef double (*nova_4_0_closure)(void*, ExceptionData*, double);
typedef void (*nova_5_0_closure)(void*, ExceptionData*);
typedef void (*nova_6_0_closure)(void*, ExceptionData*);

nova_VTable_ClosureStability nova_VTable_ClosureStability_val =
{
	nova_0_Object_toString,
	nova_0_Object_equals,
};
CCLASS_PRIVATE
(
	int nova_ClosureStability_number;
	
)

void nova_ClosureStability_incrementNumber(ClosureStability* this, ExceptionData* exceptionData);
void nova_static_ClosureStability_testClosures(ClosureStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program);
void nova_static_ClosureStability_testMathClosures(ClosureStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program);
void nova_static_ClosureStability_testInstanceClosure(ClosureStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program);
int nova_static_ClosureStability_callClosure(ClosureStability* this, ExceptionData* exceptionData, nova_2_0_closure nova_0_closure, void* nova_ref_0_closure, int nova_0_a, int nova_0_b);
double nova_static_ClosureStability_mathClosure(ClosureStability* this, ExceptionData* exceptionData, nova_4_0_closure nova_0_closure, void* nova_ref_0_closure, double nova_0_value);
void nova_static_ClosureStability_instanceClosure(ClosureStability* this, ExceptionData* exceptionData, nova_6_0_closure nova_0_closure, void* nova_ref_0_closure);
int nova_static_ClosureStability_multiply(ClosureStability* this, ExceptionData* exceptionData, int nova_0_value1, int nova_0_value2);
int nova_static_ClosureStability_pow(ClosureStability* this, ExceptionData* exceptionData, int nova_0_base, int nova_0_pow);
double nova_static_ClosureStability_TOLERANCE;

ClosureStability* nova_0_ClosureStability_construct(ClosureStability* this, ExceptionData* exceptionData)
{
	CCLASS_NEW(ClosureStability, this);
	this->vtable = &nova_VTable_ClosureStability_val;
	nova_Object_super((Object*)this, 0);
	nova_Object_this((Object*)(this), exceptionData);
	nova_ClosureStability_super(this, 0);
	
	{
		nova_ClosureStability_this(this, exceptionData);
	}
	
	return this;
}

void nova_del_ClosureStability(ClosureStability** this, ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	NOVA_FREE((*this)->prv);
	
	{
	}
	NOVA_FREE(*this);
}

void nova_ClosureStability_incrementNumber(ClosureStability* this, ExceptionData* exceptionData)
{
	this->prv->nova_ClosureStability_number++;
}

void nova_static_ClosureStability_test(ClosureStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program)
{
	nova_static_ClosureStability_TOLERANCE = 0.0000000001;
	nova_static_ClosureStability_testClosures((ClosureStability*)nova_null, exceptionData, nova_0_program);
}

void nova_static_ClosureStability_testClosures(ClosureStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program)
{
	nova_static_0_Console_writeLine(0, exceptionData, nova_String_construct(0, exceptionData, "Testing closures..."));
	nova_static_ClosureStability_testMathClosures((ClosureStability*)nova_null, exceptionData, nova_0_program);
	nova_static_ClosureStability_testInstanceClosure((ClosureStability*)nova_null, exceptionData, nova_0_program);
}

void nova_static_ClosureStability_testMathClosures(ClosureStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program)
{
	int nova_1_a;
	int nova_1_b;
	double nova_1_value;
	
	nova_static_0_Console_write(0, exceptionData, nova_String_construct(0, exceptionData, "Testing static math closures... "));
	nova_1_a = 5;
	nova_1_b = 3;
	nova_1_value = 0.5;
	if (nova_static_ClosureStability_callClosure((ClosureStability*)nova_null, exceptionData, (nova_2_0_closure)&nova_static_ClosureStability_multiply, (ClosureStability*)nova_null, nova_1_a, nova_1_b) != nova_static_ClosureStability_multiply((ClosureStability*)nova_null, exceptionData, nova_1_a, nova_1_b))
	{
		nova_1_StabilityTest_fail(nova_0_program, exceptionData, nova_String_construct(0, exceptionData, "Failed to call multiply(int, int) closure"));
	}
	if (nova_static_ClosureStability_callClosure((ClosureStability*)nova_null, exceptionData, (nova_2_0_closure)&nova_static_ClosureStability_pow, (ClosureStability*)nova_null, nova_1_a, nova_1_b) != nova_static_ClosureStability_pow((ClosureStability*)nova_null, exceptionData, nova_1_a, nova_1_b))
	{
		nova_1_StabilityTest_fail(nova_0_program, exceptionData, nova_String_construct(0, exceptionData, "Failed to call pow(int, int) closure"));
	}
	if (nova_static_ClosureStability_mathClosure((ClosureStability*)nova_null, exceptionData, (nova_4_0_closure)&nova_static_Math_sin, 0, nova_1_value) - nova_static_Math_sin(0, exceptionData, nova_1_value) >= nova_static_ClosureStability_TOLERANCE)
	{
		nova_1_StabilityTest_fail(nova_0_program, exceptionData, nova_String_construct(0, exceptionData, "Failed to call Math.sin(double) closure"));
	}
	if (nova_static_ClosureStability_mathClosure((ClosureStability*)nova_null, exceptionData, (nova_4_0_closure)&nova_static_Math_tan, 0, nova_1_value) - nova_static_Math_tan(0, exceptionData, nova_1_value) >= nova_static_ClosureStability_TOLERANCE)
	{
		nova_1_StabilityTest_fail(nova_0_program, exceptionData, nova_String_construct(0, exceptionData, "Failed to call Math.tan(double) closure"));
	}
	nova_static_0_Console_writeLine(0, exceptionData, nova_String_construct(0, exceptionData, "OK"));
}

void nova_static_ClosureStability_testInstanceClosure(ClosureStability* this, ExceptionData* exceptionData, StabilityTest* nova_0_program)
{
	ClosureStability* nova_1_c;
	
	nova_static_0_Console_write(0, exceptionData, nova_String_construct(0, exceptionData, "Testing instance closures... "));
	nova_1_c = nova_0_ClosureStability_construct(0, exceptionData);
	nova_static_ClosureStability_instanceClosure((ClosureStability*)nova_null, exceptionData, (nova_6_0_closure)&nova_ClosureStability_incrementNumber, nova_1_c);
	if (nova_1_c->prv->nova_ClosureStability_number == 0)
	{
		nova_1_StabilityTest_fail(nova_0_program, exceptionData, nova_String_construct(0, exceptionData, "Failed to call incrementNumber() instance closure"));
	}
	nova_static_ClosureStability_instanceClosure((ClosureStability*)nova_null, exceptionData, (nova_6_0_closure)&nova_ClosureStability_incrementNumber, nova_0_ClosureStability_construct(0, exceptionData));
	nova_static_ClosureStability_instanceClosure((ClosureStability*)nova_null, exceptionData, (nova_6_0_closure)&nova_ClosureStability_incrementNumber, nova_1_c);
	nova_static_0_Console_writeLine(0, exceptionData, nova_String_construct(0, exceptionData, "OK"));
}

int nova_static_ClosureStability_callClosure(ClosureStability* this, ExceptionData* exceptionData, nova_2_0_closure nova_0_closure, void* nova_ref_0_closure, int nova_0_a, int nova_0_b)
{
	return nova_0_closure(nova_ref_0_closure, exceptionData, nova_0_a, nova_0_b);
}

double nova_static_ClosureStability_mathClosure(ClosureStability* this, ExceptionData* exceptionData, nova_4_0_closure nova_0_closure, void* nova_ref_0_closure, double nova_0_value)
{
	return nova_0_closure(nova_ref_0_closure, exceptionData, nova_0_value);
}

void nova_static_ClosureStability_instanceClosure(ClosureStability* this, ExceptionData* exceptionData, nova_6_0_closure nova_0_closure, void* nova_ref_0_closure)
{
	nova_0_closure(nova_ref_0_closure, exceptionData);
}

int nova_static_ClosureStability_multiply(ClosureStability* this, ExceptionData* exceptionData, int nova_0_value1, int nova_0_value2)
{
	return nova_0_value1 * nova_0_value2;
}

int nova_static_ClosureStability_pow(ClosureStability* this, ExceptionData* exceptionData, int nova_0_base, int nova_0_pow)
{
	int nova_1_value;
	int nova_1_i;
	
	nova_1_value = nova_0_base;
	nova_1_i = 0;
	for (; nova_1_i < nova_0_pow - 1; nova_1_i++)
	{
		nova_1_value = nova_1_value * nova_0_base;
	}
	return nova_1_value;
}

void nova_ClosureStability_this(ClosureStability* this, ExceptionData* exceptionData)
{
}

void nova_ClosureStability_super(ClosureStability* this, ExceptionData* exceptionData)
{
	this->prv->nova_ClosureStability_number = 0;
}
