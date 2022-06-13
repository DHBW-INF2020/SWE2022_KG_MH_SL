package input;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * The InputHandler is dealing with different numbers of Arguments from the Program Call
 */
public class InputHandler {
    /**
     * This Method will create a ProgramConfiguration according to the number of Arguments
     *
     * @param Args a String Array that contains the Parameters from Program startup
     * @return ProgramConfiguration the configuration for this iteration of the program
     */
    public ProgramConfiguration readInput(String[] Args) throws Exception {
        ProgramConfiguration currentConfiguration = null;

        // default config
        if(Args.length == 0)
        {
            System.out.println("no flags found - using default config ./input/default_configuration.json");
            currentConfiguration = createProgramConfiguration("./input/default_configuration.json");
        }

        // config file
        else if (Args.length == 1) {
            System.out.println("creating Configuration according to given JSON...");
            currentConfiguration = createProgramConfiguration(Args[0]);
        }

        // config with flags
        else
        {
            System.out.println("creating Configuration according to given Parameters...");
            currentConfiguration = new ProgramConfiguration(Args);
        }
        return currentConfiguration;
    }

    /**
     * This is to prevent Code duplication, as with 0 or 1 Arguments a JSON has to be parsed
     * and the according Object of ProgramConfiguration has to be created
     *
     * An Exception will be thrown, if the file doesnt exist
     *
     * @param filepath String of the Path to a JSON Config File
     * @return ProgramConfiguration The Program Configuration created from JSON
     */
    private ProgramConfiguration createProgramConfiguration(String filepath)
    {
        Gson gson = new Gson();
        ProgramConfiguration createdConfig = null;

        try {
            Reader reader = new FileReader(filepath);
            createdConfig = gson.fromJson(reader, ProgramConfiguration.class);
        }catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
        return createdConfig;
    }
}
