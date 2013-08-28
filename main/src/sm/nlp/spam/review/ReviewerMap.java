package sm.nlp.spam.review;

import java.util.HashMap;
import java.util.Map;

public class ReviewerMap {
	static Map<String, AvgReviewerRating> reviewMap = new HashMap<String, AvgReviewerRating>();

	public static void addReviewerToMap(Review rev){
		AvgReviewerRating reviewer = reviewMap.get(rev.reviewer.id);
		
		if(reviewer == null){
			reviewMap.put(rev.reviewer.id, new AvgReviewerRating( 1, rev.review_rating));
		}else {
			reviewer.no_review++;
			reviewer.total_review += rev.review_rating;
		}
	}
	
	public static double getAvgReviewerRating(String id){
		AvgReviewerRating rev = reviewMap.get(id);
		if(rev == null) {	return 0;}
		else {
			return (rev.total_review / rev.no_review);
		}
	}
}

class AvgReviewerRating {
	
	int no_review;
	double total_review;
	public AvgReviewerRating(int no, double total){
	
		this.no_review = no;
		this.total_review = total;
	}
}