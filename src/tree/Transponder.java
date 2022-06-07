package tree;

import visitors.IVisitor;

public class Transponder extends Node {
    
    private String pol;
    private String freq;
    private String sym;
    
    public Transponder(String pol, String freq, String sym) {
    	this.pol = replaceQutationMark(pol);
    	this.freq = replaceQutationMark(freq);
    	this.sym = replaceQutationMark(sym);
    }
    
    public Transponder(Transponder t) {
    	this.pol = t.pol;
    	this.freq = t.freq;
    	this.sym = t.sym;
    }

	public String getPol() {
		return pol;
	}

	public String getFreq() {
		return freq;
	}

	public String getSym() {
		return sym;
	}

	@Override
	public String toJson() {
		return String.format("\"pol\": \"%s\",\n\"freq\": \"%s\",\n\"sym\": \"%s\"", pol, freq, sym);
	}
    
	@Override
	public <T> T accept(IVisitor<T> v) {
		return v.visitTransponder(this);
	}
}
