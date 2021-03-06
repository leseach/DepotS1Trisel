package com.util;

import java.io.*;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.metier.Levee;
import com.persistance.LeveeDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
public class InsertionLevee {
	
	public static void traitementLevee()  {
		// récupération  du chemin du dossier des levées  à traiter
		String cheminATraiter = Parametre.getCheminLeveeATraiter();
		// récupération  chemin des levées traitées
		String cheminTraite = Parametre.getCheminLeveeTraites();	
		// récupération  chemin des log
		String cheminLog = Parametre.getCheminLeveeLog();	
		// ouverture du dossier des levées
		File dossierLevee = new File(cheminATraiter);
		// déclaration extension du fichier
		String extension = null;
		String cheminDestination = null;
		// récupérationliste fichier du dossier
		File[] listeFichier = dossierLevee.listFiles();
		// test si il est vide
		if ( listeFichier.length == 0) {
			System.out.println ("Aucun fichier à traiter");
		}
		else {
			for (File fichier : listeFichier) {
				// test si c'est un fichier
				if (fichier.isFile()) {
					// on récupère l'extension du fichier
					extension = Parametre.getExtensionFichier(fichier.getName());
					// on teste l'extension pour savoir ce qu'il y a à  faire
					switch (extension)
					{
					case "txt" :
						// appel de la procédure de traitement du fichier xml
						traitementFichierTexte(fichier.getAbsolutePath());
						// déplacement  du  fichier traité
						Parametre.transfertFichier(fichier,  cheminTraite);
						/*cheminDestination =  cheminTraite + "\\"  + fichier.getName();
						fichier.renameTo(new File(cheminDestination));*/
						break ;
					case "xml" :
						// appel de la procédure de traitement du fichier xml
						if (traitementFichierXml(fichier.getAbsolutePath())) { 
						// déplacement  du  fichier traité
						Parametre.transfertFichier(fichier,  cheminTraite);
						}
						break ;
					default :
						// déplacement du  fichier dans log
						Parametre.transfertFichier(fichier,  cheminLog);
						break;
					}
				}
				else {
					// déplacement du  dossier dans log
					Parametre.transfertFichier(fichier,  cheminLog);
				}
			}
		}
	}

	private static void traitementFichierTexte(String cheminLevee) {
		
		String ligne = null;
		String date = null;
		java.util.Date laDate = null;
		String data[] = null;
		String idPoubelle= null;
		double poids = 0 ;
		Levee l = null;
		LeveeDAO lDAO = new LeveeDAO();

		// format de la date en français
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		// instanciation d'un objet fichier texte
		FichierTexte ft = new FichierTexte();
		// ouverture du fichier en lecture
		ft.openFileReader(cheminLevee);
		// on récupère la date sous forme d'une chaine  
		date = ft.readLigne();
		try {
			// conversion de la chaine en date java.util
			laDate = sdf.parse(date);
			// parcours des lignes levee
			// parcours tant qu'il y a des lignes   à  lire 
			//  retour de readLigne à null en fin de fichier
			while ((ligne = ft.readLigne()) != null) {
				// extraction des données séparateur : par un split dans un tableau de chaine
				data = ligne.split(":");
				// conversion des données en fonction du type attendu par le constructeur
				poids = Double.parseDouble(data[2]);
				idPoubelle = data[1];
				// instanciation objet levée
				l = new Levee(laDate, poids, idPoubelle);
				lDAO.create(l);
			} 
		}
		catch (ParseException e) {
		}
		finally {
			ft.closeFileReader();
		}
	}
	
	private  static boolean traitementFichierXml(String cheminLevee) {
		boolean ok = true;
		java.util.Date laDate = null;
		String idPoubelle= null;
		double poids = 0 ;
		Levee l = null;
		LeveeDAO lDAO = new LeveeDAO();
		// format de la date en français
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//déclaration document xml
		Document document =  null;
		// déclaration élément racine
		Element racine = null;
		//On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		File f = null;
		try {
			f = new File(cheminLevee);
			// ouverture du fichier xml
			document = sxb.build(f);
			// récupération de l'élément racine
			racine = document.getRootElement();
			// récupération de l'élément date
			Element ladate = racine.getChild("Date");
			// conversion de la chaine en date java.util
			laDate = sdf.parse(ladate.getText());
			//On crée une Liste contenant tous les noeuds "Levee"
			List<Element> listeLevee = racine.getChildren("Levee");
			// parcours des levées
			for (Element e: listeLevee)
			{
				// on récupère le numéro de poubelle
				idPoubelle = e.getChild("poubelle").getText();
				// on récupère  le poids 
				poids = Double.parseDouble(e.getChild("poids").getText());
				// instanciation objet levée
				l = new Levee(laDate, poids, idPoubelle);
				lDAO.create(l);
			} 
		}
		catch (ParseException e) {
			ok = false;	
			}
		catch (JDOMException e2) { 
			document = null;
			f = null;
			ok = false;// erreur de parsage du fichier
			}
		catch (IOException e2) {// erreur fichier non trouvé
			ok = false;
			}
		return ok;
	}
}
