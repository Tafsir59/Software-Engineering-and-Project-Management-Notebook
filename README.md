# Java Multithreading — Race Condition

This project demonstrates a **race condition in Java multithreading** and two common ways to solve it:

1. `synchronized`
2. `AtomicInteger`

## 📌 Concept

Three threads share a single counter. Each thread increments the counter 100,000 times.

```text
Number of threads = 3
Increments per thread = 100,000

Expected count = 3 × 100,000
               = 300,000
```

## 🔴 Race Condition

The following operation is not thread-safe:

```java
count++;
```

Although it looks like one operation, it involves reading, modifying, and writing the value.

When multiple threads execute it concurrently, some increments may be lost.

Example:

```text
Expected count = 300000
Actual count   = 247831
```

The actual result can vary between executions.

---

# 🟢 Solution 1 — `synchronized`

The `increment()` method can be synchronized:

```java
static synchronized void increment() {
    count++;
}
```

This ensures that only **one thread at a time** can execute the method.

### `M1.java`

```java
public class M1 extends Thread {

    static int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            increment();
        }
    }

    static synchronized void increment() {
        count++;
    }
}
```

### `Main.java`

```java
public class Main {
    public static void main(String[] args) throws InterruptedException {

        M1 t1 = new M1();
        M1 t2 = new M1();
        M1 t3 = new M1();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Expected count = " + 300000);
        System.out.println("Actual count = " + M1.count);
    }
}
```

### Result

```text
Expected count = 300000
Actual count = 300000
```

---

# 🟢 Solution 2 — `AtomicInteger`

Java provides `AtomicInteger` for thread-safe operations on integers.

Instead of:

```java
static int count = 0;
```

we use:

```java
static AtomicInteger count = new AtomicInteger(0);
```

And instead of:

```java
count++;
```

we use:

```java
count.incrementAndGet();
```

### `M1.java`

```java
import java.util.concurrent.atomic.AtomicInteger;

public class M1 extends Thread {

    static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            count.incrementAndGet();
        }
    }
}
```

### `Main.java`

```java
public class Main {
    public static void main(String[] args) throws InterruptedException {

        M1 t1 = new M1();
        M1 t2 = new M1();
        M1 t3 = new M1();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Expected count = " + 300000);
        System.out.println("Actual count = " + M1.count.get());
    }
}
```

### Result

```text
Expected count = 300000
Actual count = 300000
```

---

# 🔍 Important Methods

### `start()`

Starts a thread and causes its `run()` method to execute.

```java
t1.start();
```

### `join()`

Makes the main thread wait until the specified thread finishes.

```java
t1.join();
```

`join()` **does not solve the race condition**. It only ensures that the threads have finished before we print the final result.

### `synchronized`

Allows only one thread at a time to execute the synchronized method.

```java
static synchronized void increment()
```

### `AtomicInteger`

Provides thread-safe operations on an integer.

```java
count.incrementAndGet();
```

To retrieve the current value:

```java
count.get();
```

---

# ⚖️ `synchronized` vs `AtomicInteger`

| Feature                  | `synchronized`                      | `AtomicInteger`     |
| ------------------------ | ----------------------------------- | ------------------- |
| Thread-safe              | ✅                                   | ✅                   |
| Prevents race condition  | ✅                                   | ✅                   |
| Uses locking             | ✅                                   | ❌                   |
| Good for simple counters | ✅                                   | ✅                   |
| Increment operation      | `count++` inside synchronized block | `incrementAndGet()` |
| Easy to understand       | ✅                                   | ✅                   |

## 🧠 Key Takeaways

* Multiple threads accessing shared data can cause a **race condition**.
* `count++` is not atomic.
* `join()` waits for threads to finish but **does not prevent race conditions**.
* `synchronized` provides mutual exclusion using a lock.
* `AtomicInteger` provides atomic operations without explicitly using `synchronized`.
* Both approaches can safely produce the expected result of `300000`.

### Important distinction

```text
join()
  ↓
Wait for thread to finish

synchronized
  ↓
Allow only one thread at a time

AtomicInteger
  ↓
Perform integer operations atomically
```
