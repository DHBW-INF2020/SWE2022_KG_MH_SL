package output;

import java.util.Collections;
import java.util.Iterator;

import tree.Channel;
import tree.Node;
import tree.Root;
import tree.Satellite;
import tree.Transponder;
import visitors.BaseVisitor;

public class TreeToJsonConverter extends BaseVisitor<StringBuilder> {
	
	public StringBuilder functionName(Node node) {
		StringBuilder stringBuilder = new StringBuilder(node.toJson());
		
		if(!node.children().isEmpty()) {
			stringBuilder.append(",\n\"" + node.children().get(0).getClass().getSimpleName() + "s\": [\n");
			
			Iterator<Node> it = node.children().iterator();
			while (it.hasNext()) {
				stringBuilder.append("{\n");
				stringBuilder.append((String) it.next().accept(this).toString());
				if(it.hasNext()) {
					stringBuilder.append("},\n");
				}else {
					stringBuilder.append("}\n");
				}
		    }
			stringBuilder.append("]\n");
		}
		return stringBuilder;
	}
	
	@Override
	public StringBuilder visitRoot(Root r) {
		StringBuilder stringBuilder = new StringBuilder("[\n");
		
		if(!r.children().isEmpty()) {
			
			Iterator<Node> it = r.children().iterator();
			while (it.hasNext()) {
				stringBuilder.append("{\n");
				stringBuilder.append((String) it.next().accept(this).toString());
				if(it.hasNext()) {
					stringBuilder.append("},\n");
				}else {
					stringBuilder.append("}\n");
				}
		    }
		}
		stringBuilder.append("]\n");
		return stringBuilder;
	}
	
	@Override
	public StringBuilder visitSatellite(Satellite s) {
		return functionName(s);
	}

	@Override
	public StringBuilder visitTransponder(Transponder t) {
		return functionName(t);
	}

	@Override
	public StringBuilder visitChannel(Channel c) {
		return functionName(c);
	}
}
