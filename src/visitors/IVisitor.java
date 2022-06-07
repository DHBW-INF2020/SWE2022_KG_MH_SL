package visitors;

import tree.*;

/**
 *
 * @param <T>
 */
public interface IVisitor<T> {
	/**
	 * @param r
	 * @return
	 */
	T visitRoot(Root r);
	/**
	 * @param s
	 * @return
	 */
	T visitSatellite(Satellite s);
	/**
	 * @param t
	 * @return
	 */
	T visitTransponder(Transponder t);
	/**
	 * @param c
	 * @return
	 */
	T visitChannel(Channel c);
}
