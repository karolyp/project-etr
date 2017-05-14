package hu.adatb.jetr.model;

import java.sql.Date;

public class VizsgaBean {
	/*
	 * SELECT V.VI_DATUM, C.CODE, C.NAME, VI.JELENTKEZETT, VI.MAX_JELENTKEZO,
	 * V.JEGY FROM VIZSGA V INNER JOIN COURSE C ON C.CODE=V.VI_KURZUS_KOD INNER
	 * JOIN VIZSGAIDOPONT VI ON VI.DATUM=V.VI_DATUM AND
	 * V.VI_KURZUS_KOD=VI.KURZUS_KOD INNER JOIN REGISTEREDCOURSE RC ON
	 * RC.COURSE_CODE=V.VI_KURZUS_KOD AND RC.GRADE <= 1 WHERE
	 * V.HALLGATO_EHA='PAKXADT.SZE'
	 */
	private Date datum;
	private String kurzuskod;
	private String kurzusNev;
	private int jelentkezok;
	private int maxJelentkezok;
	private int jegy;
	private String terem;

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getKurzuskod() {
		return kurzuskod;
	}

	public void setKurzuskod(String kurzuskod) {
		this.kurzuskod = kurzuskod;
	}

	public String getKurzusNev() {
		return kurzusNev;
	}

	public void setKurzusNev(String kurzusNev) {
		this.kurzusNev = kurzusNev;
	}

	public int getJelentkezok() {
		return jelentkezok;
	}

	public void setJelentkezok(int jelentkezok) {
		this.jelentkezok = jelentkezok;
	}

	public int getMaxJelentkezok() {
		return maxJelentkezok;
	}

	public void setMaxJelentkezok(int maxJelentkezok) {
		this.maxJelentkezok = maxJelentkezok;
	}

	public int getJegy() {
		return jegy;
	}

	public void setJegy(int jegy) {
		this.jegy = jegy;
	}

	public VizsgaBean(Date datum, String kurzuskod, String kurzusNev, int jelentkezok, int maxJelentkezok, int jegy) {
		this.datum = datum;
		this.kurzuskod = kurzuskod;
		this.kurzusNev = kurzusNev;
		this.jelentkezok = jelentkezok;
		this.maxJelentkezok = maxJelentkezok;
		this.jegy = jegy;
	}

	public VizsgaBean(Date datum, String kurzuskod, String kurzusNev, String terem, int jelentkezok,
			int maxJelentkezok) {
		this.datum = datum;
		this.kurzuskod = kurzuskod;
		this.kurzusNev = kurzusNev;
		this.terem = terem;
		this.jelentkezok = jelentkezok;
		this.maxJelentkezok = maxJelentkezok;
	}

	public Object[] toArray() {
		return new Object[] { this.datum, this.kurzusNev, this.jelentkezok, this.maxJelentkezok, this.jegy,
				"Lejelentkezés" };
	}

	public Object[] toArray(boolean b) {
		return new Object[] { this.datum, this.kurzusNev, this.jelentkezok, this.maxJelentkezok, this.jegy,
				"nemlehet" };
	}

	public Object[] toArray(int b) {
		return new Object[] { this.datum, this.kurzusNev, this.terem, this.jelentkezok, this.maxJelentkezok,
				"Felvétel" };
	}

}
