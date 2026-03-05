# Python Basics

## 📋 Summary

* **Core Concept:** Python is a high-level, interpreted, dynamically typed programming language designed for readability and simplicity. It supports multiple paradigms including procedural, object-oriented, and functional programming.

> **Takeaways:** Python uses indentation to define code blocks, requires no variable type declarations, and provides a rich standard library. It is widely used in scripting, automation, data science, machine learning, and web development.

---

## 📖 Definition

* **Interpreter:** A program that executes Python source code line-by-line at runtime, without prior compilation to machine code.
* **Dynamic Typing:** Variable types are resolved at runtime rather than at compile time.
* **Indentation:** Python uses consistent whitespace (typically 4 spaces) to delimit code blocks instead of braces `{}`.
* **REPL:** Read-Eval-Print Loop — an interactive shell for running Python expressions immediately.
* **Module:** A single `.py` file containing reusable functions, classes, or variables.
* **Package:** A directory of modules with an `__init__.py` file.

---

## ❓ Why We Use It

* **Readable syntax:** Python reads close to plain English, reducing cognitive load and making code easier to maintain.
* **Rapid prototyping:** Minimal boilerplate allows ideas to be tested quickly.
* **Extensive ecosystem:** Libraries like `NumPy`, `TensorFlow`, `Flask`, and `OpenCV` cover nearly every domain.
* **Cross-platform:** Runs on Windows, macOS, and Linux with minimal modification.
* **Community support:** One of the largest developer communities, with abundant documentation and third-party resources.

---

## ⚙️ Language Fundamentals

### 1. Running Python

```python
# Run a script
# $ python script.py

# Interactive shell
# $ python

# Check version
# $ python --version
```

---

### 2. Comments

```python
# This is a single-line comment

"""
This is a
multi-line string, often used as a docstring or block comment.
"""
```

---

### 3. Variables and Assignment

Python variables require no type declaration. They are created upon assignment.

```python
x = 10
name = "Alice"
pi = 3.14159
is_active = True

# Multiple assignment
a, b, c = 1, 2, 3

# Swap values
a, b = b, a

# Augmented assignment
x += 5   # x = x + 5
x -= 2
x *= 3
x //= 2  # Integer division assignment
x **= 2  # Exponentiation assignment
x %= 3   # Modulo assignment
```

---

### 4. Data Types

```python
# Integer
age = 25

# Float
height = 1.75

# Complex
z = 3 + 4j

# String
greeting = "Hello, World"

# Boolean
flag = True   # or False

# NoneType
value = None

# Check type
print(type(age))       # <class 'int'>
print(isinstance(age, int))  # True
```

---

### 5. Type Conversion

```python
int("42")        # 42
float("3.14")    # 3.14
str(100)         # "100"
bool(0)          # False
bool(1)          # True
list("abc")      # ['a', 'b', 'c']
```

---

### 6. Strings

```python
s = "Hello, Python"

# Indexing and slicing
s[0]       # 'H'
s[-1]      # 'n'
s[0:5]     # 'Hello'
s[::2]     # Every 2nd character
s[::-1]    # Reversed string

# Common methods
s.upper()           # 'HELLO, PYTHON'
s.lower()           # 'hello, python'
s.strip()           # Remove leading/trailing whitespace
s.replace("Python", "World")
s.split(", ")       # ['Hello', 'Python']
s.startswith("He")  # True
s.endswith("on")    # True
len(s)              # 13

# f-strings (recommended for formatting)
name = "Alice"
score = 95.5
print(f"Student: {name}, Score: {score:.2f}")

# String concatenation
full = "Hello" + " " + "World"

# Repetition
line = "-" * 20

# Multi-line string
text = """
Line one
Line two
Line three
"""

# Raw string (disables escape sequences)
path = r"C:\Users\Alice\Documents"
```

---

### 7. Operators

```python
# Arithmetic
5 + 2    # 7
5 - 2    # 3
5 * 2    # 10
5 / 2    # 2.5   (float division)
5 // 2   # 2     (integer division)
5 % 2    # 1     (modulo)
5 ** 2   # 25    (exponentiation)

# Comparison (return bool)
x == y
x != y
x > y
x < y
x >= y
x <= y

# Logical
True and False   # False
True or False    # True
not True         # False

# Identity
x is None        # True if x is None
x is not None

# Membership
3 in [1, 2, 3]   # True
"a" not in "bcd" # True

# Bitwise
0b1010 & 0b1100  # AND  → 0b1000
0b1010 | 0b1100  # OR   → 0b1110
0b1010 ^ 0b1100  # XOR  → 0b0110
~0b1010          # NOT
0b1010 << 1      # Left shift
0b1010 >> 1      # Right shift
```

---

### 8. Input and Output

```python
# Output
print("Hello")
print("a", "b", "c", sep="-")   # a-b-c
print("No newline", end="")

# Input (always returns string)
name = input("Enter your name: ")
age = int(input("Enter your age: "))
```

---

## 🔁 Control Flow

### 9. Conditionals

```python
score = 85

if score >= 90:
    print("A")
elif score >= 80:
    print("B")
elif score >= 70:
    print("C")
else:
    print("F")

# Ternary (inline conditional)
result = "Pass" if score >= 60 else "Fail"
```

---

### 10. Loops

```python
# for loop
for i in range(5):        # 0 1 2 3 4
    print(i)

for i in range(2, 10, 2): # 2 4 6 8
    print(i)

# Iterating over a list
fruits = ["apple", "banana", "cherry"]
for fruit in fruits:
    print(fruit)

# Enumerate (index + value)
for i, fruit in enumerate(fruits):
    print(i, fruit)

# while loop
count = 0
while count < 5:
    print(count)
    count += 1

# Loop controls
for i in range(10):
    if i == 3:
        continue    # Skip iteration
    if i == 7:
        break       # Exit loop
    print(i)

# else on loop (executes if loop was NOT broken)
for i in range(5):
    pass
else:
    print("Loop completed normally")
```

---

### 11. Exception Handling

```python
try:
    result = 10 / 0
except ZeroDivisionError as e:
    print(f"Error: {e}")
except (TypeError, ValueError) as e:
    print(f"Type or Value error: {e}")
else:
    print("No exception occurred")      # Runs only if no exception
finally:
    print("This always runs")

# Raising exceptions
def divide(a, b):
    if b == 0:
        raise ValueError("Denominator cannot be zero.")
    return a / b

# Custom exception
class NegativeValueError(Exception):
    pass

def sqrt(x):
    if x < 0:
        raise NegativeValueError(f"Cannot compute sqrt of {x}")
    return x ** 0.5
```

---

## 📦 Data Structures

### 12. Lists

Ordered, mutable, allows duplicates.

```python
nums = [1, 2, 3, 4, 5]

# Access
nums[0]    # 1
nums[-1]   # 5
nums[1:3]  # [2, 3]

# Modify
nums.append(6)          # Add to end
nums.insert(2, 99)      # Insert at index
nums.remove(99)         # Remove first occurrence
nums.pop()              # Remove and return last
nums.pop(0)             # Remove and return at index
del nums[0]             # Delete by index

# Info
len(nums)
nums.count(3)           # Count occurrences
nums.index(4)           # Find index

# Sort
nums.sort()             # In-place ascending
nums.sort(reverse=True) # In-place descending
sorted(nums)            # Returns new sorted list

# Other
nums.reverse()
nums.copy()
nums.extend([7, 8])     # Merge another list
nums.clear()            # Empty the list

# List comprehension
squares = [x**2 for x in range(10)]
evens   = [x for x in range(20) if x % 2 == 0]
matrix  = [[i * j for j in range(3)] for i in range(3)]
```

---

### 13. Tuples

Ordered, immutable, allows duplicates.

```python
point = (3, 5)
single = (42,)          # Trailing comma required for single-element tuple

# Unpacking
x, y = point

# Named tuple (from collections)
from collections import namedtuple
Point = namedtuple("Point", ["x", "y"])
p = Point(3, 5)
print(p.x, p.y)

# Tuples can be used as dictionary keys (lists cannot)
coords = {(0, 0): "origin", (1, 2): "A"}
```

---

### 14. Sets

Unordered, mutable, no duplicates.

```python
s = {1, 2, 3, 3, 2}    # {1, 2, 3}
empty = set()           # NOT {} — that creates a dict

s.add(4)
s.remove(2)             # Raises KeyError if missing
s.discard(99)           # No error if missing

# Set operations
a = {1, 2, 3}
b = {3, 4, 5}

a | b    # Union        → {1, 2, 3, 4, 5}
a & b    # Intersection → {3}
a - b    # Difference   → {1, 2}
a ^ b    # Symmetric diff → {1, 2, 4, 5}

# Membership test: O(1) average
3 in s   # True

# Frozenset (immutable set)
fs = frozenset([1, 2, 3])
```

---

### 15. Dictionaries

Ordered (Python 3.7+), mutable, key-value pairs, unique keys.

```python
person = {"name": "Alice", "age": 25, "city": "NY"}

# Access
person["name"]              # 'Alice'
person.get("age")           # 25
person.get("salary", 0)     # Default 0 if key missing

# Modify
person["age"] = 26
person["email"] = "a@b.com"
del person["city"]
person.pop("email")

# Iteration
for key in person:
    print(key)

for key, value in person.items():
    print(key, value)

for value in person.values():
    print(value)

# Info
len(person)
"name" in person    # True

# Merge (Python 3.9+)
merged = person | {"score": 100}

# Dict comprehension
squares = {x: x**2 for x in range(5)}

# Nested dict
data = {
    "user": {
        "id": 1,
        "roles": ["admin", "editor"]
    }
}
data["user"]["roles"][0]   # 'admin'
```

---

### 16. Stack and Queue (via collections)

```python
from collections import deque

# Stack (LIFO)
stack = []
stack.append(1)
stack.append(2)
stack.pop()     # 2

# Queue (FIFO) using deque
queue = deque()
queue.append(1)
queue.append(2)
queue.popleft()  # 1

# Counter
from collections import Counter
c = Counter("aabbccca")
c.most_common(2)     # [('a', 3), ('c', 3)]

# defaultdict
from collections import defaultdict
dd = defaultdict(list)
dd["key"].append(1)  # No KeyError
```

---

## 🔧 Functions

### 17. Defining Functions

```python
def greet(name):
    """Return a greeting string."""
    return f"Hello, {name}!"

print(greet("Alice"))   # Hello, Alice!
```

---

### 18. Parameters and Arguments

```python
# Default parameters
def power(base, exp=2):
    return base ** exp

power(3)       # 9
power(3, 3)    # 27

# Keyword arguments
def describe(name, age, city="Unknown"):
    print(f"{name}, {age}, from {city}")

describe(age=25, name="Bob")

# *args — variable positional arguments
def total(*nums):
    return sum(nums)

total(1, 2, 3, 4)   # 10

# **kwargs — variable keyword arguments
def info(**details):
    for k, v in details.items():
        print(f"{k}: {v}")

info(name="Alice", score=95)

# Combined
def mixed(a, b, *args, key="default", **kwargs):
    pass
```

---

### 19. Lambda Functions

```python
square = lambda x: x ** 2
square(5)   # 25

# Used with built-ins
nums = [3, 1, 4, 1, 5]
nums.sort(key=lambda x: -x)    # Descending sort

pairs = [(1, 'b'), (2, 'a')]
pairs.sort(key=lambda p: p[1]) # Sort by second element
```

---

### 20. Higher-Order Functions

```python
# map — apply function to each element
list(map(lambda x: x**2, [1, 2, 3]))   # [1, 4, 9]

# filter — keep elements matching predicate
list(filter(lambda x: x % 2 == 0, range(10)))  # [0, 2, 4, 6, 8]

# reduce
from functools import reduce
reduce(lambda a, b: a + b, [1, 2, 3, 4])   # 10

# zip — pair up iterables
list(zip([1, 2, 3], ['a', 'b', 'c']))   # [(1,'a'), (2,'b'), (3,'c')]

# any / all
any([False, True, False])   # True
all([True, True, True])     # True
```

---

### 21. Closures and Decorators

```python
# Closure
def make_multiplier(n):
    def multiply(x):
        return x * n
    return multiply

double = make_multiplier(2)
double(5)   # 10

# Decorator
def log(func):
    def wrapper(*args, **kwargs):
        print(f"Calling {func.__name__}")
        result = func(*args, **kwargs)
        print(f"Done")
        return result
    return wrapper

@log
def add(a, b):
    return a + b

add(2, 3)
# Calling add
# Done
# 5

# functools.wraps — preserve metadata
from functools import wraps

def log(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        return func(*args, **kwargs)
    return wrapper
```

---

### 22. Generators

```python
# Generator function (uses yield)
def count_up(n):
    for i in range(n):
        yield i

gen = count_up(5)
next(gen)           # 0
next(gen)           # 1
list(count_up(3))   # [0, 1, 2]

# Generator expression
squares = (x**2 for x in range(10))

# Useful for large datasets (lazy evaluation)
def read_large_file(path):
    with open(path) as f:
        for line in f:
            yield line.strip()
```

---

## 🏛️ Object-Oriented Programming

### 23. Classes and Objects

```python
class Animal:
    # Class variable (shared across all instances)
    kingdom = "Animalia"

    def __init__(self, name, sound):
        # Instance variables
        self.name = name
        self.sound = sound

    def speak(self):
        return f"{self.name} says {self.sound}"

    def __repr__(self):
        return f"Animal({self.name!r})"

    def __str__(self):
        return self.name


dog = Animal("Dog", "Woof")
print(dog.speak())    # Dog says Woof
print(repr(dog))      # Animal('Dog')
```

---

### 24. Inheritance

```python
class Dog(Animal):
    def __init__(self, name, breed):
        super().__init__(name, "Woof")
        self.breed = breed

    def fetch(self):
        return f"{self.name} fetches the ball!"

    # Override
    def speak(self):
        return f"{self.name} barks loudly!"


lab = Dog("Rex", "Labrador")
lab.speak()     # Rex barks loudly!
lab.fetch()     # Rex fetches the ball!
isinstance(lab, Dog)     # True
isinstance(lab, Animal)  # True
```

---

### 25. Special (Dunder) Methods

```python
class Vector:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __add__(self, other):
        return Vector(self.x + other.x, self.y + other.y)

    def __len__(self):
        return 2

    def __getitem__(self, index):
        return (self.x, self.y)[index]

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y

    def __repr__(self):
        return f"Vector({self.x}, {self.y})"


v1 = Vector(1, 2)
v2 = Vector(3, 4)
v1 + v2     # Vector(4, 6)
len(v1)     # 2
v1[0]       # 1
```

---

### 26. Properties and Access Control

```python
class Circle:
    def __init__(self, radius):
        self._radius = radius   # Convention: "private"

    @property
    def radius(self):
        return self._radius

    @radius.setter
    def radius(self, value):
        if value < 0:
            raise ValueError("Radius must be non-negative.")
        self._radius = value

    @property
    def area(self):
        import math
        return math.pi * self._radius ** 2


c = Circle(5)
c.radius        # 5
c.radius = 10   # Uses setter
c.area          # ~314.16
```

---

### 27. Class and Static Methods

```python
class Counter:
    count = 0

    def __init__(self):
        Counter.count += 1

    @classmethod
    def get_count(cls):
        return cls.count

    @staticmethod
    def is_positive(n):
        return n > 0


Counter()
Counter()
Counter.get_count()       # 2
Counter.is_positive(-1)   # False
```

---

### 28. Abstract Classes

```python
from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def area(self):
        pass

    @abstractmethod
    def perimeter(self):
        pass


class Rectangle(Shape):
    def __init__(self, w, h):
        self.w = w
        self.h = h

    def area(self):
        return self.w * self.h

    def perimeter(self):
        return 2 * (self.w + self.h)
```

---

## 📂 Modules and Packages

### 29. Importing

```python
import math
import math as m
from math import sqrt, pi
from math import *          # Import everything (avoid in large projects)

# Import from same package
from . import module         # Relative import
from .utils import helper    # Relative import of specific function
```

---

### 30. Useful Standard Library Modules

```python
import os
import sys
import math
import random
import datetime
import json
import re
import time
import pathlib
import itertools
import functools
import collections
import copy
import hashlib
import logging
```

---

### 31. The `if __name__ == "__main__"` Guard

```python
def main():
    print("Running as a script.")

if __name__ == "__main__":
    main()
# When imported as a module, main() is NOT called automatically.
```

---

## 📁 File I/O

### 32. Reading and Writing Files

```python
# Write a file
with open("data.txt", "w") as f:
    f.write("Hello\n")
    f.writelines(["Line 2\n", "Line 3\n"])

# Read entire file
with open("data.txt", "r") as f:
    content = f.read()

# Read line by line
with open("data.txt", "r") as f:
    for line in f:
        print(line.strip())

# Read all lines into list
with open("data.txt", "r") as f:
    lines = f.readlines()

# Append mode
with open("data.txt", "a") as f:
    f.write("New line\n")

# File modes
# "r"  — read (default)
# "w"  — write (overwrites)
# "a"  — append
# "rb" — read binary
# "wb" — write binary
```

---

### 33. JSON

```python
import json

data = {"name": "Alice", "scores": [90, 85, 95]}

# Serialize to string
json_str = json.dumps(data, indent=2)

# Deserialize from string
obj = json.loads(json_str)

# Write to file
with open("data.json", "w") as f:
    json.dump(data, f, indent=2)

# Read from file
with open("data.json", "r") as f:
    loaded = json.load(f)
```

---

### 34. `pathlib` (Modern File Paths)

```python
from pathlib import Path

p = Path("folder/subfolder/file.txt")
p.name          # 'file.txt'
p.stem          # 'file'
p.suffix        # '.txt'
p.parent        # PosixPath('folder/subfolder')
p.exists()      # True/False
p.is_file()     # True/False
p.is_dir()      # True/False

# Create directories
Path("new_dir").mkdir(parents=True, exist_ok=True)

# List files
for f in Path(".").iterdir():
    print(f)

# Glob pattern
for py_file in Path(".").glob("**/*.py"):
    print(py_file)
```

---

## 🔬 Advanced Topics

### 35. List, Set, Dict Comprehensions

```python
# List
squares = [x**2 for x in range(10) if x % 2 == 0]

# Set
unique_lens = {len(word) for word in ["hi", "hello", "hey"]}

# Dict
word_len = {word: len(word) for word in ["alpha", "beta", "gamma"]}
```

---

### 36. Context Managers

```python
# Using with statement
with open("file.txt") as f:
    data = f.read()

# Custom context manager
from contextlib import contextmanager

@contextmanager
def timer():
    import time
    start = time.time()
    yield
    elapsed = time.time() - start
    print(f"Elapsed: {elapsed:.4f}s")

with timer():
    sum(range(1_000_000))
```

---

### 37. Type Hints (PEP 484)

```python
def greet(name: str) -> str:
    return f"Hello, {name}"

def add(a: int, b: int) -> int:
    return a + b

from typing import List, Dict, Optional, Tuple, Union

def process(data: List[int]) -> Dict[str, int]:
    return {"sum": sum(data), "count": len(data)}

def find(name: str) -> Optional[str]:
    return name if name else None
```

---

### 38. Dataclasses

```python
from dataclasses import dataclass, field

@dataclass
class Student:
    name: str
    grade: int
    scores: list = field(default_factory=list)

    def average(self) -> float:
        return sum(self.scores) / len(self.scores) if self.scores else 0.0

s = Student("Alice", 10, [90, 85, 95])
s.average()   # 90.0
print(s)      # Student(name='Alice', grade=10, scores=[90, 85, 95])
```

---

### 39. Regular Expressions

```python
import re

text = "Contact us at support@example.com or info@domain.org"

# Search for pattern
match = re.search(r"\w+@\w+\.\w+", text)
if match:
    print(match.group())    # support@example.com

# Find all
emails = re.findall(r"\w+@\w+\.\w+", text)

# Substitute
clean = re.sub(r"\d+", "#", "Item 42 costs 100 units")

# Split
parts = re.split(r"\s+", "split   on  spaces")

# Compile for reuse
pattern = re.compile(r"^\d{3}-\d{4}$")
pattern.match("123-4567")  # Match object
```

---

### 40. Itertools and Functools

```python
import itertools
import functools

# Combinations and permutations
list(itertools.combinations([1,2,3], 2))   # [(1,2),(1,3),(2,3)]
list(itertools.permutations([1,2,3], 2))   # All ordered pairs

# Chain iterables
list(itertools.chain([1,2], [3,4], [5]))   # [1,2,3,4,5]

# Accumulate
list(itertools.accumulate([1,2,3,4], lambda a,b: a+b))  # [1,3,6,10]

# Groupby
data = [("a",1),("a",2),("b",3)]
for key, group in itertools.groupby(data, key=lambda x: x[0]):
    print(key, list(group))

# LRU Cache (memoization)
@functools.lru_cache(maxsize=None)
def fib(n):
    if n < 2:
        return n
    return fib(n-1) + fib(n-2)

fib(50)   # Very fast due to caching
```

---

### 41. Virtual Environments and Packages

```bash
# Create virtual environment
python -m venv venv

# Activate (Linux/macOS)
source venv/bin/activate

# Activate (Windows)
venv\Scripts\activate

# Deactivate
deactivate

# Install packages
pip install requests numpy

# Save dependencies
pip freeze > requirements.txt

# Restore dependencies
pip install -r requirements.txt
```

---

### 42. Common Built-in Functions

```python
abs(-5)             # 5
round(3.567, 2)     # 3.57
min(3, 1, 4)        # 1
max(3, 1, 4)        # 4
sum([1, 2, 3])      # 6
pow(2, 10)          # 1024

divmod(17, 5)       # (3, 2)  → quotient and remainder

# Sequence
len([1,2,3])        # 3
range(1, 10, 2)     # 1, 3, 5, 7, 9
reversed([1,2,3])   # iterator in reverse
sorted([3,1,2])     # [1, 2, 3]
enumerate(["a","b"])# [(0,'a'), (1,'b')]
zip([1,2], ["a","b"]) # [(1,'a'), (2,'b')]

# Type checks
type(3.14)          # <class 'float'>
isinstance(3, int)  # True

# Object inspection
dir(str)            # List of attributes/methods
help(str.split)     # Documentation

# Input/output
input("Prompt: ")
print("value:", 42, sep=" | ", end="\n")

# Eval and exec (use with caution)
eval("2 + 2")       # 4
exec("x = 10")
```

---

## 💻 Usage / Example

```python
# Example: A small student grade tracker using core Python concepts

from dataclasses import dataclass, field
from typing import List, Dict
import statistics

@dataclass
class Student:
    name: str
    grades: List[float] = field(default_factory=list)

    def add_grade(self, grade: float) -> None:
        if not (0 <= grade <= 100):
            raise ValueError(f"Grade {grade} is out of range.")
        self.grades.append(grade)

    def average(self) -> float:
        return statistics.mean(self.grades) if self.grades else 0.0

    def letter_grade(self) -> str:
        avg = self.average()
        if avg >= 90: return "A"
        elif avg >= 80: return "B"
        elif avg >= 70: return "C"
        elif avg >= 60: return "D"
        else: return "F"


def class_report(students: List[Student]) -> Dict[str, float]:
    return {s.name: s.average() for s in students}


# --- Main ---
alice = Student("Alice")
bob   = Student("Bob")

for g in [92, 88, 95, 79]:
    alice.add_grade(g)

for g in [70, 65, 72, 68]:
    bob.add_grade(g)

report = class_report([alice, bob])

for name, avg in report.items():
    print(f"{name}: {avg:.2f}")

# Alice: 88.50
# Bob:   68.75

print(f"Alice's letter grade: {alice.letter_grade()}")  # B
print(f"Bob's letter grade: {bob.letter_grade()}")      # D
```

---

## References

* [Official Python Documentation](https://docs.python.org/3/) — The primary reference for all built-ins, standard library, and language specification.
* [PEP 8 — Style Guide](https://peps.python.org/pep-0008/) — The official Python code style guide.
* [PEP 484 — Type Hints](https://peps.python.org/pep-0484/) — Specification for type annotation syntax.
* [Learn Python in Y Minutes](https://learnxinyminutes.com/docs/python/) — Quick reference for syntax overview.
* [Python Cheatsheet](https://www.pythoncheatsheet.org/) — Comprehensive community-maintained reference.
* [Fluent Python] — Luciano Ramalho, O'Reilly Media — Deep coverage of idiomatic Python.
* [Python Cookbook] — David Beazley & Brian Jones, O'Reilly Media — Practical recipes for intermediate topics.