# Inheritance

## 📋 Summary
* **Core Concept:** Inheritance is the OOP principle that allows a class (child/subclass) to acquire the attributes and methods of another class (parent/superclass), establishing a hierarchical relationship between classes.

> **Takeaways:** Inheritance promotes code reuse by letting subclasses inherit common behavior from a superclass and extend or override it as needed. It models real-world "is-a" relationships (e.g., a `Dog` *is a* `Animal`).


## 📖 Definition

* **Superclass (Parent Class):** The class whose attributes and methods are inherited by another class.
* **Subclass (Child Class):** The class that inherits from a superclass and may add new attributes, methods, or override existing ones.
* **Inheritance:** A mechanism in which a subclass derives properties and behaviors from a superclass using the `extends` keyword.
* **Method Overriding:** When a subclass provides its own implementation of a method already defined in the superclass.
* **`super` Keyword:** Used in a subclass to refer to the superclass — commonly used to call the parent constructor or a parent method.
* **Requirements:**
    * Understanding of classes, objects, and constructors
    * Familiarity with access modifiers, particularly `protected`


## ❓ Why we use it

* **Code Reuse:** Common logic is written once in the superclass and automatically available to all subclasses, reducing redundancy.
* **Hierarchical Organization:** Reflects real-world relationships and organizes classes in a logical, layered structure.
* **Extensibility:** New subclasses can be added without modifying the existing superclass.
* **Maintainability:** A fix or update to a shared behavior in the superclass propagates to all subclasses automatically.


## ⚙️ How it works

1. **Define a superclass:** Write the common attributes and methods shared by all related classes.
2. **Use `extends` to create a subclass:** The subclass automatically inherits all non-private members of the superclass.
3. **Call the parent constructor with `super()`:** Must be the first statement in the subclass constructor if used.
4. **Add subclass-specific members:** Declare new attributes or methods unique to the subclass.
5. **Override methods when needed:** Use `@Override` to redefine a superclass method with new behavior in the subclass.


## 💻 Usage / Example

```java
// Step 1: Define the superclass
public class Animal {

    protected String name;  // protected: accessible by subclasses
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void describe() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// Step 2: Define a subclass using 'extends'
public class Dog extends Animal {

    private String breed;  // Subclass-specific attribute

    // Step 3: Call the parent constructor using 'super'
    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    // Step 4: Add subclass-specific behavior
    public void fetch() {
        System.out.println(name + " is fetching the ball!");
    }

    // Step 5: Override a superclass method
    @Override
    public void describe() {
        super.describe();  // Calls the parent version first
        System.out.println("Breed: " + breed);
    }
}

// Usage
Dog dog = new Dog("Rex", 3, "Labrador");
dog.eat();      // Inherited from Animal  → Output: Rex is eating.
dog.fetch();    // Defined in Dog         → Output: Rex is fetching the ball!
dog.describe(); // Overridden in Dog      → Output: Name: Rex, Age: 3 / Breed: Labrador
```


## References

* [Oracle Java Docs — Inheritance](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html) — Official reference on inheritance and subclassing in Java.
* [Introduction to OOP.pdf](https://drive.google.com/file/d/1gX-0gKWjqGXvSr_qltkrQH5RT4cKCtRW/view?usp=drive_link) — Foundational overview of OOP principles.