#include <precompiled.h>
#include <nova/standard/datastruct/list/nova_standard_datastruct_list_Nova_List.h>

nova_standard_datastruct_list_Extension_VTable_List nova_standard_datastruct_list_Extension_VTable_List_val =
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
		0,
		0,
		0,
	},
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_toArray,
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_forEach,
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_map,
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_any,
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_all,
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_filter,
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_take,
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_skip,
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_firstWhere,
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_reverse,
	nova_standard_datastruct_list_Nova_List_virtual0_Nova_join,
};


CCLASS_PRIVATE
(
	nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_List_Nova_first;
	nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_List_Nova_last;
	
)
void nova_standard_datastruct_list_Nova_ListNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}












nova_standard_datastruct_list_Nova_Array* nova_standard_datastruct_list_Nova_List_virtual0_Nova_toArray(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	return this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_toArray((nova_standard_datastruct_list_Nova_List*)(this), exceptionData);
}

void nova_standard_datastruct_list_Nova_List_virtual0_Nova_forEach(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_List_closure3_Nova_func nova_standard_datastruct_list_Nova_List_Nova_func, void* nova_standard_datastruct_list_Nova_List_ref_Nova_func)
{
	this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_forEach((nova_standard_datastruct_list_Nova_List*)(this), exceptionData, nova_standard_datastruct_list_Nova_List_Nova_func, nova_standard_datastruct_list_Nova_List_ref_Nova_func);
}

nova_standard_datastruct_list_Nova_List* nova_standard_datastruct_list_Nova_List_virtual0_Nova_map(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_List_closure6_Nova_mapFunc nova_standard_datastruct_list_Nova_List_Nova_mapFunc, void* nova_standard_datastruct_list_Nova_List_ref_Nova_mapFunc)
{
	return this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_map((nova_standard_datastruct_list_Nova_List*)(this), exceptionData, nova_standard_datastruct_list_Nova_List_Nova_mapFunc, nova_standard_datastruct_list_Nova_List_ref_Nova_mapFunc);
}

char nova_standard_datastruct_list_Nova_List_virtual0_Nova_any(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_List_closure9_Nova_anyFunc nova_standard_datastruct_list_Nova_List_Nova_anyFunc, void* nova_standard_datastruct_list_Nova_List_ref_Nova_anyFunc)
{
	return this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_any((nova_standard_datastruct_list_Nova_List*)(this), exceptionData, nova_standard_datastruct_list_Nova_List_Nova_anyFunc, nova_standard_datastruct_list_Nova_List_ref_Nova_anyFunc);
}

char nova_standard_datastruct_list_Nova_List_virtual0_Nova_all(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_List_closure12_Nova_allFunc nova_standard_datastruct_list_Nova_List_Nova_allFunc, void* nova_standard_datastruct_list_Nova_List_ref_Nova_allFunc)
{
	return this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_all((nova_standard_datastruct_list_Nova_List*)(this), exceptionData, nova_standard_datastruct_list_Nova_List_Nova_allFunc, nova_standard_datastruct_list_Nova_List_ref_Nova_allFunc);
}

nova_standard_datastruct_list_Nova_List* nova_standard_datastruct_list_Nova_List_virtual0_Nova_filter(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_List_closure15_Nova_filterFunc nova_standard_datastruct_list_Nova_List_Nova_filterFunc, void* nova_standard_datastruct_list_Nova_List_ref_Nova_filterFunc)
{
	return this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_filter((nova_standard_datastruct_list_Nova_List*)(this), exceptionData, nova_standard_datastruct_list_Nova_List_Nova_filterFunc, nova_standard_datastruct_list_Nova_List_ref_Nova_filterFunc);
}

nova_standard_datastruct_list_Nova_List* nova_standard_datastruct_list_Nova_List_virtual0_Nova_take(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_List_Nova_howMany)
{
	return this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_take((nova_standard_datastruct_list_Nova_List*)(this), exceptionData, nova_standard_datastruct_list_Nova_List_Nova_howMany);
}

nova_standard_datastruct_list_Nova_List* nova_standard_datastruct_list_Nova_List_virtual0_Nova_skip(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData, int nova_standard_datastruct_list_Nova_List_Nova_howMany)
{
	return this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_skip((nova_standard_datastruct_list_Nova_List*)(this), exceptionData, nova_standard_datastruct_list_Nova_List_Nova_howMany);
}

nova_standard_Nova_Object* nova_standard_datastruct_list_Nova_List_virtual0_Nova_firstWhere(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_list_Nova_List_closure18_Nova_func nova_standard_datastruct_list_Nova_List_Nova_func, void* nova_standard_datastruct_list_Nova_List_ref_Nova_func)
{
	return this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_firstWhere((nova_standard_datastruct_list_Nova_List*)(this), exceptionData, nova_standard_datastruct_list_Nova_List_Nova_func, nova_standard_datastruct_list_Nova_List_ref_Nova_func);
}

nova_standard_datastruct_list_Nova_List* nova_standard_datastruct_list_Nova_List_virtual0_Nova_reverse(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	return this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_reverse((nova_standard_datastruct_list_Nova_List*)(this), exceptionData);
}

nova_standard_Nova_String* nova_standard_datastruct_list_Nova_List_virtual0_Nova_join(nova_standard_datastruct_list_Nova_List* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_String* nova_standard_datastruct_list_Nova_List_Nova_delimiter)
{
	return this->vtable->itable.nova_standard_datastruct_list_Nova_List_virtual0_Nova_join((nova_standard_datastruct_list_Nova_List*)(this), exceptionData, nova_standard_datastruct_list_Nova_List_Nova_delimiter);
}

