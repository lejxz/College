# Red-Black Tree

## 📋 Summary
* **Core Concept:** A Red-Black Tree is a self-balancing BST that assigns each node a color (red or black) and enforces five structural properties to guarantee the tree's height is always $O(\log n)$, ensuring worst-case $O(\log n)$ operations.

> **Takeaways:** Red-Black Trees are the standard choice for implementing ordered maps and sets in most programming languages (Java `TreeMap`, C++ `std::map`). They offer better worst-case insertion/deletion performance than AVL Trees in exchange for slightly more complex rebalancing logic.


## 📖 Definition

* **Red-Black Tree:** A BST where every node is colored red or black, and the following five properties are always maintained:
  1. **Color Property:** Every node is either red or black.
  2. **Root Property:** The root is always black.
  3. **Leaf (NIL) Property:** Every NIL leaf node is black.
  4. **Red Property:** A red node's children must both be black (no two consecutive red nodes on any path).
  5. **Black-Height Property:** All paths from a given node to its NIL descendants contain the same number of black nodes.
* **Black-Height ($\text{bh}(v)$):** The number of black nodes on any path from node $v$ (exclusive) to a NIL leaf.
* **Recoloring:** Changing a node's color to restore violated properties after insert or delete.
* **Rotation:** A local restructuring (left or right rotate) used together with recoloring to restore Red-Black properties.
* **Requirements:**
    * All five Red-Black properties must hold after every insertion and deletion.
    * NIL nodes (sentinels) are treated as black leaves.


## 📊 Complexity Analysis

| Operation | Time Complexity |
| :--- | :--- |
| Search | $O(\log n)$ |
| Insert | $O(\log n)$ |
| Delete | $O(\log n)$ |
| Rotation | $O(1)$ |

* **Worst-Case ($O$):** $O(\log n)$ — height is at most $2 \log_2(n + 1)$, guaranteed by the black-height property.
* **Best-Case ($\Omega$):** $\Omega(1)$ — root access.
* **Average-Case ($\Theta$):** $\Theta(\log n)$ — consistent for all standard operations.
* **Height bound:**
  $$h \leq 2 \log_2(n + 1)$$


## ❓ Why We Use It

* **Worst-case guarantees:** Unlike AVL Trees, Red-Black Trees perform at most 3 rotations per insertion and at most 3 rotations per deletion, making them more efficient for write-heavy workloads.
* **Standard library choice:** Used to implement `std::map`/`std::set` in C++, `TreeMap`/`TreeSet` in Java, and Linux kernel data structures (e.g., the CFS process scheduler uses an RB-Tree).
* **Balanced trade-off:** Slightly less balanced than AVL (which allows faster reads) but faster rebalancing, making Red-Black Trees better for mixed read-write workloads.


## ⚙️ How It Works

### Insertion
1. **Step 1:** Insert the node using standard BST insertion; color it **red**.
2. **Step 2:** If the parent is black, no property is violated — done.
3. **Step 3:** If the parent is red (violation of the Red Property), fix using cases:
   - **Case 1 — Uncle is red:** Recolor parent and uncle to black; recolor grandparent to red; recurse upward.
   - **Case 2 — Uncle is black, node is inner child:** Rotate parent to convert to Case 3.
   - **Case 3 — Uncle is black, node is outer child:** Rotate grandparent; recolor.
4. **Step 4:** Ensure the root is black.

### Deletion
1. **Step 1:** Perform BST deletion; if the deleted node or its replacement is red, recolor black — done.
2. **Step 2:** If a black node is deleted, a **double-black** deficit must be resolved using four symmetric cases involving sibling color and sibling's children colors.
3. **Step 3:** Propagate fixes upward until the root is reached or the deficit is resolved.


## 💻 Usage / Example

```c
#include <stdio.h>
#include <stdlib.h>

#define RED   0
#define BLACK 1

typedef struct RBNode {
    int key, color;
    struct RBNode *left, *right, *parent;
} RBNode;

/* Global NIL sentinel (black leaf) */
static RBNode NIL_NODE = {0, BLACK, NULL, NULL, NULL};
static RBNode *NIL = &NIL_NODE;

RBNode *new_rb(int key) {
    RBNode *n   = malloc(sizeof(RBNode));
    n->key      = key;
    n->color    = RED;        /* New nodes start red */
    n->left = n->right = n->parent = NIL;
    return n;
}

void left_rotate(RBNode **root, RBNode *x) {
    RBNode *y   = x->right;
    x->right    = y->left;
    if (y->left != NIL) y->left->parent = x;
    y->parent   = x->parent;
    if (x->parent == NIL)       *root = y;
    else if (x == x->parent->left) x->parent->left  = y;
    else                           x->parent->right = y;
    y->left     = x;
    x->parent   = y;
}

void right_rotate(RBNode **root, RBNode *x) {
    RBNode *y   = x->left;
    x->left     = y->right;
    if (y->right != NIL) y->right->parent = x;
    y->parent   = x->parent;
    if (x->parent == NIL)        *root = y;
    else if (x == x->parent->right) x->parent->right = y;
    else                             x->parent->left  = y;
    y->right    = x;
    x->parent   = y;
}

void rb_fix(RBNode **root, RBNode *z) {
    while (z->parent->color == RED) {
        if (z->parent == z->parent->parent->left) {
            RBNode *y = z->parent->parent->right;   /* Uncle */
            if (y->color == RED) {                  /* Case 1 */
                z->parent->color          = BLACK;
                y->color                  = BLACK;
                z->parent->parent->color  = RED;
                z = z->parent->parent;
            } else {
                if (z == z->parent->right) {        /* Case 2 */
                    z = z->parent;
                    left_rotate(root, z);
                }
                z->parent->color         = BLACK;   /* Case 3 */
                z->parent->parent->color = RED;
                right_rotate(root, z->parent->parent);
            }
        } else {                                    /* Mirror cases */
            RBNode *y = z->parent->parent->left;
            if (y->color == RED) {
                z->parent->color          = BLACK;
                y->color                  = BLACK;
                z->parent->parent->color  = RED;
                z = z->parent->parent;
            } else {
                if (z == z->parent->left) {
                    z = z->parent;
                    right_rotate(root, z);
                }
                z->parent->color         = BLACK;
                z->parent->parent->color = RED;
                left_rotate(root, z->parent->parent);
            }
        }
    }
    (*root)->color = BLACK;                         /* Root always black */
}

void rb_insert(RBNode **root, int key) {
    RBNode *z = new_rb(key);
    RBNode *y = NIL, *x = *root;
    while (x != NIL) {
        y = x;
        x = (z->key < x->key) ? x->left : x->right;
    }
    z->parent = y;
    if (y == NIL)            *root     = z;
    else if (z->key < y->key) y->left  = z;
    else                       y->right = z;
    rb_fix(root, z);
}

/* In-order traversal */
void inorder(RBNode *n) {
    if (n == NIL) return;
    inorder(n->left);
    printf("%d(%s) ", n->key, n->color == RED ? "R" : "B");
    inorder(n->right);
}

int main(void) {
    RBNode *root = NIL;
    int keys[] = {10, 20, 30, 15, 5, 25};
    for (int i = 0; i < 6; i++) rb_insert(&root, keys[i]);

    printf("In-order: ");
    inorder(root);
    printf("\n");
    printf("Root: %d (must be BLACK)\n", root->key);

    /* Complexity: O(log n) per operation */
    return 0;
}
```


## References

* [Introduction to Algorithms (CLRS)](https://mitpress.mit.edu/9780262046305/introduction-to-algorithms/) — Cormen et al., Chapter 13: Red-Black Trees.
* [Introduction to the Design and Analysis of Algorithms](https://www.amazon.com/Introduction-Design-Analysis-Algorithms-3rd/dp/0132316811) — Anany Levitin, Chapter 6.
* [GeeksforGeeks — Red-Black Tree](https://www.geeksforgeeks.org/red-black-tree-set-1-introduction-2/) — Complete insertion and deletion case analysis.
* [Wikipedia — Red-Black Tree](https://en.wikipedia.org/wiki/Red%E2%80%93black_tree) — Formal properties and comparison with AVL Trees.
