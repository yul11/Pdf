package pdf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.ListIterator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Start3 {

	public static void main(String[] args) {

		String dir     = "D:/Banken/comdirect/Finanzreports_altesFormat/";               //name of directory which contains Finanzreports			
		String resFile = "D:/Banken/comdirect/Finanzreport_altesFormat_TextFile/Ergebnis.txt";  //name of file which contains results		
		Datei d = new Datei(new File(dir));		
		ArrayList<String> li_FileNames;
		li_FileNames = d.getFileNames(new File(dir));					   //li_FileNames contains names of files which are stored in dir	      
		ListIterator<String> it_li = li_FileNames.listIterator();  

		while (it_li.hasNext()){
			try {
				File file = new File(dir + it_li.next()) ;
				PDDocument document = PDDocument.load(file) ;			
				PDFTextStripper pdfStripper = new PDFTextStripper() ;
				String text       = pdfStripper.getText(document) ;		      
				StringBuffer sb   = new StringBuffer(text);	
				BufferedReader  bufR = new BufferedReader(new StringReader(sb.toString()));		      
				BufferedWriter  bufW = new BufferedWriter(new FileWriter(resFile,true));		      		      
				boolean writeActive = false;
				String line   = new String();
				StringBuffer[] sb_amounts = new StringBuffer[4];
				StringBuffer[] sb_dates   = new StringBuffer[4];
		        for (int j=0; j<4; j++)
		        	sb_amounts[j]=new StringBuffer("");		        
		        for (int j=0; j<4; j++)
		        	sb_dates[j]=new StringBuffer("");		        

				String Datum          = (".*[0-9][0-9][.][0-9][0-9][.][0-9][0-9][0-9][0-9].*");
				String DatumElement   = ("[0-9][0-9][.][0-9][0-9][.][0-9][0-9][0-9][0-9]");				
				String Betrag         = ("[+-]?[0-9]?[0-9]?[.]?[0-9]?[0-9]?[0-9]?[,][0-9]{2}");	
				
				while ((line = bufR.readLine()) != null) {
					//System.out.println(line);	
										
					if (line.contains("Ü")) line = line.replace("Ü", "Ue");	            	  		
					if (line.contains("ü")) line = line.replace("ü", "ue");	            	  		
					if (line.contains("Ä")) line = line.replace("Ä", "Ae");	            	  		
					if (line.contains("ä")) line = line.replace("ä", "ae");	       
					
					if (!line.contains("Neuer Saldo")){	
						if (line.contains("Auszahlung GAA") || line.contains("Lastschrift Einzug") || line.contains("Uebertrag/Ueberweisung") || line.contains("Kontouebertrag") || line.contains("Storno Gebuehren")){
							//System.out.println(line);	
							
							if (sb_amounts[0].length()!=0)
								bufW.write("|" + sb_amounts[0] + "|" + "\n");							
							
							if (line.matches(Datum)){
								System.out.println(line);	
								writeActive=true;
								
								Filter f = new Filter();								
								sb_dates   = f.get_elements(line, DatumElement);
								sb_amounts = f.get_elements(line, Betrag);	
																
								bufW.write(sb_dates[0] + "|" + sb_dates[1] + "|");
							}
						}						
						else{
							if (writeActive){								
								bufW.write(line + " ");
							}
						}
					}									
					else{
						if (writeActive){
							bufW.write("\n");						
							writeActive=false;
						}
					}					
				}	
				bufW.close();	  
				document.close() ;
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}	    
	}
}


