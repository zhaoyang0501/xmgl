package com.pzy.action.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.pzy.entity.Apply;
import com.pzy.entity.User;
import com.pzy.service.ApplyService;

@Namespace("/admin/gocheck")
@ParentPackage("json-default") 
public class GocheckAction extends PageAction {
	private List<Apply> applys;
	
	private Apply apply; 
	
	 /**上传的文件*/
    private File filePath;  
    /**上传的文件ContentType*/
    private String filePathContentType;  
	/**上传的文件名*/
    private String filePathFileName; 
	@Autowired
	private ApplyService applyService;
	public List<Apply> getApplys() {
		return applys;
	}
	public void setApplys(List<Apply> applys) {
		this.applys = applys;
	}
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/gocheck/index.jsp") }) 
    public String index(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		applys=applyService.findAllForCheck(user,"申请通过");
		return SUCCESS;
    }
	@Action(value = "doCheck", results = { @Result(name = "success", location = "/WEB-INF/views/admin/gocheck/index.jsp") }) 
	public String doCheck(){
		Apply newApply=applyService.find(apply.getId());
		newApply.setCheckDate(new Date());
		newApply.setCheckRemark(apply.getCheckRemark());
		newApply.setCheckFilePath(apply.getCheckFilePath());
		newApply.setState("已提交验收");
		/**文件上传逻辑*/
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		System.out.println(realpath);
		File savefile = new File(new File(realpath), this.filePathFileName);
         try {
			FileUtils.copyFile(filePath, savefile);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
        newApply.setCheckFilePath(filePathFileName);
		try {
			applyService.save(newApply);
			this.setTip("验收申请成功，请等待管理员审批");
		} catch (Exception e) {
			this.setTip("已经验收申请，请不要重复申请");
		}
		
		return SUCCESS;
	}
	
	public Apply getApply() {
		return apply;
	}
	public void setApply(Apply apply) {
		this.apply = apply;
	}
	public File getFilePath() {
		return filePath;
	}
	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}
	public String getFilePathContentType() {
		return filePathContentType;
	}
	public void setFilePathContentType(String filePathContentType) {
		this.filePathContentType = filePathContentType;
	}
	public String getFilePathFileName() {
		return filePathFileName;
	}
	public void setFilePathFileName(String filePathFileName) {
		this.filePathFileName = filePathFileName;
	}
}
