package Automobili;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.Scanner;

import javax.imageio.stream.FileImageInputStream;

import TelefonskiImenik.Imenik;

public class Aplikacija {

	public static void main(String[] args) {
		napraviFajl();
		Scanner unos = new Scanner(System.in);
		String ime = unos.next();

		if (ime.equals("admin")) {
			int izbor = unos.nextInt();
			switch (izbor) {
			case 1:
				String brend = unos.nextLine();
				String model = unos.nextLine();
				int godiste = unos.nextInt();
				double potrosnja = unos.nextDouble();
				int snaga = unos.nextInt();
				
				kreirajNovoAuto(brend, model, godiste, potrosnja, snaga);
			}

		}

	}

	public static void meniKorisnik() {

	}

	public static void meniAdmin() {
	}

	public static void napraviFajl() {
		String filename = "automobili.txt";
		File fajl = new File(filename);
		if (!fajl.exists()) {
			listaPodataka lista = new listaPodataka();

			try {
				ObjectOutputStream os = new ObjectOutputStream(
						new FileOutputStream(filename));
				os.writeObject(lista);

				os.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.out.println("Upravo je napravljen fajl imenik!");
		}
	}

	public static void kreirajNovoAuto(String brend, String model, int godiste,
			double potrosnja, int snaga) {

		Auto novoAuto = new Auto(brend, model, godiste, potrosnja, snaga);
		try {
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream("automobili.txt"));
			try {
				listaPodataka lista = (listaPodataka) input.readObject();

				lista.dodajAuto(novoAuto);
				input.close();

				ObjectOutputStream snimi = new ObjectOutputStream(
						new FileOutputStream("automobili.txt"));
				snimi.writeObject(lista);
				snimi.close();

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

	public static void ispisiSveAutomobile() {
		try {
			ObjectInputStream ipisi = new ObjectInputStream(
					new FileInputStream("automobili.txt"));
			try {
				listaPodataka lista = (listaPodataka) ipisi.readObject();
				lista.ispisiSvaAuta();
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

	public static void ispisiSveAutomobilePoCjeni(double cjena) {
		try {
			ObjectInputStream ipisi = new ObjectInputStream(
					new FileInputStream("automobili.txt"));
			try {
				listaPodataka lista = (listaPodataka) ipisi.readObject();
				lista.ispisiAutaPoCjeni(cjena);
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

	public static void ispisiSveAutomobilePoPotrosnji(double potrosnja) {
		try {
			ObjectInputStream ipisi = new ObjectInputStream(
					new FileInputStream("automobili.txt"));
			try {
				listaPodataka lista = (listaPodataka) ipisi.readObject();
				lista.ispisiAutaPoPotrosnji(potrosnja);
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

	public static void ispisiSveAutomobilePoModelu(String model) {
		try {
			ObjectInputStream ipisi = new ObjectInputStream(
					new FileInputStream("automobili.txt"));
			try {
				listaPodataka lista = (listaPodataka) ipisi.readObject();
				lista.ispisiAutaPoModelu(model);
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

	public static void ispisiSveAutomobilePoSnazi(int snaga) {
		try {
			ObjectInputStream ipisi = new ObjectInputStream(
					new FileInputStream("automobili.txt"));
			try {
				listaPodataka lista = (listaPodataka) ipisi.readObject();
				lista.ispisiAutaPoSnazi(snaga);
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

	public static void ispisiSveAutomobilePoBrendu(String brend) {
		try {
			ObjectInputStream ipisi = new ObjectInputStream(
					new FileInputStream("automobili.txt"));
			try {
				listaPodataka lista = (listaPodataka) ipisi.readObject();
				lista.ispisiAutaPoBrendu(brend);
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

	public static void ispisiSveAutomobilePoGodistu(int god) {
		try {
			ObjectInputStream ipisi = new ObjectInputStream(
					new FileInputStream("automobili.txt"));
			try {
				listaPodataka lista = (listaPodataka) ipisi.readObject();
				lista.ispisiAutaPoGodiniProizvocdnje(god);
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
}