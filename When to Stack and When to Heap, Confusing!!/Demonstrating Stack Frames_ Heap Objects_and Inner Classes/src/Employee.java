public class Employee {
    private int salary;
    public Employee(int salary) {
        this.salary = salary;
        System.out.println("Employee Object Created");
    }
    public void display(int bonus) {
        System.out.println("Inside display() Method:");
        System.out.println("Salary (Instance Variable): " + salary);
        System.out.println("Bonus (Local Variable): " + bonus);
        class Report {
            public void show() {
                System.out.println("Report: Employee salary is " + salary);
            }
        }
        Report r = new Report();
        System.out.println("Report Identity Hash Code: " + System.identityHashCode(r));
    }
}
