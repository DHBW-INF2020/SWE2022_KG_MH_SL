package tree;

import visitors.Visitor;

public class Satellite extends Node{
    
    String sat;
    String orbital;
    
	@Override
	public <T> T accept(Visitor<T> v) {
		v.visitSatellite(this);
		return null;
	}

}
