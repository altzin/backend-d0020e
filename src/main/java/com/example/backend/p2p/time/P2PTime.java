

package com.example.backend.p2p.time;

public class P2PTime {

	private ExponentialRandomStream nodeSendFile;
	private UniformRandomStream sendToNodesGenerator, mAPGenerator;

	/**
	 * Construct a new Time Object
	 * 
	 * @param lambda
	 * @param seed
	 */

	public P2PTime(double lambda, long seed, int nrOfNodes) {
		this.nodeSendFile = new ExponentialRandomStream(lambda, seed);
		this.sendToNodesGenerator = new UniformRandomStream(0, nrOfNodes, seed);
		this.mAPGenerator = new UniformRandomStream(-0.2, 0.2, seed);
	}

	/**
	 * Returns time for next
	 *
	 * @Return time for next
	 */
	public double timeSendNextFile() {
		return 0.1;
		//return nodeSendFile.next();
	}

	public int nrSendToNodes(int i) {
		return sendToNodesGenerator.nextInt(i);
	}
	public double nextMap(){
		return mAPGenerator.next();
	}
}
