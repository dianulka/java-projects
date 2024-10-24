import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PrimeCheckerExample {
    static class PrimeChecker extends Thread {
        final BlockingQueue<Integer> input;
        final BlockingQueue<Integer> output;


        public PrimeChecker(BlockingQueue<Integer> input, BlockingQueue<Integer> output) {
            this.input = input;
            this.output = output;
        }

        boolean isPrime(int n) {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        }


        @Override
        public void run() {

            while (true) {
                try {
                    int n = input.take();
                    if (n == -1) {
                        break;
                    }
                    if (isPrime(n)) {
                        output.put(n);
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public static void parallelPrimes(int range, int cnt) throws InterruptedException {
        int outputt = (int) ((int) range * 0.1);
        BlockingQueue<Integer> input = new LinkedBlockingQueue<>(range);
        BlockingQueue<Integer> output = new LinkedBlockingQueue<>(outputt);

        PrimeChecker[] primeCheckers = new PrimeChecker[cnt];
        for (int i = 0; i < cnt; i++) {
            primeCheckers[i] = new PrimeChecker(input, output);
            primeCheckers[i].start();
        }

        for (int i = 2; i < range; i++) {
            try {
                input.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        input.put(-1);
        try {
            while (cnt > 0) {
                int pierwsza = output.take();
                System.out.println("liczba pierwsza " + pierwsza);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (PrimeChecker primeChecker : primeCheckers) {
            try {
                primeChecker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        parallelPrimes(100,5);


    }
}

