import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.google.gson.Gson;

import aggregates.ChannelSatellitesAggregate;
import aggregates.SatelliteTranspondersAggregate;
import input.InputHandler;
import input.ProgramConfiguration;
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
        InputHandler inputHandler = new InputHandler();

        // create new Configuration for Program
        ProgramConfiguration currentConfiguration = inputHandler.readInput(Args);;

        System.out.println(currentConfiguration.getAggregateType());

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



