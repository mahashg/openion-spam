package sm.nlp.spam.ngram;

public class NGramTester {

	public static void main(String[] args) {
		String sentence1 = "i love i love i love";
		String sen2 = "i love you priyanka";
		
		NGramGroup ng1 = parseSentence(sentence1);
		ng1 = ng1.union(parseSentence(sen2));
		System.out.println(ng1);
		
		ng1 = ng1.intersection(parseSentence(sen2));
		System.out.println(ng1);
	}
	
	private static NGramGroup parseSentence(String s){
		NGramGroup group = new NGramGroup();
		
		String[] arr = s.split(" ");
		int gpSize = 2;
		
		for(int i=0 ; i<arr.length-gpSize+1 ; ++i){
			String[] strArr = new String[gpSize];
			for(int j=0 ; j<gpSize ; ++j){
				strArr[j] = arr[i+j];
			}
			
			group.add(new NGram(strArr));
		}
		
		
		return group;
		
	}

}
