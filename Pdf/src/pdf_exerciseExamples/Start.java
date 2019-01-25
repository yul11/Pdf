package pdf_exerciseExamples;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

public class Start {

	public static void main(String[] args) {
		
		
		  //this program sets the document-informations
		
	      //Loading an existing document	      
	      File file = new File("D:/PC/Software/Java/Pdf/Examples/Finanzreport_Nr._03_per_04.04.2016788314.pdf") ; 
	      	      
	      
	      PDDocument document;
		  try {
			  document = PDDocument.load(file);
			  
			  
			  int noOfPages= document.getNumberOfPages() ;
			  System.out.print("number of pages: " +noOfPages) ;
			
		      //Adding a blank page to the document 
		      document.addPage(new PDPage() );  
		      		      
		      
		      //Creating the PDDocumentInformation object 
		      PDDocumentInformation pdd = document.getDocumentInformation() ;

		      //Setting the author of the document
		      pdd.setAuthor("author: juo") ;
		      System.out.println("\nAuthor of the document is :"+ pdd.getAuthor() );
		       
		      // Setting the title of the document
		      pdd.setTitle("title: juos document") ; 
		      

		      //Saving the document 
		      document.save("D:/PC/Software/Java/Pdf/Examples/Finanzreport_Nr._03_per_04.04.2016788314.pdf") ;

		      //Closing the document  
		      document.close() ; 
		      
		      System.out.println("PDF loaded") ; 

			
			
			
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	        
	      
	}

}
