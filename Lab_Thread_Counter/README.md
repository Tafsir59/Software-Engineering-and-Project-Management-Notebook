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
