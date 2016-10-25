#include <stdio.h>
#include <stdlib.h>

int a = 5;

int func(int par)
{
	int d = 30;
	printf("par:\t%p\n",&par);
	printf("d:\t%p\n",&d);
	return par;
}

int main(void) 
{
	int b = 10;
	int c = 20;
	int *e = NULL;

	printf("main:\t%p\n", &main);
	printf("a:\t%p\n", &a);
	printf("b:\t%p\n", &b);
	printf("c:\t%p\n", &c);
	func(13);

	e = malloc(sizeof(int));

	printf("e:\t%p\n", e);

	printf("%d\n", a+b); 
	return 0;
}
