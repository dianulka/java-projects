import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


import static org.junit.jupiter.api.Assertions.*;

class UnorderListTest {

    @Test
    public void writeHTML() throws Exception {

        String item1="Język angielski";
        String item2="Język niemiecki";
        String item3="Język polski";

        UnorderList unorderedList = new UnorderList()
                .addItem(new ListItem(item1))
                .addItem(new ListItem(item2))
                .addItem(new ListItem(item3));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        unorderedList.writeHTML(ps);

        String result = null;

        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<ul>"));
        assertTrue(result.contains("<li> "+item1+" </li>"));
        assertTrue(result.contains("<li> " +item2 +" </li>"));
        assertTrue(result.contains("<li> " +item3+" </li>"));
        assertTrue(result.contains("</ul>"));
    }
}