# Greedy Algorithm

## 📋 Summary

* **Core Concept:** A Greedy Algorithm is a problem-solving paradigm that builds a solution incrementally by always making the **locally optimal choice** at each step, with the expectation (or proof) that this leads to a **globally optimal solution**.

> **Takeaways:** Greedy algorithms are **fast and simple**, but correctness is not automatic. A greedy strategy is only valid when the problem satisfies the **Greedy Choice Property** and **Optimal Substructure**. If either property fails, the greedy approach produces a suboptimal or incorrect answer. Always **prove** greedy correctness before trusting it — exchange argument and induction are the two standard proof techniques.


## 📖 Definition

* **Greedy Choice Property:** A globally optimal solution can always be reached by making a locally optimal (greedy) choice at each step — the current choice does not depend on future subproblems.
* **Optimal Substructure:** An optimal solution to the full problem contains optimal solutions to its subproblems. This is a shared requirement with Dynamic Programming; the difference is that greedy resolves one subproblem per step without reconsidering past choices.
* **Feasible Solution:** A candidate solution that satisfies all problem constraints (e.g., a valid schedule, a valid coin set). The greedy approach always selects the best feasible candidate available.
* **Exchange Argument:** A standard proof technique for greedy correctness. Assume any optimal solution $OPT$ differs from the greedy solution $G$. Show that swapping one element of $OPT$ to match $G$ does not worsen the objective — repeating this argument transforms $OPT$ into $G$, proving $G$ is also optimal.
* **Requirements:**
    * The problem must exhibit **Greedy Choice Property**.
    * The problem must exhibit **Optimal Substructure**.
    * If either property is absent, use **Dynamic Programming** or another exact method instead.


## 📊 Complexity Analysis

> Greedy algorithms do not have a single complexity. The table below covers the three canonical greedy problems used as examples in this note.

| Problem | Time Complexity | Bottleneck |
| :--- | :--- | :--- |
| Activity Selection | $O(n \log n)$ | Sorting by finish time |
| Fractional Knapsack | $O(n \log n)$ | Sorting by value/weight ratio |
| Coin Change (canonical) | $O(n / d_{min})$ | Greedy selection loop |
| Huffman Coding | $O(n \log n)$ | Priority queue operations |

* **Worst-Case ($O$):** Typically $O(n \log n)$ — sorting the input candidates dominates in most greedy problems.
* **Best-Case ($\Omega$):** $\Omega(n)$ — at minimum, every input element must be examined once.
* **Average-Case ($\Theta$):** $\Theta(n \log n)$ for sorting-based greedy strategies on random input.

> **Space Complexity:** $O(1)$ to $O(n)$ depending on whether auxiliary storage (heap, sorted array) is required.

> **Greedy vs. Dynamic Programming:**
> | Property | Greedy | DP |
> | :--- | :--- | :--- |
> | Reconsiders past choices | No | No |
> | Explores all subproblems | No (one path) | Yes (all paths) |
> | Time complexity | Lower (usually) | Higher |
> | Correctness guarantee | Requires proof | Always correct if implemented right |


## ❓ Why We Use It

* **Speed:** Greedy algorithms typically run in $O(n \log n)$ or better — far faster than the exponential cost of brute-force enumeration.
* **Simplicity:** The logic is intuitive: always pick the best available option. Implementation is usually short and easy to reason about.
* **Optimality on the right problems:** On problems with both required properties (e.g., Activity Selection, Fractional Knapsack, Huffman Coding, MST), the greedy solution is provably optimal — not just a heuristic.
* **Foundation for advanced algorithms:** Prim's and Kruskal's (MST), Dijkstra's (shortest path), and Huffman Coding are all greedy algorithms. Understanding the paradigm is essential before studying these.
* **Practical applications:** Task scheduling, data compression (Huffman), network routing, cache replacement policies (LRU is greedy), and resource allocation.


## ⚙️ How It Works

**General Greedy Template:**

```
1. Define the set of candidates (input items/edges/tasks).
2. Sort or structure candidates by a greedy criterion
   (e.g., minimum cost, maximum value/weight ratio, earliest finish time).
3. Iterate through candidates:
   a. If the current candidate is feasible → select it (add to solution).
   b. If not feasible → skip it.
4. Return the accumulated solution.
```

---

### Example 1 — Activity Selection Problem

**Problem:** Given $n$ activities with start times $s_i$ and finish times $f_i$, select the maximum number of non-overlapping activities.

**Greedy Criterion:** Always pick the activity with the **earliest finish time** that does not conflict with the last selected activity.

```
Activities (sorted by finish time):
  ID  Start  Finish
   A    1      3
   B    2      5
   C    4      6
   D    6      8
   E    5      9
   F    8     10

Greedy Trace:
  last_finish = -∞

  → A: finish=3, start=1 ≥ -∞  ✓ SELECT  last_finish = 3
  → B: finish=5, start=2 < 3   ✗ SKIP  (overlaps A)
  → C: finish=6, start=4 ≥ 3   ✓ SELECT  last_finish = 6
  → D: finish=8, start=6 ≥ 6   ✓ SELECT  last_finish = 8
  → E: finish=9, start=5 < 8   ✗ SKIP  (overlaps D)
  → F: finish=10,start=8 ≥ 8   ✓ SELECT  last_finish = 10

Selected: {A, C, D, F}  — 4 activities (optimal)

Timeline:
  1  2  3  4  5  6  7  8  9  10
  [──A──]
     [────B────]
           [──C──]
                 [──D──]
              [──────E──────]
                       [──F──]

  Selected (✓):
  [──A──]   [──C──][──D──][──F──]
```

---

### Example 2 — Fractional Knapsack

**Problem:** A thief can carry weight $W$. Items have values $v_i$ and weights $w_i$. Fractions of items are allowed. Maximize total value.

**Greedy Criterion:** Sort by **value-per-unit-weight** ($v_i / w_i$) in descending order. Take as much of each item as possible.

```
W = 50 kg
  Item  Value  Weight  Ratio (v/w)
    A     60     10      6.0  ← highest
    B    100     20      5.0
    C    120     30      4.0

Greedy Trace:
  remaining_capacity = 50

  → A: weight=10 ≤ 50  take all  value += 60,   capacity = 40
  → B: weight=20 ≤ 40  take all  value += 100,  capacity = 20
  → C: weight=30 > 20  take 20/30 fraction
       value += 120 × (20/30) = 80,  capacity = 0

Total Value = 60 + 100 + 80 = 240  (optimal)
```

---

**Formal Steps (General):**
1. **Identify** the greedy criterion — what property makes a candidate "best" at each step.
2. **Sort** candidates by this criterion if order matters.
3. **Select** the most promising feasible candidate at each step; discard infeasible ones.
4. **Accumulate** selected candidates into the solution set.
5. **Verify** (at design time, not runtime) that the greedy choice property and optimal substructure hold — otherwise the algorithm is incorrect.


## 💻 Usage / Example

```c
#include <stdio.h>
#include <stdlib.h>

/* ── Activity Selection ─────────────────────────────────────────── */

typedef struct {
    int start, finish;
    char id;
} Activity;

int cmp_finish(const void *a, const void *b) {
    return ((Activity *)a)->finish - ((Activity *)b)->finish;
}

void activity_selection(Activity acts[], int n) {
    qsort(acts, n, sizeof(Activity), cmp_finish);  /* Basic Operation: Sort by finish */

    printf("Selected Activities:\n");
    int last_finish = -1;

    for (int i = 0; i < n; i++) {
        if (acts[i].start >= last_finish) {        /* Feasibility check: no overlap */
            printf("  %c: [%d, %d]\n", acts[i].id, acts[i].start, acts[i].finish);
            last_finish = acts[i].finish;           /* Greedy update */
        }
    }
}

/* ── Fractional Knapsack ────────────────────────────────────────── */

typedef struct {
    double value, weight;
} Item;

int cmp_ratio(const void *a, const void *b) {
    double ra = ((Item *)a)->value / ((Item *)a)->weight;
    double rb = ((Item *)b)->value / ((Item *)b)->weight;
    return (rb > ra) - (rb < ra);   /* Descending ratio order */
}

double fractional_knapsack(Item items[], int n, double capacity) {
    qsort(items, n, sizeof(Item), cmp_ratio);  /* Basic Operation: Sort by v/w ratio */

    double total = 0.0;

    for (int i = 0; i < n && capacity > 0.0; i++) {
        double take  = (items[i].weight <= capacity)
                       ? items[i].weight
                       : capacity;               /* Take as much as possible */
        total       += items[i].value * (take / items[i].weight);
        capacity    -= take;
    }

    return total;
}

/* ── Driver ─────────────────────────────────────────────────────── */

int main(void) {
    /* Activity Selection */
    Activity acts[] = {
        {1, 3, 'A'}, {2, 5, 'B'}, {4, 6, 'C'},
        {6, 8, 'D'}, {5, 9, 'E'}, {8, 10, 'F'},
    };
    int n_acts = sizeof(acts) / sizeof(acts[0]);
    activity_selection(acts, n_acts);

    /* Fractional Knapsack */
    Item items[] = {
        {60.0, 10.0},   /* ratio = 6.0 */
        {100.0, 20.0},  /* ratio = 5.0 */
        {120.0, 30.0},  /* ratio = 4.0 */
    };
    int    n_items  = sizeof(items) / sizeof(items[0]);
    double max_val  = fractional_knapsack(items, n_items, 50.0);
    printf("\nFractional Knapsack Maximum Value: %.2f\n", max_val);

    return 0;
}

/*
 * Output:
 *   Selected Activities:
 *     A: [1, 3]
 *     C: [4, 6]
 *     D: [6, 8]
 *     F: [8, 10]
 *
 *   Fractional Knapsack Maximum Value: 240.00
 *
 * Both functions: Complexity O(n log n) — qsort dominates
 * Selection/knapsack loop: O(n)
 */
```


## References

* [Introduction to Algorithms, 4th ed.](https://mitpress.mit.edu/9780262046305/) — Cormen, Leiserson, Rivest, Stein (CLRS), Chapter 15: Greedy Algorithms (Activity Selection, Huffman Coding).
* [Algorithm Design] — Jon Kleinberg & Éva Tardos, Chapter 4: Greedy Algorithms — comprehensive coverage of exchange arguments and proofs of correctness.
* [GeeksforGeeks — Greedy Algorithms](https://www.geeksforgeeks.org/greedy-algorithms/) — Catalog of classic greedy problems with implementations.
* [Introduction to the Design and Analysis of Algorithms, 3rd ed.] — Anany Levitin, Chapter 9: Greedy Technique — formal definition and problem taxonomy.
* [Algorithms Illuminated, Part 3] — Tim Roughgarden — Greedy algorithms with intuition-first proofs.
