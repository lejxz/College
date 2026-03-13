# Gaussian Elimination Method

## 📋 Summary
* **Core Concept:** Gaussian Elimination is a systematic method for solving systems of linear equations by applying row operations to transform an augmented matrix into row echelon form, followed by back substitution.

> **Takeaways:** It is the foundational algorithm for solving $Ax = b$ in linear algebra. Combined with partial pivoting, it is numerically stable and runs in $O(n^3)$ time. It is also the basis for computing matrix determinants and ranks.


## 📖 Definition

* **System of Linear Equations:** A set of $n$ equations with $n$ unknowns, written in matrix form as $Ax = b$, where $A$ is the coefficient matrix, $x$ is the unknown vector, and $b$ is the constant vector.
* **Augmented Matrix:** The matrix $[A | b]$ formed by appending $b$ as an additional column to $A$.
* **Row Echelon Form (REF):** A matrix form where each row's leading nonzero entry (pivot) is strictly to the right of the pivot in the row above.
* **Back Substitution:** The process of solving for unknowns from the last equation upward after reaching REF.
* **Partial Pivoting:** A numerical stability technique that selects the row with the largest absolute value in the current pivot column before elimination.
* **Requirements:**
    * The coefficient matrix $A$ must be square ($n \times n$) for a unique solution.
    * No pivot element may be zero after pivoting (otherwise the system is singular).


## 📊 Complexity Analysis

| Operation | Time Complexity |
| :--- | :--- |
| Forward Elimination | $O(n^3)$ |
| Back Substitution | $O(n^2)$ |
| Total | $O(n^3)$ |

* **Worst-Case ($O$):** $O(n^3)$ — the elimination step dominates with $\frac{n^3}{3}$ divisions and multiplications.
* **Best-Case ($\Omega$):** $\Omega(n^2)$ — at minimum, all entries must be visited once.
* **Average-Case ($\Theta$):** $\Theta(n^3)$ — consistent regardless of the specific values in $A$.


## ❓ Why We Use It

* **Foundational linear algebra tool:** Directly solves $Ax = b$ without requiring matrix inversion (which is more expensive and less stable).
* **Determinant computation:** The product of pivots after elimination equals $\det(A)$.
* **Rank determination:** The number of nonzero rows after elimination equals the rank of $A$.
* **Applications in graphics and ML:** Solving linear systems appears in least-squares regression, 3D transformations, and camera calibration in AR/VR pipelines.


## ⚙️ How It Works

1. **Step 1:** Form the augmented matrix $[A | b]$ of size $n \times (n+1)$.
2. **Step 2 — Forward Elimination:** For each pivot column $k = 0, 1, \ldots, n-1$:
   - Apply partial pivoting: swap rows so the largest value in column $k$ is the pivot.
   - For each row $i > k$, compute the elimination factor:
     $$m_{ik} = \frac{A[i][k]}{A[k][k]}$$
   - Subtract $m_{ik}$ times row $k$ from row $i$ across all columns.
3. **Step 3:** After elimination, the matrix is in row echelon form.
4. **Step 4 — Back Substitution:** Solve from the last row upward:
   $$x_i = \frac{b_i - \sum_{j=i+1}^{n} A[i][j] \cdot x_j}{A[i][i]}$$


## 💻 Usage / Example

```c
#include <stdio.h>
#include <math.h>

#define N 3

/*
 * Gaussian elimination with partial pivoting.
 * Solves the system A * x = b.
 * Modifies M (augmented matrix) in place.
 */
void gaussian_elimination(double M[N][N + 1], double x[N]) {
    /* Forward elimination */
    for (int k = 0; k < N; k++) {
        /* Partial pivoting: find row with largest |M[i][k]| */
        int max_row = k;
        for (int i = k + 1; i < N; i++)
            if (fabs(M[i][k]) > fabs(M[max_row][k])) max_row = i;

        /* Swap rows k and max_row */
        for (int j = 0; j <= N; j++) {
            double tmp = M[k][j];
            M[k][j]       = M[max_row][j];
            M[max_row][j] = tmp;
        }

        /* Eliminate below pivot */
        for (int i = k + 1; i < N; i++) {
            double factor = M[i][k] / M[k][k];
            for (int j = k; j <= N; j++)
                M[i][j] -= factor * M[k][j];
        }
    }

    /* Back substitution */
    for (int i = N - 1; i >= 0; i--) {
        x[i] = M[i][N];
        for (int j = i + 1; j < N; j++)
            x[i] -= M[i][j] * x[j];
        x[i] /= M[i][i];
    }
}

int main(void) {
    /*
     * Solve:  2x +  y -  z =  8
     *        -3x -  y + 2z = -11
     *        -2x +  y + 2z = -3
     * Expected: x=2, y=3, z=-1
     */
    double M[N][N + 1] = {
        { 2,  1, -1,   8},
        {-3, -1,  2, -11},
        {-2,  1,  2,  -3}
    };
    double x[N];

    gaussian_elimination(M, x);

    printf("x = %.1f\n", x[0]); /* 2.0 */
    printf("y = %.1f\n", x[1]); /* 3.0 */
    printf("z = %.1f\n", x[2]); /* -1.0 */

    /* Complexity: O(n^3) */
    return 0;
}
```


## References

* [Introduction to the Design and Analysis of Algorithms](https://www.amazon.com/Introduction-Design-Analysis-Algorithms-3rd/dp/0132316811) — Anany Levitin, Chapter 6.4.
* [Wikipedia — Gaussian Elimination](https://en.wikipedia.org/wiki/Gaussian_elimination) — Complete derivation including LU decomposition.
* [Numerical Recipes in C](http://numerical.recipes/) — Press et al., Chapter 2 — Numerical stability and pivoting strategies.
