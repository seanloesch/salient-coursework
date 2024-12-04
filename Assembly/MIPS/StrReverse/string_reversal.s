# string_reversal.s
# Date: February 5, 2024
# Description: Reverses a string in place using the registers. Accepts strings up to
# 25 characters long, which is stored in 27 bytes to account for the newline charatcer
# (inserted by syscall 8 in the last empty space) and the null terminator.
#      Author: Sean Loesch

	.data
prompt:    .asciiz  "Enter the string you want to reverse: (max 25 characters): "
my_string: .space   27   # Accepts strings up to 25 chars long (including '\n' & '\0')

	.text
# Register usage:
#  $t0 - stores index of current char in my_string when finding end
#  $s0 - iterated index for finding end of my_string, becomes down-counting index
#        for string reversal
#  $s1 - up-counting index for string reversal
#  $s2 - stores temp value for replaced char at s1-th index
#  $s3 - stores temp value for replaced char at s0-th index
######################################################################
main:                                                                #
    # Print prompt                                                   #
    li    $v0,    4                                                  #
    la    $a0,    prompt                                             #
    syscall                                                          #
                                                                     #
    # Read input from user                                           #
    li    $v0,    8                                                  #
    la    $a0,    my_string                                          #
    li    $a1,    27   # Max number of chars (inlcuding '\0' & '\n') #
    syscall                                                          #
                                                                     #
                                                                     #
# Iterate through string until null terminator is reached            #
find_end:                                                            #
    # Load current character                                         #
    lb    $t0,    my_string($s0)                                     #
                                                                     #
    # If reached the null terminator, now ready to reverse           #
    beq   $zero,  $t0,    reverse_string                             #
                                                                     #
    # Else, Increment $s0                                            #
    add   $s0,    $s0,    1                                          #
                                                                     #
    # Iterate again                                                  #
    j     find_end                                                   #
                                                                     #
                                                                     #
# Reverse string using $s0 (counts down) and $s1 (counts up)         #
reverse_string:                                                      #
    # Decrement $s0 (now represents index of last char in my_string) #
    sub   $s0,    $s0,    1                                          #
                                                                     #
    # Reverse my_string using $s2 and $s3 to store temp values       #
    loop:                                                            #
    # If $s1 and $s0 store the same index or passed one another,     #
    # print my_string and exit                                       #
    ble   $s0,    $s1,    print_and_exit                             #
                                                                     #
    # Store character at $s1-th index in $s2                         #
    lb    $s2,    my_string($s1)                                     #
                                                                     #
    # Copy character at $s0-th index to $s1-th index                 #
    lb    $s3,    my_string($s0)                                     #
    sb    $s3,    my_string($s1)                                     #
                                                                     #
    # Move temporarily stored char in $s2 to $s0-th index            #
    sb    $s2,    my_string($s0)                                     #
                                                                     #
    # Increment $s1 and decrement $s0                                #
    add   $s1,    $s1,    1                                          #
    sub   $s0,    $s0,    1                                          #
                                                                     #
    # Move on to next char pair                                      #
    j     loop                                                       #
                                                                     #
                                                                     #
print_and_exit:                                                      #
    # Print my_string                                                #
    li    $v0,    4                                                  #
    la    $a0,    my_string                                          #
    syscall                                                          #
                                                                     #
    # End program                                                    #
    li    $v0,    10                                                 #
    syscall                                                          #
######################################################################
