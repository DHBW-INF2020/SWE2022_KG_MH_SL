package output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;  
import org.json.XML;  
import java.io.FileWriter; 
import java.io.IOException;
import org.json.JSONException;  
import tree.Root;

public class Export {

    public static void as_JSON(Root formated_tree){
        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(formated_tree, new FileWriter("./output/output.json"));

        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void as_XML(Root formated_tree){
        try {
            Gson gson = new GsonBuilder().create();
            JSONObject json = new JSONObject(gson.toJson(formated_tree));
            
            String xml = XML.toString(json);
            FileWriter writer = new FileWriter("./output/output.xml");
            writer.write(xml);
            writer.close();

        } 
        catch (JSONException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
