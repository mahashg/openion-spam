package sm.nlp.spam.xml;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class XMLTester {

	public static void main(String[] args) throws Exception {
		Serializer serial = new Persister();
		
		Feature feature = new Feature(null);
		
		//feature.setProduct(new ProductFeature(10.5, 7, 8, 4));
		feature.setReview(new ReviewFeature(8, 9, 5));
		//feature.setReviewer(new ReviewerFeature(5.5, 4.8, 3.9));
		feature.setTextual(new TextualFeature(5, 4, 8, 8));
		
		
		serial.write(feature, new File("test.xml"));
	}

}
