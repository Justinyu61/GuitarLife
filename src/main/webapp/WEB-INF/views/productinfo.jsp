<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type='text/css' href='css/dl.css'>
<link rel="stylesheet" href='css/dl-allcss.css' type='text/css'>
<link rel="stylesheet" href='css/dl-main.css' type='text/css'>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<style>
.productinfo {
	float: center;
	margin: 0 28%;
}

.protext {
	white-space: nowrap;
	color: #111;
	font-weight: bold;
}

.productDetail {
	/* 	float: right; */
	/* 	text-align: center; */
	width: 100%;
	height: 80%;
	padding-top: 60px;
}

.productDetail h1 {
	width: 100%;
	margin-top: 60;
	text-rendering: optimizeSpeed;
	font-size: 1.4em;
	line-height: 1.3;
	color: #555555;
}

.productData img {
	float: left;
	width: 100%;
	max-width: 400px;
	min-width: 20;
	pxdisplay: inline-block;
	vertical-align: middle;
	border-style: none;
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
			<img style="margin-right: 60px;" id='photo' src="${prodInfo.photoUrl}">
		</div>
		<form class="cart" action="/GuitarLife/shoppingcart" method="GET">
			<div class='productDetail'>
				<h1>${prodInfo.name}</h1>
				<div class="protext">品牌:${prodInfo.brand}</div>
				<div class="protext">數量:${prodInfo.stock}</div>
				<div class="protext">NT:${prodInfo.unitPrice}</div>
			</div>
			<input type='hidden' name='productId' value='${prodInfo.id}'>

			<input type="submit" value="加入購物車" onClick=alert('${prodInfo.id}')>
		</form>
	</div>
</body>

