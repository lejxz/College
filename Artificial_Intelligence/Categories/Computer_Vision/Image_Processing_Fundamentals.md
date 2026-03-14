# Image Processing Fundamentals

## 📋 Summary

* **Core Concept:** Image processing is the application of mathematical operations on digital images to enhance, transform, or extract information — forming the foundational pipeline for Computer Vision (CV) and AI perception systems.

> **Takeaways:** Before any AI model can interpret visual data, the raw image must be acquired, cleaned, and transformed into a structured representation. Image processing covers that entire preparation stage — from pixel-level operations (filtering, thresholding) to geometric transformations (resize, rotate, warp). In CV pipelines, this step directly determines the quality of features fed into detection, segmentation, or classification models.


---

## 📖 Definition

* **Digital Image:** A discrete, two-dimensional function `f(x, y)` where `x` and `y` are spatial coordinates and the amplitude at each point represents pixel intensity (grayscale) or a color vector (e.g., RGB).
* **Pixel (Picture Element):** The smallest addressable unit of a digital image. In an 8-bit grayscale image, pixel values range from `0` (black) to `255` (white). In RGB, each pixel holds three such values.
* **Image Processing:** The set of algorithms applied to a digital image to improve its quality, extract features, or transform it into a format suitable for further analysis or machine learning.
* **Computer Vision (CV):** A subfield of AI that enables machines to interpret and understand visual data from the world — built on top of image processing pipelines.
* **Color Space:** A mathematical model describing how colors are encoded numerically:
  * `RGB` — Red, Green, Blue (standard display format)
  * `Grayscale` — Single-channel luminance; reduces computational cost
  * `HSV` — Hue, Saturation, Value; useful for color-based segmentation
  * `BGR` — OpenCV's default channel order (Blue, Green, Red)
* **Kernel / Filter:** A small matrix (e.g., 3×3, 5×5) convolved over an image to apply spatial operations such as blurring, sharpening, or edge detection.
* **Convolution:** A mathematical operation that slides a kernel across an image, computing a weighted sum at each position to produce a transformed output.

  $$G(x, y) = \sum_{i} \sum_{j} f(x+i,\ y+j) \cdot K(i, j)$$

* **Requirements:**
  * Python 3.8 or higher
  * Libraries: `opencv-python`, `numpy`, `matplotlib`, `Pillow`
  * A digital image in a supported format (JPEG, PNG, BMP, etc.)
  * Basic understanding of NumPy array operations and matrix math


---

## ❓ Why We Use It

* **Preprocessing for ML/AI:** Raw images are rarely model-ready. Resizing, normalization, and noise removal improve both training stability and model accuracy.
* **Feature Extraction:** Spatial filters (Sobel, Laplacian, Canny) reveal structural features — edges, corners, gradients — that traditional CV algorithms and deep learning backbones depend on.
* **Noise Reduction:** Sensors and compression introduce artifacts. Smoothing filters (Gaussian blur, median filter) suppress these before downstream analysis.
* **Segmentation Preparation:** Thresholding and morphological operations (erosion, dilation) isolate regions of interest (ROI), enabling object-level analysis.
* **Data Augmentation:** Geometric and photometric transforms (flip, crop, brightness shift) expand training datasets without additional data collection, improving model generalization.
* **AR/VR Applications:** Real-time image processing enables marker detection, depth map generation, and scene understanding — all critical to AR/VR spatial computing pipelines.


---

## ⚙️ How It Works

1. **Step 1 — Image Acquisition:** Read the image into memory as a NumPy array using OpenCV or Pillow. Each image is stored as a matrix of shape `(H, W, C)` where `H` = height, `W` = width, and `C` = number of channels.

2. **Step 2 — Color Space Conversion:** Convert to the appropriate color space for the task. For most CV preprocessing, convert `BGR → Grayscale` or `BGR → HSV`.

   $$\text{Gray} = 0.299R + 0.587G + 0.114B$$

3. **Step 3 — Filtering / Spatial Operations:** Apply a kernel via 2D convolution. For a Gaussian blur kernel of standard deviation `σ`:

   $$G(x, y) = \frac{1}{2\pi\sigma^2} e^{-\frac{x^2 + y^2}{2\sigma^2}}$$

   This smooths the image, reducing high-frequency noise before edge detection or thresholding.

4. **Step 4 — Thresholding / Segmentation:** Binarize the image using a threshold value `T`. Otsu's method automatically computes the optimal `T` by minimizing intra-class intensity variance:

   $$\sigma^2_w(T) = w_0(T)\sigma^2_0(T) + w_1(T)\sigma^2_1(T)$$

5. **Step 5 — Morphological Operations:** Refine binary masks using erosion and dilation to remove noise and fill gaps. These are applied using a structuring element (kernel).

6. **Step 6 — Feature Extraction / Output:** Pass the processed image to a CV algorithm (e.g., contour detection, SIFT, HOG) or a deep learning model (e.g., YOLO, ResNet) as a cleaned, structured input.


---

## 💻 Usage / Example

```python
import cv2
import numpy as np
import matplotlib.pyplot as plt

# ─────────────────────────────────────────────
# Example: Core Image Processing Pipeline
# Context: Preprocessing for a CV/AI task
# ─────────────────────────────────────────────

def preprocess_image(image_path: str) -> dict:
    """
    Full image preprocessing pipeline for CV tasks.
    Covers: load → grayscale → blur → threshold → edge detection
    """

    # Step 1: Load image (BGR format by default in OpenCV)
    img_bgr = cv2.imread(image_path)
    if img_bgr is None:
        raise FileNotFoundError(f"Image not found: {image_path}")

    # Step 2: Convert BGR → Grayscale
    # Formula: Gray = 0.299R + 0.587G + 0.114B
    img_gray = cv2.cvtColor(img_bgr, cv2.COLOR_BGR2GRAY)

    # Step 3: Gaussian Blur — suppress noise before edge detection
    # Kernel size (5, 5), sigma = 0 (auto-computed from kernel size)
    img_blurred = cv2.GaussianBlur(img_gray, (5, 5), sigmaX=0)

    # Step 4: Otsu's Thresholding — automatic binarization
    # Returns: threshold value T, and the binary image
    T, img_thresh = cv2.threshold(
        img_blurred, 0, 255,
        cv2.THRESH_BINARY + cv2.THRESH_OTSU
    )
    print(f"Otsu's optimal threshold: {T:.2f}")

    # Step 5: Canny Edge Detection
    # Parameters: lower_threshold=50, upper_threshold=150
    img_edges = cv2.Canny(img_blurred, threshold1=50, threshold2=150)

    # Step 6: Morphological Dilation — connect broken edges
    kernel = np.ones((3, 3), dtype=np.uint8)
    img_dilated = cv2.dilate(img_edges, kernel, iterations=1)

    return {
        "original":   cv2.cvtColor(img_bgr, cv2.COLOR_BGR2RGB),
        "grayscale":  img_gray,
        "blurred":    img_blurred,
        "threshold":  img_thresh,
        "edges":      img_edges,
        "dilated":    img_dilated,
    }


def visualize_pipeline(results: dict) -> None:
    """Display all pipeline stages side by side using Matplotlib."""
    titles = list(results.keys())
    images = list(results.values())

    fig, axes = plt.subplots(2, 3, figsize=(14, 8))
    fig.suptitle("Image Processing Pipeline — CV/AI Preprocessing", fontsize=14)

    for ax, img, title in zip(axes.flat, images, titles):
        cmap = "gray" if img.ndim == 2 else None
        ax.imshow(img, cmap=cmap)
        ax.set_title(title.capitalize())
        ax.axis("off")

    plt.tight_layout()
    plt.show()


# ─────────────────────────────────────────────
# Run pipeline on a sample image
# Replace "sample.jpg" with your own image path
# ─────────────────────────────────────────────
if __name__ == "__main__":
    results = preprocess_image("sample.jpg")
    visualize_pipeline(results)

# Pipeline Complexity:
# Each stage is O(H × W) — linear in the number of pixels.
# Convolution with a k×k kernel: O(H × W × k²)
# Canny edge detection:           O(H × W)
```

> **Note:** OpenCV loads images in **BGR** format by default, not RGB. Always convert with `cv2.COLOR_BGR2RGB` before displaying with Matplotlib, which expects RGB.


---

## References

* [OpenCV Documentation](https://docs.opencv.org/4.x/) — Official reference for all OpenCV functions used in this note.
* [scikit-image Documentation](https://scikit-image.org/docs/stable/) — Python image processing library with academic-grade algorithms.
* [Digital Image Processing] — Gonzalez, R.C. & Woods, R.E., 4th Edition — Chapter 2 (Digital Image Fundamentals), Chapter 3 (Intensity Transformations and Spatial Filtering).
* [Computer Vision: Algorithms and Applications] — Szeliski, R., 2nd Edition — Chapter 3 (Image Processing).
* [Canny Edge Detection Paper](https://ieeexplore.ieee.org/document/4767851) — Canny, J. (1986). *A Computational Approach to Edge Detection*. IEEE TPAMI.
