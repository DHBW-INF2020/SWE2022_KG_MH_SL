package main.tree;

import main.visitors.IVisitor;

/**
 * A node in the tree representing the
 * attributes of the Transponder
 *
 * @author Gavagan
 * @version 1.0
 */
public class Transponder extends Node {
    
    private String pol;
    private String freq;
    private String sym;
    
    /**
     * @param Value of polarization
     * @param Value of frequency
     * @param Value of symmetry
     */
    public Transponder(String pol, String freq, String sym) {
    	this.pol = replaceQutationMark(pol);
    	this.freq = replaceQutationMark(freq);
    	this.sym = replaceQutationMark(sym);
    }
    
    /**
     * @param Transonder object to copy
     */
    public Transponder(Transponder t) {
    	this.pol = t.pol;
    	this.freq = t.freq;
    	this.sym = t.sym;
    }

	/**
	 * @return Value of polarization
	 */
	public String getPol() {
		return pol;
	}

	/**
	 * @return Value of frequency
	 */
	public String getFreq() {
		return freq;
	}

	/**
	 * @return Value of symmetry
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
