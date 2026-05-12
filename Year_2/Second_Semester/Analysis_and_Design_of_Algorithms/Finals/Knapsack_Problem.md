# Knapsack Problem (0/1)

## 📋 Summary

* **Core Concept:** The 0/1 Knapsack Problem is a combinatorial optimization problem that determines the maximum total value achievable by selecting items — each taken **whole or not at all** — without exceeding a given weight capacity.

> **Takeaways:** The brute-force approach evaluates $2^n$ subsets. Dynamic programming reduces this to $O(nW)$ by building a table where each cell represents the optimal value achievable using the first $i$ items within capacity $w$. The key insight is that each subproblem's answer depends only on the row above it — no item is revisited.

---

## 📖 Definition

* **Knapsack Problem:** Given $n$ items, each with a weight $w_i$ and value $v_i$, and a knapsack of maximum capacity $W$, select a subset of items to **maximize total value** without exceeding total weight $W$.
* **0/1 Constraint:** Each item is either taken entirely ($x_i = 1$) or excluded entirely ($x_i = 0$). Fractions are not allowed. This distinguishes it from the Fractional Knapsack, which is solvable greedily.
* **Optimal Substructure:** The optimal solution to the full problem contains optimal solutions to its subproblems. This property justifies the use of dynamic programming.
* **DP Table $V[i][w]$:** A 2D table where $V[i][w]$ stores the maximum value attainable using only items $1$ through $i$ with a weight limit of $w$.
* **Recurrence Relation:**

$$V[i][w] = \begin{cases} V[i-1][w] & \text{if } w_i > w \quad \text{(item too heavy, skip)} \\ \max\bigl(V[i-1][w],\; v_i + V[i-1][w - w_i]\bigr) & \text{if } w_i \leq w \quad \text{(take or skip)} \end{cases}$$

* **Requirements:**
  * Item weights $w_i$ and capacity $W$ must be **non-negative integers** (for standard DP table indexing).
  * Each item is **indivisible** — no partial selection.
  * All $v_i > 0$ (items with zero value are trivially excluded).

---

## 📊 Complexity Analysis

| Notation | Name | Growth Rate |
| :--- | :--- | :--- |
| $O(1)$ | Constant | Excellent |
| $O(\log n)$ | Logarithmic | Very Good |
| $O(n)$ | Linear | Good |
| $O(n \log n)$ | Linearithmic | Good |
| $O(nW)$ | Pseudo-polynomial | Moderate |
| $O(2^n)$ | Exponential | Terrible |

* **Worst-Case ($O$):** $O(nW)$ — The DP table has $(n+1) \times (W+1)$ cells, each filled in $O(1)$.
* **Best-Case ($\Omega$):** $\Omega(nW)$ — No early termination exists; the entire table is always constructed.
* **Average-Case ($\Theta$):** $\Theta(nW)$ — Uniform fill pattern regardless of item values or weights.
* **Space Complexity:** $O(nW)$ for the full table; reducible to $O(W)$ using a 1D rolling array (tracing the selected items is lost in that case).
* **Why "Pseudo-Polynomial":** $O(nW)$ appears polynomial but $W$ is the **numeric value** of input, not its bit-length. In terms of input size, this is exponential — the problem is NP-Hard.

---

## ❓ Why We Use It

* **Optimal resource allocation:** Models any scenario where a fixed budget or capacity must be allocated across competing options — financial portfolios, cargo loading, project selection, and memory allocation in embedded systems.
* **Avoids exponential brute force:** Checking all $2^n$ subsets is infeasible for large $n$. DP makes it tractable for practical $n$ and $W$ values.
* **Foundation for more complex DP:** Mastering the 0/1 Knapsack establishes the pattern — define state, write recurrence, fill table bottom-up — used in interval scheduling, sequence alignment, and cut-rod problems.
* **Exact vs. approximate:** Unlike greedy heuristics (which fail on 0/1 instances), DP guarantees the **globally optimal** solution.

---

## ⚙️ How It Works

1. **Step 1 — Define the state.** Let $V[i][w]$ represent the maximum value achievable using items $1$ to $i$ with a capacity of exactly $w$. Initialize the base cases: $V[0][w] = 0$ for all $w$ (no items → no value) and $V[i][0] = 0$ for all $i$ (no capacity → no value).

2. **Step 2 — Iterate over items and capacities.** Fill the table row by row (item $i = 1$ to $n$), column by column (capacity $w = 1$ to $W$).

3. **Step 3 — Apply the recurrence.** For each cell $(i, w)$, decide: if item $i$ is too heavy ($w_i > w$), carry forward the value without it. Otherwise, take the maximum between excluding item $i$ or including it:

$$V[i][w] = \max\bigl(V[i-1][w],\; v_i + V[i-1][w - w_i]\bigr)$$

4. **Step 4 — Identify the basic operation.** The **max comparison** at each cell is the basic operation, performed exactly $n \times W$ times:

$$C(n, W) = n \cdot W \implies T(n, W) \approx c_{op} \cdot nW$$

5. **Step 5 — Traceback (optional).** Starting from $V[n][W]$, walk backwards through the table: if $V[i][w] \neq V[i-1][w]$, item $i$ was included. Reduce $w$ by $w_i$ and move to row $i-1$.

---

## 📊 Step-by-Step Visualization

**Example Input:**

| Item | Weight ($w_i$) | Value ($v_i$) |
|:-----|:--------------|:-------------|
| 1    | 2             | 3            |
| 2    | 3             | 4            |
| 3    | 4             | 5            |
| 4    | 5             | 6            |

**Capacity $W = 8$, $n = 4$ items**

---

**DP Table $V[i][w]$ — Maximum value for items $1..i$ at capacity $w$:**

| $i$ \ $w$ | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|:----------|:--|:--|:--|:--|:--|:--|:--|:--|:--|
| **0** (no items) | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| **1** (item 1: w=2, v=3) | 0 | 0 | 3 | 3 | 3 | 3 | 3 | 3 | 3 |
| **2** (item 2: w=3, v=4) | 0 | 0 | 3 | 4 | 4 | 7 | 7 | 7 | 7 |
| **3** (item 3: w=4, v=5) | 0 | 0 | 3 | 4 | 5 | 7 | 8 | 9 | 9 |
| **4** (item 4: w=5, v=6) | 0 | 0 | 3 | 4 | 5 | 7 | 8 | 9 | **10** |

> **Answer:** $V[4][8] = 10$ — Maximum value is **10**.

---

**Traceback to find selected items:**

| Step | $i$ | $w$ | $V[i][w]$ | $V[i-1][w]$ | Decision |
|:-----|:----|:----|:----------|:------------|:---------|
| 1 | 4 | 8 | 10 | 9 | $10 \neq 9$ → **Include item 4** (w=5, v=6). New $w = 8-5=3$ |
| 2 | 3 | 3 | 4 | 4 | $4 = 4$ → **Skip item 3** |
| 3 | 2 | 3 | 4 | 0 | $4 \neq 0$ → **Include item 2** (w=3, v=4). New $w = 3-3=0$ |
| 4 | 1 | 0 | 0 | — | Capacity exhausted. Stop. |

> **Selected Items:** Item 2 (w=3, v=4) + Item 4 (w=5, v=6) → Total weight = **8**, Total value = **10** ✓

---

## 💻 Usage / Example

```c
/* Example: 0/1 Knapsack — Dynamic Programming
   Basic Operation: max() comparison → O(nW)          */

#include <stdio.h>

#define N 4           /* Number of items  */
#define W_CAP 8       /* Knapsack capacity */

/* Helper: return the larger of two integers */
int max(int a, int b) { return (a > b) ? a : b; }

/* Fill DP table and return the maximum value achievable */
int knapsack_01(int weights[], int values[], int n, int capacity,
                int V[N + 1][W_CAP + 1]) {
    int i, w;

    /* Step 1: Base cases — zero items or zero capacity yields zero value */
    for (i = 0; i <= n; i++)    V[i][0] = 0;
    for (w = 0; w <= capacity; w++) V[0][w] = 0;

    /* Step 2–3: Fill the table bottom-up */
    for (i = 1; i <= n; i++) {
        int w_i = weights[i - 1];
        int v_i = values[i - 1];
        for (w = 0; w <= capacity; w++) {
            if (w_i > w) {
                /* Item too heavy — carry forward without it */
                V[i][w] = V[i - 1][w];
            } else {
                /* Basic Operation: choose the better of skip vs. include */
                V[i][w] = max(V[i - 1][w], v_i + V[i - 1][w - w_i]);
            }
        }
    }
    return V[n][capacity];
}

/* Step 5: Traceback — print which items were selected */
void traceback(int weights[], int values[], int n, int capacity,
               int V[N + 1][W_CAP + 1]) {
    int w = capacity;
    int i;
    printf("Selected Items :\n");
    for (i = n; i > 0; i--) {
        if (V[i][w] != V[i - 1][w]) {   /* Item i was included */
            printf("  Item %d: weight=%d, value=%d\n",
                   i, weights[i - 1], values[i - 1]);
            w -= weights[i - 1];
        }
    }
}

int main(void) {
    int weights[N]  = {2, 3, 4, 5};
    int values[N]   = {3, 4, 5, 6};
    int V[N + 1][W_CAP + 1];

    int result = knapsack_01(weights, values, N, W_CAP, V);

    printf("Capacity       : %d\n", W_CAP);
    printf("Maximum Value  : %d\n", result);
    traceback(weights, values, N, W_CAP, V);

    return 0;
}

/* Complexity: O(nW) time | O(nW) space */
```

**Output:**
```
Capacity       : 8
Maximum Value  : 10
Selected Items :
  Item 4: weight=5, value=6
  Item 2: weight=3, value=4
```

---

## References

* [0/1 Knapsack Problem — Wikipedia](https://en.wikipedia.org/wiki/Knapsack_problem) — Formal problem statement, variants, and complexity discussion.
* [Introduction to the Design and Analysis of Algorithms] — Anany Levitin, 3rd Edition, Chapter 8.2 (The Knapsack Problem and Memory Functions).
* [Introduction to Algorithms (CLRS)] — Cormen et al., 4th Edition, Chapter 16 (Greedy Algorithms) and Chapter 15 (Dynamic Programming) for contrast between problem variants.
* [GeeksforGeeks — 0/1 Knapsack Problem](https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/) — Worked examples with code and space optimization techniques.
