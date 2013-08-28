package sm.nlp.spam.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import sm.nlp.spam.review.Review;
import sm.nlp.spam.util.TextUtils;
import sm.nlp.spam.util.WordList;

@Root(name="textual")
public class TextualFeature {
	@Element(name="positive_review", required=false)
	private int positive_review;
	
	@Element(name="negative_review", required=false)
	private int negative_review;
	
	@Element(name="numbers", required=false)
	private int num_count;
	
	@Element(name="captial_letter", required=false)
	private int capital_count;
	
	public TextualFeature(int positive_review, int negative_review, int num_count,
			int capital_count) {
		
		super();
		this.positive_review = positive_review;
		this.negative_review = negative_review;
		this.num_count = num_count;
		this.capital_count = capital_count;
	}
	
	public int getPositive_review() {
		return positive_review;
	}
	public void setPositive_review(int positive_review) {
		this.positive_review = positive_review;
	}
	public int getNegative_review() {
		return negative_review;
	}
	public void setNegative_review(int negative_review) {
		this.negative_review = negative_review;
	}
	public int getNum_count() {
		return num_count;
	}
	public void setNum_count(int num_count) {
		this.num_count = num_count;
	}
	public int getCapital_count() {
		return capital_count;
	}
	public void setCapital_count(int capital_count) {
		this.capital_count = capital_count;
	}	
	
	
	public static TextualFeature getTextFeature(Review review){
		int num_count = TextUtils.getPatternOccurenceCount("[0-9]+", review.content);
		int capital_count = TextUtils.getPatternOccurenceCount("[A-Z]+", review.content);
		int positive_words_count = TextUtils.getWordCountFromList(WordList.positive_word_list, review.content);
		int negative_words_count = TextUtils.getWordCountFromList(WordList.negative_word_list, review.content);
		
		// Dummy Code
		return new TextualFeature(positive_words_count, negative_words_count, num_count, capital_count);
	}
	
	@Override
	public String toString() {
		return positive_review
				+ "," + negative_review				
				+ "," + num_count + ","
				+ capital_count ;
	}
	

	public boolean isSpam(){
		if(num_count >=5){ return false; } // lots of factual data
		if(positive_review > 20 || negative_review > 20) {	return true;}
		if(capital_count > 20){	return true; }
		
		return false;
	}
}
