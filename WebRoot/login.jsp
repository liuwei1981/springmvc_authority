<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>系统登录</title>
<script>
   var info = "${info}";
   if(info!=""){
	   alert(info);
   }
</script>
</head>
<body>
	<form action="<c:url value="/login"/>" method="post">
		<table align="center" width="50%">
			<tr>
				<td align="right">用户名：</td>
				<td align="left"><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td align="right">密码：</td>
				<td align="left"><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td align="right">&nbsp;</td>
				<td align="left"><input type="submit" value="登录" /></td>
			</tr>
		</table>
	</form>
</body>
</html>