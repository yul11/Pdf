package pdf;

public class Start_BlackList_Test {

    public static void main(String[] args)
    {
        Blacklist bl = new Blacklist();        
        System.out.println("Finanzreport Nr. enthalten: " + bl.isInBlackList("Finanzreport Nr."));
        System.out.println("comdirect bank AG • 25449 Quickborn • enthalten: " + bl.isInBlackList("comdirect bank AG • 25449 Quickborn • E-mail: info@comdirect.de • Internet: www.comdirect.de • Telefax: 01803 - 33 63 60"));
        System.out.println("element100 enthalten: " + bl.isInBlackList("element100"));        
        System.out.println("Finanzreport Nr. 1 per 01.02.2008 - Seite 2 enthalten: " + bl.isInBlackList("Finanzreport Nr. 1 per 01.02.2008 - Seite 2"));
    }
}
