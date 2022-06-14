package main.visitors;

import main.tree.Channel;
import main.tree.Node;
import main.tree.Root;
import main.tree.Satellite;
import main.tree.Transponder;

/**
 * This class implements standard behavior for
 * a visitor. If one of these methods is not
 * overwritten (used) by the child class the
 * BaseVisitor will automatically continue
 * traversing to the next element in the tree.
 *
 * @param <T>
 */
public class BaseVisitor<T> implements IVisitor{

	@Override
	public T visitRoot(Root r) {
		return visitChildren(r);
	}
	
	@Override
	public T visitSatellite(Satellite s) {
		return visitChildren(s);
	}

	@Override
	public T visitTransponder(Transponder t) {
		return visitChildren(t);
	}

	@Override
	public T visitChannel(Channel c) {
		return visitChildren(c);
	}
	
	private T visitChildren(Node n) {
	    T r = null;
	    for (Node c : n.children()) {
	        r = (T) c.accept(this);
	    }
	    return r;
	}
}
