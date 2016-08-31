#include <precompiled.h>
#include <compiler/tree/node/compiler_tree_node_Nova_Node.h>

typedef struct
{
	/* Node child */ compiler_tree_node_Nova_Node** compiler_tree_node_Nova_Node_Nova_child;
} Context1;


compiler_tree_node_Extension_VTable_Node compiler_tree_node_Extension_VTable_Node_val =
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
		(void(*)(compiler_tree_node_Nova_Listenable*, nova_exception_Nova_ExceptionData*, compiler_tree_node_Nova_Node*))compiler_tree_node_Nova_Node_Nova_onAdded,
		(void(*)(compiler_tree_node_annotation_Nova_Annotatable*, nova_exception_Nova_ExceptionData*, compiler_tree_node_annotation_Nova_Annotation*))compiler_tree_node_Nova_Node_Nova_addAnnotation,
		0,
		0,
	},
	nova_Nova_Object_0_Nova_toString,
	nova_Nova_Object_0_Nova_equals,
	nova_Nova_Object_Accessor_Nova_hashCodeLong,
	compiler_tree_node_Nova_Node_Nova_addAnnotation,
	compiler_tree_node_Nova_Node_Nova_onAdded,
	compiler_tree_node_Nova_Node_virtual1_Nova_clone,
};



void compiler_tree_node_Nova_Node_1_Nova_detach(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_fromNode);
char compiler_tree_node_Nova_Node_Nova_testLambda56(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova__1, Context1* context);


void compiler_tree_node_Nova_Node_Nova_init_static(nova_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}

compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_construct(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_parent, compiler_util_Nova_Location* compiler_tree_node_Nova_Node_Nova_location)
{
	CCLASS_NEW(compiler_tree_node_Nova_Node, this,);
	this->vtable = &compiler_tree_node_Extension_VTable_Node_val;
	nova_Nova_Object_Nova_super((nova_Nova_Object*)this, exceptionData);
	compiler_tree_node_Nova_Node_Nova_super(this, exceptionData);
	
	{
		compiler_tree_node_Nova_Node_1_Nova_this(this, exceptionData, compiler_tree_node_Nova_Node_Nova_parent, compiler_tree_node_Nova_Node_Nova_location);
	}
	
	return this;
}

void compiler_tree_node_Nova_Node_Nova_destroy(compiler_tree_node_Nova_Node** this, nova_exception_Nova_ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	compiler_util_Nova_Location_Nova_destroy(&(*this)->compiler_tree_node_Nova_Node_Nova_location, exceptionData);
	compiler_tree_node_Nova_Node_Nova_destroy(&(*this)->compiler_tree_node_Nova_Node_Nova_parent, exceptionData);
	nova_datastruct_list_Nova_Array_Nova_destroy(&(*this)->compiler_tree_node_Nova_Node_Nova_children, exceptionData);
	nova_datastruct_list_Nova_Array_Nova_destroy(&(*this)->compiler_tree_node_Nova_Node_Nova_annotations, exceptionData);
	
	NOVA_FREE(*this);
}

void compiler_tree_node_Nova_Node_1_Nova_this(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_parent, compiler_util_Nova_Location* compiler_tree_node_Nova_Node_Nova_location)
{
	this->compiler_tree_node_Nova_Node_Nova_children = nova_datastruct_list_Nova_Array_1_Nova_construct(0, exceptionData, 4);
	compiler_tree_node_Nova_Node_Mutator_Nova_parent(this, exceptionData, compiler_tree_node_Nova_Node_Nova_parent);
	this->compiler_tree_node_Nova_Node_Nova_location = compiler_tree_node_Nova_Node_Nova_location;
}

void compiler_tree_node_Nova_Node_Nova_addAnnotation(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_annotation_Nova_Annotation* compiler_tree_node_Nova_Node_Nova_annotation)
{
	this->compiler_tree_node_Nova_Node_Nova_annotations = this->compiler_tree_node_Nova_Node_Nova_annotations != (nova_datastruct_list_Nova_Array*)nova_null ? this->compiler_tree_node_Nova_Node_Nova_annotations : nova_datastruct_list_Nova_Array_0_Nova_construct(0, exceptionData);
	nova_datastruct_list_Nova_Array_0_Nova_add((nova_datastruct_list_Nova_Array*)(this->compiler_tree_node_Nova_Node_Nova_annotations), exceptionData, (nova_Nova_Object*)(compiler_tree_node_Nova_Node_Nova_annotation));
	compiler_tree_node_Nova_Node_Mutator_Nova_parent((compiler_tree_node_Nova_Node*)(compiler_tree_node_Nova_Node_Nova_annotation), exceptionData, this);
}

void compiler_tree_node_Nova_Node_0_Nova_detach(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	compiler_tree_node_Nova_Node* l1_Nova_from = (compiler_tree_node_Nova_Node*)nova_null;
	
	if (compiler_tree_node_Nova_Node_Accessor_Nova_isDecoding(this, exceptionData))
	{
		return;
	}
	l1_Nova_from = compiler_tree_node_Nova_Node_Accessor_Nova_parent(this, exceptionData);
	if (compiler_tree_node_Nova_Node_Accessor_Nova_parent(this, exceptionData)->compiler_tree_node_Nova_Node_Nova_children->nova_datastruct_list_Nova_Array_Nova_count > 0 && !compiler_tree_node_Nova_Node_0_Nova_containsChild(compiler_tree_node_Nova_Node_Accessor_Nova_parent(this, exceptionData), exceptionData, this) && compiler_tree_node_Nova_Node_Accessor_Nova_containsScope(compiler_tree_node_Nova_Node_Accessor_Nova_parent(this, exceptionData), exceptionData))
	{
		l1_Nova_from = (compiler_tree_node_Nova_Node*)(compiler_tree_node_Nova_Node_Accessor_Nova_scope(compiler_tree_node_Nova_Node_Accessor_Nova_parent(this, exceptionData), exceptionData));
	}
	compiler_tree_node_Nova_Node_1_Nova_detach(this, exceptionData, l1_Nova_from);
}

void compiler_tree_node_Nova_Node_1_Nova_detach(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_fromNode)
{
	nova_datastruct_list_Nova_Array_1_Nova_remove((nova_datastruct_list_Nova_Array*)(compiler_tree_node_Nova_Node_Nova_fromNode->compiler_tree_node_Nova_Node_Nova_children), exceptionData, (nova_Nova_Object*)(this));
	compiler_tree_node_Nova_Node_Mutator_Nova_parent(this, exceptionData, (compiler_tree_node_Nova_Node*)((nova_Nova_Object*)nova_null));
	compiler_tree_node_Nova_Node_Nova_onRemoved(this, exceptionData, compiler_tree_node_Nova_Node_Nova_fromNode);
}

void compiler_tree_node_Nova_Node_Nova_onRemoved(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_parent)
{
}

char compiler_tree_node_Nova_Node_0_Nova_containsChild(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_child)
{
	return compiler_tree_node_Nova_Node_1_Nova_containsChild(this, exceptionData, compiler_tree_node_Nova_Node_Nova_child, 0);
}

char compiler_tree_node_Nova_Node_1_Nova_containsChild(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_child, char compiler_tree_node_Nova_Node_Nova_recursive)
{
	if (nova_datastruct_list_Nova_List_virtual0_Nova_contains((nova_datastruct_list_Nova_List*)(this->compiler_tree_node_Nova_Node_Nova_children), exceptionData, (nova_Nova_Object*)(compiler_tree_node_Nova_Node_Nova_child)))
	{
		return 1;
	}
	else if (compiler_tree_node_Nova_Node_Nova_recursive)
	{
		Context1 contextArg59 = 
		{
			&compiler_tree_node_Nova_Node_Nova_child,
		};
		
		nova_datastruct_list_Nova_List_virtual0_Nova_any((nova_datastruct_list_Nova_List*)(this->compiler_tree_node_Nova_Node_Nova_children), exceptionData, (nova_datastruct_list_Nova_List_closure9_Nova_anyFunc)&compiler_tree_node_Nova_Node_Nova_testLambda56, this, &contextArg59);
	}
	return 0;
}

void compiler_tree_node_Nova_Node_Nova_onAdded(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_parent)
{
}

compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_0_Nova_clone(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_temporaryParent, compiler_util_Nova_Location* compiler_tree_node_Nova_Node_Nova_locationIn)
{
	return compiler_tree_node_Nova_Node_virtual1_Nova_clone((compiler_tree_node_Nova_Node*)(this), exceptionData, compiler_tree_node_Nova_Node_Nova_temporaryParent, compiler_tree_node_Nova_Node_Nova_locationIn, 1);
}


char compiler_tree_node_Nova_Node_Nova_testLambda56(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova__1, Context1* context)
{
	return compiler_tree_node_Nova_Node_1_Nova_containsChild(compiler_tree_node_Nova_Node_Nova__1, exceptionData, (*context->compiler_tree_node_Nova_Node_Nova_child), 1);
}

compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Accessor_Nova_parent(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	return this->compiler_tree_node_Nova_Node_Nova_parent;
}

compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Mutator_Nova_parent(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_value)
{
	if (compiler_tree_node_Nova_Node_Accessor_Nova_parent(this, exceptionData) != compiler_tree_node_Nova_Node_Nova_value)
	{
		compiler_tree_node_Nova_Node_0_Nova_detach(this, exceptionData);
		compiler_tree_node_Nova_Node_Mutator_Nova_parent(this, exceptionData, compiler_tree_node_Nova_Node_Nova_value);
	}
	return compiler_tree_node_Nova_Node_Nova_value;
}

compiler_tree_node_Nova_Scope* compiler_tree_node_Nova_Node_Accessor_Nova_scope(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	return (compiler_tree_node_Nova_Scope*)nova_null;
}

compiler_tree_node_Nova_Scope* compiler_tree_node_Nova_Node_Mutator_Nova_scope(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Scope* compiler_tree_node_Nova_Node_Nova_value)
{
	THROW(7, compiler_error_Nova_UnimplementedOperationException_Nova_construct(0, exceptionData, nova_Nova_String_1_Nova_construct(0, exceptionData, (char*)("This Node does not support Scopes"))));
	return compiler_tree_node_Nova_Node_Nova_value;
}

char compiler_tree_node_Nova_Node_Accessor_Nova_isDecoding(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	return compiler_tree_node_Nova_Node_Accessor_Nova_parent(this, exceptionData) == (compiler_tree_node_Nova_Node*)nova_null || !compiler_tree_node_Nova_Node_0_Nova_containsChild(compiler_tree_node_Nova_Node_Accessor_Nova_parent(this, exceptionData), exceptionData, this);
}


char compiler_tree_node_Nova_Node_Accessor_Nova_containsScope(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	return compiler_tree_node_Nova_Node_Accessor_Nova_scope(this, exceptionData) != (compiler_tree_node_Nova_Scope*)nova_null;
}


void compiler_tree_node_Nova_Node_Nova_super(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	this->compiler_tree_node_Nova_Node_Nova_location = (compiler_util_Nova_Location*)nova_null;
	this->compiler_tree_node_Nova_Node_Nova_parent = (compiler_tree_node_Nova_Node*)nova_null;
	this->compiler_tree_node_Nova_Node_Nova_children = (nova_datastruct_list_Nova_Array*)nova_null;
	this->compiler_tree_node_Nova_Node_Nova_annotations = (nova_datastruct_list_Nova_Array*)nova_null;
}

compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_virtual1_Nova_clone(compiler_tree_node_Nova_Node* this, nova_exception_Nova_ExceptionData* exceptionData, compiler_tree_node_Nova_Node* compiler_tree_node_Nova_Node_Nova_temporaryParent, compiler_util_Nova_Location* compiler_tree_node_Nova_Node_Nova_locationIn, char compiler_tree_node_Nova_Node_Nova_cloneChildren)
{
	return this->vtable->compiler_tree_node_Nova_Node_virtual1_Nova_clone((compiler_tree_node_Nova_Node*)(this), exceptionData, compiler_tree_node_Nova_Node_Nova_temporaryParent, compiler_tree_node_Nova_Node_Nova_locationIn, compiler_tree_node_Nova_Node_Nova_cloneChildren);
}
