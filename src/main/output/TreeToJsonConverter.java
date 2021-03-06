package main.output;

import java.util.Iterator;

import main.tree.Channel;
import main.tree.Node;
import main.tree.Root;
import main.tree.Satellite;
import main.tree.Transponder;
import main.visitors.BaseVisitor;

/**
 *	Converts a tree to a JSON String equivalent
 *
 *  @author Lang
 *  @version 1.0
 */
public class TreeToJsonConverter extends BaseVisitor<StringBuilder> {
	
	/**
	 * Converts a whole node to JSON by using a StringBuilder
	 * 
	 * @param Node to convert
	 * @return StringBuilder object with the converted JSON String
	 */
	private StringBuilder convertNodeToJson(Node node) {
		StringBuilder stringBuilder = new StringBuilder(node.toJson());
		if(!node.children().isEmpty()) {
			stringBuilder.append(",\n\"" + node.children().get(0).getClass().getSimpleName().toLowerCase() + "s\": [\n");
			convertAllChildrenToJson(stringBuilder, node);
			stringBuilder.append("]\n");
		}
		return stringBuilder;
	}
	
	/**
	 * Converts all children of a node to JSON
	 * 
	 * @param A string builder that is being used to convert Node itself
	 * @param The Node which children it have to be converted
	 */
	private void convertAllChildrenToJson(StringBuilder stringBuilder, Node node) {
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
	}
	
	@Override
	public StringBuilder visitRoot(Root r) {
		StringBuilder stringBuilder = new StringBuilder("[\n");
		if(!r.children().isEmpty()) {
			convertAllChildrenToJson(stringBuilder, r);
		}
		stringBuilder.append("]\n");
		return stringBuilder;
	}
	
	@Override
	public StringBuilder visitSatellite(Satellite s) {
		return convertNodeToJson(s);
	}

	@Override
	public StringBuilder visitTransponder(Transponder t) {
		return convertNodeToJson(t);
	}

	@Override
	public StringBuilder visitChannel(Channel c) {
		return convertNodeToJson(c);
	}
}
