# Introduction to Computer Vision

## 📋 Summary

* **Core Concept:** Computer Vision (CV) is a field of artificial intelligence that enables machines to interpret and understand visual information from the world, such as images and video, in a manner similar to human sight.

> **Takeaways:** Computer Vision bridges raw pixel data and meaningful interpretation. It serves as the perceptual backbone of modern AI systems — from facial recognition in security to object tracking in AR/VR environments. Understanding its foundations is essential before applying libraries like OpenCV or building vision-based ML models.


## 📖 Definition

* **Computer Vision (CV):** A subfield of artificial intelligence concerned with enabling machines to extract, analyze, and understand information from visual data (images, video, or other visual inputs).
* **Image:** A two-dimensional grid of pixel values, where each pixel encodes intensity or color information.
* **Pixel (Picture Element):** The smallest discrete unit of a digital image, represented as an integer value (grayscale) or a tuple of values (e.g., RGB or BGR for color).
* **Feature:** A measurable, distinguishable property extracted from an image — such as edges, corners, textures, or shapes — used for recognition and classification.
* **Model (CV context):** A mathematical function trained to map visual input to a desired output (e.g., label, bounding box, depth estimate).
* **Ground Truth:** The verified, correct label or annotation for a given piece of visual data, used to train and evaluate models.
* **Requirements:**
    * Foundational knowledge of linear algebra (matrices, vectors)
    * Basic understanding of Python and NumPy
    * Familiarity with image formats (`.jpg`, `.png`, `.bmp`)
    * Optional: introductory knowledge of machine learning concepts


## ❓ Why We Use It

* **Automation of visual tasks:** CV removes the need for manual inspection in repetitive or high-volume tasks such as quality control in manufacturing or medical image screening.
* **Enabling intelligent systems:** Autonomous vehicles, drones, and AR/VR headsets all rely on CV to perceive and respond to their physical environment in real time.
* **Security and surveillance:** CV powers biometric authentication (face recognition, iris scanning) and anomaly detection in video feeds — directly relevant to the Cybersecurity domain.
* **AR/VR foundation:** Spatial mapping, hand tracking, and environment understanding in augmented and virtual reality are entirely dependent on computer vision algorithms.
* **AI and ML integration:** CV is one of the primary input modalities for deep learning models, making it central to building intelligent, perception-driven applications.


## ⚙️ How It Works

1. **Step 1 — Image Acquisition:** Visual data is captured through a sensor (camera, scanner, LiDAR) and stored as a matrix of pixel values.

2. **Step 2 — Preprocessing:** The raw image is cleaned and normalized. This includes operations such as resizing, noise removal, contrast adjustment, and color space conversion to prepare data for analysis.

3. **Step 3 — Feature Extraction:** Meaningful patterns are identified within the image. The image is analyzed for low-level features (edges, gradients) or high-level features (faces, objects), described mathematically as:

   $$F = \phi(I)$$

   Where $I$ is the input image, $\phi$ is the feature extraction function, and $F$ is the resulting feature representation.

4. **Step 4 — Analysis / Inference:** The extracted features are passed to a model or algorithm that performs the desired task — classification, detection, segmentation, or reconstruction.

5. **Step 5 — Output / Decision:** The system produces a structured result: a label, bounding box, mask, depth map, or action — which is then used by a downstream application.


## 💻 Usage / Example

```python
import cv2
import numpy as np

# ── PIPELINE DEMONSTRATION ────────────────────────────────────────────────────
# This example walks through a simplified CV pipeline:
# Acquire → Preprocess → Extract Features → Analyze → Output

# ── STEP 1: IMAGE ACQUISITION ─────────────────────────────────────────────────
image = cv2.imread("scene.jpg")           # Load image from disk (BGR format)
print("Original shape:", image.shape)     # (height, width, channels)

# ── STEP 2: PREPROCESSING ─────────────────────────────────────────────────────
# Convert to grayscale — reduces complexity, many algorithms require single channel
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

# Apply Gaussian blur to reduce noise
blurred = cv2.GaussianBlur(gray, (5, 5), 0)

# ── STEP 3: FEATURE EXTRACTION — Edge Detection ───────────────────────────────
# Canny edge detector: finds boundaries where pixel intensity changes sharply
# Parameters: (image, low_threshold, high_threshold)
edges = cv2.Canny(blurred, threshold1=50, threshold2=150)

# ── STEP 4: ANALYSIS — Contour Detection ──────────────────────────────────────
# Contours represent the outlines of detected shapes/objects
contours, hierarchy = cv2.findContours(
    edges,
    cv2.RETR_EXTERNAL,        # Retrieve only outermost contours
    cv2.CHAIN_APPROX_SIMPLE   # Compress horizontal/vertical segments
)
print(f"Objects detected: {len(contours)}")

# ── STEP 5: OUTPUT — Draw Results ─────────────────────────────────────────────
output = image.copy()
cv2.drawContours(output, contours, -1, (0, 255, 0), 2)  # Draw all in green

cv2.imshow("Original", image)
cv2.imshow("Edges (Features)", edges)
cv2.imshow("Detected Contours (Output)", output)
cv2.waitKey(0)
cv2.destroyAllWindows()

cv2.imwrite("cv_pipeline_output.jpg", output)
```

### CV Task Categories

| Task                  | Description                                           | Example Use Case                    |
|-----------------------|-------------------------------------------------------|-------------------------------------|
| **Classification**    | Assign a label to an entire image                     | "Is this a cat or a dog?"           |
| **Object Detection**  | Locate and label objects within an image              | Bounding boxes around pedestrians   |
| **Segmentation**      | Assign a label to every individual pixel              | AR object masking, medical imaging  |
| **Depth Estimation**  | Predict distance of objects from the camera           | VR spatial mapping, robotics        |
| **Optical Flow**      | Track pixel movement between video frames             | Motion detection, gesture tracking  |
| **Pose Estimation**   | Identify the position and orientation of body joints  | AR avatars, physical therapy        |

### CV Pipeline at a Glance

```
[Camera / Sensor]
       ↓
[Raw Image (Pixel Array)]
       ↓
[Preprocessing — Resize, Denoise, Normalize]
       ↓
[Feature Extraction — Edges, Keypoints, Descriptors]
       ↓
[Model / Algorithm — Classifier, Detector, Estimator]
       ↓
[Output — Label, Mask, Bounding Box, Depth Map]
```


## References

* [OpenCV Official Documentation](https://docs.opencv.org/4.x/) — Primary reference for CV functions and algorithms in Python.
* [CS231n: Convolutional Neural Networks for Visual Recognition](https://cs231n.stanford.edu/) — Stanford course covering CV fundamentals and deep learning applications.
* [PyImageSearch](https://pyimagesearch.com/) — Practical tutorials on OpenCV, image processing, and computer vision pipelines.
* *Computer Vision: Algorithms and Applications* — Richard Szeliski, Chapters 1–2 (Introduction and Image Formation). Available free at [szeliski.org](https://szeliski.org/Book/).
* *Programming Computer Vision with Python* — Jan Erik Solem, Chapter 1 (Basic Image Handling and Concepts).