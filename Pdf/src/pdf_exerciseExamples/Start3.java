package pdf_exerciseExamples;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public class Start3 {

	public static void main(String[] args) {
		
		  //this program reads text from a pdf-document. 
	      
		  try {
		      //Loading an existing document
		      File file = new File("D:/PC/Software/Java/Pdf/Examples/Finanzreport_Nr._03_per_04.04.2016788314.pdf") ;
		      PDDocument document = PDDocument.load(file) ;

		      //Instantiate PDFTextStripper class
		      PDFTextStripper pdfStripper = new PDFTextStripper() ;

		      //Retrieving text from PDF document
		      String text = pdfStripper.getText(document) ;
		      
		      System.out.println(text) ;

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
