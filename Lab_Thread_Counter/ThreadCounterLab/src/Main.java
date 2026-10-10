public class Main {
    public static void main(String[] args) throws InterruptedException {
        int NumOfThreads = Integer.parseInt(args[0]);
        int increments = Integer.parseInt(args[1]);
        boolean isThreadSafe = Boolean.parseBoolean(args[2]);

        MyThread[] t = new MyThread[NumOfThreads];
        long ExpectedCount = (long) NumOfThreads * increments;

        for(int i = 0; i < NumOfThreads; i++){
            t[i] = new MyThread(increments, isThreadSafe);
            t[i].start();
        }

        for(int i = 0; i < NumOfThreads; i++){
            t[i].join();
        }
        System.out.println("Expected Count: " + ExpectedCount);
        System.out.println("Unsafe Count: " + MyThread.getUnsafeCount());
        System.out.println("Safe Count: " + MyThread.getSafeCount());
        long difference;
        if(isThreadSafe){
            difference = ExpectedCount - MyThread.getSafeCount();
        }
        else{
            difference = ExpectedCount - MyThread.getUnsafeCount();
        }
        double percentage = ((difference / (double)ExpectedCount)) * 100;
        System.out.println("Error Percentage: " + percentage);

    }
}