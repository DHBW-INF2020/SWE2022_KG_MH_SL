package tree;

import visitors.Visitor;

public class Transponder extends Node {
    
    String pol;
    String freq;
    String sym;
    
	@Override
	public <T> T accept(Visitor<T> v) {
		v.visitTransponder(this);
		return null;
	}
}
