<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/falgun/bootbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/falgun/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	function getBadRecord(){
		$.ajax({
			type : "get",
			url : $.ace.getContextPath() + "/admin/badrecord/get?id="+$("#badrecordid").val(),
			dataType : "json",
			success : function(json) {
				if(json.resultMap.state=='success'){
				 	$("#carid").val(json.resultMap.object.car.id);
					$("#address").val(json.resultMap.object.address);
					$("#state").val(json.resultMap.object.state);
					$("#type").val(json.resultMap.object.badRecordType.id);
					$("#createDate").val(json.resultMap.object.createDate);
				}else{
					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
				}
			}
		});
	}
	function cleanBadRecord(){
		$.ajax({
			type : "get",
			url : $.ace.getContextPath() + "/admin/badrecord/clean?id="+$("#badrecordid").val()+"&license="+$("#license").val(),
			dataType : "json",
			success : function(json) {
				if(json.resultMap.state=='success'){
					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
				}else{
					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
				}
			}
		});
	}
	$(document).ready(function(){
		
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
								<h3>项目申请</h3>
							</div>
							
							<div class="container">
							<c:if test="${tip!=null }">
								<div class="alert alert-info">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<i class="icon-info-sign"></i>${tip }
							</div>
							</c:if>
								<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/apply/doApply">
								  <div class="control-group">
								<label for="name" class="control-label">可申报的项目：</label>
								<div class="controls">
									<select name='apply.project.id'>
										<c:forEach items="${projects}" var="bean">
										<option value="${bean.id }">${bean.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label for="address" class="control-label">申请理由：</label>
								<div class="controls">
									 	<textarea name='apply.remark' rows="5" cols="" class='span12'></textarea>
								</div>
							</div>
								  <div class="control-group">
								    <div class="controls">
								      <button type="submit" class="btn">提交申请</button>
								    </div>
								  </div>
								</form>
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