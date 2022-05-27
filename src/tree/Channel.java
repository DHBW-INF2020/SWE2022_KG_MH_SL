package tree;

import visitors.Visitor;

public class Channel extends Node{
    
    private String name;
    
    public Channel(String name) {
    	this.name = name;
    }

	@Override
	public <T> T accept(Visitor<T> v) {
		v.visitChannel(this);
		return null;
	}

}
