### About This Project
This program was originally written in MARS, a lightweight IDE for programming
in MIPS assembly. Instructions for setup can be found here:
https://www.d.umn.edu/~gshute/mips/Mars/Mars.html. The official GitHub page can
be found here: https://github.com/dpetersanderson/MARS/.

_Last Updated: February 5th, 2024_

### Attachments
In the `attachments` directory, you will find a word document containing the
algorithm I used to conceptualize and construct the program.

# OVERVIEW
This program calculates and display the result of the multiplication of two
square matrices (meaning the number of their rows and columns must be equal).

The program comes with a supplementary text file
(`matrix_mult_data_templates.txt`) so the user can test multiple cases to ensure
proper functionality. 

## How to Use
To use the program, simply assemble and run it. No inputs are needed.

To cross-check the results, please refer to this online
matrix multiplcation calculator which I found quite useful:
https://matrix.reshish.com/multiplication.php.

Please note that the program is initially set up with two 1x1 matrices, which
serve as the simplest form of matrix multiplication. To use the supplementary
templates, simply replace the `.data` section with the template of your choice,
or create your own, ensuring it follows the correct format.

## Notes
- Assumes suitable matrix formatting for multiplication and thus does not offer
erroneous input structure detection.
