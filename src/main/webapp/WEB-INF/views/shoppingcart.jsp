<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.gl.spring.entity.ShippingType"%>
<%@page import="com.gl.spring.entity.Customer"%>
<%@page import="com.gl.spring.entity.Color"%>
<%@page import="com.gl.spring.entity.Product"%>
<%@page import="com.gl.spring.entity.CartItem"%>
<%@page import="com.gl.spring.entity.ShoppingCart"%>


<div>
	<body>
		<header>
			<jsp:include page="/header">
				<jsp:param value="購物車" name="subtitle" />
			</jsp:include>
		</header>
		 
		<table class="cart-table">
			<thead>
				<tr>
					<th class="pruductName" colspan="3">商品</th>
					<th class="pruductPrice">價格</th>
					<th class="pruductqty">數量</th>
					<th class="pruductSubtotal">小計</th>
				</tr>
			</thead>
			<tbody>
			<tr>
			<td class="product-where" data-title="商品">
			<img alt="" src="">
			</td>
			<td class="product-name"></td>
			<td class="product-price" data-title="價格"></td>
			
			<td></td>
			</tbody>
			</table>
	</body>
</div>