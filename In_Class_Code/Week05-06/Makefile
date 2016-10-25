all: fifo sjf pr

sjf: sjf.c
	gcc -o sjf sjf.c

fifo: fifo.c
	gcc -o fifo fifo.c

pr: pr.c
	gcc -o pr pr.c

fifo.pdf: fifo.ps
	ps2pdf fifo.ps

fifo.ps: fifo.c
	enscript --media=A4 -Ec -Gp fifo.ps fifo.c

clean:
	rm -f *.o *~ fifo sjf pr fifo.ps fifo.pdf
