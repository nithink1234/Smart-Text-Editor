package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary {

	private LinkedList<String> dict;
	// TODO: Add a constructor
	int count;

	public DictionaryLL() {
		 dict = new LinkedList<String>();
		count =0;
	}

	/**
	 * Add this word to the dictionary. Convert it to lowercase first for the
	 * assignment requirements.
	 * 
	 * @param word
	 *            The word to add
	 * @return true if the word was added to the dictionary (it wasn't already
	 *         there).
	 */
	public boolean addWord(String word) {
		// TODO: Implement this method
	/*	for (int i = 0; i < count; i++) {
			if (word.equals(dict.get(i))) {
				return true;
			}
		}*/
		
		if(dict.contains(word.toLowerCase())){
			return true;
		}
		
		else if (!(word.equals(null))) {	
			//System.out.println(word.toLowerCase());
			dict.add(word.toLowerCase());
			// count++;
			//System.out.println(count);
			return true;
		}
		return false;
	}

	/** Return the number of words in the dictionary */
	public int size() {
		// TODO: Implement this method
		return dict.size();
	}

	/** Is this a word according to this dictionary? */
	public boolean isWord(String s) {
		// TODO: Implement this method
	/*	for (int i = 0; i < count; i++) {
			if (s.equals(dict.get(i))) {
				return true;
			}
		}*/
		
		if(dict.contains(s.toLowerCase())){
			return true;
		}
			return false;
	}
}
