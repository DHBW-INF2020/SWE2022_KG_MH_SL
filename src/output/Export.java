package output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;  
import org.json.XML;

import java.io.BufferedWriter;
import java.io.FileWriter; 
import java.io.IOException;
import org.json.JSONException;  
import tree.Root;

/**
 *
 */
public class Export {

	/**
	 * @param jsonString
	 * @return
	 */
	private static String subsituteSpecialCharacters(String jsonString) {
		return jsonString
				.replace("\t", "\\t")
				.replace("\b", "\\b")
				.replace("\r", "\\r")
				.replace("\f", "\\f");
	}
	
    /**
     * @param jsonTree
     */
    public static void as_JSON(StringBuilder jsonTree){
        try {
            //Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter("./output/output.json");
            //gson.toJson(formated_tree, fileWriter);
            //If anything left in buffer, write the rest to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String printString = jsonTree.toString();
            printString = Export.subsituteSpecialCharacters(printString);
            bufferedWriter.append(printString);
            bufferedWriter.flush();
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * @param formated_tree
     */
    public static void as_XML(Root formated_tree){
        try {
            Gson gson = new GsonBuilder().create();
            JSONObject json = new JSONObject(gson.toJson(formated_tree));
            
            String xml = XML.toString(json);
            FileWriter writer = new FileWriter("./output/output.xml");
            writer.write(xml);
            writer.close();

        } 
        catch (JSONException | IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
