package input;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class InputHandler {
    public InputHandler(){};

    public ProgramConfiguration readInput(String[] Args) throws Exception {
        ProgramConfiguration currentConfiguration = null;

        // default config
        if(Args.length == 0)
        {
            currentConfiguration = createProgramConfiguration("./input/default_configuration.json");
            System.out.println("no flags found - using default config ./input/default_configuration.json");
        }

        // config file
        else if (Args.length == 1) {
            System.out.println("debug - else if readInput");

            currentConfiguration = createProgramConfiguration(Args[0]);
        }

        // config with flags
        else
        {
            System.out.println("debug - else read input");
            currentConfiguration = new ProgramConfiguration(Args);
        }
        return currentConfiguration;
    }

    /**
     * This is to prevent Code duplication
     *
     *
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
