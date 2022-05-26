package tree;

import visitors.Visitor;

public class Channel extends Node{
    
    String name;

	@Override
	public <T> T accept(Visitor<T> v) {
		v.visitChannel(this);
		return null;
	}

}
