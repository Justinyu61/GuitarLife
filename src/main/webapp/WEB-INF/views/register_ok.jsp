<%@page import="uuu.DL.entity.Customer"%>
<%@page import="uuu.DL.Service.CustomerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv='refresh' content='3; url=/dl'/>
<title>登入成功</title>
<link rel="stylesheet" href='css/dl-main.css' type='text/css'>
<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
</head>
<body>
   <header>
        
		<jsp:include page="/subviews/header.jsp" >
		<jsp:param value="註冊成功" name="subtitle"/>
		</jsp:include>
	 </header>
     
  

   <article>
     <h2 style="text-align: center;margin-top: 10%;">
      <%  //後端的java...
          Customer member =(Customer)request.getAttribute("customer");
         %>  
         <%if(member!=null) {%> 
          "註冊成功 " + member.getName(), 你好
     <%}else{ %>
      "尚未註冊!"
     <%} %>
     
       </h2>
      <p style="text-align: center;margin-bottom: 20%;">3秒後將會跳回<a href='/dl'>首頁</a></p>
      
   </article>
   
   <%@ include file="/subviews/footer.jsp"%>

</body>
</html>