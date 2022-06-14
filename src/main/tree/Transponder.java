package main.tree;

import main.visitors.IVisitor;

/**
 *
 */
public class Transponder extends Node {
    
    private String pol;
    private String freq;
    private String sym;
    
    /**
     * @param pol
     * @param freq
     * @param sym
     */
    public Transponder(String pol, String freq, String sym) {
    	this.pol = replaceQutationMark(pol);
    	this.freq = replaceQutationMark(freq);
    	this.sym = replaceQutationMark(sym);
    }
    
    /**
     * @param t
     */
    public Transponder(Transponder t) {
    	this.pol = t.pol;
    	this.freq = t.freq;
    	this.sym = t.sym;
    }

	/**
	 * @return
	 */
	public String getPol() {
		return pol;
	}

	/**
	 * @return
	 */
	public String getFreq() {
		return freq;
	}

	/**
	 * @return
	 */
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
