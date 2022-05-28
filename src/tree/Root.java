package tree;

import visitors.Visitor;

public class Root extends Node{

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitRoot(this);
	}

}
