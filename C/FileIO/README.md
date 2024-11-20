### About This Project
This program was originally written for and executed on the University of
Wisconsin-Eau Claire's supercomputing cluster, using Vim on a Linux operating
system.

_Last Updated: December 22, 2022_

### Attachments
In the `attachments` directory, you will find:

- A pdf file detailing the original requirements for the program.
- A word document containing the algorithms I used to conceptualize and 
construct the program, written in plain text and language-agnostic.

# OVERVIEW
This program demonstrates my proficiency in file manipulation and effective
memory management in C. It includes:

1. `fileCmp` - Compares the contents of two text files and writes their
differences to a third file.

2. `encrypt` & `decrypt` - Facilitate the encryption and decryption of a custom
secret message using the Columnar Transposition Cipher.

3. `removeFile` - Prints detailed, custom error messages for each error number
if a file cannot be removed.

4. `isFormatted` - Checks the formatting validity of a .c file.

5. `pigLatin` - Translates a text file from English to Pig Latin.

## How to Use
The program is run by the `main` function, found at the bottom of the file (line 
875). All necessary variables are found there. All necessary files can be
found in the `test_files` directory.

### `fileCmp`
- Necessary files are found in [test_files/p1](test_files/p1).

`fileCmp` compares two files, f1 and f2, by writing any differences
(different words or a different order of words) to an output file. It reads in
both files (in this case, `file_comp_1.txt` and `file_comp_2.txt`) and compares
their words. If the words are different, the word from f2 is written to the
output file (`file_comp_result.txt`). It also writes any remaining
words (lack of words in f2 are represented using newlines with slashes) from the
first or second file to the output file if one of the files has reached the
end of file (EOF) before the other.

You may change the contents of `file_comp_1.txt` and `file_comp_2.txt` with
different sentences or word combinations as you please.

### `encrypt` & `decrypt`
- Necessary files are found in [test_files/p2](test_files/p2).

`encrypt` and `decrypt` use a key (MUST BE THE SAME KEY) to encrypt and decrypt
a message using the Columnar Transposition Cipher. To do this, you must specify
the key (in this case, I have used 'german') in `main` for both functions, then
modify the message you with to encrypt and/or decrypt in `secret_message.txt`.

The contents of the message needn't be alphanumeric or case-homogenous. Any
necessary text padding is achieved using x's.

To view results, simply check `encrypted.txt` and `decrypted.txt` after running
the program.

For information on the Columnar Transposition Cipher, please visit
http://practicalcryptography.com/ciphers/columnar-transposition-cipher/.

### `removeFile`
- Necessary files are found in [test_files/p3](test_files/p3).

This function removes a file from the file system given its filename (in this
case, `file_to_remove`, but you can use any filename). If the file is
successfully removed, it prints a message indicating this. If the file cannot be
removed, it prints an error message with the correct description.

**PLEASE NOTE THAT THE FILE MUST BE MANUALLY RESTORED AFTER DELETION.**

### `isFormatted`
- Necessary files are found in [test_files/p4](test_files/p4).

`isFormatted` checks a .c file for proper formatting (in this case,
`format_check.c`, although this is customizable), printing 1 if it is properly
formatted, and 0 if is not (refer to the function documentation for minutiae)
to the console.

### `pigLatin`
- Necessary files are found in [test_files/p5](test_files/p5).

Given a file (in this case, `pigged_before.txt`) written in english, `pigLatin`
converts it to Pig Latin and writes the results in another file
(`pigged_after.txt`). Please view the documentation or 
[cs252_io_assignment.pdf](attachments/cs252_io_assignment.pdf) for more
information on Pig Latin.

You may change the contents of the input file as you please, as long as you do
not include punctuation and the words are lowercase.

## Output
The only things printed to the console are:

- The message from `removeFile`.
- The result from `isFormatted`.

All other outputs are reflected in each function's corresponding file(s). Please
see the **"How to Use"** section for details.
