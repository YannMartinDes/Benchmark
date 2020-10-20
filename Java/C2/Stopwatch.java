/*
 * Copyright (c) 02/07/2020 19:25 - Jean-Charles Régin - All Rights Reserved
 * You are not allowed to distribute and/or modify this code, which is considered as a trade secret.
 * You are not allowed to reuse any idea of this code. Algorithms are the property of the owner of the code.
 * Reproducing, even with modifications is not allowed without the permission of the author
 *
 */

package C2;

// Fonctionnement
// Start demarre le chronometre, cumul n'est pas changé
// Stop arrete le chronometre et ajoute la difference entre le stop et le dernier start à cumul
// Reset : remet le chronometre à Zero. Donc arrete tout
// Split : Provoque un stop/start immédiat
// elapsedXXX lit la valeur courante de cumul
// lastElapsedXXX lit la dervière valeur de temps

public final class Stopwatch extends StopwatchAccessor {
	public static final int START_MODE=0;
	public static final int UNSTART_MODE=1;

    public Stopwatch(int mode){
    	if (mode == START_MODE){start();}
	}
	public Stopwatch(){
    	this(UNSTART_MODE);
	}

	public void start() {
		if (start != -1){
			throw new IllegalStateException("Problem: Stopwatch successive calls of function start()");
		}
		start = System.nanoTime();
	}
	public Stopwatch stop() {
	    if (start == -1){
			throw new IllegalStateException("Problem: Stopwatch function stop() is called without a corresponding start() call");
        }
        last=(System.nanoTime() - start);
	    cumul += last;
	    start=-1;
	    numCumuls++;
	    return this;
	}
	public Stopwatch split(){
	    if (start == -1) {
            throw new IllegalStateException("Problem: Stopwatch function split() is called without a corresponding start() call");
        }
        final long time=System.nanoTime();
	    last=time -start;
        cumul += last;
        start=time;
        numCumuls++;
        return this;
    }
    public void reset(){
	    start=-1;
        last=cumul=numCumuls=0;
	}
}
