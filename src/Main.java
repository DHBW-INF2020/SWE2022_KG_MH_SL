import java.util.ArrayList;

import com.google.gson.Gson;

import aggregates.ChannelSatellitesAggregate;
import aggregates.SatelliteTranspondersAggregate;
import tree.*;
import output.JSONExport;
import output.XMLExport;
import output.TreeToJsonConverter;
import input.JsonToTreeConverter;

import java.io.Reader;
import org.json.JSONException;
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.event.TreeExpansionEvent;

/**
 *
 */
public class Main {

    //--------------------------- main(Sting[]Args) -----------------------

    /**
     * @param Args
     */
    public static void main(String[]Args) throws JSONException {
    	//main.testAggregate();
        // es sind 3031 Transpoder - SatKombinationen
        // 136 Satelliten
        JsonToTreeConverter converter = new JsonToTreeConverter();
        converter.readInputJson("./res/Aufgabe_3_satellites.json");

        //Main.readInputJson();

        ChannelSatellitesAggregate csa = new ChannelSatellitesAggregate();
        Root returnTree = (Root) converter.readInputJson("./res/Aufgabe_3_satellites.json").accept(csa);

        TreeToJsonConverter converter_2 = new TreeToJsonConverter();
        StringBuilder jsonStringBuilder = (StringBuilder) returnTree.accept(converter_2);
//            StringBuilder jsonStringBuilder = (StringBuilder) root.accept(converter);

        JSONExport export_json = new JSONExport();
        export_json.export(jsonStringBuilder);
        XMLExport export_xml = new XMLExport();
        export_xml.export(jsonStringBuilder);
    }
}



