<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>操作成功</title>
</head>
<body>
	<table align="center" width="95%">
		<c:forEach items="${info}" var="pic">
			<tr>
				<td width="50%"><img
					src='${pageContext.request.contextPath}${pic.imgURL}' height="350" />
				</td>
				<td width="50%"><img
					src='${pageContext.request.contextPath}${pic.markImgURL}' height="350" />
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>