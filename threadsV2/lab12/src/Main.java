// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
      //  Mean m = new Mean();

            Mean.initArray(128000000);
            for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
                Mean.parallelMean_3(cnt);

        }

        //AsyncMean.asyncMeanv2();


    }
}


//kolejka metoda put wstawia el do kolejki, offer nie blokuje kolejki jezeli sie nie uda zwraca false, take pobiera i usuwa
//el z klejki