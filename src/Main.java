import java.util.ArrayList;

import com.google.gson.Gson;

import aggregates.ChannelSatellitesAggregate;
import aggregates.SatelliteTranspondersAggregate;
import tree.*;
import output.JSONExport;
import output.XMLExport;
import output.TreeToJsonConverter;

import java.io.Reader;
import org.json.JSONException;
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.event.TreeExpansionEvent;

/**
 *
 */
public class Main {
	
	//public static Root createTree() {};
	
    /**
     * 
     */
    public static void readInputJson(){
        // Dummie Class "transportAndSatellite" is needed for Gson to parse the json to the right structure
        Gson gson = new Gson();

        try {
        	Reader reader = new FileReader("./res/Aufgabe_3_satellites.json");
            // Parse the JSON
            TransponderAndSatellite[] sat = gson.fromJson(reader, TransponderAndSatellite[].class);

            // Create a List for all the previously created Satellites
            ArrayList<Satellite> existingSatList= new ArrayList<Satellite>();

            // create helpers
            int positionOfCurrentSat = -1;

            // loop over all Transponder-Satellite-Combinations in the Input
            for (TransponderAndSatellite transportAndSatellite : sat) {
                // loop over the already created Satellite Nodes -> existingSatList
                for (int i = 0; i < existingSatList.size(); i++) {
                    // if the Sat is already created set "positionOfCurrentSat" to the Position in "existingSatList"
                    if (Objects.equals(transportAndSatellite.getSat_name(), existingSatList.get(i).getSat())) {
                        positionOfCurrentSat = i;
                        // Terminate the loop over Sat List
                        i = existingSatList.size();
                    }

                    else {
                        positionOfCurrentSat = -1;
                    }
                }

                // if sat doesnt exist create and append new sat to "existingSatList"
                if (positionOfCurrentSat == -1) {
                    existingSatList.add(new Satellite(transportAndSatellite.getSat_name(), transportAndSatellite.getOrbital()));
                    positionOfCurrentSat = existingSatList.size() - 1;
                }

                // create the Transponder at current Transponder-Satellite-Combinations
                Transponder new_transponder = new Transponder(transportAndSatellite.getPol(), transportAndSatellite.getFreq(), transportAndSatellite.getSym());
                // create Channellist to iterate over it
                ArrayList<Channel> channel_list = transportAndSatellite.getChannels();

                // add channels to new_transponder
                for (Channel channel : channel_list) {
                    Channel new_channel = new Channel(channel.getName());
                    new_transponder.addNode(new_channel);
                }
                // append current Transponder to matching Sat in existingSatList
                existingSatList.get(positionOfCurrentSat).addNode(new_transponder);
            }

            // create Tree Root
            Root root = new Root();

            // append Sats to Root
            for (Satellite satellite : existingSatList) {
                root.addNode(satellite);
            }

//            SatelliteTranspondersAggregate sta = new SatelliteTranspondersAggregate();
//            Root returnTree = (Root) root.accept(sta);
            
            ChannelSatellitesAggregate csa = new ChannelSatellitesAggregate();
            Root returnTree = (Root) root.accept(csa);
            
            TreeToJsonConverter converter = new TreeToJsonConverter();
            StringBuilder jsonStringBuilder = (StringBuilder) returnTree.accept(converter);
//            StringBuilder jsonStringBuilder = (StringBuilder) root.accept(converter);
            
            JSONExport export_json = new JSONExport();
            export_json.export(jsonStringBuilder);
            XMLExport export_xml = new XMLExport();
            export_xml.export(jsonStringBuilder);


        }catch (JSONException exception){
            exception.printStackTrace();
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }
        

    //--------------------------- main(Sting[]Args) -----------------------

    /**
     * @param Args
     */
    public static void main(String[]Args){
    	//main.testAggregate();
        // es sind 3031 Transpoder - SatKombinationen
        // 136 Satelliten
        Main.readInputJson();
    }
}



