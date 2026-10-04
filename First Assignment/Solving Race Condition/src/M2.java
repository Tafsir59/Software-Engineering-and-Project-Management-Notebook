import java.util.concurrent.atomic.AtomicInteger;
public class M2 extends Thread{
    static AtomicInteger count = new AtomicInteger(0);
    public void run(){
        for(int i = 0; i < 100000; i++){
            count.incrementAndGet();
        }
    }
}
