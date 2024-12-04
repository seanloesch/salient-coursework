/**
 * @file sm.c
 * @brief A program demonstrating various string manipulation techniques in C,
 * written using Vim
 * @date 2022-12-18
 * @author Sean Loesch
 * 
 * @details This program demonstrates proficiency in various string manipulation
 * techniques and dynamic memory management in C. It includes:
 * 
 * 1. recCap - Recursively capitalizes a string and prints it to
 * the console.
 * 
 * 2. reverseStr - Reverses a string in place (the same memory
 * location).
 * 
 * 3. insertStr - Inserts a string into another string using
 * various rules (see documentation for details).
 * 
 * 4. longestSameSeq - Calculates the length of the longest common subsequence
 * of two given strings containing alphabetical characters.
 * 
 * 5. longestPal - Finds the longest Palindromic substring from a given string
 * and returns it.
 * 
 * 6. wordSort - Sorts the words from a file (wherein the words are delimited by
 * newline characters) from longest to shortest, writing them in a new file.
 * 
 * 7. countChars - Counts the number of times each character (characters must
 * have an int value of 0-128) in a string appears and prints the results to the
 * console.
 * 
 * @note See README.md for additional details.
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/**
 * @brief Recursively prints a string in all capitals, regardless of what the
 * case of the original characters were
 * 
 * @details Recursively prints a string of characters one by one
 * in all capitals to the console, regardless of the case of the original
 * characters. This is achieved using modular arithmetic and ASCII values
 * instead of switches or other conditional statements to determine each
 * character's case.
 * 
 * @note Capitalization applies only to alphabetical characters, as it can occur
 * only in such context.
 * 
 * @param str string to be capitalized
 */
void recCap(char *str) {
    
    //  If string is empty, exit
    if (str[0] == '\0') {
        return;
    }
    
    //  Convert first character of str to an int
    char currChar = str[0];
    int currInt = (int) currChar;
    
    //  If character is a letter, capitalize it
    if ((65 <= currInt && currInt <= 90) 
         || (97 <= currInt && currInt <= 122) ) {
                
        //  Reduces currInt to range {1-26} using modulus and adds 64 to make it
        //  in the range {65-90} (the range for lowercase letters in ASCII)
        currInt = (currInt % 32) + 64;
        currChar = (char) currInt;
    }
    
    printf("%c", currChar);
    
    //  remove first character in str
    char *str_chopped = str + 1;
    
    recCap(str_chopped);
}

/**
 * @brief Reverses string in place in memory
 * 
 * @details Reverses a string in place in memory, meaning no auxiliary string 
 * variables are used.
 * 
 * @param str string to be reversed
 */
void reverseStr(char *str) {
    
    int len = strlen(str);
    
    //  Loop over str. Integer division doesn't include
    //  middle character if len is odd
    for (int i = 0; i < len / 2; i++) {
        char temp = str[i];
        str[i] = str[len - i - 1];
        str[len - i - 1] = temp;
    }
}

/**
 * @brief Inserts string s2 into s1 directly after the kth character
 * 
 * @details If k is less than or equal to 0, s2 is inserted before s1. If k is
 * greater than the length of s1, s2 is inserted after s1. If k is within the
 * bounds of s1's length, s2 is inserted into s1 after the kth character (index
 * k).
 * 
 * @warning The result must be freed in case 3 after the function call, else a
 * memory leak will occur.
 * 
 * @param s1 the string to be inserted into
 * @param s2 the string to insert
 * @param k the index of the insertion site
 * @return the combined string
 */
char* insertStr(char *s1, char *s2, int k) {
    
    int lenS1 = strlen(s1);
    
    //  Case 1: k is 0
    if (k <= 0) {
        return strcat(s2, s1);
    }
    //  Case 2: k > length of s1
    else if (k > lenS1) {
        return strcat(s1, s2);
    }
    //  Case 3: k is within the bounds of s1's length
    else {

        //  Make res the same size as s1 + s2 - 1 (no need for duplicate null
        //  terminator) to prevent segfault
        char *res = malloc(sizeof(s1) + sizeof(s2) - 1);

        int i, j;

        //  Inserting first portion of s1
        for (i = 0; i < k; i++) {
            res[i] = s1[i];
        }

        int resIndex = i;

        //  Inserting s2 (null terminator excluded)
        for (j = 0; j < sizeof(s2) - 1; j++, resIndex++) {
            res[resIndex] = s2[j];
        }

        //  Inserting rest of s1 (null terminator included)
        for (i; i < sizeof(s1); i++, resIndex++) {
            res[resIndex] = s1[i];
        }
        
        return res;
    }
}

/**
 * @brief Calculates the length of the longest common subsequence of two given
 * strings
 * 
 * @param s1 the first string
 * @param s2 the second string
 * @return the length of the longest common subsequence
 */
int longestSameSeq(char *s1, char *s2) {
    
    int lenS1 = strlen(s1);
    int lenS2 = strlen(s2);
    
    int longest = 0;
    
    //  Loop through every letter of both strings
    for (int i = 0; i < lenS1; i++) {
        
        //  Short circuit if the number of characters left in S1 is less than
        //  the longest similar subsequence found
        if (lenS1 - i < longest) {
            break;
        }
        
        for (int j = 0; j < lenS2; j++) {
            
            //  Short circuit if the number of characters left in S2 is less
            //  than the longest similar subsequence found
            if (lenS2 - j < longest) {
                break;
            }
            
            //  If a match is found, begin counting the length of the current
            //  matching subsequence
            if (s1[i] == s2[j]) {
                int currSequence = 1;
                
                //  Until you reach the end of one of the strings or the current
                //  subsequence stops matching, keep track of the current
                //  subsequence
                while (i + currSequence < lenS1 &&
                       j + currSequence < lenS2 &&
                       s1[i + currSequence] == s2[j + currSequence]) {
                    
                    currSequence++;
                }
                
                //  If the current similar subsequence is longer than the
                //  current stored longest similar subsequence, store that
                //  length
                if (currSequence > longest) {
                    longest = currSequence;
                }
                
            }
            
        }
    }
    
    return longest;
}

/**
 * @brief Finds the longest palindrome in a string
 * 
 * @details Finds the longest palindromic substring in a string, with the
 * shortest valid palindrome being 2 characters.
 * 
 * @warning To ensure there are no segfaults, ensure that res is at least as
 * large as str.
 * 
 * @param res the string holding the result
 * @param str the checked string
 * @return the longest palindrome
 */
char* longestPal(char *res, char *str) {
    
    int lenStr = strlen(str);
    
    //  Initialize reversed version of str for comparison
    char *strRev = malloc(lenStr * sizeof(char));
    strcpy(strRev, str);
    reverseStr(strRev);
    
    //  Stores the indices of the longest palindrome
    int start = 0;
    
    //  Stores the length of the longest palindrome
    int longestLen = 0;
    
    //  Iterate through characters in str
    for (int i = 0; i < lenStr; i++) {
        
        //  Short circuits if the number of characters left is less than the
        //  longest palindrome already found
        if (longestLen > lenStr - i) {
            break;
        }
        
        //  Iterate through characters in strRev
        for (int j = 0; j < lenStr; j++) {
            
            //  Short circuits if the number of characters left is less than the
            //  longest palindrome already found  
            if (longestLen > lenStr - j) {
                break;
            }
            
            //  If characters match
            if (str[i] == strRev[j]) {
                
                //  Stores length of current palindrome being checked
                int runner = 1;
                
                //  Until you reach the end of one of the strings or the current
                //  subsequence stops matching, keep track of the current
                //  subsequence
                while (i + runner < lenStr &&
                       j + runner < lenStr &&
                       str[i + runner] == strRev[j + runner]) {
                    
                    runner++;
                }
                
                //  Check if the last character of the palindrome is the first
                //  character matched (the palindrome is connected) and this
                //  palindrome is longer than the longest found palindrome
                if (i + runner == lenStr - j &&
                    runner > longestLen) {
                    
                        longestLen = runner;
                        start = i;
                }
                
            }
            
        }
        
        
    }
    
    free(strRev);
    
    //  If the longest found palindrome is 1 character long, return res (empty)
    if (longestLen == 1) {
        return res;
    }
    
    //  Building palindrome substring (res) from str
    for (int i = 0; i < longestLen; i++) {
        
        res[i] = str[start + i];
    }
    
    res[longestLen] = '\0';
    
    return res;
}

/**
 * @brief Compare two strings and return their length difference
 * 
 * @details Compares two strings and returns their difference in
 * length. It is used as a callback function for qsort to sort an array of
 * strings in descending order of lengths. Used in wordSort.
 * 
 * @param a pointer to the first string to compare
 * @param b pointer to the second string to compare
 * @return the difference between the two strings' lengths
*/
int cmpFunc(const void* a, const void* b) {

    //  b - a instead of a - b to sort longest to shortest
    return strlen(*(const char**)b) - strlen(*(const char**)a);
}

/**
 * @brief Sorts words in a file from longest to shortest and writes them to
 * another file
 * 
 * @details Sorts words in a file from longest to shortest. Writes words to
 * sorted.txt. Empty lines in the file being read are allowed, but not counted.
 * 
 * @warning The function may exhibit undefined behavior or throw errors if the
 * following requirements are not met:
 * - The words from the unsorted file must be on their own lines.
 * - Word length musn't exceed 99 characters.
 * - There should be an empty line at the end of the read file for the most
 * readable results.
 * 
 * @param filename the file to be analyzed
 */
void wordSort(char *fileName) {

    //  Open file. If it doesn't work, print error message
    FILE *fp = fopen(fileName, "r");
    if (fp == NULL) {
        printf("Error opening file\n");
        return;
    }

    //  Number of lines in file
    int lineCounter = 0;
    //  Length of longest word in file (excluding null terminator)
    int maxLength = 0;

    //  Temporary variable storing current word
    char buf[100];

    //  Reads either until a newline or 100 characters on a line.
    //  Exit when it reaches the end of the file
    while (fgets(buf, 100, fp) != NULL) {

	    //  Move to next line    
        lineCounter++;
        
	    //  If the current word is larger than the largest
	    //  stored word, store its length
        if (strlen(buf) > maxLength) {
            maxLength = strlen(buf);
        }
    }

    //  Declare an array with lineCounter number of strings of length
    //  lenCounter + 1
    char **wordsArr = malloc(lineCounter * sizeof(char *));
    for (int i = 0; i < lineCounter; i++) {
         //  Allocate memory for each row
        wordsArr[i] = malloc((maxLength + 1) * sizeof(char));
    }

    //  Counter for inserting values into wordsArr
    int i = 0;

    //  Starts reading from the beginning of the file with 0 offset.
    //  Moves through the file without reading or writing to it
    fseek(fp, 0, SEEK_SET);

    //  Inserting values into wordsArr from file
    while (fgets(buf, 100, fp) != NULL) {

	    //  Inserts current word into wordsArr
        if (strlen(buf) > 0) {
            strcpy(wordsArr[i], buf);
            i++;
        }
        
    }

    //  Sort array using qsort. Sorts from smallest to largest
    qsort(wordsArr, lineCounter, sizeof(wordsArr[0]), cmpFunc);

    //  Close file storing unsorted words (parameter)
    fclose(fp);

    //  Open file that will store the sorted words
    FILE *fp2 = fopen("test_files/sorted.txt", "w");
    if (fp2 == NULL) {
        printf("Error opening file\n");
        return;
    }

    //  Write to sorted.txt
    for (int i = 0; i < lineCounter; i++) {
        fprintf(fp2, "%s", wordsArr[i]);
    }

    //  Deallocate 2D array wordsArr
    for (int i = 0; i < lineCounter; i++) {
        free(wordsArr[i]); //  Free each row of wordsArr
    }
    free(wordsArr);

    //  Close file storing sorted words
    fclose(fp2);
}

/**
 * @brief Counts the number of times each character in a string appears and
 * displays said counts to the console
 * 
 * @note
 * - Only counts characters that appear in the standard ASCII chart {0-127}.
 * - Displays a character's count only if it is nonzero, and in the order of
 * which they appear in the ASCII table, delimited by newlines.
 * 
 * @param str the string being analyzed
 */
void countChars(char *str) {
    
    int counts[128];
    
    int lenStr = strlen(str);
    
    //  Fill counts with 0
    for (int i = 0; i < 128; i++) {
        counts[i] = 0;
    }
    
    for (int i = 0; i < lenStr; i++) {
        
        //  Cast each character in str to ASCII
        int value = (int) str[i];
        
        //  Increment count of current character
        counts[value]++;
    }

    //  Displaying counts of each character
    for (int i = 0; i < 128; i++) {
        
        if (counts[i] > 0) {
            printf("%c: %d\n", i, counts[i]);
        }
    }
    
}

/**
 * @brief Runs the program
 * 
 * @details Runs the program and manages all relevant files.
*/
int main() {
    
    //  TESTING recCap
    char test1[] = "sghdlYFDPO456[]-=;/.";
    recCap(test1);
    printf("\n\n");
    
    //  TESTING reverseStr
    char test2[] = "baseball";
    reverseStr(test2);
    printf("%s\n\n", test2);
    
    //  TESTING insertStr
    //  Case 1: k <= 0
    char test3A1[] = "one";
    char test3A2[] = "two";
    printf("%s\n", insertStr(test3A1, test3A2, 0));
    //  Case 2: k > length of s1
    char test3B1[] = "one";
    char test3B2[] = "two";
    printf("%s\n", insertStr(test3B1, test3B2, 99999));
    //  Case 3: k is within the range {0-length of s1}
    char test3C1[] = "one";
    char test3C2[] = "two";
    char *insertRes = insertStr(test3C1, test3C2, 1); //  Store returned pointer
    printf("%s\n\n", insertRes);
    free(insertRes); //  Free allocated memory
    
    //  TESTING longestSameSeq
    char test4S1[] = "Football";
    char test4S2[] = "Basketball";
    printf("%d\n\n", longestSameSeq(test4S1, test4S2));
    
    
    //  TESTING longestPal
    char test5Res[50]; 
    char test5Str[] = "hdasABCBAjhgfgjofdiABCDCBA";
    printf("%s\n\n", longestPal(test5Res, test5Str));
   
    //  TESTING wordSort
    wordSort("test_files/words.txt");

    //  TESTING countChars
    char test7Str[] = "32572890hgfjhkdlfghsdjkl';'./     ";
    countChars(test7Str);
    printf("\n");

    return 0;
}
