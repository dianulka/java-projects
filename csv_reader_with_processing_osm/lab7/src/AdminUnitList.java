import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;


public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    //tylko czytac mapa ma nie byc atrybutem

    /**
     * Wypisuje zawartość korzystając z AdminUnit.toString()
     *
     * @param out
     */
    void list(PrintStream out) {
        for (AdminUnit unit : units) {
            out.println(unit.toString());
        }

    }

    void list2(PrintStream out) {
        for (AdminUnit unit : units) {
            out.println(unit.toString2());
        }
    }

    /**
     * Wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
     *
     * @param out    - strumień wyjsciowy
     * @param offset - od którego elementu rozpocząć wypisywanie
     * @param limit  - ile (maksymalnie) elementów wypisać
     */
    void list(PrintStream out, int offset, int limit) {
        for (int i = offset; i < offset + limit + 1; i++) {
            out.println(units.get(i).toString());
        }

    }

    /**
     * Czyta rekordy pliku i dodaje do listy
     *
     * @param filename nazwa pliku
     */

    // id,parent,name,admin_level,population,area,density,x1,y1,x2,y2,x3,y3,x4,y4
    //10465,10464,Bębło,8,,5.74514,,19.7630452162734,50.1686080490355,19.7630452162734,50.1974469404449,
    // 19.8131578242296,50.1974469404449,19.8131578242296,50.1686080490355,19.7630452162734,50.1686080490355
    public void read(String filename) throws IllegalArgumentException, IOException {
        Map<Long, AdminUnit> idToAdminUnitMap = new HashMap<>();
        Map<AdminUnit, Long> adminUnitToParentIdMap = new HashMap<>();


        CSVReader reader = new CSVReader(filename, ",", true);
        AdminUnit unit = null;
        Map<Long, List<AdminUnit>> parentid2child = new HashMap<>();
        // Map<Long,List<Long>> parentid2childid = new HashMap<>();


        while (reader.next()) {
            List<Long> childIds = new ArrayList<>();
            int adminLevel = -1;
            long id;
            long parent = -1L;
            String name;
            double population = Double.NaN, area = Double.NaN, density = Double.NaN, x1 = Double.NaN, y1 = Double.NaN,
                    x2 = Double.NaN, y2 = Double.NaN, x3 = Double.NaN, y3 = Double.NaN, x4 = Double.NaN, y4 = Double.NaN;

            id = reader.getLong("id");
            //parent = reader.getLong("parent");
            if (!reader.isMissing("parent")) {
                parent = reader.getLong("parent");
            }

            name = reader.get("name");
            if (!reader.isMissing("admin_level")) {
                adminLevel = reader.getInt("admin_level");
            }


            if (!reader.isMissing("population")) {
                population = reader.getDouble("population");
            }
            if (!reader.isMissing("area")) {
                area = reader.getDouble("area");
            }

            if (!reader.isMissing("density")) {
                density = reader.getDouble("density");
            }

            if (!reader.isMissing("x1")) {
                x1 = reader.getDouble("x1");
            }

            if (!reader.isMissing("y1")) {
                y1 = reader.getDouble("y1");
            }

            if (!reader.isMissing("x2")) {
                x2 = reader.getDouble("x2");
            }

            if (!reader.isMissing("y2")) {
                y2 = reader.getDouble("y2");
                ;
            }

            if (!reader.isMissing("x3")) {
                x3 = reader.getDouble("x3");
            }

            if (!reader.isMissing("y3")) {
                y3 = reader.getDouble("y3");
            }

            if (!reader.isMissing("x4")) {
                x4 = reader.getDouble("x4");
            }
            if (!reader.isMissing("y4")) {
                y4 = reader.getDouble("y4");
            }


            unit = new AdminUnit(name, adminLevel, population, area, density, x1, y1, x2, y2, x3, y3, x4, y4);
            idToAdminUnitMap.put(id, unit);
            adminUnitToParentIdMap.put(unit, parent);
            units.add(unit);

           // parentid2child.computeIfAbsent(parent, k -> new ArrayList<>()).add(unit);


        }

        for (AdminUnit unitCurrent : units) {
            long parentId = adminUnitToParentIdMap.getOrDefault(unitCurrent, -1L);
            unitCurrent.parent = idToAdminUnitMap.getOrDefault(parentId, null);

            List<AdminUnit> childrenList = parentid2child.computeIfAbsent(parentId, k -> new ArrayList<>());

            if (unitCurrent.parent != null) {
                unitCurrent.parent.children = childrenList;
                unitCurrent.parent.children.add(unitCurrent);
            }
        }


    }

    /**
     * Zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
     *
     * @param pattern - wzorzec dla nazwy
     * @param regex   - jeśli regex=true, użyj finkcji String matches(); jeśli false użyj funkcji contains()
     * @return podzbiór elementów, których nazwy spełniają kryterium wyboru
     */
    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();
        // przeiteruj po zawartości units
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret
        if (regex) {
            for (AdminUnit unit : units) {
                if (unit.name.matches(pattern)) {
                    ret.units.add(unit);
                }
            }
        } else {
            for (AdminUnit unit : units) {
                if (unit.name.contains(pattern)) {
                    ret.units.add(unit);
                }
            }
        }
        return ret;
    }
    //Projekt interfejsu w stylu lista zwraca listę obiektów spełniających kryteria będziemy dalej rozwijać.
    // To jest całkiem wydajne. Lista jest tablicą referencji (8-bajtowych wartości).
    // Obiekty nie są kopiowane, więc listy nie zużywają dużo pamięci w porównaniu do obecnie dostępnych zasobów.


    void fixMissingValues() {
        for (AdminUnit unit : units) {
            unit.fixMissingValues();
        }
    }

    /**
     * Zwraca listę jednostek sąsiadujących z jendostką unit na tym samym poziomie hierarchii admin_level.
     * Czyli sąsiadami wojweództw są województwa, powiatów - powiaty, gmin - gminy, miejscowości - inne miejscowości
     *
     * @param unit        - jednostka, której sąsiedzi mają być wyznaczeni
     * @param maxdistance - parametr stosowany wyłącznie dla miejscowości, maksymalny promień odległości od środka unit,
     *                    w którym mają sie znaleźć punkty środkowe BoundingBox sąsiadów
     * @return lista wypełniona sąsiadami
     * <p>
     * wojewodztwo - 4
     * powiat - 6
     * gmina - 7
     * miejscowosc - 8
     */
    AdminUnitList getNeighbors2(AdminUnit unit, double maxdistance) {
        AdminUnitList unitsNeighbors = new AdminUnitList();
        int adminLevelNedded = unit.adminLevel;

        if (adminLevelNedded >= 8) {
            for (AdminUnit currentUnit : units) {
                if (currentUnit.adminLevel == adminLevelNedded && !currentUnit.equals(unit) && unit.bbox.distanceTo(currentUnit.bbox) < maxdistance) {
                    unitsNeighbors.units.add(currentUnit);
                }
            }
        } else {
            for (AdminUnit currentUnit : units) {
                if (currentUnit.adminLevel == adminLevelNedded && currentUnit.bbox.intersects(unit.bbox) && !currentUnit.equals(unit)) {
                    unitsNeighbors.units.add(currentUnit);
                }
            }
        }

                return unitsNeighbors;
    }
    /////////////////////
    public AdminUnitList getNeighborsTree(AdminUnit unit, double maxdistance) {
        AdminUnitList unitsNeighbors = new AdminUnitList();

        if (unit.parent == null) {
            getNeighborsRoot(unit, unitsNeighbors);
        } else {
            getNeighborsWithParent(unit, maxdistance, unitsNeighbors);
        }

        return unitsNeighbors;
    }

    private void getNeighborsRoot(AdminUnit unit, AdminUnitList unitsNeighbors) {
        for (AdminUnit currentUnit : units) {
            if (currentUnit != unit && currentUnit.adminLevel == unit.adminLevel && currentUnit.bbox.intersects(unit.bbox)) {
                unitsNeighbors.units.add(currentUnit);
            }
        }
    }

    private void getNeighborsWithParent(AdminUnit unit, double maxdistance, AdminUnitList unitsNeighbors) {
        if (unit.parent != null) {
            for (AdminUnit sibling : unit.parent.children) {
                if (sibling != unit && (sibling.bbox.distanceTo(unit.bbox) < maxdistance || sibling.bbox.intersects(unit.bbox))) {
                    unitsNeighbors.units.add(sibling);
                }

            }

            for (AdminUnit aunt : unit.parent.parent.children) {
                    for (AdminUnit cousin : aunt.children) {
                        if (cousin != unit && (cousin.bbox.distanceTo(unit.bbox) < maxdistance || cousin.bbox.intersects(unit.bbox))) {
                            unitsNeighbors.units.add(cousin);
                        }
                    }

                }


            }
        }


     // ------------------------------------------------------------------------------------------------------------

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    public AdminUnitList sortInplaceByName(){

        class NameComparator implements Comparator<AdminUnit> {
            @Override
            public int compare(AdminUnit t, AdminUnit t1) {
                return String.CASE_INSENSITIVE_ORDER.compare(t.name, t1.name);
            }
        }
        NameComparator comparator = new NameComparator();
        units.sort(comparator);
        return this;
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByArea() {
        units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit unit1, AdminUnit unit2) {
                return Double.compare(unit1.area, unit2.area);
            }
        });
        return this;
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */

    public AdminUnitList sortInplaceByPopulation() {
        units.sort((unit1, unit2) ->
                Double.compare(unit1.population, unit2.population));
        return this;
    }

    public AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        // Tworzy wyjściową listę
        // Kopiuje wszystkie jednostki
        // woła sortInPlace

        AdminUnitList sortedList = new AdminUnitList();
        sortedList.units.addAll(this.units);
        sortedList.sortInplace(cmp);
        return sortedList;
    }

    /**
     *
     * @param pred referencja do interfejsu Predicate
     * @return nową listę, na której pozostawiono tylko te jednostki,
     * dla których metoda test() zwraca true
     */
    public AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList filteredList = new AdminUnitList();

        for (AdminUnit unit : units) {
            if (pred.test(unit)) {
                filteredList.units.add(unit);
            }
        }

        return filteredList;
    }
    /**
     * Zwraca co najwyżej limit elementów spełniających pred
     * @param pred - predykat
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    public AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        AdminUnitList filteredList = new AdminUnitList();
        int count = 0;

        for (AdminUnit unit : units) {
            if (pred.test(unit)) {
                filteredList.units.add(unit);
                count++;

                if (count >= limit) {
                    break;
                }
            }
        }

        return filteredList;
    }

    /**
     * Zwraca co najwyżej limit elementów spełniających pred począwszy od offset
     * Offest jest obliczany po przefiltrowaniu
     * @param pred - predykat
     * @param - od którego elementu
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    public AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        AdminUnitList filteredList = new AdminUnitList();
        int count = 0;

        for (AdminUnit unit : units) {
            if (pred.test(unit)) {
                if (count >= offset) {
                    filteredList.units.add(unit);
                    if (++count >= offset + limit) {
                        break;
                    }
                } else {
                    count++;
                }
            }
        }

        return filteredList;
    }





}


//w ciele lambdy










