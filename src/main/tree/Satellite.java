package main.tree;

import main.visitors.IVisitor;

/**
 * A node in the tree representing the
 * attributes of the Satellite
 */
public class Satellite extends Node{

	private String sat;
    private String orbital;
    
    /**
     * @param Name of satellite
     * @param Name of orbital
     */
    public Satellite(String sat, String orbital) {
    	this.sat = replaceQutationMark(sat);
    	this.orbital = replaceQutationMark(orbital);
    }
    
    /**
     * @param Sattelite node
     */
    public Satellite(Satellite s) {
    	this.sat = s.sat;
    	this.orbital = s.orbital;
    }

	/**
	 * @return Satellite name
	 */
	public String getSat() {
		return sat;
	}

	/**
	 * @return Orbital name
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
