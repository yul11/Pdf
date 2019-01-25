package pdf;

import java.util.HashSet;
import java.util.Set;

public class Blacklist {
	
	Set<String> blackList = new HashSet<String>();	
	
	Blacklist (){
    	blackList.add("comdirect bank AG • 25449 Quickborn • E-mail: info@comdirect.de • Internet: www.comdirect.de • Telefax: 01803 - 33 63 60");
    	blackList.add("comdirect bank AG • 25449 Quickborn • E-mail: info@comdirect.de • Internet: www.comdirect.de • Telefax: 01805 - 33 64 55*");
    	blackList.add("*0,09 Euro/Min. (Telefon) bzw. 0,14 Euro/Min. (Telefax) aus dem Festnetz der Dt. Telekom/Mobilfunkpreise ggf. abweichend");    	   	
    	blackList.add("Finanzreport Nr.");
    	blackList.add("Kontonummer 2174050 / BLZ 20041111");
    	blackList.add("Umsaetze  (Fortsetzung)");	
	}		
    public boolean isInBlackList(String element)
    {
        return blackList.contains(element);
    }
}
