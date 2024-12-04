### About This Project
This program was originally written in MARS, a lightweight IDE for programming
in MIPS assembly. Instructions for setup can be found here:
https://www.d.umn.edu/~gshute/mips/Mars/Mars.html. The official GitHub page can
be found here: https://github.com/dpetersanderson/MARS/.

_Last Updated: April 25th, 2024_

### Attachments
In the `attachments` directory, you will find a word document containing the
algorithms I used to conceptualize and construct the program.

# OVERVIEW
This is a fully functional linked list program allowing the insertion, deletion,
and printing of integers from a linked list.

This project was created to demonstrates my understanding of the functionings of
data structures at near-machine-language level.

## How to Use
The program will prompt you to choose an action by entering its assoicated key:

1. Insert new integer - `i`
2. Delete value - `d`
3. Traverse and print list - `tp`
4. Quit - `q`

You can build and edit your own linked list through the commands allotted to
you.

## Notes
- The deletion function does not delete duplicates.
- Additionally, the MARS simulator does not allow for manual memory deallocation
back to the heap, making memory leaks unavoidable upon node deletion.
