import static java.lang.Double.NaN;

public class BoundingBox {
    double xmin = Double.NaN;
    double ymin = Double.NaN;
    double xmax = Double.NaN;
    double ymax =Double.NaN;
    BoundingBox(){}
    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     * Jeżeli był wcześniej pusty - wówczas ma zawierać wyłącznie ten punkt
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    void addPoint(double x, double y){
        if (this.isEmpty()){
            xmin=x;
            xmax=x;
            ymin=y;
            ymax=y;
        }

        xmin = Math.min(xmin, x);
        ymin = Math.min(ymin, y);
        xmax = Math.max(xmax, x);
        ymax = Math.max(ymax, y);

    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y){
        return x >= xmin && x <= xmax && y >= ymin && y <= ymax;
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb){
        return bb.xmin>= xmin && bb.xmax<=xmax && bb.ymin >= ymin && bb.ymax<=ymax;
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb){
        return !(bb.xmin > this.xmax || bb.xmax < this.xmin || bb.ymin > this.ymax || bb.ymax < this.ymin);

    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * Jeżeli był pusty - po wykonaniu operacji ma być równy bb
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){
        if (bb.isEmpty()) {
            return this;
        }
        if (isEmpty()) {
            xmin = bb.xmin;
            ymin = bb.ymin;
            xmax = bb.xmax;
            ymax = bb.ymax;
        } else {
            xmin = Math.min(xmin, bb.xmin);
            ymin = Math.min(ymin, bb.ymin);
            xmax = Math.max(xmax, bb.xmax);
            ymax = Math.max(ymax, bb.ymax);
        }
        return this;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty(){
        return (Double.isNaN(xmin) && Double.isNaN(xmax) && Double.isNaN(ymin) && Double.isNaN(ymax));
    }

    /**
     * Sprawdza czy
     * 1) typem o jest BoundingBox
     * 2) this jest równy bb
     * @return
     */
    public boolean equals(Object o){ //
      //  if this == o return true; czy mamy ten sam obiekt spraedza, czy sa w tym samym
      //  miejscu w pamieci, wiec jest źle
        //equals sprawdza rownosc, == identycznosc
        if (!(o instanceof BoundingBox)) return false;
        BoundingBox bb = (BoundingBox) o; // rzutowanie
        return xmin == bb.xmin && ymin == bb.ymin && xmax == bb.xmax && ymax == bb.ymax;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX(){
        if (isEmpty()) {
            throw new RuntimeException("BoundingBox is empty");
        }
        return (xmin + xmax) / 2.0;
    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY(){
        if (isEmpty()) {
            throw new RuntimeException("BoundingBox is empty");
        }
        return (ymin + ymax) / 2.0;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległości użyj wzoru haversine
     * (ang. haversine formula)
     *
     * Gotowy kod można znaleźć w Internecie...
     */
    double distanceTo(BoundingBox bbx){
        if (isEmpty() || bbx.isEmpty()) {
            throw
                    new RuntimeException("BoundingBox is empty");
        }
        double lat1=this.getCenterX();
        double lon1= this.getCenterY();

        double lat2= bbx.getCenterX();
        double lon2= bbx.getCenterY();

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    public String toString() {
        return " "+ xmin + " "+ ymin + " " + xmax + " " + ymax;
    }



}

//drzewo chcemy znalezn sasiada
             //                       sztuczny wezel
       /*                     w               w
                         /  | \
                   powiaty
                    gminy
                        miasta */

//latwiej kiedysie nie pokrywaja, a pozniej zaprzeczyc intersect