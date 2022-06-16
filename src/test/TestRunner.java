package test;

import java.io.IOException;
import org.json.JSONException;

/**
 * Class to run Unittests
 */
public class TestRunner {
    /**
     * @author Lang
     * @param args 
     *
     */
    public static void main(String[] args) {
        try {

            //
            //Json to Tree Conversion Test
            //
            JsonToTeeConverterTest JsonToTree = new JsonToTeeConverterTest();
            boolean test1 = JsonToTree.readInputJson();
            System.out.println("Json to Tree Conversion Test successful: " + test1);

            //
            //JSON Export Test
            //
            JSONExportTest JSONExportTest = new JSONExportTest();
            boolean test2 = JSONExportTest.export();
            System.out.println("JSON Export Test successful: " + test2);

            //
            //XML Export Test
            //
            XMLExportTest XMLExportTest = new XMLExportTest();
            boolean test3 = XMLExportTest.export();
            System.out.println("XML Export Test successful: " + test3);

            
        }
        catch (IOException  | JSONException e) {
            e.printStackTrace();
        }
        
    }
     	
}
