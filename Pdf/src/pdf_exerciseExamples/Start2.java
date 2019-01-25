package pdf_exerciseExamples;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage; 
import org.apache.pdfbox.pdmodel.PDPageContentStream; 
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public class Start2 {

	public static void main(String[] args) {
		
		  //this program writes text into the 7th page. 
		
	      //Loading an existing document	      
	      File file = new File("D:/PC/Software/Java/Pdf/Examples/Finanzreport_Nr._03_per_04.04.2016788314.pdf") ; 
	      
	      
	      
	      PDDocument document;
		  try {
			  document = PDDocument.load(file);
			  
			  
			  int noOfPages= document.getNumberOfPages() ;
			  System.out.print("number of pages: " +noOfPages) ;
			  
			  
			  PDPage page = document.getPage(7) ;			  
		      PDPageContentStream contentStream = new PDPageContentStream(document, page) ;
		      
		      //Begin the Content stream 
		      contentStream.beginText() ; 
		       
		      //Setting the font to the Content stream  
		      contentStream.setFont(PDType1Font.TIMES_ROMAN, 12) ;

		      //Setting the position for the line 
		      contentStream.newLineAtOffset(25, 500) ;

		      String text = "This is the sample document and we are adding content to it.";

		      //Adding text in the form of string 
		      contentStream.showText(text) ;      

		      //Ending the content stream
		      contentStream.endText() ;

		      System.out.println("Content added") ;

		      //Closing the content stream
		      contentStream.close() ;

		      //Saving the document
		      document.save("D:/PC/Software/Java/Pdf/Examples/Finanzreport_Nr._03_per_04.04.2016788314.pdf") ;

		      //Closing the document
		      document.close() ;			  
			  
			


			
			
			
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	        
	      
	}

}
