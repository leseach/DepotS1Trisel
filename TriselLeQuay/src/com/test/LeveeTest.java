package com.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.metier.Levee;

public class LeveeTest {
	private Date d1 = null;
	private Date d2 = null;
	private SimpleDateFormat dateFormat = null;
	private Calendar cal = null;
	private Levee le1 = null;
	private Levee le2 = null;
	
	@Before
	public void setUp() throws Exception {
		// format de la date
		dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
		cal = Calendar.getInstance();
		try
		{
			// instanciation date de levée au format français
			d1 = dateFormat.parse("15/05/2015");
			// instanciation objet Levée avec les deux constructeurs
			le1 = new Levee(d1, 5, "p1");
			le2 = new Levee(2, d1, 10.5, "p2");
		} catch (ParseException e){
			e.printStackTrace();
		} 
	}

	@Test
	public void testLevee() {
		// test existence objet 
		//vérifier les 2 instanciations
		assertNotNull(le1);
		assertNotNull(le2);
	}

	@Test
	public void testGetLaDate() {

		//    à  faire sur les 2 objets pour vérifier les affectations 
		// dans les 2  constructeurs
		//récupération de la levée  avec manipulation de date
		cal.setTime(le1.getLaDate());
		int year = cal.get(Calendar.YEAR);
		// extraction du mois mettre + 1 car démarre  à 0 et non pas 1
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		// comparaison des éléments  extraits
		assertEquals(year,2015);
		assertEquals(month, 5);
		assertEquals(day, 15);
		// date de levée  avec dateFormat
		assertEquals("15/05/2015", dateFormat.format(le2.getLaDate()));
	
	}
	

	@Test
	public void testSetLaDate() {
		// changement de date, peut-être fait sur un seul objet
		try
		{
			// instanciation date de levée au format français
			d2 = dateFormat.parse("30/06/2015");
			// changement date de levée
			le1.setLaDate(d2);
			assertEquals("30/06/2015", dateFormat.format(le1.getLaDate()));

		} catch (ParseException e){
			e.printStackTrace();
		} 
	}

	@Test
	public void testGetPoids() {
		//  à faire sur les 2 objets
		// situation de réussite
		assertTrue(le1.getPoids() == 5);
		// situation d'échec
		assertFalse(le1.getPoids() == 0);
		// situation de réussite
		assertTrue(le2.getPoids() == 10.5);
		// situation d'échec
		assertFalse(le2.getPoids() == 0);
	}
	@Test
	public void testGetIdPoubelle() {
		// à faire sur les 2 objets
		assertEquals(le1.getIdPoubelle(), "p1");
		assertNotEquals(le1.getIdPoubelle(), "");
		assertEquals(le2.getIdPoubelle(), "p2");
		assertNotEquals(le2.getIdPoubelle(), "");
	}
	@Test
	public void testGetIdLevee() {
		// à faire sur les 2 objets
		assertTrue(le1.getIdLevee() == 0);
		assertFalse(le1.getIdLevee() == 999);
		assertTrue(le2.getIdLevee() == 2);
		assertFalse(le2.getIdLevee() == 999);
	}
	@Test
	public void testSetPoids() {
		// peut-être fait sur un seul objet
		le1.setPoids(10);
		assertFalse(le1.getPoids() == 5);
		assertTrue(le1.getPoids() == 10);
		le2.setPoids(15);
		assertFalse(le2.getPoids() == 5);
		assertTrue(le2.getPoids() == 15);
		}
	@Test
	public void testToString() {
		String attenduL1 = "Levee [idLevee=0, laDate=Fri May 15 00:00:00 CEST 2015, poids=5.0, idPoubelle=p1]";
		String attenduL2 = "Levee [idLevee=2, laDate=Fri May 15 00:00:00 CEST 2015, poids=10.5, idPoubelle=p2]";
		assertEquals(le1.toString(), attenduL1);
		assertNotEquals(le1.toString(), "");
		assertEquals(le2.toString(), attenduL2);
		assertNotEquals(le2.toString(), "");
	}
}
