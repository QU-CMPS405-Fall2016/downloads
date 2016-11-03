/******************************************************************************
* FILE: pthread_simple.c
* DESCRIPTION:
*   A "hello world" Pthreads program.  Demonstrates thread creation and
*   termination.
* AUTHOR: Blaise Barney
* LAST REVISED: 08/09/11
******************************************************************************/
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#define NUM_THREADS	5

#define NUMS 100000000
int nums[NUMS];

struct timeval tv1, tv2;
void timer_start()
{
        gettimeofday(&tv1, NULL);
}

double timer_stop()
{
        gettimeofday(&tv2, NULL);

        return ( (double) (tv2.tv_usec - tv1.tv_usec) / 1000000 +
                 (double) (tv2.tv_sec - tv1.tv_sec) );
}

void * calc_sum(void *threadid)
{
    long tid = (long) threadid;
    long start = (NUMS/NUM_THREADS)*tid;
    long sum = 0;
    long i = 0;
    
    for(i = start; i < (start+NUMS/NUM_THREADS); i++) {
	sum = sum + nums[i];
    }

    printf("Hello World! It's me, thread #%ld and sum is %ld!\n", tid, sum);
    pthread_exit((void *)sum);
}

int main(int argc, char *argv[])
{
    pthread_t threads[NUM_THREADS];
    int rc;
    long t;
    long retval;
    long sum = 0;
    double time;

    // Generate a bunch of random numbers
    for(t = 0; t < NUMS; t++) {
	nums[t] = rand() % 10;
    }

    timer_start();
    for (t = 0; t < NUM_THREADS; t++) {
	printf("In main: creating thread %ld\n", t);
	rc = pthread_create(&threads[t], NULL, calc_sum, (void *) t);
	if (rc) {
	    printf("ERROR; return code from pthread_create() is %d\n", rc);
	    exit(-1);
	}
    }

    // Join all the threads to get their results
    for(t = 0; t < NUM_THREADS; t++) {
	pthread_join(threads[t], (void **)&retval);
	sum += retval;
    }
    time = timer_stop();

    printf("The sum is %ld and the time is %lf\n", sum, time);
    /* Last thing that main() should do */
    pthread_exit(NULL);
}
