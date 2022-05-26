package tree;

import visitors.Visitor;

public class Root extends Node{

	@Override
	public <T> T accept(Visitor<T> v) {
	    for (Node c : n.children()) {
	        r = (T) c.accept(this);
	    }
	}

}
