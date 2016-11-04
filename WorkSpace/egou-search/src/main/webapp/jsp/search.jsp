<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微店网-搜索</title>
<script type="text/javascript"
	src="http://base1.okwei.com/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/statics/js/search.js"></script>
<script type="text/javascript" src="/statics/js/cookieHelper.js"></script>
<link rel="stylesheet" type="text/css" href="/statics/css/base.css" />
</head>
<body>
	<div class="mar1200 logies">
		<h1 class="mt20 fl">
			<a href="http://www.okwei.com"><img
				src="http://base3.okimgs.com/images/xh_logo.gif"></a>
		</h1>
		<h2 class=" ml10 fl mt20 pr">
			<a href="http://www.okwei.com/help/aboutus"><img
				src="http://base2.okimgs.com/images/xh_gaiban_cctv.png"></a>
			<div class="pa c6 f12 tfont pl10">微店概念发起者、领导者</div>
		</h2>
		<div class="fl ml100 inputmit mt30">
			<input id="searchTxt" type="text" value="${title }"> <input type="submit"
				value="搜索" onclick="search()">
		</div>
		<div class="fr wx_dabao">
			<ul>
				<li class="img_ic1 c6">微信支付</li>
				<li class="img_ic2"><a href="http://www.okwei.com/help/us#qtbt"
					class="c6">七天包退</a></li>
				<li class="img_ic3"><a href="http://www.okwei.com/help/us#dbjy"
					class="c6">担保交易</a></li>
			</ul>
		</div>
	</div>
	
	<div class="w fl cpimgs mt20" id="showtype1div">
	<div>总共 ${page.getTotal() }条记录</div>
		<ul>
			<c:if test="${page !=null && page.getList() !=null && page.getList().size()>0}">
				<c:forEach items="${page.getList()}" var="product">
					<li class="pro_li">
						<div class="pro_div">
							<div class="imgps">
								<a target="_blank" href="#"> <img name="productImg"
									height="206" src="${product.defaultimg }" />
								</a>
							</div>
							<p class="smwzs">
								<a target="_blank" href="#">${product.title } </a>
							</p>
						</div>
					</li>
				</c:forEach>
			</c:if>
		</ul>
		<c:if
			test="${page ==null || page.getList() ==null || page.getList().size()==0}">
			<div class="null_coues fl pb30">
				<p class="f18 tc">没有符合条件的商品，请尝试其他搜索条件</p>
			</div>
		</c:if>
	</div>

	<jsp:include page="/usercontrol/footer.jsp" flush="true">
		<jsp:param name="step" value="1" />
	</jsp:include>
</body>
</html>