package main.output;

import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.StringReader;
import java.io.StringWriter;

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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;

/**
 * The XMLExport is responsible for exporting a given Stringbuilder as a .xml-file
 */
public class XMLExport implements IExport{

     /**
     * This Method will write a given Stringbuilder to a .xml-file
     *
     * @param jsonString a String containing a JSONArray or JSONObject
     * @param aggregat a String containing information about the used aggregate
     */
    public void export(StringBuilder jsonTree, String aggregat) throws JSONException{
        try {
            
            // Create Writer
            FileWriter fileWriter = new FileWriter("./output/output.xml");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Create JSONArray
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jsonParser = new JsonParser();
            JSONArray json = new JSONArray(gson.toJson(jsonParser.parse(jsonTree.toString())));

            // Create XML-String from JSONArray
            String rootname = "";
            String aggregatname = "";
            switch (aggregat){
                case "csa":
                    rootname = "channels";
                    aggregatname = "channel";
                break;
                case "sta":
                    rootname = "satellites";
                    aggregatname = "sat";
                break;
            }
            String xml =  "<" + rootname + ">" + XML.toString(json, aggregatname)+ "</" + rootname + ">";
            Document xmlDoc = xmlToDocument(xml);
            String formattedXML = prettyPrint(xmlDoc);
            
            // Write formated XML-String to file
            bufferedWriter.write(formattedXML);
            bufferedWriter.flush();
            bufferedWriter.close();

        } 
        // Catch Exeptions
        catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }

    }
    /**
     * This Method will prettyprint a given document
     *
     * @param document Document containing the xml-String which will be prettyprinted
     */
    private static String prettyPrint(Document document) throws TransformerException {

        // Create Transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Set Output Properties
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        // Create source and Result for transformation
        DOMSource source = new DOMSource(document);
        StringWriter strWriter = new StringWriter();
        StreamResult result = new StreamResult(strWriter);
        transformer.transform(source, result);
    
        return strWriter.getBuffer().toString();
    }
    /**
     * This Method will convert a xml-String to a Document
     *
     * @param data a xml-String
     */
    private static Document xmlToDocument(String data)throws ParserConfigurationException, SAXException, IOException {
    
        // Create Document from String
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(new InputSource(new StringReader(data)));
    
        return document;
      
    }
}

