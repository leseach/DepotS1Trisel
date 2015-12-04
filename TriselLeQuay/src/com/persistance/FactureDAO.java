package com.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metier.Facture;

public class FactureDAO extends DAO<Facture> {
	public FactureDAO()
	{
		super();
	}
	@Override
	public  boolean create(Facture obj)
	{
		Facture f = obj;
		boolean ok = false;
		ResultSet rsFacture = null;
		int mois = f.getMois();
		int an = f.getAn();
		String nomFacture = f.getNomFacture();
		String idHabitation = f.getIdHabitation();
		// vérification existence d'une facture pour l'habitation , le mois , l'année
		// possible si rectificatif facture
		// dans ce cas le nom de la facture étant identique, on ne réenregistrera pas la facture
		// le fichier facture lui sera remplacé dans le dosiier facture
		String sql = "select * from facture where moisF=" + mois + " and anF=" + an;
		sql = sql + " and idHabitation ='" + idHabitation + "'";
		try {
			rsFacture = con.createStatement().executeQuery(sql);
			if (!rsFacture.next()) {
				// pas de facture existante
				// on va donc enregistrer la facture
				sql = "insert into facture(moisF, anF, nomFacture,  idHabitation) values(";
				sql = sql + mois +", "+ an +", '" + nomFacture +"', '" + idHabitation + "')";
				con.createStatement().execute(sql);
				ok = true;
		}
		}
		catch (SQLException e)	{ 
			ok = false; 
		}
		return ok;
	}
	@Override
	public  boolean delete(Facture obj)
	{
		return false;
	}
	@Override
	public boolean update(Facture obj)
	{
	return false;
	}
	@Override
	public Facture find(int id)
	{
		 return null;
	}
	@Override
	public  Facture find(String  id)
	{
		 return null;
	}
	@Override
	public ArrayList<Facture> retrieve()
	{
		return null;
	}
}
