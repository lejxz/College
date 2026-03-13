# Evaluating a Polynomial

## 📋 Summary
* **Core Concept:** Polynomial evaluation computes $p(x)$ for a given value of $x$; Horner's Method is the most efficient algorithm, requiring only $O(n)$ multiplications and additions.

> **Takeaways:** The naive approach evaluates each term independently at $O(n^2)$ cost. Horner's Method rewrites the polynomial in nested form, reducing this to $O(n)$, making it the standard approach in numerical computing.


## 📖 Definition

* **Polynomial:** An expression of the form $p(x) = a_n x^n + a_{n-1} x^{n-1} + \cdots + a_1 x + a_0$, where $a_i$ are coefficients and $n$ is the degree.
* **Horner's Method (Horner's Rule):** A representation change that rewrites the polynomial in nested form to minimize arithmetic operations:
  $$p(x) = a_0 + x(a_1 + x(a_2 + \cdots + x(a_{n-1} + x \cdot a_n)\cdots))$$
* **Basic Operation:** One multiplication and one addition per iteration.
* **Requirements:**
    * The coefficients must be stored in order (either ascending or descending by degree).
    * A single evaluation point $x$ must be provided.


## 📊 Complexity Analysis

| Method | Multiplications | Additions |
| :--- | :--- | :--- |
| Naive (direct) | $O(n^2)$ | $O(n)$ |
| Horner's Method | $O(n)$ | $O(n)$ |

* **Worst-Case ($O$):** $O(n)$ for Horner's Method — processes all $n$ coefficients exactly once.
* **Best-Case ($\Omega$):** $\Omega(n)$ — must read all $n$ coefficients regardless of input.
* **Average-Case ($\Theta$):** $\Theta(n)$ — performance is consistent across all inputs.


## ❓ Why We Use It

* **Numerical stability:** Horner's Method minimizes floating-point errors compared to evaluating each power independently.
* **Efficiency:** Reduces the number of multiplications from $O(n^2)$ (naive) to $O(n)$.
* **Practical use:** Applied in graphics pipelines, signal processing, and compiler expression evaluation — all relevant in AR/VR systems.


## ⚙️ How It Works

1. **Step 1:** Given polynomial $p(x) = a_n x^n + \cdots + a_1 x + a_0$, rewrite in Horner's form.
2. **Step 2:** Initialize the accumulator with the leading coefficient:
   $$\text{result} = a_n$$
3. **Step 3:** Iterate from $i = n-1$ down to $0$, updating:
   $$\text{result} = \text{result} \times x + a_i$$
4. **Step 4:** After the loop, $\text{result} = p(x)$.
5. **Step 5 — Complexity:**
   $$T(n) = n \text{ multiplications} + n \text{ additions} = \Theta(n)$$


## 💻 Usage / Example

```c
#include <stdio.h>

/*
 * Horner's Method for polynomial evaluation.
 * coeffs[]: coefficients from highest degree to lowest [a_n, ..., a_1, a_0]
 * degree  : degree of the polynomial (= number of coefficients - 1)
 * x       : evaluation point
 */
double horner(double coeffs[], int degree, double x) {
    double result = coeffs[0]; /* Start with leading coefficient */
    for (int i = 1; i <= degree; i++) {
        result = result * x + coeffs[i]; /* Basic Operation */
    }
    return result;
}

int main(void) {
    /*
     * p(x) = 2x^3 + 3x^2 - x + 5
     * Coefficients (highest to lowest): {2, 3, -1, 5}
     */
    double coeffs[] = {2.0, 3.0, -1.0, 5.0};
    int degree = 3;
    double x = 2.0;

    double result = horner(coeffs, degree, x);
    printf("p(%.1f) = %.1f\n", x, result);
    /* Manual check: 2(8) + 3(4) - 2 + 5 = 16 + 12 - 2 + 5 = 31 */
    /* Output: p(2.0) = 31.0 */

    /* Complexity: O(n) where n = degree of polynomial */
    return 0;
}
```


## References

* [Introduction to the Design and Analysis of Algorithms](https://www.amazon.com/Introduction-Design-Analysis-Algorithms-3rd/dp/0132316811) — Anany Levitin, Chapter 6.3.
* [Wikipedia — Horner's Method](https://en.wikipedia.org/wiki/Horner%27s_method) — Mathematical derivation and applications.
* [GeeksforGeeks — Horner's Method for Polynomial Evaluation](https://www.geeksforgeeks.org/horners-method-polynomial-evaluation/) — Implementation examples.
