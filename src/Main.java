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
     *
     * Variant 1: no Args
     *
     * Variant 2: 1 Arg -> Path of Config File (JSON)
     * "D:\Dateien Schnell\Java\SWE2022_KG_MH_SL\input\program_configuration_2.json"
     *
     * Variant 3: 4 Args (AggregateType OutputFileType sourceJSONFile outputPath)
     * sta json "D:\Dateien Schnell\Java\SWE2022_KG_MH_SL\res\Aufgabe_3_satellites.json" "D:\Dateien Schnell\Java"
     */
    public static void main(String[] Args) throws Exception {
        InputHandler inputHandler = new InputHandler();

        // create new Configuration for Program
        ProgramConfiguration currentConfiguration = inputHandler.readInput(Args);;
        // run Program
        currentConfiguration.runProgramWithConfig();
    }
}



