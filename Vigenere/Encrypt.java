import java.util.*;
import java.io.*;

public class Encrypt {

    public static void main(String[] args) {
    
        // User inputs file name containing plaintext and the keyword
        String fileName = args[0];
        String keyword = args[1];
        
        // Read contents of input file and store in an array
        // Remove punctuation from plaintext
        char[] plaintext = readFile(fileName);
        plaintext = removePunctuation(plaintext);
        
        // Get ciphertext
        char[] ciphertext = substitute(plaintext, keyword);
        
        try {
            System.setOut(new PrintStream(new FileOutputStream("ciphertext.txt")));
            System.out.println(ciphertext);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    // Method to read from file and store contents in char array
    public static char[] readFile(String fileName) {
        
        File inputFile = new File(fileName);
        String str = "";
        
        try {
            Scanner input = new Scanner(inputFile);
            
            while(input.hasNext()) {
                str = str.concat(input.next());
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        
        str = str.toLowerCase();
        char[] array = str.toCharArray();
        
        return array;
        
    }
    
    // Method to remove punctuation from char array
    public static char[] removePunctuation(char[] array) {
        
        char[] temp = new char[array.length];
        int i = 0;
        int j = 0;
        
        while(i < array.length) {
            if(Character.isLetter(array[i]) == true) {
                temp[j] = array[i];
                j++;
            }
            i++;
        }
        
        return temp;
        
    }
    
    // Use the keyword to compute the ciphertext
    public static char[] substitute(char[] array, String keyword) {
        
        char[] key = keyword.toCharArray();
        char[] ciphertext = new char[array.length];
        int i = 0;
        
        while(i < array.length && Character.isLetter(array[i]) == true) {
            int value = getNumerical(array[i]) + getNumerical(key[i % key.length]);
            ciphertext[i] = getCharacter(value % 26);
            i++;
        }
        
        return ciphertext;
        
    }
    
    public static  int getNumerical(char character) {
        
        int value = 0;
        
        switch(character) {
            case 'a':
                value = 0;
                break;
            case 'b':
                value = 1;
                break;
            case 'c':
                value = 2;
                break;
            case 'd':
                value = 3;
                break;
            case 'e':
                value = 4;
                break;
            case 'f':
                value = 5;
                break;
            case 'g':
                value = 6;
                break;
            case 'h':
                value = 7;
                break;
            case 'i':
                value = 8;
                break;
            case 'j':
                value = 9;
                break;
            case 'k':
                value = 10;
                break;
            case 'l':
                value = 11;
                break;
            case 'm':
                value = 12;
                break;
            case 'n':
                value = 13;
                break;
            case 'o':
                value = 14;
                break;
            case 'p':
                value = 15;
                break;
            case 'q':
                value = 16;
                break;
            case 'r':
                value = 17;
                break;
            case 's':
                value = 18;
                break;
            case 't':
                value = 19;
                break;
            case 'u':
                value = 20;
                break;
            case 'v':
                value = 21;
                break;
            case 'w':
                value = 22;
                break;
            case 'x':
                value = 23;
                break;
            case 'y':
                value = 24;
                break;
            default:
                value = 25;
                break;
            
        }
        
        return value;
        
    }
    
    public static char getCharacter(int value) {
        
        char character;
        
        switch(value) {
            case 0:
                character = 'a';
                break;
            case 1:
                character = 'b';
                break;
            case 2:
                character = 'c';
                break;
            case 3:
                character = 'd';
                break;
            case 4:
                character = 'e';
                break;
            case 5:
                character = 'f';
                break;
            case 6:
                character = 'g';
                break;
            case 7:
                character = 'h';
                break;
            case 8:
                character = 'i';
                break;
            case 9:
                character = 'j';
                break;
            case 10:
                character = 'k';
                break;
            case 11:
                character = 'l';
                break;
            case 12:
                character = 'm';
                break;
            case 13:
                character = 'n';
                break;
            case 14:
                character = 'o';
                break;
            case 15:
                character = 'p';
                break;
            case 16:
                character = 'q';
                break;
            case 17:
                character = 'r';
                break;
            case 18:
                character = 's';
                break;
            case 19:
                character = 't';
                break;
            case 20:
                character = 'u';
                break;
            case 21:
                character = 'v';
                break;
            case 22:
                character = 'w';
                break;
            case 23:
                character = 'x';
                break;
            case 24:
                character = 'y';
                break;
            default:
                character = 'z';
                break;
                
        }
        
        return character;
        
    }
    
}