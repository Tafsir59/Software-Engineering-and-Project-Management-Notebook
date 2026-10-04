//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Mythreads t1 = new Mythreads();
        Mythreads t2 = new Mythreads();
        Mythreads t3 = new Mythreads();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Expected count = " + 3 * 100000);
        System.out.println("Actual count = " + Mythreads.count);
    }
}