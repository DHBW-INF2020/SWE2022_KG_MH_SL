package main.tree;

import main.visitors.IVisitor;

/**
 * A node in the tree representing the Root of the Tree
 *
 * @author Gavagan
 * @version 1.0
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
