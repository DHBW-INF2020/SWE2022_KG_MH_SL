package tree;

import visitors.Visitor;

public class Transponder extends Node {
    
    private String pol;
    private String freq;
    private String sym;
    
    public Transponder(String pol, String freq, String sym) {
    	this.pol = pol;
    	this.freq = freq;
    	this.sym = sym;
    }
    
	@Override
	public <T> T accept(Visitor<T> v) {
		v.visitTransponder(this);
		return null;
	}
}
