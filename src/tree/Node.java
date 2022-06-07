package tree;

import visitors.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Node {
    
    protected List<Node> children;
	
	public abstract String toJson();
	public abstract <T> T accept(IVisitor<T> v);
	
	public Node() {
		children = new ArrayList<>();
	}
	
	public Node(Node child) {
		children = new ArrayList<>();
		children.add(child);
	}
	
	public Node(List<Node> children) {
		this.children = children;
	}
	
	public void addNode(Node child) {
		children.add(child);
	}

	public List<Node> children() {
		if(children != null) {
			return children;
		}
		return Collections.emptyList();
	}
	
	protected String replaceQutationMark(String text) {
		return text.replace("\"", "\\\"");
	}
}
