package output;

import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;

import org.json.JSONException;
import org.json.XML;

public class XMLExport implements IExport{

    public void export(StringBuilder jsonTree) throws JSONException{
        try {
            FileWriter fileWriter = new FileWriter("./output/output.xml");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String printString = jsonTree.toString();
            String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n<root>" + XML.toString(printString) + "</root>";
            bufferedWriter.write(xml);
            bufferedWriter.flush();
            bufferedWriter.close();

        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
