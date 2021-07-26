<%@page import="uuu.DL.Service.BrandService"%>
<%@page import="uuu.DL.Service.ProductService"%>
<%@page import="uuu.DL.entity.Outlet"%>
<%@page import="uuu.DL.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>網路商城 | Guitar Life</title>
<link rel="stylesheet" href='css/dl-allcss.css' type='text/css'>
<link rel="stylesheet" href='css/dl-main.css' type='text/css'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<script src='https://kit.fontawesome.com/95ce010baa.js'
	crossorigin='anonymous'></script>
<style>

/*這裡是Body*/
.leftSort {
	display: inline-block;
	/*  	box-shadow: gray 5px 5px 20px;  */
	width: 250px;
	height: 1000px;
	padding: 8px;
	vertical-align: top;
	margin-top: 130px;
	margin-left: 80px;
	float: left;
}

/* .leftSort .leftSort-1 { */
/* 	list-style-type: none; */
/* 	margin: 0; */
/* 	padding: 0; */
/* 	width: 200px; */
/* 	background-color: #f1f1f1; */
/* } */

/* .leftSort .leftSort-1 a { */
/* 	display: block; */
/* 	color: #000; */
/* 	padding: 8px 16px; */
/* 	text-decoration: none; */
/* } */
.productcase {
	width: auto;
	heigh: auto;
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
	width: 98%;
	float: center;
}

.subtitle {
	font-size: smaller
}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
 function getProductDateil(pid) {
	 alert(pid);
	 //同步請求
	location.herf="product_detail.jsp?productId="+productId;
	 //TODO:改非同步請求
	 //$.ajax({
		// url:"product_detail.jsp?productId="+productId,
		//		 method:'GET'
 	 //}).done(getProductDetailHandler);
}	
  

	
</script>

</head>
<body>
	<header>
		<jsp:include page="/subviews/header.jsp" >
		<jsp:param value="產品資訊" name="subtitle"/>
		</jsp:include>
	</header>	
	<!-- 這裡是商品區 -->
	<div class="productcase">
		<div class="productWhere">
			<article style="overflow: hidden;">			               
		      <ul>
			      <li>
				    <a href="product_type.jsp?type=<%= "弦"%>">
				      <img src="https://guitartogo-music.com/wp-content/uploads/%E7%B6%B2%E9%A0%81%E5%88%86%E9%A1%9E%E5%9C%96-%E5%BC%A6-7.jpg">
				    </a>
				     <h2>吉他弦</h2>
				  </li>
				  <li>
				    <a href="product_type.jsp?type=<%= "枕"%>">
				      <img src="https://guitartogo-music.com/wp-content/uploads/%E5%8C%96%E9%AA%A8%E9%95%B7%E6%AF%9B%E8%B1%A1%E7%89%99-1-500x500.jpg">
				    </a>
				     <h2>弦枕</h2>
				  </li>
				  <li>
				    <a href="product_type.jsp?type=<%= "盒"%>">
				      <img src="https://guitartogo-music.com/wp-content/uploads/SKB-36%E5%90%8B-%E5%90%89%E4%BB%96%E7%A1%AC%E7%9B%92-3-500x500.jpg">
				    </a>
				     <h2>吉他盒</h2>
				  </li>
				  
			  </ul>
		</div>
	</div>


	<%-- 	<%@ include file="/subviews/leftbar.jsp"%> --%>

	</article>
	<%@ include file="/subviews/footer.jsp"%>
</body>
</html>