<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>	<!-- jstl,然后用c打头做标识符 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- yb显示用户信息的界面 -->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>yuanbao list.jsp</title>
		
		<!-- yb写css   //td,th是画单元格的框线  //tr:hover隔行变色                  -->
		<style type="text/css">	
			#t1{
				width:1000px;
			}
			
			#t2{
				border:1px solid gray;
				border-collapse: collapse;
				font-size:15px;
				text-align:center;
			}
			
			#t2 td,th{
				border:1px solid gray;
			}
			#t2 tr:hover{
				background-color: ffccff;
			}
		</style>
	</head>
	
	<!-- yb拷贝来的，解决全选多选，以及删除多个的问题 -->
	<script type="text/javascript">
		//yb实现全选和非全选
    	function checkAll(flag){
    		//拿到所有的记录
    		var ids = document.getElementsByName("ids") ;
    		//循环设置每一个复选框
    		for(var i = 0 ;i <ids.length ;i++){
    			ids[i].checked = flag ;	
    		}
    	}
    	
		//yb传递勾选的记录为"id1,id2..."这样子
    	function delmore(){
    		//拿到所有的记录的复选框
    		var ids = document.getElementsByName("ids") ;
    		//循环判断每一个复选框是否选中，构建id字符串
    		var s = "" ;
    		for(var i = 0 ;i<ids.length ;i++){
    			if(ids[i].checked == true){
    				//拿到此复选框的value
    				s += ids[i].value + "," ;
    			}
    		}
    		//数据传递到服务端进行删除
    		window.location = "${pageContext.request.contextPath }/servlet/Controller?op=delmore&ids=" + s ;
    	}
    </script>
	
	<body>
		<h1 align="center">元宝--通讯录管理中心</h1>
		<hr>	<!-- hr为创建水平线 -->
		<table id="t1" align="center">
			<tr>	<!-- 行 -->
				<td align="left">	<!-- td单元格 -->
					<a href = "${pageContext.request.contextPath }/add.jsp">添加</a>&nbsp;&nbsp;&nbsp;
					<a href = "javascript:delmore()">删除</a>&nbsp;&nbsp;&nbsp;		<!-- 调用js写的delmore()方法 -->
				</td>
			</tr>
			<tr>	
				<td>	
					<table width="100%" id="t2">
						<tr>
							<th nowrap><input type="checkbox" id="all" onclick="checkAll(this.checked)">全选</th>	<!-- th为加粗单元格 -->
							<th>姓名</th>
							<th>性别</th>
							<th>生日</th>
							<th>电话</th>
							<th>邮箱</th>
							<th>爱好</th>
							<th>类型</th>
							<th>描述</th>
							<th>操作</th>
						</tr>
						
						<c:choose>
							<c:when test="${empty list }">
								<tr>
									<td colspan="10" align="center">暂时没有数据</td>
								</tr>
							</c:when>	
							<c:otherwise>
								<c:forEach items="${list }" var="c" >
									<tr>
										<td><input type="checkbox" name="ids" value="${c.id }"></td>
										<td>${c.name }</td>
										<td>${c.gender }</td>
										<td>${c.birthday }</td>
										<td>${c.telephone }</td>
										<td>${c.email }</td>
										<td>${c.hobby }</td>
										<td>${c.type=="vip"? "vip贵宾":"普通用户" }</td>
										<td>${c.description }</td>
										<td>
											<a href="${pageContext.request.contextPath }/servlet/Controller?op=toupdatepage&id=${c.id}">修改</a> &nbsp;&nbsp;&nbsp;
											<a href="${pageContext.request.contextPath }/servlet/Controller?op=delete&id=${c.id}">删除</a> 
										</td>
									</tr>
									
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
				</td>
			</tr>
		</table>
	</body>
	
</html>