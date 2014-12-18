#include <precompiled.h>
#include <nova/standard/datastruct/nova_standard_datastruct_Nova_BinaryNode.h>


nova_VTable_nova_standard_datastruct_Nova_BinaryNode nova_VTable_nova_standard_datastruct_Nova_BinaryNode_val =
{
	nova_standard_Nova_Object_1_Nova_getHashCodeLong,
	nova_standard_Nova_Object_0_Nova_toString,
	nova_standard_Nova_Object_0_Nova_equals,
	nova_standard_datastruct_Nova_BinaryNode_Nova_preorder,
	nova_standard_datastruct_Nova_BinaryNode_Nova_inorder,
	nova_standard_datastruct_Nova_BinaryNode_Nova_postorder,
};

void nova_standard_datastruct_Nova_BinaryNode_Mutator_Nova_left(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_Nova_BinaryNode* l0_Nova_data);
void nova_standard_datastruct_Nova_BinaryNode_Mutator_Nova_right(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_Nova_BinaryNode* l0_Nova_data);
void nova_standard_datastruct_Nova_BinaryNodeNova_init_static(nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}

nova_standard_datastruct_Nova_BinaryNode* nova_standard_datastruct_Nova_BinaryNode_2_Nova_construct(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	CCLASS_NEW(nova_standard_datastruct_Nova_BinaryNode, this,);
	this->vtable = &nova_VTable_nova_standard_datastruct_Nova_BinaryNode_val;
	nova_standard_Nova_Object_Nova_super((nova_standard_Nova_Object*)this, exceptionData);
	nova_standard_datastruct_Nova_Node_Nova_super((nova_standard_datastruct_Nova_Node*)this, exceptionData);
	nova_standard_Nova_Object_2_Nova_this((nova_standard_Nova_Object*)(this), exceptionData);
	nova_standard_datastruct_Nova_Node_2_Nova_this((nova_standard_datastruct_Nova_Node*)(this), exceptionData);
	nova_standard_datastruct_Nova_BinaryNode_2_Nova_super(this, exceptionData);
	
	{
		nova_standard_datastruct_Nova_BinaryNode_2_Nova_this(this, exceptionData);
	}
	
	return this;
}

nova_standard_datastruct_Nova_BinaryNode* nova_standard_datastruct_Nova_BinaryNode_3_Nova_construct(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object* l0_Nova_data)
{
	CCLASS_NEW(nova_standard_datastruct_Nova_BinaryNode, this,);
	this->vtable = &nova_VTable_nova_standard_datastruct_Nova_BinaryNode_val;
	nova_standard_Nova_Object_Nova_super((nova_standard_Nova_Object*)this, exceptionData);
	nova_standard_datastruct_Nova_Node_Nova_super((nova_standard_datastruct_Nova_Node*)this, exceptionData);
	nova_standard_Nova_Object_2_Nova_this((nova_standard_Nova_Object*)(this), exceptionData);
	nova_standard_datastruct_Nova_Node_3_Nova_this((nova_standard_datastruct_Nova_Node*)(this), exceptionData, l0_Nova_data);
	nova_standard_datastruct_Nova_BinaryNode_2_Nova_super(this, exceptionData);
	
	{
		nova_standard_datastruct_Nova_BinaryNode_3_Nova_this(this, exceptionData, l0_Nova_data);
	}
	
	return this;
}

nova_standard_datastruct_Nova_BinaryNode* nova_standard_datastruct_Nova_BinaryNode_4_Nova_construct(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object* l0_Nova_data, int l0_Nova_size)
{
	CCLASS_NEW(nova_standard_datastruct_Nova_BinaryNode, this,);
	this->vtable = &nova_VTable_nova_standard_datastruct_Nova_BinaryNode_val;
	nova_standard_Nova_Object_Nova_super((nova_standard_Nova_Object*)this, exceptionData);
	nova_standard_datastruct_Nova_Node_Nova_super((nova_standard_datastruct_Nova_Node*)this, exceptionData);
	nova_standard_Nova_Object_2_Nova_this((nova_standard_Nova_Object*)(this), exceptionData);
	nova_standard_datastruct_Nova_Node_5_Nova_this((nova_standard_datastruct_Nova_Node*)(this), exceptionData, l0_Nova_data, l0_Nova_size);
	nova_standard_datastruct_Nova_BinaryNode_2_Nova_super(this, exceptionData);
	
	{
		nova_standard_datastruct_Nova_BinaryNode_4_Nova_this(this, exceptionData, l0_Nova_data, l0_Nova_size);
	}
	
	return this;
}

void nova_standard_datastruct_Nova_BinaryNode_Nova_destroy(nova_standard_datastruct_Nova_BinaryNode** this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	NOVA_FREE(*this);
}

void nova_standard_datastruct_Nova_BinaryNode_2_Nova_this(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	nova_standard_datastruct_Nova_BinaryNode_3_Nova_this(this, exceptionData, (nova_standard_Nova_Object*)nova_null);
}

void nova_standard_datastruct_Nova_BinaryNode_3_Nova_this(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object* l0_Nova_data)
{
	nova_standard_datastruct_Nova_BinaryNode_4_Nova_this(this, exceptionData, l0_Nova_data, 2);
}

void nova_standard_datastruct_Nova_BinaryNode_4_Nova_this(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object* l0_Nova_data, int l0_Nova_size)
{
	nova_standard_datastruct_Nova_Node_5_Nova_this((nova_standard_datastruct_Nova_Node*)(this), exceptionData, l0_Nova_data, l0_Nova_size);
}

void nova_standard_datastruct_Nova_BinaryNode_Nova_addChild(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_Nova_Object* l0_Nova_data)
{
	nova_standard_datastruct_Nova_Comparable* l1_Nova_temp;
	
	l1_Nova_temp = (nova_standard_datastruct_Nova_Comparable*)this->nova_standard_datastruct_Nova_Node_Nova_data;
	if (l1_Nova_temp->vtable->nova_standard_datastruct_Nova_Comparable_virtual0_Nova_compareTo(l1_Nova_temp, exceptionData, (nova_standard_Nova_Object*)(nova_standard_primitive_number_Nova_Char_5_Nova_construct(0, exceptionData, (char)l0_Nova_data))) < 0)
	{
		if (nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData) == (nova_standard_datastruct_Nova_BinaryNode*)nova_null)
		{
			nova_standard_datastruct_Nova_BinaryNode_Mutator_Nova_left(this, exceptionData, nova_standard_datastruct_Nova_BinaryNode_3_Nova_construct(0, exceptionData, l0_Nova_data));
		}
		else
		{
			nova_standard_datastruct_Nova_BinaryNode_Nova_addChild(nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData), exceptionData, l0_Nova_data);
		}
	}
	else
	{
		if (nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData) == (nova_standard_datastruct_Nova_BinaryNode*)nova_null)
		{
			nova_standard_datastruct_Nova_BinaryNode_Mutator_Nova_right(this, exceptionData, nova_standard_datastruct_Nova_BinaryNode_3_Nova_construct(0, exceptionData, l0_Nova_data));
		}
		else
		{
			nova_standard_datastruct_Nova_BinaryNode_Nova_addChild(nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData), exceptionData, l0_Nova_data);
		}
	}
}

nova_standard_Nova_String* nova_standard_datastruct_Nova_BinaryNode_Nova_preorder(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	nova_standard_Nova_String* l1_Nova_str;
	
	l1_Nova_str = nova_standard_Nova_String_1_Nova_construct(0, exceptionData, "");
	l1_Nova_str = l1_Nova_str->vtable->nova_standard_Nova_String_virtual0_Nova_concat(l1_Nova_str, exceptionData, this->nova_standard_datastruct_Nova_Node_Nova_data->vtable->nova_standard_Nova_Object_virtual0_Nova_toString(this->nova_standard_datastruct_Nova_Node_Nova_data, exceptionData));
	if (nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData) != (nova_standard_datastruct_Nova_BinaryNode*)nova_null)
	{
		nova_standard_Nova_String* nova_local_0;
		
		nova_local_0 = nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData)->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_preorder(nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData), exceptionData);
		l1_Nova_str = l1_Nova_str->vtable->nova_standard_Nova_String_virtual0_Nova_concat(l1_Nova_str, exceptionData, nova_standard_Nova_String_0_Nova_concat(nova_standard_Nova_String_1_Nova_construct(0, exceptionData, ", "), exceptionData, nova_local_0->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_preorder((nova_standard_datastruct_Nova_BinaryNode*)(nova_local_0), exceptionData)));
	}
	if (nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData) != (nova_standard_datastruct_Nova_BinaryNode*)nova_null)
	{
		nova_standard_Nova_String* nova_local_1;
		
		nova_local_1 = nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData)->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_preorder(nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData), exceptionData);
		l1_Nova_str = l1_Nova_str->vtable->nova_standard_Nova_String_virtual0_Nova_concat(l1_Nova_str, exceptionData, nova_standard_Nova_String_0_Nova_concat(nova_standard_Nova_String_1_Nova_construct(0, exceptionData, ", "), exceptionData, nova_local_1->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_preorder((nova_standard_datastruct_Nova_BinaryNode*)(nova_local_1), exceptionData)));
	}
	return l1_Nova_str;
}

nova_standard_Nova_String* nova_standard_datastruct_Nova_BinaryNode_Nova_inorder(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	nova_standard_Nova_String* l1_Nova_str;
	
	l1_Nova_str = nova_standard_Nova_String_1_Nova_construct(0, exceptionData, "");
	if (nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData) != (nova_standard_datastruct_Nova_BinaryNode*)nova_null)
	{
		nova_standard_Nova_String* nova_local_0;
		nova_standard_Nova_String* nova_local_1;
		
		nova_local_0 = nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData)->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_inorder(nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData), exceptionData)->vtable->nova_standard_Nova_String_virtual0_Nova_concat(vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_inorder(nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData), exceptionData), exceptionData, nova_standard_Nova_String_1_Nova_construct(0, exceptionData, ", "));
		nova_local_1 = nova_local_0->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_inorder((nova_standard_datastruct_Nova_BinaryNode*)(nova_local_0), exceptionData);
		l1_Nova_str = l1_Nova_str->vtable->nova_standard_Nova_String_virtual0_Nova_concat(l1_Nova_str, exceptionData, nova_local_1->vtable->nova_standard_Nova_String_virtual0_Nova_concat(nova_local_1, exceptionData, nova_standard_Nova_String_1_Nova_construct(0, exceptionData, ", ")));
	}
	l1_Nova_str = l1_Nova_str->vtable->nova_standard_Nova_String_virtual0_Nova_concat(l1_Nova_str, exceptionData, this->nova_standard_datastruct_Nova_Node_Nova_data->vtable->nova_standard_Nova_Object_virtual0_Nova_toString(this->nova_standard_datastruct_Nova_Node_Nova_data, exceptionData));
	if (nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData) != (nova_standard_datastruct_Nova_BinaryNode*)nova_null)
	{
		nova_standard_Nova_String* nova_local_2;
		
		nova_local_2 = nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData)->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_inorder(nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData), exceptionData);
		l1_Nova_str = l1_Nova_str->vtable->nova_standard_Nova_String_virtual0_Nova_concat(l1_Nova_str, exceptionData, nova_standard_Nova_String_0_Nova_concat(nova_standard_Nova_String_1_Nova_construct(0, exceptionData, ", "), exceptionData, nova_local_2->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_inorder((nova_standard_datastruct_Nova_BinaryNode*)(nova_local_2), exceptionData)));
	}
	return l1_Nova_str;
}

nova_standard_Nova_String* nova_standard_datastruct_Nova_BinaryNode_Nova_postorder(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	nova_standard_Nova_String* l1_Nova_str;
	
	l1_Nova_str = nova_standard_Nova_String_1_Nova_construct(0, exceptionData, "");
	if (nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData) != (nova_standard_datastruct_Nova_BinaryNode*)nova_null)
	{
		nova_standard_Nova_String* nova_local_0;
		
		nova_local_0 = nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData)->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_preorder(nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(this, exceptionData), exceptionData);
		l1_Nova_str = l1_Nova_str->vtable->nova_standard_Nova_String_virtual0_Nova_concat(l1_Nova_str, exceptionData, nova_standard_Nova_String_0_Nova_concat(nova_standard_Nova_String_1_Nova_construct(0, exceptionData, ", "), exceptionData, nova_local_0->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_preorder((nova_standard_datastruct_Nova_BinaryNode*)(nova_local_0), exceptionData)));
	}
	if (nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData) != (nova_standard_datastruct_Nova_BinaryNode*)nova_null)
	{
		nova_standard_Nova_String* nova_local_1;
		
		nova_local_1 = nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData)->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_preorder(nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(this, exceptionData), exceptionData);
		l1_Nova_str = l1_Nova_str->vtable->nova_standard_Nova_String_virtual0_Nova_concat(l1_Nova_str, exceptionData, nova_standard_Nova_String_0_Nova_concat(nova_standard_Nova_String_1_Nova_construct(0, exceptionData, ", "), exceptionData, nova_local_1->vtable->nova_standard_datastruct_Nova_BinaryNode_virtual_Nova_preorder((nova_standard_datastruct_Nova_BinaryNode*)(nova_local_1), exceptionData)));
	}
	l1_Nova_str = l1_Nova_str->vtable->nova_standard_Nova_String_virtual0_Nova_concat(l1_Nova_str, exceptionData, this->nova_standard_datastruct_Nova_Node_Nova_data->vtable->nova_standard_Nova_Object_virtual0_Nova_toString(this->nova_standard_datastruct_Nova_Node_Nova_data, exceptionData));
	return l1_Nova_str;
}

nova_standard_datastruct_Nova_BinaryNode* nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_left(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	if (nova_standard_datastruct_Nova_ArrayList_Accessor_Nova_empty(this->nova_standard_datastruct_Nova_Node_Nova_children, exceptionData))
	{
		return (nova_standard_datastruct_Nova_BinaryNode*)nova_null;
	}
	return (nova_standard_datastruct_Nova_BinaryNode*)((nova_standard_datastruct_Nova_Node*)nova_standard_datastruct_Nova_ArrayList_Nova_get(this->nova_standard_datastruct_Nova_Node_Nova_children, exceptionData, 0));
}

void nova_standard_datastruct_Nova_BinaryNode_Mutator_Nova_left(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_Nova_BinaryNode* l0_Nova_data)
{
	if (nova_standard_datastruct_Nova_ArrayList_Accessor_Nova_empty(this->nova_standard_datastruct_Nova_Node_Nova_children, exceptionData))
	{
		nova_standard_datastruct_Nova_ArrayList_1_Nova_add(this->nova_standard_datastruct_Nova_Node_Nova_children, exceptionData, 0, (nova_standard_Nova_Object*)(l0_Nova_data));
	}
	else
	{
		nova_standard_datastruct_Nova_ArrayList_Nova_set(this->nova_standard_datastruct_Nova_Node_Nova_children, exceptionData, 0, (nova_standard_Nova_Object*)(l0_Nova_data));
	}
}

nova_standard_datastruct_Nova_BinaryNode* nova_standard_datastruct_Nova_BinaryNode_Accessor_Nova_right(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
	if (this->nova_standard_datastruct_Nova_Node_Nova_children->nova_standard_datastruct_Nova_ArrayList_Nova_size <= 1)
	{
		return (nova_standard_datastruct_Nova_BinaryNode*)nova_null;
	}
	return (nova_standard_datastruct_Nova_BinaryNode*)((nova_standard_datastruct_Nova_Node*)nova_standard_datastruct_Nova_ArrayList_Nova_get(this->nova_standard_datastruct_Nova_Node_Nova_children, exceptionData, 1));
}

void nova_standard_datastruct_Nova_BinaryNode_Mutator_Nova_right(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData, nova_standard_datastruct_Nova_BinaryNode* l0_Nova_data)
{
	if (this->nova_standard_datastruct_Nova_Node_Nova_children->nova_standard_datastruct_Nova_ArrayList_Nova_size <= 1)
	{
		nova_standard_datastruct_Nova_ArrayList_1_Nova_add(this->nova_standard_datastruct_Nova_Node_Nova_children, exceptionData, 1, (nova_standard_Nova_Object*)(l0_Nova_data));
	}
	else
	{
		nova_standard_datastruct_Nova_ArrayList_Nova_set(this->nova_standard_datastruct_Nova_Node_Nova_children, exceptionData, 1, (nova_standard_Nova_Object*)(l0_Nova_data));
	}
}

void nova_standard_datastruct_Nova_BinaryNode_2_Nova_super(nova_standard_datastruct_Nova_BinaryNode* this, nova_standard_exception_Nova_ExceptionData* exceptionData)
{
}

