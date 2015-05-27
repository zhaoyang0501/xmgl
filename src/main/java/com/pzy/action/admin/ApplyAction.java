package com.pzy.action.admin;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.pzy.entity.Apply;
import com.pzy.entity.Project;
import com.pzy.entity.User;
import com.pzy.service.ApplyService;
import com.pzy.service.ProjectService;

@Namespace("/admin/apply")
@ParentPackage("json-default") 
public class ApplyAction extends PageAction {
	
	private List<Project> projects;
	
	private Apply apply; 
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ApplyService applyService;
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/apply/index.jsp") }) 
    public String index(){
		projects=this.projectService.findAll();
		return SUCCESS;
    }
	@Action(value = "doApply", results = { @Result(name = "success", location = "/WEB-INF/views/admin/apply/index.jsp") }) 
	public String doApply(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		apply.setCreateDate(new Date());
		apply.setUser(user);
		try {
			applyService.save(apply);
		} catch (Exception e) {
			this.setTip("已经申请过，请不要重复申请");
		}
		this.setTip("申请成功，请等待管理员审批");
		return SUCCESS;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}
