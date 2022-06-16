package main.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.visitors.*;

/**
 * The base class of the tree 
 */
public abstract class Node {
    
    protected List<Node> children;
	
    /**
     * The given node will print its information
     * in a json format
     * 
     * @return String formatted in JSON
     */
	public abstract String toJson();
	
	/**
	 * Accepts the given visitor and calls the
	 * corresponding visit function
	 * @param <T>
	 * @param v
	 * @return
	 */
	public abstract <T> T accept(IVisitor<T> v);

	public Node() {
		children = new ArrayList<>();
	}
	
	/**
	 * @param A node to add
	 */
	public Node(Node child) {
		children = new ArrayList<>();
		children.add(child);
	}
	
	/**
	 * @param A list of nodes to add
	 */
	public Node(List<Node> children) {
		this.children = children;
	}
	
	/**
	 * Adds a node to the tree
	 * 
	 * @param Child node to add
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
	 * Replaces quotations marks within the string
	 * with \" (escaped quotation mark)
	 * 
	 * @param Text to be refactored
	 * @return Refactored text
	 */
	protected String replaceQutationMark(String text) {
		return text.replace("\"", "\\\"");
	}
}
