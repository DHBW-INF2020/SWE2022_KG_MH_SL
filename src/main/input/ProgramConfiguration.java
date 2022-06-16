package main.input;

import org.json.JSONException;

import main.aggregates.ChannelSatellitesAggregate;
import main.aggregates.SatelliteTranspondersAggregate;
import main.output.IExport;
import main.output.JSONExport;
import main.output.TreeToJsonConverter;
import main.output.XMLExport;
import main.tree.Root;
import main.visitors.BaseVisitor;

import java.io.File;
import java.util.Objects;


/**
 * Creates the Config and runs the Program according to this config
 */
public class ProgramConfiguration {

    private String[] programFlags;
    //
    private String inputFilePath;
    //
    private String aggregateType;
    // j = json | x = xml
    private String outputFileType;


    /**
     * Constructor:
     * Creates a Programm Configuration according to a given number of Input Parameters from the Program Call
     *
     * The Program Configuration controls the "flow" of the program
     * @author Hartmann
     * @param Args a String Array that contains the Parameters from Program startup
     */
    public ProgramConfiguration(String[] Args) throws Exception {
        programFlags = Args;

        // if number of args is correct, try to use them for Program
        if(Args.length == 3)
        {
            // Set aggregateType
            if(Objects.equals(Args[0], "csa")||Objects.equals(Args[0],"sta"))
            {
                aggregateType = Args[0];
            }
            else
            {
                System.out.println("Aggregate-Flag is not valid");
                System.out.println("Set to default => csa");
                aggregateType = "csa";
            }

            // Set outputFileType
            if(Objects.equals(Args[1], "xml")||Objects.equals(Args[1],"json"))
            {
                outputFileType = Args[1];
            }
            else{
                System.out.println("Output-File-Type-Flag is not valid");
                System.out.println("Set to default => json");
                outputFileType = "json";
            }

            // Set inputFilePath
            File file = new File(Args[2]);
            if(file.exists())
            {
                inputFilePath = Args[2];
            }
            else{
                System.out.println("Input-File doesnt exist or Path is not valid");
                System.out.println("Set to default => ./res/Aufgabe_3_satellites.json");
                inputFilePath = "./res/Aufgabe_3_satellites.json";
            }
        }

        // False input -> use default instead
        else{
            System.out.println("Number of Flags not Valid");
            System.out.println("Set to default => default config");
            aggregateType = "csa";
            outputFileType = "json";
            inputFilePath = "./res/Aufgabe_3_satellites.json";
        }
    }

    // Getter for ProgramFlags[]
    /**
     * @return String[] returns all Program Flags from the Program Call
     */
    public String[] getProgramFlags() {
        return programFlags;
    }
    /**
     * @return String returns the File Path of the Input File
     */
    public String getInputFilePath() {
        return inputFilePath;
    }
    /**
     * @return String returns the whished Aggregate type -> eg: sta, csa
     */
    public String getAggregateType() {
        return aggregateType;
    }
    /**
     * @return String returns the output file type -> eg: json, xml
     */
    public String getOutputFileType() {
        return outputFileType;
    }

    // ------------- run Program Config -----------------

    /**
     * The Programm is run according to the previously created Program Configuration
     *
     * This Method will create a output at ./output/output.[insert Filetype here]
     *
     * @author Hartmann
     */
    public void runProgramWithConfig() throws JSONException {
        JsonToTreeConverter inputConverter = new JsonToTreeConverter();

        // choose aggregate type according to Config
        BaseVisitor aggregate = null;

        if(Objects.equals(aggregateType, "csa"))
        {
            aggregate = new ChannelSatellitesAggregate();
        }
        else if (Objects.equals(aggregateType, "sta"))
        {
            aggregate = new SatelliteTranspondersAggregate();
        }
        else
        {
            System.out.println("unknown aggregateType - using csa now");
            aggregate = new ChannelSatellitesAggregate();
        }

        // ------------------------------------------

        // convert the Tree that was created from the Input Json to an aggregated Tree
        Root returnTree = (Root) inputConverter.readInputJson(inputFilePath).accept(aggregate);

        // convert the converted Tree to a JSON String
        TreeToJsonConverter outputConverter = new TreeToJsonConverter();
        StringBuilder jsonStringBuilder = (StringBuilder) returnTree.accept(outputConverter);

        IExport exporter;
        // choose output file type according to Config and export
        if(Objects.equals(outputFileType, "xml"))
        {
            // convert JSON String to XML
        	exporter = new XMLExport();
        }
        else if (Objects.equals(outputFileType, "json"))
        {
            // Print JSON String to JSON File
        	exporter = new JSONExport();
        }
        else
        {
            System.out.println("unknown File Type - using json now");
            exporter = new JSONExport();
        }
        exporter.export(jsonStringBuilder, aggregateType);
    }

}