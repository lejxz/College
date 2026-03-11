#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAX 100

typedef struct points {
	int x, y;
}point;

float calcDistance(point p1, point p2);
int getInput(point[]);
void findClosest(point[], int);


int main(void) {
	point p[MAX];
	
	int n = getInput(p);
	
	for (int i = 0; i < n; i++)
		printf("\n(%d, %d)", p[i].x, p[i].y);
		
	findClosest(p, n);

	return 0;
}

int getInput(point p[]) {
	int n;
	printf("Enter number of points: ");
	scanf("%d", &n);
	
	for(int i = 0; i < n; i++) {
		printf("Enter x and y values(ex: x, y): ");
		scanf("%d, %d", &p[i].x, &p[i].y);
	}
	
	return n;
}

void findClosest(point p[], int n) {
	if (n < 2) {
		printf("Err: Only 1 set of points.");
		return;
	}
	float min = INT_MAX;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            float d = calcDistance(p[i], p[j]);
            if (d < min) min = d;
        }
    }
    printf("\nMinimum Distance: %f\n", min);
}

float calcDistance(point p1, point p2){
	return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
}

