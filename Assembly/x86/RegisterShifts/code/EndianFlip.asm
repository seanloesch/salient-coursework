; ------------------------------------------------------------------------------
; File: EndianFlip.asm
; Author: Sean Loesch
; Date: 2022-11-1
; Brief: This program demonstrates knowledge of basic loops, MOV operations, and
;        endianness in assembly. Originally written on an x86 emulator
;        (https://carlosrafaelgn.com.br/Asm86/).
; 
; Description:
; This program takes a 32-bit number represented by the bytes 12h, 34h, 56h, and
; 78h in big-endian format and rearranges them to produce the same four bytes in 
; little-endian format (78h, 56h, 34h, 12h).
;
; Directions for Use:
; First, open the link for the emulator provided in the brief and copy/paste
; this program into it.
; 
; Please note that there is no .data section for this program, as it was built
; and ran on an emulator. You must create the variables manually before the
; program can run. To do this, select "Windows", then select the "Variables"
; window. To create an array, begin by selecting "Add..." and provide
; the array name. Then, select "Array (length)", and select the desired number
; of bytes. 
;
; Now that you know how to create arrays, create the following:
; 
; bigEndian:    4 byte array {0, 0, 0, 0}
; littleEndian: 4 byte array {0, 0, 0, 0}
;
; then press "Compile". If all is well, you will see the prompt "Compiled
; successfully!" appear on the bottom of the screen. Now you are free to run the
; program.
; 
; To view the results, open the "Registers" window, run the program, and check
; EBX. You will see that littleEndian {78h, 56h, 34h, 12h} is indeed the
; little-endian representation of the elements in bigEndian
; {12h, 34h, 56h, 78h}.
; ------------------------------------------------------------------------------
; store [12h, 34h, 56h, 78h] in bigEndian
MOV AL, 12h
MOV [bigEndian], AL
MOV AL, 34h
MOV [bigEndian+1], AL
MOV AL, 56h
MOV [bigEndian+2], AL
MOV AL, 78h
MOV [bigEndian+3], AL

; number of loop iterations required
MOV ECX, 4
; store starting index of bigEndian (we want to go backwards, so start at end)
MOV BL, 3

; iterates through bigEndian backwards and stores result in EAX
l1:
	; store current index of bigEndian in EAX 
	MOV EAX, [bigEndian+BL]
	
	DEC BL
loop l1

; result now contained in EAX - move into littleEndian
MOV littleEndian, EAX

; check result
MOV EBX, littleEndian