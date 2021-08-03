<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<body>

<div>
訂單編號:${orderId.id},訂購人:${order.member.name}<br>
訂單日期時間:${order.CreatDate},${order.CreatTime}<br>
處理狀態:${order.Status}<br>
付款方式:${order.PaymentType.SPName}
手續費:<C:if ${order.PaymentFee > 0}>${order.PaymentFee}</C:if>
貨運方式:${order.ShippingType }
</div>
<fieldset>
<legend>收件人</legend>姓名: ${order.RecipientName},
E-mail:${order.RecipientEmail},電話:${order.RecipientPhone}<br>
收件地址:${order.RecipientAddress }
</fieldset>

</body>