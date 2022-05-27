package tree;

import visitors.Visitor;

public class Satellite extends Node{
    
    private String sat;
    private String orbital;
    
    public Satellite(String sat, String orbital) {
    	this.sat = sat;
    	this.orbital = orbital;
    }
    
	@Override
	public <T> T accept(Visitor<T> v) {
		v.visitSatellite(this);
		return null;
	}

}
