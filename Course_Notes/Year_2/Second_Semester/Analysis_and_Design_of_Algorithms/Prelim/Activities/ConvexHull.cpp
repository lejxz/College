#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAX 100

typedef struct points {
	int x, y;
}point;

int getInput(point[]);
void convexHull(point[], int);

int main(void) {
	point p[MAX];
	
	int n = getInput(p);
	
	for (int i = 0; i < n; i++)
		printf("\n(%d, %d)", p[i].x, p[i].y);
		
	convexHull(p, n);
	
	return 0;
}


int getInput(point p[]) {
	int n;
	printf("Enter number of points: ");
	scanf("%d", &n);
	
	for (int i = 0; i < n; i++) {
		printf("Enter x and y values(ex: x, y): ");
		scanf("%d, %d", &p[i].x, &p[i].y);
	}
	
	return n;
}


// O(n^3) 
void convexHull(point p[], int n) {
	for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int positive = 0;
            int negative = 0;

            for (int k = 0; k < n; k++) {
                if (k == i || k == j) continue;

                int val = ((p[j].x - p[i].x) * (p[k].y - p[i].y)) - 
                          ((p[j].y - p[i].y) * (p[k].x - p[i].x));

                if (val > 0) positive++;
                if (val < 0) negative++;
            }
            if (positive > 0 && negative > 0)
                printf("\nNot a convex hull: (%d,%d) to (%d,%d)", p[i].x, p[i].y, p[j].x, p[j].y);
            else
                printf("\nBoundary Edge: (%d,%d) to (%d,%d)", p[i].x, p[i].y, p[j].x, p[j].y);
        }
    }
}
