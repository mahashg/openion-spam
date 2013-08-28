package sm.nlp.spam.review;

import java.util.HashMap;
import java.util.Map;

public class ProductMap {
	static Map<String, AvgProdReview> map = new HashMap<String, AvgProdReview>();
	
	public static void putIntoMap(Review rev){
		AvgProdReview prod = map.get(rev.product.prod_name);
		
		if(prod == null){
			map.put(rev.product.prod_name, new AvgProdReview(rev.review_rating, 1));
		}else {
			prod.rating_total += rev.review_rating;
			prod.review_count++;
		}
	}
	
	public static double getAvgRatingFor(String id){
		AvgProdReview prod = map.get(id);
		
		if(prod == null){	return 0.0; }
		
		return (prod.rating_total / prod.review_count);
	}
}

class AvgProdReview {
	double rating_total;
	int review_count;
	
	public AvgProdReview(double total, int count){
		this.rating_total = total;
		this.review_count = count;
	}
}

