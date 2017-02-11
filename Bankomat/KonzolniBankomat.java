package Bankomat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class KonzolniBankomat {
	private static final long serialVersionUID = -6166120235848705746L;
	public static void main(String[] args) {
		 
		
		napraviFileBnka();
		Scanner unos = new Scanner(System.in);
		while (true) {
			System.out.println("Unesite vase korisnicko ime:");

			String name = unos.next();
			File fajl = new File(name + ".txt");

			// ADMINISTRATORSKI MENI
			if (name.equals("admin")) {

				System.out
						.println("\t\tADMINISTRATORSKI MENI:\nUnesite vasu sifru:");
				String pass = unos.next();
				if (pass.equals("kkwbs")) {

					administratorskiMeni();
					int izbor = unos.nextInt();
					do {
						switch (izbor) {
						case 1:
							System.out
									.println("Unesite ime za novog korisnika");
							String imeKorisnika = unos.next();
							System.out
									.println("Unesite stanje racuna za novog korisnika");
							double balanc = unos.nextDouble();
							napraviKorisnika(imeKorisnika, balanc);
							administratorskiMeni();
							izbor = unos.nextInt();
							if (izbor < 1 || izbor > 7) {
								System.out
										.println("Niste unjeli niti jednu od ponudjenih opcija\nAutomacki ste izlogovani!");
								izbor = 6;
							}
							break;

						case 2:
							stanjeApoenaZaDeponovanje();
							System.out
									.println("Napunite bankomat sa apoenima sledecim redom 10, 20, 50 i 100 KM");
							int niz[] = new int[4];
							for (int i = 0; i < 4; i++) {
								niz[i] = unos.nextInt();
							}

							napuniBankomat(niz[3], niz[2], niz[1], niz[0]);

							administratorskiMeni();
							izbor = unos.nextInt();
							if (izbor < 1 || izbor > 7) {
								System.out
										.println("Niste unjeli niti jednu od ponudjenih opcija\nAutomacki ste izlogovani!");
								izbor = 6;
							}
							break;

						case 3:
							System.out
									.println("Mozete izvaditi sledece apoene:");
							stanjeApoenaZaVadjenje();
							System.out
									.println("--------------------\nMozete dodati sledece apoene:");
							stanjeApoenaZaDeponovanje();
							System.out
									.println("Unesite koje apoene zelite dodati:");
							int apoenaStavi = unos.nextInt();
						

							System.out
									.println("Unesite koliko komada zelite staviti, novcanica od "
											+ apoenaStavi + " apoena");
							int komadaStavi = unos.nextInt();
							System.out
									.println("Unesite koje apoene zelite izvaditi:");
							int apoenaIzvadi = unos.nextInt();
						
							System.out
									.println("Unesite koliko komada zelite izvaditi, novcanica od "
											+ apoenaIzvadi + " apoena");
							int komadaIzvadi = unos.nextInt();

							promeniteStanjeNovcanica(apoenaStavi, komadaStavi,
									apoenaIzvadi, komadaIzvadi);

							administratorskiMeni();
							izbor = unos.nextInt();
							if (izbor < 1 || izbor > 7) {
								System.out
										.println("Niste unjeli niti jednu od ponudjenih opcija\nVi ste izlogovani!");
								izbor = 6;
							}
							break;

						case 4:
							trenutnoStanjeBankomata();
							administratorskiMeni();
							izbor = unos.nextInt();
							if (izbor < 1 || izbor > 7) {
								System.out
										.println("Niste unjeli niti jednu od ponudjenih opcija\nVi ste izlogovani!");
								izbor = 6;
							}
							break;

						case 5:
							System.out
									.println("Unesite ime korisnika kojeg zelite obrisati:");
							String korisnik = unos.next();
							obrisiKorisnika(korisnik);
							administratorskiMeni();

							izbor = unos.nextInt();
							if (izbor < 1 || izbor > 7) {
								System.out
										.println("Niste unjeli niti jednu od ponudjenih opcija\nVi ste izlogovani!");
								izbor = 6;
							}
							break;

						}
					} while (izbor != 6);

				} else {
					System.out.println("Sifra nije dobra");
				}

			}// nesto da se vratim i ponovo upisem sifru

			// KORISNICKI MENI
			if (fajl.exists() && !name.equals("admin")) {

				int kraj = 0;
				System.out
						.println("\t\t KORISNICKI MENI:\nKorisnice unesite vasu sifru:");
				String pass = unos.next();
				if (validnaSifra(name, pass)) {
					korisnickiMeni();
					int izbor = unos.nextInt();
					if (izbor == 4) {
						kraj = 4;
						System.out.println("\nVi ste izlogovani!");
					}
					do {
						switch (izbor) {
						case 1:
							korisnikovoStanjeRacuna(name);
							korisnickiMeni();
							izbor = unos.nextInt();
							if (izbor < 1 || izbor >= 4) {
								System.out.println("\nVi ste izlogovani!");
								kraj = 4;
							}
							break;
						case 2:
							System.out
									.println("Unesite svotu novca koju zelite podignuti:");
							double withdrowe = unos.nextDouble();

							klijentPovlacenjeSredstava(name, withdrowe);
							korisnickiMeni();
							izbor = unos.nextInt();
							if (izbor < 1 || izbor >= 4) {
								System.out.println("\nVi ste izlogovani!");
								kraj = 4;
							}
							break;
						case 3:
							System.out
									.println("Unesite novu sifru koju zelite:");
							String novasifra = unos.next();
							korisnikPromjenaSifre(name, novasifra);
							korisnickiMeni();
							izbor = unos.nextInt();
							if (izbor == 4) {
								kraj = 4;
							}
							break;

						}

					} while (kraj != 4);
				}
				if (!fajl.exists()) {
					System.out.println("korisnicko ime ne postoji");

				}
			}
		}
	}

	// radi,nadam se!!!
	public static void napraviFileBnka() {
		File fajl = new File("automat.txt");
		if (!fajl.exists()) {
			Bankomat banka = new Bankomat();
			String filename = "automat.txt";

			try {
				ObjectOutputStream os = new ObjectOutputStream(
						new FileOutputStream(filename));
				os.writeObject(banka);

				os.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.out.println("Upravo je napravljen fajl admin!");
		}
	}

	public static void korisnickiMeni() {
		System.out.println("***********************************************\n"
				+ "Unesite 1 za stanje na racunu\n"
				+ "Unesite 2 za podizanje novca\n"
				+ "Unesite 3 da promjenite sifru!\n"
				+ "Unesite 4 ako zelite da se izlogujete!\n"
				+ ":::::::::::::::::::::::::::::::::::::::::::::::");
	}

	public static void administratorskiMeni() {
		System.out.println("*************************************************"
				+ "\nUnesite 1 da napravite korisnicki nalog\n"
				+ "Unesite 2 za punjenje bankomata\n"
				+ "Unesite 3 za promjenu stanja apoena u bankomatu\n"
				+ "Unesite 4 da provjerite stanje u bankomatu\n"
				+ "Unesite 5 da obrisete korisnicki nalog\n"
				+ "Unesite 6 ako se ako zelite da se izlogujete!\n"
				+ ":::::::::::::::::::::::::::::::::::::::::::::::::");
	}

	// radi
	public static void napraviKorisnika(String ime, double balanc) {

		Klijent korisnik = new Klijent(ime, balanc);

		String fileName = ime + ".txt";

		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream(fileName));

			os.writeObject(korisnik);

			os.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("Done writing!");
	}

	// radi
	public static void korisnikovoStanjeRacuna(String ime, String pass) {
		try {

			
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(ime + ".txt"));
			try {
				Klijent korisnik = (Klijent) input.readObject();
				if (korisnik.getPass().equals(pass)) {
					System.out.println("Ime korisnika: " + korisnik.getIme()
							+ "\nTrenutno stanje racuna: "
							+ korisnik.getBalanc());
				} else
					System.out.println(korisnik.getIme()
							+ " niste upisali dobru sifru");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ne radi
	private static void dodajKlijentanaListu(Klijent klijent) {
		Bankomat bankomat = new Bankomat();
		bankomat.dodajKorisnikaUListu(klijent);

		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream("automat.txt"));

			os.writeObject(bankomat);

			os.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// ne radi
	public static void ispisiListuKlijenata() {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"automat.txt"));
			Bankomat bankomat = (Bankomat) is.readObject();

			bankomat.getList();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ne radi
	public static void sviKorisnici() {
		Bankomat b = new Bankomat();
		b.ispisiListuKorisnika();
	}

	// 
	public static void korisnikPromjenaSifre(String ime, String noviPass) {

		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					ime + ".txt"));
			try {
				Klijent korisnik = (Klijent) is.readObject();
				korisnik.setPass(noviPass);

				ObjectOutputStream upis = new ObjectOutputStream(
						new FileOutputStream(ime + ".txt"));
				upis.writeObject(korisnik);
				is.close();
				upis.close();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// radi
	public static void klijentPovlacenjeSredstava(String name, double withdrowe) {
		if (withdrowe % 10 != 0) {
			System.out
					.println("Morate traziti okrugao iznos za povlacenje, jer banka posjeduje najamnje apoena u iznosu od 10KM!");
		} else {
			try {

				ObjectInputStream is = new ObjectInputStream(
						new FileInputStream(name + ".txt"));

				Klijent korisnik = (Klijent) is.readObject();

				if (korisnik.getBalanc() < withdrowe) {
					System.out.println(korisnik.getIme()
							+ " nemate dovolno sredstava, razligu!");
				}
				if (daLiBankaPosjedujeApoene(withdrowe)
						&& (korisnik.getBalanc() > withdrowe)) {
					povlacenjeSredstavaIzBanke(withdrowe);
					korisnik.setBalanc(korisnik.getBalanc() - withdrowe);

					System.out.println(" Korisnik " + name
							+ " je upravo povukao " + withdrowe
							+ ", tako da je stanje racuna trenutno: "
							+ korisnik.getBalanc());

					ObjectOutputStream upis = new ObjectOutputStream(
							new FileOutputStream(name + ".txt"));

					upis.writeObject(korisnik);

					upis.close();

				}

				if (!daLiBankaPosjedujeApoene(withdrowe)) {
					System.out
							.println("Banka trenutno nema toliko raspolozivih sredstava na stanju,prosetajte do sledeceg bankomata!");

				}
				is.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// radi
	private static boolean daLiBankaPosjedujeApoene(double widhdrove) {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"automat.txt"));
			try {
				Bankomat banka = (Bankomat) is.readObject();

				int ukupnoStoja = (int) (widhdrove / 100);
				if ((banka.getStoji() - ukupnoStoja) < 0) {
					widhdrove = widhdrove % 100
							+ (ukupnoStoja - banka.getStoji()) * 100;
					banka.setStoji(0);
				} else {
					banka.nastimajStojanke(widhdrove);
					widhdrove = widhdrove % 100;
				}

				int ukupnoPedesetki = (int) (widhdrove / 50);
				if ((banka.getPedesetica() - ukupnoPedesetki) < 0) {
					widhdrove = widhdrove % 50
							+ (ukupnoPedesetki - banka.getPedesetica()) * 50;
					banka.setPedesetica(0);
				} else {
					banka.nastimajPedesetke(widhdrove);
					widhdrove = widhdrove % 50;
				}

				int ukupnoDvadesetki = (int) (widhdrove / 20);
				if ((banka.getDvadesetki() - ukupnoDvadesetki) < 0) {
					widhdrove = widhdrove % 20
							+ (ukupnoDvadesetki - banka.getDvadesetki()) * 20;
					banka.setDvadesetki(0);
				} else {
					banka.nastimajdvadesetka(widhdrove);
					widhdrove = widhdrove % 20;
				}

				int ukupnoCenera = (int) (widhdrove / 10);
				if ((banka.getCenera() - ukupnoCenera) < 0) {
					widhdrove = widhdrove % 10
							+ (ukupnoCenera - banka.getCenera()) * 10;
					banka.setCenera(0);
				} else {
					banka.nastimajcenere(widhdrove);
					widhdrove = widhdrove % 10;
				}

				if (widhdrove == 0.0) {
					return true;
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	// radi
	public static void napuniBankomat(int stoji, int pedesetki, int dvadesetki,
			int cenera) {
		if (stoji < 0 || dvadesetki < 0 || pedesetki < 0 || cenera < 0) {
			System.out.println("Ne mozete unjeti negativan br novcanica!");
		} else {
			try {
				ObjectInputStream is = new ObjectInputStream(
						new FileInputStream("automat.txt"));

				try {
					Bankomat banka = (Bankomat) is.readObject();
					System.out.println("Stanje banke prije punjenja: "
							+ banka.getBalanc());
					if (banka.getStoji() + stoji > 100
							|| banka.getPedesetica() + pedesetki > 100
							|| banka.getDvadesetki() + dvadesetki > 100
							|| banka.getCenera() + cenera > 100) {
						System.out
								.println("Maksimalan broj po valuti je 100, bankomat nije primio unesena sredstva!");
					} else {
						double deposit = stoji * 100 + pedesetki * 50
								+ dvadesetki * 20 + cenera * 10;
						banka.napuniCenere(cenera);
						banka.napuniDvadesetke(dvadesetki);
						banka.napuniPedesetke(pedesetki);
						banka.napuniStojanke(stoji);
						banka.deponuj(deposit);
					}

					System.out.println("Stanje banke posle punjenja"
							+ banka.getBalanc());
					System.out.println("Cenera ima: " + banka.getCenera()
							+ "\nDvadesetki ima: " + banka.getDvadesetki()
							+ "\n" + "Pedesetki ima: " + banka.getPedesetica()
							+ "\nStoji ima: " + banka.getStoji());

					ObjectOutputStream os = new ObjectOutputStream(
							new FileOutputStream("automat.txt"));
					os.writeObject(banka);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// radi
	public static void promeniteStanjeNovcanica(int dodajApoene,
			int dodajKomada, int izvadiApoene, int izvadiKomada) {

		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"automat.txt"));

			try {
				Bankomat bankomat = (Bankomat) is.readObject();

				if (dodajApoene == 10
						&& (bankomat.getCenera() + dodajKomada) < 100) {
					bankomat.napuniCenere(dodajKomada);
					bankomat.deponuj(dodajApoene * dodajKomada);
				}
				if (dodajApoene == 20
						&& (bankomat.getDvadesetki() + dodajKomada) < 100) {
					bankomat.napuniDvadesetke(dodajKomada);
					bankomat.deponuj(dodajApoene * dodajKomada);
				}
				if (dodajApoene == 50
						&& (bankomat.getPedesetica() + dodajKomada) < 100) {
					bankomat.napuniDvadesetke(dodajKomada);
					bankomat.deponuj(dodajApoene * dodajKomada);
				}
				if (dodajApoene == 100
						&& (bankomat.getStoji() + dodajKomada) < 100) {
					bankomat.napuniStojanke(dodajKomada);
					bankomat.deponuj(dodajApoene * dodajKomada);
				}
				
				if (izvadiApoene == 10 && bankomat.getCenera() > izvadiKomada) {
					bankomat.izvadiCenera(izvadiKomada);
					bankomat.smanjiBalanc(izvadiKomada * izvadiApoene);
				}
				if (izvadiApoene == 20
						&& bankomat.getDvadesetki() > izvadiKomada) {
					bankomat.izvadiDvadesetki(izvadiKomada);
					bankomat.smanjiBalanc(izvadiKomada * izvadiApoene);
				}
				if (izvadiApoene == 50
						&& bankomat.getPedesetica() > izvadiKomada) {
					bankomat.izvadiPedesetki(izvadiKomada);
					bankomat.smanjiBalanc(izvadiKomada * izvadiApoene);
				}
				if (izvadiApoene == 100 && bankomat.getStoji() > izvadiKomada) {
					bankomat.izvadiStoji(izvadiKomada);
					bankomat.smanjiBalanc(izvadiKomada * izvadiApoene);
				}	
				

				System.out.println("Stanje banke nakon ove transakcie iznosi:"
						+ bankomat.getBalanc());
				System.out.println("Cenera ima: " + bankomat.getCenera()
						+ "\nDvadesetki ima: " + bankomat.getDvadesetki()
				
						+ "\n" + "Pedesetki ima: " + bankomat.getPedesetica()
						+ "\nStoji ima: " + bankomat.getStoji());

				ObjectOutputStream os = new ObjectOutputStream(
						new FileOutputStream("automat.txt"));
				os.writeObject(bankomat);
				os.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// radi
	public static void povlacenjeSredstavaIzBanke(double widhdrowe) {

		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"automat.txt"));
			try {
				Bankomat banka = (Bankomat) is.readObject();
				// System.out.println("pre povlacenja " + banka.getBalanc());
				if (banka.getBalanc() > widhdrowe) {
					banka.setBalanc(banka.getBalanc() - widhdrowe);// povlacenje
																	// balansa
					int ukupnoStoja = (int) (widhdrowe / 100); // povlacenje
																// apoena
					if ((banka.getStoji() - ukupnoStoja) < 0) {
						widhdrowe = widhdrowe % 100
								+ (ukupnoStoja - banka.getStoji()) * 100;
						banka.setStoji(0);
					} else {
						banka.nastimajStojanke(widhdrowe);
						widhdrowe = widhdrowe % 100;
					}

					int ukupnoPedesetki = (int) (widhdrowe / 50);
					if ((banka.getPedesetica() - ukupnoPedesetki) < 0) {
						widhdrowe = widhdrowe % 50
								+ (ukupnoPedesetki - banka.getPedesetica())
								* 50;
						banka.setPedesetica(0);
					} else {
						banka.nastimajPedesetke(widhdrowe);
						widhdrowe = widhdrowe % 50;
					}

					int ukupnoDvadesetki = (int) (widhdrowe / 20);
					if ((banka.getDvadesetki() - ukupnoDvadesetki) < 0) {
						widhdrowe = widhdrowe % 20
								+ (ukupnoDvadesetki - banka.getDvadesetki())
								* 20;
						banka.setDvadesetki(0);
					} else {
						banka.nastimajdvadesetka(widhdrowe);
						widhdrowe = widhdrowe % 20;
					}

					int ukupnoCenera = (int) (widhdrowe / 10);
					if ((banka.getCenera() - ukupnoCenera) < 0) {
						widhdrowe = widhdrowe % 10
								+ (ukupnoCenera - banka.getCenera()) * 10;
						banka.setCenera(0);
					} else {
						banka.nastimajcenere(widhdrowe);
						widhdrowe = widhdrowe % 10;
					}

					ObjectOutputStream upis1 = new ObjectOutputStream(
							new FileOutputStream("automat.txt"));
					upis1.writeObject(banka);

					upis1.close();
				} else {
					System.out.println("Vi nemate toliko sredstava na racunu!");
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void obrisiKorisnika(String name) {
		File file = new File(name + ".txt");
		if (file.exists()) {
			file.delete();
			System.out.println("uspjesno ste obrisali " + name
					+ " iz liste korisnika bankomata!");
		} else
			System.out.println("U bankomatu ne postoji korisnik pod imenom "
					+ name);

	}

	public static boolean validnaSifra(String ime, String pass) {

		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					ime + ".txt"));
			try {
				Klijent korisnik = (Klijent) is.readObject();
				if (korisnik.getPass().equals(pass)) {
					is.close();
					return true;

				}
				is.close();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public static void korisnikovoStanjeRacuna(String ime) {
		try {
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(ime + ".txt"));
			try {
				Klijent korisnik = (Klijent) input.readObject();

				System.out.println("Ime korisnika: " + korisnik.getIme()
						+ "\nTrenutno stanje racuna: " + korisnik.getBalanc());
				input.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void trenutnoStanjeBankomata() {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"automat.txt"));

			try {
				Bankomat bankomat = (Bankomat) is.readObject();

				System.out.println("Stanje banke" + bankomat.getBalanc());
				System.out.println("Cenera ima: " + bankomat.getCenera()
						+ "\nDvadesetki ima: " + bankomat.getDvadesetki()
						+ "\n" + "Pedesetki ima: " + bankomat.getPedesetica()
						+ "\nStoji ima: " + bankomat.getStoji());

				is.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// tj trenutno stanje
	public static void stanjeApoenaZaVadjenje() {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"automat.txt"));

			try {
				Bankomat bankomat = (Bankomat) is.readObject();

				System.out.println("Cenera mozete izvaditi: "
						+ (bankomat.getCenera())
						+ "\nDvadesetki mozete izvaditi: "
						+ (bankomat.getDvadesetki()) + "\n"
						+ "Pedesetki mozete izvaditi: "
						+ (bankomat.getPedesetica())
						+ "\nStoji mozete izvaditi: " + (bankomat.getStoji()));

				is.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void stanjeApoenaZaDeponovanje() {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"automat.txt"));

			try {
				Bankomat bankomat = (Bankomat) is.readObject();

				System.out.println("Cenera mozete staviti: "
						+ (100 - bankomat.getCenera())
						+ "\nDvadesetki mozete staviti: "
						+ (100 - bankomat.getDvadesetki()) + "\n"
						+ "Pedesetki mozete staviti: "
						+ (100 - bankomat.getPedesetica())
						+ "\nStoji mozete staviti: "
						+ (100 - bankomat.getStoji()));

				is.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
