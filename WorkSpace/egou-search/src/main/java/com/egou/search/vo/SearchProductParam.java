package com.egou.search.vo;


public class SearchProductParam {

	private Long productid;

    private Long brandid; 

    private Integer cateOne;
    
    private Integer cateTwo;
    
    private Integer cateThree;

    private String title;

    private Long sellerid;

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public Long getBrandid() {
		return brandid;
	}

	public void setBrandid(Long brandid) {
		this.brandid = brandid;
	}

	public Integer getCateOne() {
		return cateOne;
	}

	public void setCateOne(Integer cateOne) {
		this.cateOne = cateOne;
	}

	public Integer getCateTwo() {
		return cateTwo;
	}

	public void setCateTwo(Integer cateTwo) {
		this.cateTwo = cateTwo;
	}

	public Integer getCateThree() {
		return cateThree;
	}

	public void setCateThree(Integer cateThree) {
		this.cateThree = cateThree;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSellerid() {
		return sellerid;
	}

	public void setSellerid(Long sellerid) {
		this.sellerid = sellerid;
	}
    
    
}
