package com.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metier.Habitation;
import com.metier.Poubelle;
import com.metier.Usager;

public class HabitationDAO extends DAO<Habitation> {

	@Override
	public boolean create(Habitation obj) {
		return false;
	}

	@Override
	public boolean delete(Habitation obj) {
		return false;
	}

	@Override
	public boolean update(Habitation obj) {
		return false;
	}

	@Override
	public Habitation find(int id) {
		return null;
	}

	@Override
	public Habitation find(String id) {
		Habitation hab = null ;
		Usager u = null;
		Poubelle pb = null;
		String  sqlHab, sqlPoubelle; 
		String adresseRue = null;
		String adresseVille = null;
		String codePostal = null;
		ResultSet rsHab, rsPoubelle;
		PoubelleDAO pbDAO = new PoubelleDAO();
		UsagerDAO uDAO = new UsagerDAO();
		// requête de sélection de l'habitation
		sqlHab = "select * from habitation  where idHabitation ='" + id +"'";
		try {
			// exécution requête
			rsHab = con.createStatement().executeQuery(sqlHab);
			// tentative lecture car 0 ou 1 tuple
			if (rsHab.next())
			{
				// récupération de l'usager concerné
				u = uDAO.find(rsHab.getString("idUsager"));
				// récupération des données pour notation plus courte dans appel du constructeur
				adresseRue = rsHab.getString("adresseRue");
				adresseVille =  rsHab.getString("adresseVille");
				codePostal = rsHab.getString("codePostal");
				// instanciation objet habitation
				hab = new Habitation(id, adresseRue, codePostal, adresseVille, u);
				// recherche des poubelles de l'habitation dans la base
				sqlPoubelle = "select idPoubelle from poubelle where idHabitation ='"+id +"'";
				rsPoubelle = con.createStatement().executeQuery(sqlPoubelle);
				// parcours car requête ramène 0 ou n tuples
				while (rsPoubelle.next()) {
					// récupération objet poubelle
					pb = pbDAO.find(rsPoubelle.getString("idPoubelle"));
					// ajout de la poubelle à l'habitation
					hab.ajoutPoubelle(pb);
				}
			}
		}
		catch (SQLException e)	{ 
			hab = null; 
		}
		return hab;
	}

	
	@Override
	public ArrayList<Habitation> retrieve()
	{
		ArrayList<Habitation> lesHabitations = new ArrayList<Habitation>();
		ResultSet rsHab = null;
		String sql = "select idHabitation from habitation";
		try {
			rsHab = con.createStatement().executeQuery(sql);
			while  (rsHab.next())
			{
				lesHabitations.add(this.find(rsHab.getString("idhabitation")));
			}
		}
		catch (SQLException e)	{ 

		}
		return lesHabitations;
	}

}
