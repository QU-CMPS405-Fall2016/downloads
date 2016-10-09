#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>

void stop_handler(int signo);
void quit_handler(int signo);
void int_handler(int signo);

int main(void) 
{
	int i;

	signal(SIGTSTP, stop_handler);
	signal(SIGQUIT, quit_handler);
	signal(SIGINT, int_handler);

	printf("Press the keyboard commands that cause some signals: Ctrl-z Ctrl-\\ Ctrl-c\n");
	while(1) {
		// Put the process to sleep until a signal is received
		pause();
	}

	exit(0);
}

void stop_handler(int signo) 
{
	// If we get a signal while we're in the signal handler,
	// the default handler will run.  We do this to prevent that.
	signal(SIGTSTP, stop_handler);

	fprintf(stderr, "Nope, not gonna stop!\n");
}

void quit_handler(int signo) 
{
	signal(SIGQUIT, quit_handler);

	fprintf(stderr, "Nope, not gonna quit!\n");
}

void int_handler(int signo) 
{
	signal(SIGINT, int_handler);

	fprintf(stderr, "Nope, not gonna interrupt!\n");
}

