public class Mythreads extends Thread{
    static int count = 0;
    public void run(){
        for(int i = 0; i <= 100000; i++){
            count++;
        }
    }
}
