<%@page import="uuu.DL.entity.ShoppingCart"%>
<%@ page pageEncoding="UTF-8"%>

<%
 ShoppingCart cart =(ShoppingCart)session.getAttribute("cart");
if(cart!=null && cart.getTotalQuantity()>0){%>
 <%= cart.getTotalQuantity() %>
<% }%>