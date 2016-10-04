#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int pfds[2];

    if (pipe(pfds) == -1) {
        printf("Could not create pipe!\n");
        exit(-1);
    }
    
    if (!fork()) {        
        /* make stdout same as pfds[1] 
         * Note that 1 is the fd for stdout
         */
        dup2(pfds[1], 1); 

        /* We will be writing to the pipe, so close this. */
        close(pfds[0]);

        /* Exec the new command */ 
        execlp("ls", "ls", NULL);
    } else {      
        /* make stdin same as pfds[0] 
         * Note that 0 is the fd for stdin
         */  
        dup2(pfds[0], 0);

        /* We will be reading from the pipe, so close this. */
        close(pfds[1]); 

        /* Exec the new command */
        execlp("wc", "wc", "-l", NULL);
    }
}

