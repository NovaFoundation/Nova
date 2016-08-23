#include <precompiled.h>
#include <example/network/example_network_Nova_ClientDemo.h>



example_network_Extension_VTable_ClientDemo example_network_Extension_VTable_ClientDemo_val =
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


void example_network_Nova_ClientDemo_Nova_init_static(nova_exception_Nova_ExceptionData* exceptionData)
{
	{
	}
}

example_network_Nova_ClientDemo* example_network_Nova_ClientDemo_Nova_ClientDemo(example_network_Nova_ClientDemo* this, nova_exception_Nova_ExceptionData* exceptionData)
{
	CCLASS_NEW(example_network_Nova_ClientDemo, this,);
	this->vtable = &example_network_Extension_VTable_ClientDemo_val;
	nova_Nova_Object_Nova_super((nova_Nova_Object*)this, exceptionData);
	example_network_Nova_ClientDemo_Nova_super(this, exceptionData);
	
	{
		example_network_Nova_ClientDemo_0_Nova_this(this, exceptionData);
	}
	
	return this;
}

void example_network_Nova_ClientDemo_Nova_destroy(example_network_Nova_ClientDemo** this, nova_exception_Nova_ExceptionData* exceptionData)
{
	if (!*this)
	{
		return;
	}
	
	
	NOVA_FREE(*this);
}

void example_network_Nova_ClientDemo_Nova_main(example_network_Nova_ClientDemo* this, nova_exception_Nova_ExceptionData* exceptionData, nova_datastruct_list_Nova_Array* example_network_Nova_ClientDemo_Nova_args)
{
	nova_network_Nova_ClientSocket* l1_Nova_socket = (nova_network_Nova_ClientSocket*)nova_null;
	nova_Nova_String* l1_Nova_ip = (nova_Nova_String*)nova_null;
	int l1_Nova_port = 0;
	char l1_Nova_connected = 0;
	
	l1_Nova_socket = nova_network_Nova_ClientSocket_Nova_ClientSocket(0, exceptionData);
	l1_Nova_ip = nova_Nova_String_1_Nova_String(0, exceptionData, "127.0.0.1");
	l1_Nova_port = (int)(5675);
	nova_io_Nova_Console_1_Nova_writeLine(0, exceptionData, nova_Nova_String_0_Nova_concat(nova_Nova_String_1_Nova_String(0, exceptionData, "Attempting to connect to "), exceptionData, nova_Nova_String_virtual1_Nova_concat((nova_Nova_String*)(l1_Nova_ip), exceptionData, nova_Nova_String_0_Nova_concat(nova_Nova_String_1_Nova_String(0, exceptionData, ":"), exceptionData, nova_primitive_number_Nova_Int_2_Nova_toString(0, exceptionData, l1_Nova_port)))));
	l1_Nova_connected = nova_network_Nova_ClientSocket_Nova_connect(l1_Nova_socket, exceptionData, l1_Nova_ip, l1_Nova_port);
	if (l1_Nova_connected)
	{
		example_network_Nova_ConnectionThread* l1_Nova_thread = (example_network_Nova_ConnectionThread*)nova_null;
		
		l1_Nova_thread = example_network_Nova_ConnectionThread_Nova_ConnectionThread(0, exceptionData, l1_Nova_socket->nova_network_Nova_ClientSocket_Nova_connection);
		nova_thread_Nova_Thread_Nova_start((nova_thread_Nova_Thread*)(l1_Nova_thread), exceptionData);
		nova_io_Nova_Console_1_Nova_writeLine(0, exceptionData, nova_Nova_String_1_Nova_String(0, exceptionData, "Connected!"));
		while (l1_Nova_socket->nova_network_Nova_ClientSocket_Nova_connection->nova_network_Nova_ConnectionSocket_Nova_connected)
		{
			nova_Nova_String* l2_Nova_message = (nova_Nova_String*)nova_null;
			
			l2_Nova_message = nova_io_Nova_Console_Nova_readLine(0, exceptionData);
			if (nova_operators_Nova_Equals_virtual0_Nova_equals((nova_operators_Nova_Equals*)(l2_Nova_message), exceptionData, (nova_Nova_Object*)(nova_Nova_String_1_Nova_String(0, exceptionData, "q"))))
			{
				nova_network_Nova_ClientSocket_Nova_close(l1_Nova_socket, exceptionData);
				break;
			}
			nova_io_Nova_OutputStream_virtual0_Nova_write((nova_io_Nova_OutputStream*)(l1_Nova_socket->nova_network_Nova_ClientSocket_Nova_connection->nova_network_Nova_ConnectionSocket_Nova_out), exceptionData, l2_Nova_message);
		}
		nova_network_Nova_ClientSocket_Nova_close(l1_Nova_socket, exceptionData);
	}
	else
	{
		nova_io_Nova_Console_1_Nova_writeLine(0, exceptionData, nova_Nova_String_1_Nova_String(0, exceptionData, "Failed to connect"));
		nova_io_Nova_Console_Nova_waitForEnter(0, exceptionData);
	}
}

void example_network_Nova_ClientDemo_0_Nova_this(example_network_Nova_ClientDemo* this, nova_exception_Nova_ExceptionData* exceptionData)
{
}

void example_network_Nova_ClientDemo_Nova_super(example_network_Nova_ClientDemo* this, nova_exception_Nova_ExceptionData* exceptionData)
{
}

