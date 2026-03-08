# Artificial Intelligence

## 📋 Summary
* **Core Concept:** Artificial Intelligence (AI) is the field of computer science focused on building systems that can reason, learn, and act in ways that simulate human intelligence.

> **Takeaways:** AI is the broadest category in intelligent computing. It includes both rule-based systems and learning-based approaches. Its goal is to automate tasks that would otherwise require human cognition.


## 📖 Definition

* **Artificial Intelligence (AI):** A branch of computer science concerned with the design of systems capable of performing tasks that require human-like intelligence, including reasoning, language understanding, perception, and planning.
* **Agent:** Any system that perceives its environment through sensors and acts upon it through actuators.
* **Rationality:** The property of an agent that acts to achieve the best expected outcome given its knowledge and goals.
* **Requirements:**
    * A clearly defined problem or environment
    * A performance measure to evaluate behavior
    * A knowledge representation scheme
    * A mechanism for decision-making or inference


## ❓ Why we use it

* **Automation:** AI removes the need for manual, repetitive decision-making in complex systems.
* **Scalability:** AI systems can process information and make decisions at a scale and speed that humans cannot match.
* **Adaptability:** Learning-based AI systems can adjust to new data or changing environments over time.
* **Broad applicability:** AI is used in healthcare, cybersecurity, finance, robotics, and AR/VR systems.


## ⚙️ How it works
1. **Step 1:** Define the **problem** and the **goal** of the AI agent (e.g., classify an image, play a game).
2. **Step 2:** Choose an AI approach:
   * Rule-based (expert systems, logic)
   * Search-based (BFS, A* algorithm)
   * Learning-based (ML, NN)
3. **Step 3:** Represent knowledge using an appropriate structure (decision trees, graphs, probability distributions).
4. **Step 4:** Apply inference or a search strategy to produce an output:
   $$\text{Utility}(s) = \sum_{a} P(a \mid s) \cdot R(s, a)$$
   where $P(a \mid s)$ is the probability of action $a$ in state $s$, and $R(s, a)$ is the reward.
5. **Step 5:** Evaluate performance against the defined metric and refine the system.


## 💻 Usage / Example

```python
# Example: Simple Rule-Based AI — Symptom Checker (Expert System)

def symptom_checker(symptoms: list[str]) -> str:
    """
    A rule-based AI agent that diagnoses a condition
    based on a predefined set of rules.
    """
    rules = {
        frozenset(["fever", "cough", "fatigue"]): "Possible flu — consult a doctor.",
        frozenset(["headache", "nausea"]): "Possible migraine — rest and hydrate.",
        frozenset(["chest pain", "shortness of breath"]): "Seek emergency care immediately.",
    }

    input_set = frozenset(symptoms)

    for condition, diagnosis in rules.items():
        if condition.issubset(input_set):
            return diagnosis

    return "No matching condition found. Please consult a medical professional."


# Test the AI agent
patient_symptoms = ["fever", "cough", "fatigue", "sore throat"]
result = symptom_checker(patient_symptoms)
print(f"Diagnosis: {result}")
# Output: Diagnosis: Possible flu — consult a doctor.
```


## References

* [Russell, S. & Norvig, P. — *Artificial Intelligence: A Modern Approach*, 4th Ed., Ch. 1–2] — Foundational definitions of AI, agents, and rationality.
* [Stanford AI Lab](https://ai.stanford.edu/) — Research and resources in AI fundamentals.
* [MIT OpenCourseWare — 6.034 Artificial Intelligence](https://ocw.mit.edu/courses/6-034-artificial-intelligence-fall-2010/) — Lecture notes and problem sets.