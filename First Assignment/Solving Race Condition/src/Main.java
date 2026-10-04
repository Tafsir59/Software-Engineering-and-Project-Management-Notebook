//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException{
        M1 t1 = new M1();
        M1 t2 = new M1();
        M1 t3 = new M1();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Expected count = " + 3 * 100000);
        System.out.println("Actual count = " + M1.count);

        M2 t4 = new M2();
        M2 t5 = new M2();
        M2 t6 = new M2();

        t4.start();
        t5.start();
        t6.start();

        t4.join();
        t5.join();
        t6.join();

        System.out.println("Expected count = " + 3 * 100000);
        System.out.println("Actual count = " + M2.count.get());
    }
}