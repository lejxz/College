# Warshall's Algorithm

## 📋 Summary

* **Core Concept:** Warshall's Algorithm computes the **transitive closure** of a directed graph — determining which vertices are reachable from which — using iterative matrix refinement over all intermediate vertices.

> **Takeaways:** The algorithm transforms an adjacency matrix into a reachability matrix by asking one question per intermediate vertex: *"Does routing through vertex k create a new path from i to j?"* Every path that exists — direct or indirect — is captured after n iterations.

---

## 📖 Definition

* **Transitive Closure:** A boolean matrix $R$ where $R[i][j] = 1$ if and only if there exists a directed path (of one or more edges) from vertex $i$ to vertex $j$ in graph $G$.
* **Adjacency Matrix ($R^{(0)}$):** An $n \times n$ boolean matrix representing direct edges. $R^{(0)}[i][j] = 1$ if there is a direct edge from $i$ to $j$, and $0$ otherwise.
* **Intermediate Vertex:** A vertex $k$ used as a stepping stone when evaluating whether a path from $i$ to $j$ exists through $k$.
* **Recurrence Relation:** The core update rule applied at each iteration $k$:

$$R^{(k)}[i][j] = R^{(k-1)}[i][j] \;\lor\; \bigl(R^{(k-1)}[i][k] \;\land\; R^{(k-1)}[k][j]\bigr)$$

* **Requirements:**
  * Input must be a **directed graph** (digraph).
  * Graph must be represented as an **$n \times n$ adjacency matrix**.
  * Diagonal entries $R[i][i]$ may be set to $1$ (a vertex can reach itself) or left as $0$ depending on the convention used.

---

## 📊 Complexity Analysis

| Notation | Name | Growth Rate |
| :--- | :--- | :--- |
| $O(1)$ | Constant | Excellent |
| $O(\log n)$ | Logarithmic | Very Good |
| $O(n)$ | Linear | Good |
| $O(n^2)$ | Quadratic | Poor |
| $O(n^3)$ | Cubic | Very Poor |

* **Worst-Case ($O$):** $O(n^3)$ — Three nested loops each of size $n$ always run to completion regardless of graph structure.
* **Best-Case ($\Omega$):** $\Omega(n^3)$ — The algorithm has **no early exit**; all $n^3$ iterations execute unconditionally.
* **Average-Case ($\Theta$):** $\Theta(n^3)$ — Performance is uniform across all input cases. There is no structural variation to exploit.
* **Space Complexity:** $O(n^2)$ — Stores the $n \times n$ reachability matrix. In-place updates are possible since the recurrence is safe to apply without a copy.

---

## ❓ Why We Use It

* **Reachability queries:** Determines, in $O(1)$ per query after $O(n^3)$ preprocessing, whether any vertex $i$ can reach any vertex $j$. Useful in compiler dependency graphs and network routing tables.
* **Transitive closure in databases:** Used in relational algebra to compute recursive relationships (e.g., ancestor queries in hierarchical data).
* **Simpler than path-finding alternatives:** Unlike Dijkstra or BFS repeated per source vertex, Warshall's produces a complete reachability map in a single, uniform pass — no priority queues or visited arrays needed.
* **Foundation for Floyd-Warshall:** Understanding Warshall's boolean logic directly prepares you for Floyd-Warshall, which extends the same structure to compute all-pairs shortest paths with weighted edges.

---

## ⚙️ How It Works

1. **Step 1 — Initialize the matrix.** Set $R^{(0)}$ equal to the adjacency matrix of the directed graph. Each $1$ represents a direct edge; each $0$ means no direct connection.

2. **Step 2 — Iterate over intermediate vertices.** For $k = 1$ to $n$, treat vertex $k$ as the only allowed intermediate stop. Ask: *"Can I travel from $i$ to $j$ by passing through $k$?"*

3. **Step 3 — Apply the recurrence.** For every pair $(i, j)$, update using the recurrence relation:

$$R^{(k)}[i][j] = R^{(k-1)}[i][j] \;\lor\; \bigl(R^{(k-1)}[i][k] \;\land\; R^{(k-1)}[k][j]\bigr)$$

   A cell is set to $1$ if it was already $1$, **or** if vertex $k$ bridges a new path.

4. **Step 4 — Identify the basic operation.** The **OR-AND** boolean update is the basic operation, performed exactly $n^3$ times total, giving $C(n) = n^3$.

5. **Step 5 — Read the result.** After iteration $k = n$, the matrix $R^{(n)}$ is the complete transitive closure. $R[i][j] = 1$ means $j$ is reachable from $i$ by any path of any length.

---

## 📊 Step-by-Step Visualization

**Example Graph (4 vertices):**

```
Edges: 1→2, 2→4, 3→1, 4→3
```

**Initial Adjacency Matrix $R^{(0)}$:**

|   | 1 | 2 | 3 | 4 |
|:--|:--|:--|:--|:--|
| **1** | 0 | 1 | 0 | 0 |
| **2** | 0 | 0 | 0 | 1 |
| **3** | 1 | 0 | 0 | 0 |
| **4** | 0 | 0 | 1 | 0 |

---

**After $k=1$ (intermediate: vertex 1) — New paths through vertex 1:**

> Row 3 gains reachability: $3 \to 1 \to 2$, so $R[3][2] = 1$

|   | 1 | 2 | 3 | 4 |
|:--|:--|:--|:--|:--|
| **1** | 0 | 1 | 0 | 0 |
| **2** | 0 | 0 | 0 | 1 |
| **3** | 1 | **1** | 0 | 0 |
| **4** | 0 | 0 | 1 | 0 |

---

**After $k=2$ (intermediate: vertex 2) — New paths through vertex 2:**

> $1 \to 2 \to 4$, so $R[1][4] = 1$; $3 \to 2 \to 4$, so $R[3][4] = 1$

|   | 1 | 2 | 3 | 4 |
|:--|:--|:--|:--|:--|
| **1** | 0 | 1 | 0 | **1** |
| **2** | 0 | 0 | 0 | 1 |
| **3** | 1 | 1 | 0 | **1** |
| **4** | 0 | 0 | 1 | 0 |

---

**After $k=3$ (intermediate: vertex 3) — New paths through vertex 3:**

> $2 \to 4 \to 3 \to 1$, so $R[2][1] = 1$; $4 \to 3 \to 1$, so $R[4][1] = 1$; $4 \to 3 \to 2$, so $R[4][2] = 1$

|   | 1 | 2 | 3 | 4 |
|:--|:--|:--|:--|:--|
| **1** | 0 | 1 | 0 | 1 |
| **2** | **1** | 0 | 1 | 1 |
| **3** | 1 | 1 | 0 | 1 |
| **4** | **1** | **1** | 1 | 0 |

---

**After $k=4$ (intermediate: vertex 4) — Final transitive closure $R^{(4)}$:**

> All remaining reachable pairs resolved. Every vertex can reach every other vertex in this graph.

|   | 1 | 2 | 3 | 4 |
|:--|:--|:--|:--|:--|
| **1** | **1** | 1 | **1** | 1 |
| **2** | 1 | **1** | 1 | 1 |
| **3** | 1 | 1 | **1** | 1 |
| **4** | 1 | 1 | 1 | **1** |

> **Bold diagonal** entries = self-reachability via cycles. All entries are 1, meaning the graph is **strongly connected**.

---

## 💻 Usage / Example

```c
/* Example: Warshall's Algorithm — Transitive Closure
   Basic Operation: Boolean OR-AND update → O(n^3)       */

#include <stdio.h>

#define N 4

/* Step 1: Copy adjacency matrix into R, then apply recurrence in-place */
void warshalls(int R[N][N]) {
    int k, i, j;

    /* Step 2–3: Iterate over each intermediate vertex k (0-indexed) */
    for (k = 0; k < N; k++) {
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                /* Recurrence: R[i][j] = R[i][j] OR (R[i][k] AND R[k][j]) */
                R[i][j] = R[i][j] || (R[i][k] && R[k][j]); /* Basic Operation */
            }
        }
    }
}

void print_matrix(int R[N][N], const char *label) {
    int i, j;
    printf("\n%s\n", label);
    printf("   ");
    for (j = 0; j < N; j++) printf(" %d", j + 1);
    printf("\n");
    for (i = 0; i < N; i++) {
        printf(" %d ", i + 1);
        for (j = 0; j < N; j++) printf(" %d", R[i][j]);
        printf("\n");
    }
}

int main(void) {
    /* Graph: 1->2, 2->4, 3->1, 4->3  (0-indexed internally) */
    int R[N][N] = {
        {0, 1, 0, 0},
        {0, 0, 0, 1},
        {1, 0, 0, 0},
        {0, 0, 1, 0}
    };

    print_matrix(R, "Adjacency Matrix R^(0):");
    warshalls(R);
    print_matrix(R, "Transitive Closure R^(n):");

    return 0;
}

/* Complexity: O(n^3) time | O(n^2) space */
```

**Output:**
```
Adjacency Matrix R^(0):
    1  2  3  4
 1  0  1  0  0
 2  0  0  0  1
 3  1  0  0  0
 4  0  0  1  0

Transitive Closure R^(n):
    1  2  3  4
 1  1  1  1  1
 2  1  1  1  1
 3  1  1  1  1
 4  1  1  1  1
```

---

## References

* [Warshall's Algorithm — Wikipedia](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm) — Overview of the algorithm and its relation to Floyd-Warshall.
* [Introduction to the Design and Analysis of Algorithms] — Anany Levitin, 3rd Edition, Chapter 8.2 (Warshall's and Floyd's Algorithms).
* [GeeksforGeeks — Transitive Closure of a Graph](https://www.geeksforgeeks.org/transitive-closure-of-a-graph/) — Practical walkthrough with code examples.
