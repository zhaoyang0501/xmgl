<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace/admin.approved.js"></script>
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
								<h3>我申请的项目</h3>
							</div>
							<div class="box well form-inline">
								<span>项目：</span>
								<select name='project.id' id='project'>
								<option value=""></option>
										<c:forEach items="${projects}" var="bean">
										<option value="${bean.id }">${bean.name }</option>
										</c:forEach>
								</select>
								<span>项目状态：</span>
								<select name='project.state' id='state'>
									<option value=""></option>
										<option value="已申请待审核">已提交申请</option>
										<option value="申请退回">申请退回</option>
										<option value="申请通过">申请通过</option>
										<option value="已提交验收">已提交验收</option>
										<option value="验收通过">验收通过</option>
										<option value="验收不通过">验收不通过</option>
								</select>
								<a onclick="$.adminApproved.initSearchDataTable()"
									class="btn btn-info" data-loading-text="正在加载..."><i class="icon-search"></i>查询</a>
							</div>
							<div class="widget-container">
								<table class="responsive table table-striped table-bordered"
									id="dt_table_view">
									<thead>
										<tr>
											<th >id</th>
											<th >项目</th>
											<th >申请者</th>
											<th >申请日期</th>
											
											<th >申请理由</th>
											<th >状态</th>
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
</body>
</html>