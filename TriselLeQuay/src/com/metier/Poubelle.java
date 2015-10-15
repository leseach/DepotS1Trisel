package com.metier;

import java.util.ArrayList;
import java.util.Calendar;


public class Poubelle {
	private String idPoubelle;
	private TypeDechet nature;
	private String idHabitation;
	private ArrayList<Levee> lesLevees;
	
	
	public Poubelle(String id, TypeDechet nature, String idHabitation) {
		this.idPoubelle = id;
		this.nature = nature;
		this.idHabitation = idHabitation;
		this.lesLevees= new ArrayList<Levee>();
	}
	public Poubelle(String id, TypeDechet nature) {
		this.idPoubelle = id;
		this.nature = nature;
		this.idHabitation = "";
		this.lesLevees= new ArrayList<Levee>();
	}
	public String getIdPoubelle() {
		return idPoubelle;
	}
	public TypeDechet getNature() {
		return nature;
	}
	public String getIdHabitation() {
		return idHabitation;
	}
	
	public void setIdHabitation(String idHabitation) {
		this.idHabitation = idHabitation;
	}
	public void ajoutLevee(Levee uneLevee)
	{
		lesLevees.add(uneLevee);
	}
	
	public ArrayList<Levee> getLesLevees() {
		return lesLevees;
	}
	public void  setLesLevees(ArrayList<Levee> lesLevees) {
		this.lesLevees = lesLevees;
	}
	@Override
	public String toString() {
		return "Poubelle [idPoubelle=" + idPoubelle + ", nature=" + nature
				+ ", idHabitation=" + idHabitation + ", lesLevees=" + lesLevees
				+ "]";
	}
	public ArrayList<Levee> getLesLevees(int an, int mois) {
		// déclaration et instanciation collection de travail
		ArrayList<Levee> lesLeveesMoisAn= new ArrayList<Levee>();
		// récupération d'une  instance de Calendar
		Calendar cal = Calendar.getInstance();
		// parcours des levées
		for(Levee uneLevee : lesLevees)
		{
			// passage par la classe Calendar pour extraire les composants de la date
			cal.setTime(uneLevee.getLaDate());
			int year = cal.get(Calendar.YEAR);
			// extraction du mois mettre + 1 car démarre à 0 et non pas 1
			int month = cal.get(Calendar.MONTH) + 1;
			// test sur date
			if ((year == an ) && (month == mois)) {
				// ajout de la levée dans  la collection
				lesLeveesMoisAn.add(uneLevee);
			}
		}
		// on délivre la collection
		return lesLeveesMoisAn;
	}
	
	public double getCout(int an, int mois) {
		// variables de cumul
		double totalPoids = 0;
		double cout = 0;
		// déclaration  collection de travail pour récupérer les levées concernées
		ArrayList<Levee> lesLeveesMA = this.getLesLevees(an, mois);
		// parcours des levées pour faire la somme des pesées
		for(Levee uneLevee : lesLeveesMA)
		{
			totalPoids = totalPoids + uneLevee.getPoids();
		}
		// prise en compte du tarif en fonction de la nature des déchets
		cout = totalPoids * nature.getTarif();
		return (double) Math.round(cout*100)/100;
	}
}
