package tree;

import visitors.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Node {
    
    protected List<Node> children;
	private boolean logEnabled = false;
	
	public abstract <T> T accept(Visitor<T> v);

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
	
	public void log(String msg) {
		if (logEnabled) {
			System.out.println(msg);
		}
	}

	public List<Node> children() {
		if(children != null) {
			return children;
		}
		return Collections.emptyList();
	}
}
