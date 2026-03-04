# Abstraction

## 📋 Summary
* **Core Concept:** Abstraction is the OOP principle of hiding complex internal implementation details and exposing only the essential features of an object, allowing the user to interact with *what* it does rather than *how* it does it.

> **Takeaways:** Abstraction is achieved in Java through abstract classes and interfaces. It defines a contract — what methods must exist — without specifying how those methods are implemented. This separates design from implementation.


## 📖 Definition

* **Abstraction:** The process of exposing only the relevant capabilities of an object while concealing unnecessary internal complexity.
* **Abstract Class:** A class declared with the `abstract` keyword that cannot be instantiated directly. It may contain both abstract methods (no body) and concrete methods (with body).
* **Abstract Method:** A method declared with no implementation, forcing all non-abstract subclasses to provide their own version.
* **Interface:** A fully abstract contract that declares method signatures only. A class implements an interface using the `implements` keyword and must define all declared methods.
* **Concrete Class:** A non-abstract class that provides full implementations of all inherited abstract methods and can be instantiated.
* **Requirements:**
    * Understanding of inheritance and method overriding
    * Familiarity with the `abstract` keyword and interfaces


## ❓ Why we use it

* **Simplicity:** Users of a class interact with a simple, clean interface without needing to understand the internal mechanics.
* **Separation of Concerns:** Design (what to do) is separated from implementation (how to do it), making systems easier to manage.
* **Enforced Structure:** Abstract classes and interfaces guarantee that all subclasses implement a required set of behaviors.
* **Flexibility:** Multiple concrete classes can implement the same abstract contract in entirely different ways.


## ⚙️ How it works

1. **Declare an abstract class or interface:** Define the method signatures that all subclasses or implementors must provide.
2. **Mark methods as `abstract`:** These methods have no body — only a declaration.
3. **Create concrete subclasses:** Each subclass extends the abstract class (or implements the interface) and provides full method bodies.
4. **Instantiate the concrete class:** The abstract class itself cannot be instantiated with `new`, only its concrete subclasses can.
5. **Use the abstract type as a reference:** Code can refer to objects through the abstract type, keeping the design decoupled from the implementation.


## 💻 Usage / Example

```java
// Step 1 & 2: Declare an abstract class with abstract and concrete methods
public abstract class Vehicle {

    private String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;  // Concrete method — shared behavior
    }

    // Abstract method — each vehicle type must define how it moves
    public abstract void move();

    // Abstract method — each vehicle type must define its fuel type
    public abstract String getFuelType();
}

// Step 3: Concrete subclass — provides implementations for all abstract methods
public class Car extends Vehicle {

    public Car(String brand) {
        super(brand);
    }

    @Override
    public void move() {
        System.out.println(getBrand() + " car is driving on the road.");
    }

    @Override
    public String getFuelType() {
        return "Gasoline";
    }
}

// Step 3: Another concrete subclass with different implementations
public class Boat extends Vehicle {

    public Boat(String brand) {
        super(brand);
    }

    @Override
    public void move() {
        System.out.println(getBrand() + " boat is sailing on water.");
    }

    @Override
    public String getFuelType() {
        return "Diesel";
    }
}

// Step 4 & 5: Use the abstract type as a reference
// Vehicle v = new Vehicle("X");  // ❌ Compile error — cannot instantiate abstract class

Vehicle car  = new Car("Toyota");
Vehicle boat = new Boat("Yamaha");

car.move();                          // Output: Toyota car is driving on the road.
System.out.println(car.getFuelType()); // Output: Gasoline

boat.move();                           // Output: Yamaha boat is sailing on water.
System.out.println(boat.getFuelType()); // Output: Diesel

// --- Using an Interface for full abstraction ---
public interface Printable {
    void printDetails();  // All implementors must define this
}

public class Invoice implements Printable {
    @Override
    public void printDetails() {
        System.out.println("Printing invoice details...");
    }
}
```


## References

* [Oracle Java Docs — Abstract Methods and Classes](https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html) — Official reference on abstract classes and methods in Java.
* [Oracle Java Docs — Interfaces](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html) — Official reference on interfaces in Java.
* [Introduction to OOP.pdf](https://drive.google.com/file/d/1gX-0gKWjqGXvSr_qltkrQH5RT4cKCtRW/view?usp=drive_link) — Foundational overview of OOP principles.