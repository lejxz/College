# Polymorphism

## 📋 Summary
* **Core Concept:** Polymorphism is the OOP principle that allows objects of different classes to be treated through a common interface, with each object responding to the same method call in its own way.

> **Takeaways:** Polymorphism means "many forms." A single method name or interface can behave differently depending on the object that calls it. This is achieved through method overriding (runtime polymorphism) and method overloading (compile-time polymorphism).


## 📖 Definition

* **Polymorphism:** The ability of different objects to respond to the same method call in ways specific to their own class.
* **Runtime Polymorphism (Dynamic Dispatch):** Occurs when a subclass overrides a superclass method and the correct version is selected at runtime based on the actual object type.
* **Compile-time Polymorphism (Method Overloading):** Occurs when multiple methods share the same name but differ in the number or type of parameters; the correct version is selected at compile time.
* **Method Overriding:** A subclass provides its own implementation of a method defined in the superclass. Requires `@Override`.
* **Method Overloading:** Multiple methods in the same class share a name but have different parameter lists.
* **Requirements:**
    * Understanding of inheritance and method overriding
    * Familiarity with superclass references and the `@Override` annotation


## ❓ Why we use it

* **Flexibility:** A single interface or reference type can work with many different object types without modification.
* **Extensibility:** New subclasses can be introduced without changing existing code that uses the superclass reference.
* **Reduced Complexity:** Eliminates the need for long chains of `if-else` or `switch` statements to handle different object types.
* **Clean Design:** Enables consistent method calls across a class hierarchy, making code easier to read and maintain.


## ⚙️ How it works

1. **Define a superclass with a method:** This serves as the common interface for all subclasses.
2. **Create subclasses that override the method:** Each subclass provides its own behavior using `@Override`.
3. **Use a superclass reference to hold subclass objects:** A variable of the parent type can point to any child object.
4. **Call the method through the reference:** Java determines at runtime which version of the method to execute based on the actual object type (dynamic dispatch).
5. *(Optional)* **Use method overloading within a class:** Define multiple methods with the same name but different parameter signatures for compile-time polymorphism.


## 💻 Usage / Example

```java
// Step 1: Define the superclass with a common method
public class Shape {
    public void draw() {
        System.out.println("Drawing a generic shape.");
    }
}

// Step 2: Subclasses override the method with specific behavior
public class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle.");
    }
}

public class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle.");
    }
}

public class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Triangle.");
    }
}

// Step 3 & 4: Use superclass references — runtime decides which draw() to call
Shape[] shapes = {
    new Circle(),
    new Rectangle(),
    new Triangle()
};

for (Shape s : shapes) {
    s.draw();  // Each object calls its own overridden version
}
// Output:
// Drawing a Circle.
// Drawing a Rectangle.
// Drawing a Triangle.

// --- Compile-time Polymorphism: Method Overloading ---
public class Printer {
    public void print(String text) {
        System.out.println("Text: " + text);
    }

    public void print(int number) {
        System.out.println("Number: " + number);
    }

    public void print(String text, int copies) {
        for (int i = 0; i < copies; i++) {
            System.out.println("Text: " + text);
        }
    }
}

Printer p = new Printer();
p.print("Hello");       // Calls print(String)
p.print(42);            // Calls print(int)
p.print("Hi", 3);       // Calls print(String, int)
```


## References

* [Oracle Java Docs — Polymorphism](https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html) — Official reference on polymorphism in Java.
* [Introduction to OOP.pdf](https://drive.google.com/file/d/1gX-0gKWjqGXvSr_qltkrQH5RT4cKCtRW/view?usp=drive_link) — Foundational overview of OOP principles.