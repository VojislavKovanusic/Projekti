package Biblioteka;

import java.util.ArrayList;

class Biblioteka {

	private static ArrayList<Racun> SviRacuni = new ArrayList<Racun>();
	private static ArrayList<Knjiga> SveKnjige = new ArrayList<Knjiga>();

	public Biblioteka(Racun racun, Knjiga knjiga) {
		SviRacuni.add(racun);
		SveKnjige.add(knjiga);
	}

	public Biblioteka() {

	}

	public boolean daliPostojiRacun(int br) {

		for (Racun racun : SviRacuni) {
			if (racun.getBrojRacuna() == br) {

				return true;
			}
		}
		return false;
	}

	public boolean daliPostojiKnjigaUBiblioteci(int broj) {
		for (Knjiga knjiga : SveKnjige) {
			if (knjiga.getBrojKnjige() == (broj)) {
				return true;
			}

		}
		return false;
	}

	public Racun nadjiRacun(int br) {
		for (Racun racun : SviRacuni) {
			if (racun.getBrojRacuna() == br) {
				return racun;
			}

		}
		return null;
	}

	public Knjiga nadjiKnjigu(int br) {
		for (Knjiga knjiga : SveKnjige) {
			if (knjiga.getBrojKnjige() == br) {
				return knjiga;
			}

		}
		return null;
	}

	public Biblioteka(Knjiga knjiga) {

		SveKnjige.add(knjiga);

	}

	public void ispisiSveKnjige() {
		System.out.println("Spisak knjiga koje bibloteka posjeduje:\n");
		for (Knjiga knjiga : SveKnjige) {
			System.out.println(knjiga);
		}
	}

	public void ispisiSveRacune() {
		System.out.println("Spisak svih racuna u biblioteci:\n");
		for (Racun r : SviRacuni) {
			System.out.println(r);
		}
	}

	public void dodajKnjiguUBiblioteku(Knjiga knjiga) {
		SveKnjige.add(knjiga);
	}

	public void dodajRacunUBiblioteku(Racun racun) {
		SviRacuni.add(racun);
	}

	public String toString() {
		return "Biblioteka [SviRacuni=" + SviRacuni + "\n, SveKnjige="
				+ SveKnjige + "]";
	}

}
