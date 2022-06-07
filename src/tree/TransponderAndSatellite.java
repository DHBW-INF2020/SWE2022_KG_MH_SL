package tree;

import java.util.ArrayList;

public class TransponderAndSatellite {
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
