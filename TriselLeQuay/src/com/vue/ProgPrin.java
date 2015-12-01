package com.vue;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.metier.Levee;
import com.metier.Poubelle;
import com.metier.TypeDechet;
import com.metier.Usager;
import java.sql.*;
import com.persistance.AccesBd;
import com.util.*;

import java.sql.*;
public class ProgPrin {
	public static void main(String[] args) {
	Testfacture.testfacture(2015, 7);
		//InsertionLevee.traitementLevee();
		// récupération de la connection à la base
		Connection cnx =AccesBd.getInstance();
		try {
			// création d'un objet statement
			Statement requete = cnx.createStatement();
			// texte de la requête
			String sql = "select nom, prenom from usager";
			// exécution de la requête, résultat dans un jeu d'enregistrement de type resulset
			ResultSet rsUsager = requete.executeQuery(sql);
			// parcours 
			while (rsUsager.next()) {
				// accès aux champs du rs en fonction du type et du nom
				System.out.println(rsUsager.getString("nom") + "  " + rsUsager.getString("prenom"));
			}
		} catch (SQLException e1) {
			
		}
}
}
		
		
		// format de la date
		/*SimpleDateFormat	dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
		Calendar		cal = Calendar.getInstance();
				try
				{
					// instanciation date de lev�e au format fran�ais
				Date	d1 = dateFormat.parse("15/05/2015");
					// instanciation objet Lev�e avec les deux constructeurs
				Levee	le1 = new Levee(d1, 5, "p1");
				Levee 	le2 = new Levee(2, d1, 10.5, "p2");
					System.out.println(le2.toString());
					System.out.println(le1.toString());
				} catch (ParseException e){
					e.printStackTrace();
				} 
	  }*/
//	Usager	u1 = new Usager("u1", "Dupont", "Laurent");
//	Usager	u2 = new Usager("u1", "Albert", "Andr�", "user2", "mdp2");
//	TypeDechet td = new TypeDechet("ver", "verre", 0.10);
//System.out.println(u1.toString());
//System.out.println(td.toString());

//instanciation d'un type d�chet n�cessaire dans l'objet Poubelle
	
/*	// instanciation Poubelle avec les deux constructeurs
Poubelle 	pb1 = new Poubelle("pb1", td);
//instanciation dates de lev�es au format fran�ais
SimpleDateFormat	dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
	try
	{
	Date 	d1 = dateFormat.parse("15/05/2015");
	Date d2 = dateFormat.parse("30/05/2015");
		// instanciation 2 lev�es par poubelle
		Levee le1 = new Levee(d1, 5, pb1.getIdPoubelle());
		Levee le2 = new Levee(d2, 10, pb1.getIdPoubelle());
		pb1.ajoutLevee(le1);
		pb1.ajoutLevee(le2);
		// pour affectation des lev�es par liste
		// et non pas par la m�thode ajout
		System.out.println(pb1.toString());
	} catch (ParseException e){
		e.printStackTrace();
	} 	
	}*/
	




