package sm.nlp.spam.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import sm.nlp.spam.review.Review;
import sm.nlp.spam.review.ReviewerMap;

@Root(name="Reviewer")
public class ReviewerFeature {
	
	private String reviewerId;
	
	@Element(name="no_good_review", required=false)
	private double good_review;
	
	@Element(name="avg_rating", required=false)
	private double avg_rating;
	
	@Element(name="no_feedback",required=false)
	private int no_feedback;
	
	public ReviewerFeature(String reviewerId, double good_review, int no_feedback, double avg_rating) {
		super();
		this.reviewerId = reviewerId;
		this.good_review = good_review;
		this.avg_rating = avg_rating;	
		this.no_feedback= no_feedback;
	}
	
	public double getGood_review() {
		return good_review;
	}
	public void setGood_review(double good_review) {
		this.good_review = good_review;
	}
	public double getAvg_rating() {
		return ReviewerMap.getAvgReviewerRating(this.reviewerId);
	}
	public void setAvg_rating(double avg_rating) {
		this.avg_rating = avg_rating;
	}
	
	public static ReviewerFeature getReviewerFeature(Review review) {
		int goodness = review.no_helpful_feedback;
		int no_feedback = review.no_feedback;
		double reviewer_rating = review.review_rating;
		
		
		ReviewerMap.addReviewerToMap(review);
		return new ReviewerFeature(review.reviewer.id ,goodness, no_feedback, reviewer_rating);
	}

	@Override
	public String toString() {
		return good_review + ","
				+ getAvg_rating() + ","+no_feedback + ",";
	}
	
	public boolean isSpam() {
		if(getAvg_rating() >= 4.5){
			if((good_review * 1.0 / no_feedback) < 0.1)
				return true;
		}
		return false;
	}
	
	
}
