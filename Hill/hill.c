// Amanda Rampersad
// CIS 3360
// 1 October 2012
// Hill Cipher

#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#define KEYSIZE 10
#define MAXLENGTH 10000

void UserPrompt(char key[], char input[], char output[]);
void InitializeKey(int array[KEYSIZE][KEYSIZE]);
void ReadKey(int array[KEYSIZE][KEYSIZE], int size, FILE *ifp);
int GetCharValue(FILE *ifp, int characters[]);
int PadWithX(int length, int plaintext[], int size);
void Encrypt(int length, int size, int plaintext[], int key[KEYSIZE][KEYSIZE], FILE *ofp);
void PrintFormatted(int ciphertext[], int length, FILE *ofp);

int main() {

    // Initialize strings for filenames
    char keyfile[100] = {0};
    char inputfile[100] = {0};
    char outputfile[100] = {0};

    // Prompt user for input of filenames
    UserPrompt(keyfile, inputfile, outputfile);

    // Open files
    FILE *ifp;
    FILE *ofp;
    FILE *key;

    ifp = fopen(inputfile, "r");
    ofp = fopen(outputfile, "w");
    key = fopen(keyfile, "r");

    // Read in the size of the key
    int size = 0;
    fscanf(key, "%d", &size);

    // Initialize key matrix
    int keymatrix[KEYSIZE][KEYSIZE];
    InitializeKey(keymatrix);

    // Read in the encryption key
    ReadKey(keymatrix, size, key);

    // Read in characters from plaintext and store in array
    int plaintext[MAXLENGTH] = {0};

    // Get the number of letters in the plaintext
    int length = 0;
    length = GetCharValue(ifp, plaintext);

    // Get the new number of letters in the plaintext after padding with x
    length = PadWithX(length, plaintext, size);

    //Encrypt the plaintext and prepare for output
    Encrypt(length, size, plaintext, keymatrix, ofp);

    fclose(key);
    fclose(ofp);
    fclose(ifp);

}

// Pre Conditions: Three null strings
// Post Conditions: User is prompted for input and the name of each file is stored in each corresponding string
void UserPrompt(char key[], char input[], char output[]) {

    printf("Input the name of the file storing the key.\n");
    scanf("%s", key);
    printf("Input the name of the file to encrypt.\n");
    scanf("%s", input);
    printf("Input the name of the file to store the ciphertext.\n");
    scanf("%s", output);

}

// Pre Conditions: A two dimensional array that will correspond to the key used for encryption
// Post Conditions: The array is initialized so that all locations hold 0
void InitializeKey(int array[KEYSIZE][KEYSIZE]) {

    int i, j;
    for(i=0; i<KEYSIZE; i++) {
        for(j=0; j<KEYSIZE; j++) {
            array[i][j] = 0;
        }
    }

}

// Pre Conditions: Takes in the array that corresponds to the key, the dimensions of the array (size), and the
//                 input file
// Post conditions: Stores the values in the key matrix in the key array
void ReadKey(int array[KEYSIZE][KEYSIZE], int size, FILE *ifp) {

    int i, j;
    for(i=0; i<size; i++) {
        for(j=0; j<size; j++) {
            fscanf(ifp, "%d", &array[i][j]);
        }
    }

}

// Pre Conditions: Takes in the input file with the plaintext and an empty string to store the characters
// Post Conditions: Stores the ASCII values of lowercase alphabetical letters in the plaintext ONLY
//                  and returns the number of characters
int GetCharValue(FILE *ifp, int characters[]) {

    char letter;
    int length = 0;

    while(!feof(ifp)) {
        fscanf(ifp, "%c", &letter);
        if(isalpha(letter)) {
            letter = tolower(letter);
            characters[length] = (letter - 'a');
            length++;
        }

    }

    return length;
}

// Pre Conditions: Takes in the number of characters in the plaintext and the size of the key
// Post conditions: Detects if the plaintext is too short and pads the end characters with x's ASCII value of 23
int PadWithX(int length, int plaintext[], int size) {

    while(length%size != 0) {
        plaintext[length] = 23;
        length++;
    }

    return length;

}

// Pre conditions: Takes in the number of characters, the size of the key, the plaintext, the key, and the
//                  output file
// Post conditions: Encrypts the text using the Hill Cipher and stores the encrypted text in an array for
//                  ciphertext
void Encrypt(int length, int size, int plaintext[], int key[KEYSIZE][KEYSIZE], FILE *ofp) {

    int i, j, x, y;
    int array[KEYSIZE] = {0};
    int ciphertext[MAXLENGTH] = {0};

    for(i=0; i<length; i+=size) {
        for(j=i; j<(i+size); j++) {
            array[j%size] = plaintext[j];
        }
        for(x=0; x<size; x++) {
            int total = 0;
            for(y=0; y<size; y++) {
                total+=array[y]*key[x][y];
            }
            ciphertext[x+i] = total%26;
        }
    }

    // Call function to print text
    PrintFormatted(ciphertext, length, ofp);

}

// Pre conditions: Takes in the ciphertext array, the number of characters, and the output file
// Post conditions: Prints the text in the output file with 80 characters per line, until the last line
void PrintFormatted(int ciphertext[], int length, FILE *ofp) {

    int i = 0;
    int j = 0;

    for(i=0; i<length; i+=80) {
        for(j=i; j<(i+80); j++) {
            if(j<length)
            fprintf(ofp, "%c", (char)(ciphertext[j]+'a'));
        }
        fprintf(ofp, "\n");
    }

}



