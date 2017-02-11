package Automobili;

import java.io.Serializable;

public class Auto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brend;
	private String model;
	private int godiste;
	private double potrosnja;
	private int snaga;
	private double cjena;

	public Auto(String brend, String model, int godiste, double potrosnja,
			int snaga) {
		super();
		this.brend = brend;
		this.model = model;
		this.godiste = godiste;
		this.potrosnja = potrosnja;
		this.snaga = snaga;
	}

	public String getBrend() {
		return brend;
	}

	public void setBrend(String brend) {
		this.brend = brend;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getGodiste() {
		return godiste;
	}

	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}

	public double getPotrosnja() {
		return potrosnja;
	}

	public void setPotrosnja(double potrosnja) {
		this.potrosnja = potrosnja;
	}

	public int getSnaga() {
		return snaga;
	}

	public void setSnaga(int snaga) {
		this.snaga = snaga;
	}

	@Override
	public String toString() {
		return "Auto [brend=" + brend + ", model=" + model + ", godiste="
				+ godiste + ", potrosnja=" + potrosnja + ", snaga=" + snaga
				+ "cjena=" + cjena +"]";
	}

	public double getCjena() {
		return cjena;
	}

	public void setCjena(double cjena) {
		this.cjena = cjena;
	}

}
