<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="leftbar leftbar-close clearfix">
	<div class="admin-info clearfix">
		<div class="admin-thumb">
			<i class="icon-user"></i>
		</div>
		<div class="admin-meta">
			<ul>
				<li class="admin-username" style="margin-top: 10px;">欢迎你 ${sessionScope.user.userName}</li>
				<li><a href="${pageContext.request.contextPath}/admin/loginout">
				<i class="icon-lock"></i> 退出</a></li>
			</ul>
		</div>
	</div>

	<div class="left-nav clearfix">
		<div class="left-primary-nav">
			<ul id="myTab">
				<li  class="active"><a href="#dailyreport" class="icon-calendar" data-original-title="日报"></a></li>
			</ul>
		</div>
		<div class="responsive-leftbar">
			<i class="icon-list"></i>
		</div>
		<div class="left-secondary-nav tab-content" >
			<div class="tab-pane active dailyreport" id="dailyreport">
				<ul id="nav" class="accordion-nav" >
				 <c:if test="${sessionScope.user.role=='管理员' }">
					<li><a href="${pageContext.request.contextPath}/admin/projectadd/index"><i class="icon-pencil"></i> 项目发布</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/approve/index"><i class="icon-upload"></i> 项目审核 </a></li>
					<li><a href="${pageContext.request.contextPath}/admin/project/index"><i class="icon-zoom-out"></i>项目查询</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/check/index"><i class="icon-zoom-in"></i> 项目验收</a></li>
				</c:if>	
				<c:if test="${sessionScope.user.role=='普通用户' }">
					<li><a href="${pageContext.request.contextPath}/admin/apply/index"><i class="icon-pencil"></i> 项目申请</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/approved/index"><i class="icon-upload"></i> 项目查询 </a></li>
					<li><a href="${pageContext.request.contextPath}/admin/gocheck/index"><i class="icon-zoom-out"></i>项目验收</a></li>
				</c:if>
					
					<!--  
					<li><a href="${pageContext.request.contextPath}/dailyReport/weekReportExport"><i class="icon-download-alt"></i> 数据库备份</a></li>
					-->
				</ul>
			</div>
		</div>
	</div>
</div>