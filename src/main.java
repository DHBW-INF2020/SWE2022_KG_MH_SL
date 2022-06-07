import java.util.ArrayList;

import com.google.gson.Gson;

import aggregates.SatelliteTranspondersAggregate;
import tree.*;
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
    	SatelliteTranspondersAggregate sta = new SatelliteTranspondersAggregate();
    	Root root = main.buildExampleTree();
    	Root returnTree = (Root) root.accept(sta);
	}

    //--------------------------- readInputJson() -----------------------

    public static void readInputJson(){
        // Dummie Class "Trans_and_Sat" is needed for Gson to parse the json to the right structure
        Gson gson = new Gson();

        try(Reader reader = new FileReader("./res/Aufgabe_3_satellites.json")){
            // Parse the JSON
            Trans_and_Sat[] sat = gson.fromJson(reader, Trans_and_Sat[].class);

            // Create a List for all the previously created Satellites
            ArrayList<Satellite> existingSatList= new ArrayList<Satellite>();

            // create helpers
            String current_sat = sat[0].getSat_name();
            String previous_sat = sat[0].getSat_name();
            int positionOfCurrentSat = -1;

            // loop over all Transponder-Satellite-Combinations in the Input
            for (Trans_and_Sat trans_and_sat : sat) {
                // loop over the already created Satellite Nodes -> existingSatList
                for (int j = 0; j < existingSatList.size(); j++) {
                    // if the Sat is already created set "positionOfCurrentSat" to the Position in "existingSatList"
                    if (Objects.equals(trans_and_sat.getSat_name(), existingSatList.get(j).getSat())) {
                        positionOfCurrentSat = j;
                        // Terminate the loop over Sat List
                        j = existingSatList.size();
                    }

                    else {
                        positionOfCurrentSat = -1;
                    }
                }

                // if sat doesnt exist create and append new sat to "existingSatList"
                if (positionOfCurrentSat == -1) {
                    existingSatList.add(new Satellite(trans_and_sat.getSat_name(), trans_and_sat.getOrbital()));
                    positionOfCurrentSat = existingSatList.size() - 1;
                }

                // create the Transponder at current Transponder-Satellite-Combinations
                Transponder new_transponder = new Transponder(trans_and_sat.getPol(), trans_and_sat.getFreq(), trans_and_sat.getSym());
                // create Channellist to iterate over it
                ArrayList<Channel> channel_list = trans_and_sat.getChannels();

                // add channels to new_transponder
                for (Channel channel : channel_list) {
                    Channel new_channel = new Channel(channel.getName());
                    new_transponder.addNode(new_channel);
                }
                // append current Transponder to matching Sat in existingSatList
                existingSatList.get(positionOfCurrentSat).addNode(new_transponder);
            }

            SatelliteTranspondersAggregate sta = new SatelliteTranspondersAggregate();

            // create Tree Root
            Root root = new Root();

            // append Sats to Root
            for (Satellite satellite : existingSatList) {
                root.addNode(satellite);
            }

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
        // 136 Satelliten
        main.readInputJson();
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



