package com.vue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.metier.Levee;
import com.persistance.LeveeDAO;
import com.util.InsertionLevee;

public class TestInsertionLevee {

	public static void main(String[] args) {
		Date d1 = null;
		SimpleDateFormat dateFormat = null;
		dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
		Levee le1 = null;
		Levee l = null;
		LeveeDAO lDAO = new LeveeDAO();
		try
		{
			// instanciation date de levée au format français
			d1 = dateFormat.parse("15/05/2015");
			// instanciation objet Levée 
			le1 = new Levee(d1, 5, "pb1");
			if (lDAO.create(le1)) {
				System.out.println("insertion effectuée");
			}
			
				else
				{
					System.out.println("échec insertion");
				}
		} catch (ParseException e){
			e.printStackTrace();
		} 
		
		
		InsertionLevee.traitementLevee();
	}

}
