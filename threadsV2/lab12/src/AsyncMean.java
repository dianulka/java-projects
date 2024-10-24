import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class AsyncMean {
    static double[] array;

    static void initArray(int size) {
        array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random() * size / (i + 1);
        }
    }

 static class MeanCalcSupplier implements Supplier<Double> {
     private final int start;
     private final int end;
     double mean = 0;
        //...

        MeanCalcSupplier(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public Double get() {
            double mean=0;
            // oblicz średnią
            int numberOfElements = end - start;
            for (int i = start; i < end; i++) {
                mean += array[i];
            }
            mean = mean / numberOfElements;
            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
            return mean;
        }
    }

    public static void asyncMeanv1() {
        int size = 100_000_000;
        initArray(size);
        ExecutorService executor = Executors.newFixedThreadPool(16);
        int n=10;
        int elementsPerThread = size / n;
        // Utwórz listę future
        List<CompletableFuture<Double>> partialResults = new ArrayList<>();
        double t1 = System.nanoTime() / 1e6;
        for(int i=0;i<n;i++){
            int start = i * elementsPerThread;
            int end = (i + 1) * elementsPerThread;
            CompletableFuture<Double> partialMean = CompletableFuture.supplyAsync(
                    new MeanCalcSupplier(start,end),executor);
            partialResults.add(partialMean);
        }
        // zagreguj wyniki
        double t2 = System.nanoTime() / 1e6;
        double mean=0;
        for(var pr:partialResults){
            // wywołaj pr.join() aby odczytać wartość future;
            mean += pr.join();
            // join() zawiesza wątek wołający
        }
        double t3 = System.nanoTime() / 1e6;
        mean /= n;
        System.out.printf(Locale.US, "size = %d  >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                t2 - t1,
                t3 - t1,
                mean);

        executor.shutdown();
    }

    static void asyncMeanv2() throws InterruptedException {
        int size = 100_000_000;
        initArray(size);
        ExecutorService executor = Executors.newFixedThreadPool(16);
        int n=10;

        BlockingQueue<Double> queue = new ArrayBlockingQueue<>(n);
        int elementsPerThread = size/ n;
        double t1 = System.nanoTime() / 1e6;
        for (int i = 0; i < n; i++) {
            int start = i * elementsPerThread;
            int end = (i + 1) * elementsPerThread;
            CompletableFuture.supplyAsync(
                    new MeanCalcSupplier(start,end), executor)
            .thenApply(d -> queue.offer(d));
        }
        double t2 = System.nanoTime() / 1e6;
        double mean=0;
        // w pętli obejmującej n iteracji wywołaj queue.take() i oblicz średnią
        for (int i = 0; i < n; i++){
            mean += queue.take();
        }
        mean/=n;
        double t3 = System.nanoTime() / 1e6;

        System.out.printf(Locale.US, "size = %d  >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                t2 - t1,
                t3 - t1,
                mean);

        executor.shutdown();
    }


}
