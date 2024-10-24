import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static java.lang.System.out;
import static java.lang.System.setOut;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        AdminUnitList lista = new AdminUnitList();
        lista.read("admin-units.csv");
        lista.fixMissingValues();
        AdminUnit jednostka= lista.selectByName("Wiśniowa Poduchowna",false).units.get(0);

        executeAndListQuery4(lista);


/*
        executeAndListQuery3(lista);
        executeAndListQuery2(lista);
        executeAndListQuery(lista);
        getNeighborsAndMeasureTimeTree(lista, jednostka, 15);

        /query1(lista);

        query2(lista);

        query3(lista);

        query4(lista);*/
    }
    private static void getNeighborsAndMeasureTimeTree(AdminUnitList lista, AdminUnit unit, double maxDistance) {
        double t1 = System.nanoTime() / 1e6;
        lista.getNeighborsTree(unit, maxDistance).list(System.out);
        double t2 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "Czas funkcji: t2-t1=%f\n", t2 - t1);
    }
        private static void query1(AdminUnitList lista) {
            lista
                    .filter(a -> a.name.startsWith("Ż"))
                    .sortInplaceByArea()
                    .list(System.out);
        }

        private static void query2(AdminUnitList lista) {
            lista
                    .filter(a -> a.name.startsWith("K"))
                    .sort((unit1, unit2) -> Integer.compare(unit1.adminLevel, unit2.adminLevel))
                    .sortInplaceByArea()
                    .list(System.out);
        }

        private static void query3(AdminUnitList lista) {
            lista
                    .filter(a -> a.adminLevel == 6)
                    .filter(a -> a.parent.name.equals("województwo małopolskie"))
                    .list(System.out);
        }

        private static void query4(AdminUnitList lista) {
            lista
                    .filter(a -> a.adminLevel == 7)
                    .filter(a -> a.parent.parent.name.equals("województwo świętokrzyskie"))
                    .sortInplaceByName()
                    .sortInplaceByPopulation()
                    .list(System.out);
        }

    private static void executeAndListQuery(AdminUnitList lista) {
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(lista)
                .where(a -> a.area < 1000)
                .or(a -> a.name.startsWith("Sz"))
                .sort((a, b) -> Double.compare(a.area, b.area))
                .limit(100);

        query.execute().list(System.out);
    }

    private static void executeAndListQuery2(AdminUnitList lista) {
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(lista)
                .where(a -> a.population > 1000)
                .and(a -> a.name.startsWith("K"))
                .sort((a, b) -> Double.compare(a.population, b.population))
                .sort((a,b)->String.CASE_INSENSITIVE_ORDER.compare(a.name, b.name))
                .limit(100);

        query.execute().list(System.out);
    }

    private static void executeAndListQuery3(AdminUnitList lista) {
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(lista)
                .where(a -> a.population < 1000000)
                .and(a -> a.name.startsWith("w"))
                .or(a-> a.area>300)
                .sort((a, b) -> Double.compare(a.population, b.population))
                .limit(3);

        query.execute().list(System.out);
    }

    private static void executeAndListQuery4(AdminUnitList lista) {
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(lista)
                .where(a -> a.parent==null)
                .and(a->a.children.size()>25)
                .and(a-> a.area>3000)
                .and(a-> a.population>1500000)
                .sort((a,b)->String.CASE_INSENSITIVE_ORDER.compare(a.name, b.name))
                .limit(16);

        query.execute().list(System.out);

    }


    }


