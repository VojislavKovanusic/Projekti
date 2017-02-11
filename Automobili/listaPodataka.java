package Automobili;

import java.io.Serializable;
import java.util.ArrayList;

import TelefonskiImenik.Korisnik;

public class listaPodataka implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Auto> lista;
	private ArrayList<Korisnik> korisnici;

	public listaPodataka() {
		lista = new ArrayList<>();
	}

	public void dodajAuto(Auto a) {
		lista.add(a);

	}

	public void napraviKorisnika(Korisnik korisnik) {
		korisnici.add(korisnik);

	}

	public boolean daLiPostojiKorisnik(String ime) {
		for (Korisnik korisnik : this.korisnici) {
			if (korisnik.getIme().equals(ime))
				return true;

		}
		return false;

	}
	public boolean validnaSifra(String pass) {
		for (Korisnik korisnik : this.korisnici) {
			if (korisnik.getPass().equals(pass))
				return true;

		}
		return false;

	}
	

	public void ispisiSvaAuta() {

		for (Auto auto : lista) {
			System.out.println(auto);

		}

	}

	public void ispisiAutaPoModelu(String model) {
		for (Auto auto : lista) {
			if (auto.getModel() == model) {
				System.out.println(auto);
			}

		}
	}

	public void ispisiAutaPoBrendu(String brend) {
		for (Auto auto : lista) {
			if (auto.getBrend() == brend) {
				System.out.println(auto);
			}

		}
	}

	public void ispisiAutaPoCjeni(double cjena) {
		for (Auto auto : lista) {
			if (auto.getCjena() == cjena) {
				System.out.println(auto);
			}

		}
	}

	public void ispisiAutaPoSnazi(int snaga) {
		for (Auto auto : lista) {
			if (auto.getSnaga() == snaga) {
				System.out.println(auto);
			}

		}
	}

	public void ispisiAutaPoPotrosnji(double potrosnja) {
		for (Auto auto : lista) {
			if (auto.getPotrosnja() == potrosnja) {
				System.out.println(auto);
			}
		}

	}

	public void ispisiAutaPoGodiniProizvocdnje(int god) {
		for (Auto auto : lista) {
			if (auto.getGodiste() == god) {
				System.out.println(auto);
			}
		}
	}

}
