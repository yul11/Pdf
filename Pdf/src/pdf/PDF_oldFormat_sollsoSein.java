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

public class PDF_oldFormat_sollsoSein {

	public File getResultFile(String path_pdfFiles, String path_resFile) throws IOException {

		Datei d = new Datei(new File(path_pdfFiles));		
		ArrayList<String> li_FileNames;
		li_FileNames = d.getFileNames(new File(path_pdfFiles));	      //li_FileNames contains names of files which are stored in path_pdfFiles	      
		ListIterator<String> it_li = li_FileNames.listIterator();  
        Blacklist bl = new Blacklist();
        File resFile = new File(path_resFile);
		        
		while (it_li.hasNext()){
			try {
				File file = new File(path_pdfFiles + it_li.next()) ;
				PDDocument document = PDDocument.load(file) ;			
				PDFTextStripper pdfStripper = new PDFTextStripper() ;
				String text       = pdfStripper.getText(document) ;		      
				StringBuffer sb   = new StringBuffer(text);	
				BufferedReader  bufR = new BufferedReader(new StringReader(sb.toString()));					
				BufferedWriter  bufW = new BufferedWriter(new FileWriter(resFile,true));				
				boolean writeActive = false;
				boolean TagesgeldPLUS = false;
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
				
				do{					
					line = bufR.readLine();
					if (line.contains("Ü")) line = line.replace("Ü", "UE");	            	  		
					if (line.contains("ü")) line = line.replace("ü", "ue");	 
										
					if (line.contains("Ä")) line = line.replace("Ä", "AE");	            	  							
					if (line.contains("ä")) line = line.replace("ä", "ae");	  
										
					if (line.contains("Ö")) line = line.replace("Ö", "OE");	            	  												
					if (line.contains("ö")) line = line.replace("ö", "oe");	   
					
					if (line.equals("Tagesgeld PLUS"))
						TagesgeldPLUS=true;
					
					if (!TagesgeldPLUS){
						if (line.contains("Auszahlung GAA") || line.contains("Lastschrift Einzug") || line.contains("UEbertrag/UEberweisung") || line.contains("Kontouebertrag") || line.contains("Storno Gebuehren")){
							
							writeActive=true;						
							if (sb_amounts[0].length()!=0)
								bufW.write("|" + sb_amounts[0] + "|" + "\n");							
							
							if (line.matches(Datum)){
								//System.out.println(line);								
								Filter f = new Filter();								
								sb_dates   = f.get_elements(line, DatumElement);
								sb_amounts = f.get_elements(line, Betrag);																
								bufW.write(sb_dates[0] + "|" + sb_dates[1] + "|");
							}
						}						
						else{
							if (writeActive){
								System.out.println(line);
								if (!bl.isInBlackList(line))
									bufW.write(line + " ");
							}
						}
					}						
				}
				while (!line.contains("Neuer Saldo"));	
				
				if (!TagesgeldPLUS){
					//amount for last entry
					if (sb_amounts[0].length()!=0)
						bufW.write("|" + sb_amounts[0] + "|" + "\n");						
				}
				else
					TagesgeldPLUS=false;
				
				bufW.close();	  
				document.close() ;	
				
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resFile;	
	}
}


