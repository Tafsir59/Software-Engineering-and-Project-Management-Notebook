public class MyThreads extends Thread{
    public static double c1 = 0;
    public double c2 = 0;

    public void run(){
        long start_time = System.currentTimeMillis();
        for(;;){
            c1++;
            c2++;
            if(System.currentTimeMillis()-start_time > 15000){
                break;
            }
        }
    }
}
