# Algorithm Design Techniques — Laboratory Reference

---

## Table of Contents

1. [Decrease and Conquer](#decrease-and-conquer)
   - [Josephus Problem](#josephus-problem)
   - [Generating Permutations](#generating-permutations)
     - [Trotter's Algorithm](#trotters-algorithm)
     - [Bottom-Up (Heap's Algorithm)](#bottom-up-heaps-algorithm)
2. [Transform and Conquer](#transform-and-conquer)
   - [Gaussian Elimination](#gaussian-elimination)
   - [Horner's Rule](#horners-rule)
   - [Binary Exponentiation](#binary-exponentiation)
   - [Counting Paths in a Graph](#counting-paths-in-a-graph)

---

# Decrease and Conquer

**Definition:**
Decrease and Conquer reduces a problem into a **smaller instance of the same problem**, solves the smaller instance, and uses that result to solve the original.

**Three variants:**
- **Decrease by a constant** — reduce size by 1 each time
- **Decrease by a constant factor** — reduce size by half each time
- **Variable-size decrease** — reduction varies per step

---

## Josephus Problem

### Summary

`n` people stand in a circle. Starting from position 1, every `k`-th person is eliminated until only one remains. The goal is to determine the **safe position** — the last survivor's index.

### Key Concept

This problem uses **decrease-by-one** recursion. The position of the survivor can be computed without simulating the entire elimination.

**Recursive Formula (k = 2):**
```
J(1) = 0
J(n) = (J(n - 1) + 2) % n
```
> Note: This yields a 0-based index. Add 1 for the 1-based position.

### Full C Program

```c
#include <stdio.h>

/*
 * josephus()
 * Recursively computes the 0-based index of the survivor.
 * Parameters:
 *   n - number of people in the circle
 *   k - every k-th person is eliminated (fixed at 2 here)
 * Returns:
 *   0-based safe position
 */
int josephus(int n, int k) {
    if (n == 1)
        return 0;
    return (josephus(n - 1, k) + k) % n;
}

int main(void) {
    int n, k;

    printf("=== Josephus Problem ===\n");
    printf("Enter number of people (n): ");
    scanf("%d", &n);
    printf("Enter step count (k): ");
    scanf("%d", &k);

    int safe_pos = josephus(n, k) + 1;  /* Convert to 1-based index */
    printf("\nWith n = %d people and k = %d step, the safe position is: %d\n",
           n, k, safe_pos);

    return 0;
}
```

### Sample Output

```
=== Josephus Problem ===
Enter number of people (n): 7
Enter step count (k): 2

With n = 7 people and k = 2 step, the safe position is: 7
```

### Trace (n=7, k=2)

| Call       | Return Value         |
|------------|----------------------|
| J(1)       | 0                    |
| J(2)       | (0 + 2) % 2 = 0     |
| J(3)       | (0 + 2) % 3 = 2     |
| J(4)       | (2 + 2) % 4 = 0     |
| J(5)       | (0 + 2) % 5 = 2     |
| J(6)       | (2 + 2) % 6 = 4     |
| J(7)       | (4 + 2) % 7 = **6** |

Result: 6 + 1 = **Position 7**

---

## Generating Permutations

### Summary

A permutation is an **arrangement** of all elements in a set. For `n` elements, there are `n!` permutations. The two algorithms below generate all permutations using different strategies.

---

### Trotter's Algorithm

#### Key Concept

Trotter's (Johnson-Trotter) algorithm generates permutations such that **each successive permutation differs by exactly one adjacent swap**. Each element is assigned a direction (left or right). The **largest mobile element** (one whose neighbor in its direction is smaller) is moved one step in its direction per iteration.

A **mobile element** is an element that is:
- Pointing left, and the element to its left is smaller, OR
- Pointing right, and the element to its right is smaller.

#### Full C Program

```c
#include <stdio.h>

#define MAX 8

int arr[MAX];       /* Current permutation */
int dir[MAX];       /* Direction: -1 = left, +1 = right */
int n;

/*
 * print_perm()
 * Prints the current permutation array.
 */
void print_perm(void) {
    for (int i = 0; i < n; i++)
        printf("%d ", arr[i]);
    printf("\n");
}

/*
 * find_mobile()
 * Finds the index of the largest mobile element.
 * Returns -1 if no mobile element exists (generation is complete).
 */
int find_mobile(void) {
    int mobile_idx = -1;

    for (int i = 0; i < n; i++) {
        int neighbor = i + dir[i];

        /* Check if neighbor index is within bounds */
        if (neighbor < 0 || neighbor >= n)
            continue;

        /* Element is mobile if its neighbor in its direction is smaller */
        if (arr[i] > arr[neighbor]) {
            if (mobile_idx == -1 || arr[i] > arr[mobile_idx])
                mobile_idx = i;
        }
    }

    return mobile_idx;
}

/*
 * trotter()
 * Generates all n! permutations using Trotter's (Johnson-Trotter) algorithm.
 * Starts from the identity permutation {1, 2, ..., n}.
 */
void trotter(void) {
    int count = 1;

    /* Initialize: identity permutation, all directions pointing left */
    for (int i = 0; i < n; i++) {
        arr[i] = i + 1;
        dir[i] = -1;   /* All elements start pointing left */
    }

    printf("Permutation %d: ", count++);
    print_perm();

    int mobile;
    while ((mobile = find_mobile()) != -1) {
        int neighbor = mobile + dir[mobile];

        /* Swap mobile element with its neighbor in its direction */
        int tmp = arr[mobile];
        arr[mobile] = arr[neighbor];
        arr[neighbor] = tmp;

        /* Also swap their directions */
        int tmp_dir = dir[mobile];
        dir[mobile] = dir[neighbor];
        dir[neighbor] = tmp_dir;

        /* Reverse direction of all elements larger than the moved element */
        int moved_val = arr[neighbor];
        for (int i = 0; i < n; i++) {
            if (arr[i] > moved_val)
                dir[i] = -dir[i];
        }

        printf("Permutation %d: ", count++);
        print_perm();
    }
}

int main(void) {
    printf("=== Trotter's Algorithm (Johnson-Trotter) ===\n");
    printf("Enter number of elements (n <= %d): ", MAX);
    scanf("%d", &n);
    printf("\nAll permutations of {1 .. %d}:\n\n", n);

    trotter();

    return 0;
}
```

#### Sample Output (n = 3)

```
=== Trotter's Algorithm (Johnson-Trotter) ===
Enter number of elements (n <= 8): 3

All permutations of {1 .. 3}:

Permutation 1: 1 2 3
Permutation 2: 1 3 2
Permutation 3: 3 1 2
Permutation 4: 3 2 1
Permutation 5: 2 3 1
Permutation 6: 2 1 3
```

---

### Bottom-Up (Heap's Algorithm)

#### Key Concept

Heap's Algorithm generates all permutations by performing a **minimum number of swaps**. It works bottom-up: generate all permutations of `n-1` elements, then insert the `n`-th element into each.

**Swap rule:**
- If `n` is **even**: swap `arr[i]` and `arr[n-1]`
- If `n` is **odd**: swap `arr[0]` and `arr[n-1]`

#### Full C Program

```c
#include <stdio.h>

#define MAX 8

int arr[MAX];
int n;
int perm_count;

/*
 * print_perm()
 * Prints the current permutation.
 */
void print_perm(void) {
    printf("Permutation %d: ", ++perm_count);
    for (int i = 0; i < n; i++)
        printf("%d ", arr[i]);
    printf("\n");
}

/*
 * swap()
 * Swaps two integer values by reference.
 */
void swap(int *a, int *b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

/*
 * heap_generate()
 * Recursively generates all permutations using Heap's algorithm.
 * Parameters:
 *   size - the size of the current sub-array being permuted
 */
void heap_generate(int size) {
    if (size == 1) {
        print_perm();
        return;
    }

    for (int i = 0; i < size; i++) {
        heap_generate(size - 1);

        /* Determine which elements to swap based on parity */
        if (size % 2 == 0)
            swap(&arr[i], &arr[size - 1]);   /* Even: swap arr[i] and arr[size-1] */
        else
            swap(&arr[0], &arr[size - 1]);   /* Odd:  swap arr[0] and arr[size-1] */
    }
}

int main(void) {
    printf("=== Bottom-Up Permutation (Heap's Algorithm) ===\n");
    printf("Enter number of elements (n <= %d): ", MAX);
    scanf("%d", &n);

    for (int i = 0; i < n; i++)
        arr[i] = i + 1;

    perm_count = 0;
    printf("\nAll permutations of {1 .. %d}:\n\n", n);

    heap_generate(n);

    printf("\nTotal permutations generated: %d\n", perm_count);

    return 0;
}
```

#### Sample Output (n = 3)

```
=== Bottom-Up Permutation (Heap's Algorithm) ===
Enter number of elements (n <= 8): 3

All permutations of {1 .. 3}:

Permutation 1: 1 2 3
Permutation 2: 2 1 3
Permutation 3: 3 1 2
Permutation 4: 1 3 2
Permutation 5: 2 3 1
Permutation 6: 3 2 1

Total permutations generated: 6
```

---

#### Comparison: Trotter's vs. Heap's

| Property                  | Trotter's (Johnson-Trotter) | Heap's Algorithm      |
|---------------------------|-----------------------------|-----------------------|
| Swap type                 | Always adjacent             | Not always adjacent   |
| Direction tracking needed | Yes                         | No                    |
| Total swaps               | n! - 1                      | n! - 1                |
| Output order              | Minimal adjacent changes    | Minimal total swaps   |
| Implementation complexity | Moderate                    | Simple (recursive)    |

---

# Transform and Conquer

**Definition:**
Transform and Conquer **transforms** the problem into a different, easier form, solves it in that form, and then translates the result back.

**Three variants:**
- **Instance simplification** — transform to a simpler instance (e.g., presorting)
- **Representation change** — transform to a different data structure (e.g., heaps)
- **Problem reduction** — transform to a completely different problem

---

## Gaussian Elimination

### Summary

Gaussian Elimination solves a system of `n` linear equations in `n` unknowns by transforming the augmented matrix into **upper triangular form** (forward elimination), then solving via **back substitution**.

### Key Steps

1. **Forward Elimination**: For each pivot row `k`, eliminate the variable `x[k]` from all rows below it.
2. **Back Substitution**: Starting from the last equation, solve for each variable upward.

### Full C Program

```c
#include <stdio.h>
#include <math.h>

#define MAX 10
#define EPSILON 1e-9

/*
 * gaussian_elimination()
 * Solves Ax = b using Gaussian elimination with partial pivoting.
 * Parameters:
 *   n   - number of equations (and unknowns)
 *   mat - augmented matrix [A | b] of size n x (n+1)
 *   x   - output array where the solution is stored
 * Returns:
 *   1 on success, 0 if the system has no unique solution
 */
int gaussian_elimination(int n, double mat[MAX][MAX + 1], double x[MAX]) {
    /* === Forward Elimination === */
    for (int k = 0; k < n; k++) {

        /* Partial Pivoting: find the row with the largest absolute value in column k */
        int max_row = k;
        for (int i = k + 1; i < n; i++) {
            if (fabs(mat[i][k]) > fabs(mat[max_row][k]))
                max_row = i;
        }

        /* Swap the pivot row with the current row k */
        for (int j = 0; j <= n; j++) {
            double tmp = mat[k][j];
            mat[k][j] = mat[max_row][j];
            mat[max_row][j] = tmp;
        }

        /* Check for a singular (no unique solution) matrix */
        if (fabs(mat[k][k]) < EPSILON) {
            printf("Error: Matrix is singular. No unique solution exists.\n");
            return 0;
        }

        /* Eliminate column k from all rows below row k */
        for (int i = k + 1; i < n; i++) {
            double factor = mat[i][k] / mat[k][k];
            for (int j = k; j <= n; j++)
                mat[i][j] -= factor * mat[k][j];
        }
    }

    /* === Back Substitution === */
    for (int i = n - 1; i >= 0; i--) {
        x[i] = mat[i][n];
        for (int j = i + 1; j < n; j++)
            x[i] -= mat[i][j] * x[j];
        x[i] /= mat[i][i];
    }

    return 1;
}

int main(void) {
    int n;
    double mat[MAX][MAX + 1];
    double x[MAX];

    printf("=== Gaussian Elimination ===\n");
    printf("Enter number of equations (n): ");
    scanf("%d", &n);

    printf("Enter augmented matrix [A | b] row by row (%d values per row):\n", n + 1);
    for (int i = 0; i < n; i++) {
        printf("Row %d: ", i + 1);
        for (int j = 0; j <= n; j++)
            scanf("%lf", &mat[i][j]);
    }

    if (gaussian_elimination(n, mat, x)) {
        printf("\nSolution:\n");
        for (int i = 0; i < n; i++)
            printf("  x[%d] = %.6f\n", i + 1, x[i]);
    }

    return 0;
}
```

### Sample Run

For the system:
```
2x + y - z = 8
-3x - y + 2z = -11
-2x + y + 2z = -3
```

Input (augmented matrix):
```
2  1 -1  8
-3 -1  2 -11
-2  1  2 -3
```

Output:
```
Solution:
  x[1] = 2.000000
  x[2] = 3.000000
  x[3] = -1.000000
```

---

## Horner's Rule

### Summary

Horner's Rule evaluates a polynomial at a given value of `x` by **rewriting the polynomial** to avoid redundant multiplications.

**Standard form:**
```
P(x) = a_n*x^n + a_(n-1)*x^(n-1) + ... + a_1*x + a_0
```

**Horner's nested form:**
```
P(x) = (...((a_n * x + a_(n-1)) * x + a_(n-2)) * x + ... + a_1) * x + a_0
```

**Complexity:**
- Standard: O(n²) multiplications
- Horner's:  O(n)  multiplications — a major improvement

### Full C Program

```c
#include <stdio.h>

#define MAX_DEGREE 20

/*
 * horner()
 * Evaluates polynomial P(x) using Horner's nested multiplication.
 * Parameters:
 *   coeff[] - coefficients from highest degree to lowest (coeff[0] = a_n)
 *   degree  - degree of the polynomial
 *   x       - the value at which to evaluate the polynomial
 * Returns:
 *   The computed value P(x)
 */
double horner(double coeff[], int degree, double x) {
    double result = coeff[0];   /* Start with the leading coefficient */

    for (int i = 1; i <= degree; i++)
        result = result * x + coeff[i];

    return result;
}

int main(void) {
    int degree;
    double coeff[MAX_DEGREE + 1];
    double x;

    printf("=== Horner's Rule — Polynomial Evaluation ===\n");
    printf("Enter polynomial degree: ");
    scanf("%d", &degree);

    printf("Enter coefficients from highest degree to lowest (%d values):\n", degree + 1);
    for (int i = 0; i <= degree; i++) {
        printf("  a[%d] (x^%d): ", i, degree - i);
        scanf("%lf", &coeff[i]);
    }

    printf("Enter x: ");
    scanf("%lf", &x);

    double result = horner(coeff, degree, x);

    printf("\nP(%.4f) = %.6f\n", x, result);

    return 0;
}
```

### Sample Run

For `P(x) = 2x^3 - 6x^2 + 2x - 1` at `x = 3`:

Input:
```
Degree: 3
Coefficients: 2  -6  2  -1
x: 3
```

Output:
```
P(3.0000) = 5.000000
```

**Manual Trace:**
```
result = 2
result = 2 * 3 + (-6) = 0
result = 0 * 3 +   2  = 2
result = 2 * 3 + (-1) = 5   ✓
```

---

## Binary Exponentiation

### Summary

Binary Exponentiation (also called "Exponentiation by Squaring") computes `base^exp` in **O(log n)** time by exploiting the binary representation of the exponent.

**Key recurrences:**
```
power(b, 0) = 1
power(b, n) = power(b, n/2)^2          if n is even
power(b, n) = b * power(b, n/2)^2      if n is odd
```

**Why it is efficient:**
Each recursive call halves the exponent, so only O(log n) multiplications are performed instead of O(n).

### Full C Program

```c
#include <stdio.h>

/*
 * power_recursive()
 * Computes base^exp using recursive binary exponentiation.
 * Parameters:
 *   base - the base value
 *   exp  - the non-negative integer exponent
 * Returns:
 *   base raised to the power exp
 */
long long power_recursive(long long base, int exp) {
    if (exp == 0)
        return 1;
    if (exp % 2 == 0) {
        long long half = power_recursive(base, exp / 2);
        return half * half;
    } else {
        long long half = power_recursive(base, exp / 2);
        return base * half * half;
    }
}

/*
 * power_iterative()
 * Computes base^exp using iterative binary exponentiation.
 * Reads the binary bits of exp from LSB to MSB.
 * Parameters:
 *   base - the base value
 *   exp  - the non-negative integer exponent
 * Returns:
 *   base raised to the power exp
 */
long long power_iterative(long long base, int exp) {
    long long result = 1;

    while (exp > 0) {
        if (exp % 2 == 1)       /* If current bit is 1, multiply result by base */
            result *= base;
        base *= base;           /* Square the base for the next bit */
        exp /= 2;               /* Move to the next bit */
    }

    return result;
}

int main(void) {
    long long base;
    int exp;

    printf("=== Binary Exponentiation ===\n");
    printf("Enter base: ");
    scanf("%lld", &base);
    printf("Enter exponent (non-negative integer): ");
    scanf("%d", &exp);

    long long result_r = power_recursive(base, exp);
    long long result_i = power_iterative(base, exp);

    printf("\n%lld^%d = %lld  (recursive)\n", base, exp, result_r);
    printf("%lld^%d = %lld  (iterative)\n", base, exp, result_i);

    return 0;
}
```

### Sample Output

```
=== Binary Exponentiation ===
Enter base: 2
Enter exponent (non-negative integer): 10

2^10 = 1024  (recursive)
2^10 = 1024  (iterative)
```

**Trace (2^10):**
```
exp=10 (even) → half = power(2,5);   result = half * half
exp=5  (odd)  → half = power(2,2);   result = 2 * half * half
exp=2  (even) → half = power(2,1);   result = half * half
exp=1  (odd)  → half = power(2,0);   result = 2 * half * half
exp=0         → return 1
```

---

## Counting Paths in a Graph

### Summary

Given a directed graph with `n` vertices and an adjacency matrix `A`, the number of paths of **exactly length `k`** between any two vertices `i` and `j` is given by the `(i, j)` entry of `A^k` (matrix raised to the `k`-th power).

**Transformation applied:**
The graph problem is transformed into a **matrix exponentiation** problem, which can itself be solved using binary exponentiation.

### Full C Program

```c
#include <stdio.h>
#include <string.h>

#define MAX_V 10

int n;  /* Number of vertices */

/*
 * mat_multiply()
 * Multiplies two n x n matrices A and B, storing the result in C.
 * Parameters:
 *   A, B - input matrices
 *   C    - output matrix (result of A * B)
 */
void mat_multiply(int A[MAX_V][MAX_V], int B[MAX_V][MAX_V],
                  int C[MAX_V][MAX_V]) {
    int tmp[MAX_V][MAX_V];
    memset(tmp, 0, sizeof(tmp));

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            for (int k = 0; k < n; k++)
                tmp[i][j] += A[i][k] * B[k][j];

    /* Copy result to C (allows C to be one of A or B) */
    memcpy(C, tmp, sizeof(tmp));
}

/*
 * mat_power()
 * Raises matrix A to the power p using binary exponentiation.
 * Stores the result in the result matrix.
 * Parameters:
 *   A      - the adjacency matrix
 *   p      - the exponent (path length)
 *   result - output matrix A^p
 */
void mat_power(int A[MAX_V][MAX_V], int p, int result[MAX_V][MAX_V]) {
    int base[MAX_V][MAX_V];

    /* Initialize result as the identity matrix */
    memset(result, 0, sizeof(int) * MAX_V * MAX_V);
    for (int i = 0; i < n; i++)
        result[i][i] = 1;

    memcpy(base, A, sizeof(base));

    while (p > 0) {
        if (p % 2 == 1)
            mat_multiply(result, base, result);
        mat_multiply(base, base, base);
        p /= 2;
    }
}

/*
 * print_matrix()
 * Prints an n x n integer matrix with a label.
 */
void print_matrix(const char *label, int M[MAX_V][MAX_V]) {
    printf("%s\n", label);
    for (int i = 0; i < n; i++) {
        printf("  ");
        for (int j = 0; j < n; j++)
            printf("%4d", M[i][j]);
        printf("\n");
    }
    printf("\n");
}

int main(void) {
    int adj[MAX_V][MAX_V];
    int result[MAX_V][MAX_V];
    int k;

    printf("=== Counting Paths in a Graph (Matrix Exponentiation) ===\n");
    printf("Enter number of vertices (n): ");
    scanf("%d", &n);

    printf("Enter adjacency matrix (%d x %d), row by row (0 or 1):\n", n, n);
    for (int i = 0; i < n; i++) {
        printf("Row %d: ", i + 1);
        for (int j = 0; j < n; j++)
            scanf("%d", &adj[i][j]);
    }

    printf("Enter path length (k): ");
    scanf("%d", &k);

    print_matrix("Adjacency Matrix A:", adj);

    mat_power(adj, k, result);

    printf("A^%d (paths of length %d):\n", k, k);
    print_matrix("", result);

    printf("Paths of length %d between each pair of vertices:\n\n", k);
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            if (result[i][j] > 0)
                printf("  Vertex %d -> Vertex %d : %d path(s)\n",
                       i + 1, j + 1, result[i][j]);

    return 0;
}
```

### Sample Run

Directed graph with 4 vertices:

```
Adjacency Matrix:
  0 1 0 0
  0 0 1 0
  0 0 0 1
  1 0 0 0
```
*(A cycle: 1→2→3→4→1)*

Input:
```
n = 4
k = 4
```

Output:
```
A^4 (paths of length 4):
  1 0 0 0
  0 1 0 0
  0 0 1 0
  0 0 0 1

Paths of length 4 between each pair of vertices:

  Vertex 1 -> Vertex 1 : 1 path(s)
  Vertex 2 -> Vertex 2 : 1 path(s)
  Vertex 3 -> Vertex 3 : 1 path(s)
  Vertex 4 -> Vertex 4 : 1 path(s)
```

> Interpretation: After 4 steps in a 4-cycle, each vertex returns to itself exactly once — which is correct.

---

## Quick Reference Summary

| Topic                    | Strategy                    | Time Complexity      |
|--------------------------|-----------------------------|----------------------|
| Josephus Problem         | Decrease by 1 (recursion)   | O(n)                 |
| Trotter's Permutations   | Decrease by 1 (mobile elem) | O(n · n!)            |
| Heap's Permutations      | Decrease by 1 (bottom-up)   | O(n · n!)            |
| Gaussian Elimination     | Instance simplification     | O(n³)                |
| Horner's Rule            | Representation change       | O(n)                 |
| Binary Exponentiation    | Problem reduction           | O(log n)             |
| Counting Paths           | Problem reduction (matrix)  | O(n³ · log k)        |

---

*End of Laboratory Reference*
