# linked_list.s
# Date: April 25, 2024
# Description: A MIPS assembly program that allows for the insertion, deletion, and printing into and from a linked list.
# The deletion function does not delete duplicates. Note that the MARS simulator does not allow for manual memory
# deallocation back to the heap, making memory leaks unavoidable upon node deletion.
#      Author: Sean Loesch

	.data
# Prompt messages
function_select:    .asciiz "\nPlease designate your desired action:\n\ti: Insert new integer\n\td: Delete value\n\tp: Traverse and print list\n\tq: Quit\n"
invalid_command:    .asciiz "Invalid command. Please try again:\n"
insert_prompt:      .asciiz "\nPlease enter the integer you would like to insert: "
delete_prompt:      .asciiz "\nPlease enter the value you would like to delete: "

# Report messages
insert_complete:    .asciiz "Value inserted."
print_complete:     .asciiz "\nThe list has been printed."
delete_success_msg: .asciiz "Value deleted."
delete_fail_msg:    .asciiz "Deletion failed: Value is not present."

# Space and newline characters, for readability
space:              .asciiz " "
newline:            .asciiz "\n"

# Statically allocated pointer variable to locate head of LL
head_ptr:           .word 0

	.text
# Register usage:
#  $t0-$t2 - Pointer manipulation, value retrieval
#  $s0 - Storing user input
##################################################################################################
# Code to call the insertion, deletion, and traversal functions
main:
    # Print initial prompt
    li    $v0,    4
    la    $a0,    function_select
    syscall

#######################################    
get_input:
    # Read character from user    
    li    $v0,    12
    syscall
    
    # Jump to desired function
    beq   $v0,    'i',    insert_node
    beq   $v0,    'd',    delete_node
    beq   $v0,    'p',    print_list
    beq   $v0,    'q',    end_program
    
    # Prompt user again if input is invalid
    li    $v0,    4
    la    $a0,    function_select
    syscall
    j     get_input

#######################################
# End the program
end_program:
    li    $v0,    10
    syscall
    
##################################################################################################
# Reads input from user and appends it to the end of the LL
insert_node:
    # Print initial prompt
    li    $v0,    4
    la    $a0,    insert_prompt
    syscall
    
    # Read integer and store in $s0
    li    $v0,    5
    syscall
    move  $s0,    $v0           # Move user input to $s0
    
    # If no head, jump to no_head function. Else jump to std_insert
    lw    $t0,    head_ptr      # Copy head_ptr's value to $t0
    bnez  $t0,    std_insert    # If head does not exist, jump to std_insert
    
#######################################
# If there is no head, insert node in heap space and store address of integer in head_ptr
no_head:
    la    $a0,    8             # Load node size to allocate
    li    $v0,    9             # Prepare heap space allocation (sbrk)
    syscall
    sw    $v0,    head_ptr      # Store address of allocated memory in head_ptr
    
    # Store value in new node
    sw    $s0,    0($v0)        # Store value in first word of new node
    
    j     insert_message        # Print confirmation
    
#######################################
# Inserts a node at the end of the LL
std_insert:
    addi  $t0,    $t0,    4     # Add 4 to value, $t0 now holds address of first node's pointer
    
insert_traverse:
    lw    $t1,    0($t0)        # Grab value in node's pointer
    beqz  $t1,    insert_func   # Jump to insert_func if current node has null pointer
    
    # Else, increment current pointer by 4 (to look at next node's pointer)
    addi  $t1,    $t1,    4     # Add 4 to value, $t0 now holds address of next node's pointer
    move  $t0,    $t1           # Update $t0 for next iteration
    
    j     insert_traverse       # End loop
    
#######################################
# Insert new node
insert_func:
    # Insert new node and move its address (will be at $v0) into 0($t1) (the address of $t1)
    la    $a0,    8         # Load node size to allocate
    li    $v0,    9         # Prepare heap space allocation (sbrk)
    syscall
    sw    $v0,    0($t0)    # Store address of allocated memory in prev. node's pointer
                            # (address of prev. node's ptr stored at address in $t0)
    
    # Store value in new node
    sw    $s0,    0($v0)    # Store value in first word of new node

#######################################    
# Report insertion and return to main
insert_message:
    li    $v0,    4
    la    $a0,    insert_complete
    syscall
    
    j     main    # Return to main
    
##################################################################################################
# Traverses the LL and prints each node
print_list:
    # Print newline character (for readability)
    li    $v0,    4
    la    $a0,    newline
    syscall
    
    # If head exists, carry on with printing. Else jump to print_message
    lw    $t0,    head_ptr         # Load value of head_ptr into $t0
    beqz  $t0,    print_message    # If head does not exist, jump to print_message
    
    # Else, print value
    move  $t2,    $t0          # Duplicate value's address for pasting
    lw    $t2,    0($t2)       # $t2 now holds first node's value
    li    $v0,    1
    move  $a0,    $t2
    syscall                    # Print value
    li    $v0,    4
    la    $a0,    space
    syscall                    # Print space
    
    addi  $t0,    $t0,    4    # Add 4 to value, $t0 now holds address of first node's pointer
    
#######################################
print_traverse:
    lw    $t1,    0($t0)           # Grab value in node's pointer and put in $t1
    beqz  $t1,    print_message    # Jump to print_message if current node has null pointer
    
    # Else, print value
    move  $t2,    $t1          # Duplicate value address for pasting value stored here
    lw    $t2,    0($t2)       # $t2 now holds value first node's value
    li    $v0,    1
    move  $a0,    $t2
    syscall                    # Print value
    li    $v0,    4
    la    $a0,    space
    syscall                    # Print space
    
    # Increment current pointer by 4 (to look at next node's pointer)
    addi  $t1,    $t1,    4    # Add 4 to value, $t0 now holds address of next node's pointer
    move  $t0,    $t1          # Update $t0 for next iteration
    
    j     print_traverse       # Loop until condition is met
    
#######################################
# Report completion and return to main
print_message:
    li    $v0,    4
    la    $a0,    print_complete
    syscall
    
    j     main    # Return to main
    
##################################################################################################
# Reads input from user, deletes desired value. Changes appropriate pointers upon
# deletion. Does not delete duplicates
delete_node:
    # Print initial prompt
    li    $v0,    4
    la    $a0,    delete_prompt
    syscall
    
    # Read integer and store in $s0
    li    $v0,    5
    syscall
    move  $s0,    $v0          # Move user input to $s0
    
    # If head exists, carry on with deletion. Else jump to deletion_failure
    lw    $t0,    head_ptr            # Copy head_ptr's value to $t0
    beqz  $t0,    deletion_failure    # If head doesn't exist, jump to deletion_failure
    la    $t2,    head_ptr            # Load address of head_ptr into $t2 (keeps address record)
    
#######################################
# Traverse LL and delete node if possible
delete_traverse:
    lw    $t1,    0($t0)              # Access node's value, store in $t1
    beq   $t1,    $s0,    delete_func # Check if value matches user input
    
    addi  $t0,    $t0,    4           # If no match, add 4 to address value, $t0 now
                                      # holds address of current node's pointer
                                      
    lw    $t1,    0($t0)              # Access pointer's value, store in $t1
    beqz  $t1,    deletion_failure    # If end of LL has been reached with no 
                                      # match (null pointer), go to deletion_failure
    
    # Else, move to next node
    move  $t2,    $t0          # Keep record of current node's pointer address
    lw    $t0,    0($t0)       # Else, move to address of next value
    
    j     delete_traverse # End loop
    
#######################################
# Performs the node deletion
delete_func:
    # If matching node's pointer is null (end of LL), change previous node's pointer to null
    addi  $t0,    $t0,    4    # Add 4 to address value, $t0 now holds address of current node's pointer
    lw    $t1,    0($t0)       # Access pointer's value, store in $t1
    bnez  $t1,    skip         # Skip next part if match is not at end of LL
    
    # Else (matching node is not null), change previous node's pointer to current node's stored address
    skip:
    lw    $t0,    0($t0)       # Store address in current node's pointer in $t0 
    sw    $t0,    0($t2)       # Store address in current node's pointer in previous pointer
    
    # Print success message
    li    $v0,    4
    la    $a0,    delete_success_msg
    syscall
    
    j     main    # Return to main
    
#######################################
# Report failed completion and return to main
deletion_failure:
    li    $v0,    4
    la    $a0,    delete_fail_msg
    syscall
    
    j     main    # Return to main
    