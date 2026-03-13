# Binary Exponentiation

## 📋 Summary
* **Core Concept:** Binary Exponentiation (also called fast power or exponentiation by squaring) computes $a^n$ in $O(\log n)$ multiplications by repeatedly squaring the base and using the binary representation of the exponent.

> **Takeaways:** Instead of multiplying $a$ by itself $n$ times (which costs $O(n)$), binary exponentiation halves the problem at each step. It is widely used in modular arithmetic, cryptography (e.g., RSA), and matrix exponentiation.


## 📖 Definition

* **Binary Exponentiation:** A divide-and-conquer technique to compute $a^n$ using the recurrence:
  $$a^n = \begin{cases} 1 & \text{if } n = 0 \\ (a^{n/2})^2 & \text{if } n \text{ is even} \\ a \cdot a^{n-1} & \text{if } n \text{ is odd} \end{cases}$$
* **Squaring Step:** The core operation that reduces the exponent by half each iteration.
* **Modular Exponentiation:** A variant that computes $(a^n) \mod m$ without computing the full value of $a^n$, essential in cryptographic algorithms.
* **Requirements:**
    * The base $a$ and exponent $n$ must be integers ($n \geq 0$).
    * For modular exponentiation, a modulus $m > 1$ must be provided.


## 📊 Complexity Analysis

| Method | Time Complexity | Space Complexity |
| :--- | :--- | :--- |
| Naive (loop) | $O(n)$ | $O(1)$ |
| Binary Exponentiation (iterative) | $O(\log n)$ | $O(1)$ |
| Binary Exponentiation (recursive) | $O(\log n)$ | $O(\log n)$ |

* **Worst-Case ($O$):** $O(\log n)$ — the number of squaring steps equals $\lfloor \log_2 n \rfloor$.
* **Best-Case ($\Omega$):** $\Omega(1)$ — when $n = 0$ or $n = 1$.
* **Average-Case ($\Theta$):** $\Theta(\log n)$ — consistent for all values of $n > 1$.


## ❓ Why We Use It

* **Cryptography:** RSA encryption and Diffie-Hellman key exchange rely on modular exponentiation with very large exponents; $O(\log n)$ is critical.
* **Competitive programming:** Required for fast modular arithmetic under time constraints.
* **Matrix exponentiation:** Used to compute $A^k$ efficiently, enabling fast Fibonacci computation and path counting in graphs.
* **AR/VR relevance:** Fast numerical computations underpin real-time transformation matrices in spatial computing pipelines.


## ⚙️ How It Works

1. **Step 1:** Express the exponent $n$ in binary form.
2. **Step 2:** Initialize $\text{result} = 1$ and $\text{base} = a$.
3. **Step 3:** While $n > 0$:
   - If the least significant bit of $n$ is $1$: $\text{result} \mathrel{*}= \text{base}$
   - Square the base: $\text{base} \mathrel{*}= \text{base}$
   - Right-shift $n$: $n \mathrel{>>}= 1$
4. **Step 4:** Return $\text{result}$.
5. **Step 5 — Recurrence:**
   $$T(n) = T(n/2) + O(1) \implies T(n) = O(\log n)$$


## 💻 Usage / Example

```c
#include <stdio.h>

/*
 * Iterative binary exponentiation.
 * Computes (base^exp) % mod.
 * Pass mod = 0 to skip modular reduction.
 */
long long binary_exp(long long base, long long exp, long long mod) {
    long long result = 1;
    while (exp > 0) {
        if (exp % 2 == 1) {          /* Current bit is 1 */
            result *= base;
            if (mod) result %= mod;
        }
        base *= base;                /* Square the base */
        if (mod) base %= mod;
        exp /= 2;                    /* Move to next bit */
    }
    return result;
}

int main(void) {
    /* Standard: 2^10 = 1024 */
    printf("2^10 = %lld\n", binary_exp(2, 10, 0));

    /* Modular (used in RSA): 2^10 mod 1000 = 24 */
    printf("2^10 mod 1000 = %lld\n", binary_exp(2, 10, 1000));

    /* Complexity: O(log n) multiplications */
    return 0;
}
```


## References

* [Introduction to the Design and Analysis of Algorithms](https://www.amazon.com/Introduction-Design-Analysis-Algorithms-3rd/dp/0132316811) — Anany Levitin, Chapter 6.
* [CP-Algorithms — Binary Exponentiation](https://cp-algorithms.com/algebra/binary-exp.html) — Detailed iterative and recursive implementations.
* [Wikipedia — Exponentiation by Squaring](https://en.wikipedia.org/wiki/Exponentiation_by_squaring) — Mathematical foundation and variants.
