<%@page language="java" contentType="text/html; charset=utf-8" 
    pageEncoding="utf-8"%> 

<html> 
<head> 
<title>Insert title here</title> 
</head> 

<link rel="stylesheet" type="text/css" href="jquery-ui/jquery-ui.theme.min.css" /> 
<link rel="stylesheet" type="text/css" href="jquery-ui/jquery-ui.min.css" /> 
<link rel="stylesheet" type="text/css" href="jquery-ui/jquery-ui.structure.min.css" /> 

<script type="text/javascript" src="jquery-ui/jquery-ui.min.js"></script> 
<script type="text/javascript" src="jquery-ui/external/jquery/jquery.js"></script> 
<script >
$(function(){
	
	alert("查询成功");
	//$(document.body).css( "background", "black" );
	
	
});

</script>
<body>
		<div class="content1">
			<div>
				<a href="#"> 新增 </a>
			</div>
			
			<div>
				<form action="/student/query" method="post" id="search_form">
					<input type="hidden" id="search_form_currpage" name="currpage" value="${page.pageNumber}">
					年级: <input type="text" name="student.nj" value="1111" />
					学期: <input type="text" name="student.xq" value="2222" />
					<input value="查询" type="submit">
				</form>
				<table>
					<thead>
						<tr>
							<th width="10%">ID</th>
							<th width="20%">姓名</th>
							<th width="20%">年龄</th>
							<th width="20%">地址</th>
							
							<th width="20%">操作</th>
						</tr>
					</thead>
					<tbody>
					
						<tr>
							<td>$onelist.XH</td>
							<td>$onelist.nj</td>
							<td>$onelist.xq</td>
							<td>$onelist.km</td>
					
							<td>$onelist.fs</td>
							<td>
								<a href="/student/delete/$onelist.id">删除</a>&nbsp;&nbsp;
								<a href="#">修改</a>
							</td>
						</tr>
						
					</tbody>
				</table>
				
			</div>
		</div>
	</body>
</html>