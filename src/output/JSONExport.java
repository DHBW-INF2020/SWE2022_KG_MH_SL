package output;

import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JSONExport implements IExport{

    private static String subsituteSpecialCharacters(String jsonString) {
        return jsonString
                .replace("\t", "\\t")
                .replace("\b", "\\b")
                .replace("\r", "\\r")
                .replace("\f", "\\f");
    }

    public void export(StringBuilder jsonTree, String aggregat) {
        
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter("./output/output.json");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String printString = jsonTree.toString();
            printString = JSONExport.subsituteSpecialCharacters(printString);
            JsonElement jsonObject = JsonParser.parseString(printString);
            gson.toJson(jsonObject, bufferedWriter);
            bufferedWriter.flush();
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    
    }
}
