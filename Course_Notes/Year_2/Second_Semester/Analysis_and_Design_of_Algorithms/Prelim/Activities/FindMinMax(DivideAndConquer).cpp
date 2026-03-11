#include <stdio.h>

#define MAX 100

struct pair {
    int min, max;
};

struct pair findMinMaxDC(int arr[], int low, int high);


int main (void) {
    int count, arr[MAX];
    struct pair result;

    printf("How many num to enter: ");
    scanf("%d", &count);

    printf("Enter %d numbers: ", count);

    for (int i = 0; i < count; i++)
        scanf("%d", &arr[i]);

    result = findMinMaxDC(arr, 0, count - 1);

    printf("Divide & Conquer - Max: %d, Min: %d\n", result.max, result.min);

    return 0;
}

struct pair findMinMaxDC(int arr[], int low, int high){
    struct pair result, left, right;
    int mid;

    //only one
    if (low == high) {
        result.min = arr[low];
        result.max = arr[low];
        return result;
    }

    //only two
    if (high == low + 1) {
        if (arr[low] < arr[high]) {
            result.min = arr[low];
            result.max = arr[high];
        } else {
            result.min = arr[high];
            result.max = arr[low];
        }
        return result;
    }

    //More than two
    mid = low + (high - low) / 2;
    left = findMinMaxDC(arr, low, mid);
    right = findMinMaxDC(arr, mid + 1, high);

    //combine
    result.min = (left.min < right.min) ? left.min : right.min;
    result.max = (left.max > right.max) ? left.max : right.max;

    return result;
}