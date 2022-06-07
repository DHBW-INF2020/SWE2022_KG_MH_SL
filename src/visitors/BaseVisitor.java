package visitors;

import tree.Channel;
import tree.Node;
import tree.Root;
import tree.Satellite;
import tree.Transponder;

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
	
	/**
	 * @param n
	 * @return
	 */
	private T visitChildren(Node n) {
	    T r = null;
	    for (Node c : n.children()) {
	        r = (T) c.accept(this);
	    }
	    return r;
	}
}
