#include <stdlib.h>
#include <stdio.h>

struct task {			//process 
    int id;			//pid 
    int bt;			//burst time
    int at;			//arrival time
    int pr;			//priority
};
typedef struct task Task;
typedef Task *TaskPtr;

struct qnode {			//a node in the run/ready queue
    Task data;			//process
    struct qnode *nextPtr;
};
typedef struct qnode Qnode;
typedef Qnode *QnodePtr;

void enqueue(QnodePtr * headPtr, QnodePtr * tailPtr, Task task);
Task dequeue(QnodePtr * headPtr, QnodePtr * tailPtr);
int isEmpty(QnodePtr headPtr);

void enqueue(QnodePtr * headPtr, QnodePtr * tailPtr, Task task)
{
    QnodePtr newNodePtr = malloc(sizeof(Qnode));
    if (newNodePtr != NULL) {
	newNodePtr->data = task;
	newNodePtr->nextPtr = NULL;
	if (isEmpty(*headPtr)) {
	    *headPtr = newNodePtr;
	} else {
	    (*tailPtr)->nextPtr = newNodePtr;
	}
	*tailPtr = newNodePtr;
    }
}

Task dequeue(QnodePtr * headPtr, QnodePtr * tailPtr)
{
    Task value;
    QnodePtr tempPtr;
    value = (*headPtr)->data;
    tempPtr = *headPtr;
    *headPtr = (*headPtr)->nextPtr;
    if (*headPtr == NULL) {
	*tailPtr = NULL;
    }
    free(tempPtr);
    return value;
}

int isEmpty(QnodePtr headPtr)
{
    return headPtr == NULL;
}

///////////////////////////////////////////////

struct event {			//an event event
    int type;			//event type 0:arrival, 1: departure
    int time;			//event time
    Task task;			//the process
};
typedef struct event Event;
typedef Event *EventPtr;

struct eventQnode {		//an node in the events list
    Event data;			//the event
    struct eventQnode *nextPtr;
};
typedef struct eventQnode EventQnode;
typedef EventQnode *EventQnodePtr;


void enqueueevent(EventQnodePtr * headPtr, EventQnodePtr * tailPtr,
		  Event e);
Event dequeueevent(EventQnodePtr * headPtr, EventQnodePtr * tailPtr);
int isEmptyEQ(EventQnodePtr headPtr);
void displayEvents(EventQnodePtr currentPtr);

void
enqueueevent(EventQnodePtr * headPtr, EventQnodePtr * tailPtr, Event se)
{
    EventQnodePtr newNodePtr = malloc(sizeof(EventQnode));
    if (newNodePtr != NULL) {
	newNodePtr->data = se;
	newNodePtr->nextPtr = NULL;
    }
    EventQnodePtr current = *headPtr, prev = NULL;

    //find the insert position in order of time
    while (current != NULL && se.time > (current->data).time) {	
	prev = current;
	current = current->nextPtr;
    }

    //then find the insert position in order of event's type
    while (current != NULL && se.time == (current->data).time 
	   && se.type < (current->data).type) {	
	prev = current;
	current = current->nextPtr;
    }

    if (prev == NULL) {
	newNodePtr->nextPtr = *headPtr;
	*headPtr = newNodePtr;
    } else {
	newNodePtr->nextPtr = prev->nextPtr;
	prev->nextPtr = newNodePtr;
    }
    if (newNodePtr->nextPtr == NULL) {
	*tailPtr = newNodePtr;
    }
}

Event dequeueevent(EventQnodePtr * headPtr, EventQnodePtr * tailPtr)
{
    Event value;
    EventQnodePtr tempPtr;
    value = (*headPtr)->data;
    tempPtr = *headPtr;
    *headPtr = (*headPtr)->nextPtr;
    if (*headPtr == NULL) {
	*tailPtr = NULL;
    }
    free(tempPtr);
    return value;
}

int isEmptyEQ(EventQnodePtr headPtr)
{
    return headPtr == NULL;
}

void displayEvents(EventQnodePtr currentPtr)
{
    if (currentPtr == NULL)
	printf("The event list is empty ...\n");
    else {
	printf("The event list is:\n");
	Event tempevent;
	while (currentPtr != NULL) {
	    printf("time: %d, type : %d : task(id:%d,bt:%d)\n",
		   (currentPtr->data).time, (currentPtr->data).type,
		   (currentPtr->data).task.id, (currentPtr->data).task.bt);
	    currentPtr = currentPtr->nextPtr;
	}
    }
}


///////////////////////////////////////////////

const int MAXTASKS = 10;
const int MAXBURSTTIME = 70;
const int IAT = 10;

void main()
{
    QnodePtr rqheadPtr = NULL, rqtailPtr = NULL;	//the run/ready queue
    EventQnodePtr eventsQheadPtr = NULL, eventsQtailPtr = NULL;	//the event queue/list

    Task task;			//the process structure
    Event event;		//the event structure
    int prevat = 0, i;		//set the previous arrival time to zero 

    //generate all arrivals and insert them in the event list
    for (i = 0; i < MAXTASKS; i++) {	
	//fill up the info of the process structure             
	task.id = i;
	task.at = rand() % IAT + prevat;
	prevat = task.at;
	task.bt = rand() % MAXBURSTTIME + 1;
	//fill up the info of the event structure
	event.type = 0;		//event type is 0:arrival
	event.time = task.at;	//event time
	event.task = task;	//note that the process is encapsulated in an event structure

	//insert the event in the events list
	enqueueevent(&eventsQheadPtr, &eventsQtailPtr, event);
    }
    //Display the events list of all arrivals before starting the simulation
    displayEvents(eventsQheadPtr);	

    int clock = 0;		//the sim clock time is currently 0
    int idle = 1;		//CPU is initially idle


    Event currentEvent;		//to hold the current event
    int wt = 0;			//waiting time
    int idletime = 0;		//CPU idel time
    printf("\nTime: %d: Simulation is started ...\n", clock);

    while (!isEmptyEQ(eventsQheadPtr)) {
	//get an event from the events list
	currentEvent = dequeueevent(&eventsQheadPtr, &eventsQtailPtr);	

	clock = currentEvent.time;
	if (currentEvent.type == 1) {	//Departure logic 
	    idle = 1;
	    task = currentEvent.task;
	    printf("Time: %d : Departure : task(id:%d,bt:%d) has finished.\n",
		 clock, task.id, task.bt);
	}
	if (currentEvent.type == 0) {	//Arrival logic
	    task = currentEvent.task;
	    printf("Time: %d : Arrival : task(id:%d,bt:%d).\n", clock,
		   task.id, task.bt);
	    enqueue(&rqheadPtr, &rqtailPtr, task);
	}

	if (idle == 1 && !isEmpty(rqheadPtr)) {	//Service logic 
	    idle = 0;
	    currentEvent.task = dequeue(&rqheadPtr, &rqtailPtr);

	    wt += clock - currentEvent.task.at;

	    currentEvent.type = 1;
	    currentEvent.time = clock + currentEvent.task.bt;

	    enqueueevent(&eventsQheadPtr, &eventsQtailPtr, currentEvent);

	    task = currentEvent.task;
	    printf("Time: %d : Serving : task(id:%d,bt:%d).\n", clock,
		   task.id, task.bt);
	}
    }

    printf("\nSimulation has finished.\n");
    printf("\nResults:\n");
    printf("Total tasks served = %d.\n", MAXTASKS);
    printf("Average wating time for all processes = %f\n",
	   (double) wt / MAXTASKS);
}
