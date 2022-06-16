package main.input;

import com.google.gson.Gson;

import main.tree.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Hartmann
 * @version 1.0
 */
public class JsonToTreeConverter {

    /**
     * This Method use GSON to parse the JSON behind "filename" and create the according Tree Structure
     *
     * The mixed up structure (Satellites and Transponders combined in an Object) will be corrected in this Method
     *
     * @param filename the Path to the JSON containing the Satellites, Transponders and Channels
     * @return Root the Root of the created Tree
     */
    public Root readInputJson(String filename)
    {
        // Dummie Class "transportAndSatellite" is needed for Gson to parse the json to the right structure
        Gson gson = new Gson();

        try {
            Reader reader = new FileReader(filename);
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
            return root;
        }
        catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
