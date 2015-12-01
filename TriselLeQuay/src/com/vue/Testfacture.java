package com.vue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.metier.Habitation;
import com.metier.Levee;
import com.metier.Poubelle;
import com.metier.Usager;
import com.persistance.HabitationDAO;
public class Testfacture {
	private static final String  tabMois[]={"","janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre" };
	public static void testfacture(int an, int mois){
		HabitationDAO HabitationDAO = new HabitationDAO();
		ArrayList<Habitation> lesHabitations = HabitationDAO.retrieve();
		if(lesHabitations.size() != 0){
			for(Habitation h : lesHabitations){
				editionElementFactureV2(h, an, mois);
			}
		}
	}

	private static void editionElementFacture(Habitation h, int an, int mois){
		//Date Format
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// collection de poubelle
		ArrayList<Poubelle> lesPoubelles = null;
		// collection de Levee
		ArrayList<Levee> lesLevees = null;
		// variable de cumul pour le total général
		double totalGeneralHT = 0;
		double totalPoubelle = 0;
		double nbKilo = 0;
		double prixHT = 0;
		double totalLevee = 0;
		double tva = 0;
		double tauxTva = 0.2;
		double totalTTC = 0;
		//récupération des données de l'habitation
		String nom = h.getUsager().getNom();
		String prenom = h.getUsager().getPrenom();
		String adresseRue = h.getAdresseRue();
		String adresseVille = h.getAdresseVille();
		String cp = h.getCodePostal();
		System.out.println("------------------------------------");
		System.out.println(nom + " " + prenom);
		System.out.println(adresseRue);
		System.out.println(cp + " " + adresseVille);
		//Info Usager
		Usager usager = h.getUsager();
		String codeUsager = usager.getIdUsager();
		String adresseRueUsager = usager.getAdresseRue();
		String adresseVilleUsager = usager.getAdresseVille();
		String cpUsager = usager.getCodePostal();
		// affichage entête facture
		System.out.println();
		System.out.println("Adresse de facturation : ");
		System.out.println(adresseRueUsager);
		System.out.println(cpUsager + " " + adresseVilleUsager);
		System.out.println("------------------------------------");
		System.out.println("Code usager : " + codeUsager);
		System.out.println("Récapitulatif des pesées des poubelles au mois de : " + tabMois[mois]);
		// récupération des poubelles dans une collection de travail
		// plus optimisé car pas un seul appel à la méthode getLesPoubelles()
		lesPoubelles = h.getLesPoubelles();
		// parcours des poubelles de l'habitation
		for(Poubelle p : lesPoubelles){
			//Informations sur la poubelle
			String codePoubelle = p.getIdPoubelle();
			String nature = p.getNature().getLibelle();
			// affichage entête poubelle
			System.out.println("------------------------------------");
			System.out.println("Poubelle : " + codePoubelle + " - Nature des déchets : " + nature);
			// mise à zéro cumul poubelle
			totalPoubelle = 0;
			//affichage entête levée
			System.out.println("Date de pesée   Nombre de Kilos Prix HT au Kilo Total HT ");       
			//récupération levées du mois et de l'année pour la poubelle
			lesLevees = p.getLesLevees(an, mois);
			// parcours des levées
			for(Levee lv : lesLevees){
				//traitement levée
				Date d = new Date(lv.getLaDate().getTime());
				String date = dateFormat.format(d);
				nbKilo = lv.getPoids();
				prixHT = p.getNature().getTarif();
				totalLevee = (double) Math.round((nbKilo * prixHT) * 1000) / 1000;
				// affichage données levée
				System.out.println(date + "  " + nbKilo + "   " + prixHT + "   " + totalLevee);
				// cumul dans totalPoubelle
				totalPoubelle = totalPoubelle + totalLevee;
			}
			// affichage total poubelle
			totalPoubelle = (double) Math.round(totalPoubelle * 100) / 100;
			// comparaison avec méthode getCout de habitation
			System.out.println("....................................");
			System.out.println("Total HT "  + totalPoubelle);
			// cumul dans totalGeneralHt
			totalGeneralHT = totalGeneralHT +  totalPoubelle;
		}
		tva =(double) Math.round((totalGeneralHT * tauxTva) * 100) / 100;
		totalTTC = totalGeneralHT + tva;
		// affichage totaux
		System.out.println("------------------------------------");
		System.out.println("			Total général HT : " + totalGeneralHT);
		System.out.println("			     Montant TVA : " + tva);
		System.out.println("			       Total TTC : " + totalTTC);
		System.out.println();
		}
	private static void editionElementFactureV2(Habitation h, int an, int mois){
		//Date Format
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// collection de poubelle
		ArrayList<Poubelle> lesPoubelles = null;
		// collection de Levee
		ArrayList<Levee> lesLevees = null;
		// variable de cumul pour le total général
		
		double nbKilo = 0;
		double prixHT = 0;
		double totalLevee = 0;
		double tva = 0;
		double tauxTva = 0.2;
		double totalTTC = 0;
		//récupération des données de l'habitation
		String nom = h.getUsager().getNom();
		String prenom = h.getUsager().getPrenom();
		String adresseRue = h.getAdresseRue();
		String adresseVille = h.getAdresseVille();
		String cp = h.getCodePostal();
		System.out.println("------------------------------------");
		System.out.println(nom + " " + prenom);
		System.out.println(adresseRue);
		System.out.println(cp + " " + adresseVille);
		//Info Usager
		Usager usager = h.getUsager();
		String codeUsager = usager.getIdUsager();
		String adresseRueUsager = usager.getAdresseRue();
		String adresseVilleUsager = usager.getAdresseVille();
		String cpUsager = usager.getCodePostal();
		// affichage entête facture
		System.out.println();
		System.out.println("Adresse de facturation : ");
		System.out.println(adresseRueUsager);
		System.out.println(cpUsager + " " + adresseVilleUsager);
		System.out.println("------------------------------------");
		System.out.println("Code usager : " + codeUsager);
		System.out.println("Récapitulatif des pesées des poubelles au mois de : " + tabMois[mois]);
		// récupération des poubelles dans une collection de travail
		// plus optimisé car pas un seul appel à la méthode getLesPoubelles()
		lesPoubelles = h.getLesPoubelles();
		// parcours des poubelles de l'habitation
		for(Poubelle p : lesPoubelles){
			//Informations sur la poubelle
			String codePoubelle = p.getIdPoubelle();
			String nature = p.getNature().getLibelle();
			// affichage entête poubelle
			System.out.println("------------------------------------");
			System.out.println("Poubelle : " + codePoubelle + " - Nature des déchets : " + nature);
			//affichage entête levée
			System.out.println("Date de pesée   Nombre de Kilos Prix HT au Kilo Total HT ");       
			//récupération levées du mois et de l'année pour la poubelle
			lesLevees = p.getLesLevees(an, mois);
			// parcours des levées
			for(Levee lv : lesLevees){
				//traitement levée
				Date d = new Date(lv.getLaDate().getTime());
				String date = dateFormat.format(d);
				nbKilo = lv.getPoids();
				prixHT = p.getNature().getTarif();
				totalLevee = (double) Math.round((nbKilo * prixHT) * 1000) / 1000;
				// affichage données levée
				System.out.println(date + "  " + nbKilo + "   " + prixHT + "   " + totalLevee);
			}
			// affichage total poubelle
			System.out.println("....................................");
			System.out.println("Total HT "  + p.getCout(an, mois));
		}
		tva =(double) Math.round((h.getCout(an, mois) * tauxTva) * 100) / 100;
		totalTTC = h.getCout(an, mois) + tva;
		// affichage totaux
		System.out.println("------------------------------------");
		System.out.println("			Total général HT : " + h.getCout(an, mois));
		System.out.println("			     Montant TVA : " + tva);
		System.out.println("			       Total TTC : " + totalTTC);
		System.out.println();
		}
}




