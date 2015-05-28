package com.pzy.action.admin;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.pzy.entity.Apply;
import com.pzy.entity.Project;
import com.pzy.service.ApplyService;
import com.pzy.service.ProjectService;

@Namespace("/admin/approve")
@ParentPackage("json-default") 
public class ApproveAction extends PageAction {
	private Long id;
	private List<Project> projects;
	@Autowired
	private ProjectService projectService;
	private Project project;
	private List<Apply> applys;
	@Autowired
	private ApplyService applyService;
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/approve/index.jsp") }) 
    public String index(){
		projects=this.projectService.findAll();
		return SUCCESS;
    }
	@Action(value = "list", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String list() {
		int pageNumber = (int) (this.getIDisplayStart() / this.getIDisplayLength()) + 1;
		int pageSize =  this.getIDisplayLength();
		Page<Apply> list = applyService.findAllForApprove(pageNumber, pageSize,project);
		this.getResultMap().put("aaData", list.getContent());
		this.getResultMap().put("iTotalRecords", list.getTotalElements());
		this.getResultMap().put("iTotalDisplayRecords", list.getTotalElements());
		this.getResultMap().put("sEcho", getSEcho());
		return SUCCESS;
	}
	@Action(value = "pass", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String pass() {
		Apply apply = applyService.find(id);
		apply.setState("申请通过");
		applyService.save(apply);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "审核通过成功");
		return SUCCESS;
	}
	@Action(value = "notpass", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String notpass() {
		Apply apply = applyService.find(id);
		apply.setState("申请退回");
		applyService.save(apply);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "审核通过成功");
		return SUCCESS;
	}
	public List<Apply> getApplys() {
		return applys;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
}
