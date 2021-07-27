<%@page import="java.util.List"%>
<%@page import="com.gl.spring.entity.Customer"%>
<%@page pageEncoding="UTF-8"%>
<head>
<link rel="icon" href="favicon.ico" type="image/x-icon" />


<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<%
	String search = request.getParameter("search");
String type = request.getParameter("type");
%>

<script type="text/javascript">
<%--
$(document).ready(function(){
	$("#cartbtn").hover(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/cart_ajax.jsp",
				async : false,
				success : function(result) {
					$("#cart_tab").html(result);
				}
			});
		});
	});

	$(document).ready(function() {
		$("#cartbtn").mouseenter(function() {
			$("#cart_tab").css("display", "block");
		});
		$("#cart_tab").mouseenter(function() {
			$("#cart_tab").css("display", "block");
		});
		$("#cart_tab").mouseleave(function() {
			$("#cart_tab").css("display", "none");
		});
		$("#cartbtn").mouseleave(function() {
			$("#cart_tab").css("display", "none");
		});
	});
	
	
</script>
<script>
$(document).ready(init);
	
	function init(){			
		repopulateFormData();			
	}
	
	function repopulateFormData(){
		<% if(search!=null){%>
			//search.value='<%= search%>';
			$("#search").val('<%= search %>');
		<%}%>
	
		<% if(type!=null){%>
			$("#type").val('<%= type %>');
		<%}%>
	}
	function getProductDateil(productId){
		//alert(pid); //顯示產品編號
		//作法1: 同步請求
//		location.href="product_detail.jsp?productId="+productId;//ajax與同步請求不得並存
		
		//作法2: 改成非同步請求...，//ajax與同步請求不得並存
		$.ajax({
			url:'product_detail_ajax.jsp?productId=' + productId,
			method:'GET'				
		}).done(getProductDetailDoneHandler);			
	}
	
	function getProductDetailDoneHandler(result){
		//alert("Done");
		//console.log(result);
		$('#productDetailDiv').html(result);//將取得的結果寫入指定的div
		
		//用fancybox開啟燈箱來顯示productDetailDiv
		$.fancybox.open({
			src  : '#productDetailDiv',
			type : 'inline',
			opts : {
				afterShow : function( instance, current ) {
					console.info( 'done!' );
				}
			}
		});
	}		
	--%>
</script>

<style>
.page-header {
	display: flex;
	justify-content: space-around;
}

.customer-con {
	background-color: #aaa097;
	float: right;
	width: 32%;
	margin: 0;
	padding: 0;
}

.wrapper {
	max-width: 90%;
	margin: 0 auto;
}

.logo {
	display: block;
	width: 150px;
	height: 150px;
	margin-top: 14px;
	text-align: center;
}

/* header css end */

/* navbar css star */
header ul {
	width: 30%;
	margin: 0;
	padding: 0;
}

.navbar {
	text-align: center;
	max-width: 100%;
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #aaa097;
	width: 100%;
	display: flex;
}

header li {
	display: inline-block;
	height: 55px;
}

header li a, .dropbtn {
	display: inline-block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	/*   text-decoration: none; */
}

header li a:hover, .dropdown:hover .dropbtn {
	background-color: gray;
}

header li.dropdown {
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #aaa097;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: white;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
	text-align: left;
}

.dropdown-content a:hover {
	background-color: gray;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown-content .dropdown-first {
	display: none;
	position: absolute;
	background-color: #aaa097;
	min-width: 160px;
	left: 90%;
}

.dropdown-content .dropdown-first a {
	color: white;
	padding: 12px 16px;
	text-decoration: none;
}

.dropdown-content:hover .dropdown-first {
	display: block;
}

#cart_tab {
	margin: 0px;
	font-weight: bold;
	text-align: left;
	border: 3px solid white;
	background: white;
	box-shadow: 2px 2px 10px;
	border-radius: 10px;
	padding: 5px 10px 5px 10px;
}

#cart_tab input {
	text-align: center;
	width: 100%;
	background-color: grey;
	border-color: grey;
	color: white;
	border-radius: 5px;
}

#cart_tab input:hover {
	background: #23272b;
	border-color: #1d2124;
}

.tab_content {
	border: none;
	background: #eee;
}

#cartSub {
	color: white;
	position: absolute;
	right: 49px;
	top: 63px;
}
</style>
<!-- header.jsp start -->
</head>
<%
	Customer member = (Customer) session.getAttribute("member");
%>

<header class="page-header wrapper">


	<h1 class="header-logo">
		<a href='<%=request.getContextPath()%>/index.jsp'> <img
			class="logo" src="<%=request.getContextPath()%>/images/logo.jpg"
			alt="Guitar Life">
		</a>
	</h1>

	<!--    fb ig -->
	<!-- <nav class="header-nav"> -->
	<!--   <ul class="main-nav"> -->
	<!--    <li><a href="#"><img src="images/facebookicon.png"></a></li> -->
	<!--    <li><a href="#"><img src="images/instagramicon.png"></a></li> -->
	<!--   </ul> -->
	<!-- </nav> -->


	<!--                會員名子 -->
	<%-- 	          <div style='float:right'><%= member!=null?(member instanceof VIP?"VIP ":"")+member.getName():"" %></div> --%>

</header>

<!-- navbar -->

<div class="navbar">
	<div class="hold1" style="width: 30%;"></div>

	<ul>

		<li><a href='<%=request.getContextPath()%>/index'>HOME</a></li>
		<li><a href="#news">NEWS</a></li>
		<li class="dropdown"><a href="javascript:void(0)" class="dropbtn">SHOP</a>
			<div class="dropdown-content">
				<a href="<%=request.getContextPath()%>/product_brand.jsp">吉他</a>
				<div Class="dropdown-first">
					<!-- 			<a href="#">TAYLOR</a>  -->
					<!-- 			<a href="#">MARTIN</a>  -->
					<!-- 			<a href="#">FAITH</a> -->
				</div>
				<a href="<%=request.getContextPath()%>/product_brand2.jsp">配件</a> <a
					href="#">其他</a>
			</div></li>
		<li class="dropdown"><a href="javascript:void(0)" class="dropbtn">精選文章</a>
			<div class="dropdown-content">
				<a href="<%=request.getContextPath()%>/taylor_brandstory.jsp">Taylor美國木吉他</a>
				<a href="<%=request.getContextPath()%>/martin_brandstory.jsp">Martin美國百年吉他</a>
				<a href="<%=request.getContextPath()%>/faith_brandstory.jsp">Faith英國最佳原聲吉他</a>
			</div></li>
	</ul>
	<div class="hold2" style="margin-right: 7%;; display: block;"></div>
	<ul class="customer-con">

		<li>
			<%--
 ProductService service = new ProductService();
 List<Product>list = null;
 if(search!=null && type !=null){
	 list = service.getProductsByBrandAndType(search.trim(), type);
 }else if(search==null && type!=null){
	 list = service.getProductsByBrandAndType("", type);
 }else{
	 list = service.getAllProducts();
 }
 List<String> typelist = service.getAllDistinctType();
--%> <input type="search" name="search" placeholder="Search...">
			<a type="submit"> <i type="submit" class='fas fa-search'></i>
		</a>
		</li>
		<%-- 	<li id="cartbtn"><a
			<href='<%=request.getContextPath()%>/member/shopping_cart.jsp'> <i
				class='fas fa-shopping-cart'></i> <jsp:include
					page="/small_cart.jsp" /></a>
			<div id="cart_tab" class="tab_content"
				style="position: absolute; right: 190px; display: none; z-index: 8;">
				<div id="cart_item" class="cartSub">
					<jsp:include page="/cart_ajax.jsp" />
				</div>
			</div>--%>
		</li>

	</ul>

</div>





<!-- header.jsp end -->
