/******************************************************************************
* FILE: pthread_vars.c
* DESCRIPTION:
*   A "hello world" Pthreads program.  Demonstrates thread creation and
*   termination.  Also shows that global variables are shared, local ones
*   are not.
* AUTHORS: Blaise Barney
* 	   Ryan Riley
* LAST REVISED: November 1, 2016
******************************************************************************/
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#define NUM_THREADS	5

int global_int = 10;

void *PrintHello(void *threadid)
{
    long tid;
    int local_int = 10;
    tid = (long) threadid;
    global_int++;
    local_int++;
    printf("Hello World! It's me, thread #%ld.\n\tglobal_int is %d.\n\tlocal_int is %d\n", tid, global_int, local_int);
    pthread_exit(NULL);
}

int main(int argc, char *argv[])
{
    pthread_t threads[NUM_THREADS];
    int rc;
    long t;
    for (t = 0; t < NUM_THREADS; t++) {
	printf("In main: creating thread %ld\n", t);
	rc = pthread_create(&threads[t], NULL, PrintHello, (void *) t);
	if (rc) {
	    printf("ERROR; return code from pthread_create() is %d\n", rc);
	    exit(-1);
	}
    }

    /* Last thing that main() should do */
    pthread_exit(NULL);
}
