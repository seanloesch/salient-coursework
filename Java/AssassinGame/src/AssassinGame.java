import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * Sean Loesch
 * 4/12/2022
 * Assignment 4
 * The purpose of this class is to create a game of assassin where the user specifies a player in the "kill ring" (starting
 * with all the players in the game) that is killed, where they then get moved to the graveyard. Last person standing wins.
 */
public class AssassinGame {
	// This class has two fields, each CircLinkedStrLists, one for the kill ring and one for the graveyard.
	CircLinkedStrList killRing = new CircLinkedStrList();
	CircLinkedStrList graveyard = new CircLinkedStrList();
	
	
	
	// Implementation of nested Node class
	private class Node {
		String data;
		Node next;
		
		Node(String dataIn) {
			data = dataIn;
		}
		
	}
	
	
	
	
	
	// Implementation of nested CircLinkedStrList class
	private class CircLinkedStrList {
		Node head;
		
		// Adds node including specified string to list
		public void add(String dataIn) {
			
			Node newNode = new Node(dataIn);
			
			// If linked list is empty, make this node the head
			if (head == null) {
				head = newNode;
			} else {
				
				// Traverse the linked list once
				Node currNode = head;
				while (currNode.next != null && currNode.next != head) {
					currNode = currNode.next;
				}
					
				// Insert Node at end of list and make last node's next the head of the list,
				// making the list circular
				currNode.next = newNode;
				newNode.next = head;
				
			}
			
		}
		
		// Removes node including specified string from list. If no names match, nothing happens
		public void remove(String dataIn) {
			
			// This remove method requires checking the head node first
			if (head.data.equals(dataIn)) {
				head = head.next;
			}
			
			// Traverse list once (until last node)
			Node currNode = head;
			while (currNode.next != head) {
				// If input string matches the data value of the next node, remove that node
				if (currNode.next.data.equals(dataIn)) {
					currNode.next = currNode.next.next;
					
				}
				
				currNode = currNode.next;
			}
			
		}
		
		// Returns boolean value representing whether the input string is a member in the list
		public boolean contains(String dataIn) {
			
			// Traverse list once (until last node)
			Node currNode = head;
			while (currNode.next != head) {
				
				//If input string matches the data value of the next node, return true
				if (currNode.data.equals(dataIn)) {
					return true;
					
				}
				
				currNode = currNode.next;
			}
			
			// If the last element matches, return true. Otherwise return false
			return currNode.data.equals(dataIn);
		}
		
		// Returns the number of elements in the list
		public int size() {
			
			int count = 1;
			
			// Traverse the linked list once
			Node currNode = head;
			while (currNode.next != null && currNode.next != head) {
				
				// Update count and move to next node
				count++;
				currNode = currNode.next;
			}
			
			return count;
		}
		
		// Returns the element at head of list
		public String getFirst() {
			
			return head.data;
		}
		
		// toString method for this class
		public String toString() {
			
			// If list is empty, return empty brackets
			if (head == null) {
				
				return "{  }";
			}
			
			String listStr = "{ ";
			
			// Traverse linked list while adding each name to listStr
			Node currNode = head;
			while (currNode.next != null && currNode.next != head) {
				listStr += currNode.data + ", ";
				currNode = currNode.next;
			}
			
			listStr += currNode.data + " }";
			
			return listStr;
		}
		
	}
	
	
	
	
	
	
	// Inserts names from a space, tab, or escape character-delimited
	// text file into an ArrayList where it is sorted and then converted
	// into a linked list
	AssassinGame(File names) throws FileNotFoundException {
		Scanner reader = new Scanner(names);
		// ArrayList used only to shuffle names
		ArrayList<String> krList = new ArrayList<String>();
		
		while (reader.hasNext()) {
			krList.add(reader.next());
		}
		reader.close();
		
		// Shuffle names
		Collections.shuffle(krList);
		
		// Turn shuffled names into linked list
		for (int i = 0; i < krList.size(); i++) {
			killRing.add(krList.get(i));
		}
		
	}
	
	// Runs the "Assassin" Game.
	public void run() {
		Scanner input = new Scanner(System.in);
		String currName = "";
		
		// Print how to play instructions to user
		System.out.println("Welcome to the Assassin Game! Enter a player's name when a player is killed "
				+ "to move them to the graveyard.\nNote that the player's name must be spelled correctly and is case sensitive. "
				+ "When one player is left,\nthe winner will be announced automatically.\n");
		
		System.out.println("Starting Kill Ring Members:");
		System.out.println(killRing);
		System.out.println("Starting Graveyard:");
		System.out.println(graveyard);
		
		// Start the game
		do {
			// Prompt user to input killed player's name
			System.out.println("Please enter the name of the player that has been killed: ");
			
			// User input
			currName = input.next();
			
			// If the user input a valid name, kill that player and move them to the graveyard.
			// If name is not valid, print a message and let them try again
			if (killRing.contains(currName)) {
				System.out.println();
				
				// Kill named player and move to graveyard. Print details
				this.kill(currName);
				System.out.println("\n\"" + currName + "\" HAS BEEN KILLED!\n\nRemaining Players in Kill Ring:");
				System.out.println(killRing + "\n");
				System.out.println("Current Players in Graveyard:");
				System.out.println(graveyard + "\n");
				
			} else {
				System.out.println("\nInvalid name. Please try again.");
			}
			
		} while (killRing.size() > 1);
		 
		// Now that there is only one player left, end the game and announce the winner
		
		System.out.println("Game over. " + killRing.getFirst() + " is the last one standing and wins the game!");
		
	}
	
	// "Kills" player by removing name from killRing linked list
	// and inserting it into the graveyard linked list
	public void kill(String name) {
		
		killRing.remove(name);
		graveyard.add(name);
		
	}
	
}