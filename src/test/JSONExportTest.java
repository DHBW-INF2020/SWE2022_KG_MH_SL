package test;

import org.junit.jupiter.api.Test;

import main.aggregates.ChannelSatellitesAggregate;
import main.aggregates.SatelliteTranspondersAggregate;
import main.input.JsonToTreeConverter;
import main.output.JSONExport;
import main.output.TreeToJsonConverter;
import main.tree.Root;
import main.visitors.BaseVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * Class with unittests for JSONExport class
 */
public class JSONExportTest {

    @Test
    /**
     * This Method will perform the unittests
     * @author Lang
     * @return True if test passed
     */
    public boolean export() throws IOException
    {
        
        JsonToTreeConverter inputConverter = new JsonToTreeConverter();
        JSONExport export_json = new JSONExport();

        //
        // Test 1
        //

        // Set aggregateType
        String aggregateType = "csa";

        // Set aggregate
        BaseVisitor aggregate = new ChannelSatellitesAggregate();

        // convert the Tree that was created from the Input Json to an aggregated Tree
        Root returnTree = (Root) inputConverter.readInputJson("./src/test/resources/TestInput.json").accept(aggregate);

        // convert the converted Tree to a JSON String
        TreeToJsonConverter outputConverter = new TreeToJsonConverter();
        StringBuilder jsonStringBuilder = (StringBuilder) returnTree.accept(outputConverter);

        // export as jsonfile
        export_json.export(jsonStringBuilder, aggregateType);

        // Set output filepaths
        File expectedOutput = new File("./src/test/resources/TestOutputCSA.json");
        File actualOutput = new File("./output/output.json");

        // Convert to ByteArray
        byte[] expected = Files.readAllBytes(expectedOutput.toPath());
        byte[] actual = Files.readAllBytes(actualOutput.toPath());

        // Check if both arrays are equal
        boolean testResult1 = Arrays.equals(expected, actual);


        //
        // Test 2
        //

        // Set aggregate
        aggregate = new SatelliteTranspondersAggregate();

        // Set aggregateType
        aggregateType = "sta";

        // convert the Tree that was created from the Input Json to an aggregated Tree
        returnTree = (Root) inputConverter.readInputJson("./src/test/resources/TestInput.json").accept(aggregate);

        // convert the converted Tree to a JSON String
        outputConverter = new TreeToJsonConverter();
        jsonStringBuilder = (StringBuilder) returnTree.accept(outputConverter);

        // export as jsonfile
        export_json.export(jsonStringBuilder, aggregateType);

        // Set output filepaths
        expectedOutput = new File("./src/test/resources/TestOutputSTA.json");
        actualOutput = new File("./output/output.json");

        // Convert to ByteArray
        expected = Files.readAllBytes(expectedOutput.toPath());
        actual = Files.readAllBytes(actualOutput.toPath());

        // Check if both arrays are equal
        boolean testResult2 = Arrays.equals(expected, actual);

        if (testResult1 == true && testResult2 == true){return true;}
        else{return false;}
    }
}
