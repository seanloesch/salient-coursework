### About This Project
These programs were originally written for and executed on an x86 Assembly
Emulator. The emulator can be found at https://carlosrafaelgn.com.br/Asm86/.

_Last Updated: November 1, 2022_

### Attachments
In the `attachments` directory, you will find a word document containing the
algorithms I used to conceptualize and construct the programs, written in plain
text and language-agnostic.

# OVERVIEW
These programs were written on an x86 emulator used in my Computer Systems
course. The documentation for each program explains how to set up and run it.

In this project, I demonstrate my basic proficiency in x86 assembly,
particularly in MOV operations, simple loops, and understanding endianness:

## ArrayShift

Rotates the members of a 32-bit byte array forward one position
(e.g., from {1, 2, 3, 4, 5} to {5, 1, 2, 3, 4}), where the value originally at
the end of the array is wraps to the first position of the array.

## EndianFlip

Takes a 32-bit number represented by the bytes 12h, 34h, 56h, and 78h in
big-endian format and rearranges them to produce the same four bytes in
little-endian format (78h, 56h, 34h, 12h).

## Output

All output foe each program can be viewed in the registers window of the
emulator. Directions for viewing are provided in their documentation.
