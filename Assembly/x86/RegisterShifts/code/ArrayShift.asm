; ------------------------------------------------------------------------------
; File: ArrayShift.asm
; Author: Sean Loesch
; Date: 2022-11-1
; Brief: This program demonstrates knowledge of basic loops and MOV operations
;        in assembly. Originally written on an x86 emulator
;        (https://carlosrafaelgn.com.br/Asm86/).
; 
; Description:
; This program rotates the members of a 32-bit byte array forward one position
; (e.g., from {1, 2, 3, 4, 5} to {5, 1, 2, 3, 4}), where the value originally at
; the end of the array is wraps to the first position of the array.
;
; Directions for Use:
; First, open the link for the emulator provided in the brief and copy/paste
; this program into it.
; 
; Please note that there is no .data section for this program, as it was built
; and ran on an emulator. You must create the variables manually before the
; program can run. To do this, select "Windows", then select the "Variables"
; window. To create a single variable, begin by selecting "Add..." and provide
; the variable name. Then, simply select "1 byte", "2 bytes", or "4 bytes", and
; provide the initial value. To create an array, select "Array (length)", and
; select the desired number of bytes.
;
; Now that you know how to create variables, create the following:
; 
; arr1:   5 byte array {0, 0, 0, 0, 0}
; arr2:   5 byte array {0, 0, 0, 0, 0}
; index1: 1 byte var (0)
; index2: 1 byte var (0)
;
; then press "Compile". If all is well, you will see the prompt "Compiled
; successfully!" appear on the bottom of the screen. Now you are free to run the
; program.
; 
; To view the results, open the "Registers" window, then step through the
; program. On lines 80-84, watch AL (the lower 8 bits/2 bytes of EAX), and
; you'll see that arr2 contains the same values as arr1, but shifted one byte
; with the last element, 55h, wrapped around to the first position.
; ------------------------------------------------------------------------------
; store [11h, 22h, 33h, 44h, 55h] in arr1
MOV AL, 11h
MOV [arr1], AL
MOV AL, 22h
MOV [arr1+1], AL
MOV AL, 33h
MOV [arr1+2], AL
MOV AL, 44h
MOV [arr1+3], AL
MOV AL, 55h
MOV [arr1+4], AL

; store last value of arr1 (55h) in EDX
MOV EDX, [arr1+4]

; number of loop iterations required
MOV ECX, 4
; index2 is offset from index1 by 1 (shifting arr1 1 byte to the right)
MOV index2, 1

; loop over arr1 to insert shifted bytes into arr2
l1:
	; store byte at current index of arr1 in AL
	MOV BL, index1
	MOV AL, [arr1+BL]
	
	; store value currently in AL (byte at current index of arr1) 
	; in the subsequent relative index of arr2
	MOV BL, index2
	MOV [arr2+BL], AL
	
	INC index1
	INC index2
loop l1

; insert last byte of arr1 into first byte of arr2
; arr2 should now be [55h, 11h, 22h, 33h, 44h]
MOV [arr2], DL

; check result
MOV AL, [arr2]
MOV AL, [arr2+1]
MOV AL, [arr2+2]
MOV AL, [arr2+3]
MOV AL, [arr2+4]