#ifndef EXCEPTION_HANDLER_H
#define EXCEPTION_HANDLER_H

#include <stdio.h>
#include <setjmp.h>

#define TRY \
	do\
	{\
		jmp_buf __FATHOM__jmp_buf;\
		\
		ExceptionData* newData = new_ExceptionData(__FATHOM__exception_data, &__FATHOM__jmp_buf);\
		\
		if (__FATHOM__exception_data)\
		{\
			newData->setParent(newData, __FATHOM__exception_data, __FATHOM__exception_data);\
		}\
		\
		__FATHOM__exception_data = newData;\
		\
		switch(setjmp(__FATHOM__jmp_buf))\
		{\
			case 0:\
				while(1)\
				{

#define CATCH(x) \
	break;\
	case x:

#define FINALLY	\
		break;\
	}\
	default:

#define END_TRY \
		}\
	}\
	while(0)

#define THROW(x) __FATHOM__exception_data->jumpToBuffer(__FATHOM__exception_data, 0, x);//longjmp(*__FATHOM__exception_data->getCorrectBuffer(__FATHOM__exception_data, 0, x), x)

#endif
