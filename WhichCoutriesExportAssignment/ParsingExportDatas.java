
/**
 * Write a description of CountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportDatas {
    public void numberOfExporters(CSVParser parser,String exportItem){
        int count =0;
        //for each in the CSV file
        for(CSVRecord record : parser){
            //Look at the "Exports" columns
            
            String export = record.get("Exports");
            //check if it contains exportOfInterest
            if (export.contains(exportItem)){
                //if so, write dowm the "country" from that row
                String country = record.get("Country");
                System.out.println(country);
                count++;
            }
        } 
        System.out.println("How many countries export " + exportItem+" : "+ count);
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportItem1)&& export.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }    
    public void CountryInfo(CSVParser parser,String country){
        boolean found = false;
        for(CSVRecord record : parser){
            String getCountry = record.get("Country");
            if(getCountry.contains(country)){
                String export = record.get("Exports");
                System.out.println(getCountry + " : "+ export);
                found = true;
            }
        }
        if(!found)System.out.println("Not Found");
    }
    
    public void bigExporters(CSVParser parser,String amount){
        for (CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if (value.length() >("$"+amount).length() ) {
				//If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country + " : " + value);

	     }
        }
    }
    public void tester(){
            FileResource fr = new FileResource();
            CSVParser parser = fr.getCSVParser();
            CountryInfo(parser,"Nauru");
            parser = fr.getCSVParser();
            listExportersTwoProducts(parser,"gold","diamonds");
            parser = fr.getCSVParser();
            numberOfExporters(parser,"sugar");
            parser = fr.getCSVParser();
            bigExporters(parser,"$999,999,999,999");
            
        }
}
