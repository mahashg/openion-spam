package sm.nlp.spam.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordList {
	public static List<String> positive_word_list = new ArrayList<String>();
	
	public static List<String> negative_word_list = new ArrayList<String>();
	
	static {
		try {
			Scanner read = new Scanner(new File("store//negative_word.txt"));
		
			while(read.hasNext()){
				negative_word_list.add(read.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			Scanner read = new Scanner(new File("store//positive_word.txt"));
		
			while(read.hasNext()){
				positive_word_list.add(read.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
