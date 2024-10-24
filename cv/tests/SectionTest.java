import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


import static org.junit.jupiter.api.Assertions.*;

class SectionTest {

    @Test
    public void writeHTML() throws Exception {
        String sectionTitle = "Umiejętności";
        String paragraphContent = "Zbieranie grzybów";
        String paragraphContent2 = "Zbieranie jagód";

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        Section section = new Section();
        section.setTitle(sectionTitle);
        section.addParagraph(paragraphContent);
        section.addParagraph(paragraphContent2);


        String result = null;
        section.writeHTML(ps);

        try {
            result = os.toString("UTF-8");
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


         assertTrue(result.contains("<section>"));
         assertTrue(result.contains("<h2> " + sectionTitle + " </h2>"));
         assertTrue(result.contains("<p>" + paragraphContent + "</p>"));
         assertTrue(result.contains("<p>" + paragraphContent2 + "</p>"));
         assertTrue(result.contains("</section>"));
    }

    }
