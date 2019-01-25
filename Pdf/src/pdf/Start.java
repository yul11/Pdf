package pdf;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Start {

	public static void main(String[] args) throws IOException {

		String path_ResultFile = "D:/Banken/comdirect/Ergebnis/Ergebnis.txt";
		

		
		//pdf-Files im alten Format von 2001 bis 2009
		//String path_pdfFiles_old   = "D:/Banken/comdirect/Finanzreports_altesFormat/";	
		//PDF_oldFormat p_old = new PDF_oldFormat();
		//File resFile_o = p_old.getResultFile(path_pdfFiles_old,path_ResultFile);	
		//System.out.println("path old pdf-files: " +resFile_o.getAbsoluteFile());
		
		
		//pdf-Files im neuen Format von 2010 bis 2017		
		String path_pdfFiles_new     = "D:/Banken/comdirect/Finanzreports_neuesFormat/";
		PDF_newFormat p_new = new PDF_newFormat();
		File resFile_n = p_new.getResultFile(path_pdfFiles_new,path_ResultFile);
		System.out.println("path new pdf-files: " +resFile_n.getAbsoluteFile());
		
	    String catFile    = "D:/Banken/comdirect/CategoryFile/cat.txt";	
		Category c = new Category();		
		List<String[]> li = c.create_ResultList(path_ResultFile, catFile);
		String resFileFinal = "D:/Banken/comdirect/Ergebnis/ErgebnisFinal.txt";
		File f = c.create_ResultFile(li,resFileFinal);
		f.getAbsoluteFile();		

	}
}
