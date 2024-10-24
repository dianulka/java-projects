import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadExample {
    static int count = 0;
    static AtomicInteger count2=new AtomicInteger(0);
    static Semaphore sem = new Semaphore(0);
    // lista plików do pobrania
    static String [] toDownload = {
            "https://home.agh.edu.pl/~pszwed/wyklad-c/01-jezyk-c-intro.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/02-jezyk-c-podstawy-skladni.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/03-jezyk-c-instrukcje.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/04-jezyk-c-funkcje.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/05-jezyk-c-deklaracje-typy.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/06-jezyk-c-wskazniki.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/07-jezyk-c-operatory.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/08-jezyk-c-lancuchy-znakow.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/09-jezyk-c-struktura-programow.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/10-jezyk-c-dynamiczna-alokacja-pamieci.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/11-jezyk-c-biblioteka-we-wy.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/preprocesor-make-funkcje-biblioteczne.pdf",
    };
    static class Downloader implements Runnable{
        private final String url;

        Downloader(String url){
            this.url = url;

        }


        public void run() {

            String fileName;
            try {
                fileName = Paths.get(new URL(url).getPath()).getFileName().toString();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            try (InputStream in = new URL(url).openStream();
                 FileOutputStream out = new FileOutputStream(fileName)) {
                    int bytesRead;
                    byte[] buffor = new byte[1000];
                    while ((bytesRead = in.read(buffor))>0) {
                        out.write(buffor, 0, bytesRead);
                    }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done:" + fileName);
        }
        static void sequentialDownload(){
            double t1 = System.nanoTime()/1e6;
            for(String url:toDownload){
                System.out.println("start");
                new Downloader(url).run();  // uwaga tu jest run()
                count++;
                System.out.println("count:" + count);
            }
            double t2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);

        }

        static void concurrentDownload(){
            double t1 = System.nanoTime()/1e6;

            for(String url:toDownload){
                // uruchom Downloader jako wątek... czyli wywołaj start()
                new Thread(new Downloader(url)).start();
                System.out.println("count:" + count);
                count++;

            }
            double t2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
        }

        static void concurrentDownload2(){
            double t1 = System.nanoTime()/1e6;

            for(String url:toDownload) {
                // uruchom Downloader jako wątek... czyli wywołaj start()
                new Thread(new Downloader(url)).start();
                System.out.println("count:" + count);
                ++count;

            }

            while(count!=toDownload.length){
                    // wait...
                    Thread.yield();
            }
            double t2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);

        }

        static void concurrentDownload2_5(){
            double t1 = System.nanoTime()/1e6;

            for(String url:toDownload) {
                // uruchom Downloader jako wątek... czyli wywołaj start()
                new Thread(new Downloader(url)).start();
                System.out.println("count:" + count2);
                count2.getAndIncrement();

            }

            while(count2.get()!=toDownload.length){
                // wait...
                Thread.yield();
            }
            double t2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);

        }

        static void concurrentDownload3() throws InterruptedException {
            double t1 = System.nanoTime()/1e6;

            for(String url:toDownload) {
                // uruchom Downloader jako wątek... czyli wywołaj start()
                new Thread(new Downloader(url)).start();
                System.out.println("co :" + sem.toString());
               sem.release();

            }

            sem.acquire(toDownload.length);


            double t2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);

        }

        public static void main(String[] args) throws InterruptedException {
           sequentialDownload();
           concurrentDownload3();

        }

    }



}
