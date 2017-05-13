package hu.adatb.jetr.model;

public class FelvettKurzusBean {
	private String hallgato;
	private String kurzuskod;
	private int erdemjegy;

	public FelvettKurzusBean(String hallgato, String kurzuskod, int erdemjegy) {
		super();
		this.hallgato = hallgato;
		this.kurzuskod = kurzuskod;
		this.erdemjegy = erdemjegy;
	}

	public String getHallgato() {
		return hallgato;
	}

	public void setHallgato(String hallgato) {
		this.hallgato = hallgato;
	}

	public String getKurzuskod() {
		return kurzuskod;
	}

	public void setKurzuskod(String kurzuskod) {
		this.kurzuskod = kurzuskod;
	}

	public int getErdemjegy() {
		return erdemjegy;
	}

	public void setErdemjegy(int erdemjegy) {
		this.erdemjegy = erdemjegy;
	}

}
