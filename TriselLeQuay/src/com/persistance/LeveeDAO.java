package com.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metier.Levee;
public class LeveeDAO extends DAO<Levee> {

		@Override
		public  boolean create(Levee obj)
		{  
			boolean ok ;
			// conversion de la date en java.sql.Date
			java.sql.Date laDatesql= new java.sql.Date(obj.getLaDate().getTime());
			// la date doit être entre ' ' pour que cela fonctionne
			String sqlLevee = "insert into levee(laDate, poids, idPoubelle) values('";
			sqlLevee = sqlLevee + laDatesql + "', "+ obj.getPoids() + ", '" + obj.getIdPoubelle() + "')";
			System.out.println(sqlLevee);
			try {
				con.createStatement().executeUpdate(sqlLevee);
				ok = true;
			}
			catch (SQLException e)	{ 
				ok = false; 
			}
			return ok;
	}

	@Override
	public boolean delete(Levee obj) {
		
		return false;
	}

	@Override
	public boolean update(Levee obj) {
		
		return false;
	}

	@Override
	public Levee find(int id) {
		
		Levee uneLevee = null;
		ResultSet rsLevee = null;
		String sqlLevee = "select * from levee where idLevee=" + id;
		try {
			rsLevee = con.createStatement().executeQuery(sqlLevee);
			//parcours des levées
			if (rsLevee.next()) {
				uneLevee = new Levee(rsLevee.getInt("idlevee"), rsLevee.getDate("ladate"), rsLevee.getDouble("poids"),rsLevee.getString("idPoubelle"));
			}
		}
		catch (SQLException e)	{ 
			uneLevee = null; 
		}
		return uneLevee;
	}

	@Override
	public Levee find(String id) {
		return null;
	}

	@Override
	public ArrayList<Levee> retrieve() {
		return null;
	}

}
