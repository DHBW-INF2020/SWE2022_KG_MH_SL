package tree;

import visitors.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Node {
    
    ArrayList<Node> children;
	private boolean logEnabled = false;
	
	public abstract <T> T accept(Visitor<T> v);

	public void log(String msg) {
		if (logEnabled) {
			System.out.println(msg);
		}
	}

	protected List<Node> children() {
		return Collections.emptyList();
	}
}
