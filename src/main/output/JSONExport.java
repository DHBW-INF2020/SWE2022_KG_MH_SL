package main.output;

import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * The JSONExport is responsible for exporting a given Stringbuilder as a .json-file
 *
 * @author Lang
 * @version 1.0
 */
public class JSONExport implements IExport{
    /**
     * This Method will substitute special characters within a given String
     *
     * @param jsonString a String containing a JSONArray or JSONObject
     * @return The jsonstring without special characters
     */
    private static String subsituteSpecialCharacters(String jsonString) {
        return jsonString
                .replace("\t", "\\t")
                .replace("\b", "\\b")
                .replace("\r", "\\r")
                .replace("\f", "\\f");
    }

    /**
     * This Method will write a given Stringbuilder to a .json-file
     *
     * @param jsonString a String containing a JSONArray or JSONObject
     * @param aggregat a String containing information about the used aggregate
     */
    public void export(StringBuilder jsonTree, String aggregat) {
        
        try {
            // Create Writer
            FileWriter fileWriter = new FileWriter("./output/output.json");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Create jsonString with correct formating
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = jsonTree.toString();
            jsonString = JSONExport.subsituteSpecialCharacters(jsonString);
            JsonElement jsonObject = JsonParser.parseString(jsonString);

            // Write jsonString to file
            gson.toJson(jsonObject, bufferedWriter);
            bufferedWriter.flush();
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    
    }
}
