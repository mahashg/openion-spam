package sm.nlp.spam.ngram;

class NGram{
	String str1 , str2;
	int count;
	
	public NGram(String[] strArr) {
		this.str1 = strArr[0];
		this.str2 = strArr[1];
		this.count = 1;
	}

	public void addCount(int c) {
		this.count = this.count + c;		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof NGram)){
			return false;
		}
		NGram ng = (NGram) obj;
		
		
		return this.hashCode() == ng.hashCode();
	}
	
	@Override
	public int hashCode() {
		if(str1.compareTo(str2) > 0){
			new String(str1+"$"+str2).hashCode();
		}
		
		return new String(str2+"$"+str1).hashCode();		
			
	}
	
	@Override
	public String toString() {		
		return "[("+this.str1+", "+this.str2+") => "+this.count+"]";
	}
}