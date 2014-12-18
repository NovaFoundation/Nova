#include <precompiled.h>
#include <nova/standard/primitive/number/nova_standard_primitive_number_Nova_Long.h>


nova_VTable_nova_standard_primitive_number_Nova_Long nova_VTable_nova_standard_primitive_number_Nova_Long_val =
{
	nova_standard_Nova_Object_1_Nova_getHashCodeLong,
	nova_standard_primitive_number_Nova_Long_2_Nova_toString,
	nova_standard_Nova_Object_0_Nova_equals,
	nova_standard_primitive_number_Nova_Long_0_Nova_numDigits,
};
void nova_standard_primitive_number_Nova_LongNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}

nova_standard_primitive_number_Nova_Long* nova_standard_primitive_number_Nova_Long_Nova_construct(nova_standard_primitive_number_Nova_Long* this, nova_standard_exception_Nova_ExceptionData* exceptionData, long l0_Nova_value)
{
	CCLASS_NEW(nova_standard_primitive_number_Nova_Long, this,);
	this->vtable = &nova_VTable_nova_standard_primitive_number_Nova_Long_val;
	nova_standard_Nova_Object_Nova_super((nova_standard_Nova_Object*)this, exceptionData);
	nova_standard_primitive_Nova_Primitive_Nova_super((nova_standard_primitive_Nova_Primitive*)this, exceptionData);
	nova_standard_primitive_number_Nova_Number_2_Nova_super((nova_standard_primitive_number_Nova_Number*)this, exceptionData);
	nova_standard_Nova_Object_2_Nova_this((nova_standard_Nova_Object*)(this), exceptionData);
	nova_standard_primitive_Nova_Primitive_4_Nova_this((nova_standard_primitive_Nova_Primitive*)(this), exceptionData);
	nova_standard_primitive_number_Nova_Number_6_Nova_this((nova_standard_primitive_number_Nova_Number*)(this), exceptionData);
	nova_standard_primitive_number_Nova_Long_4_Nova_super(this, exceptionData);
	
	{
		nova_standard_primitive_number_Nova_Long_Nova_this(this, exceptionData, l0_Nova_value);
	}
	
	return this;
}

void nova_standard_primitive_number_Nova_Long_Nova_destroy(nova_standard_primitive_number_Nova_Long** this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	
	NOVA_FREE(*this);
}

void nova_standard_primitive_number_Nova_Long_Nova_this(nova_standard_primitive_number_Nova_Long* this, nova_standard_exception_Nova_ExceptionData* exceptionData, long l0_Nova_value)
{
	this->nova_standard_primitive_number_Nova_Long_Nova_value = l0_Nova_value;
}

int nova_standard_primitive_number_Nova_Long_0_Nova_numDigits(nova_standard_primitive_number_Nova_Long* this, nova_standard_exception_Nova_ExceptionData* exceptionData, long l0_Nova_number)
{
	int l1_Nova_size;
	
	if (l0_Nova_number < 0)
	{
		return nova_standard_primitive_number_Nova_Long_0_Nova_numDigits((nova_standard_primitive_number_Nova_Long*)nova_null, exceptionData, -l0_Nova_number) + 1;
	}
	l0_Nova_number = l0_Nova_number / 10;
	l1_Nova_size = 1;
	for (; l0_Nova_number > 0; l1_Nova_size++)
	{
		l0_Nova_number = l0_Nova_number / 10;
	}
	return l1_Nova_size;
}

nova_standard_Nova_String* nova_standard_primitive_number_Nova_Long_1_Nova_toString(nova_standard_primitive_number_Nova_Long* this, nova_standard_exception_Nova_ExceptionData* exceptionData, long l0_Nova_value)
{
	int l1_Nova_charOffset;
	int l1_Nova_digits;
	char* l1_Nova_data;
	int l1_Nova_offset;
	int l1_Nova_nums;
	int l2_Nova_index;
	
	l1_Nova_charOffset = (int)('0');
	l1_Nova_digits = nova_standard_primitive_number_Nova_Long_0_Nova_numDigits((nova_standard_primitive_number_Nova_Long*)nova_null, exceptionData, l0_Nova_value);
	l1_Nova_data = (char*)NOVA_MALLOC(sizeof(nova_standard_primitive_number_Nova_Char) * l1_Nova_digits + 1);
	l1_Nova_data[l1_Nova_digits] = '\0';
	l1_Nova_offset = 0;
	if (l0_Nova_value < 0)
	{
		l1_Nova_data[0] = '-';
		l0_Nova_value = -l0_Nova_value;
		l1_Nova_offset = 1;
	}
	l1_Nova_nums = l1_Nova_digits-- - l1_Nova_offset;
	l2_Nova_index = 0;
	for (; l2_Nova_index < l1_Nova_nums; l2_Nova_index++)
	{
		l1_Nova_data[l1_Nova_digits - l2_Nova_index] = (char)(l1_Nova_charOffset + l0_Nova_value % 10);
		l0_Nova_value = l0_Nova_value / 10;
	}
	return nova_standard_Nova_String_1_Nova_construct(0, exceptionData, l1_Nova_data);
}

nova_standard_Nova_String* nova_standard_primitive_number_Nova_Long_2_Nova_toString(nova_standard_primitive_number_Nova_Long* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	return nova_standard_primitive_number_Nova_Long_1_Nova_toString(this, exceptionData, this->nova_standard_primitive_number_Nova_Long_Nova_value);
}

void nova_standard_primitive_number_Nova_Long_4_Nova_super(nova_standard_primitive_number_Nova_Long* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	this->nova_standard_primitive_number_Nova_Long_Nova_value = 0;
}

