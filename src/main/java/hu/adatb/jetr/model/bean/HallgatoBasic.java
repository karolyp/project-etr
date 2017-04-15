package hu.adatb.jetr.model.bean;

public class HallgatoBasic {
	private String eha;
	private String vezeteknev;
	private String utonev;

	public String getEha() {
		return eha;
	}

	public void setEha(String eha) {
		this.eha = eha;
	}

	public String getVezeteknev() {
		return vezeteknev;
	}

	public void setVezeteknev(String vezeteknev) {
		this.vezeteknev = vezeteknev;
	}

	public String getUtonev() {
		return utonev;
	}

	public void setUtonev(String utonev) {
		this.utonev = utonev;
	}

	public HallgatoBasic(String eha, String vezeteknev, String utonev) {
		super();
		this.eha = eha;
		this.vezeteknev = vezeteknev;
		this.utonev = utonev;
	}

}
