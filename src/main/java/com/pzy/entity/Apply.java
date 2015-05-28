package com.pzy.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "t_aopply")
public class Apply {
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 @ManyToOne(fetch = FetchType.EAGER)
	 private Project project;
	 @ManyToOne(fetch = FetchType.EAGER)
	 private User user;
	 /**申请理由*/
	 private String remark;
	 private Date createDate;
	 private String state;
	 private  Date checkDate;
	 private String checkRemark;
	 private String checkFilePath;
	 public String getCheckFilePath() {
		return checkFilePath;
	}
	public void setCheckFilePath(String checkFilePath) {
		this.checkFilePath = checkFilePath;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckRemark() {
		return checkRemark;
	}
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
