<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<script type="text/javascript">
function chooseBrand(){
	alert("selected brand is" + document.getElementById("brand").value);
	
	document.location.href="productbrand?brand="+document.getElementById("brand").value;
}
</script>
</head>
<div class="productcase">
	<div class="productWhere">
		<article style="overflow: hidden;">
		  <select id="brand" onchange="chooseBrand()">
		  
		    <option value="Faith" <c:if test="${brand eq 'Faith'}">selected</c:if>>Faith</option>
		    
		    <option value="Taylor" <c:if test="${brand eq 'Taylor'}">selected</c:if>>Taylor</option>
		    
		    <option value="Martin" <c:if test="${brand eq 'Martin'}">selected</c:if>>Martin</option>
		    
		    <option value="Martin" <c:if test="${brand eq 'aNueNue'}">selected</c:if>>aNueNue</option>
		    
		    <option value="Martin" <c:if test="${brand eq 'Veelah'}">selected</c:if>>Veelah</option>
		    
		    <option value="Martin" <c:if test="${brand eq 'Trumon'}">selected</c:if>>Trumon</option>
		  </select>

			<ul>
			<c:forEach items="${products}" var="prod">
				<li name="${prod.id}">
					<a href="productinfo?id=${prod.id}"> <!-- 改成Prodbrand 再到ProdList 再到Prod的id -->
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

