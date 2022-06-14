package main.visitors;

import main.tree.*;

/**
 * Interface for the Visitor in order to
 * traverse through the whole tree
 *
 * @param <T>
 */
public interface IVisitor<T> {
	T visitRoot(Root r);
	T visitSatellite(Satellite s);
	T visitTransponder(Transponder t);
	T visitChannel(Channel c);
}
