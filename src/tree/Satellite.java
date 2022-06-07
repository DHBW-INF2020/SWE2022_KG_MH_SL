package tree;

import visitors.Visitor;

public class Satellite extends Node{

	private String sat;
    private String orbital;
    
    public Satellite(String sat, String orbital) {
    	this.sat = sat;
    	this.orbital = orbital;
    }
    
    public Satellite(Satellite s) {
    	this.sat = s.sat;
    	this.orbital = s.orbital;
    }

	// Getter SatName
	public String getSat() {
		return sat;
	}
    
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitSatellite(this);
	}

}
