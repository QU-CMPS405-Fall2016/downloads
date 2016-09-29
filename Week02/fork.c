#include <stdio.h>
#include <unistd.h>

int main(void)
{
	pid_t ret;
	int x = 5;

	printf("1\n");

	ret = fork();

	// I am the child
	if (ret == 0) {
		printf("(C) I am the child\n");
		x = 10;
		printf("(C) I just changed x to 10\n");
		printf("(C) x = %d\n", x);
	}
	// I am the parent
	else {
		sleep(5);
		printf("(P) I am the parent\n");
		printf("(P) x = %d\n", x);
	}

	return 0;
}
