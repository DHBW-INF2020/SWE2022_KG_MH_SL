package output;

import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.XML;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.Writer;

public class XMLExport implements IExport{

    public void export(StringBuilder jsonTree) throws JSONException{
        try {
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            StringBuilder builder = new StringBuilder();
            FileWriter fileWriter = new FileWriter("./output/output.xml");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String singleString = jsonTree.toString();
            JsonParser jsonParser = new JsonParser();
            JSONArray json = new JSONArray(gson.toJson(jsonParser.parse(singleString)));
            String xml = XML.toString(json, "root");

            bufferedWriter.write(xml);
            bufferedWriter.flush();
            bufferedWriter.close();

        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (Exception e) {
        throw new RuntimeException("Error occurs when pretty-printing xml:\n", e);
        }

    }
}

