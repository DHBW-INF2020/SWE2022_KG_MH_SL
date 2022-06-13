package aggregates;

import java.util.ArrayList;
import java.util.List;

import tree.Channel;
import tree.Node;
import tree.Root;
import tree.Satellite;
import visitors.BaseVisitor;

/**
 *	Aggregates Sattelites under the individual Channels by
 *	traversing through the tree based on a visitor pattern
 */
public class ChannelSatellitesAggregate extends BaseVisitor<Node>{

	private List<Channel> channelList = new ArrayList<>();
	private Satellite currentSatellite;

	@Override
	public Node visitRoot(Root r) {
		super.visitRoot(r);
		Root root = new Root();
		for(Channel channel : channelList) {
			root.addNode(channel);
		}
		return root;
	}
	
	@Override
	public Node visitSatellite(Satellite s) {
		currentSatellite = s;
		super.visitSatellite(s);
		return null;
	}

	@Override
	public Node visitChannel(Channel c) {
		Channel existingChannel = doesChannelExist(c);
		if(existingChannel == null) {
			Channel newChannel = new Channel(c);
			newChannel.addNode(new Satellite(currentSatellite));
			channelList.add(newChannel);
		}else {
			existingChannel.addNode(new Satellite(currentSatellite));
		}
		return null;
	}
	
	/**
	 * Checks to see if channel already exists in the
	 * global list.
	 * 
	 * @param c to search for
	 * @return Returns a channel if one has been found
	 */
	private Channel doesChannelExist(Channel c) {
		for(Channel channel : channelList) {
			if(channel.isEqual(c)) return channel;
		}
		return null;
	}
}
