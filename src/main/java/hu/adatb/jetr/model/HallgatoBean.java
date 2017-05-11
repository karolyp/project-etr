package hu.adatb.jetr.model;

public class HallgatoBean {
	private String eha;
	private String jelszo;

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

	public HallgatoBean(String eha, String jelszo) {
		super();
		this.eha = eha;
		this.jelszo = jelszo;
	}

}
