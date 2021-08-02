<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href='css/dl-allcss.css' type='text/css'>
<link rel="stylesheet" href='css/dl-main.css' type='text/css'>
<head>
<style>
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
<script type="text/javascript">
	function chooseBrand() {
		alert("selected brand is" + document.getElementById("brand").value);
		if (document.getElementById("brand").value == "All") {
			document.location.href = "products";
		} else {
			document.location.href = "productbrand?brand="
					+ document.getElementById("brand").value;
		}
	}
</script>
</head>
<body>
	<header>
		<jsp:include page="/header">
			<jsp:param value="產品資訊" name="subtitle" />
		</jsp:include>
	</header>
	<div class="productcase">
		<div class="productWhere">
			<article style="overflow: hidden;">
				<select id="brand" onchange="chooseBrand()">

					<option value="All" <c:if test="${brand eq 'All'}">selected</c:if>>All</option>

					<option value="Faith"
						<c:if test="${brand eq 'Faith'}">selected</c:if>>Faith</option>

					<option value="Taylor"
						<c:if test="${brand eq 'Taylor'}">selected</c:if>>Taylor</option>

					<option value="Martin"
						<c:if test="${brand eq 'Martin'}">selected</c:if>>Martin</option>

					<option value="Martin"
						<c:if test="${brand eq 'aNueNue'}">selected</c:if>>aNueNue</option>

					<option value="Martin"
						<c:if test="${brand eq 'Veelah'}">selected</c:if>>Veelah</option>

					<option value="Martin"
						<c:if test="${brand eq 'Trumon'}">selected</c:if>>Trumon</option>
				</select>

				<ul>
					<c:forEach items="${products}" var="prod">
						<li name="${prod.id}"><a href="productinfo?id=${prod.id}">
								<!-- 改成Prodbrand 再到ProdList 再到Prod的id --> <img
								src="${prod.photoUrl}">
						</a>
							<h2>${prod.name}</h2> <c:if test="${prod.stock == 0}">
								<h2 style="color: red;">已售完</h2>
							</c:if> <c:if test="${prod.stock >= 1}">
								<h2>庫存:${prod.stock}</h2>
							</c:if></li>
					</c:forEach>
				</ul>
			</article>
		</div>
	</div>
</body>
