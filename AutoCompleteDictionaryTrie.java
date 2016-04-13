package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 * ADT
 * 
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	private TrieNode root;
	public TrieNode next;
	public TrieNode current;
	private int size = 0;
	private String firstele;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
	}

	/**
	 * Insert a word into the trie. For the basic part of the assignment (part
	 * 2), you should ignore the word's case. That is, you should convert the
	 * string to all lower case as you insert it.
	 */
	public boolean addWord(String word) {
		char[] addtthisword = word.toLowerCase().toCharArray();
		current = null;
		next = null;
		current = root;
		if (!(word.equals(null))) {
			for (int i = 0; i < addtthisword.length; i++) {
				next = current.insert(addtthisword[i]);
				// If Char is already present in the node
				if (next == null) {
					next = current.getChild(addtthisword[i]);
				}
				current = next;
			}
			// Check if word is already present
			if (current.endsWord() && word.toLowerCase().equals(current.getText())) {
				// System.out.println("this happens");
				return false;
			} else
				current.setEndsWord(true);
			size++;
			// System.out.println(current.getText());
			// printTree();
			// System.out.println(size);
			// System.out.println(" ");
			return true;
		}
		// TODO: Implement this method.

		return false;
	}

	/**
	 * Return the number of words in the dictionary. This is NOT necessarily the
	 * same as the number of TrieNodes in the trie.
	 */
	public int size() {
		// TODO: Implement this method
		return size;
	}

	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) {
		// TODO: Implement this method
		char[] checkthisword = s.toLowerCase().toCharArray();
		current = root;
		if ((size > 0) && current != null) {
			for (int i = 0; i < checkthisword.length; i++) {
				if (current.getChild(checkthisword[i]) != null) {
					current = current.getChild(checkthisword[i]);
					// System.out.println(current.getText()+ " " +
					// current.endsWord());
					if ((current.getText().equals(s.toLowerCase()) && (current.endsWord()))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * * Returns up to the n "best" predictions, including the word itself, in
	 * terms of length If this string is not in the trie, it returns null.
	 * 
	 * @param text
	 *            The text to use at the word stem
	 * @param n
	 *            The maximum number of predictions desired.
	 * @return A list containing the up to n best predictions
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		// TODO: Implement this method
		// This method should implement the following algorithm:
		// 1. Find the stem in the trie. If the stem does not appear in the
		// trie, return an
		// empty list
		int count = 0;
		List<String> completetion = new LinkedList<String>();
		Queue<TrieNode> checkq = new LinkedList<TrieNode>();
		TrieNode headofq = null;
		current = null;
		// System.out.println(root.getValidNextCharacters());
		if (numCompletions > 0) {
			char[] checkthisword = prefix.toLowerCase().toCharArray();
			current = root;
			if (prefix.length() > 0) {
				inner: for (int i = 0; i < checkthisword[i]; i++) {
					if (current.getChild(checkthisword[i]) != null) {
						current = current.getChild(checkthisword[i]);
						if (current.getText().equals(prefix.toLowerCase())) {
							break inner;
						}
					} else {
						return completetion;
					}
				}
			}
			checkq.add(current);
			/*
			 * System.out.println(checkq.size()); System.out.println(count);
			 */
			while (checkq.size() > 0 && count != numCompletions) {
				headofq = checkq.remove();
				// System.out.println(headofq.endsWord());
				if (headofq.endsWord()) {
					completetion.add(headofq.getText());
					count++;
				}
				for (Character c : headofq.getValidNextCharacters()) {
					// System.out.println(headofq.getChild(c));
					if (headofq.getChild(c) != null) {
						checkq.add(headofq.getChild(c));

					}
				}
			}

		}
		// 2. Once the stem is found, perform a breadth first search to generate
		// completions
		// using the following algorithm:
		// Create a queue (LinkedList) and add the node that completes the stem
		// to the back
		// of the list.
		// Create a list of completions to return (initially empty)
		// While the queue is not empty and you don't have enough completions:
		// remove the first Node from the queue
		// If it is a word, add it to the completions list
		// Add all of its child nodes to the back of the queue
		// Return the list of completions

		return completetion;
	}

	// For debugging
	public void printTree() {
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr) {
		if (curr == null) {
			System.out.println(" ");
			return;
		}
		System.out.println(curr.getText());
		// System.out.println(curr.endsWord());
		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}

}