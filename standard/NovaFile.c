#include <precompiled.h>
#include "NovaFile.h"


nova_VTable_File nova_VTable_File_val =
{
	nova_0_Object_toString,
	nova_0_Object_equals,
};
CCLASS_PRIVATE
(
	FILE* nova_File_fp;
	String* nova_File_location;
	
)

File* nova_1_File_construct(File* this, ExceptionData* exceptionData, String* nova_0_location)
{
	CCLASS_NEW(File, this);
	this->vtable = &nova_VTable_File_val;
	nova_Object_super((Object*)this, 0);
	nova_Object_this((Object*)(this), exceptionData);
	nova_File_super(this, 0);
	
	{
		nova_0_File_this(this, exceptionData, nova_0_location);
	}
	
	return this;
}

File* nova_2_File_construct(File* this, ExceptionData* exceptionData, FILE* nova_0_fp)
{
	CCLASS_NEW(File, this);
	this->vtable = &nova_VTable_File_val;
	nova_Object_super((Object*)this, 0);
	nova_Object_this((Object*)(this), exceptionData);
	nova_File_super(this, 0);
	
	{
		nova_1_File_this(this, exceptionData, nova_0_fp);
	}
	
	return this;
}

void nova_del_File(File** this, ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	nova_del_String(&(*this)->prv->nova_File_location, exceptionData);
	NOVA_FREE((*this)->prv);
	
	{
	}
	NOVA_FREE(*this);
}

void nova_0_File_this(File* this, ExceptionData* exceptionData, String* nova_0_location)
{
	this->prv->nova_File_location = nova_0_location;
	this->prv->nova_File_fp = fopen(nova_String_toCharArray(nova_0_location, exceptionData), "r+");
}

void nova_1_File_this(File* this, ExceptionData* exceptionData, FILE* nova_0_fp)
{
	this->prv->nova_File_fp = nova_0_fp;
}

char nova_File_delete(File* this, ExceptionData* exceptionData)
{
	nova_File_close(this, exceptionData);
	return remove(nova_String_toCharArray(this->prv->nova_File_location, exceptionData)) == 0;
}

void nova_File_reopen(File* this, ExceptionData* exceptionData)
{
	nova_File_close(this, exceptionData);
	this->prv->nova_File_fp = fopen(nova_String_toCharArray(this->prv->nova_File_location, exceptionData), "r+");
}

void nova_File_rewind(File* this, ExceptionData* exceptionData)
{
	rewind(this->prv->nova_File_fp);
}

char nova_File_exists(File* this, ExceptionData* exceptionData)
{
	return this->prv->nova_File_fp != 0;
}

void nova_File_clearContents(File* this, ExceptionData* exceptionData)
{
	if (nova_File_exists(this, exceptionData))
	{
		this->prv->nova_File_fp = fopen(nova_String_toCharArray(this->prv->nova_File_location, exceptionData), "w");
	}
}

char nova_File_create(File* this, ExceptionData* exceptionData)
{
	if (!nova_File_exists(this, exceptionData))
	{
		this->prv->nova_File_fp = fopen(nova_String_toCharArray(this->prv->nova_File_location, exceptionData), "w");
		if (!nova_File_exists(this, exceptionData))
		{
			perror("fopen");
			return 0;
		}
		nova_File_reopen(this, exceptionData);
		if (!nova_File_exists(this, exceptionData))
		{
			perror("fopen");
			return 0;
		}
		return 1;
	}
	return 0;
}

String* nova_File_readAllContents(File* this, ExceptionData* exceptionData)
{
	String* nova_1_data;
	String* nova_1_line;
	
	nova_1_data = nova_String_construct(0, exceptionData, "");
	nova_1_line = nova_File_readLine(this, exceptionData);
	while (nova_1_line != (String*)nova_null)
	{
		if (nova_1_data->nova_String_length > 0)
		{
			nova_1_data = nova_1_data->vtable->nova_virtual_0_concat(nova_1_data, exceptionData, nova_String_construct(0, exceptionData, "\n"));
		}
		nova_1_data = nova_1_data->vtable->nova_virtual_0_concat(nova_1_data, exceptionData, nova_1_line);
		nova_1_line = nova_File_readLine(this, exceptionData);
	}
	return nova_1_data;
}

String* nova_File_readLine(File* this, ExceptionData* exceptionData)
{
	int nova_1_buf;
	int nova_1_size;
	char* nova_1_line;
	char nova_1_c;
	int nova_1_index;
	
	nova_1_buf = 5;
	nova_1_size = nova_1_buf;
	nova_1_line = (char*)NOVA_MALLOC(sizeof(char) * (nova_1_size));
	nova_1_c = getc(this->prv->nova_File_fp);
	if (nova_1_c == EOF)
	{
		return (String*)nova_null;
	}
	nova_1_index = 0;
	while (nova_1_c != '\n' && nova_1_c != EOF)
	{
		if (nova_1_index >= nova_1_size)
		{
			nova_1_size = nova_1_size + nova_1_buf;
			nova_1_line = realloc(nova_1_line, nova_1_size);
		}
		nova_1_line[nova_1_index++] = nova_1_c;
		nova_1_c = getc(this->prv->nova_File_fp);
	}
	if (nova_1_index >= nova_1_size)
	{
		nova_1_size++;
		nova_1_line = realloc(nova_1_line, nova_1_size);
	}
	nova_1_line[nova_1_index++] = '\0';
	nova_1_line = realloc(nova_1_line, nova_1_index);
	return nova_String_construct(0, exceptionData, nova_1_line);
}

void nova_File_writeLine(File* this, ExceptionData* exceptionData, String* nova_0_line)
{
	nova_File_write(this, exceptionData, nova_0_line->vtable->nova_virtual_0_concat(nova_0_line, exceptionData, nova_String_construct(0, exceptionData, "\n")));
}

void nova_File_write(File* this, ExceptionData* exceptionData, String* nova_0_data)
{
	fputs(nova_String_toCharArray(nova_0_data, exceptionData), this->prv->nova_File_fp);
	nova_File_flush(this, exceptionData);
}

void nova_File_flush(File* this, ExceptionData* exceptionData)
{
	fflush(this->prv->nova_File_fp);
}

void nova_File_close(File* this, ExceptionData* exceptionData)
{
	if (nova_File_exists(this, exceptionData))
	{
		fclose(this->prv->nova_File_fp);
	}
}

int nova_static_File_getMaxOpenFiles(File* this, ExceptionData* exceptionData)
{
	return getMaxOpenFiles();
}

void nova_static_File_setMaxOpenFiles(File* this, ExceptionData* exceptionData, int nova_0_num)
{
	short nova_1_min;
	short nova_1_max;
	
	nova_1_min = (short)(20);
	nova_1_max = (short)(2048);
	if (nova_0_num > nova_1_max || nova_0_num < nova_1_min)
	{
		String* nova_local_0;
		String* nova_local_1;
		
		nova_local_0 = nova_2_Int_toString(nova_Int_construct(0, exceptionData, nova_0_num), exceptionData);
		nova_local_1 = nova_2_Short_toString(nova_Short_construct(0, exceptionData, nova_1_min), exceptionData);
		nova_static_0_Console_writeLine(0, exceptionData, nova_0_String_concat(nova_String_construct(0, exceptionData, "Invalid max number of open files: "), exceptionData, nova_local_0->vtable->nova_virtual_0_concat(nova_local_0, exceptionData, nova_0_String_concat(nova_String_construct(0, exceptionData, "\nValid values include "), exceptionData, nova_local_1->vtable->nova_virtual_0_concat(nova_local_1, exceptionData, nova_0_String_concat(nova_String_construct(0, exceptionData, "-"), exceptionData, nova_2_Short_toString(nova_Short_construct(0, exceptionData, nova_1_max), exceptionData)))))));
	}
	else
	{
		setMaxOpenFiles(nova_0_num);
	}
}

void nova_File_super(File* this, ExceptionData* exceptionData)
{
	this->prv->nova_File_fp = (FILE*)nova_null;
	this->prv->nova_File_location = (String*)nova_null;
}
