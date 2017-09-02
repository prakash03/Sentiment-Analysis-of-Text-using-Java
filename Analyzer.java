package edx_course.SentimentAnalysis;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Analyzer {
	public static List<Sentence> readFile(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename ))) {
			String sCurrentLine;
			//List<String> sentences = null;
			List<Sentence> sentences = new ArrayList<>();
			while ((sCurrentLine = br.readLine()) != null) {
				//sentences.add(sCurrentLine);
				String[] tokens = sCurrentLine.split(" ");
				//System.out.println(Arrays.toString(tokens)); //Remember this format of printing Arrays. You cannot do tokens.toString()
				String text = " ";
				for (int i=1;i<tokens.length;i++){
					text = text + " " + tokens[i];
				}
				//System.out.println(tokens[0] + text);
				Sentence sentence = new Sentence(Integer.valueOf(tokens[0]), text);
				sentences.add(sentence);

				//Sentence sentence = new Sentence(tokens[0],tokens[0:-1]);
				//System.out.println(sCurrentLine);
			}
			return sentences;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; // this line is here only so this code will compile if you don't modify it

	}

	public static Set<Word> allWords(List<Sentence> sentences) {
	try {
		Set<Word> words = new HashSet<>();
		ArrayList<Word> newWords = new ArrayList<>();//Use of ArrayList for achieving the set has taken me 2 days.
		//Array List has been used to be able to add word objects in, and to keep checking if that word object has been previously
		//added. If it has been previously added, we get its existing total and increase it's total.
		//This ability to retrieve an existing element and getting it's attributes is missing when we add objects directly into the Set
		//data structure. So after all the object's have successfully been added to the ArrayList, we store the Word objects in the Set
		//at the end.
		for (Sentence sentence : sentences) {
			String[] tokens = sentence.getText().split(" ");
			for (String token : tokens) {
				if (token.matches("[A-Za-z]+")) { //Checking this to avoid special characters like '.',',','...' etc
					token = token.toLowerCase();
					Word word = new Word(token);
					word.increaseTotal(sentence.getScore());
					if (newWords.contains(word)){
						int indexOfWord  = newWords.indexOf(word); //Without array list, getting the words, was a nightmare.
						newWords.get(indexOfWord).increaseTotal(word.getTotal());
						//newWords.get(indexOfWord).increaseCount();
					}
					else{
						newWords.add(word);
					}
				}
			}
		}
		//Copying all the word objects now in to the Set.
		for (Word word : newWords){
			words.add(word);
		}
		/*
		for (Word word : words){
			System.out.println( word.getText() + " total is " + word.getTotal() + " and count is " + word.getCount());
		}
		System.out.println("Size of set is :" + words.size());
		*/
		return words;
	}
	catch (Exception e) {
		e.printStackTrace();
	}
		return null; // this line is here only so this code will compile if you don't modify it
	}

	public static Map<String, Double> calculateScores(Set<Word> words) {
	try{
		Map<String, Double> wordScores = new HashMap<>();
		for (Word word : words){
			wordScores.put(word.getText(),word.calculateScore());
		}
		for (Map.Entry<String, Double> entry:wordScores.entrySet()){
			String word = entry.getKey();
			double score = entry.getValue();
			System.out.println(word + " score is " +  score);
		}
		return wordScores;
	}
	catch(Exception e){
		e.printStackTrace();
	}
		return null; // this line is here only so this code will compile if you don't modify it

	}

	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
	try{
		String[] tokens = sentence.split(" ");
		double sentiment = 0;
		for (String token: tokens){
			if (token.matches("[A-Za-z]+")){
				if(wordScores.containsKey(token)){
					sentiment += wordScores.get(token);
				}
				else sentiment += 0; //If the word is not already in the Map (unseen word), assign it's score to be zero.
			}
		}
		return sentiment;
	}
	catch (Exception e){
		e.printStackTrace();
	}

		return 0; // this line is here only so this code will compile if you don't modify it

	}

	public static void main(String[] args) {
		/*
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		*/

		//testing the working of "readFile" method.
		String filename = "reviews.txt";
		List<Sentence> output = readFile(filename);
		Set<Word> words = allWords(output);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		System.out.println("Type a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
		/*
		Set<Word> words = allWords(output);
		System.out.println(words.size());
		for (Word word : words){
			System.out.println(word.text + " " + word.getTotal() + " " + word.getCount());

		}
		*/

		/*
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
		*/
	}
}
