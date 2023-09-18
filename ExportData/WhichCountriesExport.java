
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (Tushar Pawar) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser,String exportOfInterest){
        //for each in the CSV file
        for(CSVRecord record : parser){
            //Look at the "Exports" columns
            String export = record.get("Exports");
            //check if it contains exportOfInterest
            if (export.contains(exportOfInterest)){
                //if so, write dowm the "country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        } 
    }
    public void whoExportsCoffee(){
            FileResource fr = new FileResource();
            CSVParser parser = fr.getCSVParser();
            listExporters(parser,"coffee");
        }
}
