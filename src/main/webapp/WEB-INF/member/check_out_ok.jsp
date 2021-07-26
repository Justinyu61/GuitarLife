<%@page import="uuu.DL.entity.*"%>

<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>結帳成功</title>
<link rel="stylesheet" type='text/css' href='css/dl.css'>
<link rel="stylesheet" href='../css/dl-allcss.css' type='text/css'>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script src='https://kit.fontawesome.com/95ce010baa.js'
	crossorigin='anonymous'></script>
<style>

.reupdate{
background-color: #446084;
opacity: .6;
border: 2px solid currentColor;
line-height: 2.3em;
color: white;
}
.return{
background-color: #446084;
opacity: .6;
border: 2px solid currentColor;
line-height: 2.3em;
color: white;
}
</style>
</head>
<body>
<header>
		<jsp:include page="/subviews/header.jsp" >
		<jsp:param value="會員登入" name="subtitle"/>
		</jsp:include>
	</header>
	<%
	Order order = (Order)session.getAttribute("order");
	Customer member = (Customer)session.getAttribute("member");	
	%>
	
	<div style="height: 100vh;overflow: scroll;">
	 <div style="float: left; width: 100%;display: block;height: 7em;">
	  <h1>訂單成功</h1> 
	 </div>
<!-- 	********************************************左邊產品明細******************************************** -->
		 <div class="product-where" style="width: 50%;float: left;display: block;">		 		 
		 <% if(order != null){
			 order.setMember(member);
		 %>
		  
		 <% for(OrderItem item:order.getOrderItemSet()){
		     Product p = item.getProduct();
		     Color color = item.getColor();
		 %>
		
		 <div style="margin-left: 5em;width: 80%;display: flex;">
	 		<div class="img-where" style="padding: 0 20px;">
	  			<img src="<%= color!=null?color.getPhotoUrl() :p.getPhotoUrl() %>" width="150">   						  			
		   </div>
		   <div class="product-list" style="padding: 10px 0;">
			   <div>產品品牌:<%= p.getBrand() %></div>
			   <div>產品名稱:<%= p.getName() %></div>
			   <div>產品價格:<%= Math.round(p.getUnitPrice()) %></div>
			   <div>產品數量:<%= item.getQuantity() %></div>			   			
			   <div><%= p instanceof Outlet?"折扣後金額:"+((Outlet)p).getDiscountString():""%></div>			   			   
		   </div>
		   </div>
		    <%} %>		    
		    <div style="display: flex;float: right;">
			    <div style="border-right: none">
					<input class="return botton" type="button" value="返回首頁"  onclick="location.href='<%= request.getContextPath() %>'">			
				</div>
				<div style="text-align: right;border-left: none;">
					<input  class="reupdate botton" type="button" value="會員專區" onclick="location.href='<%=request.getContextPath()%>/member/update.jsp'">			
				</div>
			</div>
		 </div>
		 
		
<!-- 	********************************************右邊訂單明細********************************************  -->
	     <div class="order-where" style="float: right;width: 50%;">
	      <div class="order-list" style="border: 2px solid #446084;padding: 15px 30px 30px;width: 50%;margin-left: 2.2em;">
	       <div>訂單編號:<%= order.getId() %></div>	       
	       <div>訂購人:${sessionScope.member.name}</div>
	       <div>收件人: <%= order.getRecipientName() %></div>
	       <div>電話:<%= order.getRecipientPhone() %></div>
	       <div>收件地址:<%= order.getShippingAddress() %></div>
	       <div>E-mail:<%= order.getRecipientEmail() %></div>
	       <div>付款方式:<%= order.getPaymentType().getTWName() %>,
	          <% if(order.getPaymentFee()>0){%>手續費:<%= order.getPaymentFee() %><% }%> 
	          <% if(order.getPaymentType()==PaymentType.ATM && order.getStatus()==0){%>
	           <a href="atm_transfered.jsp?orderId=<%= order.getId() %>">通知已轉帳</a>
	          <%}else if(order.getPaymentType()==PaymentType.ATM && order.getStatus()==1){ %>
	           <br>
	           <span style="font-size:  smaller;"><%= order.getPaymentNote() %></span>
	          <%} %>
	       </div>
	       <div>貨運方式:<%= order.getShippingType().getTWName() %>,物流費:<%= Math.round(order.getShippingFee())  %>元</div>
	       <br>
	       <div>總金額:<%= Math.round(order.getTotalAmount() + order.getShippingFee() + order.getPaymentFee())   %>元</div>
	      </div>	     
	     </div>
         <%} %>
	</div>


	<%@ include file="/subviews/footer.jsp"%>
</body>
</html>