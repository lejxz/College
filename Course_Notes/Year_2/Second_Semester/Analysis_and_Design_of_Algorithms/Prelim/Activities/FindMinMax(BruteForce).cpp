#include <stdio.h>

#define MAX 100

void findMinMaxBruteForce(int arr[], int n);

int main(void) {
    int count, arr[MAX];

    printf("How many num to enter: ");
    scanf("%d", &count);

    printf("Enter %d numbers: ", count);

    for (int i = 0; i < count; i++)
        scanf("%d", &arr[i]);

    findMinMaxBruteForce(arr, count);

    return 0;
}


void findMinMaxBruteForce(int arr[], int n){
    int min = arr[0];
    int max = arr[0];

    for (int i = 0; i < n; i++)
        if (min > arr[i])
            min = arr[i];
        else if (max < arr[i])
            max = arr[i];
    

    printf("Brute Force - Max: %d, Min: %d\n", max, min);
}