package sm.nlp.spam.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

	/*
	 * Tested
	 */
	public static int getPatternOccurenceCount(String regEx, String data){
		if(regEx == null || regEx.isEmpty()){
			System.err.println("No regEx.. "+data);
			return 0;
		}
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(data);
		int count=0;
		
		while(mat.find()){
			++count;			
		}
		
		return count;
	}

	/*
	 * Tested
	 */
	public static int getWordCountFromList(List<String> positive_word_list,
			String content) {
		
		if(positive_word_list == null || positive_word_list.isEmpty()){
			System.err.println("No Regex for Word count");
			return 0;
		}
		
		int count = 0;
		for(String word : positive_word_list){
			count += getPatternOccurenceCount(word, content);
		}
		
		return count;
	}
}
