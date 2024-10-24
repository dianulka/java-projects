import java.util.Locale;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

    static void initArray(int size) {
        array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random() * size / (i + 1);
        }
    }

    public static void main(String[] args) {
        initArray(100000000);
    }

    static class MeanCalc extends Thread {
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            // ??? liczymy średnią
            int numberOfElements = end - start;
            for (int i = start; i < end; i++) {
                mean += array[i];
            }
            mean = mean / numberOfElements;

            try {
                results.put(mean);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.printf(Locale.US, "%d-%d mean=%f\n", start, end, mean);
        }
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     *
     * @param cnt - liczba wątków
     */
    static void parallelMean(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[] = new MeanCalc[cnt];

        int elementsPerThread = array.length / cnt;
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        for (int i = 0; i < cnt; i++) {
            int start = i * elementsPerThread;
            int end = (i + 1) * elementsPerThread;
            threads[i] = new MeanCalc(start, end);
        }
        // załóż, że array.length dzieli się przez cnt)
        double t1 = System.nanoTime() / 1e6;
        //uruchom wątki
        for (MeanCalc mc : threads) {
            mc.start();
        }
        double t2 = System.nanoTime() / 1e6;
        // czekaj na ich zakończenie używając metody ''join''
        for (MeanCalc mc : threads) {
            mc.join();
        }
        double mean = 0;
        // oblicz średnią ze średnich
        for (MeanCalc mc : threads) {
            mean += mc.mean;
        }
        mean = mean / cnt;
        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2 - t1,
                t3 - t1,
                mean);
    }

    static void parallelMean_2(int cnt) throws InterruptedException {

        // utwórz tablicę wątków
        MeanCalc threads[] = new MeanCalc[cnt];

        int elementsPerThread = array.length / cnt;
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        for (int i = 0; i < cnt; i++) {
            int start = i * elementsPerThread;
            int end = (i + 1) * elementsPerThread;
            threads[i] = new MeanCalc(start, end);
        }
        // załóż, że array.length dzieli się przez cnt)
        double t1 = System.nanoTime() / 1e6;
        //uruchom wątki
        for (MeanCalc mc : threads) {
            mc.start();
        }
        double t2 = System.nanoTime() / 1e6;


        double mean = 0;
        for (int i = 0; i < cnt; i++) {
            try {
                mean += results.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mean /= cnt;

        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2 - t1,
                t3 - t1,
                mean);
    }

    static void parallelMean_3(int cnt) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(cnt);

        double t1 = System.nanoTime() / 1e6;
        int elementsPerThread = array.length / cnt;
        double mean = 0;

        for (int i = 0; i < cnt; i++) {
            int start = i * elementsPerThread;
            int end = (i + 1) * elementsPerThread;
            executor.execute(new MeanCalc(start, end));
        }

        executor.shutdown();
        //executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // Wait for all tasks to finish

        double t2 = System.nanoTime() / 1e6;

        for (int i = 0; i < cnt; i++) {
            try {
                mean += results.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        mean /= cnt;

        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2 - t1,
                t3 - t1,
                mean);


    }





}







//shutdown na koncu metody




