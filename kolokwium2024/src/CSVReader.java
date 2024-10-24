import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    /**
     * @param filename  - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        //   reader = new BufferedReader(new FileReader(filename)); //klasa obslugujaca strumienei danych
        // this.delimiter = delimiter;
        //  this.hasHeader = hasHeader;
        // if (hasHeader) parseHeader();
        this(new BufferedReader(new FileReader(filename)),delimiter,hasHeader);

    }

    CSVReader(String filename,String delimiter) throws IOException {
        this(filename,delimiter,true);
    }

    CSVReader(String filename) throws IOException {
        this(filename,",");
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException { //
        this.reader=new BufferedReader(reader);
        this.delimiter=delimiter;
        this.hasHeader=hasHeader;
        if (hasHeader) parseHeader();
    }

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
            columnLabels.add(header[i]); //kolejnosc kolmny na lisciwe daje nr koluny
            columnLabelsToInt.put(header[i],i);
        }
        //...
    }

    String[]current;
    boolean next() throws IOException { //wolamy next, jezeli sie udalo zwracamy true, w przeciwnym razie false ma za zadanie wczytac next wiersz, podzielic na elementy , przypisac na current , to co header to trafi do current
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        //
        String line = reader.readLine();
        if (line == null) {
            return false;
        }

        current=line.split(delimiter); // line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        return true;

    }


    List<String> getColumnLabels(){
        return columnLabels;
    }

    int getRecordLength() {
        return current.length;
    }

    boolean isMissing(int columnIndex)  {
        if (columnIndex>=current.length || columnIndex<0 || current[columnIndex].isEmpty()){
            return true;
        }
        return false;

    }

    boolean isMissing(String columnLabel) throws RuntimeException {
        Integer columnIndex = columnLabelsToInt.get(columnLabel);
        return columnIndex == null || isMissing(columnIndex);
    }

    String get(int columnIndex) {
        if (isMissing(columnIndex)){
            return "";
        }
        return current[columnIndex];
    }

    String get(String columnLabel){
        if (isMissing(columnLabel)){
            return "";
        }
        Integer columnIndex= columnLabelsToInt.get(columnLabel);
        return current[columnIndex];
    }

    int getInt(int columnIndex) throws RuntimeException {
        if (get(columnIndex).equals("")) {
            // throw new Exception("empty int index");
            throw new RuntimeException();
        }
        String s= get(columnIndex);
        return Integer.parseInt(s);
    }

    int getInt(String columnLabel) throws RuntimeException{
        if (get(columnLabel).equals("")) {
            throw new RuntimeException();
            //throw new Exception("empty int label");
        }
        String s=get(columnLabel);
        return Integer.parseInt(s);
    }

    long getLong(int columnIndex) throws RuntimeException{
        if (this.get(columnIndex).equals("")) {
            //throw new Exception("Empty long index");
            throw new RuntimeException();
        }
        String s= get(columnIndex);
        return Long.parseLong(s);
    }

    long getLong(String columnLabel){
        Integer columnIndex= columnLabelsToInt.get(columnLabel);
        if (isMissing(columnIndex)) {
            // throw new Exception("Empty long label");
            throw new IllegalArgumentException();
        }
        String s=get(columnLabel);
        return Long.parseLong(s);
    }

    double getDouble(int columnIndex) throws RuntimeException{
        if (get(columnIndex).equals("")) {
            //throw new Exception("Empty double index ");
            throw new RuntimeException();
        }
        String s= get(columnIndex);
        return Double.parseDouble(s);
    }

    double getDouble(String columnLabel) throws RuntimeException{
        if (this.get(columnLabel).equals("")) {
            //throw new Exception("Empty double label");
            throw new RuntimeException();
            //return -1;
        }
        String s=get(columnLabel);
        return Double.parseDouble(s);
    }

    double getDouble(String columnLabel,double def){
        return 1.0;
    }

    LocalTime getTime(int columnIndex, String format) throws IOException {
        if (isMissing(columnIndex)) {
            return null;
        }
        String timeString = current[columnIndex];
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern(format));
        return time;
    }

    LocalTime getTime(String columnLabel, String format) throws IOException {
        Integer columnIndex = columnLabelsToInt.get(columnLabel);
        if (columnIndex == null) {
            return null;
        }

        return getTime(columnIndex, format);
    }

    LocalDate getDate(int columnIndex, String format) throws IOException {
        if (isMissing(columnIndex)){
            return null;
        }
        String dateString=current[columnIndex];
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format));
        return date;


    }

    LocalDate getDate(String columnLabel, String format) throws IOException {
        Integer columnIndex = columnLabelsToInt.get(columnLabel);
        if (columnIndex == null) {
            return null;
        }
        return getDate(columnIndex, format);
    }

    //Double getDouble()
    // null

    // Nan

    //4) double getDoube (string cd,double det)

    //density:12575
    //population: 12576
    //area:7



}