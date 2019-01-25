package pdf;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class Category {	

	
	//returns categoryMap which contains keywords and categories
	//data were readout from external file	
	private HashMap<String,String> get_CategoryMap(String catFile) throws IOException{									
		HashMap<String, String> hm = new HashMap<String,String>();		
		BufferedReader br  = new BufferedReader(new FileReader(new File(catFile)));
		String line;		
		while ((line = br.readLine()) != null) {
			//System.out.println(line);
			String[] str_parts = line.split("[|]");
			hm.put(str_parts[0], str_parts[1]);
		}
		br.close();	  
		return hm;
	}

	

	public List<String[]> create_ResultList(String resFile, String catFile) throws IOException {	
		
		HashMap<String,String> map_category = get_CategoryMap(catFile);
		BufferedReader br  = new BufferedReader(new FileReader(new File(resFile)));
		List<String[]> li  = new ArrayList<String[]>(); 		
		String[]str_parts  = new String[6];	
		String line="";
		
		while ((line = br.readLine()) != null){
			//System.out.println(line);			
			str_parts = line.split("[|]");
			
			//http://javawiki.sowas.com/doku.php?id=java:array_vergroessern
			//Ein Array kann nicht wirklich vergrößert werden. Es kann aber recht einfach ein neues, größeres Array mit dem alten Inhalt erzeugt werden. Im Beispiel wird die Größe eines String-Array von 4 auf 6 Elemente „vergrößert“.			
	        Object newArray = Array.newInstance(str_parts.getClass().getComponentType(), Array.getLength(str_parts)+2);  // +2
	        System.arraycopy(str_parts, 0, newArray, 0, Array.getLength(str_parts));
	        str_parts = (String[]) newArray;
			
			for (Entry<String, String> entry : map_category.entrySet()) {
			    //System.out.println(entry.getKey());
			    //System.out.println(entry.getValue() + "\n");
			    if (str_parts[2].contains(entry.getKey())){
			    	//System.out.println(entry.getKey() + " gefunden");
			    	str_parts[4] = entry.getKey();
			    	str_parts[5] = entry.getValue();
			    }	
			}						
			li.add(str_parts);
		}
		//Array-Liste ausgeben. Jedes Element der Liste ist ein String-Array, das eine Buchung enthält.
        for (String[] a : li) {
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
            }
            System.out.println("\n");
		}			
		br.close();
		return li;
	}

	
	
	
	public File create_ResultFile(List<String[]> list, String path_ResultFile) throws IOException{
		
		File resFile = new File(path_ResultFile);
		BufferedWriter  bufW = new BufferedWriter(new FileWriter(resFile,true));
        for (String[] a : list) {
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
                if (a[i]!=null){                	
                	if (i==5){                		
                		bufW.write("|" + a[5] + "|"); 
                		bufW.write(      a[4] + "|");
                		bufW.write(      a[0] + "|");
                		bufW.write(      a[2] + "|");
                		bufW.write(      a[3] + "|"); 
                		bufW.write("\n"); 
                	}               	
                }                	
            }
            System.out.println("\n");
		}			
        bufW.close();
		return resFile;		
	}	
}

