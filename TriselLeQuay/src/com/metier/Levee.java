package com.metier;

import java.util.Date;

public class Levee {
	
	private int idLevee;
	private java.util.Date laDate;
	private double poids;
	private String idPoubelle;
	// surcharge de constructeur
	// constructeur pour une nouvelle levée car numéro auto
	public Levee(Date uneDate, double unPoids, String idPoubelle) {
		this.laDate = uneDate;
		this.poids = unPoids;
		this.idPoubelle = idPoubelle;
	}
	// constructeur pour instancier une levée à  partir de la persistance
	public Levee(int idLevee, Date uneDate, double unPoids, String idPoubelle) {
		this.idLevee = idLevee;
		this.laDate = uneDate;
		this.poids = unPoids;
		this.idPoubelle = idPoubelle;
	}
	public int getIdLevee() {
		return idLevee;
	}
	public java.util.Date getLaDate() {
		return laDate;
	}
	public void setLaDate(java.util.Date laDate) {
		this.laDate = laDate;
	}
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	public String getIdPoubelle() {
		return idPoubelle;
	}
	@Override
	public String toString() {
		return "Levee [idLevee=" + idLevee + ", laDate=" + laDate + ", poids="
				+ poids + ", idPoubelle=" + idPoubelle + "]";
	}
}
