package sm.nlp.spam.util;

public class TextUtilTester {
	public static void main(String[] args) {
		String line = "I have 3 people in the line who are 9 miles away from 7 km east.";
		int count = TextUtils.getPatternOccurenceCount("[0-9]+", line);
		System.out.println(count);
	
		String line2 = "I Love you DArling.";
		count = TextUtils.getPatternOccurenceCount("[A-Z]", line2);
		System.out.println(count);
		
		String line3 = "I Love you DArling. LoveLove";
		count = TextUtils.getPatternOccurenceCount("Love", line3);
		System.out.println(count);
	}
}
