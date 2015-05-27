package com.pzy.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
@Entity
@Table(name = "ace_user")
public class User implements Serializable {

     /**
     *
     */
     private static final long serialVersionUID = 1L;

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
    
     private String userName;
     private String password;
     private String school;
     private String className;
     private String sex;
     private Date birthDay;
     public Long getId() {
          return id;
     }
     public void setId(Long id) {
          this.id = id;
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
     public void setPassword(String password) {
          this.password = password;
     }
     public String getSchool() {
          return school;
     }
     public void setSchool(String school) {
          this.school = school;
     }
     public String getClassName() {
          return className;
     }
     public void setClassName(String className) {
          this.className = className;
     }
     public String getSex() {
          return sex;
     }
     public void setSex(String sex) {
          this.sex = sex;
     }
     @JSON(format="yyyy-MM-dd")
     public Date getBirthDay() {
          return birthDay;
     }
     public void setBirthDay(Date birthDay) {
          this.birthDay = birthDay;
     }

}