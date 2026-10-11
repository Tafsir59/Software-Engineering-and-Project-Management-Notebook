import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Salary: ");
        int salary = sc.nextInt();
        System.out.print("Enter Bonus: ");
        int bonus = sc.nextInt();
        Employee e = new Employee(salary);
        System.out.println("Employee Identity Hash Code: " + System.identityHashCode(e));
        e.display(bonus);

        System.out.println("\nMemory Concept:");
        System.out.println("Employee object -> Heap");
        System.out.println("Report object -> Heap");
        System.out.println("salary -> Instance field");
        System.out.println("bonus -> Local variable in display()");
        System.out.println("show() -> Executes in its own method frame");
    }
}