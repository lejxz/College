# Kruskal's Algorithm

## 📋 Summary

* **Core Concept:** Kruskal's Algorithm builds a Minimum Spanning Tree (MST) by sorting all edges by weight and greedily adding the next cheapest edge, skipping any edge that would form a cycle, until exactly $V - 1$ edges are included.

> **Takeaways:** Kruskal's is best suited for **sparse graphs** (few edges). Its defining operation is **cycle detection via Union-Find (Disjoint Set Union)**, which keeps the algorithm efficient. Unlike Prim's, Kruskal's processes edges globally rather than growing a single connected tree — it merges multiple components until one remains.


## 📖 Definition

* **Minimum Spanning Tree (MST):** A spanning tree of a connected, undirected, weighted graph with the minimum possible total edge weight. It contains exactly $V - 1$ edges and no cycles.
* **Disjoint Set Union (DSU / Union-Find):** A data structure that tracks which vertices belong to the same connected component. Supports two core operations: `find(v)` (returns the representative/root of $v$'s component) and `union(u, v)` (merges the components of $u$ and $v$).
* **Path Compression:** An optimization in `find()` that flattens the tree by pointing every node directly to the root, keeping future lookups nearly $O(1)$.
* **Union by Rank:** An optimization in `union()` that always attaches the smaller tree under the root of the larger tree, preventing degenerate chain structures.
* **Cycle:** A path in a graph that starts and ends at the same vertex. In Kruskal's, an edge $(u, v)$ forms a cycle if and only if $u$ and $v$ already belong to the same component (i.e., `find(u) == find(v)`).
* **Requirements:**
    * The graph must be **connected** and **undirected**.
    * Edge weights must be **comparable** (numerical).
    * A **full edge list** must be available (edge-list representation preferred).


## 📊 Complexity Analysis

| Notation | Name | Growth Rate |
| :--- | :--- | :--- |
| $O(1)$ | Constant | Excellent |
| $O(\alpha(n))$ | Inverse Ackermann | Near-Constant |
| $O(\log n)$ | Logarithmic | Very Good |
| $O(n \log n)$ | Linearithmic | Good |
| $O(n^2)$ | Quadratic | Poor |

* **Worst-Case ($O$):** $O(E \log E)$ — dominated by the edge sorting step. Since $E \leq V^2$, this is equivalent to $O(E \log V)$.
* **Best-Case ($\Omega$):** $\Omega(E \log E)$ — sorting is unavoidable; the best case is not practically distinct.
* **Average-Case ($\Theta$):** $\Theta(E \log E)$ — Union-Find operations with path compression and union by rank run in nearly $O(\alpha(E))$ per operation (inverse Ackermann), making sorting the true bottleneck.

> **Space Complexity:** $O(V + E)$ for the edge list and DSU arrays.

> **Comparison with Prim's:**
> * Kruskal's: better for **sparse** graphs where $E \approx V$.
> * Prim's (heap): better for **dense** graphs where $E \approx V^2$.


## ❓ Why We Use It

* **Handles disconnected graphs during execution:** Kruskal's naturally works with forests; it merges components incrementally and is straightforward to extend for detecting whether the original graph is connected.
* **Simplicity of implementation:** Sorting edges and checking Union-Find membership is simpler to implement correctly than maintaining a priority queue over vertices (as in Prim's).
* **Optimal by the cycle property:** The cycle property guarantees that the minimum-weight edge crossing any cut that does not form a cycle is always safe to include — Kruskal's directly exploits this.
* **Practical applications:** Network infrastructure design, image segmentation (graph-cut methods), cluster analysis, and road/cable network planning where edge costs dominate.


## ⚙️ How It Works

```
Visualization — Example Graph (5 vertices, 7 edges):

  Vertices: {A, B, C, D, E}
  Edges sorted by weight:
    (C, E): 1
    (D, E): 1
    (A, B): 2
    (B, C): 3
    (A, E): 4
    (B, E): 5
    (A, D): 6

Initial DSU state (each vertex is its own component):
  Component: {A} {B} {C} {D} {E}
  MST edges: []   |   MST weight: 0

── Step 1: Edge (C, E) weight = 1 ───────────────
  find(C) = C,  find(E) = E  →  Different components ✓
  → ADD edge (C, E)
  union(C, E): merge {C} and {E} → {C, E}

  Components: {A} {B} {C, E} {D}
  MST edges: [(C,E,1)]   |   MST weight: 1

── Step 2: Edge (D, E) weight = 1 ───────────────
  find(D) = D,  find(E) = C  →  Different components ✓
  → ADD edge (D, E)
  union(D, E): merge {D} and {C, E} → {C, D, E}

  Components: {A} {B} {C, D, E}
  MST edges: [(C,E,1), (D,E,1)]   |   MST weight: 2

── Step 3: Edge (A, B) weight = 2 ───────────────
  find(A) = A,  find(B) = B  →  Different components ✓
  → ADD edge (A, B)
  union(A, B): merge {A} and {B} → {A, B}

  Components: {A, B} {C, D, E}
  MST edges: [(C,E,1),(D,E,1),(A,B,2)]   |   MST weight: 4

── Step 4: Edge (B, C) weight = 3 ───────────────
  find(B) = A,  find(C) = C  →  Different components ✓
  → ADD edge (B, C)
  union(B, C): merge {A, B} and {C, D, E} → {A, B, C, D, E}

  Components: {A, B, C, D, E}
  MST edges: [(C,E,1),(D,E,1),(A,B,2),(B,C,3)]   |   MST weight: 7

  MST now has V-1 = 4 edges → DONE ✓

── Remaining edges are skipped (not processed):
  (A,E): find(A)==find(E) → same component, SKIP (cycle)
  (B,E): same component, SKIP
  (A,D): same component, SKIP

Final MST:
  C ──1── E ──1── D
          │
   A──2──B──3──C    (C appears once; reformatted below)

  Correct MST structure:
    A ──2── B
            │
            3
            │
    D──1── E ──1── C

Total Weight = 1 + 1 + 2 + 3 = 7
```

**Formal Steps:**
1. **Create** a list of all edges in the graph. **Sort** the list in non-decreasing order by edge weight. This is the key operation: $O(E \log E)$.
2. **Initialize** a Disjoint Set Union (DSU) structure where each vertex is its own component: $parent[v] = v$, $rank[v] = 0$.
3. **Iterate** over each edge $(u, v, w)$ in sorted order:
    * Call `find(u)` and `find(v)` to get their component roots.
    * **If roots differ:** the edge is safe — add it to the MST, call `union(u, v)`, increment the edge count.
    * **If roots are equal:** adding this edge would form a cycle — **skip** it.
4. **Terminate** when the MST contains exactly $V - 1$ edges, or when all edges are exhausted.
5. **Output** the MST edge list and total weight.


## 💻 Usage / Example

```c
#include <stdio.h>
#include <stdlib.h>

#define V 5   /* Number of vertices */
#define E 7   /* Number of edges    */

typedef struct {
    int weight, u, v;
} Edge;

/* DSU arrays */
int parent[V];
int rank_[V];

void dsu_init(void) {
    for (int i = 0; i < V; i++) {
        parent[i] = i;
        rank_[i]  = 0;
    }
}

/* Path-compressed find — returns root of v's component */
int find(int v) {                          /* Basic Operation */
    if (parent[v] != v)
        parent[v] = find(parent[v]);       /* Path compression */
    return parent[v];
}

/* Union by rank — returns 1 if merged, 0 if already same component */
int unite(int u, int v) {
    int ru = find(u), rv = find(v);
    if (ru == rv) return 0;               /* Same component → cycle */
    if (rank_[ru] < rank_[rv]) { int t = ru; ru = rv; rv = t; }
    parent[rv] = ru;
    if (rank_[ru] == rank_[rv]) rank_[ru]++;
    return 1;
}

int cmp_edge(const void *a, const void *b) {
    return ((Edge *)a)->weight - ((Edge *)b)->weight;
}

void kruskal(Edge edges[E]) {
    qsort(edges, E, sizeof(Edge), cmp_edge);   /* Sort edges by weight */
    dsu_init();

    Edge mst[V - 1];
    int  mst_size    = 0;
    int  total_weight = 0;

    for (int i = 0; i < E && mst_size < V - 1; i++) {
        Edge e = edges[i];
        if (unite(e.u, e.v)) {             /* Add only if no cycle formed */
            mst[mst_size++]  = e;
            total_weight    += e.weight;
        }
    }

    /* Print MST */
    char labels[V] = {'A', 'B', 'C', 'D', 'E'};
    printf("MST Edges:\n");
    for (int i = 0; i < mst_size; i++)
        printf("  %c -- %c  (weight %d)\n",
               labels[mst[i].u], labels[mst[i].v], mst[i].weight);
    printf("Total Weight: %d\n", total_weight);
}

int main(void) {
    /* Vertices: A=0, B=1, C=2, D=3, E=4 */
    Edge edges[E] = {
        {2, 0, 1}, {6, 0, 3}, {4, 0, 4},   /* A-B, A-D, A-E */
        {3, 1, 2}, {5, 1, 4},               /* B-C, B-E       */
        {1, 2, 4},                           /* C-E            */
        {1, 3, 4},                           /* D-E            */
    };

    kruskal(edges);
    return 0;
}

/*
 * Output:
 *   MST Edges:
 *     C -- E  (weight 1)
 *     D -- E  (weight 1)
 *     A -- B  (weight 2)
 *     B -- C  (weight 3)
 *   Total Weight: 7
 *
 * Complexity: O(E log E) — qsort dominates
 * DSU operations: nearly O(α(V)) per call (inverse Ackermann)
 */
```


## References

* [Introduction to Algorithms, 4th ed.](https://mitpress.mit.edu/9780262046305/) — Cormen, Leiserson, Rivest, Stein (CLRS), Chapter 21: Minimum Spanning Trees (Kruskal's section).
* [GeeksforGeeks — Kruskal's Algorithm](https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/) — Implementation and Union-Find explanation.
* [CP-Algorithms — Minimum Spanning Tree: Kruskal](https://cp-algorithms.com/graph/mst_kruskal.html) — Formal proof via cycle property and DSU with complexity analysis.
* [Algorithm Design] — Jon Kleinberg & Éva Tardos, Chapter 4: Greedy Algorithms (Cycle Property and MST correctness proofs).
* [Data Structures and Algorithm Analysis in C, 3rd ed.] — Mark Allen Weiss, Chapter 9: Graph Algorithms.
