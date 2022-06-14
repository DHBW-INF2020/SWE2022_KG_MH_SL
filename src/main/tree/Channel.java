package main.tree;

import main.visitors.IVisitor;

/**
 *
 */
public class Channel extends Node{

	private String name;
    
    /**
     * @param name
     */
    public Channel(String name) {
    	this.name = replaceQutationMark(name);
    }
    
    /**
     * @param c
     */
    public Channel(Channel c) {
    	this.name = c.name;
    }

	/**
	 * @return
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
