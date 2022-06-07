package tree;

import java.util.ArrayList;

/**
 *
 */
public class TransponderAndSatellite {
	private String sat;
    private String orbital;
    private String pol;
    private String freq;
    private String sym;
    private ArrayList<tree.Channel> channels;

    /**
     * @return
     */
    public String getSat_name() {return sat;}
    /**
     * @return
     */
    public String getOrbital() {return orbital;}
    /**
     * @return
     */
    public String getPol() {return pol;}
    /**
     * @return
     */
    public String getFreq() {return freq;}
    /**
     * @return
     */
    public String getSym() {return sym;}
    /**
     * @return
     */
    public ArrayList<Channel> getChannels() {return channels;}
}
