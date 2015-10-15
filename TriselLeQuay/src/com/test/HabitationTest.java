package com.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.metier.Habitation;
import com.metier.Levee;
import com.metier.Poubelle;
import com.metier.TypeDechet;
import com.metier.Usager;

public class HabitationTest {
	private Habitation habIn = null;
	private Usager u = null;
	private	TypeDechet td1;
	private	TypeDechet td2;
	private	Poubelle pb1;
	private	Poubelle pb2;
	private Date d1 = null;
	private Date d2 = null ;
	private Date d3 = null;
	private Date d4 = null;
	private Levee le1=null;
	private Levee le2=null;
	private Levee le3=null;
	private Levee le4=null;
	private SimpleDateFormat dateFormat;
	@Before
	public void setUp() throws Exception {
		
		// instanciation usager
		u = new Usager("u1", "Dupont", "Albert","63 grand-rue", "29150", "Châteaulin");
		// instanciation habitation
		habIn = new Habitation("hab1", "63 grand-rue", "29150", "Châteaulin", u);
		// instanciation 2 types de déchets
		td1 = new TypeDechet("ver", "verre", 0.10);
		td2 = new TypeDechet("plas", "plastique", 0.15);
		// instanciation 2 poubelles affectées à l'habitation
		pb1 = new Poubelle("pb1", td1 , habIn.getIdHabitation());
		pb2 = new Poubelle("pb2", td2 , habIn.getIdHabitation());
		// instanciation dates de levée
		dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			d1 = dateFormat.parse("15/05/2015");
			d2 = dateFormat.parse("30/05/2015");
			d3 = dateFormat.parse("15/06/2015");
			d4 = dateFormat.parse("30/06/2015");
		} catch (ParseException e){
			e.printStackTrace();
		} 
		// instanciation 3 levées pour la poubelle pb1, 1 pour pb2
		// 2 en mai , 1 en juin
		le1 = new Levee(d1, 5, pb1.getIdPoubelle());
		le2 = new Levee(d2, 10, pb1.getIdPoubelle());
		le3 = new Levee(d3, 12, pb1.getIdPoubelle());
		le4 = new Levee(d4, 30, pb2.getIdPoubelle());
		// ajout des levées à la poubelle 
		pb1.ajoutLevee(le1);
		pb1.ajoutLevee(le2);
		pb1.ajoutLevee(le3);
		pb2.ajoutLevee(le4);
	}

	@Test
	public void testHabitation() {
		assertNotNull(habIn);
	}

	@Test
	public void testGetUnUsager() {
		assertEquals(habIn.getUsager(), u);
		assertNotEquals(habIn.getUsager(), null);
	}

	@Test
	public void testGetIdHabitation() {
		assertEquals(habIn.getIdHabitation(), "hab1");
		assertNotEquals(habIn.getIdHabitation(), "");
	}
	@Test
	public void testGetLesPoubelles() {
		assertEquals(habIn.getLesPoubelles().size(), 0);
		assertNotEquals(habIn.getLesPoubelles().size(), null);
	}

	@Test
	public void testGetAdresseRue() {
		assertEquals(habIn.getAdresseRue(), "63 grand-rue");
	}

	@Test
	public void testGetAdresseVille() {
		assertEquals(habIn.getAdresseVille(), "Châteaulin");
	}

	@Test
	public void testGetCodePostal() {
		assertEquals(habIn.getCodePostal(), "29150");
	}
	@Test
	public void testAjoutPoubelle() {
		habIn.ajoutPoubelle(pb1);
		habIn.ajoutPoubelle(pb2);
		assertEquals(habIn.getLesPoubelles().size(), 2);
		assertEquals(habIn.getLesPoubelles().get(0), pb1);
		assertEquals(habIn.getLesPoubelles().get(1), pb2);
	}
}
