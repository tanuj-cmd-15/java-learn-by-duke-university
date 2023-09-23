
/**
 * Write a description of CeaserCipher here.
 * 
 * @author Tushar 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CeaserCipher {
    public String encrypt(String input , int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        for(int i =0; i< encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if(Character.isLowerCase(currChar)) newChar= Character.toLowerCase(newChar);
                if(Character.isUpperCase(currChar)) newChar = Character.toUpperCase(newChar);
                encrypted.setCharAt(i,newChar);          
            }
            
        }
        return encrypted.toString();
    }
    public void testCeaser(){
        int key = 15;
        String message ="India, officially the Republic of India, is a country in South Asia. It is the seventh-largest country by area; the most populous country as of June 2023; and from the time of its independence in 1947, the world's most populous democracy.";
        String encrypted = encrypt(message,key);
        System.out.println(" key is "+ key + "\n" + encrypted);
        String decrypted = encrypt(encrypted,26-key);
        System.out.println(decrypted);
    }
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}
