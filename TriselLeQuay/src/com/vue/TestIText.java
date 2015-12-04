package com.vue;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.*;

public class TestIText {

	public static void main(String[] args) {
		Document document = new Document ();
		try {
			PdfWriter.getInstance (document,
					new FileOutputStream ("HelloWorld.pdf"));
			document.open ();
			document.add(new Paragraph("Un document PDF Bonjour tout le monde."));
		Anchor 	ancre = new Anchor ( 
		            "http://tutorials.jenkov.com/java-itext/index.html"); 
		            ancre.setReference ( 
		            "http://tutorials.jenkov.com/java-itext/index.html"); 
		            document.add(ancre);
		            PdfPTable table = new PdfPTable(3);

			document.close ();
			Runtime.getRuntime().exec("explorer.exe " + "HelloWorld.pdf");
		} catch (DocumentException e) {
			e.printStackTrace ();
		} catch (FileNotFoundException e) {
			e.printStackTrace ();
		}
		catch (IOException ex) { 
			ex.printStackTrace();
		}
	}
}





