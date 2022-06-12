package input;

import aggregates.ChannelSatellitesAggregate;
import aggregates.SatelliteTranspondersAggregate;
import org.json.JSONException;
import output.JSONExport;
import output.TreeToJsonConverter;
import output.XMLExport;
import tree.Root;
import visitors.BaseVisitor;

import java.io.File;
import java.util.Objects;

/**
 *
 */
public class ProgramConfiguration {

    private String[] programFlags;
    //
    private String inputFilePath;

    private String outputFilePath;
    //
    private String aggregateType;
    // j = json | x = xml
    private String outputFileType;


    /**
     *
     */
    public ProgramConfiguration(String[] Args) throws Exception {
        programFlags = Args;

        if(Args.length == 0|| Args.length == 1 ||Args.length == 4)
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

            // Set outputFilePath
            outputFilePath = Args[3];
        }
        else{
            System.out.println("Number of Flags not Valid");
            System.out.println("Set to default => default config");
            aggregateType = "csa";
            outputFileType = "json";
            inputFilePath = "./res/Aufgabe_3_satellites.json";
            outputFilePath = "./output/output.json";
        }
    }

    // Getter for ProgramFlags[]

    public String[] getProgramFlags() {
        return programFlags;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public String getOutputFileType() {
        return outputFileType;
    }

    // ------------- run Program Config -----------------

    /**
     *
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

        // choose output file type according to Config and export
        if(Objects.equals(outputFileType, "xml"))
        {
            // convert JSON String to XML
            XMLExport export_xml = new XMLExport();
            export_xml.export(jsonStringBuilder, aggregateType);
        }
        else if (Objects.equals(outputFileType, "json"))
        {
            // Print JSON String to JSON File
            JSONExport export_json = new JSONExport();
            export_json.export(jsonStringBuilder, aggregateType);
        }
        else
        {
            System.out.println("unknown File Type - using json now");
            JSONExport export_json = new JSONExport();
            export_json.export(jsonStringBuilder, aggregateType);
        }
    }

}
