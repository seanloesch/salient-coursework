# matrix_mult.s
# Date: February 5, 2024
# Description: A MIPS assembly program to calculate and display the result of the 
# multiplication of two square matrices. Assumes suitable matrix formatting for 
# multiplication and thus does not offer erroneous input structure detection.
#      Author: Sean Loesch

	.data
space:    .byte   ' '      # Space character, for convenience
newline:  .byte   '\n'     # Newline character, for convenience
matrix_a:
    .word    7
matrix_b:
    .word    5
# Resultant matrix
matrix_c:
    .word    0

# Constants for matrix array dimensions
a_rows:   .word   1
a_cols:   .word   1        # Note b_rows need not be inlcuded - a_cols and
b_cols:   .word   1        # b_rows must be the same in matrix multiplcation
c_rows:   .word   1
c_cols:   .word   1

	.text
# Register usage:
#  $t0 - a_rows
#  $t1 - b_cols
#  $t2 - a_cols
#  $t3 - 1. temporary storage for loading the result from an a x b multiplication into
#        the correct element in matrix_c
#        2. multiplicand (c_rows) to find total number of integers in matrix_c for printing
#  $t4 - c_cols:
#        1. multiplicand to find total number of integers in matrix_c for printing
#        2. divisor determinant to print/not print newline
#  $t5 - memory offset/element storage for matrix_a
#  $t6 - memory offset/element storage for matrix_b
#  $t7 - memory offset/element storage for matrix_c
#  $t8 - product of matrix_a & matrix_b elements, added to current matrix_c element
#  $s0-$s2 - Outer, middle and inner loop counters (matrix multiplcation)
#  $s3 - Print loop counter
###############################################################################
main:                                                                         #
    # Load a_rows, b_cols, and a_cols into $t0, $t1, and $t2                  #
    lw    $t0,    a_rows                                                      #
    lw    $t1,    b_cols                                                      #
    lw    $t2,    a_cols                                                      #
                                                                              #
# Loop over rows in matrix_a                                                  #
outer_begin:                                                                  #
    # Check if outer loop counter (using $s0) <= a_rows                       #
    bge   $s0,    $t0,    outer_end                                           #
                                                                              #
    # Initialize/update middle loop counter                                   #
    li    $s1,    0                                                           #
                                                                              #
# Loop over columns in matrix_b                                               #
middle_begin:                                                                 #
    # Check if middle loop counter <= b_cols                                  #
    bge   $s1,    $t1,    middle_end                                          #
                                                                              #
    # Initialize/update inner loop counter                                    #
    li    $s2,    0                                                           #
                                                                              #
# Loop over columns in matrix_a and rows in matrix_b (they are equal)         #
inner_begin:                                                                  #
    # Check if inner loop counter <= a_cols                                   #
    bge   $s2,    $t2,    inner_end                                           #
                                                                              #
    # Calculate memory offset for the current element in matrix_a             #
    mul   $t5,    $s0,    $t2    # Offset for current row                     #
    add   $t5,    $t5,    $s2    # Offset for current column                  #
                                                                              #
    # Convert word offset to byte offset (multiply by 4)                      #
    sll   $t5,    $t5,    2                                                   #
                                                                              #
    # Load currrent element from matrix_a into $t5                            #
    lw    $t5,    matrix_a($t5)                                               #
                                                                              #
    # Calculate memory offset for the current element in matrix_b             #
    mul   $t6,    $s2,    $t1    # Offset for current row                     #
    add   $t6,    $t6,    $s1    # Offset for current column                  #
                                                                              #
    # Convert word offset to byte offset (multiply by 4)                      #
    sll   $t6,    $t6,    2                                                   #
                                                                              #
    # Load current element from matrix_b into $t6                             #
    lw    $t6,    matrix_b($t6)                                               #
                                                                              #    
                                                                              #    
                                                                              #
    # Multiply current elements in matrix_a and matrix_b, store result in $t8 #
    mul   $t8,    $t5,    $t6                                                 #
                                                                              #
    # Calculate memory offset for the current element in matrix_c             #
    mul   $t7,    $s0,    $t2    # Offset for current row                     #
    add   $t7,    $t7,    $s1    # Offset for current column                  #
                                                                              #
    # Convert word offset to byte offset (multiply by 4)                      #
    sll   $t7,    $t7,    2                                                   #
                                                                              #
    # Add the result from multiplication to the correct element in matrix_c   #
    lw    $t3,    matrix_c($t7)                                               #
    add   $t8,    $t8,    $t3                                                 #
    sw    $t8,    matrix_c($t7)                                               #
                                                                              #
                                                                              #
                                                                              #
    # Increment inner loop counter                                            #
    add   $s2,    $s2,    1                                                   #
                                                                              #
    # Move on to next element in matrix_c                                     #
    j     inner_begin                                                         #
                                                                              #
inner_end:                                                                    #
    # Increment middle loop counter                                           #
    add   $s1,    $s1,    1                                                   #
                                                                              #
    # Move on to next column of matrix_b                                      #
    j     middle_begin                                                        #
                                                                              #
middle_end:                                                                   #
    # Increment outer loop counter                                            #
    add   $s0,    $s0,    1                                                   #
                                                                              #
    # Move on to next row of matrix_a                                         #
    j     outer_begin                                                         #
                                                                              #
outer_end:                                                                    #
                                                                              #
print_matrix_c:                                                               #
    # Initialize print loop counter                                           #
    li    $s3,    0                                                           #
                                                                              #
    # Find total number of integers in matrix_c and store result in $t3       #
    lw    $t3,    c_rows                                                      #
    lw    $t4,    c_cols                                                      #
    mul   $t3,    $t3,    $t4                                                 #
                                                                              #
# Print elements in matrix_c                                                  #
print_loop_begin:                                                             #
                                                                              #
    # Check if print loop counter <= total number of elements in matrix_c     #
    bge   $s3,    $t3,    print_loop_end                                      #
                                                                              #
    # If loop counter is a multiple of the number of columns in matrix_c,     #
    # print the newline character                                             #
    div   $s3,    $t4                                                         #
    mfhi  $t1                   # Set t1 to remainder of quotient             #
    beqz  $t1,    skip_code     # Check if $t1 == 0                           #
                                                                              #
    j     continue              # Jump after newline print code if $t1 != 0   #
                                                                              #
skip_code:                                                                    #
    # Print newline character                                                 #
    lb    $a0,    newline                                                     #
    li    $v0,    11                                                          #
    syscall                                                                   #
                                                                              #
continue:                                                                     #
    # Convert word offset to byte offset for the current element in matrix_c  #
    sll   $t1,    $s3,    2                                                   #
                                                                              #
    # Retrieve element from matrix_c and print it                             #
    lw    $a0,    matrix_c($t1)                                               #
    li    $v0,    1                                                           #
    syscall                                                                   #
                                                                              #
    # Print space                                                             #
    lb    $a0,    space                                                       #
    li    $v0,    11                                                          #
    syscall                                                                   #
                                                                              #
    # Increment print loop counter                                            #
    add   $s3,    $s3,    1                                                   #
                                                                              #
    # Move on to next element of matrix_c                                     #
    j     print_loop_begin                                                    #
                                                                              #
print_loop_end:                                                               #
                                                                              #
end_program:                                                                  #
    # Terminate program                                                       #
    li    $v0,    10                                                          #
    syscall                                                                   #
###############################################################################
