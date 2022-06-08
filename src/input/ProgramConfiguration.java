package input;

public class ProgramConfiguration {

    private String[] programFlags;
    private String inputFilePath;
    private String outputFilePath;
    private String aggregateType;
    private String outputFileType;

    public ProgramConfiguration(){}

    // Getter Setter for ProgramFlags[]

    public String[] getProgramFlags() {
        return programFlags;
    }

    public void setProgramFlags() {
        this.programFlags = programFlags;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public String getOutputFileType() {
        return outputFileType;
    }

    public void setOutputFileType(String outputFileType) {
        this.outputFileType = outputFileType;
    }
}
