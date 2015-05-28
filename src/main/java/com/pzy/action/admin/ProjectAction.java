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

@Namespace("/admin/project")
@ParentPackage("json-default") 
public class ProjectAction extends PageAction {
	private String name;
	private List<Project> projects;
	@Autowired
	private ProjectService projectService;
	private Project project;
	@Autowired
	private ApplyService applyService;
	@Action(value = "list", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String list() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		int pageNumber = (int) (this.getIDisplayStart() / this.getIDisplayLength()) + 1;
		int pageSize =  this.getIDisplayLength();
		Page<Project> list = projectService.findAll(pageNumber, pageSize,name);
		this.getResultMap().put("aaData", list.getContent());
		this.getResultMap().put("iTotalRecords", list.getTotalElements());
		this.getResultMap().put("iTotalDisplayRecords", list.getTotalElements());
		this.getResultMap().put("sEcho", getSEcho());
		return SUCCESS;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
