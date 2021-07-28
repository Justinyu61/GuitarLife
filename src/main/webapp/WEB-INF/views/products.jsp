<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="productcase">
	<div class="productWhere">
		<article style="overflow: hidden;">

			<ul>
			<c:forEach items="${products}" var="prod">
				<li name="${prod.id}">
					<a href="productlist.jsp?id=${prod.id}"> 
						<img src="${prod.photoUrl}">
					</a>
					<h2>${prod.name}</h2>
										
					 <c:if test="${prod.stock == 0}">
					 	<h2 style ="color: red;">已售完</h2>
					 </c:if>
					 
					 <c:if test="${prod.stock >= 1}">
					 	<h2>庫存:${prod.stock}</h2>
					 </c:if>
					
				</li>
			</c:forEach>				
			</ul>
		</article>
	</div>
</div>

