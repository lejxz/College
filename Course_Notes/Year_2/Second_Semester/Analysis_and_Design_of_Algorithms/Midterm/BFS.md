# Breadth-First Search (BFS)

## 📋 Summary
* **Core Concept:** Breadth-First Search is a graph traversal algorithm that explores all vertices at the current distance from the source before advancing to vertices that are farther away, visiting the graph level by level.

> **Takeaways:** BFS is a core technique in the Analysis and Design of Algorithms course. It belongs to the **Decrease and Conquer** paradigm — at each step, the problem is reduced by processing one vertex and adding its unvisited neighbors to a queue. BFS is guaranteed to find the **shortest path** (in terms of edge count) between a source vertex and any reachable vertex in an **unweighted graph**. It produces a **BFS tree** that encodes these shortest-path distances. BFS is the foundation for algorithms such as Prim's MST (with modifications), bipartiteness testing, and level-order traversal in trees.


## 📖 Definition

* **Breadth-First Search (BFS):** A graph traversal algorithm that begins at a source vertex, visits all of its direct neighbors first, then all vertices two edges away, and so on, using a queue to manage the order of exploration.
* **BFS Tree:** The spanning tree formed by the edges that were used to discover new vertices during BFS. The path from the root to any vertex in this tree represents the shortest path (by edge count) in the original graph.
* **Level / Distance Layer:** The set of all vertices that are exactly $k$ edges away from the source vertex, for a given integer $k \geq 0$. BFS visits all vertices at level $k$ before any vertex at level $k+1$.
* **Shortest Path (Unweighted):** The path between two vertices with the fewest edges. BFS is guaranteed to find this path in any unweighted graph.
* **Requirements:**
    * The graph must be represented as an adjacency list or adjacency matrix.
    * A queue (FIFO) must be used to manage the traversal order.
    * A visited array or set must be maintained to prevent re-enqueuing vertices.
    * BFS computes shortest paths only for **unweighted** graphs. For weighted graphs, Dijkstra's algorithm is required.


## 📊 Complexity Analysis

| Notation | Name | Growth Rate |
| :--- | :--- | :--- |
| $O(1)$ | Constant | Excellent |
| $O(\log n)$ | Logarithmic | Very Good |
| $O(V + E)$ | Linear in Graph Size | Good |
| $O(V^2)$ | Quadratic | Poor |

Where $V$ = number of vertices, $E$ = number of edges.

* **Worst-Case ($O$):** $O(V + E)$ using an adjacency list. Every vertex is enqueued and dequeued once; every edge is examined once (twice for undirected graphs).
* **Best-Case ($\Omega$):** $\Omega(V + E)$ — BFS must process all vertices and edges to guarantee a complete traversal.
* **Average-Case ($\Theta$):** $\Theta(V + E)$ — BFS performs uniformly regardless of graph topology, as it always visits the full reachable set.

> **Representation Impact:**
> | Graph Representation | Time Complexity | Space Complexity |
> | :--- | :--- | :--- |
> | Adjacency List | $O(V + E)$ | $O(V)$ |
> | Adjacency Matrix | $O(V^2)$ | $O(V^2)$ |

> **BFS vs. DFS — Key Distinction:**
> | Property | BFS | DFS |
> | :--- | :--- | :--- |
> | Data Structure | Queue (FIFO) | Stack (LIFO) / Recursion |
> | Shortest Path | ✅ Guaranteed (unweighted) | ❌ Not guaranteed |
> | Space (worst case) | $O(V)$ — wide graphs | $O(V)$ — deep graphs |
> | Use Case | Shortest path, levels | Cycle detection, topological sort |


## ❓ Why We Use It

* **Shortest path in unweighted graphs:** BFS is the standard algorithm for finding the minimum number of edges between a source and any other vertex. This property is used in network routing, social network analysis, and puzzle solving.
* **Level-order traversal:** BFS visits vertices layer by layer, making it the natural choice for level-order traversal in trees (used in serialization, rendering, and breadth-first construction).
* **Bipartiteness testing:** A graph is bipartite if and only if it contains no odd-length cycle. BFS can detect this in $O(V + E)$ by attempting a 2-coloring during traversal.
* **Connected components:** BFS identifies all vertices reachable from a source, enabling efficient detection of connected components in an undirected graph.
* **Web crawling and peer-to-peer networks:** BFS models the natural layer-by-layer expansion used in web crawlers and distributed network discovery protocols.


## ⚙️ How It Works

1. **Step 1: Initialize** — Create a queue and enqueue the source vertex. Mark it as visited. Optionally maintain a distance array initialized to $\infty$ for all vertices except the source (distance $= 0$).
2. **Step 2: Dequeue and process** — Remove the front vertex from the queue. Examine all of its adjacent neighbors. This is the **Basic Operation**: dequeuing a vertex and inspecting each of its edges.
3. **Step 3: Enqueue unvisited neighbors** — For each unvisited neighbor, mark it as visited, record its distance as $d[current] + 1$, and enqueue it.
4. **Step 4: Repeat** — Continue until the queue is empty. At that point, all vertices reachable from the source have been visited in non-decreasing order of distance.
5. **Step 5: Set up the recurrence model:**
   $$T(V, E) = O(V + E)$$
   Each vertex is enqueued and dequeued exactly once; each edge $(u, v)$ is examined when $u$ is dequeued.
6. **Step 6: Derive order of growth** — Because each vertex and edge is processed a constant number of times:
   $$T(V, E) \in \Theta(V + E) \quad \text{(adjacency list)}$$


## 💻 Usage / Examples

### Example 1: Standard BFS Traversal

```python
from collections import deque

# BFS on an undirected graph using an adjacency list
def bfs(graph, start):
    visited = set([start])
    queue = deque([start])     # FIFO queue — core data structure for BFS

    while queue:
        vertex = queue.popleft()        # Basic Operation: dequeue and process
        print(vertex, end=" ")

        for neighbor in graph[vertex]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)  # Enqueue unvisited neighbor

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

bfs(graph, "A")
# Output: A B C D E F   (visited level by level)
# Complexity: O(V + E)
```

---

### Example 2: Shortest Path in an Unweighted Graph

```python
from collections import deque

# BFS to find the shortest path (by edge count) from source to target
def shortest_path(graph, source, target):
    visited = {source}
    queue = deque([(source, [source])])  # Queue stores (vertex, path so far)

    while queue:
        vertex, path = queue.popleft()   # Basic Operation: dequeue and inspect

        if vertex == target:
            return path                  # Shortest path found

        for neighbor in graph[vertex]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append((neighbor, path + [neighbor]))

    return None  # Target not reachable

graph = {
    "A": ["B", "C"],
    "B": ["A", "D", "E"],
    "C": ["A", "F"],
    "D": ["B"],
    "E": ["B"],
    "F": ["C"]
}

print(shortest_path(graph, "A", "F"))
# Output: ['A', 'C', 'F']
# Complexity: O(V + E)
```

---

### Example 3: Bipartiteness Testing via 2-Coloring

```python
from collections import deque

# BFS-based bipartite check: attempt to 2-color the graph
# A graph is bipartite if no two adjacent vertices share the same color
def is_bipartite(graph):
    color = {}

    for start in graph:
        if start in color:
            continue
        color[start] = 0              # Assign first color
        queue = deque([start])

        while queue:
            vertex = queue.popleft()  # Basic Operation: dequeue and inspect neighbors

            for neighbor in graph[vertex]:
                if neighbor not in color:
                    color[neighbor] = 1 - color[vertex]  # Assign opposite color
                    queue.append(neighbor)
                elif color[neighbor] == color[vertex]:   # Conflict: odd cycle found
                    return False
    return True

graph_bipartite = {
    "A": ["B", "D"],
    "B": ["A", "C"],
    "C": ["B", "D"],
    "D": ["A", "C"]
}

print(is_bipartite(graph_bipartite))  # Output: True
# Complexity: O(V + E)
```

---

### Example 4: Connected Components in an Undirected Graph

```python
from collections import deque

# BFS to identify all connected components
def connected_components(graph):
    visited = set()
    components = []

    for vertex in graph:
        if vertex not in visited:
            component = []
            queue = deque([vertex])
            visited.add(vertex)

            while queue:
                v = queue.popleft()   # Basic Operation: dequeue and process
                component.append(v)

                for neighbor in graph[v]:
                    if neighbor not in visited:
                        visited.add(neighbor)
                        queue.append(neighbor)

            components.append(component)

    return components

graph = {
    "A": ["B"],
    "B": ["A"],
    "C": ["D"],
    "D": ["C"],
    "E": []
}

print(connected_components(graph))
# Output: [['A', 'B'], ['C', 'D'], ['E']]
# Complexity: O(V + E)
```


## References

* [Introduction to the Design and Analysis of Algorithms] — Anany Levitin, Chapter 5: Decrease-and-Conquer (Graph Traversals).
* [Introduction to Algorithms (CLRS)] — Cormen, Leiserson, Rivest, Stein, Chapter 22: Elementary Graph Algorithms.
* [Breadth-First Search — Wikipedia](https://en.wikipedia.org/wiki/Breadth-first_search) — Formal definition, BFS tree, and shortest path proof.
* [BFS — GeeksforGeeks](https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/) — Implementation guide with visual step-by-step examples.