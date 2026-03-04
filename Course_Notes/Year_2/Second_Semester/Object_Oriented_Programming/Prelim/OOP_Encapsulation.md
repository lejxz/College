# Encapsulation

## 📋 Summary
* **Core Concept:** Encapsulation is the OOP principle of bundling data (attributes) and the methods that operate on that data into a single unit (a class), while restricting direct access to the internal state from outside the class.

> **Takeaways:** Encapsulation protects an object's internal data by making fields private and exposing them only through controlled public methods (getters and setters). This enforces data integrity and hides implementation details from the outside world.


## 📖 Definition

* **Encapsulation:** The process of packing data (attributes) and behavior (methods) into a single class, and controlling access to that data through access modifiers.
* **Access Modifier:** A keyword that sets the visibility level of a class member (field, method, or constructor).
* **Getter (Accessor):** A public method that returns the value of a private field.
* **Setter (Mutator):** A public method that validates and assigns a new value to a private field.
* **Requirements:**
    * Understanding of classes and objects
    * Familiarity with access modifiers (`private`, `public`, `protected`)


## ❓ Why we use it

* **Data Protection:** Private fields cannot be modified directly from outside the class, preventing invalid or unintended state changes.
* **Information Hiding:** The internal implementation can change without affecting code that uses the class, as long as the public interface remains the same.
* **Controlled Access:** Setters can include validation logic to enforce rules (e.g., a balance cannot be negative).
* **Maintainability:** Isolating data within a class makes bugs easier to locate and fix.


## ⚙️ How it works

1. **Declare fields as `private`:** This prevents direct access from outside the class.
2. **Declare the class and constructor as `public`:** This allows the class to be instantiated from anywhere.
3. **Write public getter methods:** Each getter returns the value of one private field.
4. **Write public setter methods:** Each setter receives a value, optionally validates it, and assigns it to the private field using the `this` keyword.
5. **Access data through methods only:** External code reads and writes data exclusively through these getters and setters.


## 💻 Usage / Example

```java
// Example: BankAccount class demonstrating Encapsulation
public class BankAccount {

    // Step 1: Private fields — cannot be accessed directly from outside
    private String owner;
    private double balance;

    // Step 2: Public constructor
    public BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        // Uses setter to apply validation on initialization
        setBalance(initialBalance);
    }

    // Step 3: Getter methods
    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    // Step 4: Setter with validation logic
    public void setBalance(double balance) {
        if (balance >= 0) {           // Rule: balance cannot be negative
            this.balance = balance;
        } else {
            System.out.println("Invalid balance. Must be non-negative.");
        }
    }

    // Additional behavior that also respects encapsulation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

// External code — can only interact through public methods
BankAccount account = new BankAccount("Alice", 500.0);
account.deposit(250.0);
System.out.println(account.getBalance()); // Output: 750.0

// account.balance = -9999;  // ❌ Compile error — field is private
account.setBalance(-9999);   // ✅ Allowed, but setter rejects invalid value
```


## References

* [Oracle Java Docs — Controlling Access to Members of a Class](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html) — Official reference on access modifiers in Java.
* [Introduction to OOP.pdf](https://drive.google.com/file/d/1gX-0gKWjqGXvSr_qltkrQH5RT4cKCtRW/view?usp=drive_link) — Foundational overview of OOP principles.