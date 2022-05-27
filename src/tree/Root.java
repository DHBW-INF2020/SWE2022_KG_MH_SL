package tree;

import visitors.Visitor;

public class Root extends Node{

	
	@Override
	public <T> T accept(Visitor<T> v) {
		v.visitRoot(this);
		return null;
	}

}
