<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href='css/dl-allcss.css' type='text/css'>
<link rel="stylesheet" href='css/dl-main.css' type='text/css'>

<style>
article {
	width: 100%;
}

.productinfo {
	float: center;
	margin: 0 28%;
}

.productWhere {
	margin-top: auto;
	margin-right: auto;
	width: 80%;
	float: right;
}

.productcase {
	width: auto;
	heigh: auto;
}

.productDetail {
	/* 	float: right; */
	/* 	text-align: center; */
	width: 100%;
	height: 80%;
	padding-top: 60px;
}

.productWhere li {
	display: inline-block;
	/* 	box-shadow: gray 5px 5px 20px; */
	width: 25%;
	height: 25%;
	padding: 0px;
	vertical-align: top;
	margin: 15px;
	/* 	float: right; */
}

.productWhere li a img {
	display: block;
	margin: auto;
	width: 90%;
	float: center;
}
</style>

<body>
	<header>
		<jsp:include page="/header">
			<jsp:param value="產品資訊" name="subtitle" />
		</jsp:include>
	</header>

	<div class='productinfo'>
		<div class='productData'>
			<img style="margin-right: 60px;" id='photo'
				src="${prodInfo.photoUrl}">
		</div>
		<div class='productDetail'>
			<h1>${prodInfo.name}</h1>
			<div class="protext">品牌:${prodInfo.brand}</div>
			<div class="protext">數量:${prodInfo.stock}</div>
			<div class="protext">NT:${prodInfo.unitPrice}</div>
		</div>
	</div>
</body>

