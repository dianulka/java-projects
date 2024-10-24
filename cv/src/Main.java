import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        Document cv = new Document("Puszek Mruczkowski - CV");
        cv.setPhoto("https://petcostumecenter.com/wp-content/uploads/2020/05/580413_PS_PAW_BILL_SUIT-scaled-700x766.webp");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Kopciuszka w Pałacowie")
                .addParagraph("2006-2012 SP20 im Józefa Piłsudskiego w Płocku")
                .addParagraph("2012-2015 Gimnazjum Nr 1 im. Jana Długosza w Sandomierzu ")
                .addParagraph("2015-2018 VI Liceum Ogólnokształcące im. Juliusza Słowackiego w Kielcach")
                .addParagraph("2018-2022 Politechnika Warszawska \n" + "Kierunek: Matematyka stosowana")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Języka Angielskiego")
                                .addListItem("Języka Hiszpańskiego")
                                .addListItem("Podstawy programowania w języku Basic")
                                .addListItem("Kurs PHP od podstaw")

                );
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Znane technologie")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                                .addListItem("PHP")
                                .addListItem("MySQL")
                );

        cv.addSection("Doświadczenie zawodowe")
                        .addParagraph(
                                new ParagraphWithList().setContent("Specjalista ds. Marketingu")
                                        .addListItem("Opracowanie i wdrożenie strategii marketingowej firmy," +
                                                " co przyniosło wzrost przychodów o 30% w ciągu roku.")
                                        .addListItem("Analiza danych marketingowych i tworzenie raportów,"+
                                                "które pomogły w optymalizacji działań marketingowych.")
                                        .addListItem("Współpraca z zespołem sprzedaży w celu zapewnienia spójności" +
                                                " działań marketingowych z celami sprzedażowymi.")

                        )
                        .addParagraph(
                                new ParagraphWithList().setContent("Administrator Bazy Danych")
                                        .addListItem("Zarządzanie bazą danych SQL, w tym tworzenie i utrzymanie baz danych," +
                                                " zapytań SQL i procedur przechowywanych.")
                                        .addListItem("Optymalizacja wydajności baz danych i rozwiązywanie problemów z nimi związanymi.")
                        );


        cv.addSection("Zainteresowania")
                .addParagraph("Gra na gitarze")
                .addParagraph(
                        new ParagraphWithList().setContent("Kultura krajów")
                                .addListItem("anglojęzycznych")
                                .addListItem("skandynawskich")
                                .addListItem("słowiańskich")
                );

      //cv do html
      cv.writeHTML(new PrintStream("puszek_cv.html","UTF-8"));

      //deserializacja bez przekazania informacji o typie
      String jason=cv.toJson();
      Document cv2=cv.fromJson(jason);
      cv2.writeHTML(new PrintStream("des_bez_typu.json"));

    //deserializacja z przekazaniem informacji o typie
        String jason2= cv.toJson2();
        Document cv3=cv.fromJson2(jason2);
        cv3.writeHTML(new PrintStream("des_z_typem.json"));



    }
}
