package com.metier;

public class Facture {
	private int idFacture;
	private int mois;
	private int an;
	private String nomFacture;
	// idHabitation et non pas objet pour ne pas alourdir la persistance
	// va servir uniquement pour enregistrer et retrouver les factures pour un idHabitation
	// navigabilite dans le diagramme de classes pas mis en place
	// équilibre à trouver entre tout objet et service de la couche de persistance
	// limite le nombre d'obets à "monter" et à stocker en mémoire
	// à faire dans les autres versions
	private String idHabitation;
	// surcharge de constructeur
	// constructeur pour écupérer objet facture depuis la base de données
	public Facture(int idFacture, int mois, int an, String nomFacture, String idHabitation) {
		super();
		this.idFacture = idFacture;
		this.mois = mois;
		this.an = an;
		this.nomFacture = nomFacture;
		this.idHabitation = idHabitation;
	}
	// constructeur pour créer une nouvelle facture
	public Facture(int mois, int an, String nomFacture, String idHabitation) {
		super();
		this.idFacture = 0;
		this.mois = mois;
		this.an = an;
		this.nomFacture = nomFacture;
		this.idHabitation = idHabitation;
	}
	public int getIdFacture() {
		return idFacture;
	}
	public int getMois() {
		return mois;
	}
	public int getAn() {
		return an;
	}
	public String getNomFacture() {
		return nomFacture;
	}
	public String getIdHabitation() {
		return idHabitation;
	}

	}

