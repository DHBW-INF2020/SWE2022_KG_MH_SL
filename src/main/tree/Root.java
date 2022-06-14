package main.tree;

import main.visitors.IVisitor;

/**
 *
 */
public class Root extends Node{

	@Override
	public String toJson() {
		return "";
	}

	@Override
	public <T> T accept(IVisitor<T> v) {
		return v.visitRoot(this);
	}
}
