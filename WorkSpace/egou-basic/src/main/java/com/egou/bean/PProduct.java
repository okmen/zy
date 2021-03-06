package com.egou.bean;

import java.util.Date;

/**
 * 用户表
 * @author Administrator
 *
 */
public class PProduct {
    private Long productid;

    private Long brandid; 

    private Integer classid;

    private String title;

    private Long supplieruserid;

    private Long sellerid;

    private Double defaultprice;

    private String defaultimg;

    private Date createtime;

    private Date updatetime;

    private Integer score;

    private Long postmodelid;

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

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public Double getDefaultprice() {
        return defaultprice;
    }

    public void setDefaultprice(Double defaultprice) {
        this.defaultprice = defaultprice;
    }

    public String getDefaultimg() {
        return defaultimg;
    }

    public void setDefaultimg(String defaultimg) {
        this.defaultimg = defaultimg == null ? null : defaultimg.trim();
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getPostmodelid() {
        return postmodelid;
    }

    public void setPostmodelid(Long postmodelid) {
        this.postmodelid = postmodelid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}