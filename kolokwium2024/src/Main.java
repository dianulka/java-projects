import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.function.Predicate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {


        ProductList lista = new ProductList();
        lista.read("super-toys.csv");
        //lista.list(System.out);

        System.out.println("najdrozszy:");
        //b) Znajdź najdroższy produkt z kategorii zawierającej słowo "Wózki" i wypisz jego dane
        lista
            .filter(a->(a.category.contains("Wózki")))
            .sortInplace((p1,p2)->(-1) * Double.compare(p1.price,p2.price))
            .list(System.out, 0,1);

        //Wypisz informacje o wszystkich zabawkach, które mają w nazwie słowo "mercedes"
         //(niezależnie od wielkości liter) posortowanych według ceny, a następnie liczby dostępnych produktów
        lista
                .filter(a -> a.name.toLowerCase().contains("mercedes"))
                .sortInplace((p1,p2)-> Double.compare(p1.price,p2.price))
                .sortInplace((p1,p2)->Integer.compare(p1.stock,p2.stock))
                .list(System.out);
        //Oblicz średnią cenę zabawek z kategorii zawierającej słowo "Keyboardy" ale nie podkategorii "Akcesoria"
        double cena = lista
                .filter(a->((a.category.contains("Keyboard")) && (!a.category.contains("Akcesoria"))))
                .ObliczCene();

        System.out.println(cena);

        System.out.println(lista.wartoscNetto());
        //f) Wypisz produkty, kt(órych pole product_code zawiera słowo 'BIAŁY' lub 'CZERWONY' o wadze mniejszej niż 30  posortowane według ceny

        lista
                .filter(a -> a.product_code.contains("BIAŁY") || a.product_code.contains("CZERWONY"))
                .filter(a->(a.weight<30))
                .sortInplace((p1,p2)-> Double.compare(p1.price,p2.price))
                .list(System.out);

    }
}























