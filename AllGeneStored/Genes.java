import edu.duke.*;
import java.io.*;
/**
 * Write a description of Genes here.
 * 
 * @author Tushar Pawar 
 * @version Sep, 2023
 */
public class Genes {
    public String findProtein(String dna){
        return "";
    }
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon,currIndex+1);
            }
        }
        return dnaStr.length();
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = this.findStopCodon(dna, startIndex, "TAA");
        int tagIndex = this.findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = this.findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 ||(tgaIndex != -1 && tgaIndex < taaIndex)) {
                minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || ( tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
      
        if(minIndex == -1){
          return "";
        }
        return dna.substring(startIndex, minIndex + 3);
      
    }
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true) {
            String currentGene = this.findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }

            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    public String testOn(String dna){
        System.out.println("Testing print All Genes on " + dna);
        this.printAllGenes(dna);
        return "";
    }
    
    public void test() {
        this.testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        this.testOn("");
        this.testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
    
    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String dna = fr.asString();
            System.out.println("read " + dna.length() + " characters");
            String result = testOn(dna);
            System.out.println("found " + result);
        }
    }
}
