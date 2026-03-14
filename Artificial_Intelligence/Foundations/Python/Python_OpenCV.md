# Python OpenCV — Computer Vision

## 📋 Summary

* **Core Concept:** OpenCV (Open Source Computer Vision Library) is an open-source library that provides tools for real-time image and video processing, widely used in computer vision, machine learning, and AR/VR applications.

> **Takeaways:** OpenCV enables developers to read, process, and analyze visual data — such as images and video frames — using a broad set of algorithms for tasks like object detection, edge detection, feature extraction, and geometric transformations. In Python, it is accessed via the `cv2` module.

---

## 📖 Definition

* **Image:** A 2D (or 3D for color) array of pixel values. In OpenCV, color images are stored as NumPy arrays with shape `(height, width, channels)` in **BGR** (Blue-Green-Red) order, not RGB.
* **Frame:** A single image extracted from a video stream. Video processing is essentially frame-by-frame image processing.
* **Kernel / Filter:** A small matrix used in convolution operations to apply effects such as blurring, sharpening, or edge detection to an image.
* **Color Space:** A mathematical model representing color. Common spaces in OpenCV include BGR, Grayscale, HSV (Hue-Saturation-Value), and LAB.
* **Contour:** A curve that connects continuous points along the boundary of an object with the same color or intensity.

* **Requirements:**
    * Python 3.x
    * `opencv-python` package: `pip install opencv-python`
    * `numpy` (installed automatically as a dependency)
    * Optional: `opencv-contrib-python` for extra modules (e.g., SIFT, SURF)

---

## ❓ Why We Use It

* **Real-time processing:** OpenCV is optimized in C++ under the hood, making it fast enough for real-time video stream analysis even when called from Python.
* **Wide algorithm coverage:** It supports hundreds of computer vision algorithms out of the box — from simple filters to deep neural network inference via `cv2.dnn`.
* **AR/VR relevance:** OpenCV is commonly used in AR/VR pipelines for camera calibration, pose estimation, marker detection (e.g., ArUco), and depth mapping.
* **Machine learning integration:** OpenCV integrates with frameworks such as TensorFlow and PyTorch, allowing trained models to be applied directly to visual data.
* **Cross-platform:** It runs on Windows, Linux, macOS, Android, and iOS — important for embedded and mobile AR/VR devices.

---

## ⚙️ How It Works

1. **Step 1 — Read input.** Load an image from disk using `cv2.imread()` or capture a video frame using `cv2.VideoCapture`. The result is a NumPy array.

2. **Step 2 — Preprocess.** Convert the image to the appropriate color space (e.g., Grayscale or HSV) to simplify subsequent operations:
   $$I_{gray}(x, y) = 0.114 \cdot B + 0.587 \cdot G + 0.299 \cdot R$$

3. **Step 3 — Apply operations.** Perform the target computer vision task. For example, apply a convolution filter using a kernel $K$ over the image $I$:
   $$(I * K)(x, y) = \sum_{i}\sum_{j} I(x+i,\ y+j) \cdot K(i, j)$$

4. **Step 4 — Detect or extract features.** Use algorithms such as Canny edge detection, Hough transforms, contour finding, or ORB/SIFT for feature matching.

5. **Step 5 — Output results.** Display the processed image with `cv2.imshow()`, save it with `cv2.imwrite()`, or pass data downstream to a machine learning model or AR/VR rendering engine.

---

## 💻 Usage / Example

```python
import cv2
import numpy as np

# ── 1. Load an image ──────────────────────────────────────────────────────────
image = cv2.imread("sample.jpg")          # Shape: (H, W, 3) in BGR
if image is None:
    raise FileNotFoundError("Image not found.")

# ── 2. Convert to grayscale ───────────────────────────────────────────────────
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

# ── 3. Apply Gaussian Blur (noise reduction before edge detection) ────────────
# Kernel size (5, 5) must be odd; sigmaX=0 lets OpenCV compute sigma automatically
blurred = cv2.GaussianBlur(gray, (5, 5), sigmaX=0)

# ── 4. Canny Edge Detection ───────────────────────────────────────────────────
# Thresholds: pixels with gradient > 150 are edges; < 50 are discarded
edges = cv2.Canny(blurred, threshold1=50, threshold2=150)

# ── 5. Find and draw contours ─────────────────────────────────────────────────
contours, _ = cv2.findContours(edges, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
output = image.copy()
cv2.drawContours(output, contours, contourIdx=-1, color=(0, 255, 0), thickness=2)

print(f"Contours detected: {len(contours)}")

# ── 6. Display and save ───────────────────────────────────────────────────────
cv2.imshow("Original", image)
cv2.imshow("Edges", edges)
cv2.imshow("Contours", output)
cv2.waitKey(0)          # Wait for any key press
cv2.destroyAllWindows()

cv2.imwrite("output_contours.jpg", output)

# ── Complexity Notes ──────────────────────────────────────────────────────────
# GaussianBlur:    O(H × W × k²)  where k = kernel size
# Canny:           O(H × W)
# findContours:    O(H × W)
```

### Common Operations — Quick Reference

| Operation | Function | Notes |
|---|---|---|
| Read image | `cv2.imread(path)` | Returns BGR NumPy array |
| Write image | `cv2.imwrite(path, img)` | Supports .jpg, .png, etc. |
| Resize | `cv2.resize(img, (w, h))` | Width before height |
| Color convert | `cv2.cvtColor(img, code)` | e.g., `COLOR_BGR2GRAY` |
| Blur | `cv2.GaussianBlur(img, ksize, sigma)` | Kernel size must be odd |
| Edge detection | `cv2.Canny(img, t1, t2)` | Works on grayscale |
| Draw rectangle | `cv2.rectangle(img, pt1, pt2, color, thickness)` | In-place |
| Video capture | `cv2.VideoCapture(0)` | `0` = default webcam |

---

## References

* [OpenCV Official Documentation](https://docs.opencv.org/4.x/) — Full API reference and tutorials for all OpenCV modules.
* [OpenCV Python Tutorials](https://docs.opencv.org/4.x/d6/d00/tutorial_py_root.html) — Official Python-specific tutorials covering core to advanced topics.
* [PyImageSearch](https://pyimagesearch.com/) — Applied OpenCV tutorials with practical examples in Python.
* [Learning OpenCV 4 with Python] — Alberto Fernández Villán, Packt Publishing — Comprehensive guide to OpenCV 4 using Python.
* [Computer Vision: Algorithms and Applications, 2nd Ed.] — Richard Szeliski — Theoretical foundations behind the algorithms implemented in OpenCV.
