# Depth-First Search (DFS)

## 📋 Summary
* **Core Concept:** Depth-First Search is a graph traversal algorithm that explores as far as possible along each branch before backtracking, systematically visiting every vertex and edge in a graph.

> **Takeaways:** DFS is a foundational technique in the Analysis and Design of Algorithms course. It belongs to the **Decrease and Conquer** paradigm — each recursive call reduces the problem by processing one vertex and deferring the rest. DFS produces two key structures: the **DFS forest** (a spanning tree or forest of traversal paths) and **discovery/finish timestamps** that reveal the structural properties of the graph. It serves as the basis for topological sorting, cycle detection, and strongly connected component algorithms.


## 📖 Definition

* **Depth-First Search (DFS):** A graph traversal algorithm that starts at a source vertex, marks it as visited, and recursively visits all unvisited adjacent vertices before backtracking. It continues until all vertices reachable from the source have been visited.
* **DFS Forest:** The collection of DFS trees produced when DFS is applied to every unvisited vertex in a graph. Each tree in the forest corresponds to one connected component (in an undirected graph) or one DFS call on an unvisited vertex (in a directed graph).
* **Back Edge:** An edge $(u, v)$ in a directed graph where $v$ is an ancestor of $u$ in the DFS tree. The presence of a back edge indicates a cycle.
* **Topological Order:** A linear ordering of vertices in a Directed Acyclic Graph (DAG) such that for every directed edge $(u, v)$, vertex $u$ appears before $v$. DFS produces this ordering via reverse finishing times.
* **Requirements:**
    * The graph must be represented as an adjacency list or adjacency matrix.
    * A visited array or set must be maintained to prevent revisiting vertices.
    * The graph may be directed or undirected, connected or disconnected.


## 📊 Complexity Analysis

| Notation | Name | Growth Rate |
| :--- | :--- | :--- |
| $O(1)$ | Constant | Excellent |
| $O(\log n)$ | Logarithmic | Very Good |
| $O(V + E)$ | Linear in Graph Size | Good |
| $O(V^2)$ | Quadratic | Poor |

Where $V$ = number of vertices, $E$ = number of edges.

* **Worst-Case ($O$):** $O(V + E)$ using an adjacency list. Every vertex is visited once and every edge is examined once.
* **Best-Case ($\Omega$):** $\Omega(V + E)$ — DFS must visit all vertices and edges regardless of graph structure.
* **Average-Case ($\Theta$):** $\Theta(V + E)$ — DFS has no favorable "early exit" path; it always performs a full traversal.

> **Representation Impact:**
> | Graph Representation | Time Complexity | Space Complexity |
> | :--- | :--- | :--- |
> | Adjacency List | $O(V + E)$ | $O(V + E)$ |
> | Adjacency Matrix | $O(V^2)$ | $O(V^2)$ |


## ❓ Why We Use It

* **Graph structure discovery:** DFS reveals whether a graph is connected, how many components it has, and what the reachability relationships between vertices are.
* **Cycle detection:** In a directed graph, the presence of a back edge during DFS confirms the existence of a cycle — critical in dependency analysis and deadlock detection.
* **Topological sorting:** DFS is the standard algorithm for producing a topological order of a DAG, used in task scheduling, build systems, and compiler dependency resolution.
* **Strongly Connected Components (SCCs):** Algorithms such as Kosaraju's and Tarjan's are built entirely on DFS and identify SCCs in directed graphs in $O(V + E)$ time.
* **Pathfinding in mazes and trees:** DFS naturally models depth-first exploration, making it suitable for maze solving and exhaustive tree search.


## ⚙️ How It Works

1. **Step 1: Initialize** — Mark all vertices as unvisited. Maintain a `visited` set and optionally a `stack` (for iterative DFS) or use the call stack (for recursive DFS).
2. **Step 2: Select a starting vertex** — Begin DFS from any unvisited vertex. In the context of a full graph traversal, iterate over all vertices and call DFS on each unvisited one to handle disconnected graphs.
3. **Step 3: Visit and recurse** — Mark the current vertex as visited. For each adjacent unvisited neighbor, recursively call DFS. This is the **Basic Operation**: checking whether a neighbor has been visited and making the recursive call.
4. **Step 4: Backtrack** — When a vertex has no unvisited neighbors, the recursion returns (backtracks) to the previous vertex and continues with the next unvisited neighbor.
5. **Step 5: Set up the recurrence model:**
   $$T(V, E) = O(V + E)$$
   Each vertex is pushed and popped once; each edge is examined once (twice for undirected graphs).
6. **Step 6: Derive order of growth** — Since each vertex and edge is processed a constant number of times, the total work is linear in the size of the graph:
   $$T(V, E) \in \Theta(V + E) \quad \text{(adjacency list)}$$


## 💻 Usage / Examples

### Example 1: Recursive DFS Traversal

```python
# DFS on an undirected graph using an adjacency list
def dfs(graph, vertex, visited=None):
    if visited is None:
        visited = set()

    visited.add(vertex)          # Mark current vertex as visited
    print(vertex, end=" ")

    for neighbor in graph[vertex]:          # Basic Operation: visit each neighbor
        if neighbor not in visited:         # Check: unvisited neighbor
            dfs(graph, neighbor, visited)   # Decrease: recurse on smaller unvisited set

    return visited

# Example graph (undirected)
graph = {
    "A": ["B", "C"],
    "B": ["A", "D", "E"],
    "C": ["A", "F"],
    "D": ["B"],
    "E": ["B"],
    "F": ["C"]
}

dfs(graph, "A")
# Output: A B D E C F
# Complexity: O(V + E)
```

---

### Example 2: Iterative DFS Using an Explicit Stack

```python
# Iterative DFS — avoids Python recursion depth limits on large graphs
def dfs_iterative(graph, start):
    visited = set()
    stack = [start]             # Use an explicit stack

    while stack:
        vertex = stack.pop()    # Basic Operation: pop and process vertex
        if vertex not in visited:
            visited.add(vertex)
            print(vertex, end=" ")
            for neighbor in reversed(graph[vertex]):  # Reverse to match recursive order
                if neighbor not in visited:
                    stack.append(neighbor)

    return visited

dfs_iterative(graph, "A")
# Output: A B D E C F
# Complexity: O(V + E)
```

---

### Example 3: Cycle Detection in a Directed Graph

```python
# Detect a cycle in a directed graph using DFS and a recursion stack
def has_cycle(graph):
    visited = set()
    rec_stack = set()  # Tracks vertices in the current DFS path

    def dfs_cycle(v):
        visited.add(v)
        rec_stack.add(v)

        for neighbor in graph.get(v, []):
            if neighbor not in visited:
                if dfs_cycle(neighbor):   # Basic Operation: recursive DFS call
                    return True
            elif neighbor in rec_stack:   # Back edge found — cycle detected
                return True

        rec_stack.remove(v)
        return False

    for vertex in graph:
        if vertex not in visited:
            if dfs_cycle(vertex):
                return True
    return False

directed_graph = {
    "A": ["B"],
    "B": ["C"],
    "C": ["A"],  # Back edge: C -> A creates a cycle
    "D": ["B"]
}

print(has_cycle(directed_graph))  # Output: True
# Complexity: O(V + E)
```

---

### Example 4: Topological Sort via DFS

```python
# Topological Sort using DFS on a Directed Acyclic Graph (DAG)
# Vertices are appended to a stack in reverse finishing order
def topological_sort(graph):
    visited = set()
    result_stack = []

    def dfs_topo(v):
        visited.add(v)
        for neighbor in graph.get(v, []):
            if neighbor not in visited:
                dfs_topo(neighbor)          # Basic Operation: recursive DFS call
        result_stack.append(v)              # Append after all descendants are visited

    for vertex in graph:
        if vertex not in visited:
            dfs_topo(vertex)

    return result_stack[::-1]  # Reverse for correct topological order

dag = {
    "A": ["C"],
    "B": ["C", "D"],
    "C": ["E"],
    "D": ["F"],
    "E": [],
    "F": []
}

print(topological_sort(dag))
# Output: ['B', 'A', 'D', 'C', 'F', 'E'] (one valid ordering)
# Complexity: O(V + E)
```


## References

* [Introduction to the Design and Analysis of Algorithms] — Anany Levitin, Chapter 5: Decrease-and-Conquer (Graph Traversals).
* [Introduction to Algorithms (CLRS)] — Cormen, Leiserson, Rivest, Stein, Chapter 22: Elementary Graph Algorithms.
* [Depth-First Search — Wikipedia](https://en.wikipedia.org/wiki/Depth-first_search) — Formal definition, edge classification, and pseudocode.
* [DFS — GeeksforGeeks](https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/) — Implementation guide with visual examples.