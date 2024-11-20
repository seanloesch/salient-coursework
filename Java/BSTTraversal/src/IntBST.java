/*
 * Sean Loesch
 * Assignment 5
 * 4/26/2022
 * The purpose of this class is to create a binary search tree of integers that writes its traversals to files.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class IntBST {
	
	// Establishing named root node
	Node root;
	
	// Constructor sets the root of the BST to null
	IntBST(){
		root = null;
	}
	
	// Implementation of the nested Node class
	class Node {
		int key;
		Node left;
		Node right;
		
		// Constructor
		Node(int numIn){

			key = numIn;
		}
	}
	
	
	
	// Calls insertRec
	public void insert(int numIn) {
		root = insertRec(root, numIn);
	}
	
	// Recursively inserts a key in the BST
	public Node insertRec(Node root, int numIn) {
		
		// If the BST is empty, make the root the specified value and return the new root
		// (must have return value for method to work)
		if (root == null) {
			
			root = new Node(numIn);
			return root;
		} else {
			
			// Else, traverse down the tree 
			if (numIn <= root.key) {
				root.left = insertRec(root.left, numIn);
			}
			else if (numIn > root.key) {
				root.right = insertRec(root.right, numIn);
			}
			
			// Return the root (must have return value for method to work)
			return root;
		}
		
	}
	
	// Calls preorderRec. Traversal results will be written to preorderTrials.txt
	public void preorder(FileWriter wr) throws IOException {
		preorderRec(root, wr);
	}
		
	// Does a recursive preorder traversal of the BST and writes every value to a text file
	public void preorderRec(Node root, FileWriter wr) throws IOException {
			
		if (root != null) {
			// wr.write(root.key + "\n");
			preorderRec(root.left, wr);
			preorderRec(root.right, wr);
		}
	}
	
	// Calls inorderRec. Traversal results will be written to inorderTrials.txt
	public void inorder(FileWriter wr) throws IOException {
		inorderRec(root, wr);
	}
	
	// Does a recursive inorder traversal of the BST and writes every value to a text file
	public void inorderRec(Node root, FileWriter wr) throws IOException {
		
		if (root != null) {
			inorderRec(root.left, wr);
			// wr.write(root.key + "\n");
			inorderRec(root.right, wr);
		}
	}
	
	// Calls postorderRec. Traversal results will be written to postorderTrials.txt
	public void postorder(FileWriter wr) throws IOException {
		postorderRec(root, wr);
	}
	
	// Does a recursive postorder traversal of the BST and writes every value to a text file
	public void postorderRec(Node root, FileWriter wr) throws IOException {
		
		if (root != null) {
			postorderRec(root.left, wr);
			postorderRec(root.right, wr);
			// wr.write(root.key + "\n");
		}
	}
	
	// Does a levelorder traversal of the BST and writes every value to a text file. Traversal results will be written to levelorderTrials.txt
	public void levelorder(FileWriter wr) throws IOException {
		
		// Breadth-first traversal starts with the root. Use of a queue is necessary
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {

			// Print the contents of all of the nodes on the current level of height (starting with the root and going left to right at every level)
			Node currNode = queue.poll();
			// wr.write(currNode.key + "\n");
			if (currNode.left != null) {
				queue.add(currNode.left);
			}
			if (currNode.right != null) {
				queue.add(currNode.right);
			}
		}
		
	}
	
}
