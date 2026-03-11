#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAX 100

int stringSearch(char [], char [], int []);

int main(void) {
	char text[MAX], pattern[MAX];
	int indexes[MAX];
	
	printf("Enter a TEXT: ");
	fgets(text, MAX, stdin);
	text[strcspn(text, "\n")] = '\0';
	printf("Enter a PATTERN to seach in TEXT: ");
	fgets(pattern, MAX, stdin);
	pattern[strcspn(pattern, "\n")] = '\0';
	
	
	int count = stringSearch(text, pattern, indexes);
	
    if (count > 0) {
        printf("Pattern found %d time(s) at indices: ", count);
        for (int i = 0; i < count; i++) {
            printf("\n%d ", indexes[i]);
        }
        printf("\n");
    } else {
        printf("Pattern Not Found!\n");
    }
	
	return 0;
}


int stringSearch(char text[], char pattern[], int index[]) {	
	int i, j, k = 0, flag, tLen = strlen(text), pLen = strlen(pattern);
	for (i = 0; i <= tLen - pLen; i++) {
		flag = 1;
		for (j = 0; j < pLen; j++) {
			if (toupper(text[i + j]) != toupper(pattern[j])){
				flag = 0;
				break;
			}
			flag = 1;
		}
		if(flag){
			index[k++] = i;
		}
	}
	return k;
}
