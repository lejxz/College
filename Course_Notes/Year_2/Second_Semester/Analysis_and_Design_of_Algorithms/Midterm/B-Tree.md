# B-Tree

## 📋 Summary
* **Core Concept:** A B-Tree is a self-balancing multi-way search tree designed to minimize disk I/O by storing multiple keys per node, maintaining all leaves at the same depth, and keeping nodes at least half full.

> **Takeaways:** Unlike AVL Trees (binary, in-memory), B-Trees are optimized for block-based storage systems such as databases and file systems. A single node can hold hundreds of keys, dramatically reducing the number of disk reads needed to locate data.


## 📖 Definition

* **B-Tree of order $t$ (minimum degree):** A rooted tree satisfying:
  1. Every node has at most $2t - 1$ keys.
  2. Every non-root node has at least $t - 1$ keys.
  3. The root has at least $1$ key (unless the tree is empty).
  4. All leaves are at the same depth.
  5. An internal node with $k$ keys has exactly $k + 1$ children.
* **Order / Minimum Degree ($t$):** The parameter controlling node capacity; typically chosen based on disk block size.
* **Split:** The operation that divides a full node ($2t - 1$ keys) into two nodes of $t - 1$ keys each, promoting the median key to the parent.
* **Merge:** The inverse operation used during deletion to combine two underfull sibling nodes.
* **Requirements:**
    * All leaves must be at the same depth (perfect balance).
    * Insertions and deletions must maintain the minimum fill requirement ($t - 1$ keys per node).


## 📊 Complexity Analysis

| Operation | Time Complexity |
| :--- | :--- |
| Search | $O(\log n)$ |
| Insert | $O(\log n)$ |
| Delete | $O(\log n)$ |
| Node Split/Merge | $O(t)$ |

* **Worst-Case ($O$):** $O(\log n)$ — tree height is bounded by $\lfloor \log_t \frac{n+1}{2} \rfloor$.
* **Best-Case ($\Omega$):** $\Omega(1)$ — target key is in the root node.
* **Average-Case ($\Theta$):** $\Theta(\log n)$ — consistent across all operations.
* **Height bound:**
  $$h \leq \log_t \frac{n+1}{2}$$


## ❓ Why We Use It

* **Database indexing:** B-Trees (and B+ Trees) are the standard index structure in relational databases (MySQL InnoDB, PostgreSQL) due to their low I/O cost.
* **File systems:** Used in NTFS, HFS+, and ext4 for directory and file metadata indexing.
* **Disk optimization:** Large node sizes mean fewer nodes are visited per operation, reducing expensive disk reads.
* **Balanced by design:** No explicit rebalancing step is needed; balance is maintained through splits and merges during insert and delete.


## ⚙️ How It Works

### Search
1. **Step 1:** Start at the root.
2. **Step 2:** Scan keys in the current node to find the appropriate child pointer.
3. **Step 3:** Recurse into the correct child subtree.
4. **Step 4:** Repeat until the key is found or a leaf is reached.

### Insertion
1. **Step 1:** Search for the correct leaf position.
2. **Step 2:** If the leaf node is not full ($< 2t - 1$ keys), insert directly.
3. **Step 3:** If the node is full, **split** it — promote the median key to the parent.
4. **Step 4:** Repeat split upward if necessary; if the root splits, the tree height increases by one.

### Deletion
1. **Step 1:** Locate the key.
2. **Step 2:** If the key is in a leaf and the leaf has $\geq t$ keys, delete directly.
3. **Step 3:** Otherwise, **rotate** a key from a sibling or **merge** nodes to restore the minimum fill before deleting.


## 💻 Usage / Example

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define T 2                      /* Minimum degree: nodes hold 1..3 keys */
#define MAX_KEYS  (2 * T - 1)   /* 3 keys max */
#define MAX_CHILD (2 * T)       /* 4 children max */

typedef struct BNode {
    int  keys[MAX_KEYS];
    struct BNode *child[MAX_CHILD];
    int  n;          /* Current number of keys */
    int  leaf;
} BNode;

BNode *new_node(int leaf) {
    BNode *nd = calloc(1, sizeof(BNode));
    nd->leaf = leaf;
    return nd;
}

/* Search for key in the subtree rooted at nd */
BNode *search(BNode *nd, int key, int *idx) {
    int i = 0;
    while (i < nd->n && key > nd->keys[i]) i++;
    if (i < nd->n && nd->keys[i] == key) { *idx = i; return nd; }
    if (nd->leaf) return NULL;
    return search(nd->child[i], key, idx);
}

/* Split child[i] of parent nd (child must be full) */
void split_child(BNode *nd, int i) {
    BNode *z = new_node(nd->child[i]->leaf);
    BNode *y = nd->child[i];
    z->n = T - 1;

    /* Copy the last (T-1) keys of y to z */
    for (int j = 0; j < T - 1; j++) z->keys[j] = y->keys[j + T];
    if (!y->leaf)
        for (int j = 0; j < T; j++) z->child[j] = y->child[j + T];
    y->n = T - 1;

    /* Shift children and keys of nd to make room */
    for (int j = nd->n; j >= i + 1; j--) nd->child[j + 1] = nd->child[j];
    nd->child[i + 1] = z;
    for (int j = nd->n - 1; j >= i; j--) nd->keys[j + 1] = nd->keys[j];
    nd->keys[i] = y->keys[T - 1];
    nd->n++;
}

/* Insert key into non-full node nd */
void insert_non_full(BNode *nd, int key) {
    int i = nd->n - 1;
    if (nd->leaf) {
        while (i >= 0 && nd->keys[i] > key) { nd->keys[i + 1] = nd->keys[i]; i--; }
        nd->keys[i + 1] = key;
        nd->n++;
    } else {
        while (i >= 0 && nd->keys[i] > key) i--;
        i++;
        if (nd->child[i]->n == MAX_KEYS) {
            split_child(nd, i);
            if (nd->keys[i] < key) i++;
        }
        insert_non_full(nd->child[i], key);
    }
}

typedef struct { BNode *root; } BTree;

BTree *btree_new(void) {
    BTree *t = malloc(sizeof(BTree));
    t->root = new_node(1);
    return t;
}

void btree_insert(BTree *t, int key) {
    BNode *r = t->root;
    if (r->n == MAX_KEYS) {               /* Root is full — grow tree */
        BNode *s = new_node(0);
        t->root = s;
        s->child[0] = r;
        split_child(s, 0);
        insert_non_full(s, key);
    } else {
        insert_non_full(r, key);
    }
}

int main(void) {
    BTree *t = btree_new();
    int vals[] = {10, 20, 5, 6, 12, 30, 7, 17};
    for (int i = 0; i < 8; i++) btree_insert(t, vals[i]);

    int idx;
    BNode *res = search(t->root, 6, &idx);
    printf("Search 6:  %s\n", res ? "found" : "not found");  /* found */
    res = search(t->root, 99, &idx);
    printf("Search 99: %s\n", res ? "found" : "not found");  /* not found */

    /* Complexity: O(log n) per operation */
    return 0;
}
```


## References

* [Introduction to Algorithms (CLRS)](https://mitpress.mit.edu/9780262046305/introduction-to-algorithms/) — Cormen et al., Chapter 18: B-Trees.
* [Introduction to the Design and Analysis of Algorithms](https://www.amazon.com/Introduction-Design-Analysis-Algorithms-3rd/dp/0132316811) — Anany Levitin, Chapter 6.
* [GeeksforGeeks — B-Tree](https://www.geeksforgeeks.org/introduction-of-b-tree-2/) — Full insertion and deletion walkthrough.
* [Wikipedia — B-Tree](https://en.wikipedia.org/wiki/B-tree) — History, variants (B+ Tree, B* Tree), and applications.
