### About This Project
This program was originally written in Visual Studio Code.

_Last Updated: October 25th, 2023_

# OVERVIEW
This program was developed to demonstrate my understanding and proficiency in
multithreading. This program can accomplish two things:

1. Compute the total sum of a jagged 2D array of ints represented by a text
file

2. Display the amount of Fibonnaci numbers specified by the user

Both are accomplished using multithreading.

## How to Use

### Array Sum
This part of the program uses the information in
[array_input.txt](src/array_input.txt) to compute the sum of the resulting
arrays. In `array_input.txt`, each line represents an array, with the first line
denoting the number of arrays followed by the length of the longest array. All
values must be space-delimited. The file is pre-prepared, but it can easily be
adjusted.

To compute the sum of `array_input.txt`'s constituent elements, simply run
[MultiThreadSum.java](src/MultiThreadSum.java) and view the result printed to
the console.

### Fibonacci Numbers
In this portion of the program, upon running
[MultiThreadFibonacci.java](src/MultiThreadFibonacci.java), you will be
prompted to enter the desired amount of Fibonacci numbers to be displayed within
the range {2-47} inclusive. Reasoning for this can be found in the class
documentation for `MultiThreadFibonacci.java`.

Once you enter your desired number, it will be displayed in a list-like format
to the console.

## Output

### `MultiThreadSum.java`

The total sum of the 2D array.

### `MultiThreadFibonacci.java`

1. The prompt requiring the user to specify the desired amount of Fibonacci
numbers.
2. The resultant list of Fibonacci numbers.
