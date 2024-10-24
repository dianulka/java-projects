import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {

    UnorderList unorder_list;

    ParagraphWithList(){
        unorder_list = new UnorderList();
    }
    @Override
    ParagraphWithList setContent(String content) {
        super.setContent(content);
        return this;
    }

    ParagraphWithList addListItem(String item){

        ListItem newListItem = new ListItem();
        newListItem.content=item;
        unorder_list.addItem(newListItem);
        return this;
    }

    void writeHTML(PrintStream out){
        out.print("<p>");
        out.print(content);
        unorder_list.writeHTML(out);
        out.print("</p>");

    }
}
