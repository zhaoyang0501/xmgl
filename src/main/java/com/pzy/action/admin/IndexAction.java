package com.pzy.action.admin;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pzy.entity.User;
import com.pzy.service.UserService;

@Namespace("/admin")
public class IndexAction  extends ActionSupport implements SessionAware{
	private Map<String,Object> session;
	private String userName;
	private String  password;
	private String tip;
	private User user;
	@Autowired
    private UserService  userService;

	public void setPassword(String password) {
		this.password = password;
	}
	@Action(value = "/adminindex", results = { @Result(name = "success", location = "/WEB-INF/views/admin/index.jsp") })
     public String index(){
          return SUCCESS;
     }
     @Action(value = "login", results = { @Result(name = "success", location = "/WEB-INF/views/admin/login.jsp") })
     public String login(){
          return SUCCESS;
     }
     @Action(value = "loginout", results = { @Result(name = "success", location = "/WEB-INF/views/admin/login.jsp") })
     public String loginout(){
    	 	ActionContext.getContext().getSession().remove("adminuser");
    	 	ActionContext.getContext().getSession().clear();
    	 	return SUCCESS;
     }
     @Action(value = "gologin", results = { @Result(name = "success", location = "/WEB-INF/views/admin/index.jsp"),@Result(name = "input", location = "/WEB-INF/views/admin/login.jsp") })
     public String gologin(){
    	 User loginuser=userService.login(user.getId(), user.getPassword());
	    	if(loginuser!=null){
	    		session.put("user",loginuser );
	            return SUCCESS; 
	    	}
	    	else{
	    		ActionContext.getContext().getSession().clear();
	    		this.tip="登陆失败 不存在此用户名或密码!";
	    		return LOGIN;
	    	}
         
     }
 	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
    public String getUserName() {
 		return userName;
 	}
 	public void setUserName(String userName) {
 		this.userName = userName;
 	}
 	public String getPassword() {
 		return password;
 	}
 	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}

