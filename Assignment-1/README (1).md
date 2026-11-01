# Java Assignment: Tasks 1, 2, 3 and 4

**Student ID:** it24059
**Date:** November 1, 2026

This repository contains four tasks:

| Task | Topic | Technology |
|------|-------|------------|
| Task 1 | Install IntelliJ IDEA | IntelliJ IDEA, JDK |
| Task 2 | Find the nth prime and store it in a database | Java, JDBC, PostgreSQL |
| Task 3 | "Hello World" web application | Java Servlet, Tomcat |
| Task 4 | Primality checker web page | Java Servlet, Tomcat |

---

## Task 1: Installing IntelliJ IDEA

### Description
IntelliJ IDEA is the IDE (Integrated Development Environment) from JetBrains used to write, run and debug the Java programs in this repository. These steps cover installing it on Windows 10/11.

### System Requirements
- Windows 10 or Windows 11 (64-bit)
- CPU with 4 cores (x86_64 or ARM64)
- 8 GB of RAM recommended
- Internet connection for the download

> IntelliJ IDEA has its own bundled runtime, so you do not need Java installed just to run the IDE. You do need a JDK to develop Java programs (this project uses JDK 21).

### Installation Steps
1. **Download the installer.** Go to the official download page, `https://www.jetbrains.com/idea/download/`, and download the Windows `.exe` installer. Choose the ARM64 installer if your PC uses an ARM processor.
2. **Run the installer.** Double-click the downloaded `.exe` file and click **Next** in the setup wizard.
3. **Choose the install location.** Keep the default folder or select your own, then click **Next**.
4. **Select installation options.** Recommended choices:
   - Create a **desktop shortcut**
   - Add the **command-line launchers to PATH**
   - Add **Open Folder as Project** to the right-click menu
5. **Install.** Click **Install** and wait for the process to finish, then click **Finish** (restart if prompted).
6. **Launch IntelliJ IDEA.** Open it from the Start menu or the desktop shortcut, then accept the user agreement and choose your theme and settings.

> **Alternative:** You can install the **JetBrains Toolbox App** instead, then click **Install** next to IntelliJ IDEA. Toolbox makes it easy to update the IDE later.

### Setting Up Java (JDK)
1. Click **New Project** on the welcome screen.
2. Choose **Java** as the project type.
3. In the **JDK** dropdown, select an installed JDK, or choose **Download JDK** and pick version 21 (the version used in this assignment).
4. Name the project and click **Create**.

### Verify the Installation
1. Create a new class named `Main` with this code:
   ```java
   public class Main {
       public static void main(String[] args) {
           System.out.println("Hello, IntelliJ!");
       }
   }
   ```
2. Click the green **Run** button next to `main`.
3. The output `Hello, IntelliJ!` appears in the Run window, which confirms the setup works.

---

## Task 2: Nth Prime Number Stored in PostgreSQL

### Description
A console program that reads a positive integer `n`, finds the **nth prime number**, prints it, and inserts it into a PostgreSQL table using JDBC.

For example, `n = 10` gives `29` (the primes are 2, 3, 5, 7, 11, 13, 17, 19, 23, 29).

### Requirements
- JDK 21
- PostgreSQL running on `localhost:5432`
- PostgreSQL JDBC driver added to the project's classpath

### Database Setup
```sql
CREATE DATABASE prime_db;

\c prime_db

CREATE TABLE primes (
    id SERIAL PRIMARY KEY,
    prime_number INT NOT NULL
);
```

### Code (`Main.java`)
```java
import java.sql.*;
import java.util.Scanner;

public class Main {

    static boolean isPrime(int num) {

        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Enter a positive number!");
            return;
        }

        int count = 0;
        int num = 1;
        int nthPrime = 0;

        // Find nth prime number
        while (count < n) {

            num++;

            if (isPrime(num)) {
                count++;
                nthPrime = num;
            }
        }

        System.out.println("Nth Prime Number: " + nthPrime);

        String url = "jdbc:postgresql://localhost:5432/prime_db";
        String user = "postgres";
        String password = "admin123";

        try {

            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);

            // SQL query
            String sql = "INSERT INTO primes (prime_number) VALUES (?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, nthPrime);

            pst.executeUpdate();

            System.out.println("Prime number stored successfully!");

            pst.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
```

### Code Explanation

**Imports**
- `java.sql.*` provides the JDBC classes (`Connection`, `DriverManager`, `PreparedStatement`, `SQLException`) used to talk to the database.
- `java.util.Scanner` reads the user's input from the console.

**`isPrime(int num)`**
- Returns `false` for any number below 2, since 0 and 1 are not prime.
- Tests divisors from 2 up to the square root of `num` (the loop runs while `i * i <= num`). If any divisor divides `num` evenly, it is not prime.
- Returns `true` if no divisor is found. Stopping at the square root makes the check much faster than testing every number up to `num`.

**`main` method: reading input**
- Creates a `Scanner`, prompts with `Enter n: `, and reads `n`.
- If `n <= 0`, it prints `Enter a positive number!` and ends the program with `return`.

**`main` method: finding the nth prime**
- `count` tracks how many primes have been found, `num` is the number currently being tested (starting at 1), and `nthPrime` holds the latest prime found.
- The `while (count < n)` loop increases `num` by 1 each pass. When `isPrime(num)` is true, `count` is incremented and `nthPrime` is updated.
- When `count` reaches `n`, the loop stops and `nthPrime` holds the nth prime, which is printed.

**`main` method: storing the result**
- `url`, `user` and `password` define the PostgreSQL connection to the `prime_db` database on `localhost:5432`.
- `DriverManager.getConnection(...)` opens the connection.
- A `PreparedStatement` runs `INSERT INTO primes (prime_number) VALUES (?)`. The `?` placeholder is filled with `pst.setInt(1, nthPrime)`, which is safe against SQL injection.
- `executeUpdate()` runs the insert, and the program prints `Prime number stored successfully!`.
- `pst.close()` and `con.close()` release the database resources.
- The `catch (SQLException e)` block prints the error message if the connection or insert fails (for example, wrong password or a missing table).
- Finally, `sc.close()` closes the scanner.

### Sample Output
```
Enter n: 10
Nth Prime Number: 29
Prime number stored successfully!
```
![alt text](image.png)
---

## Task 3: Hello World Servlet

### Description
A basic Java servlet (`HelloServlet`) mapped to `/hello` using `@WebServlet`. It responds to a GET request with an HTML page showing **Hello World!** and **My First Java Servlet**.

### Requirements
- JDK 21
- Apache Tomcat 10 or later (uses the `jakarta.servlet` packages)
- An IDE with Tomcat support (e.g. IntelliJ IDEA) or Maven/Gradle

### Code (`HelloServlet.java`)
```java
package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hello World!</h1>");
        out.println("<h2>My First Java Servlet</h2>");
        out.println("</body>");
        out.println("</html>");
    }
}
```

### Code Explanation
- **`package com.example;`** places the class in the `com.example` package.
- **Imports** bring in the Jakarta Servlet API classes: `HttpServlet` (the base class), `HttpServletRequest` and `HttpServletResponse` (the incoming request and outgoing response), `ServletException`, and the `@WebServlet` annotation. `PrintWriter` and `IOException` come from `java.io`.
- **`@WebServlet("/hello")`** registers the servlet at the URL path `/hello`, so no `web.xml` entry is needed.
- **`extends HttpServlet`** makes the class a servlet that can handle HTTP requests.
- **`doGet(...)`** runs whenever the browser sends a GET request to `/hello`.
- **`response.setContentType("text/html")`** tells the browser the response is an HTML page.
- **`response.getWriter()`** returns a `PrintWriter` used to write the response body.
- **The `out.println(...)` lines** write a simple HTML document with an `<h1>` heading ("Hello World!") and an `<h2>` subheading ("My First Java Servlet").

### How to Run
1. Open the project and configure a Tomcat server.
2. Deploy the artifact `Task3_war_exploded`.
3. Start the server and open:
   - `http://localhost:8080/Task3_war_exploded/` for the application's start page
   - `http://localhost:8080/Task3_war_exploded/hello` for the servlet

### Screenshot
The deployed application shown at `localhost:8080/Task3_war_exploded/`:

![Task 3 output](Screenshot_2026-11-01_113806.png)

---

## Task 4: Primality Checker Servlet

### Description
An extended version of `HelloServlet` that serves a form where the user enters a number and the servlet reports whether it is prime.

- **GET `/hello`** shows the empty form.
- **POST `/hello`** reads the `number` parameter, checks primality, and redisplays the form with the answer.

### Code (`HelloServlet.java`)
```java
package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        showPage(request, response, "", "");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String input = request.getParameter("number");
        String numberValue = "";
        String result;

        try {
            long num = Long.parseLong(input.trim());
            numberValue = String.valueOf(num);

            boolean prime = true;
            if (num < 2) {
                prime = false;
            } else {
                for (long i = 2; i * i <= num; i++) {
                    if (num % i == 0) {
                        prime = false;
                        break;
                    }
                }
            }
            result = prime ? "Prime Number" : "Not a Prime Number";

        } catch (Exception e) {
            result = "Invalid input";
        }

        showPage(request, response, numberValue, result);
    }

    private void showPage(HttpServletRequest request,
                          HttpServletResponse response,
                          String numberValue,
                          String result) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println("<h2>Primality Checker</h2>");

        out.println("<form method='post' action='" + request.getContextPath() + "/hello'>");

        out.println("Enter a number: ");
        out.println("<input type='number' name='number' value='" + numberValue + "' required>");
        out.println("<br><br>");

        out.println("<input type='submit' value='Primality Check'>");
        out.println("<br><br>");

        out.println("Answer: ");
        out.println("<input type='text' value='" + result + "' readonly>");

        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
```

### Code Explanation

**Class setup**
- Same package, imports and `@WebServlet("/hello")` mapping as Task 3, so the servlet is reached at `/hello`.

**`doGet(...)`**
- Runs when the page is first opened in the browser.
- Calls `showPage(request, response, "", "")`, which displays the form with an empty number field and an empty answer box.

**`doPost(...)`**
- Runs when the user submits the form.
- `request.getParameter("number")` reads the value typed into the form.
- Inside a `try` block, `Long.parseLong(input.trim())` converts the text to a number. `numberValue` stores it so it stays in the input box after submitting.
- A `boolean prime` starts as `true`:
  - If `num < 2`, it is set to `false` (0, 1 and negatives are not prime).
  - Otherwise, a loop tests divisors from 2 while `i * i <= num`. If one divides `num` evenly, `prime` becomes `false` and the loop ends with `break`.
- `result` is set to `"Prime Number"` or `"Not a Prime Number"` using a ternary operator.
- If the input is empty or not a valid number, parsing throws an exception, and the `catch` block sets `result` to `"Invalid input"`.
- Finally it calls `showPage(...)` with the number and the result.

**`showPage(...)`**
- A helper method shared by `doGet` and `doPost`, so the HTML is written in one place.
- Sets the content type to HTML with UTF-8 encoding and gets the `PrintWriter`.
- Writes a heading, "Primality Checker", and a form that uses `method='post'` and posts to `request.getContextPath() + "/hello"`, so it works under any deployed app name.
- The form contains a number input named `number` (marked `required`, pre-filled with `numberValue`), a submit button labelled "Primality Check", and a read-only text box that displays the answer.

### Results
| Input | Answer |
|-------|--------|
| A prime (e.g. 7) | `Prime Number` |
| A non-prime or a number below 2 (e.g. 8, 1) | `Not a Prime Number` |
| Non-numeric or empty input | `Invalid input` |

### How to Run
1. Deploy the project to Tomcat 10 or later, as in Task 3.
2. Open `http://localhost:8080/<your-artifact-name>/hello`.
3. Enter a number and click **Primality Check**.

### Screenshot
Add a screenshot of the Task 4 page here:

![Task 4 output](task4_screenshot.png)

---

## Author
it24059
