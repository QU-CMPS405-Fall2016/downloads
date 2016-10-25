#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>

void alarm_handler(int signo);

int main(void) 
{
	signal(SIGALRM, alarm_handler);

	alarm(5);
	printf("Entering a big loop\n");
	while(1) {
		pause();
	}

	exit(0);
}

void alarm_handler(int signo) 
{
	printf("Got an alarm!\n");
	alarm(5);
}
