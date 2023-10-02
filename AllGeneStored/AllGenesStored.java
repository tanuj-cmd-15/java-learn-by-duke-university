import edu.duke.*;
import java.io.*;
/**
 * Write a description of AllGenesStored here.
 * 
 * @author Tushar Pawar
 * @version Sep,
 */
public class AllGenesStored {
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
   
   public StorageResource getAllGenes(String dna){
       StorageResource geneList = new StorageResource();
       int startIndex =0;
       while(true) {
         String currentGene = this.findGene(dna, startIndex);
         if (currentGene.isEmpty()) {
            break;
         }
         geneList.add(currentGene);
         startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
       }
       return geneList;
   }

   public void testOn(String dna) {
      System.out.println("Testing print All Genes on " + dna);
      StorageResource genes = getAllGenes(dna);
      for (String g: genes.data()){
          System.out.println(g);
        }
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
			String s = fr.asString();
			System.out.println("read " + s.length() + " characters");
			String result = findGene(s,0);
			System.out.println("found " + result);
		}
	}
}
