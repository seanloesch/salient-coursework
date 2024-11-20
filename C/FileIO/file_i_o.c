/**
 * @file file_i_o.c
 * @brief A program demonstrating various file I/O techniques and dynamic memory
 * management in C, written using Vim
 * @date 2022-12-22
 * @author Sean Loesch
 * 
 * @details This program demonstrates proficiency in various file I/O techniques
 * and dynamic memory management in C. It includes:
 * 
 * 1. fileCmp - Compares the contents of two text files and writes
 * their differences to a third file.
 * 
 * 2. encrypt & decrypt - Facilitate the encryption and decryption of a custom
 * secret message using the Columnar Transposition Cipher.
 * 
 * 3. removeFile - Prints detailed, custom error messages for each error number
 * if a file cannot be removed.
 * 
 * 4. isFormatted - Checks the formatting validity of a .c file (refer to the
 * function documentation for details).
 * 
 * 5. pigLatin - Translates a text file from English to Pig Latin.
 * 
 * @note See README.md for details regarding use.
 */

#include <string.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

//  PROBLEM 1

/**
 * @brief Compare two text files by writing any differences (different words or
 * a different order of words) to an output file
 * 
 * @details Compares two files by writing any differences (different words or a
 * different order of words) to an output file. It reads in both files character
 * by character and converts them into words. If the words are different, the
 * function writes the word from f2 (the second file being compared) to the
 * output file. The function also writes any remaining words (lack of words in
 * f2 are represented using lines with slashes) from the first or second file to
 * the output file if one of the files has reached the end of file (EOF) before
 * the other.
 * 
 * @warning Individual words cannot exceed 100 characters, else a segfault will
 * occur.
 * 
 * @note
 * - Words are case-sensitive; "Word" and "word" will be treated as
 *  discrepancies.
 * - Differences are represented only by the corresponding word (or lack
 *  thereof) in f2.
 * 
 * @param f1 pointer to the first file to compare
 * @param f2 pointer to the second file to compare
 * @param output pointer to the output file where different words are written
*/
void fileCmp(FILE* f1, FILE* f2, FILE* output) {
    char word1[101];
    char word2[101];
    int c1, c2;
    int i, j;

    //  Get the first character from both files
    while ((c1 = fgetc(f1)) != EOF && (c2 = fgetc(f2)) != EOF) {
        i = 0;
        j = 0;

        //  Loop until white space or punctuation to get a full word
        while (!isspace(c1) && !ispunct(c1) && c1 != EOF) {
            if (i < 100) {
                word1[i] = c1;
                i++;
            }
            c1 = fgetc(f1);
        }
        word1[i] = '\0';

        //  Loop until white space or punctuation to get a full word
        while (!isspace(c2) && !ispunct(c2) && c2 != EOF) {
            if (j < 100) {
                word2[j] = c2;
                j++;
            }
            c2 = fgetc(f2);
        }
        word2[j] = '\0';

        //  If the words are different put word2 in the file
        if (strcmp(word1, word2) != 0) {
            fprintf(output, "%s\n", word2);
        }
    }

    //  Loop over the rest of f1 if not already done
    while (c1 != EOF) {
        i = 0;

        //  Loop until white space or punctuation to get a full word
        while (!isspace(c1) && !ispunct(c1) && c1 != EOF) {
            if (i < 99) {
                word1[i] = c1;
                i++;
            }
            c1 = fgetc(f1);
        }
        word1[i] = '\0';

        //  Write a slash to the file
        if (strlen(word1) != 0) {
            fprintf(output, "/\n");
        }
        
        c1 = fgetc(f1);
    }

    //  Loop through the rest of f2 if not already done
    while (c2 != EOF) {
        j = 0;

        //  Making the word
        while (!isspace(c2) && !ispunct(c2) && c2 != EOF) {
            if (j < 99) {
                word2[j] = c2;
                j++;
            }
            c2 = fgetc(f2);
        }
        word2[j] = '\0';

        //  Write it to the file only if it has non-whitespace characters in it
        if (strlen(word2) != 0) {
            fprintf(output, "%s\n", word2);
        }
        
        c2 = fgetc(f2);
    }
}

//  PROBLEM 2

/**
 * @brief Compare two characters and return their difference
 * 
 * @details Compares two characters and returns their difference. It is used as
 * a callback function for qsort to sort an array of characters alphabetically.
 * Used in encrypt and decrypt.
 * 
 * @param a pointer to the first character to compare
 * @param b pointer to the second character to compare
 * @return the difference between the two characters
*/
int cmpChar(const void* a, const void* b) {
    return *(char*)a - *(char*)b;
}

/**
 * @brief Encrypt a file using the Columnar Transposition Cipher
 * 
 * For information on the Columnar Transposition Cipher, visit
 * http://practicalcryptography.com/ciphers/columnar-transposition-cipher/
 * 
 * @details Takes a file and a key as input, encrypts the file using the key,
 * and writes the encrypted file to a new file called "encrypted.txt". The
 * function first counts the number of letters in the file and creates a 2D
 * array with the letters. It then sorts the key alphabetically and swaps the
 * characters in the 2D array according to their new indices in the sorted key.
 * Finally, the function writes the encrypted word to the output file by going
 * column by column through the array.
 * 
 * @note The contents of the message needn't be alphanumeric or case-homogenous.
 * 
 * @param key a string containing the key to use for encryption
 * @param fp a pointer to the file to be encrypted
*/
void encrypt(const char* key, FILE* fp) {
    int counter = 0;
    int c;
    
    //  Get the number of letters in the file
    while ((c = fgetc(fp)) != EOF) {
        if (!isspace(c)) {
            counter++;
        }
    }

    //  Create the 2D array used for encryption
    const int len = strlen(key);
    const int numRows = (counter + (len - 1)) / len;

    // Allocating memory for 2D array
    char **A = malloc(numRows * sizeof(char *));
    for (int i = 0; i < numRows; i++) {
        A[i] = malloc((len + 1) * sizeof(char)); // Allocate memory for each row
    }

    int i, j;

    //  Go to beginning of the file and write the letters into the 2D array
    fseek(fp, 0, SEEK_SET);
    for (i = 0; i < numRows; i++) {
        j = 0;
        while (j != len && (c = fgetc(fp)) != EOF) {
            if (!isspace(c)) {             
                A[i][j] = c;
                j++;
            }
        }
        A[i][j] = '\0';
        
    }

    //  Prevent segfault
    i--;

    //  Fill in any remaining space for the array with 'x'
    while (j < len) {
        A[i][j] = 'x';
        j++;
    }

    //  Create an array to hold the new indices of the letters and a copy of the
    //  key to be sorted
    int *indices = malloc(len * sizeof(int));
    char *tempKey = malloc((len + 1) * sizeof(char));
    strcpy(tempKey, key);

    //  Sort the key alphabetically
    qsort(tempKey, len, sizeof(char), cmpChar);

    //  Put the new indices of the letters into the indices array
    for (i = 0; i < len; i++) {
        for (j = 0; j < len; j++) {
            if (key[i] == tempKey[j]) {
                indices[i] = j;
            }
        }
    }

    // Deallocate indices and tempKey
    free(indices);
    free(tempKey);

    //  Create the 2D array that will contain the encrypted message
    char **B = malloc(numRows * sizeof(char *));
    for (int i = 0; i < numRows; i++) {
        B[i] = malloc((len + 1) * sizeof(char)); // Allocate memory for each row
    }

    int index = 0;

    //  Swap the characters according to how they were sorted in the key and
    //  store them in B
    for (i = 0; i < numRows; i++) {
        for (j = 0; j < len; j++) {
            index = indices[j];
            B[i][index] = A[i][j];
        }
        B[i][j] = '\0';
    }

    // Deallocate 2D array A
    for (int i = 0; i < numRows; i++) {
        free(A[i]); // Free each row of A
    }
    free(A);

    //  Write the encrypted word to the file going column by column through the
    //  array
    FILE* encrypFile = fopen("test_files/p2/encrypted.txt", "w");
    for (j = 0; j < len; j++) {
        for (i = 0; i < numRows; i++) {
            fprintf(encrypFile, "%c", B[i][j]);
        }
    }
    
    fclose(encrypFile);

    // Deallocate 2D array B
    for (int i = 0; i < numRows; i++) {
        free(B[i]); // Free each row of B
    }
    free(B);
}

/**
 * @brief Decrypt a file encrypted using the Columnar Transposition Cipher with
 * a key
 * 
 * For information on the Columnar Transposition Cipher, visit
 * http://practicalcryptography.com/ciphers/columnar-transposition-cipher/
 * 
 * @details Takes a file and a key as input, decrypts the file using the key,
 * and writes the decrypted file to a new file called "decrypted.txt". The
 * function first counts the number of letters in the file and creates a 2D
 * array with the letters. It then sorts the key alphabetically and swaps the
 * characters in the 2D array back to their original positions according to
 * their new indices in the sorted key. Finally, the function writes the
 * decrypted word to the output file by going row by row through the array.
 * 
 * @param key a string containing the key to use for decryption
 * @param fp a pointer to the file to be decrypted
*/
void decrypt(const char* key, FILE* fp) {
    int len = strlen(key);
    int counter = 0;
    int c;

    //  Get the number of letters in the file
    while ((c = fgetc(fp)) != EOF) {
        if (!isspace(c)) {
            counter++;
        }
    }

    //  Create a 2D array to store the letters
    int numRows = counter / len;

    // Allocating memory for 2D array
    char **A = malloc(numRows * sizeof(char *));
    for (int i = 0; i < numRows; i++) {
        A[i] = malloc((len + 1) * sizeof(char)); // Allocate memory for each row
    }

    int i, j;

    //  Go to the beginning and write all letters into A, building column by
    //  column
    fseek(fp, 0, SEEK_SET);
    for (j = 0; j < len; j++) {
        i = 0;
        while (i != numRows && (c = fgetc(fp)) != EOF) {
            if (!isspace(c)) {
                A[i][j] = c;
                i++;
            }
        }
    }

    //  Put null terminating character at the end of every index
    for (i = 0; i < numRows; i++) {
        A[i][len] = '\0';
    }

    //  Create indices array to store sorted indices of the key
    int *indices = malloc(len * sizeof(int));
    char *tempKey = malloc((len + 1) * sizeof(char));
    strcpy(tempKey, key);

    //  Sort the key alphabetically
    qsort(tempKey, len, sizeof(char), cmpChar);

    //  Store the new indices of the letters in the indices array
    for (i = 0; i < len; i++) {
        for (j = 0; j < len; j++) {
            if (key[i] == tempKey[j]) {
                indices[i] = j;
            }
        }
    }

    // Deallocate tempKey
    free(tempKey);

    //  Create the 2D array that will contain the decrypted message
    char **B = malloc(numRows * sizeof(char *));
    for (int i = 0; i < numRows; i++) {
        B[i] = malloc((len + 1) * sizeof(char)); // Allocate memory for each row
    }

    int index = 0;
    
    //  Swap all letters in A back to original places using the indices array
    for (i = 0; i < numRows; i++) {
        for (j = 0; j < len; j++){
            index = indices[j];
            B[i][j] = A[i][index];
        }
        B[i][j] = '\0';
    }

    // Deallocate indices and 2D array A
    free(indices);

    for (int i = 0; i < numRows; i++) {
        free(A[i]); // Free each row of A
    }
    free(A);

    //  Write the decrypted word to the file row by row
    FILE* decrypFile = fopen("test_files/p2/decrypted.txt", "w");
    for (i = 0; i < numRows; i++) {
        fprintf(decrypFile, "%s", B[i]);
    }
    
    fclose(decrypFile);

    // Deallocate 2D array B
    for (int i = 0; i < numRows; i++) {
        free(B[i]); // Free each row of B
    }
    free(B);
}

//  PROBLEM 3

/**
 * @brief Remove a file from the file system
 * 
 * @details Removes a file from the file system given its
 * filename. If the file is successfully removed, the function prints a message
 * indicating this. If the file cannot be removed, the function prints an error
 * message with the correct description.
 * 
 * @param fileName a string containing the name of the file to be removed
*/
void removeFile(const char* fileName) {

    if (remove(fileName) == 0) {
        printf("File Successfully Removed\n");
    } else {
        switch (errno) {
            case 1:
                printf("ERROR REMOVING FILE - Operation not permitted\n");
                break;
            case 2:
                printf("ERROR REMOVING FILE - No such file or directory\n");
                break;
            case 3:
                printf("ERROR REMOVING FILE - No such process\n");
                break;
            case 4:
                printf("ERROR REMOVING FILE - Interrupted System Call\n");
                break;
            case 5:
                printf("ERROR REMOVING FILE - I/O error\n");
                break;
            case 6:
                printf("ERROR REMOVING FILE - No such device or address\n");
                break;
            case 7:
                printf("ERROR REMOVING FILE - Argument list too long\n");
                break;
            case 8:
                printf("ERROR REMOVING FILE - Exec format error\n");
                break;
            case 9:
                printf("ERROR REMOVING FILE - Bad file number\n");
                break;
            case 10:
                printf("ERROR REMOVING FILE - No child processes\n");
                break;
            case 11:
                printf("ERROR REMOVING FILE - Try again\n");
                break;
            case 12:
                printf("ERROR REMOVING FILE - Out of memory\n");
                break;
            case 13:
                printf("ERROR REMOVING FILE - Permission denied\n");
                break;
        }
    }
}

//  PROBLEM 4

/**
 * @brief Compare two integers and return their difference
 * 
 * @details Compares two integers and returns their difference. It is used as a
 * callback function for qsort to sort an array of integers in ascending order.
 * Used in isFormatted.
 * 
 * @param a pointer to the first integer to compare
 * @param b pointer to the second integer to compare
 * @return the difference between the two integers
*/
int cmpInt(const void *a, const void *b) {
    return *(int*)a - *(int*)b;
}

/**
 * @brief Check if a .c file has proper formatting
 * 
 * @details Checks if a .c file has proper formatting by checking
 * for matching curly braces, square braces, quotes, parantheses, and same
 * indentation for opening and closing curly braces. If the file has proper
 * formatting, the function returns 1. If it does not, the function returns 0.
 * 
 * @warning
 * The function will not work properly if:
 * 
 * - There are more than 1000 of the following characters and their closing
 * counterparts: { ( [ " '
 * - Opening and closing curly brace occur on the same line.
 * - Indents are not exclusively tabs (\t).
 * - The checked file has lines with more than 30 indents (the last slot is
 * reserved for a curly brace).
 * 
 * @note 
 * - The indent checker for curly braces will not function if the text editor
 * you are using replaces tabs with spaces (e.g. VS Code).
 * - The function reads the file character by character and stores opening
 * braces and their corresponding indentation in a 2D array. It also increments
 * counters for each type of brace/quote to ensure that they are properly
 * paired.
 * 
 * @param fp pointer to the file to be checked
 * @return 1 if the file has proper formatting, 0 if it does not
*/
int isFormatted(FILE* fp) {
    
    //  Total number of { ( [ " ' in file
    int total = 0;

    //  Array A stores each { ( [ " ' in seperate indices, so if there are more
    //  than 1000 { ( [ " ' combined, then the function will segfault. Make the
    //  first number bigger if you want more room for { ( [ " '. The second
    //  number is only used on { and } because array B stores the indentations
    //  on the same line as either { or }. Then, B gets copied to A, so the {
    //  gets written with its corresponding number of tabs. Note: B counts TABS
    //  (\t), it does not count spaces so make sure the .c file you check as
    //  tabs and not spaces for indentation.
    char A[1000][31];

    //  Holds number of indents in a particular line (max 30)
    char B[30];

    int c;
    int i = 0;
    int j = 0;

    //  Start A with null terminating characters just in case.
    //  Ensures the indices that have nothing in them do not get read
    for (i = 0; i < 1000; i++) {
        A[i][0] = '\0';
    }
    i = 0;
    while ((c = fgetc(fp)) != EOF) {
        j = 0;

        //  Loops until end of file or newline
        while (c != '\n' && c != EOF) {

            //  If a tab was read, write it to B and inc j
            if (c == '\t') {
                printf("proc'd");
                B[j] = c;
                j++;

            //  If { was read, copy B to A (for the tabs) then put { in A
            } else if (c == '{') {
                B[j] = '\0';
                strcpy(A[i], B);
                A[i][j] = c;
                A[i][j+1] = '\0';
                i++;
                total++;

            //  Put [ ( ' or " in A if any of them were read
            } else if (c == '[' || c == '(' || c == '\'' || c == '\"') {
                A[i][0] = c;
                A[i][1] = '\0';
                i++;
                total++;

            //  If a closing brace is read, first it checks if there is at least
            //  1 opening brace (if there isnt it exits). If there is, then
            //  store } and its corresponding tabs in A
            } else if (c == '}') {
                if (i == 0) {
                    return 0;
                }
                B[j] = '\0';
                strcpy(A[i], B);
                A[i][j] = c;
                A[i][j+1] = '\0';
                i++;
                total++;

            //  Similar to else-if above. If there is no [ or ( then it exits.
            //  Otherwise it stores the ] or ) in A
            } else if (c == ']' || c == ')') {
                if (i == 0) {
                    return 0;
                }
                A[i][0] = c;
                A[i][1] = '\0';
                i++;
                total++;
            }
            c = fgetc(fp);
        }
    }

    //  Counters for everything we checked for
    int sqOpenCount = 0;
    int sqCloseCount = 0;
    int parOpenCount = 0;
    int parCloseCount = 0;
    int apostCount = 0;
    int quoteCount = 0;
    for (i = 0; i < total; i++) {

        //  Increment its corresponding counter
        if (A[i][0] == '[') {
            sqOpenCount++;
        } else if (A[i][0] == ']') {
            sqCloseCount++;
        } else if (A[i][0] == '(') {
            parOpenCount++;
        } else if (A[i][0] == ')') {
            parCloseCount++;
        } else if (A[i][0] == '\'') {
            apostCount++;
        } else if (A[i][0] == '\"') {
            quoteCount++;
        }
    }

    //  If there is an odd number of apostrophes or quotes or the number of
    //  opening and closing parantheses do not match or the number of opening
    //  and closing square brace do not match, it exits
    if (sqOpenCount != sqCloseCount || parOpenCount != parCloseCount ||
        apostCount % 2 != 0 || quoteCount % 2 != 0) {
        return 0;
    }

    //  Counter for { } and tabs
    int curlOpenCount = 0;
    int curlCloseCount = 0;
    int tabCount[30];
    for (i = 0; i < 30; i++) {
        tabCount[i] = 0;
    }

    //  Loops through A. If there is a tab, tabCount[i] increments. If there's a
    //  brace, count increments
    for (i = 0; i < total; i++) {
        for (j = 0; j < strlen(A[i]); j++) {
            if (A[i][j] == '\t') {
                tabCount[i]++;
            } else if (A[i][j] == '{') {
                curlOpenCount++;
            } else if (A[i][j] == '}') {
                curlCloseCount++;
            }
        }
    }

    //  If the number of open and closing curly braces do not match, it exits 
    if (curlOpenCount != curlCloseCount) {
        return 0;
    }

    //  Sorts the tab counts from smallest to largest. This makes checking them
    //  easier in the last for loop
    qsort(tabCount, 30, sizeof(int), cmpInt);
    int sameCount = 1;

    //  Checks if number of tab counts match for open and closing braces
    for (i = 1; i < 30; i++) {

        //  If the currrent tab count is the same as the previous tab count,
        //  increment counter to show how many lines have this number of tabs
        if (tabCount[i] == tabCount[i-1]) {
            sameCount++;

        //  If the tabCount[i] changes from the previous one and checks to see
        //  if sameCount is even or odd. If it is odd, then at least one of the
        //  braces is not tabbed correctly. If it is even, reset the count to 1
        } else if (tabCount[i] != tabCount[i-1]) {
            if (sameCount % 2 != 0) {
                return 0;

            //  Otherwise a line with a different number of tabs has been
            //  reached and sameCount is reset
            } else {
                sameCount = 1;
            }
        }
    }

    //  If it makes it through everything, then it is valid
    return 1;
}

//  PROBLEM 5

/**
 * @brief Check if a string contains any vowels
 * 
 * @details A helper function for the pigLatin function.
 * 
 * @param str the string to be checked
 * @param numLetters the number of letters in the string
 * @return 1 if the string contains a vowel, 0 if it does not
*/
int hasVowel(const char *str, const int numLetters) {
    for (int i = 0; i < numLetters; i++) {
        if (str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o'
            || str[i] == 'u') {
            return 1;
        }
    }

    return 0;
}

/**
 * @brief Converts a file in english to Pig Latin
 * 
 * @details Given a file written in english, converts it to Pig Latin by
 * following these rules:
 *  1. If the word starts with "xr" or "yt" or if the word starts with a vowel,
 *     then "ay" is added to the end of the word.
 *  2. If the word starts with a consonant cluster (1-3 consonants, since the
 *     longest consonant cluster at the beginning of an english word is 3
 *     consonants) to the end of the word and add "ay" to the end of the word.
 *  3. If the word starts with a consonant followed by "qu", move them to the
 *     end of the word and add "ay" to the end of the word.
 *  4. If the word contains a "y" after a consonant cluster, move the consonant
 *     cluster to the end of the word and and add an "ay" to the end of the
 *     word.
 * The resulting Pig Latin word is then written to the file "pigged".
 * Additionally, all letters in the file must be in lowercase with no
 * punctuation between letters of words (ex. don't).
 * 
 * @param fp The file pointer to the file containing the word to be converted.
*/
void pigLatin(FILE* fp) {

    //  word stores the current word, newWord stores the word converted to pig
    //  latin twoLetters stores the first two letters of word, threeLetters
    //  stores the first three letters of word.

    //  The longest english word is 45 characters long, plus one more slot for
    //  the null terminating character
    char word[46];

    //  Same size as word except for "ay" may be added on to the end
    char newWord[48];
    char twoLetters[3];
    char threeLetters[4];
    int c, i;
    FILE* pig = fopen("test_files/p5/pigged_after.txt", "w");
    while ((c = fgetc(fp)) != EOF) {
        i = 0;
        newWord[0] = '\0';

        //  Loops to build a word until any punctuation or whitespace or EOF
        while (!isspace(c) && !ispunct(c) && c != EOF) {
            if (i < 48) {
                word[i] = c;
                i++;
            }
            c = fgetc(fp);
        }
        word[i] = '\0';

        //  If the word has at least 2 characters
        if (strlen(word) > 1) {

            //  If the word has at least 3 characters
            if (strlen(word) > 2) {
                threeLetters[0] = word[0];
                threeLetters[1] = word[1];
                threeLetters[2] = word[2];
                threeLetters[3] = '\0';
            }
            twoLetters[0] = word[0];
            twoLetters[1] = word[1];
            twoLetters[2] = '\0';
        } else {
            twoLetters[0] = '\0';
            threeLetters[0] = '\0';
        }

        //  If the first 2 letters are xr or yt, or the first letter is a vowel
        //  RULE 1
        if (strcmp(twoLetters, "xr") == 0 || strcmp(twoLetters, "yt") == 0
            || hasVowel(word, 1)) {
            word[i] = 'a';
            word[i+1] = 'y';
            word[i+2] = '\0';
            strcpy(newWord, word);

        //  If threeLetters is all consonants (excluding y)
        //  RULE 2/4
        } else if (strlen(threeLetters) == 3 && !hasVowel(threeLetters, 3)
                   && threeLetters[2] != 'y') {
            int len = strlen(word);

            for (i = 3; i < len; i++) {
                newWord[i-3] = word[i];
            }

            i = i - 3;
            newWord[i] = threeLetters[0];
            newWord[i+1] = threeLetters[1];
            newWord[i+2] = threeLetters[2];
            newWord[i+3] = 'a';
            newWord[i+4] = 'y';
            newWord[i+5] = '\0';

        //  If twoLetters is all consonants (excluding y and q)
        //  RULE 2/4
        } else if (strlen(twoLetters) == 2 && !hasVowel(twoLetters, 2)
                   && twoLetters[1] != 'y' && twoLetters[1] != 'q') {
            int len = strlen(word);

            for (i = 2; i < len; i++) {
                newWord[i-2] = word[i];
            }

            i = i - 2;
            newWord[i] = twoLetters[0];
            newWord[i+1] = twoLetters[1];
            newWord[i+2] = 'a';
            newWord[i+3] = 'y';
            newWord[i+4] = '\0';

        //  If the first letter is a consonant
        //  RULE 2/3/4
        } else if (!hasVowel(word, 1)) {
            int len = strlen(word);

            //  If the next 2 letters are "qu"
            //  RULE 3
            if (strlen(threeLetters) == 3 && threeLetters[1] == 'q'
                && threeLetters[2] == 'u') {
                for (i = 3; i < len; i++) {
                    newWord[i-3] = word[i];
                }

                i = i - 3;
                newWord[i] = threeLetters[0];
                newWord[i+1] = threeLetters[1];
                newWord[i+2] = threeLetters[2];
                newWord[i+3] = 'a';
                newWord[i+4] = 'y';
                newWord[i+5] = '\0';

            //  RULE 2/4
            } else {
                for (i = 1; i < len; i++) {
                    newWord[i-1] = word[i];
                }
                i = i - 1;
                newWord[i] = word[0];
                newWord[i+1] = 'a';
                newWord[i+2] = 'y';
                newWord[i+3] = '\0';
            }
        }

        //  If newWord was set, write it to the file
        if (strlen(newWord) > 0) {
            fprintf(pig, "%s", newWord);
        }
        fprintf(pig, " ");
    }
}

/**
 * @brief Runs the program
 * 
 * @details Runs the program and manages all relevant files.
*/
int main() {

    //  PROBLEM 1

    FILE* f1 = fopen("test_files/p1/file_comp_1.txt", "r");
    FILE* f2 = fopen("test_files/p1/file_comp_2.txt", "r");
    FILE* output = fopen("test_files/p1/file_comp_result.txt", "w");

    fileCmp(f1, f2, output);

    fclose(f1);
    fclose(f2);
    fclose(output);

    //  PROBLEM 2

    FILE* fp = fopen("test_files/p2/secret_message.txt", "r");
    if (fp == NULL) {
        perror("ERROR opening file");
        return -1;
    }
    encrypt("german", fp);
    fclose(fp);

    FILE* fp2 = fopen("test_files/p2/encrypted.txt", "r");
    if (fp2 == NULL) {
        perror("ERROR opening file");
        return -1;
    }
    decrypt("german", fp2);
    fclose(fp2);

    //  PROBLEM 3

    removeFile("test_files/p3/file_to_remove");

    //  PROBLEM 4

    FILE* fp3 = fopen("test_files/p4/format_check.c", "r");
    if (fp3 == NULL) {
        printf("Couldn't open file\n");
        return -1;
    }
    int retVal = isFormatted(fp3);
    printf("%d\n", retVal);
    fclose(fp3);

    //  PROBLEM 5

    FILE* fp4 = fopen("test_files/p5/pigged_before.txt", "r");
    if (fp4 == NULL) {
        printf("Couldn't open file\n");
        return -1;
    }
    pigLatin(fp4);
    fclose(fp4);
    return 0;
}
