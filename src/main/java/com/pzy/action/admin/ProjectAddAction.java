package com.pzy.action.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.pzy.entity.Project;
import com.pzy.service.ProjectService;

@Namespace("/admin/projectAdd")
@ParentPackage("json-default") 
public class ProjectAddAction extends PageAction {
	
	private Project project;
	 /**上传的文件*/
    private File filePath;  
    /**上传的文件ContentType*/
    private String filePathContentType;  
	/**上传的文件名*/
    private String filePathFileName; 
    
    @Autowired
    private ProjectService projectService;
	@Action(value = "doUpload", results = { @Result(name = "success", location = "/WEB-INF/views/admin/projectadd/index.jsp") })  
	public String doUpload(){
		project.setCreateDate(new Date(System.currentTimeMillis()));
		projectService.save(project);
		/**文件上传逻辑*/
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload");
		System.out.println(realpath);
		File savefile = new File(new File(realpath), this.filePathFileName);
         try {
			FileUtils.copyFile(filePath, savefile);
			project.setFilePath(filePathFileName);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
         projectService.save(project);
        this.setTip("上传成功");
		return SUCCESS;
	}
	 public Project getProject() {
			return project;
		}
		public void setProject(Project project) {
			this.project = project;
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
