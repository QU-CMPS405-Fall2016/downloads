#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>

void term_handler(int signo);

int main(void) 
{
	int i;

	signal(SIGTERM, term_handler);

	printf("Entering a big loop\n");
	for(i=0; i < 2000; i++) {
		printf("%d\n",i);
		sleep(1);
	}

	exit(0);
}

void term_handler(int signo) 
{
	// If we get a signal while we're in the signal handler,
	// the default handler will run.  We do this to prevent that.
	signal(SIGTERM, term_handler);

	fprintf(stderr, "Nope, not gonna die!\n");
}
