package com.metier;



public class Usager {
	private String idUsager;
	private String nom;
	private String prenom;
	private String adresseRue;
	private String codePostal;
	private String adresseVille;
	private String nomUser; 
	private String motDePasse;
	
	// on peut avoir 2 constructeurs
	public Usager(String idUsager, String nom, String prenom, String adresseRue, String adresseVille, String codePostal) {
		this.idUsager = idUsager;
		this.nom = nom;
		this.prenom = prenom;
		this.adresseRue = adresseRue;
		this.adresseVille = adresseVille;
		this.codePostal = codePostal;
		nomUser = "";
		motDePasse = "";
	}
	
	public Usager(String idUsager, String nom, String prenom, String adresseRue, String adresseVille, String codePostal, String nomUser,
			String motDePasse) {
		super();
		this.idUsager = idUsager;
		this.nom = nom;
		this.prenom = prenom;
		this.adresseRue = adresseRue;
		this.adresseVille = adresseVille;
		this.codePostal = codePostal;
		this.nomUser = nomUser;
		this.motDePasse = motDePasse;
	}

	public String getAdresseRue() {
		return adresseRue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public String getAdresseVille() {
		return adresseVille;
	}

	public String getIdUsager() {
		return idUsager;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Override
	public String toString() {
		return "Usager [idUsager=" + idUsager + ", nom=" + nom + ", prenom="
				+ prenom + ", nomUser=" + nomUser + ", motDePasse="
				+ motDePasse + "]";
	}
			
	
}
