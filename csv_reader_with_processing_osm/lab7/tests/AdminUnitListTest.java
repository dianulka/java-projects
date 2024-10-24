import org.junit.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.Locale;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

public class AdminUnitListTest {

    @Test
    //gmina
    public void gminaTest() throws IOException {
        AdminUnitList lista = new AdminUnitList();
        lista.read("admin-units.csv");
        AdminUnit jednostka = lista.selectByName("gmina Staszów",false).units.get(0);
        lista.getNeighbors2(jednostka,0).list(System.out);
    }

    @Test
    //gmina
    public void testWojewodztwo() throws IOException {
        AdminUnitList lista = new AdminUnitList();
        lista.read("admin-units.csv");
        AdminUnit jednostka = lista.selectByName("^województwo świętokrzyskie*",true).units.get(0);
        lista.getNeighbors2(jednostka,0).list(System.out);
    }

    @Test
    //gmina
    public void testPowiat() throws IOException {
        AdminUnitList lista = new AdminUnitList();
        lista.read("admin-units.csv");
        AdminUnit jednostka = lista.selectByName("powiat staszowski",false).units.get(0);
        lista.getNeighbors2(jednostka,0).list(System.out);
    }

    @Test
    //miejscowosc
    public void testMiejscowosc() throws IOException {
        AdminUnitList lista = new AdminUnitList();
        lista.read("admin-units.csv");
        lista.fixMissingValues();
        double t1 = System.nanoTime()/1e6;
        AdminUnit jednostka = lista.selectByName("Wiśniowa Poduchowna",false).units.get(0);
        lista.getNeighbors2(jednostka,15).list(System.out);
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"Czas funkcji: t2-t1=%f\n",t2-t1);
        //tak dobrze bo tu mieszkam! :D
    }














}