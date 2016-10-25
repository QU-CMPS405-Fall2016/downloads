#include <stdio.h>
#include <unistd.h>

/* Fun trick: Run this in one window, and the following in another:
   top -b | grep a.out
   Then you can see the priority values change
*/
int main(void)
{
	int nv = 0;

	// Get the current nice value
	nv = nice(0);
	printf("Current nice value is %d\n", nv);

	sleep(5);

	// Let's lower our priority
	// Note that a higher nice value is a lower priority
	nv = nice(10);
	printf("Lowered my priority to %d\n", nv);

	sleep(5);

	// Let's raise our priority
	nv = nice(-10);
	printf("Raised my priority to %d\n", nv);

	sleep(5);

	// Get the current nice value
	nv = nice(0);
	printf("Current nice value is %d\n", nv);

	return 0;
}
