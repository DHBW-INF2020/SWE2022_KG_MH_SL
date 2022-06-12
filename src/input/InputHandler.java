package input;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class InputHandler {
    public InputHandler(){};

    public ProgramConfiguration readInput(String[] Args)
    {
        ProgramConfiguration currentConfiguration = null;
        if(Args.length == 0)
        {
            currentConfiguration = createProgramConfiguration("./input/program_configuration.json");
        }

        else if (Args.length == 1) {
            currentConfiguration = createProgramConfiguration(Args[0]);
        }

        else
        {
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
