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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<script src='https://kit.fontawesome.com/95ce010baa.js' crossorigin='anonymous'></script>
<style >
article{	
	width: 100%;	
}
.productWhere {
margin-top:  auto;
margin-right: auto;
width: 80%;
float: right;
}
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
	width: 90%;
	float: center;
}
a{
color: #6b3d04;
}
</style> 
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
  function repopulateFormData() {
	  <% if(search!=null){%>
		//search.value='<%= search%>';
		$("#search").val('<%= search %>');
	<%}%>

	<% if(brand!=null){%>
		$("#brand").val('<%= brand %>');
	<%}%>	
}
</script>
</head>
<body>
  <header>
    <jsp:include page="/subviews/header.jsp"/>
  </header>  
 <% //2.呼叫商業邏輯
      ProductService service = new ProductService();
      List<Product> list = null;
      if(brand!=null ){
    	  list=service.getProductsByBrand(brand);
      }else{
    	  list = service.getAllProducts();//暫時防止500
      }
 %>
   <article style="overflow:hidden;" >
 <div class="productcase"> 
 <div class="productWhere">   
   <% if(list!=null || list.size()>0) {%> 
   <ul class="productList">
<!--     以下是抓商品資訊  -->
   <% for(int i=0;i<list.size();i++){ 
	   Product p =list.get(i); %>
    <li>
    <a href="product_detail.jsp?productId=<%=p.getId()%>"> 
<img src='<%= p.getPhotoUrl() %>'>
    
       <h4><%= p.getName() %></h4>
<div>NT $ <%= p instanceof Outlet?((Outlet)p).getDiscountString():""%> <%= Math.round(p.getUnitPrice()) %></div> 
    <% } %>    
   </ul>
   <% }else{ %>
   <p>查無此產品!</p>
   <%} %>
 </div>
 
 </div> 
 
 <%@ include file="/subviews/leftbar.jsp" %>
  </article>
  <%@ include file="/subviews/footer.jsp" %>
</body>
</html>