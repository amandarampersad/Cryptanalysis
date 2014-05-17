import java.util.*;
import java.io.*;
import java.lang.*;

class Individual {
    
    private String chromosome;
    private double fitness;
    
    public void setChromosome(String chromosome){
        this.chromosome = chromosome;
    }
    
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    
    public String getChromosome() {
        return chromosome;
    }
    
    public double getFitness() {
        return fitness;
    }
    
}

// Comparator to sort population from most to least fit
class FitnessComparator implements Comparator<Individual> {
    
	public int compare(Individual a, Individual b) {
        
		if (a.getFitness() < b.getFitness()) {
			return -1;
		}
        
		if (a.getFitness() == b.getFitness()) {
			return 0;
		}
        
		return 1;
        
	}
    
}

public class FindKeyLength {
    
    public static Random random = new Random();
    public static int max = 100;
    public static int numKeys = 10;
    public static int maxLength = 15;
    public static int minLength = 5;
    public static int popSize = (maxLength - minLength + 1) * numKeys;
    public static ArrayList<Individual> population = new ArrayList<Individual>();
    public static ArrayList<Individual> matingPool = new ArrayList<Individual>();
    public static HashMap<Character, Double> unigram = new HashMap<Character, Double>();
    public static HashMap<String, Double> bigram = new HashMap<String, Double>();
    public static HashMap<String, Double> trigram = new HashMap<String, Double>();
    public static double alpha = 1.0;
    public static double beta = 1.0;
    public static double gamma = 1.0;
    
    public static void main(String[] args) {
        
        // User will input file name of ciphertext
        String fileName = args[0];
        
        // Read contents of input file and store character in an array
        char[] ciphertext = readFile(fileName);
        
        // Input unigram, bigram, and trigram frequencies (found online)
        ngrams();
        
        // Initialize population and sort by fitness
        initialize(ciphertext);
        
        // Print out initial population
        System.out.println("Initial Population:" + population.size());
        for(int i = 0; i < population.size(); i++) {
            System.out.println(population.get(i).getChromosome() + " " + population.get(i).getFitness());
        }
        
        int j = 1;
        while(j <= max) {
            mate(ciphertext);
            System.out.println("Generation " + j + ":");
            for(int i = 0; i < population.size(); i++) {
                System.out.println(population.get(i).getChromosome() + " " + population.get(i).getFitness());
            }
            j++;
        }
        
    }
    
    // Method to read from file and store contents in char array
    public static char[] readFile(String fileName) {
        
        File inputFile = new File(fileName);
        String str = "";
        
        try {
            Scanner input = new Scanner(inputFile);
            
            while(input.hasNext()) {
                str = input.next();
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        
        char[] array = str.toCharArray();
        
        return array;
        
    }
    
    // Use a keyword to decrypt the ciphertext
    public static char[] decrypt(char[] array, String keyword) {
        
        char[] key = keyword.toCharArray();
        char[] plaintext = new char[array.length];
        int i = 0;
        
        while(i < array.length && Character.isLetter(array[i]) == true) {
            int value = getNumerical(array[i]) - getNumerical(key[i % key.length]);
            plaintext[i] = getCharacter((value + 26) % 26);
            i++;
        }
        
        return plaintext;
        
    }
    
    // Get numerical value of a character (0-26)
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
    
    // Get character given the number of its position (a-z)
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
    
    // Initialize the population
    // Randomly generates 10 keys for each possible key length
    public static void initialize(char[] ciphertext) {
        
        int length = minLength;
        
        while(length <= maxLength) {
            char[] chromosome = new char[length];
            for(int i = 0; i < numKeys; i++) {
                for(int j = 0; j < length; j++) {
                    int gene = random.nextInt(26);
                    chromosome[j] = getCharacter(gene);
                }
                Individual individual = new Individual();
                individual.setChromosome(String.valueOf(chromosome));
                individual.setFitness(getFitness(decrypt(ciphertext, String.valueOf(chromosome))));
                population.add(individual);
            }
            length++;
        }
        
        Collections.sort(population, new FitnessComparator());
        
    }
    
    // Input character, bigram, and trigram frequencies
    public static void ngrams() {
        
        unigram.put('a', .08167);
        unigram.put('b', .01492);
        unigram.put('c', .02782);
        unigram.put('d', .04253);
        unigram.put('e', .12702);
        unigram.put('f', .02228);
        unigram.put('g', .02015);
        unigram.put('h', .06094);
        unigram.put('i', .06966);
        unigram.put('j', .00153);
        unigram.put('k', .00772);
        unigram.put('l', .04025);
        unigram.put('m', .02406);
        unigram.put('n', .06749);
        unigram.put('o', .07507);
        unigram.put('p', .01929);
        unigram.put('q', .00095);
        unigram.put('r', .05987);
        unigram.put('s', .06327);
        unigram.put('t', .09056);
        unigram.put('u', .02758);
        unigram.put('v', .00978);
        unigram.put('w', .02360);
        unigram.put('x', .00150);
        unigram.put('y', .01974);
        unigram.put('z', .00074);
        
        bigram.put("th", .0321);
        bigram.put("he", .0305);
        bigram.put("in", .0183);
        bigram.put("er", .0174);
        bigram.put("an", .0173);
        bigram.put("re", .0137);
        bigram.put("nd", .0128);
        bigram.put("ed", .0128);
        bigram.put("ha", .0123);
        bigram.put("es", .0121);
        bigram.put("ou", .0116);
        bigram.put("to", .0112);
        bigram.put("at", .0109);
        bigram.put("en", .0107);
        bigram.put("on", .0107);
        bigram.put("ea", .0106);
        bigram.put("nt", .0105);
        bigram.put("st", .0104);
        bigram.put("hi", .0103);
        bigram.put("ng", .0095);
        bigram.put("is", .0094);
        bigram.put("it", .0092);
        bigram.put("as", .0088);
        bigram.put("or", .0086);
        bigram.put("et", .0082);
        bigram.put("te", .0082);
        bigram.put("ti", .0079);
        bigram.put("se", .0076);
        bigram.put("ar", .0075);
        bigram.put("le", .0073);
        bigram.put("of", .0072);
        bigram.put("sa", .0069);
        bigram.put("ve", .0068);
        bigram.put("me", .0065);
        bigram.put("al", .0065);
        bigram.put("no", .0063);
        bigram.put("ne", .0061);
        bigram.put("ll", .0061);
        bigram.put("el", .0060);
        bigram.put("sh", .0059);
        bigram.put("ot", .0059);
        bigram.put("tt", .0058);
        bigram.put("ro", .0057);
        bigram.put("de", .0057);
        bigram.put("ta", .0055);
        bigram.put("dt", .0054);
        bigram.put("ri", .0054);
        bigram.put("wa", .0052);
        bigram.put("wh", .0051);
        bigram.put("ho", .0051);
        bigram.put("si", .0051);
        bigram.put("so", .0050);
        bigram.put("ra", .0050);
        bigram.put("ec", .0050);
        bigram.put("yo", .0049);
        bigram.put("be", .0049);
        bigram.put("ad", .0049);
        bigram.put("ss", .0049);
        bigram.put("da", .0049);
        bigram.put("li", .0047);
        bigram.put("om", .0047);
        bigram.put("rt", .0046);
        bigram.put("ew", .0046);
        bigram.put("di", .0046);
        bigram.put("co", .0046);
        bigram.put("ee", .0046);
        bigram.put("ma", .0046);
        bigram.put("em", .0045);
        bigram.put("ai", .0044);
        bigram.put("ut", .0044);
        bigram.put("wi", .0043);
        bigram.put("ce", .0043);
        bigram.put("ow", .0043);
        bigram.put("ch", .0042);
        bigram.put("rs", .0041);
        
        trigram.put("the", .0200);
        trigram.put("and", .0093);
        trigram.put("ing", .0074);
        trigram.put("her", .0058);
        trigram.put("tha", .0047);
        trigram.put("hat", .0044);
        trigram.put("his", .0041);
        trigram.put("you", .0040);
        trigram.put("ere", .0039);
        trigram.put("dth", .0035);
        trigram.put("ent", .0034);
        trigram.put("eth", .0032);
        trigram.put("for", .0032);
        trigram.put("nth", .0031);
        trigram.put("thi", .0030);
        trigram.put("she", .0030);
        trigram.put("was", .0029);
        trigram.put("hes", .0029);
        trigram.put("ith", .0028);
        trigram.put("tth", .0028);
        trigram.put("oth", .0028);
        trigram.put("int", .0026);
        trigram.put("not", .0026);
        trigram.put("wit", .0025);
        trigram.put("edt", .0025);
        trigram.put("ver", .0024);
        trigram.put("ter", .0024);
        trigram.put("all", .0023);
        trigram.put("ion", .0022);
        trigram.put("fth", .0022);
        trigram.put("sth", .0021);
        trigram.put("oft", .0021);
        trigram.put("had", .0021);
        trigram.put("rea", .0021);
        trigram.put("est", .0021);
        trigram.put("ers", .0021);
        trigram.put("ght", .0020);
        trigram.put("ess", .0020);
        trigram.put("him", .0020);
        trigram.put("ear", .0020);
        trigram.put("ean", .0019);
        trigram.put("ave", .0019);
        trigram.put("one", .0019);
        trigram.put("hec", .0018);
        trigram.put("tin", .0018);
        trigram.put("res", .0018);
        trigram.put("hew", .0018);
        trigram.put("ont", .0018);
        trigram.put("ati", .0018);
        trigram.put("hem", .0018);
        trigram.put("esa", .0018);
        trigram.put("eve", .0018);
        trigram.put("nce", .0018);
        trigram.put("eda", .0018);
        trigram.put("aid", .0018);
        trigram.put("hin", .0018);
        trigram.put("ndt", .0018);
        trigram.put("hen", .0018);
        trigram.put("but", .0018);
        trigram.put("ome", .0018);
        trigram.put("ill", .0017);
        trigram.put("ast", .0017);
        trigram.put("rth", .0017);
        trigram.put("oul", .0017);
        trigram.put("att", .0017);
        trigram.put("sto", .0017);
        trigram.put("sai", .0017);
        trigram.put("ath", .0017);
        trigram.put("oun", .0017);
        trigram.put("ert", .0016);
        trigram.put("san", .0016);
        trigram.put("hou", .0016);
        trigram.put("our", .0016);
        trigram.put("out", .0016);
        trigram.put("hea", .0016);
        
    }
    
    // Returns the fitness based on absolute error of decrypted letter frequencies
    // The smaller the fitness, the better
    public static double getFitness(char[] plaintext) {
        
        double fitness = 0.0;
        double uni = 0.0;
        double bi = 0.0;
        double tri = 0.0;
        
        // Get unigram, bigram, and trigram frequencies in the plaintext
        double[] unigramFreq = getCharacterFrequency(plaintext);
        HashMap<String, Double> bigramFreq = getBigramFrequency(plaintext);
        HashMap<String, Double> trigramFreq = getTrigramFrequency(plaintext);
        
        // Compute sums of each relative difference
        for(int i = 0; i < unigramFreq.length; i++) {
            char character = getCharacter(i);
            double frequency = (double) unigram.get((Character) character);
            double difference = Math.abs(frequency - unigramFreq[i]);
            uni = uni + difference;
        }
        
        for(Map.Entry entry : bigramFreq.entrySet()) {
            String key = (String) entry.getKey();
            double frequency1 = (double) entry.getValue();
            double frequency2 = (double) bigram.get(key);
            double difference = Math.abs(frequency1 - frequency2);
            bi = bi + difference;
        }
        
        for(Map.Entry entry : trigramFreq.entrySet()) {
            String key = (String) entry.getKey();
            double frequency1 = (double) entry.getValue();
            double frequency2 = (double) trigram.get(key);
            double difference = Math.abs(frequency1 - frequency2);
            tri = tri + difference;
        }
        
        fitness = (alpha * uni) + (beta * bi) + (gamma * tri);
        
        return fitness;
        
    }
    
    // Returns character frequencies of all letters a-z
    public static double[] getCharacterFrequency(char[] plaintext) {
        
        int[] count = new int[26];
        int total = 0;
        double[] frequency = new double[26];
        
        for(int i = 0; i < plaintext.length; i++) {
            int index = getNumerical(plaintext[i]);
            count[index]++;
        }
        
        for(int i = 0; i < count.length; i++) {
            total = total + count[i];
        }
        
        for(int i = 0; i < count.length; i++) {
            frequency[i] = (double) count[i]/total;
        }
        
        return frequency;
        
    }
    
    // Returns bigram frequencies of most common bigrams
    public static HashMap<String, Double> getBigramFrequency(char[] plaintext) {
        
        int i = 0;
        char[] str = new char[2];
        int total = 0;
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        HashMap<String, Double> frequency = new HashMap<String, Double>();
        
        while(i + 1 < plaintext.length) {
            if(Character.isLetter(plaintext[i]) == true && Character.isLetter(plaintext[i+1]) == true) {
                str[0] = plaintext[i];
                str[1] = plaintext[i+1];
                String key = String.valueOf(str);
                
                if(count.containsKey(key) == false) {
                    count.put(key, 1);
                }
                else {
                    int temp = count.get(key);
                    temp++;
                    count.remove(key);
                    count.put(key, temp);
                }
            }
            i++;
        }
        
        for(Map.Entry entry : count.entrySet()) {
            total = total + (int) entry.getValue();
        }
        
        for(Map.Entry entry : count.entrySet()) {
            String key = (String) entry.getKey();
            Integer value = (Integer) entry.getValue();
            
            if(bigram.containsKey(key) == true) {
                Double freq = (Double) ((double) value.intValue()/total);
                frequency.put(key, freq);
            }
        }
        
        return frequency;
        
    }
    
    // Returns trigram frequencies
    public static HashMap<String, Double> getTrigramFrequency(char[] plaintext) {
        
        int i = 0;
        char[] str = new char[3];
        int total = 0;
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        HashMap<String, Double> frequency = new HashMap<String, Double>();
        
        while(i + 2 < plaintext.length) {
            if(Character.isLetter(plaintext[i]) == true && Character.isLetter(plaintext[i+1]) == true) {
                str[0] = plaintext[i];
                str[1] = plaintext[i+1];
                str[2] = plaintext[i+2];
                String key = String.valueOf(str);
                
                if(count.containsKey(key) == false) {
                    count.put(key, 1);
                }
                else {
                    int temp = count.get(key);
                    temp++;
                    count.remove(key);
                    count.put(key, temp);
                }
            }
            i++;
        }
        
        for(Map.Entry entry : count.entrySet()) {
            total = total + (int) entry.getValue();
        }
        
        for(Map.Entry entry : count.entrySet()) {
            String key = (String) entry.getKey();
            Integer value = (Integer) entry.getValue();
            
            if(trigram.containsKey(key) == true) {
                Double freq = (Double) ((double) value.intValue()/total);
                frequency.put(key, freq);
            }
        }
        
        return frequency;
        
    }
    
    // Creates two offspring from crossing over two parents in the existing population
    // Children mutate, then are added to mating pool
    // Fittest children become the new population
    public static void mate(char[] ciphertext) {
        
        int first = 0;
        int second = 0;
        Individual parent1 = new Individual();
        Individual parent2 = new Individual();
        matingPool.clear();
        
        for(int i = 0; i < popSize/2; i++) {
            first = random.nextInt(popSize);
            second = random.nextInt(popSize);
            parent1 = population.get(first);
            parent2 = population.get(second);
            while(parent1.getChromosome().length() != parent2.getChromosome().length()) {
                second = random.nextInt(popSize);
                parent2 = population.get(second);
            }
            crossover(parent1.getChromosome(), parent2.getChromosome(), ciphertext);
        }
        
        Collections.sort(matingPool, new FitnessComparator());
        
        population.clear();
        
        for(int i = 0; i < popSize; i++) {
            population.add(matingPool.get(i));
        }
        
        Collections.sort(population, new FitnessComparator());
        
    }
    
    // Crosses over two parents of equal length, then mutates both children
    public static void crossover(String parent1, String parent2, char[] ciphertext) {
        
        int i = 0;
        int point = random.nextInt(parent1.length() - 1 - 1) + 1;
        char[] child = new char[parent1.length()];
        
        for(i = 0; i < point; i++) {
            child[i] = parent1.charAt(i);
        }
        
        for(i = point; i < parent2.length(); i++) {
            child[i] = parent2.charAt(i);
        }
        
        String child1 = String.valueOf(child);
        
        for(i = 0; i < point; i++) {
            child[i] = parent2.charAt(i);
        }
        
        for(i = point; i < parent1.length(); i++) {
            child[i] = parent1.charAt(i);
        }
        
        String child2 = String.valueOf(child);
        
        Individual individual1 = new Individual();
        individual1.setChromosome(child1);
        individual1.setFitness(getFitness(decrypt(ciphertext, child1)));
        matingPool.add(individual1);
        
        Individual individual2 = new Individual();
        individual2.setChromosome(child2);
        individual2.setFitness(getFitness(decrypt(ciphertext, child2)));
        matingPool.add(individual2);
        
        mutate(child1, ciphertext);
        mutate(child2, ciphertext);
        
    }
    
    // Mutates a chromosome by changing a randomly chosen gene to a randomly chosen letter a-z
    public static void mutate(String child, char[] ciphertext) {
        
        int point = random.nextInt(child.length());
        int newGene = random.nextInt(26);
        char newGeneValue = getCharacter(newGene);
        char[] chromosome = child.toCharArray();
        
        chromosome[point] = newGeneValue;
        
        Individual individual = new Individual();
        individual.setChromosome(String.valueOf(chromosome));
        individual.setFitness(getFitness(decrypt(ciphertext, String.valueOf(chromosome))));
        matingPool.add(individual);
        
    }
    
}