#include <precompiled.h>
#include "NovaSVGComponentList.h"




SVGComponentList* nova_SVGComponentList_SVGComponentList(ExceptionData* exceptionData)
{
	CCLASS_NEW(SVGComponentList, this,);
	
	this->nova_SVGComponentList_start = (Object*)0;
	{
	}
	
	return this;
}

void nova_del_SVGComponentList(SVGComponentList** this, ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	nova_del_SVGComponentNode(&(*this)->nova_SVGComponentList_start, exceptionData);
	
	{
	}
	NOVA_FREE(*this);
}

void nova_SVGComponentList_generateOutput(SVGComponentList* this, ExceptionData* exceptionData, File* nova_0_file)
{
	SVGComponentNode* nova_1_current;
	
	nova_1_current = this->nova_SVGComponentList_start;
	while (nova_1_current != (Object*)0)
	{
		SVGCircle* nova_2_component;
		
		nova_2_component = nova_1_current->nova_SVGComponentNode_component;
		nova_2_component->vtable->nova_virtual_generateOutput(nova_2_component, exceptionData, nova_0_file);
		nova_1_current = nova_1_current->nova_SVGComponentNode_next;
	}
}

void nova_SVGComponentList_addChild(SVGComponentList* this, ExceptionData* exceptionData, SVGComponent* nova_0_component)
{
	SVGComponentNode* nova_1_node;
	
	nova_1_node = nova_SVGComponentNode_SVGComponentNode(exceptionData);
	nova_1_node->nova_SVGComponentNode_component = nova_0_component;
	if (this->nova_SVGComponentList_start == (Object*)0)
	{
		this->nova_SVGComponentList_start = nova_1_node;
	}
	else
	{
		SVGComponentNode* nova_3_current;
		
		nova_3_current = this->nova_SVGComponentList_start;
		while (nova_3_current->nova_SVGComponentNode_next != (Object*)0)
		{
			nova_3_current = nova_3_current->nova_SVGComponentNode_next;
		}
		nova_3_current->nova_SVGComponentNode_next = nova_1_node;
	}
}
