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
    public static void main(String[] Args) throws JSONException {

        JsonToTreeConverter inputConverter = new JsonToTreeConverter();
        ChannelSatellitesAggregate csa = new ChannelSatellitesAggregate();
        Root returnTree = (Root) inputConverter.readInputJson("./res/Aufgabe_3_satellites.json").accept(csa);

        TreeToJsonConverter outputConverter = new TreeToJsonConverter();
        StringBuilder jsonStringBuilder = (StringBuilder) returnTree.accept(outputConverter);
//            StringBuilder jsonStringBuilder = (StringBuilder) root.accept(outputConverter);

        JSONExport export_json = new JSONExport();
        export_json.export(jsonStringBuilder);
        XMLExport export_xml = new XMLExport();
        export_xml.export(jsonStringBuilder);
    }
}



