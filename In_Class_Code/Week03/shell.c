#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>

int main(void)
{
	pid_t ret;
	char linein[255];
	char *args[2] = {NULL,NULL};
	int status;
	int i;

	while(1) {
		printf("ryansh> ");
		fgets(linein, 255, stdin);
		for(i=0; linein[i] != '\0'; i++) {
			if (linein[i] == '\n') {
				linein[i] = '\0';
				break;
			}
		}

		if (strcmp(linein, "exit") == 0) {
			break;
		}

		ret = fork();

		// I am the child
		if (ret == 0) {
			args[0] = linein;
			execvp(linein, args);
			printf("Error: Command not found\n");
		}
		// I am the parent
		else {
			ret = wait(&status);
			fflush(stdout);
			printf("Child process %d finished with status %d\n", ret, status);
		}
	}

	return 0;
}
