package com.egou.search.vo;

import java.util.List;
import java.util.Map;


public class SearchResultBean {
	private int totalHits;
     private List<Map<String,Object>> docs;

     public SearchResultBean()
     {
         
     }
     public int getTotalHits()
     {
         return this.totalHits;
    }
 
     public void setTotalHits(int totalHits)
     {
         this.totalHits = totalHits;
     }
	public List<Map<String, Object>> getDocs() {
		return docs;
	}
	public void setDocs(List<Map<String, Object>> docs) {
		this.docs = docs;
	}

     
     

}
