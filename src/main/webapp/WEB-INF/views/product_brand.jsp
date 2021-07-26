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
<%
	request.setCharacterEncoding("UTF-8");
//1.取得request中的form data
String search = request.getParameter("search");
String brand = request.getParameter("brand");
%>
<script>
$(document).ready(init);
function init() {
	repopulateFormData();
} 
function repopulateFormData(){
	<%if (search != null) {%>
		
		$("#search").val('<%= search%>');
	<%}%>
	
	
 }


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
<%--  <% //2.呼叫商業邏輯 --%>
<!--        ProductService service = new ProductService(); -->
<!--        List<Product> list = null; -->
      
<!--        if(search!=null ){ -->
<!--      	  list=service.getProductsByBrand(search ); -->
<!--        }else if(search == null && type !=null){ -->
<!--      	  list=service.getProductsByNameAndType("", type); -->
<!--        }else{ -->
<!--      	  list = service.getAllProducts();//暫時防止500 -->
<!--       } -->
<!--        List<String> brandlist = service.getAllDistinctBrand(); -->
<!--        List<String> typelist = service.getAllDistinctType(); -->
<%--  %> --%>

<body>
	<header>
		<jsp:include page="/subviews/header.jsp" >
		<jsp:param value="產品資訊" name="subtitle"/>
		</jsp:include>
	</header>
	<!-- <article> -->
	<div class="leftSort">
		<!-- <h2>吉他</h2> -->
		<!-- <ul class="leftSort-1"> -->
		<!--   <li><a href="product_list.jsp">Faith</a></li> -->
		<!--   <li><a href="#">Martin</a></li> -->
		<!--   <li><a href="#">Taylor</a></li> -->
		<!--   <li><a href="#about">About</a></li> -->
		<!-- </ul> -->
		<!-- <h2>配件</h2> -->
		<!-- <ul class="leftSort-1"> -->
		<!--  <li><a hreff="#">1</a></li> -->
		<!--  <li><a hreff="#">2</a></li> -->
		<!--  <li><a hreff="#">3</a></li> -->
		<!--  <li><a hreff="#">4</a></li> -->
		<!-- </ul> -->
		<!-- <h2>其他</h2> -->
		<!-- <ul class="leftSort-1"> -->
		<!--  <li><a hreff="#">1</a></li> -->
		<!--  <li><a hreff="#">2</a></li> -->
		<!--  <li><a hreff="#">3</a></li> -->
		<!--  <li><a hreff="#">4</a></li> -->
		<!-- </ul> -->
	</div>
	<!-- 這裡是商品區 -->
	<div class="productcase">
		<div class="productWhere">
			<article style="overflow: hidden;">
			               
		      <ul>

				    
				<li name="faith">
				  <a href="product_list.jsp?brand=<%= "Faith"%>">
				   <img src="https://guitartogo-music.com/wp-content/uploads/%E7%B6%B2%E9%A0%81%E5%88%86%E9%A1%9E%E5%9C%96-%E7%90%B4%E9%A0%AD_faith-2-500x500.jpg">
				  </a>
					<h2>FAITH 英國最佳原聲吉他</h2>	
				</li>
				<li>
				  <a href="product_list.jsp?brand=<%="Taylor"%>"> 
				   <img src="https://guitartogo-music.com/wp-content/uploads/%E7%B6%B2%E9%A0%81%E5%88%86%E9%A1%9E%E5%9C%96-%E7%90%B4%E9%A0%AD_taylor-1-1-500x500.jpg">
				  </a>
					<h2>TAYLOR 美國木吉他</h2>
				</li>
				<li>
				  <a href="product_list.jsp?brand=<%="Martin"%>"> 
				   <img src="https://guitartogo-music.com/wp-content/uploads/%E7%B6%B2%E9%A0%81%E5%88%86%E9%A1%9E%E5%9C%96-%E7%90%B4%E9%A0%AD_Martin-3-500x500.jpg">
				  </a>
					<h2>MARTIN 美國百年吉他</h2>
				</li>
				<li>
				  <a href="product_list.jsp?brand=<%="aNueNue"%>"> 
				   <img src="https://guitartogo-music.com/wp-content/uploads/%E7%B6%B2%E9%A0%81%E5%88%86%E9%A1%9E%E5%9C%96-%E7%90%B4%E9%A0%AD_anuenue-2-500x500.jpg">
				  </a>
					<h2>ANUENUE 台日合作吉他</h2>
				</li>
				<li>
				  <a href="product_list.jsp?brand=<%="Veelah"%>"> 
				   <img src="https://guitartogo-music.com/wp-content/uploads/%E7%B6%B2%E9%A0%81%E5%88%86%E9%A1%9E%E5%9C%96-%E7%90%B4%E9%A0%AD_VEELAH-2-500x500.jpg">
				  </a>
					<h2>VEELAH 台灣優質吉他</h2>
				</li>
				<li>
				  <a href="product_list.jsp?brand=<%="Trumon"%>"> 
				   <img src="https://guitartogo-music.com/wp-content/uploads/%E7%B6%B2%E9%A0%81%E5%88%86%E9%A1%9E%E5%9C%96-%E7%90%B4%E9%A0%AD_TRUMON-1-1-500x500.jpg">
				  </a>
					<h2>TRUMON 楚門吉他</h2>
				</li>
				
				
					
				</ul>
				</article>
		</div>
	</div>


	<%-- 	<%@ include file="/subviews/leftbar.jsp"%> --%>

	
	<%@ include file="/subviews/footer.jsp"%>
</body>
</html>