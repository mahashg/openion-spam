package sm.nlp.spam.review;

public class Review {
	// review related
	public String title;
	public String content;
	public double review_rating; // review gave rating to product
	
	// product related
	public Product product;

	// reviewer related
	public Reviewer reviewer;
	public int no_helpful_feedback;
	public int no_feedback;

	public static Review parseReviewFromText(String data){
		
		String[] split = data.split("[\t]");
		
		String mem_id = split[0];
		String prod_id = split[1];
		String date = split[2];	// not going to use this
				
		Review review = new Review();
		review.content = split[7].trim();
		review.title = split[6].trim();
		review.review_rating = Double.parseDouble(split[5]);
		review.no_feedback = Integer.parseInt(split[4]);
		review.no_helpful_feedback = Integer.parseInt(split[3]);
		
		review.product = new Product(prod_id, 0.0, "movie");
		review.reviewer = new Reviewer(mem_id, 0.0);

		return review;
	}
	
}
