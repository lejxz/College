# Transform and Conquer

## 📋 Summary
* **Core Concept:** Transform and Conquer is an algorithm design strategy that solves a problem by first transforming it into a simpler or more convenient form, then solving that transformed version.

> **Takeaways:** The key insight is that the original problem may be difficult to solve directly, but a transformed version (via instance simplification, representation change, or problem reduction) may be significantly easier to handle.


## 📖 Definition

* **Transform and Conquer:** A design paradigm where a problem is first modified into a more tractable form before being solved.
* **Instance Simplification:** A transformation where the input is preprocessed (e.g., sorted) to make the core algorithm more efficient.
* **Representation Change:** A transformation where the data structure representing the input is changed to one more suitable for the algorithm (e.g., array → heap).
* **Problem Reduction:** A transformation where the original problem is converted into a different, well-known problem whose solution is already known.
* **Requirements:**
    * A well-defined mapping from the original problem to the transformed problem.
    * The transformation cost must not dominate the overall complexity.


## 📊 Complexity Analysis

| Notation | Name | Growth Rate |
| :--- | :--- | :--- |
| $O(1)$ | Constant | Excellent |
| $O(\log n)$ | Logarithmic | Very Good |
| $O(n \log n)$ | Linearithmic | Good |
| $O(n^2)$ | Quadratic | Poor |

* **Worst-Case ($O$):** Depends on the specific algorithm used after transformation; transformation itself is typically $O(n \log n)$ if sorting is involved.
* **Best-Case ($\Omega$):** $\Omega(n)$ if the transformation requires at least one pass through the input.
* **Average-Case ($\Theta$):** $\Theta(n \log n)$ for sort-based transformations.


## ❓ Why We Use It

* **Simplifies hard problems:** Transforming an instance (e.g., pre-sorting) can reduce repeated work in subsequent steps.
* **Enables reuse of known algorithms:** Problem reduction allows leveraging existing, well-optimized solutions.
* **Improves efficiency:** A representation change (e.g., using a heap or AVL tree) can convert an $O(n^2)$ solution into an $O(n \log n)$ one.


## ⚙️ How It Works

1. **Step 1 — Identify the difficulty** in solving the problem in its original form.
2. **Step 2 — Choose a transformation type:**
   - *Instance Simplification* — preprocess (e.g., sort) the input.
   - *Representation Change* — convert to a different data structure.
   - *Problem Reduction* — map to a known solvable problem.
3. **Step 3 — Apply the transformation** and solve the new form.
4. **Step 4 — Map the solution back** to the original problem if necessary.
5. **Step 5 — Analyze total cost:**
   $$T_{\text{total}}(n) = T_{\text{transform}}(n) + T_{\text{solve}}(n)$$


## 💻 Usage / Example

```c
#include <stdio.h>
#include <stdlib.h>

/* Comparator for qsort */
int cmp(const void *a, const void *b) {
    return (*(int *)a - *(int *)b);
}

/* Instance Simplification: sort first, then check adjacent elements */
int has_duplicates(int arr[], int n) {
    int *sorted = malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) sorted[i] = arr[i];

    /* Transform: sort the array — O(n log n) */
    qsort(sorted, n, sizeof(int), cmp);

    /* Conquer: check adjacent elements — O(n) */
    int found = 0;
    for (int i = 0; i < n - 1; i++) {
        if (sorted[i] == sorted[i + 1]) { found = 1; break; }
    }

    free(sorted);
    return found;
}

int main(void) {
    int a[] = {4, 2, 7, 2, 9};
    int b[] = {1, 3, 5, 7, 9};

    printf("has_duplicates(a): %d\n", has_duplicates(a, 5)); /* 1 */
    printf("has_duplicates(b): %d\n", has_duplicates(b, 5)); /* 0 */

    /* Without transformation: O(n^2) brute-force comparison */
    /* With transformation (sort first): O(n log n)           */
    return 0;
}
```


## References

* [Introduction to the Design and Analysis of Algorithms](https://www.amazon.com/Introduction-Design-Analysis-Algorithms-3rd/dp/0132316811) — Anany Levitin, Chapter 6.
* [GeeksforGeeks — Transform and Conquer](https://www.geeksforgeeks.org/transform-and-conquer/) — Overview with examples.
