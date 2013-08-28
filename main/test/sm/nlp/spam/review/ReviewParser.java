package sm.nlp.spam.review;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import sm.nlp.spam.xml.Feature;

//<member id> \t <product id> \t <date> \t <number of helpful feedbacks> \t <number of feedbacks>
// \t <rating> \t <title> \t <body>
public class ReviewParser {

	public static void main(String[] args) throws FileNotFoundException {
		String line;
		
		Scanner read = new Scanner(new File("reviews//test_review.txt"));
		
		while(read.hasNext()){
			line = read.nextLine();
		
			Review rev = Review.parseReviewFromText(line);
			Feature feature = new Feature(rev);
		
			System.out.println(feature);
		}
	/*	String[] split = line.split("[\t]");
	
		String mem_id = split[0];
		String prod_id = split[1];
		String date = split[2];	// not going to use this
		int no_helpful_feedback = Integer.parseInt(split[3]);
		int no_feedback = Integer.parseInt(split[4]);
		double rating = Double.parseDouble(split[5]);
		String title = split[6].trim();
		String body = split[7].trim();
		
		Review review = new Review();
		review.content = body;
		review.title = title;
		review.review_rating = rating;
		review.no_feedback = no_feedback;
		review.no_helpful_feedback = no_helpful_feedback;
		
		review.product = new Product(prod_id, 0.0, "");
		review.reviewer = new Reviewer(mem_id, 0.0);
	*/
	}
}
