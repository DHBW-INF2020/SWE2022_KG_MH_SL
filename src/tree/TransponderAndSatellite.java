package tree;

import java.util.ArrayList;

/**
 * THis class represents the Object Structure of Aufgabe_3_satellites
 *
 * This helper-class is needed by gson to correctly parse the Input JSON
 */
public class TransponderAndSatellite {
	private String sat;
    private String orbital;
    private String pol;
    private String freq;
    private String sym;
    private ArrayList<tree.Channel> channels;

    /**
     * @return sat -> returns the name of the Satellite
     */
    public String getSat_name() {return sat;}
    /**
     * @return orbital -> returns the orbital position of the sat
     */
    public String getOrbital() {return orbital;}
    /**
     * @return pol -> returns the polarization of a Transponder
     */
    public String getPol() {return pol;}
    /**
     * @return freq -> returns the frequency of a Transponder
     */
    public String getFreq() {return freq;}
    /**
     * @return sym ->returns sym
     */
    public String getSym() {return sym;}
    /**
     * @return channels -> returns an ArrayList<> of channels of the satellite and transponder combo
     *
     */
    public ArrayList<Channel> getChannels() {return channels;}
}
