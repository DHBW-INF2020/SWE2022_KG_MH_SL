package visitors;

import tree.Channel;
import tree.Node;
import tree.Satellite;
import tree.Transponder;

public class BaseVisitor<T> implements Visitor{

	@Override
	public Object visitSatellite(Satellite s) {
		return visitChildren(s);
	}

	@Override
	public Object visitTransponder(Transponder t) {
		return visitChildren(t);
	}

	@Override
	public Object visitChannel(Channel c) {
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
