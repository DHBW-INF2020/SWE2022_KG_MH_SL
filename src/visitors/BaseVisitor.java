package visitors;

import tree.Channel;
import tree.Node;
import tree.Root;
import tree.Satellite;
import tree.Transponder;

public class BaseVisitor<T> implements Visitor{

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
