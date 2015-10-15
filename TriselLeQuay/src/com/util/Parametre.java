package com.util;

import java.io.File;

public class Parametre {
	private static String nomFichier = "paramAppli.ini";
	public static String getCheminBd()
	{
		// nombre de lignes  à  lire
		return lire(2);
	}
	public static String getCheminLeveeATraiter()
	{
		return lire(4);
	}
	public static String getCheminLeveeLog()
	{
		return lire(10);
	}
	public static String getCheminLeveeTraites()
	{
		return lire(6);
	}
	public static String getCheminFacturePdf()
	{
		return lire(8);
	}
	private  static String lire(int n) {
		// instanciation objet FichierTexte
		FichierTexte t=new FichierTexte();
		// ouverture du fichier en lecture
		t.openFileReader(nomFichier);
		String ligne= null;
		for (int i = 0 ; i < n ; i ++ ) {
			ligne=t.readLigne();
		}
		return ligne;
	}
	
	
	
	
	
	public static String getExtensionFichier(String nomFichier) {
		String ext = "";
		//récupérartion extension d'un fichier, on teste l'existence du .
	    if (nomFichier.contains(".")) {
	    	 // caractère spécial. veut dire n'importe quel caractère pour regex donc on met \\.
	    	String[] elem =  nomFichier.split("\\.");
	 	    ext = elem[1];
	    } 
		return ext;
	}
	    // deplace et supprime le fichier traité dans le dossier destination
		public static void transfertFichier(File fichierSource, String cheminDestination) {
	      	// on integre la date de transfert
			cheminDestination = cheminDestination + "\\"+ fichierSource.getName();
			File destination = new File(cheminDestination);
			try {
				fichierSource.renameTo(destination);
			} catch (Exception e) {
				System.out.println("échec déplacement");
			}
		}
		
	public static void creerDossier(String nom) {
		File f1=new File(nom); 
		f1.mkdir();
	}
	// ramène le nombre de fichiers du dossier à traiter
	public static int  nbLevee() {
		String cheminLevee = getCheminLeveeATraiter();
		File f = new File(cheminLevee);
		return f.listFiles().length; 
	}
	public static void enregistreParam(String url, String dossierfacture, String dossierAtraiter, String dossierTraites, String dossierLogLevee)
	{
		// ouverture du fichier en lecture
				FichierTexte t=new FichierTexte();
				t.openFileWriter(nomFichier);
				t.writeLigne("// url base de données ");
				t.writeLigne(url);
				t.writeLigne("// chemin d'accès aux fichiers xml des levées" );
				t.writeLigne(dossierAtraiter);
				t.writeLigne("// chemin sauvegarde après traitement");
				t.writeLigne(dossierTraites);
				t.writeLigne("//chemin d'accès aux factures en pdf");
				t.writeLigne(dossierfacture);
				t.writeLigne("//chemin d'accès aux fichiers logs des levées");
				t.writeLigne(dossierLogLevee);
				t.closeFileWriter();
	}
}
