package cn.yuanbao.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Customer的java bean类，自己定义
 * @author yuanbao15
 * CREATE TABLE customer(
	id VARCHAR(100) PRIMARY KEY,
	NAME VARCHAR(100),
	gender VARCHAR(10),	#1 male   0 female
	birthday DATE,
	telephone VARCHAR(20),
	email VARCHAR(10),
	hobby VARCHAR(100),
	TYPE VARCHAR(40), #vip|normal
	description VARCHAR(255)
);
 */
public class Customer implements Serializable{
	
	private String id;
	private String name;
	private String gender;
	private Date birthday;	//注意是Date类，其他实现时都要是setDate
	private String telephone;
	private String email;
	private String hobby;
	private String type;
	private String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String i) {
		this.telephone = i;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
