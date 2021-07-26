<%@page import="java.time.LocalDate"%>
<%@page pageEncoding="UTF-8"%>
<style>
footer {
	clear: left;
	margin: 30px;
	background: #eaeaea;
	text-align: center;
}
</style>
<!-- fotter.jsp start -->
  <footer>
     GuitarLife@since2020~<% LocalDate.now().getYear(); %>
  </footer>
  <!-- fotter.jsp end -->