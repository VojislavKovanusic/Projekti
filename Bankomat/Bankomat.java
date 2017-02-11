package Bankomat;

import java.io.Serializable;
import java.util.ArrayList;

public class Bankomat implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pass;
	private double balanc;
	private int stoji;
	private int pedesetica;
	private int dvadesetki;
	private int cenera;
	private ArrayList<Klijent> list;

	public Bankomat() {

		balanc = 3200;
		pass = "kkwbs";
		stoji = 10;
		pedesetica = 20;
		dvadesetki = 30;
		cenera = 60;
		list = new ArrayList<Klijent>();
	}

	public Bankomat(double b) {
		this.balanc = b;
	}

	public int getStoji() {
		return this.stoji;
	}

	public void ispisiListuKorisnika() {
		for (Klijent objekat : list) {
			System.out.println(objekat);

		}
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void getList() {
		int i = 0;
		for (Klijent klijent : list) {
			i++;
			System.out.println(i + ")" + klijent + "\n");

		}

	}

	public void dodajKorisnikaUListu(Klijent x) {
		list.add(x);
	}

	public int getPedesetica() {
		return pedesetica;
	}

	public int getDvadesetki() {
		return dvadesetki;
	}

	public int getCenera() {
		return cenera;
	}

	public void setStoji(int stoji) {
		this.stoji = stoji;
	}

	public void setPedesetica(int pedesetica) {
		this.pedesetica = pedesetica;
	}

	public void setDvadesetki(int dvadesetki) {
		this.dvadesetki = dvadesetki;
	}

	public void setCenera(int cenera) {
		this.cenera = cenera;
	}

	public void nastimajStojanke(double w) {
		this.stoji -= (int) (w / 100);
	}

	public void nastimajPedesetke(double w) {
		this.pedesetica -= (int) (w / 50);
	}

	public void nastimajdvadesetka(double w) {
		this.dvadesetki -= (int) (w / 20);
	}

	public void nastimajcenere(double w) {
		this.cenera -= (int) (w / 10);
	}

	public double getBalanc() {
		return balanc;
	}

	public void setBalanc(double balanc) {
		this.balanc = balanc;
	}

	public void napuniStojanke(int x) {
		this.stoji += x;
	}

	public void napuniPedesetke(int x) {
		this.pedesetica += x;
	}

	public void napuniDvadesetke(int x) {
		this.dvadesetki += x;
	}

	public void napuniCenere(int x) {
		this.cenera += x;
	}

	public void deponuj(double x) {
		this.balanc += x;
	}

	public void smanjiBalanc(double x) {
		this.balanc -= x;
	}

	public void izvadiCenera(int x) {
		this.cenera -= x;
	}

	public void izvadiDvadesetki(int x) {
		this.dvadesetki -= x;
	}

	public void izvadiPedesetki(int x) {
		this.pedesetica -= x;
	}

	public void izvadiStoji(int x) {
		this.stoji -= x;
	}


}
