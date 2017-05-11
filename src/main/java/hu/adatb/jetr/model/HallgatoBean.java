package hu.adatb.jetr.model;

public class HallgatoBean {
	private String eha;
	private String jelszo;
	private int felev;

	public String getEha() {
		return eha;
	}

	public void setEha(String eha) {
		this.eha = eha;
	}

	public String getJelszo() {
		return jelszo;
	}

	public void setJelszo(String jelszo) {
		this.jelszo = jelszo;
	}

	public int getFelev() {
		return felev;
	}

	public void setFelev(int felev) {
		this.felev = felev;
	}

	public HallgatoBean(String eha, String jelszo, int felev) {
		this.eha = eha;
		this.jelszo = jelszo;
		this.felev = felev;
	}

}
