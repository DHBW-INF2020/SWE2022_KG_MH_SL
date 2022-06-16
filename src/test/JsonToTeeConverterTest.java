package test;

import org.junit.jupiter.api.Test;

import main.input.JsonToTreeConverter;
import main.tree.Root;

/**
 * Class with unittests for JsonToTreeConverter class
 */
public class JsonToTeeConverterTest {
    
    @Test
    /**
     * This Method will perform the unittests
     * @author Lang
     * @return True if test passed
     */
    public boolean readInputJson(){
        JsonToTreeConverter inputConverter = new JsonToTreeConverter();

        //create Tree
        Root returnTree = (Root) inputConverter.readInputJson("./src/test/resources/TestInput.json");

        if (returnTree != null){return true;}
        else{return false;}

    }

}