package com.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metier.TypeDechet;

public class TypeDechetDAO extends DAO<TypeDechet> {

	@Override
	public boolean create(TypeDechet obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TypeDechet obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TypeDechet obj) {
		return false;
	}

	@Override
	public TypeDechet find(int id) {
		return null;
	}

	@Override
	public TypeDechet find(String id) {
		ResultSet rsTypeDechet = null;
		TypeDechet typeD = null;
		// requête de recherche des informations sur le type de déchet
		String sqlTypeDechet = "select * from typedechet where idtypedechet ='" + id + "'";
		// exécution requête , ramène 1 tuple
		try {
			rsTypeDechet = con.createStatement().executeQuery(sqlTypeDechet);
			if (rsTypeDechet.next()) {
				// instanciation objet TypeDechet
				typeD = new TypeDechet(id, rsTypeDechet.getString("libelle"), rsTypeDechet.getDouble("tarif"));
			}
		}
		catch (SQLException e) {
			typeD = null;
		}
		return typeD;
	}

	@Override
	public ArrayList<TypeDechet> retrieve() {
		return null;
	}

}
