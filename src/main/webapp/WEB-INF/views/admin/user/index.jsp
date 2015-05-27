<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace/admin.user.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/falgun/bootbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".date").datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
	        format:'yyyy-mm-dd',
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
	});
</script>
</head>
<body>
	<div class="layout">
		<!-- top -->
		<%@ include file="../top.jsp"%>
		<!-- 导航 -->
		<%@ include file="../menu.jsp"%>
		
		<input type="hidden" id="hf_id" />

		<div class="main-wrapper">
			<div class="container-fluid">
				<div class="row-fluid ">
					<div class="span12">
						<div class="content-widgets light-gray">
							<div class="widget-head  bondi-blue" >
								<h3>用户信息列表</h3>
							</div>
							<div class="box well form-inline">
								<span>姓名：</span>
								<input type="text" id="_userName" >
								<a onclick="$.adminUser.initSearchDataTable()"
									class="btn btn-info" data-loading-text="正在加载..."><i class="icon-search"></i>查询</a>
							</div>
							<div class="widget-container">
								<table class="responsive table table-striped table-bordered"
									id="dt_user_view">
									<thead>
										<tr>
											<th >id</th>
											<th >用户名</th>
											<th >密码</th>
											<th >学校</th>
											<th >班级</th>
											<th >性别</th>
											<th>生日</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../foot.jsp"%>
	</div>

	<!-- 编辑新增弹出框 -->
	<div class="modal hide fade" id="user_edit_modal">
		<div class="modal-header blue">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<label id="user_modal_header_label"></label>
		</div>
		<div class="modal-body" style="min-height: 300px;">
			<div class="row-fluid">
				<div class="span12">
					<div class="form-container grid-form form-background left-align form-horizontal">
						<form action="" method="get" id=''>
							<input type="hidden" id="userid" value="">
							<div class="control-group">
								<label for="userName" class="control-label">用户名：</label>
								<div class="controls">
									<input type="text" id="userName" placeholder="">
								</div>
							</div>
							<div class="control-group" id='control_project'>
								<label for="school" class="control-label">学校：</label>
								<div class="controls">
									<input type="text" id="school" placeholder="">
								</div>
							</div>
							<div class="control-group" id='control_projectStep'>
								<label for="className" class="control-label">班级：</label>
								<div class="controls">
									<input type="text" id="className" placeholder="">
								</div>
							</div>
							
							<div class="control-group" id='control_projectStep'>
								<label for="sex" class="control-label">性别：</label>
								<div class="controls">
									<select id='sex'>
										  <option value="男">男</option>
										  <option  value="女">女</option>
									</select>
								</div>
							</div>
							<div class="control-group" id='control_projectStep'>
								<label for="" class="control-label">密码：</label>
								<div class="controls">
									<input type="password" id="password" placeholder="">
								</div>
							</div>
							<div class="control-group" id='control_projectStep'>
								<label for="birthDay" class="control-label">生日：</label>
								<div class="controls">
									<div class=" input-append date">
										 <input id="birthDay"  type="text" value="" readonly="readonly" >
										 <span class="add-on"><i class="icon-th"></i></span>
									</div>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal-footer center" id="div_footer">
			<a class="btn btn-primary" onclick="$.adminUser.saveUser()">保存</a>
			<a href="#" class="btn" data-dismiss="modal" id="closeViewModal">关闭</a>
		</div>
	</div>
</body>
</html>