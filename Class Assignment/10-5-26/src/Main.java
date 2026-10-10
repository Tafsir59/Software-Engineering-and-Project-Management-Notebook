//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThreads t1 = new MyThreads();
        MyThreads t2 = new MyThreads();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(t1.c1 + " "+ t1.c2);
        System.out.println(t2.c1 + " "+ t2.c2);
        System.out.println(t1.c1/t1.c2*100);
        System.out.println(t2.c1 /t2.c2*100);
    }
}