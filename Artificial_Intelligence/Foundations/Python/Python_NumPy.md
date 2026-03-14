# Python NumPy — Numerical Computing & Array Operations

## 📋 Summary

* **Core Concept:** NumPy (Numerical Python) is a foundational open-source library that provides a powerful N-dimensional array object and a collection of mathematical functions for efficient numerical computation in Python.

> **Takeaways:** NumPy replaces Python's slow native lists with `ndarray` — a fixed-type, contiguous-memory array that supports vectorized operations, broadcasting, and linear algebra. It is the backbone of nearly every scientific Python library, including OpenCV, TensorFlow, scikit-learn, and Pandas. In computer vision, every image is an `ndarray`.

---

## 📖 Definition

* **ndarray:** NumPy's core data structure — an N-dimensional array of elements of the same data type (`dtype`), stored in contiguous memory for fast access.
* **dtype:** The data type of elements in an array (e.g., `uint8`, `float32`, `int64`). In image processing, pixel values are typically `uint8` (0–255).
* **Shape:** A tuple describing the dimensions of an array. For a color image: `(height, width, channels)`. For a grayscale image: `(height, width)`.
* **Axis:** A specific dimension of an array. Axis 0 = rows, Axis 1 = columns, Axis 2 = channels (for 3D arrays).
* **Broadcasting:** A set of rules that allows NumPy to perform arithmetic between arrays of different — but compatible — shapes without copying data.
* **Vectorization:** Replacing explicit Python loops with NumPy array operations, which execute in optimized C internally and are significantly faster.
* **Slice / View:** A reference to a subset of an array's data without copying it. Modifying a view modifies the original array.

* **Requirements:**
    * Python 3.x
    * Install: `pip install numpy`
    * Commonly imported as: `import numpy as np`

---

## ❓ Why We Use It

* **Performance:** NumPy operations execute in compiled C/Fortran, making them orders of magnitude faster than equivalent Python loops. For large arrays (e.g., image data), this difference is critical.
* **OpenCV dependency:** Every image read by `cv2.imread()` is returned as a NumPy `ndarray`. All pixel-level operations require NumPy knowledge.
* **Memory efficiency:** `ndarray` stores elements of a single type in contiguous memory, using far less memory than Python lists of mixed objects.
* **Linear algebra support:** NumPy provides matrix multiplication, decomposition, and inversion — essential for transformation matrices in AR/VR (e.g., rotation, projection).
* **ML/AI integration:** TensorFlow and PyTorch tensors are interoperable with NumPy arrays. Understanding NumPy directly accelerates understanding of how these frameworks store and manipulate data.

---

## ⚙️ How It Works

1. **Step 1 — Create an array.** Construct an `ndarray` from a Python list, or use generator functions like `np.zeros()`, `np.ones()`, or `np.arange()`. NumPy allocates a single contiguous block of memory.

2. **Step 2 — Understand the shape.** Every array has a `.shape` attribute describing its dimensions. For a 1080p color image:
   $$(H,\ W,\ C) = (1080,\ 1920,\ 3)$$
   Total elements $= H \times W \times C = 6{,}220{,}800$

3. **Step 3 — Apply vectorized operations.** Instead of looping over elements, apply operations directly to the array. NumPy internally maps the operation across all elements using SIMD CPU instructions:
   $$A' = \frac{A}{255.0} \quad \text{(normalize pixel values to [0, 1])}$$

4. **Step 4 — Use broadcasting.** When operating on arrays of different shapes, NumPy stretches the smaller array along compatible dimensions without copying data:
   $$A_{(H,W,3)} - \mu_{(3,)} \quad \Rightarrow \quad \text{subtract mean per channel}$$

5. **Step 5 — Slice and index.** Extract regions of interest (ROI) using array slicing. This returns a **view**, not a copy — memory efficient but requires care when modifying.

---

## 💻 Usage / Example

```python
import numpy as np
import cv2

# ── 1. Array Creation ─────────────────────────────────────────────────────────
a = np.array([1, 2, 3], dtype=np.float32)       # 1D array from list
zeros = np.zeros((3, 3), dtype=np.uint8)         # 3×3 matrix of zeros
ones = np.ones((480, 640, 3), dtype=np.uint8)    # Blank color image (black)
rng = np.arange(0, 10, 2)                        # [0, 2, 4, 6, 8]
rand = np.random.rand(4, 4)                      # 4×4 random float array [0, 1)

# ── 2. Array Attributes ───────────────────────────────────────────────────────
image = cv2.imread("sample.jpg")
print(image.shape)    # (height, width, 3)  — e.g., (480, 640, 3)
print(image.dtype)    # uint8               — pixel values 0–255
print(image.size)     # total elements      — H × W × C
print(image.ndim)     # number of dimensions — 3

# ── 3. Indexing and Slicing ───────────────────────────────────────────────────
pixel = image[100, 200]            # Single pixel: array([B, G, R])
blue_channel = image[:, :, 0]     # Entire Blue channel (2D array)
roi = image[50:200, 100:300]      # Region of interest (view, not copy)
roi_copy = image[50:200, 100:300].copy()  # Explicit copy — safe to modify

# ── 4. Vectorized Operations ──────────────────────────────────────────────────
# Normalize pixel values from [0, 255] to [0.0, 1.0]
normalized = image.astype(np.float32) / 255.0

# Brighten image by adding a scalar (clip prevents overflow above 255)
brightened = np.clip(image.astype(np.int32) + 50, 0, 255).astype(np.uint8)

# Element-wise operations
a = np.array([10, 20, 30])
b = np.array([1, 2, 3])
print(a + b)    # [11 22 33]
print(a * b)    # [10 40 90]
print(a ** 2)   # [100 400 900]

# ── 5. Broadcasting ───────────────────────────────────────────────────────────
# Subtract per-channel mean (common preprocessing step for ML)
mean = np.array([103.94, 116.78, 123.68], dtype=np.float32)  # BGR means
preprocessed = normalized - mean / 255.0   # Broadcasts mean across (H, W, 3)

# ── 6. Linear Algebra (used in AR/VR transformations) ────────────────────────
# 2D Rotation matrix by angle θ (in radians)
theta = np.radians(45)
R = np.array([
    [np.cos(theta), -np.sin(theta)],
    [np.sin(theta),  np.cos(theta)]
])

point = np.array([1.0, 0.0])
rotated = R @ point                 # Matrix multiplication: @ operator
print(f"Rotated point: {rotated}")  # [0.707, 0.707]

# ── 7. Useful Aggregations ────────────────────────────────────────────────────
print(np.mean(image))              # Mean pixel value across all channels
print(np.max(image))               # Brightest pixel value
print(np.min(image))               # Darkest pixel value
print(np.std(image, axis=2))       # Std deviation per pixel across channels

# ── Complexity Notes ──────────────────────────────────────────────────────────
# Element-wise ops:   O(N)  where N = total elements
# Matrix multiply:    O(N³) naive; O(N^2.37) with optimized BLAS
# Slicing:            O(1)  — returns a view, no data copied
```

### Shape & Indexing — Quick Reference

| Array Type | Shape | Example |
|---|---|---|
| Grayscale image | `(H, W)` | `(480, 640)` |
| Color image (BGR) | `(H, W, 3)` | `(480, 640, 3)` |
| Batch of images | `(N, H, W, C)` | `(32, 224, 224, 3)` |
| 1D signal | `(N,)` | `(1024,)` |
| Transformation matrix | `(3, 3)` | Homography, rotation |

### Common Functions — Quick Reference

| Task | Function |
|---|---|
| Create zeros array | `np.zeros((H, W), dtype)` |
| Create ones array | `np.ones((H, W), dtype)` |
| Stack arrays | `np.stack([a, b], axis=0)` |
| Flatten array | `arr.flatten()` or `arr.ravel()` |
| Reshape array | `arr.reshape((new_shape))` |
| Clip values | `np.clip(arr, min, max)` |
| Matrix multiply | `A @ B` or `np.matmul(A, B)` |
| Transpose | `arr.T` |
| Unique values | `np.unique(arr)` |
| Where condition | `np.where(condition, x, y)` |

---

## References

* [NumPy Official Documentation](https://numpy.org/doc/stable/) — Full API reference and user guide.
* [NumPy Quickstart Tutorial](https://numpy.org/doc/stable/user/quickstart.html) — Official getting-started guide covering arrays, indexing, and operations.
* [CS231n Python/NumPy Tutorial](https://cs231n.github.io/python-numpy-tutorial/) — Stanford's concise NumPy tutorial written for ML practitioners.
* [Python for Data Analysis, 3rd Ed.] — Wes McKinney — Covers NumPy in depth alongside Pandas; strong focus on practical usage.
* [Mathematics for Machine Learning] — Deisenroth, Faisal, Ong — Covers the linear algebra that NumPy implements (vectors, matrices, transformations).
