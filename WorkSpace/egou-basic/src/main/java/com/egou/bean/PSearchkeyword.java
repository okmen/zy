package com.egou.bean;

import java.io.Serializable;

public class PSearchkeyword implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private Long wordid;

    private String keywords;

    private Long count;

    public Long getWordid() {
        return wordid;
    }

    public void setWordid(Long wordid) {
        this.wordid = wordid;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}