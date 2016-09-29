#include <stdio.h>
#include <unistd.h>

int main(void)
{
	pid_t ret;

	printf("1\n");

	ret = fork();

	printf("2: %d\n", ret);

	return 0;
}
