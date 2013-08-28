package sm.nlp.spam.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import sm.nlp.spam.review.ProductMap;
import sm.nlp.spam.review.Review;

@Root(name="Product")
public class ProductFeature {
	private String prod_id ;
	
	@Element(name="avg_rating", required=false)
	private double avg_rating;
	
	public ProductFeature(String id, double avg_rating) {
		super();
		this.avg_rating = avg_rating;
		this.prod_id = id;
	}
	
	public double getAvg_rating() {
		return ProductMap.getAvgRatingFor(this.prod_id);
	}
	
	public void setAvg_rating(double avg_rating) {
		this.avg_rating = avg_rating;
	}
	
	public static ProductFeature getProductFeature(Review review) {
		double rating = review.review_rating; //review.product_rating;
		String id = review.product.prod_name;
		
		ProductFeature prod = new ProductFeature(id, rating);
		ProductMap.putIntoMap(review);
		
		return prod;
	}

	@Override
	public String toString() {
		return getAvg_rating() + ",";
	}
	
	public boolean isSpam(){
		// too less or too high is likely spam
		if(this.avg_rating<2 || this.avg_rating >= 4.5) return true;
	
		return false;
	}
}
