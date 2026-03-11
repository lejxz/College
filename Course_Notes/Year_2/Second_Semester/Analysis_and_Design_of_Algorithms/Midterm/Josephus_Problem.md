# Josephus Problem

## 📋 Summary
* **Core Concept:** The Josephus Problem is a theoretical counting-out puzzle where $n$ people stand in a circle and every $k$-th person is eliminated repeatedly until one survivor remains — the goal is to determine the position of the last survivor.

> **Takeaways:** The Josephus Problem is a classic application of Decrease and Conquer. Its recursive formulation reduces a problem of size $n$ to size $n-1$, and for $k=2$, a closed-form $O(\log n)$ solution exists using bit manipulation.


## 📖 Definition

* **Josephus Problem:** Given $n$ people numbered $0$ to $n-1$ arranged in a circle, and an elimination step $k$, determine the index of the last remaining person after every $k$-th person is eliminated.
* **Recursive Substructure:** After eliminating the first person, the remaining $n-1$ people form a new circle — the solution for $n$ people reduces to the solution for $n-1$ people with a positional offset.
* **Safe Position $J(n, k)$:** The 0-indexed position of the survivor in a circle of $n$ people with step size $k$.
* **Requirements:**
    * $n \geq 1$ — at least one person must be present.
    * $k \geq 1$ — the step size must be a positive integer.
    * Counting restarts from the next person after each elimination.


## 📊 Complexity Analysis

| Notation | Name | Growth Rate |
| :--- | :--- | :--- |
| $O(1)$ | Constant | Excellent |
| $O(\log n)$ | Logarithmic | Very Good |
| $O(n)$ | Linear | Good |
| $O(n \cdot k)$ | Linear-scaled | Moderate |
| $O(n^2)$ | Quadratic | Poor |

* **Worst-Case ($O$):**
  * General $k$: $O(n)$ — the recursion unwinds through $n$ subproblems.
  * Simulation approach: $O(n \cdot k)$ — stepping through $k$ positions per elimination.
* **Best-Case ($\Omega$):**
  * $k = 2$, closed-form bit manipulation: $\Omega(\log n)$.
* **Average-Case ($\Theta$):**
  * Recursive formula: $\Theta(n)$ — linear regardless of $k$.


## ❓ Why We Use It

* **Decrease and Conquer Illustration:** The Josephus Problem is a textbook example of reducing a problem of size $n$ to size $n-1$ via a single recursive call.
* **Algorithm Design Insight:** It demonstrates how a simulation problem can be reformulated mathematically to eliminate redundant computation.
* **Competitive Programming:** The problem appears frequently in algorithm contests due to its elegant recursive structure.
* **Historical Significance:** The problem traces back to historian Flavius Josephus, who allegedly used this logic to survive a mass suicide pact during the Jewish-Roman War (AD 70).


## ⚙️ How It Works

### Recursive Formula (General $k$)

The recurrence relation for the 0-indexed safe position is:

$$J(1, k) = 0$$
$$J(n, k) = (J(n-1, k) + k) \mod n$$

1. **Step 1:** Base case — with only 1 person, the survivor is at position 0.
2. **Step 2:** For $n$ people, eliminate the $k$-th person; the remaining $n-1$ people form a new sub-problem.
3. **Step 3:** Apply the offset: the answer for $n$ is derived by shifting the answer for $n-1$ by $k$ positions and wrapping via modulo $n$.
4. **Step 4:** Unwind the recursion (or use iteration) from $n=1$ back to $n$; the final value is the 0-indexed survivor position.

### Closed-Form for $k = 2$ (Bit Manipulation)

If $n = 2^m + L$ where $0 \leq L < 2^m$:

$$J(n) = 2L + 1$$

Equivalently using bit rotation:

$$J(n) = \text{RotateLeft}(n)$$

where the leading bit of $n$ in binary is moved to the least significant position.


## 💻 Usage / Example

```c
// Example: Josephus Problem — Recursive and Iterative in C
#include <stdio.h>

// --- Recursive Approach: O(n) time, O(n) stack space ---
// Returns 0-indexed position of the survivor
int josephus_recursive(int n, int k) {
    if (n == 1)
        return 0;                                      // Base Case
    return (josephus_recursive(n - 1, k) + k) % n;    // Basic Operation: modulo reduction
}

// --- Iterative Approach: O(n) time, O(1) space ---
int josephus_iterative(int n, int k) {
    int pos = 0;                    // Survivor position for n=1
    for (int i = 2; i <= n; i++) {
        pos = (pos + k) % i;        // Basic Operation: shift and wrap
    }
    return pos;                     // 0-indexed result
}

// --- Closed-Form for k=2: O(log n) ---
// Rotate the leading bit of n to the LSB
int josephus_k2(int n) {
    int highest_bit = 1;
    while (highest_bit <= n)
        highest_bit <<= 1;
    highest_bit >>= 1;              // highest power of 2 <= n

    int L = n - highest_bit;        // L = n - 2^m
    return 2 * L + 1;               // 1-indexed result
}

int main() {
    int n = 7, k = 3;

    int r = josephus_recursive(n, k);
    printf("Recursive  (n=%d, k=%d): 0-indexed = %d, 1-indexed = %d\n", n, k, r, r + 1);

    int it = josephus_iterative(n, k);
    printf("Iterative  (n=%d, k=%d): 0-indexed = %d, 1-indexed = %d\n", n, k, it, it + 1);

    int n2 = 14;
    int cf = josephus_k2(n2);
    printf("Closed-form (n=%d, k=2): 1-indexed = %d\n", n2, cf);

    /*
     * Expected Output:
     * Recursive  (n=7, k=3): 0-indexed = 3, 1-indexed = 4
     * Iterative  (n=7, k=3): 0-indexed = 3, 1-indexed = 4
     * Closed-form (n=14, k=2): 1-indexed = 13
     */
    return 0;
}

// General Recursion/Iteration Complexity: O(n)
// Closed-form k=2 Complexity:             O(log n)
```


## References

* [Introduction to Algorithms (CLRS)](https://mitpress.mit.edu/9780262046305/) — Cormen et al.; recursive problem reduction and modular arithmetic.
* [Algorithm Design and Analysis — Levitin](https://www.pearson.com/en-us/subject-catalog/p/introduction-to-the-design-and-analysis-of-algorithms/P200000003415) — Anany Levitin, Chapter 5: Decrease-and-Conquer; Josephus Problem as a case study.
* [GeeksforGeeks – Josephus Problem](https://www.geeksforgeeks.org/josephus-problem-set-1-a-on-solution/) — Walkthrough of recursive, iterative, and bit-manipulation solutions.
* [CP-Algorithms – Josephus Problem](https://cp-algorithms.com/algebra/josephus_problem.html) — Formal derivation of the recurrence and $O(k \log n)$ variant.