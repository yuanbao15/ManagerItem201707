<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Birthday-Calendar.js"></script>
		<title>yb insert page</title>
		
		<!-- yb写css -->
		<style type="text/css">	
			#t1{
				width:800px;
				border:1px solid gray;
				border-collapse: collapse;
			}
		</style>
	</head>
	
	<body >
		<h1 align="center">添加用户信息</h1>
		<hr>	<!-- hr为创建水平线 -->
		<form action="${pageContext.request.contextPath }/servlet/Controller?op=add" method="post">
			<table id="t1" align="center">
				<tr>	<!-- 行 -->
					<td align="right" width="42.7%">姓名:</td>
					<td align="left" ><input type="text" name="name"  placeholder="请输入姓名"></td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >性别:</td>
					<td align="left">
						<input type="radio" name="gender" value="男" checked>男
						<input type="radio" name="gender" value="女">女
					</td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >生日:</td>
					<td align="left">
						<input type="text" name="birthday" onfocus="new Calendar().show(this)" readonly="readonly" value="1992-01-01">
					</td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >电话:</td>
					<td align="left"><input type="text" name="telephone"></td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >邮箱:</td>
					<td align="left"><input type="text" name="email"></td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >爱好:</td>
					<td align="left">
						<input type="checkbox" name="hobby" value="睡觉">睡觉
						<input type="checkbox" name="hobby" value="运动">运动
						<input type="checkbox" name="hobby" value="学习">学习
					</td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >类型:</td>
					<td align="left">
						<input type="radio" name="type" value="vip">vip贵宾
						<input type="radio" name="type" value="common" checked>普通用户
					</td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >描述:</td>
					<td align="left">
						<textarea rows="5" cols="16.7" name="description" >此人很懒，也没有啥想说的了。</textarea>
					</td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="center" colspan="2">
						<input type="submit" value="确定">
					</td>
				</tr>
				
			</table>
		</form>
		
	</body>
	
</html>