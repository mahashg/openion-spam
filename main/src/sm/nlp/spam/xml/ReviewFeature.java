package sm.nlp.spam.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import sm.nlp.spam.review.Review;

@Root(name="Review")
public class ReviewFeature {
	
	@Element(name="body", required=false)
	private int body_length;
	
	@Element(name="title", required=false)
	private int title_length;
	
	@Element(name="rating", required=false)
	private double rating;
	
	public ReviewFeature(int body_length, int title_length, double rating) {
		super();
		this.body_length = body_length;
		this.title_length = title_length;
		this.rating = rating;
	}
	
	public int getBody_length() {
		return body_length;
	}
	public void setBody_length(int body_length) {
		this.body_length = body_length;
	}
	public int getTitle_length() {
		return title_length;
	}
	public void setTitle_length(int title_length) {
		this.title_length = title_length;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public static ReviewFeature getReviewFeature(Review review){
		int ttlLen = review.title.length();
		int bdyLen = review.content.length();
		double rating = review.review_rating;
		
		return new ReviewFeature(bdyLen, ttlLen, rating);
	}

	@Override
	public String toString() {
		return body_length + ","
				+ title_length + "," + rating + ",";
	}	
	
	public boolean isSpam() {
		double content_ratio = body_length * 1.0 / title_length;
		
		if(rating < 4.5 && rating > 3){
			if(content_ratio > 100){ return true;}
		}
		return false;
	}
}
