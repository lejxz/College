# Neural Networks

## 📋 Summary
* **Core Concept:** A Neural Network (NN) is a subset of Machine Learning composed of layers of interconnected nodes (neurons) that learn hierarchical representations from data through a process called backpropagation.

> **Takeaways:** Neural Networks are the foundation of modern Deep Learning. They are particularly effective for complex tasks involving unstructured data such as images, audio, and text. Their power comes at the cost of interpretability and computational resources.


## 📖 Definition

* **Neural Network (NN):** A computational model made up of layers of artificial neurons, each performing a weighted sum of its inputs followed by a non-linear activation function, trained to minimize a loss via gradient descent.
* **Neuron (Node):** The basic unit of a neural network that computes a weighted sum of inputs and applies an activation function.
* **Activation Function:** A non-linear function applied to a neuron's output to introduce complexity (e.g., ReLU, Sigmoid, Tanh).
* **Backpropagation:** The algorithm used to compute gradients of the loss with respect to each weight, enabling parameter updates.
* **Requirements:**
    * A labeled dataset (for supervised learning)
    * A defined network architecture (number of layers and neurons)
    * An activation function, loss function, and optimizer
    * Sufficient computational resources (GPU recommended for deep networks)


## ❓ Why we use it

* **Feature learning:** NNs automatically extract useful features from raw data, removing the need for manual feature engineering.
* **Unstructured data:** NNs handle images, audio, and text better than traditional ML models.
* **Scalability:** Performance improves significantly with more data and larger architectures (deep learning).
* **Relevance to AI & AR/VR:** NNs power computer vision, object detection, scene understanding, and gesture recognition — all critical in AR/VR systems.


## ⚙️ How it works
1. **Step 1:** Define the **architecture** — number of layers, neurons per layer, and activation functions.
2. **Step 2:** Perform a **forward pass** — compute the output layer by layer:
   $$z^{(l)} = W^{(l)} a^{(l-1)} + b^{(l)}, \quad a^{(l)} = f(z^{(l)})$$
   where $W^{(l)}$ are weights, $b^{(l)}$ are biases, and $f$ is the activation function.
3. **Step 3:** Compute the **loss** between the predicted and true output (e.g., Cross-Entropy for classification):
   $$L = -\sum_{i} y_i \log(\hat{y}_i)$$
4. **Step 4:** Perform **backpropagation** — compute gradients using the chain rule:
   $$\frac{\partial L}{\partial W^{(l)}} = \frac{\partial L}{\partial a^{(l)}} \cdot \frac{\partial a^{(l)}}{\partial z^{(l)}} \cdot \frac{\partial z^{(l)}}{\partial W^{(l)}}$$
5. **Step 5:** Update weights using an optimizer (e.g., Adam, SGD):
   $$W := W - \alpha \cdot \frac{\partial L}{\partial W}$$


## 💻 Usage / Example

```python
# Example: Binary Classification Neural Network using PyTorch
# Task: Classify network traffic as benign (0) or malicious (1)

import torch
import torch.nn as nn
import torch.optim as optim

# --- Data: [packet_size, request_rate, failed_logins] ---
X = torch.tensor([
    [512, 10, 0],
    [1024, 5, 0],
    [256, 200, 15],
    [128, 500, 30],
    [800, 8, 1],
    [300, 350, 20],
], dtype=torch.float32)

y = torch.tensor([[0], [0], [1], [1], [0], [1]], dtype=torch.float32)

# --- Define the Neural Network ---
class TrafficClassifier(nn.Module):
    def __init__(self):
        super().__init__()
        self.network = nn.Sequential(
            nn.Linear(3, 16),   # Input layer → Hidden layer 1
            nn.ReLU(),
            nn.Linear(16, 8),   # Hidden layer 1 → Hidden layer 2
            nn.ReLU(),
            nn.Linear(8, 1),    # Hidden layer 2 → Output
            nn.Sigmoid()        # Output: probability between 0 and 1
        )

    def forward(self, x: torch.Tensor) -> torch.Tensor:
        return self.network(x)

# --- Training Setup ---
model = TrafficClassifier()
criterion = nn.BCELoss()                    # Binary Cross-Entropy Loss
optimizer = optim.Adam(model.parameters(), lr=0.01)

# --- Training Loop ---
for epoch in range(200):
    optimizer.zero_grad()           # Clear previous gradients
    output = model(X)               # Forward pass
    loss = criterion(output, y)     # Compute loss
    loss.backward()                 # Backpropagation
    optimizer.step()                # Update weights

    if (epoch + 1) % 50 == 0:
        print(f"Epoch {epoch + 1}/200 | Loss: {loss.item():.4f}")

# --- Inference ---
new_traffic = torch.tensor([[400, 450, 25]], dtype=torch.float32)
probability = model(new_traffic).item()
label = "Malicious" if probability >= 0.5 else "Benign"
print(f"\nPrediction: {label} (confidence: {probability:.4f})")
```


## References

* [Goodfellow, I., Bengio, Y., & Courville, A. — *Deep Learning*, MIT Press, Ch. 6–7] — Theoretical foundations of feedforward networks and backpropagation.
* [PyTorch Documentation](https://pytorch.org/docs/stable/index.html) — Official reference for building and training neural networks.
* [MIT — Introduction to Deep Learning (6.S191)](http://introtodeeplearning.com/) — Lecture slides and labs covering NN fundamentals.