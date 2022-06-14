package test;

import org.junit.jupiter.api.Test;

import main.input.JsonToTreeConverter;
import main.tree.Root;

public class JsonToTeeConverterTest {
    
    @Test
    public boolean readInputJson(){
        JsonToTreeConverter inputConverter = new JsonToTreeConverter();
        Root returnTree = (Root) inputConverter.readInputJson("./src/test/resources/TestInput.json");
        if (returnTree != null){return true;}
        else{return false;}

    }

}