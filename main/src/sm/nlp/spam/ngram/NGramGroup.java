package sm.nlp.spam.ngram;

import java.util.ArrayList;
import java.util.List;

public class NGramGroup {
	private List<NGram> ngram;

	public NGramGroup() {
		ngram = new ArrayList<NGram>();
	}
	
	public void add(NGram ng){
		if(ng == null){	return ;}
		
		NGram obj = get(ng);
		if(obj == null){
			ngram.add(ng);
		}else {
			obj.addCount(ng.count);
		}
	}
	
	public NGram get(NGram ng){
		for(NGram obj : ngram){
			if(obj.equals(ng)){
				return obj;
			}
		}
		return null;
	}
	
	public NGramGroup union(NGramGroup ngg){
		NGramGroup group = new NGramGroup();
		
		for(NGram obj : this.ngram){
			group.add(obj);
		}
		
		for(NGram obj : ngg.ngram){
			group.add(obj);
		}
		
		return group;
	}
	
	public NGramGroup intersection(NGramGroup ngg){
		NGramGroup group = new NGramGroup();
		
		for(NGram obj : this.ngram){
			NGram obj2;
			if((obj2 = ngg.get(obj)) != null){
				group.add(obj);
				group.add(obj2);
			}
		}
		
		return group;
	}
	
	@Override
	public String toString() {
		if(this.ngram.isEmpty()){	return ""; }
		
		StringBuilder sb = new StringBuilder();
		for(NGram ng : ngram){
			sb.append("\n"+ng);
		}
		
		return sb.toString();
	}
}