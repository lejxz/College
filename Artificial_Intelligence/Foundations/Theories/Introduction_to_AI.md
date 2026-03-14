# Introduction to AI

## 📋 Summary
* **Core Concept:** Artificial Intelligence (AI) is the broad field of building machines that simulate human intelligence. It encompasses Machine Learning (ML) and Neural Networks (NN) as increasingly specific sub-disciplines.

> **Takeaways:** AI is the umbrella term. Machine Learning is a method within AI that learns from data. Neural Networks are a specific ML architecture inspired by the human brain.


## 📖 Definition

* **Artificial Intelligence (AI):** The science of creating systems that can perform tasks that normally require human intelligence, such as reasoning, planning, and decision-making.
* **Machine Learning (ML):** A subset of AI in which algorithms learn patterns from data to make predictions or decisions without being explicitly programmed for each task.
* **Neural Network (NN):** A subset of ML composed of layers of interconnected nodes (neurons) that process data through weighted connections, loosely modeled after the biological brain.
* **Requirements:**
    * A defined problem or task
    * Data (for ML and NN)
    * Computational resources (especially for NN)


## ❓ Why we use it

* **AI:** Automates complex decision-making tasks that would be impractical to hard-code manually.
* **Machine Learning:** Allows systems to improve with experience, making it suitable for tasks with large, evolving datasets.
* **Neural Networks:** Excel at unstructured data tasks such as image recognition, speech processing, and natural language understanding.


## ⚙️ How it works

### Comparison of the Three Concepts

| Feature | AI | Machine Learning | Neural Networks |
|---|---|---|---|
| **Scope** | Broadest | Subset of AI | Subset of ML |
| **Learning** | May or may not learn | Learns from data | Learns via layered computation |
| **Data Dependency** | Low to High | High | Very High |
| **Interpretability** | Varies | Moderate | Low (often a "black box") |
| **Examples** | Expert systems, game bots | Decision trees, SVMs | CNNs, RNNs, Transformers |

### Conceptual Hierarchy

```
Artificial Intelligence
└── Machine Learning
    └── Neural Networks
        └── Deep Learning
```

### How Each Works (Step-by-Step)

**AI (Rule-Based Example):**
1. **Step 1:** Define a set of rules or logic (e.g., if-else, search algorithms).
2. **Step 2:** Feed the system an input.
3. **Step 3:** The system applies rules to produce an output.

**Machine Learning:**
1. **Step 1:** Collect and preprocess a labeled dataset.
2. **Step 2:** Select a model (e.g., linear regression, decision tree).
3. **Step 3:** Train the model by minimizing a loss function:
   $$L(\theta) = \frac{1}{n} \sum_{i=1}^{n} \left( \hat{y}_i - y_i \right)^2$$
4. **Step 4:** Evaluate and deploy the trained model.

**Neural Networks:**
1. **Step 1:** Define layers — input, hidden, and output.
2. **Step 2:** Pass data through each layer using weighted connections.
3. **Step 3:** Apply an activation function (e.g., ReLU) at each node:
   $$a = \text{ReLU}(z) = \max(0, z)$$
4. **Step 4:** Backpropagate error and update weights using gradient descent.


## 💻 Usage / Example

```python
# Example: Demonstrating the three levels — AI, ML, and NN

# --- LEVEL 1: Simple AI (Rule-Based) ---
def ai_classify(temperature: float) -> str:
    """Rule-based AI: no learning involved."""
    if temperature > 37.5:
        return "Fever detected"
    return "Normal"

# --- LEVEL 2: Machine Learning (Scikit-learn) ---
from sklearn.linear_model import LogisticRegression
import numpy as np

# Simulated training data: [temperature], label (1 = fever, 0 = normal)
X_train = np.array([[36.5], [37.0], [37.8], [38.5], [39.0]])
y_train = np.array([0, 0, 1, 1, 1])

ml_model = LogisticRegression()
ml_model.fit(X_train, y_train)
ml_prediction = ml_model.predict([[37.9]])  # Output: [1] → Fever

# --- LEVEL 3: Neural Network (PyTorch) ---
import torch
import torch.nn as nn

class SimpleNN(nn.Module):
    def __init__(self):
        super().__init__()
        self.net = nn.Sequential(
            nn.Linear(1, 8),
            nn.ReLU(),
            nn.Linear(8, 1),
            nn.Sigmoid()
        )

    def forward(self, x):
        return self.net(x)

nn_model = SimpleNN()
sample_input = torch.tensor([[37.9]])
nn_output = nn_model(sample_input)  # Raw probability output

print(f"Rule-based AI:    {ai_classify(37.9)}")
print(f"ML prediction:    {'Fever' if ml_prediction[0] == 1 else 'Normal'}")
print(f"NN raw output:    {nn_output.item():.4f}")
```


## References

* [Artificial Intelligence — Stanford Encyclopedia of Philosophy](https://plato.stanford.edu/entries/artificial-intelligence/) — Overview of AI definitions and history.
* [Géron, A. — *Hands-On Machine Learning with Scikit-Learn, Keras, and TensorFlow*, O'Reilly, Ch. 1] — Introduction to ML and the AI landscape.
* [MIT OpenCourseWare — Introduction to Deep Learning](http://introtodeeplearning.com/) — Lecture notes on ML and Neural Networks.