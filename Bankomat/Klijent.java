package Bankomat;

import java.io.Serializable;

public class Klijent implements Serializable {

	private static final long serialVersionUID = 1L;
public String ime;
String pass;
double balanc;

public Klijent(String ime,double balanc) {
	super();
	this.ime = ime;
	this.pass = "123456";
	this.balanc = balanc;
}

public String getIme() {
	return ime;
}

public String getPass() {
	return pass;
}

public double getBalanc() {
	return balanc;
}

public void setIme(String ime) {
	this.ime = ime;
}
public void setPass(String pass) {
	this.pass = pass;
}
@Override
public String toString() {
	return "Objekat [ime=" + ime + ", pass=" + pass + ", balanc=" + balanc
			+ "]";
}
public void setBalanc(double balanc) {
	this.balanc = balanc;
}

}
