package sm.nlp.spam.review;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sm.nlp.spam.xml.Feature;

public class ReviewParseTester {

	public ReviewParseTester(){
		
	}
	
	public List<Review> parseReviewFromFile(String fileName) throws FileNotFoundException{
		
		List<Review> reviewList = new ArrayList<Review>();
		
		Scanner read = new Scanner(new File(fileName));
		
		String title = "";
		String content="";
		
		String prod_id = "";
		String auth_id = "";
		int review_count=0;
		double total_review_rating=0;
		
		while(read.hasNext()){
			String line = read.nextLine();
		
			if(line.startsWith("@")){
				line = line.substring(1);	// remove initial @
				
				if(line.startsWith("author")){
					auth_id = line.replace("author:", "");
				}else if(line.startsWith("product")){
					prod_id = line.replace("product:", "");
				}
			}
			if(line.startsWith("[t]")){
				Review rev = getReviewFromRaw(title, content, prod_id, auth_id);
				
				total_review_rating += rev.review_rating;
				++review_count;
			/*	Feature feat = new Feature(rev);
				System.out.println(feat);			
			*/
				reviewList.add(rev);
				title = line.substring(3); // remove [t] init part
				content = "";
			
			}else {
				content += line.replaceAll("##", "");
			}
		}
		
		total_review_rating = total_review_rating / review_count;
		
		System.out.println("avg Review: "+total_review_rating);
		for(Review currentReview : reviewList){
			currentReview.review_rating = total_review_rating;
			System.out.println(currentReview);
		}
		// DUMMY CODE
		return reviewList;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ReviewParseTester review = new ReviewParseTester();
		
		review.parseReviewFromFile("reviews//garbage_review.txt");
		/*Pattern pattern = Pattern.compile("\\[(\\+|\\-)[0-9]+\\]");
		Matcher mat = pattern.matcher("I have [+25] [-5] two values.");
		
		while(mat.find()){
			System.out.println(mat.group());
		}
		*/
	}
	
	public static Review getReviewFromRaw(String title, String content, String productId, String reviewerId){
		String influence_pattern="\\[(\\+|\\-)[0-9]+\\]";
	
		int positive_influence =0, negative_influence=0;
		Pattern pattern = Pattern.compile(influence_pattern);
		Matcher mat = pattern.matcher(content);
		
		while(mat.find()){
			String influence  = mat.group();
			if(influence.startsWith("[+")){
				positive_influence += Integer.parseInt(influence.substring(2, influence.length()-1));
			}	else {
				negative_influence += Integer.parseInt(influence.substring(2, influence.length()-1));
			}
			
		}
		
		content = content.replaceAll(influence_pattern, "");
		
		Review review = new Review();
		review.content = content;
		review.title = title;
		// (Pos - Neg + 1) * 5
		//------------
		// Pos + Neg + 1
		review.review_rating = 1.5 + (double)Math.abs((positive_influence-negative_influence)+1)*3.5 / (positive_influence+negative_influence+1);
		
		if(positive_influence < negative_influence){
			review.review_rating = 1.0;
		}else if(positive_influence == negative_influence){
			review.review_rating = 1.5;
		}
		
		/*Product prod = ProductMap.map.get(productId);
		if(prod != null){
			review.product_price = prod.price;
			review.product_rating = prod.rating;
			review.sales_rank = prod.sales_rank;		
		}
		
		Reviewer rev = ReviewerMap.reviewMap.get(reviewerId);
		if(rev != null){
			review.reviewer_rating = rev.data.avg_rating;
		}
		*/
		return review;
	}
}
