<%@page import="uuu.DL.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="uuu.DL.Service.OrderService"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>檢視訂單</title>
<link rel="stylesheet" type='text/css' href='../css/dl.css'>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<style>
.orderTable {margin:auto;border-collapse: collapse;width: 80%;}						
			.orderTable td, .orderTable th {border: 1px solid #ccc; padding: 8px;}			
			.orderTable tr th:first-child{text-align: center}			
			.orderTable tr img {vertical-align: middle;}
			.orderTable tr:nth-child(even){background-color: #f2f2f2;}			
			.orderTable tr:hover {background-color: #ddd;}			
			.orderTable th {padding-top: 12px; padding-bottom: 12px; background-color: #aaa097; color: white;}			
			.orderTable td img{width:6%;max-width:60px;min-width:22px}
			
			.status {display:inline-block; background-color: orange; color:white; width:3.5em; height: 2.5em;padding:1ex; 
				font-size:9px; border-radius: 3px}
			.notYet{background-color: lightgray;}
			
			#transferedButton{display:inline-block; background-color: lightgray;border-radius: 2px;padding: 1px 1ex;
				box-shadow: gray 1px 1px 3px;text-decoration: none}
</style>
</head>
<body>
<header>
		<jsp:include page="/subviews/header.jsp" >
		<jsp:param value="檢視訂單" name="subtitle"/>
		</jsp:include>		
		<%
			String orderId = request.getParameter("orderId");
			Customer member = (Customer)session.getAttribute("member");
			Order order = null;
			List<OrderStatusLog> logList = null;
			if(orderId!=null && member!=null){
				OrderService oService = new OrderService();
				order = oService.getOrderById(member, orderId);
				if(order!=null){
					logList = oService.getOrderStatusLogById(orderId);
				}
			}			
		%>
		<article>
		<% if(order!=null) {%>
		<div>
		訂單編號:<%= order.getId() %>,訂購人:${sessionScope.member.name}<br>
		訂單日期時間:<%= order.getCreatedDate() %><%= order.getCreatedTime() %> <br>
		處理狀態:<%= order.getStatusString() %><br>
		付款方式:<%= order.getPaymentType().getTWName() %>
		   <% if(order.getPaymentFee()>0){ %>
		手續費:<%= order.getPaymentFee() %><% } %>
		   <% if(order.getPaymentType()==PaymentType.ATM && order.getStatus()==0){ %>
		   
		     <a href="atm_transfered.jsp?orderId=<%= order.getId()%>">通知已轉帳</a>
		     
		     <%}else if(order.getPaymentType()==PaymentType.ATM && order.getStatus()==1){ %>
		        <br><span <%= order.getPaymentNote() %>></span>
		     <% } %>
		<br>
		貨運方式:<%= order.getShippingType().getTWName() %> <br>
		<br>
		<fieldset>
					<legend>收件人</legend>姓名: <%= order.getRecipientName() %>, 
						Email: <%= order.getRecipientEmail() %>, 電話: <%= order.getRecipientPhone() %><br> 
					收件地址: <%= order.getShippingAddress() %>
					</fieldset>
			</div>
			<div style='float:left;width:40%;padding-left: 1em;text-align: right'>
					<h1>含物流費總金額: <%= order.getTotalAmount() + order.getShippingFee() + order.getPaymentFee() %>元</h1>
					<div id='statusLog'>
						<div class='status' title='<%= order.getCreatedDate() %> <%= order.getCreatedTime() %>'>
							<%= order.getStatusString(0) %><br> 
						</div>
					<% if(logList!=null && logList.size()>0){
						for(OrderStatusLog log:logList)	{						
					%>
						>
						<div class='status' title="<%= log.getUpdateTime() %>">
							<%= order.getStatusString(log.getNewStatus()) %>
						</div>
						<%}
							if(logList.get(logList.size()-1).getNewStatus()<Order.Status.COMPLETED.ordinal()){
						%>
						>
						<div class='status notYet'>
							<%= order.getStatusString(4) %>
						</div>
						>
						<div class='status notYet'>
							<%= order.getStatusString(5) %>
						</div>
						>
						<div class='status notYet'>
							<%= order.getStatusString(6) %>
						</div>
						<% } %>	
					<% } %>					
					</div>
				</div>
			</div>
			<% if(order.getOrderItemSet()!=null && order.getOrderItemSet().size()>0){%>
			<table class='orderTable'>
				<caption>訂單明細</caption>
				<thead>
					<tr>
						<th>名稱</th><th>售價</th><th>數量</th><th>小計</th>						
					</tr>
				</thead>									
			    <tbody>			    	
					<% for(OrderItem item:order.getOrderItemSet()){
						Product p = item.getProduct();
						Color color = item.getColor();						
					%>
					<tr>							
						<td><img src='<%= color!=null?color.getPhotoUrl():p.getPhotoUrl() %>'><!--圖片--><%= p.getName() %></td>												
						<td><%=  Math.round(item.getPrice()) %></td><!--售價-->
						<td><%= item.getQuantity() %></td><!--數量-->
						<td><%= Math.round(item.getPrice() * item.getQuantity())  %></td><!--小計:售價*數量 -->						
					</tr>
					<%}%>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="3" style='text-align: right'>共購買了 <%= order.getOrderItemSet().size() %>項, 
													<%= order.getTotalQuantity() %>件 商品</td>
						<td colspan="3" style='text-align: right'>總金額 <%= order.getTotalAmount() %> 元</td>
					</tr>
					<tr><td colspan='6' style='border-right: none;text-align: center;'>
						<input type='button' value='回歷史訂單' onclick='location.href="orderhistory.jsp"'>												
						</td>	
					</tr>
				</tfoot>
			</table>
			<% }%>
		<% }else{ %>
			<p>查無此訂單(orderId=<%= orderId %>)</p>
		<% } %>
		
		</article>		
		<%@ include file="/subviews/footer.jsp"%>
	</header>
</body>
</html>