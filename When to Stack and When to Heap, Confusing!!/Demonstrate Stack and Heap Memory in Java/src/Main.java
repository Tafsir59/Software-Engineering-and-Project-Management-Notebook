import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Age: ");
        int age = sc.nextInt();
        System.out.print("Marks: ");
        int marks = sc.nextInt();
        Student s = new Student(age);
        System.out.println("Object Identity Hash Code: " + System.identityHashCode(s));
        s.display(marks);
    }
}