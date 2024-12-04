### About This Project
This program was originally written in Visual Studio Code.

_Last Updated: May 15, 2023_

# OVERVIEW
This program consists of two main components: **islands** and **books**. First,
the user is prompted to specify the dimensions of a pseudo-randomly generated
`BooleanMatrix`, which is filled with 1s and 0s. In this context, 1s represent
land and 0s represent water. The program calculates the number of islands, or
connected subgraphs, by considering both orthogonal and diagonal connections.
The results, including the total number of islands, are displayed to the user.

The second component establishes a theoretical framework for a book sales
system. This includes several classes: `Book`, `ForSaleBook` (books available
for sale), `ExaminationCopy` (copies of books provided to specific recipients
for review), and `Box` (containers for storing the books). Each book has a title
and weight, but depending on the specific book variant, it may have a price
(unique to `ForSaleBook`) or a specific recipient (unique to `ExaminationCopy`).
Various tests are conducted to ensure the correct function of these classes, and
results are displayed to the user. For more details, please refer to the
documentation for each class and the Main.java file.

## How to Use
Run `Main.java` and enter the desired number of rows and columns for the matrix
into the console when prompted. Although functionality extends to large
matrices, I recommend keeping the rows and columns in single digits in order to
properly verify the results reliably with your own eyes. No further action is
necessary.

Depending on the size of your console display, you may have to scroll up to
view the results for the `BooleanMatrix` class tests and see the various `Book`
variant tests.

## Output
The program prompts the user to designate the size of the matrix, then displays
these test results to the console in the following order:

1. The generated `BooleanMatrix` and its number of islands
2. The info in a `Book`
3. The info in a `ForSaleBook`
4. The info in an `ExaminationCopy`
5. The ID of a `Box`
6. The contents of the `Box`
7. An attempt to add a book to the box that exceeds the weight limit of that box
(it should end in a `ValueError` exception being thrown)
