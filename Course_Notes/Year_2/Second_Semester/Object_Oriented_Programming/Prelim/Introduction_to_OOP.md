# Introduction to Object-Oriented Programming (OOP) Fundamentals

## 📋 Summary
* **Core Concept:** Object-Oriented Programming is a programming paradigm based on objects and classes, designed for developing complex, large-scale systems through encapsulation, inheritance, polymorphism, and abstraction.

> **Takeaways:** OOP organizes code into reusable classes that serve as blueprints for creating objects. Each object contains both data (attributes) and behavior (methods), enabling modular and maintainable software design.

## 📖 Definition

* **Object-Orientation:** A technological framework based on objects and classes designed for developing complex, large-scale systems.
* **Class:** A template, blueprint, or prototype that defines variables and methods common to all objects of a certain kind.
* **Object:** A tangible or intangible entity that possesses both state (data) and behavior (methods).
* **Requirements:**
    * Understanding of basic programming constructs
    * Familiarity with data types and control structures

## 📊 Primary Properties of an OO System

| Property | Description | Purpose |
| :--- | :--- | :--- |
| **Encapsulation** | Packing data and methods into a single component | Data protection and information hiding |
| **Inheritance** | Parent-child relationship between classes | Code reusability and hierarchical organization |
| **Polymorphism** | Objects assuming different forms | Flexibility and extensibility |
| **Abstraction** | Hiding complex implementation details, exposing only essential features | Simplicity and reduced complexity |

* **Encapsulation:** The process of packing data and methods into a single component and protecting that data from outside access.
* **Inheritance:** A concept where classes exist in a parent-child relationship, allowing for hierarchical structures (e.g., a "Car" is a child of "Vehicle").
* **Polymorphism:** The ability of an object to assume many different forms, such as different shapes (Line, Circle) sharing a common "Draw" method.
* **Abstraction:** The process of hiding the internal implementation details of a class and exposing only the necessary, relevant features to the user. It allows the programmer to focus on *what* an object does rather than *how* it does it (e.g., a user calls `drive()` without needing to know the internal engine mechanics).

## ❓ Why we use it

* **Modularity:** Code is organized into independent, self-contained units (classes) that can be developed and tested separately.
* **Reusability:** Classes can be reused across different programs, reducing redundant code.
* **Maintainability:** Changes to one class typically do not affect other parts of the program.
* **Scalability:** OOP principles facilitate the development of large-scale, complex systems.

## ⚙️ How it works

1. **Define a Class:** Create a blueprint specifying attributes (data) and methods (behavior).
2. **Instantiate Objects:** Use the `new` keyword to create instances of the class.
3. **Access Members:** Use dot notation to access attributes and methods of objects.
4. **Apply Modifiers:** Control access to class members using access modifiers (public, private, protected).

### Class Syntax
```java
<modifier> class <class_name> {
    <attribute declaration>
    <method declaration>
}
```

### Declaring Attributes
```java
<modifier> <type> <name> [=default_value];
```

### Instance Variables vs. Static Variables

| Feature | Instance Variable | Static Variable |
| :--- | :--- | :--- |
| **Ownership** | Belongs to a specific object | Belongs to the class itself |
| **Value** | Unique to each object | Shared across all objects |
| **Example** | Car color, speed | Total car count |

### Access Modifiers

| Modifier | Visibility | Use Case |
| :--- | :--- | :--- |
| **public** | Everywhere | Classes, constructors, getters/setters |
| **protected** | Same class, package, and subclasses | Inheritance scenarios |
| **default** | Same class and package | Package-level access |
| **private** | Same class only | Fields (attributes) |

* **Standard Rule:** Classes, constructors, and getters/setters should generally be **public**. Fields (attributes) should generally be **private**.

### Methods

Methods define the behaviors of the class.

* **Syntax:** `<modifier> <return_type> <name> ([parameter_list]) { <statements>; }`
* **Getter (Accessor):** Used to read values from variables; they return a value and take no arguments.
* **Setter (Mutator):** Used to change variable values; they take an argument and return `void`.
* **The `this` Keyword:** Refers to the current instance of the class, often used in setters to distinguish between local parameters and class attributes.

### Constructors

A constructor is a special method used to initialize objects during instantiation.

* **Name:** Must match the class name exactly.
* **Return Type:** Does not have any return type (not even `void`).
* **Invocation:** Cannot be called directly; only triggered via the `new` keyword.
* **Default Constructor:** If no constructor is written, the system provides one with no arguments and an empty body.

## 💻 Usage / Program Example
```java
// Example: Account class demonstrating OOP principles
public class Account {
    // Private attributes (Encapsulation)
    private String accountNumber;
    private double balance;
    private static int totalAccounts = 0; // Static variable
    
    // Constructor
    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        totalAccounts++;
    }
    
    // Getter method
    public double getBalance() {
        return balance;
    }
    
    // Setter method
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
    }
    
    // Method demonstrating behavior
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    // Static method
    public static int getTotalAccounts() {
        return totalAccounts;
    }
}

// Creating and using objects
Account myAccount = new Account("123456", 1000.0);
myAccount.deposit(500.0);
System.out.println(myAccount.getBalance()); // Output: 1500.0
```

> **Abstraction in this example:** The user of the `Account` class calls `deposit()` and `getBalance()` without knowing the internal logic that validates amounts or manages the `balance` field. The internal details are hidden — only the essential interface is exposed.

## References

* [introduction to oop.pdf](https://drive.google.com/file/d/1gX-0gKWjqGXvSr_qltkrQH5RT4cKCtRW/view?usp=drive_link) — Foundational overview of OOP principles
* [creating classes.pdf](https://drive.google.com/file/d/1dyesEo5pb3fQQ7LkDjq8NeuuDKQwnxUl/view?usp=drive_link) — Technical syntax for implementing classes in Java