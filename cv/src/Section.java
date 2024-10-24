import com.sun.source.util.ParameterNameProvider;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraps = new ArrayList<>() ;

    Section setTitle(String title){
        this.title=title;
        return this;
    }
    Section addParagraph(String paragraphText){
        Paragraph newParagraph = new Paragraph();
        newParagraph.setContent(paragraphText);
        paragraps.add(newParagraph);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraps.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
       out.print( "<section> \n");
        out.printf( "<h2> %s </h2> \n",title);
        for (Paragraph p:paragraps){
            p.writeHTML(out);
        }
        out.print( "\n </section> \n");
    }
}
