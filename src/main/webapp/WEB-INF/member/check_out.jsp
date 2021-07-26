<%@page import="java.util.List"%>
<%@page import="uuu.DL.Service.OrderService"%>
<%@page import="uuu.DL.entity.*"%>

<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>結帳</title>

<link rel="stylesheet" href='../css/dl.css' type='text/css'>
<link rel="stylesheet" href='../css/dl-allcss.css' type='text/css'>

<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src='https://kit.fontawesome.com/95ce010baa.js'
	crossorigin='anonymous'></script>
<style>
.allcheckwrapper {
	padding-top: 15px;
	max-width: 100%;
	padding-left: 0;
	padding-right: 0;
	margin-left: 20%;
	margin-right: 20%;
	justify-content: center;
	display: flex;
}

.leftinfo {
	padding-bottom: 0 !important;
	position: relative;
	margin: 0;
	box-sizing: border-box;
	max-width: 60%;
	padding: 0 30px 30px;
	padding-bottom: 30px;
	margin-bottom: 0;
	max-width: 45%;
	display: block;
}

.rightinfo {
	padding-bottom: 0 !important;
	position: relative;
	margin: 0;
	box-sizing: border-box;
	max-width: 60%;
	padding: 0 30px 30px;
	padding-bottom: 30px;
	margin-bottom: 0;
	border-left-width: .1px;
	padding-left: 30px;
	max-width: 42%;
	display: block;
}

.col {
	position: relative;
	margin: 0;
	width: 100%;
}

.right-col {
	margin-right: auto;
	margin-left: 0;
	border: 2px solid #446084;
	padding: 15px 30px 30px;
	width: 100%;
}

.loginask {
	width: auto;
	font-size: .92em;
}

.billingwhere {
	box-sizing: border-box;
	border-top: 2px solid #ddd;
}

.billing-info {
	width: 48%;
	float: left;
	margin-bottom: .5em;
	margin-top: 0;
}

.textwhere {
	width: 100%;
}

.important {
	visibility: visible;
}

abbr[title] {
	border-bottom: none;
	text-decoration: none;
}

label {
	color: #222;
	font-weight: bold;
	font-size: .9em;
	margin-bottom: 0.4em;
}

input {
	height: 35px;
	margin-top: 0px;
	margin-bottom: 0px;
}

p {
	margin: 0;
}

textarea {
	padding-top: 0.7em;
	min-height: 120px;
	width: 100%;
}

h3 {
	font-size: 1.1em;
	overflow: hidden;
	padding-top: 10px;
	font-weight: bolder;
	text-transform: uppercase;
}

th, td {
	padding: .5em;
	padding-top: 0.5em;
	padding-bottom: 0.5em;
	padding-left: 0.5em;
	text-align: left;
	border-bottom: 1px solid #ececec;
	line-height: 1.3;
	font-size: .9em;
}

td.product-total {
	text-align: right;
}
.checkout-btn{
background-color: #d26e4b;
min-width: 100%;
margin-right: 0;
display: block;
color:white; 
height: 39px;    
}
.chooseStore{
background-color: #446084;
opacity: .6;
border: 2px solid currentColor;
line-height: 2.3em;
color: white;
}
</style>
<script>
$(init);
var storeAddr = '${param.shippingAddress}';
function init(){
	<% if(request.getMethod().equals("POST")){%>
		repopulateFormData();
	<% }else{ %>
	$("#paymentType").trigger("click");
	<% } %>	    		
}
function repopulateFormData(){
	$("#paymentType").val('${param.paymentType}');
	$("#paymentType").trigger("click");
	$("#shippingType").val('<%= request.getParameter("shippingType")%>');
	$("#shippingType").trigger("click");
	$("input[name='recipientName']").val('<%= request.getParameter("recipientName")%>');
	$("input[name='recipientEmail']").val('<%= request.getParameter("recipientEmail")%>');
	$("input[name='recipientPhone']").val('<%= request.getParameter("recipientPhone")%>');	    		
	$("input[name='shippingAddress']").val('<%= request.getParameter("shippingAddress")%>');
}
	function changeShippingType() {
		$("#shippingType option").prop("disabled", true);
		$("#shippingType option:first-child").prop("disabled", false);
		$("#shippingType option:first-child").prop("selected", true);
		if ($("#paymentType option:selected").attr("data-shipping-types")) {
			var shippingsArray = $("#paymentType option:selected").attr(
					"data-shipping-types").split(",");
			for (i = 0; i < shippingsArray.length; i++) {
				$("#shippingType option[value='" + shippingsArray[i] + "']")
						.prop("disabled", false);
				if (i == 0) {
					$("#shippingType option[value='" + shippingsArray[i] + "']")
							.prop("selected", true);
				}
			}
		}
		$("#shippingType").trigger("click");
	}	
	function changeShippingWay(){
		console.log($("#paymentType").val(),$("#shippingType").val());
		$("#shopSpan").css("display","none");
		$("#shopList").prop("disabled",true);
		$("#chooseStoreBtn").css("display","none");
		console.log($("input[name='shippingAddress'").hasClass($("#shippingType").val()));
		
		if(!$("input[name='shippingAddress'").hasClass($("#shippingType").val())){
			 $("input[name='shippingAddress'").val("");
		}			
<%-- 		if($("#shippingType").val()=="<%= ShippingType.SHOP.name() %>"){ --%>
// 			$("input[name='shippingAddress'").attr('placeholder','請選擇門市...');
// 			$("#shopSpan").css("display","initial");
// 			$("#shopList").prop("disabled",false);
// 			$("input[name='shippingAddress'").prop("readonly",true);
// 			$("#shopList").trigger("click");
<%-- 		}else if($("#shippingType").val()=="<%= PaymentType.HOME.name() %>"){ --%>
// 			$("input[name='shippingAddress'").attr('placeholder','請輸入宅配地址');
// 			$("input[name='shippingAddress'").prop("readonly",false);					
// 		}else 
			if($("#shippingType").val()=="<%= PaymentType.STORE.name() %>"){					
			$("input[name='shippingAddress'").attr('placeholder','請選擇超商...');
			$("input[name='shippingAddress'").prop("readonly",true);
			$("#chooseStoreBtn").css("display","inline");
		}
		$("input[name='shippingAddress'").removeAttr('class');
		$("input[name='shippingAddress'").addClass($("#shippingType").val());
	}	
	
	function copyMember() {
		$("input[name='recipientName']").val('${sessionScope.member.name}');
		$("input[name='recipientEmail']").val('${sessionScope.member.email}');
		$("input[name='recipientPhone']").val('${sessionScope.member.phone}');
		$("input[name='shippingAddress']")
				.val('${sessionScope.member.address}');
		if ($("input[name='recipientName']").val() == "") {
			alert("請先登入!");
		}
	}
</script>
</head>
<body>
	<header>
		<jsp:include page="/subviews/header.jsp">
			<jsp:param value="結帳" name="subtitle" />
		</jsp:include>
	</header>

	<article>
		<form action='check_out.do' method='GET'>
			<div class="allcheckwrapper">
				<% ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
				   Customer member = (Customer) session.getAttribute("member"); %>
				<div class="leftinfo col">
					<div class="loginask">
						<% if (member == null) { %>
						<div>
							老顧客?<a href="<%=request.getContextPath()%>/login.jsp">按這裡登入</a>
						</div>
						<% } %>
						<div>
							有折價券嗎?<a href="">按此輸入您的折價碼</a>
						</div>
					</div>
					<div class="billingwhere">
						<h3>帳單資訊</h3>
						
						<div>
							<p class="billing-info" style="margin-right: 4%;width: -moz-available;">
								<label>名字(<a href='javascript:copyMember()'>同訂購人</a>)
								</label> <abbr title="必要欄位">*</abbr> <span><input type="text"
									name='recipientName' required></span>
							</p>
							<p>
								<label>運送地址</label> <abbr class="important" title="必要欄位">*</abbr>
								<span><input class="textwhere" type="text"
									placeholder="門牌號碼與街道名稱" name='shippingAddress' required></span>
							</p>

							<p>
								<label>聯絡電話</label> <abbr title="必要欄位">*</abbr> <span><input
									class="textwhere" type="text" name='recipientPhone' required></span>
							</p>

							<p>
								<label>電子郵件</label> <abbr title="必要欄位">*</abbr> <span><input
									class="textwhere" type="email" name='recipientEmail' required></span>
							</p>
							<% if(member ==null){ %>
							<p>
								<label> <input type="checkbox"> <span>建立帳號?</span>
								</label>
							</p>
							<% } %>
							<p>
								<label> <input type="checkbox"> <span>運送到不同的地址?</span>
								</label>
							</p>
							<span> <textarea placeholder="您的訂單的備註，例如: 運送時的特別註記。"
									rows="2" cols="5"></textarea>
							</span>
						</div>
					</div>
				</div>
				<div class="rightinfo col">
					<div class="right-col">
						<div>
<!-- ******************************右邊訂單資訊******************************						 -->
							<h3>您的訂單</h3>
							<div>

								<table>
									<% if (cart != null && cart.size() > 0) { %>
									<thead>
										<tr>
											<th style="width: 60%;" class="product-name">商品</th>
											<th style="width: 30%;" class="product-total">小計</th>
										</tr>
									</thead>
									<tbody>
										<% for (CartItem item : cart.getCartItemSet()) {
											Product p = item.getProduct();
											Color color = item.getColor();
											int stock = (color != null ? color.getStock() : p.getStock()); %>
										<tr>
											<td class="product-name"><%=p.getName()%><td>
											<strong class="product-quantity"> &nbsp &nbsp X <%=cart.getQuantity(item)%></strong>
											</td>
											<td class="product-total">NT$<%=Math.round(p.getUnitPrice()) * cart.getQuantity(item)%>
											</td>
											<% } %>
										</tr>

									</tbody>
									<tr>
										<th>總計</th>
									
										<td data-title="總計">
										<strong> 
										<span style="text-align: right; float: right;">NT$<%=cart.getTotalAmount()%>
										</span>

										</strong>
										</td>
								</table>
								<div>
									<span> 
									<label class="fieldLabel">付款方式:</label> 									
									<select	name='paymentType' id='paymentType' required>
											<option value="" data-shipping-types="">請選擇...</option>
											<% for (PaymentType pType : PaymentType.values()) { %>
											<option value="<%=pType.name()%>"
												onclick='changeShippingType()'
												data-fee='<%=pType.getFee()%>'
												data-shipping-types="<%=pType.getShippingTypes()%>">
												<%=pType%>
											</option>
											<% } %>
									</select>									
									</span> 
									</div>
									<div>
									<span class="select-style" >
										<label class="fieldLabel">貨運方式:</label> 
											<select name='shippingType' id='shippingType' required onclick='changeShippingWay()'>
												<option value="">請選擇...</option>
												<% for (ShippingType shType : ShippingType.values()) { %>
												<option value="<%=shType.name()%>"
													data-fee='<%=shType.getFee()%>'>
													<%=shType%>
												</option>															
												<% } %>
												</select>
												
												<input class="chooseStore" type='button' value='選擇超商' id='chooseStoreBtn' style='display:none' onclick='goEzShip()'>
											</span>
											<h5>*運費將於結帳時算入喔~</h5>
									
								</div>
							</div>							 	
							<div style="text-align: center;line-height: 39px;">
								<input class="checkout-btn" type='submit' value='送出訂單'>
							</div>
						</div>
						<%
							} else {
						%>
					</div>
					<%
						}
					%>
				</div>
			</div>
		</form>
					<!-- 觸發EZShip所需要的js 和隱藏form start -->
			<script>                        	
	    	function goEzShip() {//前往EZShip選擇門市
		        //alert(url);
		        //alert($("#webPara").val());		        
	            $("input[name='recipientName']").val($("input[name='recipientName']").val().trim());
	            $("input[name='recipientEmail']").val($("input[name='recipientEmail']").val().trim());
	            $("input[name='recipientPhone']").val($("input[name='recipientPhone']").val().trim());
	            $("input[name='shippingAddress']").val($("input[name='shippingAddress']").val().trim());
	            var protocol = "<%= request.getProtocol().toLowerCase().substring(0, request.getProtocol().indexOf("/")) %>";
	            var ipAddress = "<%= java.net.InetAddress.getLocalHost().getHostAddress()%>";
	            var url = protocol + "://" + ipAddress + ":" + location.port + "<%=request.getContextPath()%>/member/ezship_callback.jsp";                   
	            $("#rtURL").val(url);
	            $("#webPara").val($("form[action='check_out.do']").serialize());
		        $("#ezForm").submit();
		    }
		    </script>
		    <form method="post" name="simulation_from" action="http://map.ezship.com.tw/ezship_map_web.jsp" id="ezForm">
		        <input type="hidden" name="suID"  value="test@vgb.com"> <!-- 業主在 ezShip 使用的帳號, 隨便寫 -->
		        <input type="hidden" name="processID" value="VGB201804230000005"> <!-- 購物網站自行產生之訂單編號, 隨便寫 -->
		        <input type="hidden" name="stCate"  value=""> <!-- 取件門市通路代號 -->
		        <input type="hidden" name="stCode"  value=""> <!-- 取件門市代號 -->
		        <input type="hidden" name="rtURL" id="rtURL" value=""> <!-- 回傳路徑及程式名稱 -->
		        <input type="hidden" id="webPara" name="webPara" value=""> <!-- 結帳網頁中cartForm中的輸入欄位資料。ezShip將原值回傳，才能帶回結帳網頁 -->
		    </form>	
		    <!-- 觸發EZShip所需要的js 和隱藏form end -->
	</article>

	<%@ include file="/subviews/footer.jsp"%>

</body>
</html>