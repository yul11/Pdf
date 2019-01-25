package pdf;

import java.io.File;
import java.util.ArrayList;

public class Datei {
	
	File folder;
	
	public Datei (File fold){		
		this.folder=fold;
	}
	
	public ArrayList<String> getFileNames(File folder){
        ArrayList<String> li_fileNames = new ArrayList<String>();						
		for (File file : folder.listFiles() ){
			li_fileNames.add(file.getName());
		}
		return li_fileNames;
	}
}
