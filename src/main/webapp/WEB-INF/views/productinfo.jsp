<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Hello</h2>
<div>
<ul>
<li name="${prodInfo.name}">
<img src="${prodInfo.photoUrl}">
<h2>${prodInfo.stock}</h2>
<h2>${prodInfo.unitPrice}</h2>
</li> 
</ul>
</div>


 