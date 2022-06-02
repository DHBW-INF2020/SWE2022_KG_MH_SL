package tree;

import visitors.Visitor;

public class Channel extends Node{

	private String name;
    
    public Channel(String name) {
    	this.name = name;
    }
    
    public Channel(Channel c) {
    	this.name = c.name;
    }

	public String getName() {return name;}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visitChannel(this);
	}

}
