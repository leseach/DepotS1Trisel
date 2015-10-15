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
import com.util.*;

public class ProgPrin {
	public static void main(String[] args) {
		/*InsertionLevee.traitementLevee();
		// format de la date
		SimpleDateFormat	dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
		Calendar		cal = Calendar.getInstance();
				try
				{
					// instanciation date de levée au format français
				Date	d1 = dateFormat.parse("15/05/2015");
					// instanciation objet Levée avec les deux constructeurs
				Levee	le1 = new Levee(d1, 5, "p1");
				Levee 	le2 = new Levee(2, d1, 10.5, "p2");
					System.out.println(le2.toString());
					System.out.println(le1.toString());
				} catch (ParseException e){
					e.printStackTrace();
				} 
	  }*/
//	Usager	u1 = new Usager("u1", "Dupont", "Laurent");
//	Usager	u2 = new Usager("u1", "Albert", "André", "user2", "mdp2");
	TypeDechet td = new TypeDechet("ver", "verre", 0.10);
//System.out.println(u1.toString());
//System.out.println(td.toString());

//instanciation d'un type déchet nécessaire dans l'objet Poubelle
	
	// instanciation Poubelle avec les deux constructeurs
Poubelle 	pb1 = new Poubelle("pb1", td);
//instanciation dates de levées au format français
SimpleDateFormat	dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
	try
	{
	Date 	d1 = dateFormat.parse("15/05/2015");
	Date d2 = dateFormat.parse("30/05/2015");
		// instanciation 2 levées par poubelle
		Levee le1 = new Levee(d1, 5, pb1.getIdPoubelle());
		Levee le2 = new Levee(d2, 10, pb1.getIdPoubelle());
		pb1.ajoutLevee(le1);
		pb1.ajoutLevee(le2);
		// pour affectation des levées par liste
		// et non pas par la méthode ajout
		System.out.println(pb1.toString());
	} catch (ParseException e){
		e.printStackTrace();
	} 	
	}}



