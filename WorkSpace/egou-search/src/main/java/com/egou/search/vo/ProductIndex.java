package com.egou.search.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ProductIndex {

	   private Long productid;

	    private Long brandid;

	    private Integer cateIdOne;
	    private Integer cateIdTwo;
	    private Integer cateIdThree;

	    private String title;

	    private Long supplieruserid;

	    private Long sellerid;

	    private BigDecimal defaultprice;

	    private String defaultimg;

	    private Date createtime;

	    private Date updatetime;

	    private Integer status;

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

		

		public Integer getCateIdOne() {
			return cateIdOne;
		}

		public void setCateIdOne(Integer cateIdOne) {
			this.cateIdOne = cateIdOne;
		}

		public Integer getCateIdTwo() {
			return cateIdTwo;
		}

		public void setCateIdTwo(Integer cateIdTwo) {
			this.cateIdTwo = cateIdTwo;
		}

		public Integer getCateIdThree() {
			return cateIdThree;
		}

		public void setCateIdThree(Integer cateIdThree) {
			this.cateIdThree = cateIdThree;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Long getSupplieruserid() {
			return supplieruserid;
		}

		public void setSupplieruserid(Long supplieruserid) {
			this.supplieruserid = supplieruserid;
		}

		public Long getSellerid() {
			return sellerid;
		}

		public void setSellerid(Long sellerid) {
			this.sellerid = sellerid;
		}

		public BigDecimal getDefaultprice() {
			return defaultprice;
		}

		public void setDefaultprice(BigDecimal defaultprice) {
			this.defaultprice = defaultprice;
		}

		public String getDefaultimg() {
			return defaultimg;
		}

		public void setDefaultimg(String defaultimg) {
			this.defaultimg = defaultimg;
		}

		public Date getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}

		public Date getUpdatetime() {
			return updatetime;
		}

		public void setUpdatetime(Date updatetime) {
			this.updatetime = updatetime;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
	    
	    
}
