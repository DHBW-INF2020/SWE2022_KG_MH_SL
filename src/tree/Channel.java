package tree;

import visitors.IVisitor;

public class Channel extends Node{

	private String name;
    
    public Channel(String name) {
    	this.name = replaceQutationMark(name);
    }
    
    public Channel(Channel c) {
    	this.name = c.name;
    }

	public String getName() {
		return name;
	}
	
	@Override
	public String toJson() {
		return String.format("\"name\": \"%s\"", name);
	}
	
	@Override
	public <T> T accept(IVisitor<T> v) {
		return v.visitChannel(this);
	}

}
