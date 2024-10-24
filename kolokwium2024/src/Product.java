public class Product {
    String product_code;
    String name;
    double price;
    double vat;
    String unit;

    String category;//- kategoria [> podkategoria[ > podkategoria]]
    String producer;
    double weight; //- waga
    int stock; //- stan magazynu (liczba produktów)
    String delivery;// - czas dostawy
    int active;
    int ean;

    Product(String product_code, String name, double price, String unit, String category, String producer,
            double weight, int stock, String delivery){
        this.ean = 1;
        this.active = 1;
        this.product_code = product_code;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.category = category;
        this.producer = producer;
        this.weight = weight;
        this.stock = stock;
        this.delivery = delivery;
    }

        public String toString() {
        return product_code + " " + name+ " " + String.valueOf(price) + " " +
                String.valueOf(vat) + " " + unit +" "+  category;
    }

}
