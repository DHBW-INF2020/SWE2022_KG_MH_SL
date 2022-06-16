package main.tree;

import main.visitors.IVisitor;

/**
 * A node in the tree representing the
 * attributes of the channel
 */
public class Channel extends Node{

	private String name;
    
    /**
     * @param Name of channel
     */
    public Channel(String name) {
    	this.name = replaceQutationMark(name);
    }
    
    /**
     * @param Channel object to copy
     */
    public Channel(Channel c) {
    	this.name = c.name;
    }

	/**
	 * @return Name of channel
	 */
	public String getName() {
		return name;
	}
	
	public boolean isEqual(Channel c) {
		return this.name.equals(c.name);
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
