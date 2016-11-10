package com.egou.bean;

import java.util.Date;
/**
 * 产品信息变更临时表
 * @author Administrator
 *
 */
public class PProductOcc {
	
    private Long productid;

    private Date updatetime;

    private Integer status;

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
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