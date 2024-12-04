### About This Project
This program is the culmination of my first semester of coding experience. Since
I wrote this early in my education, comments are sparse. Thus, I wrote this
readme to elucidate its function and outputs.

This project was originally written in IntelliJ IDEA.

_Last Updated: December 14, 2021_

# OVERVIEW
This program uses a greedy algorithm to calculate the egyptian fraction
representation of user-specified fractions.

An Egyptian fraction is a sum of positive (and in this case, distinct) fractions
used to represent other fractions. For example, you can represent 1/1 (or 1)
with the expression `1/2 + 1/3 + 1/6`. This program calculates and displays this
representation of the fractions specified in the `egyptianfractions.txt` file. 

(_For a more detailed explanation, visit this resource from the creators of
Wolfram Alpha_: https://mathworld.wolfram.com/EgyptianFraction.html)

Due to the program's utilization of a greedy algorithm, process times are long.
I recommend not inserting any fractions that have a value higher than 1/1 for
the sake of reducing wait time.

### BigFraction
A `BigFraction` is my take on fractional representation using `BigInteger`s from
the `Math` library. It is simply two `BigInteger`s bifurcated into separate
numerator and denominator variables. `BigInteger`s are used to accommodate
fractions with large constituent elements, namely denominators during egyptian
fraction calculation.

### `fractions.txt`'s Content
- The first number denotes how many fractions there are
(there must be exactly 4 - it is hard-coded)
- Each subsequent pair of numbers comprises a corresponding numerator and
denominator, specified in that order. As stated previously, there are 4 pairs,
each representing a fraction.

### `egyptianfractions.txt`'s Content
`egyptianfractions.txt` has the same format and constraints as `fractions.txt`
(see above).

## OUTPUT
The first four outputs to the console are the four fractions specified in
`fractions.txt`.

The next four outputs are:

1. The 1st fraction + the 2nd fraction
2. The 3rd fraction - the 1st fraction
3. The 1st fraction * the 4th fraction
4. The 1st fraction / (the 1st fraction + the 2nd fraction)

The next four outputs are the egyptian fraction representations of the four
fractions specified in `egyptianfractions.txt`.

The final two outputs are:
1. 1st fraction in `egyptianfractions.txt` + 2nd fraction (in egyptian fraction
representation)
2. 3rd fraction + 4th fraction (in egyptian fraction representation)
