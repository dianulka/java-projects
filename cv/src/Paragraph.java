import java.io.PrintStream;
public class Paragraph  {
    String content;
    Paragraph setContent(String content){
        this.content=content;
        return this;
    }



    void writeHTML(PrintStream out){
        out.print("<p>");
        out.print(content);
        out.print("</p> \n");
    }
}
