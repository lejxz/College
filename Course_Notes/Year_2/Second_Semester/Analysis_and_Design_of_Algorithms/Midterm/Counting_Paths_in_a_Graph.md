# Counting Paths in a Graph

## 📋 Summary
* **Core Concept:** Counting paths in a graph involves determining the number of distinct paths between vertices, commonly solved using adjacency matrix exponentiation or dynamic programming on DAGs.

> **Takeaways:** For unweighted graphs, raising the adjacency matrix $A$ to the $k$-th power gives the number of paths of exactly length $k$ between all pairs of vertices. For DAGs, topological ordering enables efficient DP-based counting.


## 📖 Definition

* **Path:** A sequence of vertices where each consecutive pair is connected by an edge; no vertex is repeated in a simple path.
* **Adjacency Matrix ($A$):** An $n \times n$ matrix where $A[i][j] = 1$ if there is a directed edge from vertex $i$ to vertex $j$, and $0$ otherwise.
* **Matrix Exponentiation:** The operation $A^k$ yields a matrix where entry $[i][j]$ represents the number of walks of exactly length $k$ from vertex $i$ to vertex $j$.
* **DAG (Directed Acyclic Graph):** A directed graph with no cycles, enabling topological sorting for path counting via dynamic programming.
* **Requirements:**
    * The graph must be represented as an adjacency matrix for matrix exponentiation.
    * For DP-based counting, the graph must be a DAG with a defined topological order.


## 📊 Complexity Analysis

| Method | Time Complexity | Space Complexity |
| :--- | :--- | :--- |
| Matrix Exponentiation ($A^k$) | $O(n^3 \log k)$ | $O(n^2)$ |
| DP on DAG | $O(V + E)$ | $O(V)$ |
| Brute-force DFS | $O(2^V)$ | $O(V)$ |

* **Worst-Case ($O$):** $O(n^3 \log k)$ for matrix exponentiation using fast exponentiation.
* **Best-Case ($\Omega$):** $\Omega(V + E)$ for DAG-based dynamic programming.
* **Average-Case ($\Theta$):** $\Theta(n^3 \log k)$ for general graphs using matrix methods.


## ❓ Why We Use It

* **Network analysis:** Used to count communication paths between nodes in a network.
* **Combinatorics:** Useful in problems that require counting routes, schedules, or sequences.
* **Dynamic programming foundation:** Path counting on DAGs is a foundational DP pattern applicable in many scheduling and dependency problems.


## ⚙️ How It Works

**Method 1 — Matrix Exponentiation:**
1. **Step 1:** Represent the graph as an adjacency matrix $A$.
2. **Step 2:** Compute $A^k$ using repeated matrix multiplication.
3. **Step 3:** Entry $A^k[i][j]$ gives the number of walks of length exactly $k$ from $i$ to $j$.
4. **Step 4:** Use fast (binary) exponentiation to reduce complexity:
   $$A^k = \begin{cases} (A^{k/2})^2 & \text{if } k \text{ is even} \\ A \cdot A^{k-1} & \text{if } k \text{ is odd} \end{cases}$$

**Method 2 — DP on DAG:**
1. **Step 1:** Perform topological sort on the DAG.
2. **Step 2:** Initialize $\text{paths}[\text{source}] = 1$, all others $= 0$.
3. **Step 3:** For each vertex $u$ in topological order, update:
   $$\text{paths}[v] += \text{paths}[u] \quad \forall (u, v) \in E$$
4. **Step 4:** Read $\text{paths}[\text{target}]$ for the answer.


## 💻 Usage / Example

```c
#include <stdio.h>
#include <string.h>

#define N 4

/* Multiply two N x N matrices, store result in C */
void mat_mult(int A[N][N], int B[N][N], int C[N][N]) {
    int tmp[N][N];
    memset(tmp, 0, sizeof(tmp));
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            for (int l = 0; l < N; l++)
                tmp[i][j] += A[i][l] * B[l][j];
    memcpy(C, tmp, sizeof(tmp));
}

/* Raise matrix A to power k, store result in R */
void mat_pow(int A[N][N], int k, int R[N][N]) {
    /* Initialize R as identity matrix */
    memset(R, 0, sizeof(int) * N * N);
    for (int i = 0; i < N; i++) R[i][i] = 1;

    int base[N][N];
    memcpy(base, A, sizeof(base));

    while (k > 0) {
        if (k % 2 == 1) mat_mult(R, base, R);
        mat_mult(base, base, base);
        k /= 2;
    }
}

int main(void) {
    /*
     * Graph: 4 vertices
     * 0->1, 0->2, 1->3, 2->3
     */
    int A[N][N] = {
        {0, 1, 1, 0},
        {0, 0, 0, 1},
        {0, 0, 0, 1},
        {0, 0, 0, 0}
    };

    int k = 2;   /* paths of length exactly 2 */
    int R[N][N];
    mat_pow(A, k, R);

    printf("Paths of length %d from vertex 0 to vertex 3: %d\n", k, R[0][3]);
    /* Expected: 2  (0->1->3 and 0->2->3) */

    /* Complexity: O(n^3 log k) */
    return 0;
}
```


## References

* [Introduction to the Design and Analysis of Algorithms](https://www.amazon.com/Introduction-Design-Analysis-Algorithms-3rd/dp/0132316811) — Anany Levitin, Chapter 6.
* [CP-Algorithms — Matrix Exponentiation](https://cp-algorithms.com/algebra/matrix-exp.html) — Detailed walkthrough with examples.
* [GeeksforGeeks — Count paths in DAG](https://www.geeksforgeeks.org/count-possible-paths-two-vertices/) — DP approach on DAGs.
