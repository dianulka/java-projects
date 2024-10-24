import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    // id na poziomie odwzorowania potrzebne,
    // na poziomie progromaoqwania obiektowego nie


    // referencja do parent
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox;

    List<AdminUnit> children;

    public String toString() {
        return name + " " + Integer.toString(adminLevel) + " " + String.valueOf(Math.round(population))
                + " " + String.valueOf(area) + " " + String.valueOf(density) +
                bbox.toString();
    }


    public StringBuilder toString2(){
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append(name);
        if(this.children.isEmpty())
            return stringBuilder;
        for (AdminUnit child: children){
            stringBuilder.append(child.name);
        }
        return stringBuilder;
    }

    AdminUnit(String name, int adminLevel, double population, double area, double density,
              double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {

        this.bbox = new BoundingBox();
        bbox.addPoint(x1,y1);
        bbox.addPoint(x2,y2);
        bbox.addPoint(x3,y3);
        bbox.addPoint(x4,y4);
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;

        this.children = new ArrayList<>();


    }

    public void fixMissingValues() {
       if (Double.isNaN(population) && Double.isNaN(density) && this.parent != null) {
            this.parent.fixMissingValues();
            this.density = this.parent.density;
            this.population = this.area * this.density;
        }

    }





}

