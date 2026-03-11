# Topological Sorting

## 📋 Summary
* **Core Concept:** Topological Sorting produces a linear ordering of vertices in a Directed Acyclic Graph (DAG) such that for every directed edge `(u, v)`, vertex `u` appears before vertex `v` in the ordering.

> **Takeaways:** Topological Sorting is only valid on DAGs (no cycles). It is foundational in scheduling, build systems, and dependency resolution — any system where some tasks must precede others.


## 📖 Definition

* **Directed Acyclic Graph (DAG):** A directed graph with no directed cycles; a prerequisite structure for topological sorting.
* **Topological Order:** A linear sequence of all vertices of a DAG such that every directed edge `(u → v)` places `u` before `v`.
* **In-degree:** The number of incoming edges to a vertex; vertices with in-degree 0 have no prerequisites and are valid starting points.
* **Requirements:**
    * The graph must be **directed**.
    * The graph must be **acyclic** (no cycles); if a cycle exists, no topological order is possible.
    * All vertices must be included exactly once in the result.


## 📊 Complexity Analysis

| Notation | Name | Growth Rate |
| :--- | :--- | :--- |
| $O(1)$ | Constant | Excellent |
| $O(\log n)$ | Logarithmic | Very Good |
| $O(n)$ | Linear | Good |
| $O(n + e)$ | Linear (vertices + edges) | Good |
| $O(n^2)$ | Quadratic | Poor |

* **Worst-Case ($O$):** $O(V + E)$ — every vertex and edge is visited exactly once, where $V$ = number of vertices and $E$ = number of edges.
* **Best-Case ($\Omega$):** $\Omega(V + E)$ — regardless of structure, all vertices and edges must be examined.
* **Average-Case ($\Theta$):** $\Theta(V + E)$ — performance is consistent across all valid DAG inputs.


## ❓ Why We Use It

* **Dependency Resolution:** Package managers (e.g., `apt`, `pip`) use topological sorting to determine the install order of dependent packages.
* **Build Systems:** Tools like `make` or `cmake` use it to decide which source files to compile before others.
* **Course Scheduling:** Academic prerequisite systems can be modeled as a DAG; topological sort yields a valid course sequence.
* **Task Scheduling:** Any workflow where tasks have ordering constraints can be solved using topological sorting.


## ⚙️ How It Works

### Method 1: DFS-Based (Decrease and Conquer)

1. **Step 1:** Perform a Depth-First Search (DFS) on the DAG.
2. **Step 2:** After all neighbors of a vertex are fully explored (i.e., on the recursive return), push the vertex onto a **stack**.
3. **Step 3:** After DFS completes for all vertices, pop all elements from the stack — this produces the topological order.
4. **Step 4:** If a back edge is detected during DFS, a cycle exists and no topological order is possible.

### Method 2: Kahn's Algorithm (BFS / Source Removal)

1. **Step 1:** Compute the **in-degree** of every vertex.
2. **Step 2:** Enqueue all vertices with in-degree 0 (no prerequisites).
3. **Step 3:** Dequeue a vertex, add it to the result, and reduce the in-degree of all its neighbors by 1.
4. **Step 4:** If a neighbor's in-degree becomes 0, enqueue it.
5. **Step 5:** Repeat until the queue is empty. If the result contains fewer than $V$ vertices, a cycle exists.

The mathematical model for time complexity:
$$T(n) \approx O(V + E)$$


## 💻 Usage / Example

```c
// Example: Topological Sort using Kahn's Algorithm (BFS) in C
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 10

int adj[MAX][MAX]; // Adjacency matrix
int in_degree[MAX];
int n; // Number of vertices

void topological_sort() {
    int queue[MAX], front = 0, rear = 0;
    int result[MAX], count = 0;

    // Enqueue all vertices with in-degree 0
    for (int i = 0; i < n; i++) {
        if (in_degree[i] == 0)
            queue[rear++] = i;
    }

    while (front < rear) {
        int u = queue[front++];       // Dequeue
        result[count++] = u;

        for (int v = 0; v < n; v++) {
            if (adj[u][v]) {
                in_degree[v]--;       // Basic Operation: reduce in-degree
                if (in_degree[v] == 0)
                    queue[rear++] = v;
            }
        }
    }

    if (count != n) {
        printf("Cycle detected. No topological order exists.\n");
        return;
    }

    printf("Topological Order: ");
    for (int i = 0; i < count; i++)
        printf("%d ", result[i]);
    printf("\n");
}

int main() {
    n = 6;
    memset(adj, 0, sizeof(adj));
    memset(in_degree, 0, sizeof(in_degree));

    // Edges: 5->2, 5->0, 4->0, 4->1, 2->3, 3->1
    int edges[][2] = {{5,2},{5,0},{4,0},{4,1},{2,3},{3,1}};
    int m = 6;

    for (int i = 0; i < m; i++) {
        int u = edges[i][0], v = edges[i][1];
        adj[u][v] = 1;
        in_degree[v]++;
    }

    topological_sort();
    // Expected Output: 4 5 0 2 3 1  (one valid ordering)
    return 0;
}

// Complexity: O(V + E)
```


## References

* [Introduction to Algorithms (CLRS)](https://mitpress.mit.edu/9780262046305/) — Cormen et al., Chapter 22: Elementary Graph Algorithms.
* [Algorithm Design and Analysis — Levitin](https://www.pearson.com/en-us/subject-catalog/p/introduction-to-the-design-and-analysis-of-algorithms/P200000003415) — Anany Levitin, Chapter 5: Decrease-and-Conquer (DFS-based topological sort).
* [GeeksforGeeks – Topological Sorting](https://www.geeksforgeeks.org/topological-sorting/) — Visual walkthrough of both DFS and Kahn's Algorithm.
* [CP-Algorithms – Topological Sort](https://cp-algorithms.com/graph/topological-sort.html) — Formal treatment with proofs and edge-case handling.