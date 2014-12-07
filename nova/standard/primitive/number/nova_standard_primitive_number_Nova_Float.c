#include <precompiled.h>
#include <nova/standard/primitive/number/nova_standard_primitive_number_Nova_Float.h>


nova_VTable_nova_standard_primitive_number_Nova_Float nova_VTable_nova_standard_primitive_number_Nova_Float_val =
{
	nova_standard_Nova_Object_0_Nova_getHashCodeLong,
	nova_standard_primitive_number_Nova_Float_2_Nova_toString,
	nova_standard_Nova_Object_0_Nova_equals,
	nova_standard_primitive_number_Nova_Float_0_Nova_numDigits,
};
void nova_standard_primitive_number_Nova_FloatNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}

nova_standard_primitive_number_Nova_Float* nova_standard_primitive_number_Nova_Float_Nova_construct(nova_standard_primitive_number_Nova_Float* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int l0_Nova_value)
{
	CCLASS_NEW(nova_standard_primitive_number_Nova_Float, this,);
	this->vtable = &nova_VTable_nova_standard_primitive_number_Nova_Float_val;
	nova_standard_Nova_Object_Nova_super((nova_standard_Nova_Object*)this, exceptionData);
	nova_standard_primitive_Nova_Primitive_Nova_super((nova_standard_primitive_Nova_Primitive*)this, exceptionData);
	nova_standard_primitive_number_Nova_Number_Nova_super((nova_standard_primitive_number_Nova_Number*)this, exceptionData);
	nova_standard_Nova_Object_Nova_this((nova_standard_Nova_Object*)(this), exceptionData);
	nova_standard_primitive_Nova_Primitive_Nova_this((nova_standard_primitive_Nova_Primitive*)(this), exceptionData);
	nova_standard_primitive_number_Nova_Number_Nova_this((nova_standard_primitive_number_Nova_Number*)(this), exceptionData);
	nova_standard_primitive_number_Nova_Float_Nova_super(this, exceptionData);
	
	{
		nova_standard_primitive_number_Nova_Float_Nova_this(this, exceptionData, l0_Nova_value);
	}
	
	return this;
}

void nova_standard_primitive_number_Nova_Float_Nova_destroy(nova_standard_primitive_number_Nova_Float** this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	
	{
	}
	NOVA_FREE(*this);
}

void nova_standard_primitive_number_Nova_Float_Nova_this(nova_standard_primitive_number_Nova_Float* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int l0_Nova_value)
{
	this->nova_standard_primitive_number_Nova_Float_Nova_value = (float)(l0_Nova_value);
}

int nova_standard_primitive_number_Nova_Float_0_Nova_numDigits(nova_standard_primitive_number_Nova_Float* this, nova_standard_exception_Nova_ExceptionData* exceptionData, float l0_Nova_number)
{
	return nova_standard_primitive_number_Nova_Double_0_Nova_numDigits(0, exceptionData, (double)(l0_Nova_number));
}

nova_standard_Nova_String* nova_standard_primitive_number_Nova_Float_1_Nova_toString(nova_standard_primitive_number_Nova_Float* this, nova_standard_exception_Nova_ExceptionData* exceptionData, float l0_Nova_value)
{
	return nova_standard_primitive_number_Nova_Double_1_Nova_toString(0, exceptionData, (double)(l0_Nova_value));
}

nova_standard_Nova_String* nova_standard_primitive_number_Nova_Float_2_Nova_toString(nova_standard_primitive_number_Nova_Float* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	return nova_standard_primitive_number_Nova_Float_1_Nova_toString(this, exceptionData, this->nova_standard_primitive_number_Nova_Float_Nova_value);
}

void nova_standard_primitive_number_Nova_Float_Nova_super(nova_standard_primitive_number_Nova_Float* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	this->nova_standard_primitive_number_Nova_Float_Nova_value = 0;
}

