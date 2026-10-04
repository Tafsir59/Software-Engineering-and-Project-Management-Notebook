public class M1 extends Thread{
    static int count = 0;
    public void run(){
        for(int i = 0; i < 100000; i++){
            increment();
            }
        }
    static synchronized void increment(){
        count++;
    }
    }
