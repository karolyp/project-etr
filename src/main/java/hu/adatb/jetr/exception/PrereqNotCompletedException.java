package hu.adatb.jetr.exception;

public class PrereqNotCompletedException extends Exception {

	public PrereqNotCompletedException(String kurzuskod, String elofeltetel) {
		super("A " + kurzuskod + " kurzuskódú kurzus felvételéhez nincs teljesítve a " + elofeltetel + " kódú kurzus");
	}

}
