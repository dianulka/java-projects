import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphTest {

    @org.junit.jupiter.api.Test
    public void writeHTML() throws Exception {
        String paragraphContent = "Gra na gitarze.";

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        new Paragraph().setContent(paragraphContent).writeHTML(ps);

        String result = null;

        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<p>"));
        assertTrue(result.contains(paragraphContent));
        assertTrue(result.contains("</p>"));
    }
    }
