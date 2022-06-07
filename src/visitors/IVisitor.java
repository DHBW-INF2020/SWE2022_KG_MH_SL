package visitors;

import tree.*;

public interface IVisitor<T> {
	T visitRoot(Root r);
	T visitSatellite(Satellite s);
	T visitTransponder(Transponder t);
	T visitChannel(Channel c);
}
