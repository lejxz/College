# Feature Detection (OpenCV)

## 📋 Summary
* **Core Concept:** Feature detection identifies distinctive points, corners, or blobs in an image that remain useful under changes like scale, rotation, and lighting. In OpenCV, these features are used for tasks such as matching, tracking, object recognition, and image stitching.

> **Takeaways:** Good features are repeatable and discriminative. OpenCV provides classic detectors (Harris, Shi-Tomasi, FAST) and modern keypoint+descriptor pipelines (SIFT, ORB) to balance speed and robustness.


## 📖 Definition

* **Feature Detection:** The process of finding salient image locations (keypoints) that are stable and informative for computer vision tasks.
* **Keypoint Descriptor:** A numeric vector describing the neighborhood around a keypoint so it can be matched across images.
* **Requirements:**
	* Input image quality should be reasonable (low blur/noise helps).
	* Chosen detector must fit the task constraints (speed, invariance, accuracy).


## ❓ Why we use it

* **Robust matching:** Features allow correspondence between two images of the same scene.
* **Tracking:** Stable points can be tracked frame-to-frame in video.
* **Recognition and localization:** Keypoints help identify objects or estimate camera motion.
* **Efficiency:** Operating on sparse keypoints is often cheaper than dense pixel processing.


## ⚙️ How it works
1. **Step 1:** Convert image to grayscale and optionally denoise.
2. **Step 2:** Locate the **Basic Operation** (local intensity comparison/gradient response for corner or blob strength).
3. **Step 3:** Set up the mathematical model:
	 $$T(n) \approx c_{op} C(n)$$
4. **Step 4:** Simplify using the order of growth.
5. **Step 5:** Keep strongest responses using thresholds or non-maximum suppression.
6. **Step 6:** (Optional) Compute descriptors and match keypoints between images.


## 💻 Usage / Example
```python
# Example: ORB keypoint detection and drawing (OpenCV)
import cv2

img = cv2.imread("sample.jpg", cv2.IMREAD_GRAYSCALE)

# Create detector
orb = cv2.ORB_create(nfeatures=500)

# Detect keypoints and descriptors
keypoints, descriptors = orb.detectAndCompute(img, None)

# Visualize keypoints
out = cv2.drawKeypoints(img, keypoints, None, color=(0, 255, 0),
												flags=cv2.DRAW_MATCHES_FLAGS_DRAW_RICH_KEYPOINTS)

cv2.imshow("ORB Features", out)
cv2.waitKey(0)
cv2.destroyAllWindows()
```

```python
# Example: Harris corner response
import cv2
import numpy as np

img = cv2.imread("checkerboard.jpg")
gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
gray = np.float32(gray)

dst = cv2.cornerHarris(gray, blockSize=2, ksize=3, k=0.04)
dst = cv2.dilate(dst, None)

# Mark strong corners in red
img[dst > 0.01 * dst.max()] = [0, 0, 255]

cv2.imshow("Harris Corners", img)
cv2.waitKey(0)
cv2.destroyAllWindows()
```


## References

* [OpenCV Feature Detection and Description](https://docs.opencv.org/4.x/db/d27/tutorial_py_table_of_contents_feature2d.html) - Official OpenCV tutorials for keypoints, descriptors, and matching.
* [OpenCV ORB Class Reference](https://docs.opencv.org/4.x/db/d95/classcv_1_1ORB.html) - API details for ORB detector/descriptor.
* [Computer Vision: Algorithms and Applications] - Richard Szeliski, feature detection and matching chapters.
