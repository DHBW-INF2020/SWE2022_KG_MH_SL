package main.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.visitors.*;

/**
 *
 */
public abstract class Node {
    
    protected List<Node> children;
	
	public abstract String toJson();
	public abstract <T> T accept(IVisitor<T> v);
	
	/**
	 * 
	 */
	public Node() {
		children = new ArrayList<>();
	}
	
	/**
	 * @param child
	 */
	public Node(Node child) {
		children = new ArrayList<>();
		children.add(child);
	}
	
	/**
	 * @param children
	 */
	public Node(List<Node> children) {
		this.children = children;
	}
	
	/**
	 * @param child
	 */
	public void addNode(Node child) {
		children.add(child);
	}

	/**
	 * @return
	 */
	public List<Node> children() {
		if(children != null) {
			return children;
		}
		return Collections.emptyList();
	}
	
	/**
	 * @param text
	 * @return
	 */
	protected String replaceQutationMark(String text) {
		return text.replace("\"", "\\\"");
	}
}
