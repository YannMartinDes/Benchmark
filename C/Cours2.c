#include <stdlib.h>
#include <stdio.h>

//
// List
//

struct EltList {
	struct EltList* next;
	struct EltList* prev;
	int val;
};

//
// Pool of Object
//

const int POOLSIZE = 1000000;
const int NBOP = 100;

struct EltListPool {
	struct EltList** arr;
	int top;
};

void initPool(struct EltListPool* pool) {
	pool->arr = calloc(POOLSIZE, sizeof(struct EltList*));
	struct EltList* tmparr = calloc(POOLSIZE, sizeof(struct EltList));
	for (int i = 0; i < POOLSIZE; i++) {
		pool->arr[i] = &tmparr[i];
	}
	pool->top = POOLSIZE;
}

struct EltList* allocate(struct EltListPool* pool) {
	pool->top--;
	return pool->arr[pool->top];
}

void deallocate(struct EltListPool* pool, struct EltList* elt) {
	pool->arr[pool->top] = elt;
	pool->top++;
}

struct List {
	struct EltList* sentinel;
};

void initList(struct List* list) {
	struct EltList* elt = malloc(sizeof(struct EltList));
	elt->next = elt;
	elt->prev = elt;
	list->sentinel = elt;
}
struct List* createList() {
	struct List* list = malloc(sizeof(struct List));
	initList(list);
	return list;
}

int last(struct List* list) {
	return list->sentinel->prev->val;
}

void appendLast(struct List* list, int val) {
	struct EltList* elt = malloc(sizeof(struct EltList));
	struct EltList* sentinel = list->sentinel;
	elt->val = val;
	elt->prev = sentinel->prev;
	elt->next = sentinel;
	sentinel->prev->next = elt;
	sentinel->prev = elt;
}

void removeLast(struct List* list) {
	struct EltList* sentinel = list->sentinel;
	struct EltList* suppElt = sentinel->prev;
	suppElt->prev->next = suppElt->next;
	suppElt->next->prev = suppElt->prev;
	free(suppElt);
}

void appendLastWithPool(struct EltListPool* pool, struct List* list, int val) {
	struct EltList* elt = allocate(pool);
	struct EltList* sentinel = list->sentinel;
	elt->val = val;
	elt->prev = sentinel->prev;
	elt->next = sentinel;
	sentinel->prev->next = elt;
	sentinel->prev = elt;
}

void removeLastWithPool(struct EltListPool* pool, struct List* list) {
	struct EltList* sentinel = list->sentinel;
	struct EltList* suppElt = sentinel->prev;
	suppElt->prev->next = suppElt->next;
	suppElt->next->prev = suppElt->prev;
	deallocate(pool, suppElt);
}

//
// StackList
//

struct StackList {
	struct List list;
};

struct StackList* createStackList() {
	struct StackList* stack = malloc(sizeof(struct StackList));
	initList(&(stack->list));
	return stack;
}

void initStack(struct StackList* stack) {
	initList(&(stack->list));
}
void push(struct StackList* stack, int val) {
	appendLast(&(stack->list), val);
}
void pop(struct StackList* stack) {
	removeLast(&(stack->list));
}
int top(struct StackList* stack) {
	return last(&(stack->list));
}


//
// Stack with Pool of Object
//

void pushWithPool(struct EltListPool* pool, struct StackList* stack, int val) {
	appendLastWithPool(pool, &(stack->list), val);
}
void popWithPool(struct EltListPool* pool, struct StackList* stack) {
	removeLastWithPool(pool, &(stack->list));
}
int topWithPool(struct StackList* stack) {
	return last(&(stack->list));
}

//
// main
// 

#include <stdio.h>
#include <sys\timeb.h> 

void main() {
	struct StackList stack;
	initStack(&stack);

	long diff;
	struct timeb start, end;
	ftime(&start);
	for (int k = 0; k < NBOP;k++) {
		for (int i = 0; i < POOLSIZE; i++) {
			push(&stack, i);
		}
		for (int i = 0; i < POOLSIZE; i++) {
			//		printf("top=%d", top(&stack));
			pop(&stack);
		}
	}
	ftime(&end);
	diff = (long)(1000.0 * (end.time - start.time)
		+ (end.millitm - start.millitm));

	printf("\n%d operation of %d push/pop without pool time=%lu ms\n", NBOP, POOLSIZE, diff);

	struct StackList stackWithPool;
	initStack(&stackWithPool);
	struct EltListPool* pool = malloc(sizeof(struct EltListPool));
	initPool(pool);

	ftime(&start);
	for (int k = 0; k < NBOP;k++) {
		for (int i = 0; i < POOLSIZE; i++) {
			pushWithPool(pool, &stackWithPool, i);
		}
		for (int i = 0; i < POOLSIZE; i++) {
			//		printf("top=%d", top(&stack));
			popWithPool(pool, &stackWithPool);
		}
	}
	ftime(&end);
	diff = (long)(1000.0 * (end.time - start.time)
		+ (end.millitm - start.millitm));

	printf("\n%d operation of %d push/pop with pool time=%lu ms\n", NBOP, POOLSIZE, diff);
	getchar();
}
