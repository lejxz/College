# AVL Trees and Heaps

## 📋 Summary
* **Core Concept:** AVL Trees are self-balancing Binary Search Trees that maintain an $O(\log n)$ height guarantee through rotations; Heaps are complete binary trees satisfying the heap property, primarily used for priority queues and sorting.

> **Takeaways:** Both data structures are representation-change techniques that impose additional structural invariants on top of simpler structures (BST and array, respectively) to guarantee efficient operations. AVL Trees optimize search/insert/delete; Heaps optimize min/max retrieval.


## 📖 Definition

* **AVL Tree:** A self-balancing BST where the **balance factor** of every node (defined as height(left subtree) $-$ height(right subtree)) must be in $\{-1, 0, +1\}$.
* **Balance Factor:** $\text{BF}(v) = h(\text{left}(v)) - h(\text{right}(v))$; a value outside $[-1, 1]$ triggers a rotation.
* **Rotation:** A local restructuring operation (single or double) that restores the AVL balance invariant after an insertion or deletion.
* **Heap:** A complete binary tree where every node satisfies the **heap property**:
  - *Max-Heap:* $\text{parent} \geq \text{children}$
  - *Min-Heap:* $\text{parent} \leq \text{children}$
* **Heapify:** The process of restoring the heap property after insertion or deletion, also used to build a heap from an unsorted array in $O(n)$.
* **Requirements (AVL):**
    * Must maintain BST ordering at all times.
    * Rebalancing must occur after every insertion and deletion.
* **Requirements (Heap):**
    * Must be a complete binary tree (filled level by level, left to right).
    * Must satisfy the heap property at every node.


## 📊 Complexity Analysis

### AVL Tree

| Operation | Time Complexity |
| :--- | :--- |
| Search | $O(\log n)$ |
| Insert | $O(\log n)$ |
| Delete | $O(\log n)$ |
| Rotation | $O(1)$ |

### Heap

| Operation | Time Complexity |
| :--- | :--- |
| Insert | $O(\log n)$ |
| Extract-Min/Max | $O(\log n)$ |
| Peek Min/Max | $O(1)$ |
| Build Heap | $O(n)$ |

* **Worst-Case ($O$):** $O(\log n)$ for both AVL and Heap — height is bounded by $O(\log n)$ due to structural invariants.
* **Best-Case ($\Omega$):** $\Omega(1)$ for peek (Heap) and search on root (AVL).
* **Average-Case ($\Theta$):** $\Theta(\log n)$ for all standard operations in both structures.


## ❓ Why We Use It

* **AVL Trees:** Preferred when frequent search, insert, and delete operations must all be $O(\log n)$ in the worst case (unlike standard BSTs which degrade to $O(n)$).
* **Heaps:** Optimal for priority queues, scheduling systems, and Heapsort ($O(n \log n)$).
* **Efficiency guarantee:** Both structures provide worst-case logarithmic operations through structural invariants, unlike hash tables which only guarantee average-case performance.


## ⚙️ How It Works

### AVL Tree — Insertion
1. **Step 1:** Insert the node using standard BST insertion.
2. **Step 2:** Update height values on the path from inserted node to root.
3. **Step 3:** Check balance factors on the return path.
4. **Step 4:** If $|\text{BF}| > 1$, apply the appropriate rotation:
   - **Left-Left (LL):** Single right rotation.
   - **Right-Right (RR):** Single left rotation.
   - **Left-Right (LR):** Left rotation on child, then right rotation on node.
   - **Right-Left (RL):** Right rotation on child, then left rotation on node.

### Heap — Insert and Extract
1. **Insert:** Append element at the end of the array; **sift up** by swapping with parent while heap property is violated.
2. **Extract-Max/Min:** Swap root with last element; remove last; **sift down** (heapify) from root.
3. **Build Heap:** Apply heapify bottom-up from $\lfloor n/2 \rfloor$ down to $0$:
   $$T_{\text{build}} = O(n)$$


## 💻 Usage / Example

```c
#include <stdio.h>

/* ─────────────────────────────────────────
   Min-Heap using an array (max 64 elements)
   ───────────────────────────────────────── */
#define MAX_HEAP 64

typedef struct {
    int data[MAX_HEAP];
    int size;
} MinHeap;

void swap(int *a, int *b) { int t = *a; *a = *b; *b = t; }

void heap_push(MinHeap *h, int val) {
    int i = h->size++;
    h->data[i] = val;
    /* Sift up */
    while (i > 0 && h->data[(i - 1) / 2] > h->data[i]) {
        swap(&h->data[(i - 1) / 2], &h->data[i]);
        i = (i - 1) / 2;
    }
}

int heap_pop(MinHeap *h) {
    int min = h->data[0];
    h->data[0] = h->data[--h->size];
    /* Sift down */
    int i = 0;
    while (1) {
        int l = 2 * i + 1, r = 2 * i + 2, smallest = i;
        if (l < h->size && h->data[l] < h->data[smallest]) smallest = l;
        if (r < h->size && h->data[r] < h->data[smallest]) smallest = r;
        if (smallest == i) break;
        swap(&h->data[i], &h->data[smallest]);
        i = smallest;
    }
    return min;
}

/* ─────────────────────────────────────────
   AVL Tree node and insert
   ───────────────────────────────────────── */
typedef struct AVLNode {
    int key, height;
    struct AVLNode *left, *right;
} AVLNode;

int height(AVLNode *n) { return n ? n->height : 0; }
int max2(int a, int b) { return a > b ? a : b; }
int balance(AVLNode *n) { return n ? height(n->left) - height(n->right) : 0; }

AVLNode *new_node(int key) {
    AVLNode *n = malloc(sizeof(AVLNode));
    n->key = key; n->height = 1; n->left = n->right = NULL;
    return n;
}

AVLNode *right_rot(AVLNode *z) {
    AVLNode *y = z->left, *T3 = y->right;
    y->right = z; z->left = T3;
    z->height = 1 + max2(height(z->left), height(z->right));
    y->height = 1 + max2(height(y->left), height(y->right));
    return y;
}

AVLNode *left_rot(AVLNode *z) {
    AVLNode *y = z->right, *T2 = y->left;
    y->left = z; z->right = T2;
    z->height = 1 + max2(height(z->left), height(z->right));
    y->height = 1 + max2(height(y->left), height(y->right));
    return y;
}

AVLNode *avl_insert(AVLNode *node, int key) {
    if (!node) return new_node(key);
    if      (key < node->key) node->left  = avl_insert(node->left,  key);
    else if (key > node->key) node->right = avl_insert(node->right, key);
    else return node; /* duplicates not allowed */

    node->height = 1 + max2(height(node->left), height(node->right));
    int bf = balance(node);

    if (bf > 1  && key < node->left->key)  return right_rot(node);       /* LL */
    if (bf < -1 && key > node->right->key) return left_rot(node);        /* RR */
    if (bf > 1  && key > node->left->key)  { node->left  = left_rot(node->left);  return right_rot(node); } /* LR */
    if (bf < -1 && key < node->right->key) { node->right = right_rot(node->right); return left_rot(node); } /* RL */
    return node;
}

#include <stdlib.h>

int main(void) {
    /* Min-Heap demo */
    MinHeap h = {.size = 0};
    int vals[] = {5, 3, 8, 1, 4};
    for (int i = 0; i < 5; i++) heap_push(&h, vals[i]);
    printf("Extract min: %d\n", heap_pop(&h)); /* 1 */
    printf("Peek min: %d\n",    h.data[0]);    /* 3 */

    /* AVL Tree demo */
    AVLNode *root = NULL;
    int keys[] = {10, 20, 30, 40, 50, 25};
    for (int i = 0; i < 6; i++) root = avl_insert(root, keys[i]);
    printf("AVL root after insertions: %d\n", root->key); /* 30 */

    /* Complexity: O(log n) per operation for both */
    return 0;
}
```


## References

* [Introduction to the Design and Analysis of Algorithms](https://www.amazon.com/Introduction-Design-Analysis-Algorithms-3rd/dp/0132316811) — Anany Levitin, Chapter 6.3 (Heaps) and Chapter 6.4 (AVL Trees).
* [GeeksforGeeks — AVL Tree](https://www.geeksforgeeks.org/avl-tree-set-1-insertion/) — Full implementation with rotations.
* [GeeksforGeeks — Heap Data Structure](https://www.geeksforgeeks.org/heap-data-structure/) — Build, insert, and extract operations.
