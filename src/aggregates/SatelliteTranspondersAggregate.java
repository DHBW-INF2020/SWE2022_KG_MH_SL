package aggregates;

import tree.Node;
import tree.Root;
import tree.Satellite;
import tree.Transponder;
import visitors.BaseVisitor;

public class SatelliteTranspondersAggregate extends BaseVisitor<Node>{
	
	@Override
	public Node visitRoot(Root r) {
		System.out.println("Root");
		Root root = new Root();
		for(Node n : r.children()) {
			root.addNode((Node) n.accept(this));
		}
		return root;
	}
	
	@Override
	public Node visitSatellite(Satellite s) {
		System.out.println("Satellite");
		Satellite satellite = new Satellite(s);
		for(Node n : s.children()) {
			satellite.addNode((Node) n.accept(this));
		}
		return satellite;
	}

	@Override
	public Node visitTransponder(Transponder t) {
		System.out.println("Transponder");
		return new Transponder(t);
	}
}
