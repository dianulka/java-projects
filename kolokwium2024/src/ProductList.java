import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ProductList {
    List<Product> produkty = new ArrayList<>();

    void list(PrintStream out) {
        for (Product pr : produkty) {
            out.println(pr.toString());
       }

    }

    void list(PrintStream out, int offset, int limit) {
        for (int i = offset; i < offset + limit + 1; i++) {
            out.println(produkty.get(i).toString());
        }

    }
    public void read(String filename) throws IOException, IOException {
        CSVReader reader = new CSVReader(filename, ";", true);
        Product product = null;

        while (reader.next()) {
            String product_code = null;
            String name = null;
            double price = Double.NaN;
            double vat = Double.NaN;
            String unit = null;
            String category = null;//- kategoria [> podkategoria[ > podkategoria]]
            String producer = null;
            double weight = Double.NaN;
            int stock = -1; //- stan magazynu (liczba produktów)
            String delivery = null;// - czas dostawy;

            if (!reader.isMissing("product_code")) {
                product_code = reader.get("product_code");
            }
            if (!reader.isMissing("name")) {
                name = reader.get("name");
            }
            if (!reader.isMissing("price")) {
                price = reader.getDouble("price");
            }
            if (!reader.isMissing("vat")) {
                vat = reader.getDouble("vat");
            }
            if (!reader.isMissing("unit")) {
                unit = reader.get("unit");
            }
            if (!reader.isMissing("category")) {
                category = reader.get("category");
            }
            if (!reader.isMissing("producer")) {
                producer = reader.get("producer");
            }
            if (!reader.isMissing("weight")) {
                weight = reader.getDouble("weight");
            }
            if (!reader.isMissing("stock")) {
                stock = reader.getInt("stock");
            }
            if (!reader.isMissing("delivery")) {
                delivery = reader.get("delivery");
            }
            product = new Product(product_code, name, price, unit, category, producer, weight, stock, delivery);
            produkty.add(product);
        }
    }
       public ProductList filter(Predicate<Product> pred) {
           ProductList filteredList = new ProductList();

        for (Product pr : produkty) {
            if (pred.test(pr)) {
                filteredList.produkty.add(pr);
            }
        }

        return filteredList;
    }

    public ProductList sortInplace(Comparator<Product> cmp) {
        produkty.sort(cmp);
        return this;
    }

    public double ObliczCene(){
        double cenaOstatecznie = 0;
        for (Product pr: this.produkty){
            cenaOstatecznie+= pr.price;
        }
        cenaOstatecznie = cenaOstatecznie/produkty.size();
        return cenaOstatecznie;
    }

    //e) Oblicz sumaryczną wartość netto towarów w magazynie
    public double wartoscNetto(){
        double wartoscNetto = 0;
        for (Product pr: this.produkty) {
            if (pr.stock > 0) {
                wartoscNetto += pr.price + pr.price * pr.vat;
            }
        }
        return wartoscNetto;
    }

}
