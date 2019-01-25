package pdf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {
	
	Filter (){		
	};
	
	StringBuffer[] sb_amounts = new StringBuffer[4];

	
	public void set_amount(String amount){		
		this.sb_amounts[0] = new StringBuffer(amount);
	}		
	
	
	
	public StringBuffer[] get_amounts(String line, String regX){		
        Pattern p = Pattern.compile(regX);
        Matcher m = p.matcher(line);
        int i=0,j=0;
        
        StringBuffer[] sb = new StringBuffer[4];
        for (j=0; j<4; j++)
        	sb[j]=new StringBuffer("");
        
        while ( m.find() ) {
            //System.out.println(m.group(0));
            //System.out.println(line.substring(m.start(), m.end()));                       
            //sb.append(line.substring(m.start(), m.end()));           
            sb[i].append(m.group(0)); 
            System.out.println(sb[i].toString());   
            i++;
        }
        return sb;
	}
	
	//same like get_amounts(), just different name
	public StringBuffer[] get_elements(String line, String regX){		
        Pattern p = Pattern.compile(regX);
        Matcher m = p.matcher(line);
        int i=0,j=0;
        
        StringBuffer[] sb = new StringBuffer[4];
        for (j=0; j<4; j++)
        	sb[j]=new StringBuffer("");
        
        while ( m.find() ) {
            sb[i].append(m.group(0)); 
            System.out.println(sb[i].toString());   
            i++;
        }
        return sb;
	}	
	
	
}
