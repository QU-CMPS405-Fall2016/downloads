/******************************************************************************
* FILE: pthread_simple.c
* DESCRIPTION:
*   A "hello world" Pthreads program.  Demonstrates thread creation and
*   termination.
* AUTHOR: Blaise Barney
* LAST REVISED: 08/09/11
******************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

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

int main(int argc, char *argv[])
{
    long t;
    long sum = 0;
    double time = 0.0;

    // Generate a bunch of random numbers
    for(t = 0; t < NUMS; t++) {
	nums[t] = rand() % 10;
    }

    timer_start();
    for(t = 0; t < NUMS; t++) {
	sum += nums[t];
    }
    time = timer_stop();

    printf("The sum is %ld and time is %lf\n", sum, time);
}
