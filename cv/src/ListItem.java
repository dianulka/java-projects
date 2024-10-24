import java.io.PrintStream;
public class ListItem {
    String content;

    ListItem(String content){
        this.content=content;
    }
    ListItem(){}

    void writeHTML(PrintStream out){
        out.printf("<li> %s </li> \n",content);
    }
}
