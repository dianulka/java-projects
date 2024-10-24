import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;



public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(){}
    Document(String title){
        this.title = title;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        // ???
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        // utwórz sekcję o danym tytule i dodaj do sections
        Section newSection = new Section();
        newSection.setTitle(sectionTitle);
        sections.add(newSection);
        return newSection; //???;
    }
    Document addSection(Section s){
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        // zapisz niezbędne znaczniki HTML
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)
        out.print("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>");

        out.printf( "<h2> %s </h2>",title);
        photo.writeHTML(out);

        for (Section s: sections){
            s.writeHTML(out);
        }

        out.print("\n </body> \n" +
                    "</html>");
    }

    String toJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }


    Document fromJson(String jsonString){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonString, Document.class);
    }

   String toJson2() {
       RuntimeTypeAdapterFactory<Paragraph> adapter =
               RuntimeTypeAdapterFactory
                       .of(Paragraph.class)
                       .registerSubtype(Paragraph.class)
                       .registerSubtype(ParagraphWithList.class);
       Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).setPrettyPrinting().create();
       return gson.toJson(this);

   }

    Document fromJson2(String jsonString){
        RuntimeTypeAdapterFactory<Paragraph> adapter =
                RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).create();
        return gson.fromJson(jsonString, Document.class);
    }





}
