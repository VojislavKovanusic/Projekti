package Biblioteka;

import java.util.Scanner;

public class Aplikacija {

	public static void main(String[] args) {
		Scanner unos = new Scanner(System.in);
		Biblioteka biblioteka = new Biblioteka();
		meni();
		int izbor = unos.nextInt();
		while (true) {

			switch (izbor) {
			case 1:
				System.out
						.println("Upiseite ime korisnika kojeg zelite registrovati u bibloiteci:");
				unos.nextLine();
				String imeClana = unos.nextLine();
				System.out.println("Upiseite reg. broj za clana " + imeClana);
				int regBrRacuna = unos.nextInt();
				kreirajRacun(imeClana, regBrRacuna, biblioteka);
				meni();
				izbor = unos.nextInt();
				break;

			case 2:
				System.out.println("Unesite ime knjige:");
				unos.nextLine();
				String imeKnjige = unos.nextLine();
				System.out.println("Upiseite registarski broj za knjigu ");
				int regBrKnjige = unos.nextInt();
				kreirajKnjigu(imeKnjige, regBrKnjige, biblioteka);
				meni();
				izbor = unos.nextInt();
				break;
			case 3:
				System.out
						.println("Upiseite registarski broj racuna koji podize knjigu:");
				regBrRacuna = unos.nextInt();
				System.out
						.println("Upiseite registarski broj knjige koju zelite podignuti:");
				regBrKnjige = unos.nextInt();
				podizanjeKnjigaIzBlioteke(regBrRacuna, regBrKnjige, biblioteka);
				meni();
				izbor = unos.nextInt();
				break;
			case 4:
				System.out
						.println("Upiseite registarski broj racuna koji vraca knjigu:");
				regBrRacuna = unos.nextInt();
				unos.nextLine();
				System.out
						.println("Upiseite registarski broj knjige koju zelite vratiti u bibloiteci:");

				regBrKnjige = unos.nextInt();
				vratiKnjigu(regBrRacuna, regBrKnjige, biblioteka);
				meni();
				izbor = unos.nextInt();
				break;
			case 5:
				biblioteka.ispisiSveRacune();
				meni();
				izbor = unos.nextInt();
				break;

			case 6:
				biblioteka.ispisiSveKnjige();
				meni();
				izbor = unos.nextInt();
				break;

			default:
				System.out
						.println("Niste unjeli niti jedn od ponudjenih opcija\nUnesite ponovo opciju koju zelite");
				meni();
				izbor = unos.nextInt();
			}
		}

	}

	public static void meni() {
		System.out
				.println("1-Kreiraj racun.\n2-Ubaci knjigu u biblioteku\n3-Podigni knjigu"
						+ "\n4-Vrati knjigu\n5-Pregled svih racuna u biblioteci\n"
						+ "6-Stanje knjiga u biblioteci");
	}

	public static void kreirajRacun(String ime, int brRacuna,
			Biblioteka biblioteka) {
		if (brRacuna < 1) {
			System.out
					.println("Ne mozete registrovati racun sa negativnim brojem");
		} else {
			Racun racun = new Racun(ime, brRacuna);
			if (biblioteka.daliPostojiRacun(brRacuna)) {
				System.out
						.println("Vec postoji  korisnik sa registarskim brojem "
								+ brRacuna);
			} else {
				biblioteka.dodajRacunUBiblioteku(racun);
				System.out.println("Korisnik " + ime
						+ " je postao clan biblioteke");
			}

		}
	}

	public static void kreirajKnjigu(String ime, int brKnjige,
			Biblioteka biblioteka) {
		Knjiga knjiga = new Knjiga(ime, brKnjige);

		if (brKnjige < 1) {
			System.out
					.println("Ne mozete registrovati knjigu sa negativnim brojem");
		} else {
			if (!biblioteka.daliPostojiKnjigaUBiblioteci(brKnjige)) {
				biblioteka.dodajKnjiguUBiblioteku(knjiga);
				System.out.println("Knjiga " + ime
						+ " uspjesno je dodana u biblioteku");
			} else {
				System.out
						.println("Vec postoji kjiga u biblioteci sa indenticnim brojem");
			}

		}
	}

	public static void podizanjeKnjigaIzBlioteke(int bRacuna, int brKnjige,
			Biblioteka biblioteka) {

		if (!biblioteka.daliPostojiRacun(bRacuna)) {
			System.out
					.println("Ne postoji racun pod ovim brojem u biblioteci!");
		}

		if (!biblioteka.daliPostojiKnjigaUBiblioteci(brKnjige)) {
			System.out.println("Ne postoji knjiga pod tim brojem u biblioteci");
		}

		if (biblioteka.daliPostojiRacun(bRacuna)
				&& biblioteka.daliPostojiKnjigaUBiblioteci(brKnjige)) {
			// ako postoje u bazi podataka, nadji racun i knjigu te ih dodjeli
			// objektima
			Racun racun = biblioteka.nadjiRacun(bRacuna);
			Knjiga knjiga = biblioteka.nadjiKnjigu(brKnjige);
			// ako ima na racunu manje od 3 i ako je dostupna

			if (racun.getBrojKnjiga() == 3) {
				System.out.println(racun.getIme()
						+ " je vec zaduzio tri knjige!");
			}
			if (racun.getBrojKnjiga() < 3 && knjiga.isDostupna()) {
				knjiga.setDostupna(false);
				racun.povecajBrojPosudjenihKnjiga();
				racun.posudiKnjigu(knjiga);
				knjiga.setVremePosudjivanja();
				System.out.println("Knjigu " + knjiga.getIme()
						+ " je podigao  clan " + racun.getIme());
			}
		}
	}

	public static void vratiKnjigu(int bRacuna, int brKnjige,
			Biblioteka biblioteka) {

		if (!biblioteka.daliPostojiRacun(bRacuna)) {
			System.out
					.println("Ne postoji racun pod ovim brojem u biblioteci!");
		}

		if (!biblioteka.daliPostojiKnjigaUBiblioteci(brKnjige)) {
			System.out.println("Ne postoji knjiga pod tim brojem u biblioteci");
		}

		if (biblioteka.daliPostojiRacun(bRacuna)
				&& biblioteka.daliPostojiKnjigaUBiblioteci(brKnjige)) {

			Racun racun = biblioteka.nadjiRacun(bRacuna);
			Knjiga knjiga = biblioteka.nadjiKnjigu(brKnjige);
			if (racun.daLiJeKorisnikPodigaoKnjigu(brKnjige)) {
				knjiga.setDostupna(true);
				racun.smanjiBrojPosudjenihKnjiga();
				racun.vratiKnjigu(knjiga);
				knjiga.setVremeVracanja();

				System.out.println("Knjigu " + knjiga.getIme()
						+ " je vratio  clan " + racun.getIme());
			} else {
				System.out.println("Knjigu nije ni posudio taj korisnik!");
			}

		}

	}
}
