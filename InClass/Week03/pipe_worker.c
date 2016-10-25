#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>
#include <sys/wait.h>
#include <string.h>

int main(void)
{
	int mypipe[2];
	int myotherpipe[2];
	char buf[255];
	int i;

	if (pipe(mypipe) == -1) {
		exit(-1);
	}

	if (pipe(myotherpipe) == -1) {
		exit(-1);
	}

	if (fork() != 0) {
		// Parent
		while(1) {
			printf("Enter a string: ");
			fgets(buf, 255, stdin);
			write(mypipe[1], buf, 255);
			if (strncmp(buf, "exit", 4) == 0) {
				break;
			}
			read(myotherpipe[0], buf, 255);
			printf("%s", buf);
		}
		wait(NULL);
		exit(0);
	}
	else {
		// Child
		while(1) {
			read(mypipe[0], buf, 255);

			if (strncmp(buf, "exit", 4) == 0) {
				break;
			}

			for(i = 0; buf[i] != '\0' ; i++) {
				buf[i] = toupper(buf[i]);
			}
			write(myotherpipe[1], buf, 255);
		}
		printf("(C) is leaving\n");
		exit(0);
	}
}
