### About This Project
This program was originally written in MARS, a lightweight IDE for programming
in MIPS assembly. Instructions for setup can be found here:
https://www.d.umn.edu/~gshute/mips/Mars/Mars.html. The official GitHub page can
be found here: https://github.com/dpetersanderson/MARS/.

_Last Updated: February 6th, 2024_

### Attachments
In the `attachments` directory, you will find a word document containing the
algorithm I used to conceptualize and construct the program.

# OVERVIEW
This program calculates the factorial of a provided number (between 0-12,
inclusive).

The reasoning behind the upper limit of the accepted range is due to 13! being
the first factorial that breaches the 32-bit integer limit. To be specific, 13!
= 6,227,020,800, which exceeds the 32-bit limit of 4,294,967,295.

The program demonstrates my understanding of how recursive functions relate to
the call stack.

## How to Use
You will be prompted to enter your desired integer upon running the program.
Simply enter the desired number and the program will spit out the result.

## Notes
- Achieves recursive functionality by directly manipulating the call stack.
- There is no case handling for integers greater than 12 — MARS will simply
throw an error.
