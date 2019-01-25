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

public class PDF_newFormat {

	public File getResultFile(String path_pdfFiles, String path_resFile) throws IOException {
		
		Datei d = new Datei(new File(path_pdfFiles));		
		ArrayList<String> li_FileNames;
		li_FileNames = d.getFileNames(new File(path_pdfFiles));			//li_FileNames contains names of files which are stored in path_pdfFiles	      
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
				boolean written     = false;
				String line   = new String();
				StringBuffer[] sb_amounts = new StringBuffer[4];
		        for (int j=0; j<4; j++)
		        	sb_amounts[j]=new StringBuffer("");		        

				String Datum          = "^[0-9][0-9][.][0-9][0-9][.][0-9][0-9][0-9][0-9]";
				String Betrag         = (".*[+-]?[0-9]+[,][0-9]{2}.*"); 
				//String Betrag         = ("[+-]?[0-9]+[,][0-9]{2}");
				

				while ((line = bufR.readLine()) != null) {
					//System.out.println(line);											
					if (line.contains("Ü")) line = line.replace("Ü", "UE");	            	  		
					if (line.contains("ü")) line = line.replace("ü", "ue");	 
										
					if (line.contains("Ä")) line = line.replace("Ä", "AE");	            	  							
					if (line.contains("ä")) line = line.replace("ä", "ae");	  
										
					if (line.contains("Ö")) line = line.replace("Ö", "OE");	            	  												
					if (line.contains("ö")) line = line.replace("ö", "oe");	    
					
					if (!line.contains("Neuer Saldo")){					
						
						if (line.matches(Datum)){
							System.out.println(line);	
							writeActive=true;
							
							if (written){	
								//gespeicherte Summen-Einträge schreiben
								bufW.write("|" + sb_amounts[0].toString() + "|"+"\n");	
								//gespeicherte Summen-Einträge schreiben, Ende
								bufW.write(line + "|");
								System.out.println(line +"\n");
								written=false;
							}
							else{
								bufW.write(line + "|");
								System.out.println(line);
							}
						}
						else{
							
							if (writeActive){								
								if (line.matches(Betrag)){
									//Summe(n) in Filter-object speichern
									Filter f = new Filter();
									sb_amounts = f.get_amounts(line, "[+-]?[0-9]?[.]?[0-9]+[,][0-9]{2}");
									for (int i=0;i<4;i++){
										//System.out.println("Wert aus sbX: " +sb_amounts[i].toString());
									    f.set_amount(sb_amounts[i].toString());
									}
									//Summe(n) in Filter-object speichern Ende
								}								
								if (line.length()>10){	
									if (!bl.isInBlackList(line)){
										bufW.write(line + " ");
										System.out.println(line);
									}
								}
								written = true;
							}
							//else
								//System.out.println("Don't write line: " +line + " to txt.file");
						}
					}
					else{
						if (writeActive){
							writeActive=false;
							written=false;							
							bufW.write("|" + sb_amounts[0].toString() + "|"+"\n");
							System.out.println(sb_amounts[0].toString() + "\n");
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
		return resFile;	    
	}
}


