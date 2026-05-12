# Prim's Algorithm

## 📋 Summary

* **Core Concept:** Prim's Algorithm constructs a Minimum Spanning Tree (MST) by greedily growing a single connected tree, one minimum-weight edge at a time, starting from an arbitrary source vertex.

> **Takeaways:** Prim's is ideal for **dense graphs** (many edges relative to vertices). It always produces a valid MST and never forms a cycle, because it only connects vertices not yet in the tree. The algorithm is a direct application of the **greedy paradigm** — at every step, it picks the cheapest safe edge available.


## 📖 Definition

* **Spanning Tree:** A subgraph of a connected, undirected, weighted graph that includes **all vertices** with exactly **V − 1 edges** and no cycles.
* **Minimum Spanning Tree (MST):** A spanning tree whose total edge weight is the smallest possible among all spanning trees of the graph.
* **Cut Property:** If an edge is the minimum-weight edge crossing any cut (partition of vertices into two sets), it is safe to include in the MST.
* **Priority Queue (Min-Heap):** A data structure that always extracts the element with the lowest key value in O(log n) time; used to efficiently select the next cheapest edge.
* **Requirements:**
    * The input graph must be **connected** (every vertex is reachable).
    * The graph must be **undirected**.
    * Edge weights must be **comparable** (numerical).


## 📊 Complexity Analysis

| Notation | Name | Growth Rate |
| :--- | :--- | :--- |
| $O(1)$ | Constant | Excellent |
| $O(\log n)$ | Logarithmic | Very Good |
| $O(n)$ | Linear | Good |
| $O(n \log n)$ | Linearithmic | Good |
| $O(n^2)$ | Quadratic | Poor |

* **Worst-Case ($O$):**
    * With a **binary min-heap + adjacency list:** $O(E \log V)$
    * With a **simple array (no heap):** $O(V^2)$ — preferred for dense graphs
* **Best-Case ($\Omega$):** $\Omega(E \log V)$ — the heap must still process every edge in the worst case; the best case is not practically distinct.
* **Average-Case ($\Theta$):** $\Theta(E \log V)$ for sparse graphs using a binary heap.

> **Space Complexity:** $O(V + E)$ for the adjacency list and priority queue storage.


## ❓ Why We Use It

* **Guaranteed optimal MST:** The cut property mathematically guarantees that the greedy edge selection always produces a globally optimal spanning tree.
* **Efficient on dense graphs:** The $O(V^2)$ array-based variant outperforms Kruskal's on dense graphs where $E \approx V^2$, because sorting $E$ edges in Kruskal's becomes expensive.
* **Naturally connected output:** Because the algorithm grows one single tree, the result is always connected — no post-processing step to merge components is needed.
* **Practical applications:** Network design (cable/fiber laying), cluster analysis, circuit board routing, and approximation algorithms for the Travelling Salesman Problem (TSP).


## ⚙️ How It Works

```
Visualization — Example Graph (5 vertices, 7 edges):

        2       3
    A ───── B ───── C
    │ ╲     │       │
   6│   4╲  5│      │1
    │     ╲  │       │
    D ───── E ───── F  (not shown — simplified to 5 nodes)
        1       4

Graph used below:
  Vertices: {A, B, C, D, E}
  Edges (u, v, weight):
    A-B: 2,  A-D: 6,  A-E: 4
    B-C: 3,  B-E: 5
    C-E: 1
    D-E: 1
```

**Step-by-step trace (source = A):**

```
Initial State:
  key[]  = {A:0, B:∞, C:∞, D:∞, E:∞}
  parent = {A:-, B:-, C:-, D:-, E:-}
  inMST  = {A:F, B:F, C:F, D:F, E:F}

── Iteration 1 ──────────────────────────────
  Extract min key → A (key=0), mark inMST[A]=T
  Update neighbors of A:
    B: key[B] = min(∞, 2) = 2,  parent[B] = A
    D: key[D] = min(∞, 6) = 6,  parent[D] = A
    E: key[E] = min(∞, 4) = 4,  parent[E] = A

  key[]  = {A:0✓, B:2, C:∞, D:6, E:4}

── Iteration 2 ──────────────────────────────
  Extract min key → B (key=2), mark inMST[B]=T
  Update neighbors of B (not yet in MST):
    C: key[C] = min(∞, 3) = 3,  parent[C] = B
    E: key[E] = min(4, 5) = 4   (no update, 5 > 4)

  key[]  = {A:0✓, B:2✓, C:3, D:6, E:4}

── Iteration 3 ──────────────────────────────
  Extract min key → C (key=3), mark inMST[C]=T
  Update neighbors of C (not yet in MST):
    E: key[E] = min(4, 1) = 1,  parent[E] = C  ← updated!

  key[]  = {A:0✓, B:2✓, C:3✓, D:6, E:1}

── Iteration 4 ──────────────────────────────
  Extract min key → E (key=1), mark inMST[E]=T
  Update neighbors of E (not yet in MST):
    D: key[D] = min(6, 1) = 1,  parent[D] = E  ← updated!

  key[]  = {A:0✓, B:2✓, C:3✓, D:1, E:1✓}

── Iteration 5 ──────────────────────────────
  Extract min key → D (key=1), mark inMST[D]=T
  No unvisited neighbors to update.

  key[]  = {A:0✓, B:2✓, C:3✓, D:1✓, E:1✓}

MST Edges (from parent[]):
  A → B  (weight 2)
  B → C  (weight 3)
  C → E  (weight 1)
  E → D  (weight 1)

Total MST Weight = 2 + 3 + 1 + 1 = 7

MST Structure:
  A ──2── B ──3── C
                  │
                  1
                  │
            D──1──E
```

**Formal Steps:**
1. **Initialize** all vertex keys to $\infty$; set the source key to $0$. Mark all vertices as not in MST. Insert all vertices into a min-priority queue.
2. **Extract** the vertex $u$ with the minimum key from the priority queue.
3. **Mark** $u$ as included in the MST.
4. **Relax** each neighbor $v$ of $u$: if $v$ is not in MST and $weight(u, v) < key[v]$, update $key[v] \leftarrow weight(u, v)$ and set $parent[v] \leftarrow u$.
5. **Repeat** steps 2–4 until the priority queue is empty.
6. **Reconstruct** the MST using the $parent[]$ array.


## 💻 Usage / Example

```c
#include <stdio.h>
#include <limits.h>

#define V 5   /* Number of vertices: A=0, B=1, C=2, D=3, E=4 */

/* Adjacency matrix: graph[u][v] = edge weight, 0 = no edge */
int graph[V][V] = {
    /* A  B  C  D  E */
    {  0, 2, 0, 6, 4 },  /* A */
    {  2, 0, 3, 0, 5 },  /* B */
    {  0, 3, 0, 0, 1 },  /* C */
    {  6, 0, 0, 0, 1 },  /* D */
    {  4, 5, 1, 1, 0 },  /* E */
};

char labels[V] = {'A', 'B', 'C', 'D', 'E'};

/* Returns the vertex with the minimum key value not yet in MST */
int min_key(int key[], int in_mst[]) {
    int min = INT_MAX, min_idx = -1;
    for (int v = 0; v < V; v++)           /* Basic Operation: Linear scan */
        if (!in_mst[v] && key[v] < min) {
            min     = key[v];
            min_idx = v;
        }
    return min_idx;
}

void prim(int src) {
    int key[V];     /* Minimum edge weight to reach each vertex    */
    int parent[V];  /* MST parent of each vertex                   */
    int in_mst[V];  /* Whether vertex is included in MST           */

    for (int i = 0; i < V; i++) {
        key[i]    = INT_MAX;
        in_mst[i] = 0;
        parent[i] = -1;
    }

    key[src] = 0;   /* Source vertex always picked first */

    for (int count = 0; count < V; count++) {
        int u = min_key(key, in_mst);  /* Extract vertex with minimum key */
        in_mst[u] = 1;

        /* Relax all adjacent vertices of u not yet in MST */
        for (int v = 0; v < V; v++) {
            if (graph[u][v] && !in_mst[v] && graph[u][v] < key[v]) {
                key[v]    = graph[u][v];
                parent[v] = u;
            }
        }
    }

    /* Print MST */
    int total = 0;
    printf("MST Edges:\n");
    for (int v = 0; v < V; v++) {
        if (parent[v] != -1) {
            printf("  %c -- %c  (weight %d)\n",
                   labels[parent[v]], labels[v], graph[parent[v]][v]);
            total += graph[parent[v]][v];
        }
    }
    printf("Total Weight: %d\n", total);
}

int main(void) {
    prim(0);  /* Source = A (index 0) */
    return 0;
}

/*
 * Output:
 *   MST Edges:
 *     A -- B  (weight 2)
 *     B -- C  (weight 3)
 *     E -- D  (weight 1)
 *     C -- E  (weight 1)
 *   Total Weight: 7
 *
 * Complexity: O(V^2) — adjacency matrix + linear min_key scan
 */
```


## References

* [Introduction to Algorithms, 4th ed.](https://mitpress.mit.edu/9780262046305/) — Cormen, Leiserson, Rivest, Stein (CLRS), Chapter 21: Minimum Spanning Trees.
* [GeeksforGeeks — Prim's Algorithm](https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/) — Implementation walkthrough and complexity analysis.
* [CP-Algorithms — Prim's Algorithm](https://cp-algorithms.com/graph/mst_prim.html) — Dense vs. sparse graph variants with proofs.
* [Algorithm Design] — Jon Kleinberg & Éva Tardos, Chapter 4: Greedy Algorithms (Cut Property proof).
