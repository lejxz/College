
# Decrease and Conquer

## 📋 Summary
* **Core Concept:** Decrease and Conquer is an algorithm design strategy that solves a problem by reducing it to a single smaller instance of the same problem, solving that instance, and then extending the solution back to the original problem.

> **Takeaways:** Unlike Divide and Conquer — which splits a problem into multiple subproblems — Decrease and Conquer reduces the problem by one unit, a constant factor, or a variable factor per recursive step. This makes it simpler in structure but still powerful in practice. Many classical algorithms, such as Binary Search and Insertion Sort, are built on this paradigm.


## 📖 Definition

* **Decrease and Conquer:** A recursive algorithm design technique in which a problem of size $n$ is reduced to a single subproblem of smaller size, the subproblem is solved recursively, and its solution is then used to construct the solution to the original problem.
* **Decrease by a Constant:** The problem size is reduced by a fixed amount (commonly by 1) at each recursive step. Example: Insertion Sort reduces the unsorted portion by 1 each pass.
* **Decrease by a Constant Factor:** The problem size is reduced by a multiplicative factor (commonly by half) at each recursive step. Example: Binary Search halves the search space each iteration.
* **Variable-Size Decrease:** The reduction in problem size varies from one step to the next. Example: Euclid's Algorithm reduces the problem by a remainder that is not fixed.
* **Requirements:**
    * A well-defined base case that terminates the recursion.
    * A reduction step that strictly decreases the problem size toward the base case.
    * A method to extend the solution of the subproblem to the full problem.


## 📊 Complexity Analysis

| Notation | Name | Growth Rate |
| :--- | :--- | :--- |
| $O(1)$ | Constant | Excellent |
| $O(\log n)$ | Logarithmic | Very Good |
| $O(n)$ | Linear | Good |
| $O(n \log n)$ | Linearithmic | Fair |
| $O(n^2)$ | Quadratic | Poor |

* **Worst-Case ($O$):** The absolute maximum number of steps required across all inputs of size $n$.
* **Best-Case ($\Omega$):** The minimum number of steps required, typically on the most favorable input.
* **Average-Case ($\Theta$):** The expected performance when the algorithm is run on a random or typical input.

> **Per-Variant Complexity Summary:**
> | Variant | Example Algorithm | Time Complexity |
> | :--- | :--- | :--- |
> | Decrease by 1 | Insertion Sort | $O(n^2)$ |
> | Decrease by 1 | Selection Sort | $O(n^2)$ |
> | Decrease by Factor | Binary Search | $O(\log n)$ |
> | Variable Decrease | Euclid's GCD | $O(\log \min(a, b))$ |


## ❓ Why We Use It

* **Simplicity of structure:** Reducing to a single subproblem is easier to reason about and implement than splitting into many, as done in Divide and Conquer.
* **Foundation for efficient search:** Decrease by a constant factor (e.g., halving) yields logarithmic complexity, which is highly efficient for large datasets.
* **Natural fit for ordered problems:** Many problems — such as searching a sorted array or computing powers — naturally lend themselves to reducing by one or by half.
* **Bridges recursion and iteration:** Many Decrease and Conquer algorithms can be expressed both recursively and iteratively, making them versatile in implementation.


## ⚙️ How It Works

1. **Step 1: Identify the problem size $n$** — Define what constitutes the size of the input (e.g., length of array, value of $n$).
2. **Step 2: Locate the Basic Operation** — Identify the dominant operation that is executed most frequently (e.g., comparison, assignment).
3. **Step 3: Define the reduction** — Determine how the problem is decreased:
   - By 1: $T(n) = T(n-1) + f(n)$
   - By factor $k$: $T(n) = T(n/k) + f(n)$
4. **Step 4: Set up the recurrence relation and solve:**
   $$T(n) = T(n - 1) + c \quad \Rightarrow \quad T(n) \in O(n)$$
   $$T(n) = T\left(\frac{n}{2}\right) + c \quad \Rightarrow \quad T(n) \in O(\log n)$$
5. **Step 5: Verify the base case** — Confirm that the recursion terminates and that the base case is handled correctly.
6. **Step 6: Extend the subsolution** — Use the result of the smaller subproblem to produce the solution for the full problem.


## 💻 Usage / Examples

### Example 1: Binary Search — Decrease by Constant Factor $O(\log n)$

```python
# Binary Search: decrease by half each iteration
def binary_search(arr, target, low=0, high=None):
    if high is None:
        high = len(arr) - 1

    if low > high:
        return -1  # Base case: not found

    mid = (low + high) // 2  # Basic Operation: index computation + comparison

    if arr[mid] == target:
        return mid
    elif arr[mid] > target:
        return binary_search(arr, target, low, mid - 1)  # Decrease: left half
    else:
        return binary_search(arr, target, mid + 1, high)  # Decrease: right half

# Complexity: O(log n) — search space is halved each recursive call
```

---

### Example 2: Insertion Sort — Decrease by 1 $O(n^2)$

```python
# Insertion Sort: sort the first n-1 elements, then insert the n-th
def insertion_sort(arr):
    for i in range(1, len(arr)):       # Decrease: grow sorted portion by 1
        key = arr[i]
        j = i - 1
        while j >= 0 and arr[j] > key: # Basic Operation: comparison
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key
    return arr

# Complexity: O(n^2) worst case, O(n) best case (already sorted)
```

---

### Example 3: Euclid's Algorithm (GCD) — Variable-Size Decrease $O(\log \min(a, b))$

```python
# Euclid's Algorithm: reduce using the remainder (variable decrease)
def gcd(a, b):
    if b == 0:                # Base case
        return a
    return gcd(b, a % b)     # Basic Operation: modulo — problem size varies

# Complexity: O(log min(a, b)) — reduction is not uniform but still converges quickly
```

---

### Example 4: Russian Peasant Multiplication — Decrease by Factor $O(\log n)$

```python
# Russian Peasant Multiplication: halve n and double m at each step
def russian_peasant_multiply(n, m):
    result = 0
    while n >= 1:              # Decrease: n is halved each iteration
        if n % 2 != 0:         # Basic Operation: parity check
            result += m
        n //= 2                # Decrease by factor of 2
        m *= 2
    return result

# Complexity: O(log n) — number of iterations equals the bit-length of n
```


## References

* [Introduction to the Design and Analysis of Algorithms] — Anany Levitin, Chapter 4: Decrease-and-Conquer.
* [Algorithm Design Manual] — Steven S. Skiena, Chapter 4.
* [Decrease and Conquer — Khan Academy / GeeksforGeeks](https://www.geeksforgeeks.org/decrease-and-conquer/) — Overview and worked examples.
* [Binary Search — Wikipedia](https://en.wikipedia.org/wiki/Binary_search_algorithm) — Formal definition and complexity proof.
* [Euclidean Algorithm — Wikipedia](https://en.wikipedia.org/wiki/Euclidean_algorithm) — Historical context and complexity analysis.