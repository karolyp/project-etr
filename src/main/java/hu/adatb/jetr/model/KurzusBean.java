package hu.adatb.jetr.model;

/*
 * SELECT C.CODE, C.NAME, C.TYPE, TEACHER.NAME, CLASSROOM.NAME, C.SEMESTER, C.CREDIT
 * FROM H668139.COURSE C
 * INNER JOIN TEACHER ON C.TEACHER_ID=TEACHER.ID
 * INNER JOIN CLASSROOM ON C.CLASSROOM_ID = CLASSROOM.ID
 * LEFT JOIN HALLGATO ON 1=1
 * WHERE SEMESTER=3 AND ((HALLGATO.EHA, C.CODE) IN (SELECT HALLGATO_EHA, COURSE_CODE FROM REGISTEREDCOURSE WHERE HALLGATO_EHA=? AND GRADE=0))
 */
public class KurzusBean {
	private String kod;
	private String kurzus;
	private String tipus;
	private String tanar;
	private String terem;
	private int felev;
	private int kredit;
	private String ido;

	public String getIdo() {
		return ido;
	}

	public void setIdo(String ido) {
		this.ido = ido;
	}


	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getKurzus() {
		return kurzus;
	}

	public void setKurzus(String kurzus) {
		this.kurzus = kurzus;
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

	public KurzusBean(String kod, String kurzus, String tipus, String tanar, String terem, int felev, int kredit) {
		this.kod = kod;
		this.kurzus = kurzus;
		this.tipus = tipus;
		this.tanar = tanar;
		this.terem = terem;
		this.felev = felev;
		this.kredit = kredit;
	}
	
	public KurzusBean(String kurzus, String terem, String ido){
		this.kurzus = kurzus;
		this.terem = terem;
		this.ido = ido;
	}

	@Override
	public String toString() {
		return "KurzusBean [kod=" + kod + ", kurzus=" + kurzus + ", tipus=" + tipus + ", tanar=" + tanar + ", terem="
				+ terem + ", felev=" + felev + ", kredit=" + kredit + "]";
	}

	public Object[] toArray() {
		return new Object[] { this.kod, this.kurzus, this.tipus, this.tanar, this.terem, this.felev, this.kredit,
				"Infosheet", "Lejelentkezés" };
	}
	
	public Object[] toArray1() {
		return new Object[] { this.kurzus, this.terem, this.ido};
	}

	public Object[] toArray(boolean b) {
		return new Object[] { this.kod, this.kurzus, this.tipus, this.tanar, this.terem, this.felev, this.kredit,
				Boolean.FALSE };
	}

}
