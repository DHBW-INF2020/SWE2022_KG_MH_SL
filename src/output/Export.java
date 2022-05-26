package output;

import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
//import org.json.JSONException;  
//import org.json.JSONObject;  
//import org.json.XML;

public class Export {

    String format;
    //JSONObject jsonObject = new JSONObject();

    public void export_as_JSON(){
        try {
            File myJSONfile = new File("output.json");
            FileWriter myWriter = new FileWriter("output.json");
            //myWriter.write(jsonObject.toJSONString());
            myWriter.close();
        } 
        catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }

    public void export_as_XML(){
        try {
            File myJSONfile = new File("output.xml");
            FileWriter myWriter = new FileWriter("output.xml");
            //myWriter.write(jsonObject.toJSONString());
            myWriter.close();
        } 
        catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }
}
