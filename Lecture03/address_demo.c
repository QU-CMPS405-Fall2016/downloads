#include <stdio.h>

int a = 5;

int adder(int par)
{
	int c = 20;
	printf("Address of c:\t%p\n", &c);
	printf("Address of par:\t%p\n", &par);
	return par + c;
}

int main(void)
{
	int b = 10;

	printf("Address of addr function:\t%p\n", &adder);
	printf("Address of a:\t%p\n", &a);
	printf("Address of b:\t%p\n", &b);

	printf("%d\n",a+adder(b));
}
