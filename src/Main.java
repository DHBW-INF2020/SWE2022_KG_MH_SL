import input.InputHandler;
import input.ProgramConfiguration;


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



