package Biblioteka;

import java.util.Date;

class Knjiga {
	private String ime;
	private int brojKnjige;
	private boolean dostupna;
	private Date vremePosudjivanja;

	public Knjiga(String ime, int br) {

		this.dostupna = true;
		this.ime = ime;
		this.brojKnjige = br;
		
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getBrojKnjige() {
		return brojKnjige;
	}

	public void setBrojKnjige(int brojKnjige) {
		this.brojKnjige = brojKnjige;
	}

	public boolean isDostupna() {
		return dostupna;
	}

	public void setDostupna(boolean dostupna) {
		this.dostupna = dostupna;
	}

	public Date getVremePosudjivanja() {
		return vremePosudjivanja;
	}

	public void setVremePosudjivanja() {
	 this.vremePosudjivanja = new Date();;
	}
	public void setVremeVracanja() {
		 this.vremePosudjivanja = null;
		}

	public String toString() {
		return brojKnjige + ") ime=" + ime + ", dostupna=" + dostupna
				+ ", vremePosudjivanja=" + vremePosudjivanja + "\n";
	}

}
