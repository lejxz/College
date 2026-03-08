# Machine Learning

## 📋 Summary
* **Core Concept:** Machine Learning (ML) is a subset of AI in which systems learn patterns from data to make predictions or decisions, without being explicitly programmed for each specific task.

> **Takeaways:** ML shifts programming from writing explicit rules to training a model on examples. The model generalizes from training data to make predictions on new, unseen inputs.


## 📖 Definition

* **Machine Learning (ML):** A field of AI where algorithms are trained on data to identify patterns and make decisions with minimal human intervention, improving their performance over time.
* **Training Data:** The labeled or unlabeled dataset used to fit a model's parameters.
* **Model:** A mathematical function that maps inputs to outputs after being trained on data.
* **Loss Function:** A measure of how far the model's predictions are from the true labels, used to guide optimization.
* **Requirements:**
    * A dataset (labeled for supervised learning, unlabeled for unsupervised)
    * A chosen algorithm or model architecture
    * A loss function and an optimization strategy
    * A method to evaluate generalization (e.g., train/test split, cross-validation)


## ❓ Why we use it

* **Pattern recognition:** ML finds patterns in high-dimensional data that are too complex for hand-coded rules.
* **Generalization:** Trained models can make accurate predictions on data they have never seen before.
* **Automation of learning:** Systems improve without being reprogrammed as new data becomes available.
* **Relevance to AI & Cybersecurity:** ML powers anomaly detection, malware classification, intrusion detection systems, and more.


## ⚙️ How it works

### Types of ML

| Type | Description | Example |
|---|---|---|
| **Supervised** | Learns from labeled input-output pairs | Spam detection, image classification |
| **Unsupervised** | Finds structure in unlabeled data | Clustering, dimensionality reduction |
| **Reinforcement** | Learns by receiving rewards/penalties | Game-playing agents, robotics |

### General Training Pipeline
1. **Step 1:** Collect and preprocess data (handle missing values, normalize features).
2. **Step 2:** Split data into training and test sets.
3. **Step 3:** Choose a model and define a loss function, e.g., Mean Squared Error:
   $$L(\theta) = \frac{1}{n} \sum_{i=1}^{n} \left( \hat{y}_i - y_i \right)^2$$
4. **Step 4:** Optimize the model using gradient descent:
   $$\theta := \theta - \alpha \nabla_\theta L(\theta)$$
   where $\alpha$ is the learning rate.
5. **Step 5:** Evaluate on the test set using metrics such as accuracy, precision, recall, or F1-score.


## 💻 Usage / Example

```python
# Example: Supervised ML — Classifying Network Traffic as Malicious or Benign

from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
import numpy as np

# Simulated dataset: [packet_size, request_rate, failed_logins]
# Label: 1 = malicious, 0 = benign
X = np.array([
    [512, 10, 0],   # benign
    [1024, 5, 0],   # benign
    [256, 200, 15], # malicious (high request rate, many failures)
    [128, 500, 30], # malicious
    [800, 8, 1],    # benign
    [300, 350, 20], # malicious
])
y = np.array([0, 0, 1, 1, 0, 1])

# Step 1: Split data
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.33, random_state=42
)

# Step 2: Train model
model = RandomForestClassifier(n_estimators=100, random_state=42)
model.fit(X_train, y_train)

# Step 3: Evaluate model
y_pred = model.predict(X_test)
print(classification_report(y_test, y_pred, target_names=["Benign", "Malicious"]))

# Step 4: Predict on new traffic
new_traffic = np.array([[400, 450, 25]])
prediction = model.predict(new_traffic)
print(f"Prediction: {'Malicious' if prediction[0] == 1 else 'Benign'}")
```


## References

* [Géron, A. — *Hands-On Machine Learning with Scikit-Learn, Keras, and TensorFlow*, O'Reilly, Ch. 1–4] — Core ML concepts, pipelines, and algorithms.
* [Scikit-learn Documentation](https://scikit-learn.org/stable/user_guide.html) — Reference for ML algorithms and evaluation metrics.
* [Bishop, C. — *Pattern Recognition and Machine Learning*, Springer] — Mathematical foundations of ML.