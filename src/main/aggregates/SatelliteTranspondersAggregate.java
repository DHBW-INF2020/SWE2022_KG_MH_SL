package main.aggregates;

import main.tree.Node;
import main.tree.Root;
import main.tree.Satellite;
import main.tree.Transponder;
import main.visitors.BaseVisitor;

/**
 *	Aggregates Transponders under the individual Satellites by
 *	traversing through the tree based on a visitor pattern
 */
public class SatelliteTranspondersAggregate extends BaseVisitor<Node>{
	
	@Override
	public Node visitRoot(Root r) {
		Root root = new Root();
		for(Node n : r.children()) {
			root.addNode((Node) n.accept(this));
		}
		return root;
	}
	
	@Override
	public Node visitSatellite(Satellite s) {
		Satellite satellite = new Satellite(s);
		for(Node n : s.children()) {
			satellite.addNode((Node) n.accept(this));
		}
		return satellite;
	}

	@Override
	public Node visitTransponder(Transponder t) {
		return new Transponder(t);
	}
}
