package com.egou.bean;

public class PSearchkeyword {
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