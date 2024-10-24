import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    public void toJson2() throws Exception {
        Document cv = new Document("Anna Mróz");
        cv.addSection("Wykształcenie")
                .addParagraph("PK")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Szydełkowanie")
                                .addListItem("Robienie na drutach")
                                .addListItem("Kurs chodzenia na linie")

                );
        cv.addSection("Zainteresowania")
                .addParagraph(
                        new ParagraphWithList().setContent("Hobby")
                                .addListItem("Jazda konna")
                                .addListItem("Skoki narciarskie")
                                .addListItem("Gra na pianinie")
                                .addListItem("Gra na ukulele")
                )
                .addParagraph("Lubię czytać książki");

        //Serializacja do formatu JSON
        String jason2= cv.toJson2();
        //Deserializacja
        Document cv3=cv.fromJson2(jason2);
        //Powtórna serializacja do formatu JSON
        String jason3 = cv3.toJson2();

        assertEquals(jason2,jason3);
    }
}