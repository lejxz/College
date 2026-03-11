#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAX 100

int stringSearch(char text[], char pattern[]);

int main(void) {
	char text[MAX], pattern[MAX];
	
	printf("Enter a TEXT: ");
	fgets(text, MAX, stdin);
	text[strcspn(text, "\n")] = '\0';
	printf("Enter a PATTERN to seach in TEXT: ");
	fgets(pattern, MAX, stdin);
	pattern[strcspn(pattern, "\n")] = '\0';
	
	
	int result = stringSearch(text, pattern);
	
	if (result != -1)
		printf("String starts at index: %d", result);
	else
		printf("String Not Found!");
	
	return 0;
}


int stringSearch(char text[], char pattern[]) {	
	int i, j, tLen = strlen(text), pLen = strlen(pattern);
	for (i = 0; i <= tLen - pLen; i++) {
		for (j = 0; j < pLen; j++) {
			if(toupper(text[i + j]) != toupper(pattern[j]))
				break;
		}
		if (j == pLen) return i;
	}
	return -1;
}
