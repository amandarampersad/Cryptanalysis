To use either FindKey.java or FindKeyLength.java, you must encrypt your plaintext using Encrypt.java first.

1. Begin by saving plaintext to a file.
2. Run Encrypt.java via command line using the following command: 
        java Encrypt filename keyword
  where the "filename" parameter corresponds to the name of the file where the plaintext is saved, and the 
  "keyword" parameter corresponds to the keyword you would like to use for Vigenere encryption. Keyword
  must be between 5 and 15 (inclusive) letters in length.
3. Ciphertext will be saved to the file ciphertext.txt with all non alphabetic characters stripped.
4. To decrypt, run either FindKey.java or FindKeyLength.java. Both employ a genetic algorithm to determine
  the keyword used for encryption.
  a) FindKey.java
    i. Before compiling and running, change line 52 of FindKey.java to the length of the keyword used in step 1.
    ii. To find the best potential keyword for decryption, run FindKey.java using the following command:
            java FindKey ciphertext.txt
    iii. Command line will display best potential keyword found as the first entry of Generation 100.
  b) FindKeyLength.java
    i. To find the best potential keyword for decryption, run FindKeyLength.java using the following command:
            java FindKeyLength ciphertext.txt
    ii. Command line will display best potential keyword found as the first entry of Generation 100.


