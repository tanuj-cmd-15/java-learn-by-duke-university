public class AllCodon {

   public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
      for(int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3); currIndex != -1; currIndex = dnaStr.indexOf(stopCodon, currIndex + 1)) {
         int diff = currIndex - startIndex;
         if (diff % 3 == 0) {
            return currIndex;
         }
      }

      return -1;
   }

   public String findGene(String dna, int where) {
      int startIndex = dna.indexOf("ATG", where);
      if (startIndex == -1) {
         return "";
      } else {
         int taaIndex = this.findStopCodon(dna, startIndex, "TAA");
         int tagIndex = this.findStopCodon(dna, startIndex, "TAG");
         int tgaIndex = this.findStopCodon(dna, startIndex, "TGA");
         int minIndex;
         if (taaIndex != -1 && (tagIndex == -1 || tgaIndex >= taaIndex)) {
            minIndex = taaIndex;
         } else {
            minIndex = tgaIndex;
         }

         if (minIndex == -1 || tagIndex != -1 && tagIndex < minIndex) {
            minIndex = tagIndex;
         }

         return minIndex == -1 ? "" : dna.substring(startIndex, minIndex + 3);
      }
   }

   public void printAllGenes(String dna) {
      int startIndex = 0;

      while(true) {
         String currentGene = this.findGene(dna, startIndex);
         if (currentGene.isEmpty()) {
            return;
         }

         System.out.println(currentGene);
         startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
      }
   }

   public void testOn(String dna) {
      System.out.println("Testing print All Genes on " + dna);
      this.printAllGenes(dna);
   }

   public void test() {
      this.testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
      this.testOn("");
      this.testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
   }

   public void testFindStop() {
      String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
      int dex = this.findStopCodon(dna, 0, "TAA");
      if (dex != 9) {
         System.out.println("error on 9");
      }

      dex = this.findStopCodon(dna, 9, "TAA");
      if (dex != 21) {
         System.out.println("error on 21");
      }

      dex = this.findStopCodon(dna, 1, "TAA");
      if (dex != 26) {
         System.out.println("Error on 26");
      }

      dex = this.findStopCodon(dna, 0, "TAG");
      if (dex != 26) {
         System.out.println("error on 26 TAG");
      }

      System.out.println("Tests finished");
   }
}