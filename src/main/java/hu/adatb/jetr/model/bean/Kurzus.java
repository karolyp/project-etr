package hu.adatb.jetr.model.bean;

public class Kurzus {
	private String kod;
	private String nev;
	private String tipus;
	private String tanar; // cinkes
	private String terem; // Terem object
	private int felev;
	private int kredit;

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public String getTanar() {
		return tanar;
	}

	public void setTanar(String tanar) {
		this.tanar = tanar;
	}

	public String getTerem() {
		return terem;
	}

	public void setTerem(String terem) {
		this.terem = terem;
	}

	public int getFelev() {
		return felev;
	}

	public void setFelev(int felev) {
		this.felev = felev;
	}

	public int getKredit() {
		return kredit;
	}

	public void setKredit(int kredit) {
		this.kredit = kredit;
	}

	public Kurzus(String kod, String nev, String tipus, String tanar, String terem, int felev, int kredit) {
		this.kod = kod;
		this.nev = nev;
		this.tipus = tipus;
		this.tanar = tanar;
		this.terem = terem;
		this.felev = felev;
		this.kredit = kredit;
	}

	@Override
	public String toString() {
		return "Kurzus [kod=" + kod + ", nev=" + nev + ", tipus=" + tipus + ", tanar=" + tanar + ", terem=" + terem
				+ ", felev=" + felev + ", kredit=" + kredit + "]";
	}

}
