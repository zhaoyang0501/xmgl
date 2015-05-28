package com.pzy.action.admin;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.pzy.entity.Apply;
import com.pzy.entity.Project;
import com.pzy.entity.User;
import com.pzy.service.ApplyService;
import com.pzy.service.ProjectService;

@Namespace("/admin/approved")
@ParentPackage("json-default") 
public class ApprovedAction extends PageAction {
	private List<Project> projects;
	private String state;
	@Autowired
	private ProjectService projectService;
	private Project project;
	private List<Apply> applys;
	@Autowired
	private ApplyService applyService;
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/approved/index.jsp") }) 
    public String index(){
		projects=this.projectService.findAll();
		return SUCCESS;
    }
	@Action(value = "list", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String list() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		int pageNumber = (int) (this.getIDisplayStart() / this.getIDisplayLength()) + 1;
		int pageSize =  this.getIDisplayLength();
		Page<Apply> list = applyService.findAll(pageNumber, pageSize,project,user,state);
		this.getResultMap().put("aaData", list.getContent());
		this.getResultMap().put("iTotalRecords", list.getTotalElements());
		this.getResultMap().put("iTotalDisplayRecords", list.getTotalElements());
		this.getResultMap().put("sEcho", getSEcho());
		return SUCCESS;
	}
	public List<Apply> getApplys() {
		return applys;
	}

	public void setApplys(List<Apply> applys) {
		this.applys = applys;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
