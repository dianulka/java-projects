import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RunnableOrThread extends Thread {
    int i;

    RunnableOrThread(int i) {
        this.i = i;
    }

    public void run() {
        System.out.println("My number is " + i);
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 100; i++) {
            executor.execute(new RunnableOrThread(i));
           /* executor.execute(()->{
                System.out.println("My number is "+i);
            });*/
        }
        executor.shutdown();
    }
}
