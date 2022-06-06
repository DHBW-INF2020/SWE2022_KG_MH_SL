import java.util.ArrayList;

import com.google.gson.Gson;

import aggregates.SatelliteTransportsAggregate;
import tree.Channel;
import tree.Root;
import tree.Satellite;
import tree.Transponder;
import output.Export;

//----------- IO Import ----------
import java.io.Reader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Objects;

public class main {

    //--------------------------- buildExampleTree() -----------------------

	public static Root buildExampleTree() {
		Root root = new Root();
		Satellite sat = new Satellite("sat1", "orb1");
		Transponder tr1 = new Transponder("pol1", "freq1", "sym1");
		Transponder tr2 = new Transponder("pol2", "freq2", "sym2");
		Channel ch1 = new Channel("ch1");
		Channel ch2 = new Channel("ch2");
		Channel ch3 = new Channel("ch3");
		Channel ch4 = new Channel("ch4");
		tr1.addNode(ch1);
		tr1.addNode(ch2);
		tr2.addNode(ch3);
		tr2.addNode(ch4);
		sat.addNode(tr1);
		sat.addNode(tr2);
		root.addNode(sat);
		return root;
	}

    //--------------------------- testAggregate() -----------------------

	public static void testAggregate() {
    	SatelliteTransportsAggregate sta = new SatelliteTransportsAggregate();
    	Root root = main.buildExampleTree();
    	Root returnTree = (Root) root.accept(sta);
	}

    //--------------------------- openAndReadJson() -----------------------

    public static void openAndReadJson(){
        // Dummie Class "Trans_and_Sat" is needed for Gson to parse the json to the right structure
        Gson gson = new Gson();

        try(Reader reader = new FileReader("./res/Aufgabe_3_satellites.json")){
            // Parse the JSON
            Trans_and_Sat[] sat = gson.fromJson(reader, Trans_and_Sat[].class);

            // create Tree Root
            Root root = new Root();

            // create helpers
            String current_sat = sat[0].getSat_name();
            String previous_sat = sat[0].getSat_name();
            int j = 0;

            // new sat is needed for first loop
            Satellite new_sat = new Satellite(sat[j].getSat_name(),sat[j].getOrbital());
            
            // add the first new Satellite to the root Node
            root.addNode(new_sat);
            
            // do, for all satellites in given json
            do{
                // if there is a new/another sat create it and append to root
                if(!Objects.equals(current_sat, previous_sat))
                {
                    new_sat = new Satellite(sat[j].getSat_name(),sat[j].getOrbital());
                    root.addNode(new_sat);
                    previous_sat = current_sat;
                }
                // if the sat isnt new create and append the current transponder with all its Channels
                if(Objects.equals(previous_sat, current_sat))
                {
                    Transponder new_transponder = new Transponder(sat[j].getPol(),sat[j].getFreq(),sat[j].getSym());
                    // append transponder to
                    ArrayList<Channel> channel_list= sat[j].getChannels();

                    // append channel to transponder
                    for (Channel channel : channel_list) {
                        Channel new_channel = new Channel(channel.getName());
                        new_transponder.addNode(new_channel);
                    }
                    new_sat.addNode(new_transponder);
                }

                j++;
                current_sat = sat[j].getSat_name();
            }while(j+1 < sat.length);


            SatelliteTransportsAggregate sta = new SatelliteTransportsAggregate();
            Root returnTree = (Root) root.accept(sta);

            Export.as_JSON(root);

            Export.as_XML(returnTree);


        }catch (IOException exception){
            exception.printStackTrace();
        }

    }

    //--------------------------- main(Sting[]Args) -----------------------

    public static void main(String[]Args){
    	//main.testAggregate();
        // es sind 3031 Transpoder - SatKombinationen
        main.openAndReadJson();
    }
}

class Trans_and_Sat {
    private String sat;
    private String orbital;
    private String pol;
    private String freq;
    private String sym;
    private ArrayList<tree.Channel> channels;

    public String getSat_name() {return sat;}
    public String getOrbital() {return orbital;}
    public String getPol() {return pol;}
    public String getFreq() {return freq;}
    public String getSym() {return sym;}
    public ArrayList<Channel> getChannels() {return channels;}
}


// nicht verwendet
class jsonHirarchieTop{
    private Satellite sat;
    private ArrayList<jsonHirarchieBottom> TransponderList;
}

// nicht verwendet
class jsonHirarchieBottom{
    private Transponder trans;
    private ArrayList<Channel> ChannelList;
}
