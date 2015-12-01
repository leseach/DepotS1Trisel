package com.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metier.Levee;
import com.metier.Poubelle;
import com.metier.TypeDechet;

public class PoubelleDAO extends DAO<Poubelle> {

	@Override
	public boolean create(Poubelle obj) {
		return false;
	}

	@Override
	public boolean delete(Poubelle obj) {
		return false;
	}

	@Override
	public boolean update(Poubelle obj) {
		return false;
	}

	@Override
	public Poubelle find(int id) {
		return null;
	}

	@Override
	public Poubelle find(String id) {
		Poubelle p = null;
		TypeDechet typeD = null;
		Levee lv = null;
		ResultSet rsPoubelle = null;
		ResultSet rsLevee = null;
		TypeDechetDAO tdDAO = new TypeDechetDAO();
		LeveeDAO lvDAO = new LeveeDAO();
		// recherche des poubelles de l'habitation dans la base
		String sqlPoubelle = "select * from poubelle where idPoubelle ='"+ id + "'";
		try {
			rsPoubelle = con.createStatement().executeQuery(sqlPoubelle);
			// parcours car requête ramène 0 ou n tuples
			if (rsPoubelle.next()) {
				// recherche type de déchet
				String type = rsPoubelle.getString("idTypeDechet");
				typeD = tdDAO.find(type);
				// instanciation objet poubelle
				p = new Poubelle(rsPoubelle.getString("idPoubelle"), typeD, rsPoubelle.getString("idHabitation"));
				// recherche des levées
				String sqlLevee = "select idLevee from levee where idPoubelle='" + p.getIdPoubelle() + "'";
				rsLevee = con.createStatement().executeQuery(sqlLevee);
				//parcours des levées
				while (rsLevee.next()) {
					lv = lvDAO.find(rsLevee.getInt("idLevee"));
					p.ajoutLevee(lv);
				}
			}
		}
		catch (SQLException e)	{ 
			p = null; 
		}
		return p;
	}

	@Override
	public ArrayList<Poubelle> retrieve() {
		return null;
	}

}
