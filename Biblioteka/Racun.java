package Biblioteka;

import java.util.ArrayList;

class Racun {
	private String ime;
	private int brojRacuna;
	private int brojKnjiga;
	private ArrayList<Knjiga> listaPodignutihKnjiga;

	public Racun(String ime, int brRacuna) {
		listaPodignutihKnjiga = new ArrayList<Knjiga>();

		this.ime = ime;
		this.brojRacuna = brRacuna;
		this.brojKnjiga = 0;
	}

	public String getIme() {
		return ime;
	}

	public int getBrojRacuna() {
		return brojRacuna;
	}

	public int getBrojKnjiga() {
		return brojKnjiga;
	}

	public void povecajBrojPosudjenihKnjiga() {
		this.brojKnjiga += 1;

	}

	public void smanjiBrojPosudjenihKnjiga() {
		this.brojKnjiga -= 1;
	}

	public void ispisiListuPodignutihKnjiga() {
		for (Knjiga knjiga : listaPodignutihKnjiga) {
			System.out.println(knjiga);

		}
	}

	public void posudiKnjigu(Knjiga knjiga) {
		this.listaPodignutihKnjiga.add(knjiga);
	}

	public void vratiKnjigu(Knjiga k) {
		for (Knjiga knjiga : this.listaPodignutihKnjiga) {
			if (knjiga.equals(k)) {
				this.listaPodignutihKnjiga.remove(knjiga);
			}

		}
	}

	public boolean daLiJeKorisnikPodigaoKnjigu(int br) {
		for (Knjiga objekat : listaPodignutihKnjiga) {
			if (objekat.getBrojKnjige() == br) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return "ime=" + ime + ", brojRacuna=" + brojRacuna + ", brojKnjiga="
				+ brojKnjiga + ", listaPodignutihKnjiga="
				+ listaPodignutihKnjiga + "]";
	}

}
