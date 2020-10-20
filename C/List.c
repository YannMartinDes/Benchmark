#include <stdlib.h>

typedef struct EltList{
    struct EltList* next;
    struct EltList* prev;
    int val;
} EltList;


typedef struct List {
    EltList* sentinel;

} List;

void initList(List* list) {
    EltList* elt = (EltList*)malloc(sizeof(EltList));
    elt->next = elt;
    elt->prev = elt;
    list->sentinel = elt;
}

List* createList() {
    List* list = (List*)malloc(sizeof(List));
    initList(list);
    return list;

}

int last(List* list) {
    return list->sentinel->prev->val;
}

void appendLast(List* list, int val) {
    EltList* elt = (EltList*)malloc(sizeof(EltList));
    EltList* sentinel = list->sentinel;
    elt->val = val;
    elt->prev = sentinel->prev;
    elt->next = sentinel;
    sentinel->prev->next = elt;
    sentinel->prev = elt;
}

void removeLast(List* list) {
    EltList* sentinel = list->sentinel;
    EltList* suppElt = sentinel->prev;
    suppElt->prev->next = suppElt->next;
    suppElt->next->prev = suppElt->prev;
    free(suppElt);
}

typedef struct StackList {
    List list;
} StackList;

struct StackList* createStackList() {
    StackList* stack = (StackList*)malloc(sizeof(StackList));
    initList(&(stack->list));
    return stack;
}

void push(StackList* stack, int val) {
    appendLast(&(stack->list), val);
}

void pop(StackList* stack) {
    removeLast(&(stack->list));
}
int top(StackList* stack) {
    return last(&(stack->list));
}

#include <stdio.h>
#include <sys\timeb.h> 

void main() {
    struct StackList stack;
    initList(&stack);

    struct timeb start, end;
    ftime(&start);
    for (int i = 0; i < 1000; i++) {
        push(&stack, i);
    }
    for (int i = 0; i < 1000; i++) {
        printf("top=%d", top(&stack));
        pop(&stack);
    }
    ftime(&end);
    long diff = (long)(1000.0 * (end.time - start.time)
        + (end.millitm - start.millitm));

    printf("\ntime=%lu ms\n", diff);
}