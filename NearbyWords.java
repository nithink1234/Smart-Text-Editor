/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 1000;

	static Dictionary dict;

	public NearbyWords(Dictionary dict) {
		this.dict = dict;
	}

	/**
	 * Return the list of Strings that are one modification away from the input
	 * string.
	 * 
	 * @param s
	 *            The original String
	 * @param wordsOnly
	 *            controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly) {
		List<String> retList = new ArrayList<String>();
		insertions(s, retList, wordsOnly);
		subsitution(s, retList, wordsOnly);
		deletions(s, retList, wordsOnly);
		return retList;
	}

	/**
	 * Add to the currentList Strings that are one character mutation away from
	 * the input string.
	 * 
	 * @param s
	 *            The original String
	 * @param currentList
	 *            is the list of words to append modified words
	 * @param wordsOnly
	 *            controls whether to return only words or any String
	 * @return
	 */
	public void subsitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for (int index = 0; index < s.length(); index++) {
			for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char) charCode);
				// System.out.println(sb.toString());
				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if (!currentList.contains(sb.toString()) && (!wordsOnly || dict.isWord(sb.toString()))
						&& !s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}

	/**
	 * Add to the currentList Strings that are one character insertion away from
	 * the input string.
	 * 
	 * @param s
	 *            The original String
	 * @param currentList
	 *            is the list of words to append modified words
	 * @param wordsOnly
	 *            controls whether to return only words or any String
	 * @return
	 */
	public static void insertions(String s, List<String> currentList, boolean wordsOnly) {
		// TODO: Implement this method

		for (int index = 0; index <= s.length(); index++) {
			for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++) {

				StringBuffer sbi = new StringBuffer(s);
				sbi.insert(index, (char) charCode);
				// System.out.println(sbi.toString());
				if (!currentList.contains(sbi.toString()) && (!wordsOnly || dict.isWord(sbi.toString()))
						&& !s.equals(sbi.toString())) {
					currentList.add(sbi.toString());
				}
			}
		}
	}

	/**
	 * Add to the currentList Strings that are one character deletion away from
	 * the input string.
	 * 
	 * @param s
	 *            The original String
	 * @param currentList
	 *            is the list of words to append modified words
	 * @param wordsOnly
	 *            controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly) {
		// TODO: Implement this method
		for (int index = 0; index < s.length(); index++) {
			StringBuffer sbd = new StringBuffer(s);
			sbd.deleteCharAt(index);
			// System.out.println(sbd.toString());
			if (!currentList.contains(sbd.toString()) && (!wordsOnly || dict.isWord(sbd.toString()))
					&& !s.equals(sbd.toString())) {
				currentList.add(sbd.toString());
			}
		}

	}

	/**
	 * Add to the currentList Strings that are one character deletion away from
	 * the input string.
	 * 
	 * @param word
	 *            The misspelled word
	 * @param numSuggestions
	 *            is the maximum number of suggestions to return
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		// initial variables
		Queue<String> queue = new LinkedList<String>(); // String to explore
		HashSet<String> visited = new HashSet<String>(); // to avoid exploring
															// the same
															// string multiple
															// times
		List<String> retList = new LinkedList<String>(); // words to return
		List<String> temp = new LinkedList<String>();
		// insert first node
		queue.add(word);
		visited.add(word);

		int comp = 1000;
		// TODO: Implement the remainder of this method, see assignment for
		// algorithm
		while ((retList.size() < numSuggestions) || (comp == 0)) {
			try {
				String currentword = queue.remove();

				insertions(currentword, temp, true);

				subsitution(currentword, temp, true);

				deletions(currentword, temp, true);

				System.out.println(temp.size());
				System.out.println(temp);
				for (int i = 0; i < temp.size(); i++) {
					System.out.println(temp.get(i));
					if ((!(visited.contains(temp.get(i)))) && retList.size() < numSuggestions) {

						queue.add(temp.get(i));
						visited.add(temp.get(i));

						if (dict.isWord(temp.get(i))) {
							retList.add(temp.get(i));
						}
					}
				}

				System.out.println("The q is" + queue);
				System.out.println("The retList is" + retList);
				System.out.println("The visited is" + visited);

				temp.clear();
				comp--;

			} catch (NoSuchElementException e) {
				return retList;
			}
		}

		return retList;

	}

	public static void main(String[] args) {

		/*
		 * String s = "play"; boolean wordsonly = true; List<String> currentList
		 * = new LinkedList<String>(); //currentList.add(s);
		 * insertions(s,currentList,wordsonly);
		 */

		// basic testing code to get started
		String word = "play"; // Pass
		// NearbyWords any Dictionary implementation you prefer
		Dictionary d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");
		NearbyWords w = new NearbyWords(d);
		List<String> l = w.distanceOne(word, true);
		System.out.println("One away word Strings for for \"" + word + "\" are:");
		System.out.println(l + "\n");

		word = "kangaro";
		List<String> suggest = w.suggestions(word, 10);
		System.out.println("Spelling Suggestions for \"" + word + "\" are:");
		System.out.println(suggest);

	}

}
