/*
 * Copyright (c) 02/07/2020 19:25 - Jean-Charles Régin - All Rights Reserved
 * You are not allowed to distribute and/or modify this code, which is considered as a trade secret.
 * You are not allowed to reuse any idea of this code. Algorithms are the property of the owner of the code.
 * Reproducing, even with modifications is not allowed without the permission of the author
 *
 */

package C2;

public class StopwatchAccessor {
	protected long start = -1;
	protected long cumul=0;
    protected long numCumuls=0;
    protected long last=0;

	public final long readElapsedNanoSeconds(){ return System.nanoTime()-start; }
    public final long lastElapsedNanoSeconds() { return last; }
    public final long lastElapsedMilliSeconds() { return (last)/1000000; }
    public final long elapsedNanoSeconds() { return cumul; }
	public final long elapsedMilliSeconds() { return (cumul)/1000000; }
	public final double millionOperationPerSecond(long numOp) { return (numOp / (double)(elapsedNanoSeconds())) * 1000.; }
	public final long numberOfAccumulations(){return numCumuls;}

	static public long computeGranularityNanoSeconds(){
		long cur;
		final long last=System.nanoTime();
		do {
			cur = System.nanoTime();
		} while (cur == last);
		return cur-last;
	}
}
