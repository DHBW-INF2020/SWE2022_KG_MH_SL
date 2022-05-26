package visitors;

import tree.*;

public interface Visitor<T> {
	T visitSatellite(Satellite s);
	T visitTransponder(Transponder t);
	T visitChannel(Channel c);
}
