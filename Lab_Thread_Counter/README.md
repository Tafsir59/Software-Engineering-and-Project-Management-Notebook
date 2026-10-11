# Java Thread Counting: Static vs Atomic Variables

## Overview

This project demonstrates how Java multithreading affects shared static variables. It compares a normal static variable with an `AtomicLong` to understand race conditions and thread-safe counting.

The program allows users to specify the number of threads, the number of increments per thread, and whether to use thread-safe counting.

## Concepts Covered

- Java Multithreading
- Thread creation using the `Thread` class
- `start()` and `run()` methods
- `join()` for waiting for threads to finish
- Static variables shared between threads
- Non-atomic operations and race conditions
- Thread-safe counting using `AtomicLong`
- Getter methods for accessing static counters
- Error percentage calculation

## Project Structure
├── Main.java
├── MyThread.java
└── README.md

## How It Works

The project uses two Java classes.

### 1. Main.java

The `Main` class:

- Reads the number of threads, increments per thread, and thread-safety mode from command-line arguments.
- Creates and starts the specified number of threads.
- Uses `join()` to wait until all threads finish.
- Calculates the expected count.
- Displays the expected, safe, and unsafe counts.
- Calculates the error percentage for the selected counting mode.

### 2. MyThread.java

The `MyThread` class extends Java's `Thread` class and contains two shared static counters.

**Safe Counter**

```java
static AtomicLong SafeCount = new AtomicLong(0);