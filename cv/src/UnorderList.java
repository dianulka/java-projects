import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderList {
    List<ListItem> unorder_list = new ArrayList<>() ;


    UnorderList addItem(ListItem item){
        unorder_list.add(item);
        return this;
    }

    void writeHTML(PrintStream out){
        out.print("\n <ul>");
        for (ListItem item : unorder_list){
            item.writeHTML(out);
        }
        out.print("</ul> \n");
    }
}
