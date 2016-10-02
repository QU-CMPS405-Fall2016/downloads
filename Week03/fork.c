#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(void)
{
	pid_t ret;
	char * args[] = {"/bin/ls", "-la", NULL};
	int status=0;

	ret = fork();

	// I am the child
	if (ret == 0) {
		execv("/bin/ls", args);
	}
	// I am the parent
	else {
		ret = wait(&status);
		printf("Child process %d finished with status %d\n", ret, status);
	}

	return 0;
}
