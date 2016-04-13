package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(
			String sourceText) {
		
		List<String> numWords = getTokens("[a-zA-Z.!?,:;+-*/']+", sourceText);
		if (numWords.size()!=0){
		ListNode haha = null;
		starter = numWords.get(0);
		String currentword = null;
		String currentnode = null;
		String nextword = null;
	

		haha = new ListNode(starter);
		wordList.add(haha);
		haha.addNextWord(numWords.get(1));

		for (int i = 1; i < numWords.size() - 1; i++) {
			currentword = numWords.get(i);
			nextword = numWords.get(i + 1);
			int count = 0;

			inner: for (int j = 0; j < wordList.size(); j++) {
				currentnode = wordList.get(j).getWord();

				if (currentword.equals(currentnode)) {
					wordList.get(j).addNextWord(nextword);
					break inner;
				}
				count++;
			}

			if (count == wordList.size()) {
				haha = new ListNode(currentword);
				wordList.add(haha);
				haha.addNextWord(nextword);
			}
		}
		
		
		//For Last Word 
		haha = new ListNode(numWords.get(numWords.size()-1));
		wordList.add(haha);
		haha.addNextWord(starter);
		}
		//System.out.println(toString());
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		// TODO: Implement this method
		int count =0;
		String currentword = starter;
		StringBuilder output = new StringBuilder();
		
		for(int j=0; j<numWords; j++){
			
			// find the node for the word
			inner:for(int i=0; i<wordList.size(); i++){
				if(currentword.equals(wordList.get(i).getWord())){
					
					//Select a random node from the List
					String randomword = wordList.get(i).getRandomNextWord(rnGenerator);
					System.out.println(randomword);
					// Add to output
					output.append(randomword).append(" ");
					
					currentword = randomword;
			
					count++;
					break inner;
				}
			}
		}
		
		System.out.println(count);
		String randomsent = output.toString();
		return randomsent;
	}

	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		train(sourceText);
		
	}

	// TODO: Add any private helper methods you need here.
	protected List<String> getTokens(String pattern, String text) {
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}

	/**
	 * This is a minimal set of tests. Note that it can be difficult to test
	 * methods/classes with randomized behavior.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		 String textString = "This is a test. test bob ";
		
		System.out.println(textString);
		gen.train(textString);
		
		  System.out.println(gen); 
		  System.out.println(gen.generateText(20));
		  

	/*	  String textString2 = "You say yes, I say no, "+
		  "You say stop, and I say go, go, go, "+
		  "Oh no. You say goodbye and I say hello, hello, hello, "+
		  "I don't know why you say goodbye, I say hello, hello, hello, "+
		 "I don't know why you say goodbye, I say hello. "+
		  "I say high, you say low, "+ "You say why, and I say I don't know. "+
		  "Oh no. "+ "You say goodbye and I say hello, hello, hello. "+
		  "I don't know why you say goodbye, I say hello, hello, hello, "+
		  "I don't know why you say goodbye, I say hello. "+
		  "Why, why, why, why, why, why, "+ "Do you say goodbye. "+ "Oh no. "+
		  "You say goodbye and I say hello, hello, hello. "+
		  "I don't know why you say goodbye, I say hello, hello, hello, "+
		  "I don't know why you say goodbye, I say hello. "+
		  "You say yes, I say no, "+ "You say stop and I say go, go, go. "+
		  "Oh, oh no. "+ "You say goodbye and I say hello, hello, hello. "+
		  "I don't know why you say goodbye, I say hello, hello, hello, "+
		  "I don't know why you say goodbye, I say hello, hello, hello, "+
		  "I don't know why you say goodbye, I say hello, hello, hello,";
		  System.out.println(textString2); gen.retrain(textString2);
		  System.out.println(gen); System.out.println(gen.generateText(20));*/
		 
	}

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator) {
		// TODO: Implement this method
		// The random number generator should be passed from
		// the MarkovTextGeneratorLoL class
		 generator = new Random();
		String word = nextWords.get(generator.nextInt(nextWords.size()));
		
		return word;
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}
