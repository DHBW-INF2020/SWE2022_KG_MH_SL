package tree;

import visitors.IVisitor;

public class Satellite extends Node{

	private String sat;
    private String orbital;
    
    public Satellite(String sat, String orbital) {
    	this.sat = replaceQutationMark(sat);
    	this.orbital = replaceQutationMark(orbital);
    }
    
    public Satellite(Satellite s) {
    	this.sat = s.sat;
    	this.orbital = s.orbital;
    }

	public String getSat() {
		return sat;
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
