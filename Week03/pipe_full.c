#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int pfds[2];
    char buf[30];
    int n;
    int i=-1;

    if (pipe(pfds) == -1) {
        printf("There was an error openning the pipe!\n");
        exit(1);
    }

    do {
        n = write(pfds[1],"a",1);        
    } while (n == 1);

    printf("This will never run because when the pipe is full, write() will block.\n");
}
