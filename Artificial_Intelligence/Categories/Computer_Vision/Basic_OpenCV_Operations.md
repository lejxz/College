# Basic OpenCV Operations

## 📋 Summary

* **Core Concept:** OpenCV (Open Source Computer Vision Library) is a library that provides tools for real-time image and video processing. Basic operations include reading, displaying, modifying, and saving visual data using its built-in functions.

> **Takeaways:** OpenCV operates on images as NumPy arrays (in Python), where each pixel is a numerical value. Understanding how to load, manipulate, and save images is the foundation for all computer vision tasks — including those in AR/VR pipelines, object detection, and security systems.


## 📖 Definition

* **Image:** A two-dimensional (or three-dimensional for color) array of pixel values representing visual data.
* **Pixel:** The smallest unit of a digital image, represented as an integer (grayscale) or a triplet of integers (BGR in OpenCV).
* **Color Space:** A mathematical model that defines how colors are represented (e.g., BGR, RGB, Grayscale, HSV).
* **ROI (Region of Interest):** A specific rectangular sub-section of an image selected for focused processing.
* **Requirements:**
    * Python 3.x
    * OpenCV library (`opencv-python`)
    * NumPy (installed automatically with OpenCV)
    * Install via: `pip install opencv-python`


## ❓ Why We Use It

* **Image I/O:** OpenCV provides a simple, consistent interface to read and write images in multiple formats (`.jpg`, `.png`, `.bmp`, etc.) without manual byte handling.
* **Real-time processing:** It is optimized for speed, making it suitable for live video feeds — critical in AR/VR applications and surveillance systems.
* **Cross-platform support:** The library works across Windows, macOS, and Linux, ensuring portability across development environments including VS Code.
* **Foundation for advanced tasks:** Basic operations (cropping, resizing, color conversion) are prerequisites for machine learning pipelines, feature extraction, and computer vision models.


## ⚙️ How It Works

1. **Step 1 — Load the image:** OpenCV reads the image file from disk into memory as a NumPy array with shape `(height, width, channels)`.
2. **Step 2 — Identify the data structure:** Each image is stored in **BGR format** by default (Blue, Green, Red — not RGB), where each channel holds integer values from 0 to 255.
3. **Step 3 — Apply the operation:** Manipulate the array using OpenCV functions. Common operations follow this general transformation model:

   $$I_{out}(x, y) = f\big(I_{in}(x, y)\big)$$

   Where $I_{in}$ is the input pixel value, $f$ is the applied function (e.g., color conversion, resize, threshold), and $I_{out}$ is the resulting pixel value.

4. **Step 4 — Display or save the result:** The processed array is either rendered in a window using `cv2.imshow()` or written back to disk using `cv2.imwrite()`.


## 💻 Usage / Example

```python
import cv2
import numpy as np

# ── 1. READ AN IMAGE ──────────────────────────────────────────────────────────
image = cv2.imread("sample.jpg")          # Loads as BGR NumPy array
print("Shape:", image.shape)              # Output: (height, width, 3)

# ── 2. DISPLAY AN IMAGE ───────────────────────────────────────────────────────
cv2.imshow("Original", image)
cv2.waitKey(0)                            # Wait indefinitely for a key press
cv2.destroyAllWindows()

# ── 3. COLOR SPACE CONVERSION ─────────────────────────────────────────────────
gray   = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)   # Convert to Grayscale
hsv    = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)    # Convert to HSV
rgb    = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)    # Convert to RGB (for matplotlib)

# ── 4. RESIZE AN IMAGE ────────────────────────────────────────────────────────
resized = cv2.resize(image, (640, 480))             # Resize to fixed dimensions
half    = cv2.resize(image, (0, 0), fx=0.5, fy=0.5) # Resize by scale factor

# ── 5. CROP (REGION OF INTEREST) ──────────────────────────────────────────────
# Syntax: image[y_start:y_end, x_start:x_end]
roi = image[50:200, 100:300]              # Crop a rectangular region

# ── 6. DRAW ON AN IMAGE ───────────────────────────────────────────────────────
canvas = image.copy()
cv2.rectangle(canvas, (100, 50), (300, 200), (0, 255, 0), 2)  # Green rectangle
cv2.circle(canvas, (200, 150), 50, (255, 0, 0), 3)            # Blue circle
cv2.putText(canvas, "OpenCV", (100, 40),
            cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 2)      # Red text

# ── 7. ACCESS AND MODIFY PIXELS ───────────────────────────────────────────────
pixel_bgr = image[100, 200]               # Get pixel at row=100, col=200
print("BGR values:", pixel_bgr)           # Output: [B, G, R]

image[100, 200] = [0, 0, 255]             # Set pixel to red

# ── 8. SAVE AN IMAGE ──────────────────────────────────────────────────────────
cv2.imwrite("output.jpg", canvas)         # Save to disk
```

### Quick Reference Table

| Operation             | Function                              | Notes                            |
|-----------------------|---------------------------------------|----------------------------------|
| Read image            | `cv2.imread(path)`                    | Returns BGR NumPy array          |
| Show image            | `cv2.imshow(title, img)`              | Requires `waitKey()` after       |
| Save image            | `cv2.imwrite(path, img)`              | Supports `.jpg`, `.png`, etc.    |
| Convert color space   | `cv2.cvtColor(img, code)`             | Default format is BGR            |
| Resize                | `cv2.resize(img, (w, h))`             | Or use `fx`, `fy` for scaling    |
| Crop                  | `img[y1:y2, x1:x2]`                  | NumPy array slicing              |
| Draw rectangle        | `cv2.rectangle(img, pt1, pt2, color, thickness)` | Coordinates in `(x, y)` |
| Draw circle           | `cv2.circle(img, center, radius, color, thickness)` | Center in `(x, y)`  |
| Add text              | `cv2.putText(img, text, org, font, scale, color, thickness)` | —       |
| Get image dimensions  | `img.shape`                           | Returns `(H, W, C)`              |


## References

* [OpenCV Official Documentation](https://docs.opencv.org/4.x/) — Full API reference for all OpenCV functions and modules.
* [OpenCV Python Tutorials](https://docs.opencv.org/4.x/d6/d00/tutorial_py_root.html) — Step-by-step beginner tutorials for Python bindings.
* [NumPy Documentation](https://numpy.org/doc/) — Essential for understanding how images are stored as arrays.
* *Learning OpenCV 4* — Adrian Kaehler & Gary Bradski, Chapters 2–3 (Core Operations).
* *Programming Computer Vision with Python* — Jan Erik Solem, Chapter 1 (Basic Image Handling).
