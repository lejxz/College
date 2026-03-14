# Python Advanced

## 📋 Summary

* **Core Concept:** Advanced Python covers the deeper mechanisms of the language — including the memory model, metaprogramming, concurrency, C extension interfacing, and performance optimization patterns. These topics are essential for building production-grade systems, research pipelines, and performance-critical applications.

> **Takeaways:** Python's power comes not just from its syntax but from its runtime internals. Mastering the object model, descriptor protocol, async I/O, and C interop enables writing code that is both expressive and efficient — critical for AI, systems, and security engineering.

---

## 📖 Definition

* **Descriptor:** An object that customizes attribute access via `__get__`, `__set__`, or `__delete__` on a class.
* **Metaclass:** A class whose instances are classes. Controls class creation behavior.
* **Coroutine:** A function defined with `async def` that can suspend and resume execution using `await`.
* **Event Loop:** The runtime scheduler for async tasks; provided by `asyncio`.
* **GIL (Global Interpreter Lock):** A mutex in CPython that prevents multiple threads from executing Python bytecode simultaneously.
* **Memory View:** A zero-copy interface for accessing the internal data of buffer-protocol objects.
* **`__slots__`:** A class-level declaration that replaces per-instance `__dict__` with a fixed set of attributes, reducing memory overhead.
* **Bytecode:** The intermediate representation compiled from Python source; executed by the CPython virtual machine.
* **Weak Reference:** A reference to an object that does not prevent garbage collection.

---

## ❓ Why We Use It

* **Performance:** Understanding CPython internals, memory layout, and the GIL is necessary for writing code that does not become a bottleneck.
* **Scalability:** Async I/O with `asyncio` enables handling thousands of concurrent I/O-bound tasks without thread overhead.
* **Extensibility:** Metaprogramming and descriptors enable the construction of frameworks, ORMs, and domain-specific abstractions.
* **Systems integration:** C extensions and `ctypes`/`cffi` allow Python to interface with low-level libraries — critical for CV pipelines (OpenCV), ML runtimes (TensorFlow), and security tools.
* **Research and production parity:** Profiling, caching, and parallelism patterns keep experimental code viable at scale.

---

## ⚙️ How It Works

### 1. The Python Data Model and Object Internals

Every value in Python is an object. Objects carry a type pointer, a reference count, and their data. The type itself is also an object.

```python
x = 42
print(type(x))          # <class 'int'>
print(type(type(x)))    # <class 'type'>
print(id(x))            # Memory address (CPython)

import sys
print(sys.getrefcount(x))  # Reference count (includes the call itself)
```

**Reference counting and `gc`:**

CPython uses reference counting as the primary memory management strategy. Cycles are handled by a separate cyclic garbage collector.

```python
import gc

gc.disable()    # Turn off cyclic GC (not reference counting)
gc.collect()    # Force a collection cycle
gc.get_count()  # (gen0, gen1, gen2) collection thresholds
```

---

### 2. `__slots__` — Memory-Efficient Classes

By default, each instance stores attributes in a `__dict__`. Declaring `__slots__` replaces this with a fixed-size C array.

```python
class Dense:
    __slots__ = ("x", "y", "z")

    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

import sys

class Normal:
    def __init__(self, x, y, z):
        self.x, self.y, self.z = x, y, z

n = Normal(1, 2, 3)
d = Dense(1, 2, 3)

print(sys.getsizeof(n.__dict__))    # ~232 bytes (dict overhead)
# Dense has no __dict__ — lower memory footprint
```

> **Use case:** High-frequency instantiation in ML pipelines (e.g., bounding box objects in YOLO post-processing).

---

### 3. Descriptors

A descriptor is any object that defines `__get__`, `__set__`, or `__delete__`. They are the mechanism behind `@property`, `classmethod`, and `staticmethod`.

```python
class Validated:
    """A data descriptor that enforces a numeric range."""

    def __set_name__(self, owner, name):
        self.name = name

    def __get__(self, obj, objtype=None):
        if obj is None:
            return self
        return obj.__dict__.get(self.name)

    def __set__(self, obj, value):
        if not isinstance(value, (int, float)):
            raise TypeError(f"{self.name} must be numeric.")
        if value < 0:
            raise ValueError(f"{self.name} must be non-negative.")
        obj.__dict__[self.name] = value


class Sensor:
    temperature = Validated()
    humidity    = Validated()

    def __init__(self, temp, hum):
        self.temperature = temp
        self.humidity    = hum


s = Sensor(22.5, 60)
# s.temperature = -5  → ValueError
```

**Descriptor lookup chain:**
1. Data descriptors on the class (define `__set__`)
2. Instance `__dict__`
3. Non-data descriptors and other class attributes

---

### 4. Metaclasses

A metaclass controls how a class is constructed. `type` is the default metaclass.

```python
# Equivalent class definitions
class MyClass:
    pass

MyClass = type("MyClass", (), {})


# Custom metaclass — enforce method naming convention
class EnforceLower(type):
    def __new__(mcs, name, bases, namespace):
        for key in namespace:
            if key.startswith("__"):
                continue
            if key != key.lower():
                raise TypeError(
                    f"Method '{key}' in '{name}' must be lowercase."
                )
        return super().__new__(mcs, name, bases, namespace)


class API(metaclass=EnforceLower):
    def get_data(self):   # OK
        pass

    # def GetData(self):  # Would raise TypeError
    #     pass
```

> **Common use:** Django ORM uses metaclasses to turn class-level field declarations into database column mappings.

---

### 5. `__init_subclass__` and Class Decorators

Lighter alternatives to metaclasses for hooking into subclass creation.

```python
class Plugin:
    _registry: dict = {}

    def __init_subclass__(cls, plugin_name: str, **kwargs):
        super().__init_subclass__(**kwargs)
        Plugin._registry[plugin_name] = cls


class JSONPlugin(Plugin, plugin_name="json"):
    def process(self, data): ...

class XMLPlugin(Plugin, plugin_name="xml"):
    def process(self, data): ...

print(Plugin._registry)
# {'json': <class 'JSONPlugin'>, 'xml': <class 'XMLPlugin'>}
```

---

### 6. Context Managers — `__enter__` / `__exit__`

```python
class ManagedResource:
    def __init__(self, name):
        self.name = name

    def __enter__(self):
        print(f"[OPEN]  {self.name}")
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        print(f"[CLOSE] {self.name}")
        # Return True to suppress exceptions
        return False

    def read(self):
        return f"Data from {self.name}"


with ManagedResource("sensor_feed") as res:
    print(res.read())

# [OPEN]  sensor_feed
# Data from sensor_feed
# [CLOSE] sensor_feed
```

**`contextlib.contextmanager` — generator-based shorthand:**

```python
from contextlib import contextmanager

@contextmanager
def temp_directory():
    import tempfile, shutil
    path = tempfile.mkdtemp()
    try:
        yield path
    finally:
        shutil.rmtree(path)

with temp_directory() as tmpdir:
    print(f"Working in: {tmpdir}")
```

---

### 7. Advanced Generators and `send()` / `throw()`

Generators are two-way channels: values can be sent back into them via `.send()`.

```python
def accumulator():
    total = 0
    while True:
        value = yield total
        if value is None:
            break
        total += value

gen = accumulator()
next(gen)           # Prime the generator (advance to first yield) → 0
gen.send(10)        # → 10
gen.send(25)        # → 35
gen.send(5)         # → 40


# Chaining generators with yield from
def flatten(nested):
    for item in nested:
        if isinstance(item, list):
            yield from flatten(item)
        else:
            yield item

list(flatten([1, [2, [3, 4]], 5]))  # [1, 2, 3, 4, 5]
```

---

### 8. Async I/O with `asyncio`

`asyncio` is an event-loop-based concurrency model suited to I/O-bound tasks (network, disk, sensors).

```python
import asyncio

async def fetch(url: str) -> str:
    await asyncio.sleep(0.1)    # Simulates I/O wait
    return f"Response from {url}"

async def main():
    urls = [
        "https://api.example.com/a",
        "https://api.example.com/b",
        "https://api.example.com/c",
    ]
    # Run all concurrently
    results = await asyncio.gather(*[fetch(u) for u in urls])
    for r in results:
        print(r)

asyncio.run(main())
```

**Async context managers and iterators:**

```python
import asyncio

class AsyncStream:
    def __init__(self, items):
        self.items = iter(items)

    def __aiter__(self):
        return self

    async def __anext__(self):
        try:
            await asyncio.sleep(0)   # Yield control to event loop
            return next(self.items)
        except StopIteration:
            raise StopAsyncIteration

async def consume():
    async for item in AsyncStream([10, 20, 30]):
        print(item)

asyncio.run(consume())
```

**Task management:**

```python
import asyncio

async def worker(name: str, delay: float):
    await asyncio.sleep(delay)
    print(f"{name} done")

async def main():
    t1 = asyncio.create_task(worker("A", 1.0))
    t2 = asyncio.create_task(worker("B", 0.5))
    await t1
    await t2

asyncio.run(main())
# B done
# A done
```

---

### 9. Threading and the GIL

Threads in CPython share the GIL, which means only one thread executes Python bytecode at a time. This makes threading useful only for I/O-bound work — not CPU-bound computation.

```python
import threading
import time

def download(url: str):
    time.sleep(1)    # Simulate I/O
    print(f"Downloaded: {url}")

threads = [
    threading.Thread(target=download, args=(f"url_{i}",))
    for i in range(5)
]

for t in threads:
    t.start()
for t in threads:
    t.join()
```

**Thread-safe data sharing with `Lock`:**

```python
import threading

counter = 0
lock = threading.Lock()

def increment():
    global counter
    for _ in range(100_000):
        with lock:
            counter += 1

t1 = threading.Thread(target=increment)
t2 = threading.Thread(target=increment)
t1.start(); t2.start()
t1.join();  t2.join()

print(counter)  # 200000
```

---

### 10. Multiprocessing — True Parallelism

`multiprocessing` spawns separate processes, each with its own GIL — enabling true CPU parallelism.

```python
from multiprocessing import Pool
import math

def heavy(n: int) -> float:
    return sum(math.sqrt(i) for i in range(n))

if __name__ == "__main__":
    with Pool(processes=4) as pool:
        results = pool.map(heavy, [1_000_000] * 8)
    print(results[:2])
```

**Shared memory between processes:**

```python
from multiprocessing import Value, Array
import multiprocessing

def worker(shared_val):
    shared_val.value += 1

if __name__ == "__main__":
    val = Value("i", 0)    # Shared integer
    procs = [multiprocessing.Process(target=worker, args=(val,)) for _ in range(4)]
    for p in procs: p.start()
    for p in procs: p.join()
    print(val.value)       # 4 (with lock protection)
```

---

### 11. `concurrent.futures` — Unified Interface

```python
from concurrent.futures import ThreadPoolExecutor, ProcessPoolExecutor
import time

def task(n):
    time.sleep(0.1)
    return n * n

# I/O-bound → ThreadPoolExecutor
with ThreadPoolExecutor(max_workers=4) as ex:
    futures = [ex.submit(task, i) for i in range(10)]
    results = [f.result() for f in futures]

print(results)

# CPU-bound → ProcessPoolExecutor
with ProcessPoolExecutor(max_workers=4) as ex:
    results = list(ex.map(task, range(10)))
```

---

### 12. Memory Views and Buffer Protocol

`memoryview` provides zero-copy access to the internal buffer of objects like `bytes` and `bytearray`. This is relevant when working with binary data in network protocols or image buffers.

```python
data = bytearray(b"Hello, World!")

view = memoryview(data)

# Slice without copying
slice_view = view[7:12]
print(bytes(slice_view))    # b'World'

# Modify in-place
view[0] = ord("h")
print(data)                 # bytearray(b'hello, World!')

# Shape and format
import array
arr = array.array("i", range(10))
mv  = memoryview(arr)
print(mv.format)    # 'i'
print(mv.itemsize)  # 4
```

---

### 13. C Extensions via `ctypes`

`ctypes` allows calling functions from compiled shared libraries without writing C extension code.

```python
import ctypes
import ctypes.util

# Load the standard C library
libc_name = ctypes.util.find_library("c")
libc = ctypes.CDLL(libc_name)

# Call printf
libc.printf(b"Hello from C: %d\n", ctypes.c_int(42))

# Define argument and return types explicitly (recommended)
libc.strlen.argtypes = [ctypes.c_char_p]
libc.strlen.restype  = ctypes.c_size_t

print(libc.strlen(b"Python"))   # 6
```

---

### 14. Profiling and Performance Analysis

**`cProfile` — deterministic profiler:**

```python
import cProfile
import pstats

def slow_function():
    return sum(i**2 for i in range(100_000))

with cProfile.Profile() as pr:
    slow_function()

stats = pstats.Stats(pr)
stats.sort_stats("cumulative")
stats.print_stats(10)    # Top 10 functions by cumulative time
```

**`timeit` — micro-benchmarks:**

```python
import timeit

t1 = timeit.timeit("[x**2 for x in range(1000)]", number=1000)
t2 = timeit.timeit("list(map(lambda x: x**2, range(1000)))", number=1000)

print(f"List comp: {t1:.4f}s")
print(f"Map:       {t2:.4f}s")
```

**`tracemalloc` — memory profiling:**

```python
import tracemalloc

tracemalloc.start()

data = [i**2 for i in range(100_000)]

snapshot = tracemalloc.take_snapshot()
top_stats = snapshot.statistics("lineno")

for stat in top_stats[:5]:
    print(stat)
```

---

### 15. `functools` — Advanced Function Tools

```python
import functools

# lru_cache — memoization with LRU eviction
@functools.lru_cache(maxsize=128)
def fib(n: int) -> int:
    if n < 2:
        return n
    return fib(n - 1) + fib(n - 2)

# cache — unbounded memoization (Python 3.9+)
@functools.cache
def factorial(n: int) -> int:
    return 1 if n == 0 else n * factorial(n - 1)

# partial — freeze arguments
from functools import partial

def power(base, exp):
    return base ** exp

square = partial(power, exp=2)
cube   = partial(power, exp=3)

print(square(5))   # 25
print(cube(3))     # 27

# reduce
from functools import reduce
product = reduce(lambda a, b: a * b, [1, 2, 3, 4, 5])   # 120

# total_ordering — derive comparison methods from __eq__ and one other
from functools import total_ordering

@total_ordering
class Version:
    def __init__(self, major, minor):
        self.major = major
        self.minor = minor

    def __eq__(self, other):
        return (self.major, self.minor) == (other.major, other.minor)

    def __lt__(self, other):
        return (self.major, self.minor) < (other.major, other.minor)

v1 = Version(1, 0)
v2 = Version(2, 3)
print(v1 < v2)    # True
print(v1 >= v2)   # False (derived automatically)
```

---

### 16. Advanced Type Hints (PEP 484 / 526 / 544 / 612)

```python
from typing import (
    TypeVar, Generic, Protocol, Callable,
    overload, Final, Literal, TypedDict,
)

# TypeVar — generic functions and classes
T = TypeVar("T")

def first(items: list[T]) -> T:
    return items[0]

# Generic class
class Stack(Generic[T]):
    def __init__(self) -> None:
        self._data: list[T] = []

    def push(self, item: T) -> None:
        self._data.append(item)

    def pop(self) -> T:
        return self._data.pop()

# Protocol — structural subtyping (duck typing, typed)
class Drawable(Protocol):
    def draw(self) -> None: ...

def render(obj: Drawable) -> None:
    obj.draw()

# TypedDict — typed dictionary shapes
class Config(TypedDict):
    host: str
    port: int
    debug: bool

cfg: Config = {"host": "localhost", "port": 8080, "debug": False}

# Literal — restrict to specific values
Mode = Literal["r", "w", "a", "rb", "wb"]

def open_file(path: str, mode: Mode) -> None: ...

# Final — disallow reassignment
MAX_RETRIES: Final = 5

# overload — multiple signatures
@overload
def process(x: int) -> int: ...
@overload
def process(x: str) -> str: ...

def process(x):
    if isinstance(x, int):
        return x * 2
    return x.upper()
```

---

### 17. Abstract Base Classes and `__subclasshook__`

```python
from abc import ABC, abstractmethod

class Serializable(ABC):
    @abstractmethod
    def serialize(self) -> bytes: ...

    @abstractmethod
    def deserialize(self, data: bytes) -> None: ...

    @classmethod
    def __subclasshook__(cls, C):
        # Allow virtual subclassing: any class with these methods qualifies
        if cls is Serializable:
            if hasattr(C, "serialize") and hasattr(C, "deserialize"):
                return True
        return NotImplemented


class JSONRecord:
    def serialize(self) -> bytes:
        return b"{}"

    def deserialize(self, data: bytes) -> None:
        pass

print(isinstance(JSONRecord(), Serializable))   # True (virtual subclass)
```

---

### 18. Weak References

Weak references allow referencing an object without preventing its garbage collection. Useful for caches and observer patterns.

```python
import weakref

class Node:
    def __init__(self, value):
        self.value = value

n = Node(42)
ref = weakref.ref(n)

print(ref())        # <Node object ...>
del n
print(ref())        # None  — object was collected

# WeakValueDictionary — cache that does not prevent GC
cache = weakref.WeakValueDictionary()

def get_node(key):
    if key not in cache:
        cache[key] = Node(key)
    return cache[key]

a = get_node(1)
print(cache[1].value)   # 1
del a
# cache[1] now refers to a collected object
```

---

### 19. Operator Overloading — Full Reference

```python
class Matrix:
    def __init__(self, rows):
        self.rows = rows

    # Arithmetic
    def __add__(self, other):       return NotImplemented
    def __sub__(self, other):       return NotImplemented
    def __mul__(self, other):       return NotImplemented
    def __matmul__(self, other):    return NotImplemented   # @ operator
    def __truediv__(self, other):   return NotImplemented
    def __floordiv__(self, other):  return NotImplemented
    def __mod__(self, other):       return NotImplemented
    def __pow__(self, other):       return NotImplemented

    # Reflected (right-hand side)
    def __radd__(self, other):      return NotImplemented
    def __rmul__(self, other):      return NotImplemented

    # In-place
    def __iadd__(self, other):      return NotImplemented

    # Comparison
    def __eq__(self, other):        return NotImplemented
    def __lt__(self, other):        return NotImplemented

    # Container protocol
    def __len__(self):              return len(self.rows)
    def __getitem__(self, index):   return self.rows[index]
    def __setitem__(self, index, v): self.rows[index] = v
    def __contains__(self, item):   return any(item in row for row in self.rows)
    def __iter__(self):             return iter(self.rows)

    # Callable
    def __call__(self, *args):      return NotImplemented

    # Representation
    def __repr__(self):             return f"Matrix({self.rows!r})"
    def __str__(self):              return "\n".join(str(r) for r in self.rows)

    # Hashing (required if __eq__ is defined)
    def __hash__(self):             return id(self)
```

---

### 20. `dataclasses` — Advanced Usage

```python
from dataclasses import dataclass, field, KW_ONLY, InitVar
from typing import ClassVar

@dataclass(order=True, frozen=True)
class Point:
    # frozen=True → immutable (enables hashing)
    # order=True  → generates __lt__, __le__, etc. from field order
    x: float
    y: float

    def distance(self) -> float:
        return (self.x**2 + self.y**2) ** 0.5


@dataclass
class Model:
    name: str
    weights: list = field(default_factory=list, repr=False)
    _version: ClassVar[str] = "1.0"        # Class variable, not an instance field
    _: KW_ONLY                             # All following fields are keyword-only
    device: str = "cpu"

    # InitVar — used in __post_init__ but not stored
    scale: InitVar[float] = 1.0

    def __post_init__(self, scale: float):
        self.weights = [w * scale for w in self.weights]


m = Model("ResNet", [0.5, 1.0], scale=2.0, device="cuda")
print(m)
# Model(name='ResNet', device='cuda')
```

---

### 21. Structural Pattern Matching (`match` / `case`, Python 3.10+)

```python
def handle_event(event: dict):
    match event:
        case {"type": "click", "x": x, "y": y}:
            print(f"Click at ({x}, {y})")

        case {"type": "keypress", "key": str(k)} if k.isalpha():
            print(f"Letter pressed: {k}")

        case {"type": "resize", "width": w, "height": h} if w > 0 and h > 0:
            print(f"Resize to {w}x{h}")

        case {"type": str(t)}:
            print(f"Unknown event type: {t}")

        case _:
            print("Malformed event")


handle_event({"type": "click", "x": 100, "y": 200})
handle_event({"type": "keypress", "key": "a"})
```

---

## 💻 Usage / Example

A production-grade pipeline demonstrating: async I/O, descriptors, generics, type hints, `dataclasses`, `functools.cache`, and `concurrent.futures`.

```python
"""
Advanced Python: Async object detection result aggregator.
Simulates a pipeline of CV inference workers reporting to a central collector.
"""

import asyncio
import time
from dataclasses import dataclass, field
from typing import Generic, TypeVar
from functools import cache


# --- Descriptor: Validated confidence score ---
class Confidence:
    def __set_name__(self, owner, name):
        self.name = name

    def __get__(self, obj, objtype=None):
        if obj is None:
            return self
        return obj.__dict__.get(self.name, 0.0)

    def __set__(self, obj, value: float):
        if not (0.0 <= value <= 1.0):
            raise ValueError(f"{self.name} must be in [0.0, 1.0], got {value}")
        obj.__dict__[self.name] = value


# --- Dataclass: Bounding Box ---
@dataclass(frozen=True, order=True)
class BBox:
    x: int
    y: int
    w: int
    h: int

    @property
    def area(self) -> int:
        return self.w * self.h


# --- Dataclass: Detection Result ---
@dataclass
class Detection:
    label: str
    bbox: BBox
    score: float = field(default=0.0)
    _score = Confidence()   # Descriptor attached at class level

    def __post_init__(self):
        # Validate via descriptor manually (field default bypasses __set__)
        type(self)._score.__set__(self, self.score)


# --- Generic Result Container ---
T = TypeVar("T")

class ResultBucket(Generic[T]):
    def __init__(self):
        self._items: list[T] = []

    def add(self, item: T) -> None:
        self._items.append(item)

    def all(self) -> list[T]:
        return list(self._items)

    def __len__(self) -> int:
        return len(self._items)


# --- Cached label lookup ---
@cache
def get_class_name(class_id: int) -> str:
    # Simulates a cached lookup into a label map
    labels = {0: "person", 1: "car", 2: "bicycle", 3: "traffic_light"}
    return labels.get(class_id, "unknown")


# --- Async inference worker ---
async def run_inference(frame_id: int, class_id: int, confidence: float) -> Detection:
    await asyncio.sleep(0.05)    # Simulate network/GPU I/O
    label = get_class_name(class_id)
    bbox  = BBox(x=frame_id * 10, y=frame_id * 5, w=100, h=50)
    return Detection(label=label, bbox=bbox, score=confidence)


# --- Pipeline ---
async def pipeline(frames: list[tuple[int, int, float]]) -> ResultBucket[Detection]:
    bucket: ResultBucket[Detection] = ResultBucket()
    tasks = [run_inference(fid, cid, conf) for fid, cid, conf in frames]
    results = await asyncio.gather(*tasks)
    for det in results:
        bucket.add(det)
    return bucket


# --- Entry point ---
if __name__ == "__main__":
    frame_data = [
        (0, 0, 0.91),   # person
        (1, 1, 0.85),   # car
        (2, 2, 0.78),   # bicycle
        (3, 3, 0.95),   # traffic_light
        (4, 0, 0.60),   # person (low confidence)
    ]

    start = time.perf_counter()
    bucket = asyncio.run(pipeline(frame_data))
    elapsed = time.perf_counter() - start

    print(f"Processed {len(bucket)} detections in {elapsed:.4f}s\n")
    for det in bucket.all():
        print(f"  [{det.score:.2f}] {det.label:15s} bbox={det.bbox}")

# Processed 5 detections in ~0.05s
#   [0.91] person          bbox=BBox(x=0,  y=0,  w=100, h=50)
#   [0.85] car             bbox=BBox(x=10, y=5,  w=100, h=50)
#   [0.78] bicycle         bbox=BBox(x=20, y=10, w=100, h=50)
#   [0.95] traffic_light   bbox=BBox(x=30, y=15, w=100, h=50)
#   [0.60] person          bbox=BBox(x=40, y=20, w=100, h=50)
```

---

## References

* [Python Data Model](https://docs.python.org/3/reference/datamodel.html) — Official reference for dunder methods and the object protocol.
* [PEP 544 — Protocols](https://peps.python.org/pep-0544/) — Structural subtyping specification.
* [PEP 634 — Structural Pattern Matching](https://peps.python.org/pep-0634/) — `match`/`case` statement specification.
* [PEP 557 — Data Classes](https://peps.python.org/pep-0557/) — `@dataclass` specification.
* [asyncio Documentation](https://docs.python.org/3/library/asyncio.html) — Event loop, tasks, and coroutine API.
* [Python Concurrency Docs](https://docs.python.org/3/library/concurrency.html) — `threading`, `multiprocessing`, `concurrent.futures`.
* [Fluent Python, 2nd Ed.] — Luciano Ramalho, O'Reilly Media — Chapters on the data model, descriptors, and metaprogramming.
* [CPython Internals] — Anthony Shaw, Real Python — CPython bytecode, GIL, memory allocator.
* [High Performance Python, 2nd Ed.] — Micha Gorelick & Ian Ozsvald, O'Reilly Media — Profiling, Cython, and parallelism.
