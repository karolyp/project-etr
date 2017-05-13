package hu.adatb.jetr.model;

import java.sql.Date;

public class VizsgaBean {
	
	private Date datum;
	private String kurzus_kod;
	private int jegy;
	
	
	public VizsgaBean(Date datum, String kurzus_kod, int jegy){
		this.datum = datum;
		this.kurzus_kod = kurzus_kod;
		this.jegy = jegy;
	}
	
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getKurzus_kod() {
		return kurzus_kod;
	}
	public void setKurzus_kod(String kurzus_kod) {
		this.kurzus_kod = kurzus_kod;
	}
	public int getJegy() {
		return jegy;
	}
	public void setJegy(int jegy) {
		this.jegy = jegy;
	}
	
	@Override
	public String toString() {
		return "VizsgaBean [datum=" + datum + ", kurzus_kod=" + kurzus_kod + ", jegy=" + jegy + "]";
	}

	public Object[] toArray() {
		return new Object[] { this.datum, this.kurzus_kod, this.jegy};
	}

	public Object[] toArray(boolean b) {
		return new Object[] { this.datum, this.kurzus_kod, this.jegy, Boolean.FALSE };
	}

	
	

}
