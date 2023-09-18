
/**
 * Write a description of CSVMax here.
 * 
 * @author (Tushar Pawar) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){
        //Start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //for each row (currentRow) in the CSV file
        for (CSVRecord currentRow : parser){
            //if LargestSOFar is nothing
               largestSoFar = getLargestOfTwo(currentRow,largestSoFar);
        }
        //the largestSoFar is the answer
        return largestSoFar;
    }
    public void testHottestInDay(){
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temprature was "+largest.get("TemperatureF")+" at "+ largest.get("TimeEST"));
    } 
    
    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar =null;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow,largestSoFar);
        }
        return largestSoFar;
    }
    
    public CSVRecord getLargestOfTwo (CSVRecord currentRow,CSVRecord largestSoFar){
        if (largestSoFar  == null){
                largestSoFar = currentRow;
            }
            //otherwiae
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                //Check if currentRow's temperature > larhgestSoFar
                if (currentTemp > largestTemp){
                    //If so update largestSoFar to currentRo
                    largestSoFar = currentRow;
                }
        }
        return largestSoFar;
    }
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println(largest.get("TemperatureF")+largest.get("DateUTC"));
    }

}
