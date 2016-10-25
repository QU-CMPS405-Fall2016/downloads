#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

void term_handler(int signo)
{
	int pid;

	pid = fork();
	if (pid == 0) {
		execlp("ls","ls",NULL);
	}
	else {
		wait(NULL);
		printf("Thank you!\n");
		exit(0);
	}
}

int main(void)
{
	signal(SIGTERM, term_handler);
	while (1) {
		pause();
	}
}
