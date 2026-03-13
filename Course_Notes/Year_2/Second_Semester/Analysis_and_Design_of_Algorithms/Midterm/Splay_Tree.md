# Splay Tree

## 📋 Summary
* **Core Concept:** A Splay Tree is a self-adjusting BST that moves the most recently accessed node to the root through a sequence of rotations called **splaying**, achieving $O(\log n)$ amortized cost per operation without storing explicit balance information.

> **Takeaways:** Splay Trees exploit temporal locality — frequently accessed elements are kept near the root, making repeated access very fast. They require no extra storage per node (no height or color), but their worst-case per-operation cost is $O(n)$; only the amortized cost is $O(\log n)$.


## 📖 Definition

* **Splay Tree:** A self-adjusting BST where every access (search, insert, delete) is followed by a **splay** operation that moves the target node to the root.
* **Splay Operation:** A sequence of rotations (zig, zig-zig, zig-zag) applied bottom-up to bring a node to the root.
* **Amortized Complexity:** The average cost per operation over a sequence of $n$ operations, even if individual operations may be expensive.
* **Zig Step:** A single rotation used when the target node's parent is the root.
* **Zig-Zig Step:** Two same-direction rotations when the node and its parent are both left (or both right) children.
* **Zig-Zag Step:** Two opposite-direction rotations when the node is a right child of a left child (or vice versa).
* **Requirements:**
    * Must maintain BST ordering at all times.
    * Every access must be followed by a splay to guarantee amortized bounds.


## 📊 Complexity Analysis

| Operation | Amortized Cost | Worst-Case (Single Op) |
| :--- | :--- | :--- |
| Search | $O(\log n)$ | $O(n)$ |
| Insert | $O(\log n)$ | $O(n)$ |
| Delete | $O(\log n)$ | $O(n)$ |
| Splay | $O(\log n)$ | $O(n)$ |

* **Worst-Case ($O$):** $O(n)$ per single operation — a degenerate sequence of accesses on the deepest node.
* **Best-Case ($\Omega$):** $\Omega(1)$ — when the target is already the root.
* **Average-Case ($\Theta$):** $\Theta(\log n)$ amortized per operation over any sequence of $m$ operations:
  $$\text{Total cost of } m \text{ operations} = O(m \log n)$$


## ❓ Why We Use It

* **Cache-friendly:** Frequently accessed elements are automatically promoted near the root, reducing access time in workloads with temporal locality.
* **Simple implementation:** No balance information (height, color) is stored per node, simplifying node structure.
* **Practical performance:** Often outperforms AVL Trees and Red-Black Trees in real-world access patterns despite worse theoretical guarantees.
* **Useful in compilers and caches:** Symbol tables and LRU-like structures benefit from splay trees' self-organizing property.


## ⚙️ How It Works

1. **Step 1:** Perform a standard BST search for the target key.
2. **Step 2:** Apply the **splay** operation on the located node (or the last visited node if not found):
   - **Zig:** Target's parent is the root → single rotation.
   - **Zig-Zig:** Target and parent are same-side children → rotate parent first, then target.
   - **Zig-Zag:** Target and parent are opposite-side children → rotate target twice.
3. **Step 3:** Repeat zig/zig-zig/zig-zag until the target node becomes the root.
4. **Step 4 — Insert:** Splay the nearest key; split tree at root; attach new node as new root.
5. **Step 5 — Delete:** Splay the target to root; remove it; splay the maximum of the left subtree; attach right subtree.


## 💻 Usage / Example

```c
#include <stdio.h>
#include <stdlib.h>

typedef struct SNode {
    int key;
    struct SNode *left, *right;
} SNode;

SNode *new_snode(int key) {
    SNode *n = malloc(sizeof(SNode));
    n->key = key; n->left = n->right = NULL;
    return n;
}

SNode *right_rot(SNode *x) {
    SNode *y = x->left;
    x->left  = y->right;
    y->right = x;
    return y;
}

SNode *left_rot(SNode *x) {
    SNode *y = x->right;
    x->right = y->left;
    y->left  = x;
    return y;
}

/* Splay: bring the node with key to root (top-down style) */
SNode *splay(SNode *root, int key) {
    if (!root || root->key == key) return root;

    if (key < root->key) {
        if (!root->left) return root;
        if (key < root->left->key) {          /* Zig-Zig (left-left) */
            root->left->left = splay(root->left->left, key);
            root = right_rot(root);
        } else if (key > root->left->key) {   /* Zig-Zag (left-right) */
            root->left->right = splay(root->left->right, key);
            if (root->left->right) root->left = left_rot(root->left);
        }
        return root->left ? right_rot(root) : root;
    } else {
        if (!root->right) return root;
        if (key > root->right->key) {         /* Zig-Zig (right-right) */
            root->right->right = splay(root->right->right, key);
            root = left_rot(root);
        } else if (key < root->right->key) {  /* Zig-Zag (right-left) */
            root->right->left = splay(root->right->left, key);
            if (root->right->left) root->right = right_rot(root->right);
        }
        return root->right ? left_rot(root) : root;
    }
}

SNode *splay_insert(SNode *root, int key) {
    if (!root) return new_snode(key);
    root = splay(root, key);
    if (root->key == key) return root;        /* Already exists */

    SNode *node = new_snode(key);
    if (key < root->key) {
        node->right = root;
        node->left  = root->left;
        root->left  = NULL;
    } else {
        node->left  = root;
        node->right = root->right;
        root->right = NULL;
    }
    return node;
}

int main(void) {
    SNode *root = NULL;
    int keys[] = {10, 20, 30, 15, 5};
    for (int i = 0; i < 5; i++) root = splay_insert(root, keys[i]);

    /* Access 15 — splays it to root */
    root = splay(root, 15);
    printf("Root after accessing 15: %d\n", root->key); /* 15 */

    /* Access 10 — splays it to root */
    root = splay(root, 10);
    printf("Root after accessing 10: %d\n", root->key); /* 10 */

    /* Amortized Complexity: O(log n) per operation */
    return 0;
}
```


## References

* [Introduction to Algorithms (CLRS)](https://mitpress.mit.edu/9780262046305/introduction-to-algorithms/) — Cormen et al., Chapter 17 (Amortized Analysis).
* [Sleator & Tarjan (1985)](https://dl.acm.org/doi/10.1145/3828.3835) — Original paper introducing Splay Trees.
* [GeeksforGeeks — Splay Tree](https://www.geeksforgeeks.org/splay-tree-set-1-insert/) — Step-by-step implementation with all three cases.
* [Wikipedia — Splay Tree](https://en.wikipedia.org/wiki/Splay_tree) — Amortized analysis and applications.
