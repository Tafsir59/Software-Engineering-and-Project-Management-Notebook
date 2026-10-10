import java.util.concurrent.atomic.AtomicLong;

public class MyThread extends Thread{
    static AtomicLong SafeCount = new AtomicLong(0);
    static long UnsafeCount = 0;
    long increments = 0;
    boolean isThreadSafe;
    public MyThread(int increments, boolean isThreadSafe){
        this.increments = increments;
        this.isThreadSafe = isThreadSafe;
    }
    @Override
    public void run() {
        for(int i = 0; i < increments; i++){
            if(isThreadSafe){
                SafeCount.incrementAndGet();
            }
            else{
                UnsafeCount++;
            }
        }
    }
    public static long getSafeCount(){
        return SafeCount.get();
    }
    public static long getUnsafeCount(){
        return UnsafeCount;
    }
}
