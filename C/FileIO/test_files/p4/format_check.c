#include <stdio.h>

void exampleFunc() {
    int arr[5] = {1, 2, 3, 4, 5};

    for (int i = 0; i < 5; i++) {
        if (arr[i] > 0) {
            printf("Element %d is positive.\n", arr[i]);
        }
    }

    char test = 'c';
 // <-- Missing curly brace
