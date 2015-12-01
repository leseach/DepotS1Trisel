package com.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metier.Usager;



public class UsagerDAO extends DAO<Usager> {

	@Override
	public boolean create(Usager obj) {
		return false;
	}

	@Override
	public boolean delete(Usager obj) {
		return false;
	}

	@Override
	public boolean update(Usager obj) {
		return false;
	}

	@Override
	public Usager find(String id) {
		Usager unUsager = null;
		ResultSet rsUsager = null;
		String nom = null;
		String prenom = null;
		String adresseRue = null;
		String adresseVille = null;
		String codePostal = null;
		String nomUser = null;
		String motDePasse = null;
		String sql = "select * from usager where idUsager ='"+id +"'";
		try {
			rsUsager = con.createStatement().executeQuery(sql);
			if (rsUsager.next())
			{ 
				// récupération dans variables de travail pour diminuer 
				// la taille de l'appel au constructeur
				nom = rsUsager.getString("nom");
				prenom = rsUsager.getString("prenom");
				adresseRue = rsUsager.getString("adresseRue");
				adresseVille = rsUsager.getString("adresseVille");
				codePostal = rsUsager.getString("codePostal");
				nomUser = rsUsager.getString("nomUser");
				motDePasse = rsUsager.getString("motDePasse");
				// instanciation objet Usager
				unUsager = new Usager(id, nom, prenom,adresseRue,adresseVille,codePostal,nomUser,
						motDePasse); 
			}
		}
		catch (SQLException e)	{ 
			unUsager = null;
		}
		return unUsager;
	}

	@Override
	public ArrayList<Usager> retrieve() {
		return null;
	}

	@Override
	public Usager find(int id) {
		return null;
	}

}
