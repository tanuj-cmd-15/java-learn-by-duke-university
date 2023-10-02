
/**
 * Write a description of BabyBirths here.
 * 
 * @author (Tushar Pawar) 
 * @version Sep 2023
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
    public void printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 100){
                System.out.println("Name"+ rec.get(0)+" Gender"+rec.get(1)+" Num Born "+rec.get(2));
            } 
        }
    }
    public void totalBirths (FileResource fr){
        int totalBirths = 0 ;
        int totalBoys =0;
        int totalGirls =0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){
                totalBoys +=numBorn;
            }
            else{
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = "+ totalBirths);
        System.out.println("total boys = "+totalGirls);
        System.out.println("Total boys = "+ totalBoys);
        
    }
    public void testTotalBirths(){
        FileResource fr = new FileResource("data/example-small.csv");
        totalBirths(fr);
    }
}
