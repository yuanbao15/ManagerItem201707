<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>	<!-- 用fun做标识符，功能方法 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Birthday-Calendar.js"></script>
		<title>yb update page</title>
		
		<!-- yb写css -->
		<style type="text/css">	
			#t1{
				width:800px;
				border:1px solid gray;
				border-collapse: collapse;
			}
		</style>
	</head>
	
<%-- 	因为要将id信息也传出去，要么在action那个传递数据的op后加id=${}，要么再下面写一个隐藏的name id value多少 --%>
	<body >
		<h1 align="center">修改用户信息</h1>
		<hr>	<!-- hr为创建水平线 -->
		<form action="${pageContext.request.contextPath }/servlet/Controller?op=update&id=${customer.id}" method="post">
			<table id="t1" align="center">
				<%-- <tr>
					<td align="left" colspan = "2"><input type="hidden" name="id" value = "${customer.id }">
					</td>
				</tr> --%>
				
				<tr>	<!-- 行 -->
					<td align="right" width="42.7%">姓名:</td>
					<td align="left"><input type="text" name="name" value="${customer.name }"></td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >性别:</td>
					<td align="left">
						<input type="radio" name="gender" value="男" ${customer.gender eq "男" ? "checked":"" }>男
						<input type="radio" name="gender" value="女" ${customer.gender eq "女" ? "checked":"" }>女
					</td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >生日:</td>
					<td align="left">
						<input type="text" name="birthday" onfocus="new Calendar().show(this)" readonly="readonly" value="${customer.birthday }">
					</td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >电话:</td>
					<td align="left"><input type="text" name="telephone" value="${customer.telephone }"></td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >邮箱:</td>
					<td align="left"><input type="text" name="email" value="${customer.email }"></td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >爱好:</td>
					<td align="left">
						<input type="checkbox" name="hobby" value="睡觉" ${fun:contains(customer.hobby,"睡觉")?"checked":"" }>睡觉
						<input type="checkbox" name="hobby" value="运动" ${fun:contains(customer.hobby,"运动")?"checked":"" }>运动
						<input type="checkbox" name="hobby" value="学习" ${fun:contains(customer.hobby,"学习")?"checked":"" }>学习
					</td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >类型:</td>
					<td align="left">
						<input type="radio" name="type" value="vip"  ${customer.type eq "vip" ? "checked":"" }>vip贵宾
						<input type="radio" name="type" value="common"  ${customer.type eq "common" ? "checked":"" }>普通用户
					</td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="right" >描述:</td>
					<td align="left">
						<textarea rows="5" cols="16.7" name="description" >${customer.description }</textarea>
					</td>
				</tr>
				<tr>	<!-- 行 -->
					<td align="center" colspan="2">
						<input type="submit" value="保存">
					</td>
				</tr>
				
			</table>
		</form>
		
	</body>
	
</html>