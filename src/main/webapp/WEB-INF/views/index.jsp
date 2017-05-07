<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
table,tr,td,th{
	border: 1px solid black;
	border-collapse: collapse;
}
.iwith{}
.txt{}
.icourse{}
.ofc{}
.ireg{}
</style>
<script type="text/javascript" src="webjars/jquery/3.2.1/dist/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('.txt').keydown(function(e){
			if(e.keyCode == 13){
				$('#frmDetails').submit();
			}
		});
		
		$('a.icourse').click(function(){
			$('#code').val($(this).attr('codec'));
			$('a.iwith').show();
		});
		
		$('a.iwith').click(function(){
			$('#cmd').val($(this).text());
			$('#frmDetails').submit();
		});
		
		$('a.ofc').click(function(){
			$('#code').val($(this).attr('codec'));
			$('a.ireg').show();
		});
		
		$('a.ireg').click(function(){
			$('#cmd').val($(this).text());
			$('#frmDetails').submit();
		});
		
	});
</script>
<title>Course Registration</title>
</head>
<body>
<form id="frmDetails" method="post">
<input type="hidden" id="code" name="code">
<input type="hidden" id="cmd" name="cmd">
	<div style="float: left;padding-right: 20px">
		<table>
			<tr>
				<td><b>RegNo : </b></td>
				<td><input type="text" class="txt" name="regno" value="${student.regno}"></td>
			</tr>
			<tr>
				<td><b>Student Name : </b></td>
				<td>${student.studentname}</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><b>Registered Courses</b></td>
			</tr>
			<c:forEach var="reg" items="${regs}">
				<tr>
					<td>${reg.code}</td>
					<td><a href="#" class="icourse" codec="${reg.code}">${reg.coursename}</a></td>
				</tr>
			</c:forEach>
		</table>
		<a href="#" class="iwith" style="display: none">Withdraw</a>
	</div>
	<div style="float: left">
		<table>
			<tr>
				<td colspan="2" style="text-align: center;"><b>Offered Courses</b></td>
			</tr>
			<c:forEach var="off" items="${offcrs}">
				<tr>
					<td>${off.code}</td>
					<td><a href="#" class="ofc" codec="${off.code}">${off.coursename}</a></td>
				</tr>
			</c:forEach>
		</table>
		<a href="#" class="ireg" style="display: none">Register</a>
	</div>
</form>
</body>
</html>