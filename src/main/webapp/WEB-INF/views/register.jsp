<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員註冊</title>
<link rel="stylesheet" href='css/dl-main.css' type='text/css'>
<link rel="stylesheet" href='css/dl.css' type='text/css' >
<link rel="stylesheet" href='css/dl-allcss.css' type='text/css'>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script src='https://kit.fontawesome.com/95ce010baa.js'
	crossorigin='anonymous'></script>
<script>
  $(document).ready(init);
  function init(){
	  <% if(request.getMethod().equals("POST")) {%>
	  repopulateFormData();
	  <% }%>
  }
  function repopulateFormData() {
	$("input[name='id']").val(${(param.id)});
	$("input[name='email']").val(${(param.email)});
	$("input[name='name']").val('<%=request.getParameter("name") %>');
	$("input[name='birthday']").val(${(param.birthday)});
	$("input[name='phone']").val(${(param.phone)});
	$("input[name='address']").val(${(param.address)});
	$("input[name='subscribed']").val(${(param.subscribed)});
	
	
	//TODO:birthday address phone subscribed
}
  
function refreshcaptcha() {
	var captchaImg = document.getElementById("captchaImg");
	   captchaImg.src='images/captcha.png?refresh='+new Date();
     }
</script>
<style>


</style>

</head>
<body>

<article>
 

    <form class='memberForm' method="POST" action='register.do'>
    <p style='color=red'><%
   List<String> errorMsgs=(List<String>)request.getAttribute("errors");
    out.print(errorMsgs!=null?errorMsgs:"");
     %>
     </p>
      <p>
      <label >帳號:</label>
      <input name='id' placeholder="請輸入帳號" required pattern="[A-Z][1289][0-9]{8}" 
      Value="<%out.print(request.getParameter("id"));%>">
      </p>
      <p>
      <label >E-mail:</label>
      <input type="email" name='email' placeholder="請輸入E-mail" required value="${(param.email)}">
      </p>
      <p>
      <label >密碼:</label>
      <input type="password" id='pwd1' name='pwd1' minlength="6" maxlength="20" placeholder="請輸入密碼" autocomplete="off" required>
      </p>
      <p>
      <label >再次輸入密碼:</label>
      <input type="password" id="pwd2" name='pwd2' minlength="6" maxlength="20" placeholder="請輸入密碼" autocomplete="off" required>
      </p>
      <p>
      <label >姓名:</label>
      <input type="text" name='name' minlength="2" maxlength="20" placeholder="請輸入真實姓名" required value="${param.name}">
      </p>
      
      <p>
      <label >性別:</label>
      <input type="radio" id="male" name="gender" required value="M"><label>男</label>
      <input type="radio" id="female" name="gender" required value="F"><label>女</label>
      </p>
      <p>
      <label >生日:</label>
      <input type='date'  name='birthday' id="birthday" type="date" placeholder=" 年/ 月/ 日" 
      autocomplete="off" required>
      </p>
       <p>
      <label >手機:</label>
      <input type='tel' name='phone' placeholder="請輸入手機號碼" autocomplete="off" >
      </p>
      <p>
      <label >地址:</label>
      <input name='address' placeholder="請輸入地址" autocomplete="off" >
      </p>
     
      
      <p>
      <label >訂閱</label>
      <input type='checkbox' name='subscribed' value="yes"><label>是</label>
      </p>
      <p>
      <label>驗證碼:</label><input name='captcha' required >            
      <br>
      <img id='captchaImg' src='images/captcha.png' ><input  type="button" value="更新驗證碼" onclick="refreshcaptcha()">      
      
      </p>
      <br>
      <input type="submit" value="送出" >
      <input name='lastpage' type="button" value="上一頁" onclick="location.href='login.jsp'">
       
 
    </form>
  </article>

</body>
</html>