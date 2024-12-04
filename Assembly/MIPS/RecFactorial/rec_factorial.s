# rec_factorial.s
# Date: February 6, 2024
# Description: Calculates the factorial of an input integer recursively. Terminates early
# if input integer is less than 0. Note that it can only perform factorial calculation
# up to 12!, as anything above that causes overflow for the 32-bit integer limit.
#      Author: Sean Loesch

	.data
prompt:             .asciiz    "Enter your desired integer (must be >= 0): "
result_msg:         .asciiz    "The factorial of your number is "
invalid_int_prompt: .asciiz    "The integer must be >= 0, please try again. Terminating..."
    
	.text
# Register usage:
#  $a0 - Stores decremented values
#  $t0 - Stores copy of int to be multiplied
#  $s0 - Stores result to be printed
#######################################################################################
main:                                                                                 #
    # Print prompt                                                                    #
    li    $v0,    4                                                                   #
    la    $a0,    prompt                                                              #
    syscall                                                                           #
                                                                                      #
    # Read int from user                                                              #
    li    $v0,    5                                                                   #
    syscall                                                                           #
                                                                                      #
    # If int < 0, terminate program early                                             #
    blt   $v0,    0,    terminate_early                                               #
                                                                                      #
    # Store value in $a0 and call rec_factorial                                       #
    move  $a0,    $v0                                                                 #
    jal   rec_factorial         # Store location of next instruction (line 37) in $ra #
                                                                                      #
    # Store answer after rec_factorial completes                                      #
    move  $s0,    $v0                                                                 #
                                                                                      #
    # Print result                                                                    #
    li    $v0,    4                                                                   #
    la    $a0,    result_msg                                                          #
    syscall                                                                           #
    li    $v0,    1                                                                   #
    move  $a0,    $s0                                                                 #
    syscall                                                                           #
                                                                                      #
    end_program:                                                                      #
    # End program                                                                     #
    li    $v0,    10                                                                  #
    syscall                                                                           #
                                                                                      #
#######################################################################################
# Calculate factorial recursively                                                     #
rec_factorial:                                                                        #
    # Store local variables and return address to the stack                           #
    # Moving stack pointer 8 bytes down to be able to store 2 values                  #
    sub   $sp,    $sp,    8                                                           #
    sw    $ra,    0($sp)        # Store return address to stack (1st pos.)            #
    sw    $t0,    4($sp)        # Store value of local variable to stack (2nd pos.)   #
                                                                                      #
    # Base cases                                                                      #
    li    $v0,    1       # Prepare to return 1 if either base case is hit immediately#
    beq   $a0,    0,      end_recursion                                               #
    beq   $a0,    1,      end_recursion                                               #
                                                                                      #
    # Recursive case -> rec_factorial(int - 1)                                        #
    move  $t0,    $a0           # Move argument into $t0                              #
    sub   $a0,    $a0,    1     # Decrement value to store for future multiplication  #
                                                                                      #
    # Recursive call. Store location of the next instruction (line 74) in $ra         #
    jal   rec_factorial                                                               #
                                                                                      #
    # Where actual recursive multiplication happens                                   #
    mul   $v0,    $t0,    $v0   # Not activated until base case is hit                #
                                                                                      #
end_recursion:                                                                        #
    lw    $ra,    0($sp)        # Retrieve the value of the return address            #
    lw    $t0,    4($sp)        # Retrieve the value of the multiplied number         #
    add   $sp,    $sp,    8     # Restoring stack pointer 8 bytes upward              #
                                                                                      #
    jr    $ra                   # Jump to line 74 another time (continue multiplying) #
                                                                                      #
#######################################################################################
terminate_early:                                                                      #
    # Print invalid_int_prompt and end program                                        #
    li    $v0     4                                                                   #
    la    $a0,    invalid_int_prompt                                                  #
    syscall                                                                           #
                                                                                      #
    j     end_program                                                                 #
#######################################################################################
