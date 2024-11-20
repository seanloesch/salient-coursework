### About This Project
The constituent programs of this project were originally written in MARS, a
lightweight IDE for programming in MIPS assembly. Instructions for setup can be
found here: https://www.d.umn.edu/~gshute/mips/Mars/Mars.html. The official
GitHub page can be found here: https://github.com/dpetersanderson/MARS/.

This was the final programming project I undertook during my undergrad.

_Last Updated: April 26th, 2024_

### Attachments
In the `attachments` directory, you will find a word document containing the
algorithms I used to conceptualize and construct the programs, written in plain
text and language-agnostic.

# OVERVIEW / HOW TO USE
This project was created to demonstrate my understanding of instruction set
architectures. It contains several programs:

### `linked_list.asm`
A fully functional linked list program allowing the insertion, deletion, and
printing of elements from a linked list.

### `matrix_mult.asm`
Calculates and display the result of the multiplication of two square matrices.
Comes with a supplementary text file (`code/matrix_mult_data_templates.txt`) so
the user can test multiple cases to ensure proper functionality. To cross-check
the results, please refer to this online matrix multiplcation calculator which I
found quite useful: https://matrix.reshish.com/multiplication.php.

Please note that the program is initially set up with two 1x1 matrices, which
serve as the simplest form of matrix multiplication. To use the supplementary
templates, simply replace the .data section with the template of your choice, or
create your own, ensuring it follows the correct format.

### `rec_factorial.asm`
Calculates the factorial of a provided number (between 0-12, inclusive).
Reasoning for the accepted range of values is provided in the documentation.

### `string_reversal.asm`
Reverses a user-inputted string in place, supporting strings up to 25 characters
in length.
