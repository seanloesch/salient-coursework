### About This Project
This program was originally written for and executed on the University of
Wisconsin-Eau Claire's supercomputing cluster, using Vim on a Linux operating
system.

_Last Updated: December 18, 2022_

### Attachments
In the `attachments` directory, you will find:

- A pdf file detailing the original requirements for the program.
- A word document containing the algorithms I used to conceptualize and 
construct the program, written in plain text and language-agnostic.

# OVERVIEW
This program demonstrates my proficiency in string manipulation and effective
memory management in C. It includes:

1. `recCap` - Recursively capitalizes a string and prints it to the console.

2. `reverseStr` - Reverses a string in place (the same memory location).

3. `insertStr` - Inserts a string into another string using various rules (see
documentation for details).

4. `longestSameSeq` - Calculates the length of the longest common subsequence of
two given strings containing alphabetical characters.

5. `longestPal` - Finds the longest Palindromic substring from a given string
and returns it.

6. `wordSort` - Sorts the words from a file (wherein the words are delimited by
newline characters) from longest to shortest, writing them in a new file.

7. `countChars` - Counts the number of times each character (characters must
have an int value of 0-128) in a string appears and prints the results to the
console.

## How to Use
The program is run by the `main` function, found at the bottom of the file (line 
486). All necessary variables are found there. All necessary files can be
found in the `test_files` directory.

You may change the contents of the test variables and files as long as their
contents abide by the limitations and restrictions of each function (view their
documentation for details).

### `recCap`

`recCap` recursively prints a string of characters one by one in all capitals to
the console, regardless of the case of the original characters. Note that
capitalization applies only to alphabetical characters, as it can occur only in
such context. 

### `reverseStr`

`reverseStr` reverses a string in place in memory, meaning no auxiliary string
variables are used.

### `insertStr`

`insertStr` Inserts a string (s2) into another (s1) following a specified index
(k), bifurcating it if k's value lies within s1's length. More specifically:

- If k is less than or equal to 0, s2 is inserted before s1.
- If k is greater than the length of s1, s2 is inserted after s1.
- If k is within the bounds of s1's length, s2 is inserted into s1 after index
k.

### `longestSameSeq`

`longestSameSeq` calculates the length of the longest common subsequence of two
given strings and returns said value.

### `longestPal`

`longestPal` finds the longest palindromic substring in a string, with the
shortest valid palindrome being 2 characters.

### `wordSort`
- Necessary files are found in [test_files](test_files).

`wordSort` sorts words in `words.txt` from longest to shortest and writes the
results to `sorted.txt`. Empty lines in the read file are allowed, but are
overlooked.

### `countChars`

`countChars` counts the number of times each character in a string appears and
displays said counts to the console.

## Output
Upon running the program, you will see these results printed to the console in
the following order:

1. The capitalized version of the string passed through `recCap`.

2. The reversed version of the string passed through `reverseStr`.

3. The results for the three cases of `insertStr`.

4. The length of the longest same sequence of the strings passed through
`longestPal`.

5. The longest palindrome found in the string passed through `longestPal`.

6. The set of characters found in the string passed through `countChars`
followed by their respective counts.

*(Note that the results for* `wordSort` *will be written to* 
[test_files/sorted.txt](test_files/sorted.txt) *instead of being printed to the
console.)*
