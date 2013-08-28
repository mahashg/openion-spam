package sm.nlp.spam.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import sm.nlp.spam.review.Review;

@Root(name="Feature")
public class Feature {
	@Element(name="product", required=false)
	private ProductFeature product;
	
	@Element(name="review", required=false)
	private ReviewFeature review;
	
	@Element(name="reviewer", required=false)
	private ReviewerFeature reviewer;
	
	@Element(name="textual", required=false)
	private TextualFeature textual;	
	
	public Feature(Review review) {
		this.product = ProductFeature.getProductFeature(review);
		this.review = ReviewFeature.getReviewFeature(review);
		this.reviewer = ReviewerFeature.getReviewerFeature(review);
		this.textual = TextualFeature.getTextFeature(review);
	}

	public ProductFeature getProduct() {
		return product;
	}

	public void setProduct(ProductFeature product) {
		this.product = product;
	}

	public ReviewFeature getReview() {
		return review;
	}

	public void setReview(ReviewFeature review) {
		this.review = review;
	}

	public ReviewerFeature getReviewer() {
		return reviewer;
	}

	public void setReviewer(ReviewerFeature reviewer) {
		this.reviewer = reviewer;
	}

	public TextualFeature getTextual() {
		return textual;
	}

	public void setTextual(TextualFeature textual) {
		this.textual = textual;
	}

	@Override
	public String toString() {
		String s = isSpam() ? ",0" : ",1" ;
		return product.toString() + review.toString()
				+ reviewer.toString() + textual.toString() + s;
	}	
	
	public boolean isSpam(){
		int no_spam = 0, no_nonspam = 0;
		
		if(product.isSpam()){ ++no_spam; }else{ ++no_nonspam; }
		if(review.isSpam()){ ++no_spam; }else{ ++no_nonspam; }
		if(reviewer.isSpam()){ ++no_spam; }else{ ++no_nonspam; }
		if(textual.isSpam()){ ++no_spam; }else{ ++no_nonspam; }
		
		if(no_spam > 1) {return true;}
		return false;
	}
}
