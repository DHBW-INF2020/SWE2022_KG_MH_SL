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


public class JSONExportTest {

    @Test
    public boolean export() throws IOException
    {
        
        JsonToTreeConverter inputConverter = new JsonToTreeConverter();
        JSONExport export_json = new JSONExport();

        //
        // Test 1
        //
        String aggregateType = "csa";
        BaseVisitor aggregate = new ChannelSatellitesAggregate();

        Root returnTree = (Root) inputConverter.readInputJson("./src/test/resources/TestInput.json").accept(aggregate);

        TreeToJsonConverter outputConverter = new TreeToJsonConverter();
        StringBuilder jsonStringBuilder = (StringBuilder) returnTree.accept(outputConverter);

        export_json.export(jsonStringBuilder, aggregateType);

        // Check output
        File expectedOutput = new File("./src/test/resources/TestOutputCSA.json");
        File actualOutput = new File("./output/output.json");
        byte[] expected = Files.readAllBytes(expectedOutput.toPath());
        byte[] actual = Files.readAllBytes(actualOutput.toPath());
        boolean testResult1 = Arrays.equals(expected, actual);


        //
        // Test 2
        //
        aggregate = new SatelliteTranspondersAggregate();

        returnTree = (Root) inputConverter.readInputJson("./src/test/resources/TestInput.json").accept(aggregate);

        outputConverter = new TreeToJsonConverter();
        jsonStringBuilder = (StringBuilder) returnTree.accept(outputConverter);

        aggregateType = "sta";
        export_json.export(jsonStringBuilder, aggregateType);
        expectedOutput = new File("./src/test/resources/TestOutputSTA.json");
        actualOutput = new File("./output/output.json");
        expected = Files.readAllBytes(expectedOutput.toPath());
        actual = Files.readAllBytes(actualOutput.toPath());
        boolean testResult2 = Arrays.equals(expected, actual);

        if (testResult1 == true && testResult2 == true){return true;}
        else{return false;}
    }
}
