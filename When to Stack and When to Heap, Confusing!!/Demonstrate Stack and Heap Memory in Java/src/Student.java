public class Student {
    private int age;
    public Student(int age) {
        this.age = age;
        System.out.println("Student object created");
    }
    public int getAge() {
        return age;
    }
    public void display(int marks) {
        System.out.println("Instance variable(age): " + age);
        System.out.println("Local variable(marks): " + marks);
    }
}
