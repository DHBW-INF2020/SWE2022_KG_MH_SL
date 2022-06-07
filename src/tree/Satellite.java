package tree;

import visitors.IVisitor;

/**
 *
 */
public class Satellite extends Node{

	private String sat;
    private String orbital;
    
    /**
     * @param sat
     * @param orbital
     */
    public Satellite(String sat, String orbital) {
    	this.sat = replaceQutationMark(sat);
    	this.orbital = replaceQutationMark(orbital);
    }
    
    /**
     * @param s
     */
    public Satellite(Satellite s) {
    	this.sat = s.sat;
    	this.orbital = s.orbital;
    }

	/**
	 * @return
	 */
	public String getSat() {
		return sat;
	}

	/**
	 * @return
	 */
	public String getOrbital() {
		return orbital;
	}

	@Override
	public String toJson() {
		return String.format("\"sat\": \"%s\",\n\"orbital\": \"%s\"", sat, orbital);
	}
    
	@Override
	public <T> T accept(IVisitor<T> v) {
		return v.visitSatellite(this);
	}

}
