package input;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class ProgramConfiguration {

    private String[] programFlags;
    //
    private String inputFilePath;

    private String outputFilePath;
    //
    private String aggregateType;
    // j = json | x = xml
    private String outputFileType;

    public ProgramConfiguration(){

    }

    public ProgramConfiguration(String[] PLACEHOLDER)
    {

    }

    // Getter Setter for ProgramFlags[]

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
}
