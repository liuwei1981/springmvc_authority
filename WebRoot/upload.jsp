<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图片水印DEMO</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<h4>上传图片</h4>
	<hr />
	<form action="<c:url value="/upload/watermark_upload"/>"
		enctype="multipart/form-data" method="post">

		<table align="center" width="50%">
			<tr>
				<td align="right">上传图片一：</td>
				<td align="left"><input type="file" name="file">
				</td>
			</tr>
			<tr>
				<td align="right">上传图片二：</td>
				<td align="left"><input type="file" name="file">
				</td>
			</tr>
			<tr>
				<td align="right">上传图片三：</td>
				<td align="left"><input type="file" name="file">
				</td>
			</tr>
			<tr>
				<td align="right">&nbsp;</td>
				<td align="left"><input type="submit" value="上传" />
				</td>
			</tr>
		</table>
	</form>
	<hr />
</body>
</html>