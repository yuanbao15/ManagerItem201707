package cn.yuanbao.web.formbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CustomerFormBean implements Serializable {
	private String id;
	private String name;
	private String gender;
	private String birthday;	
	private String telephone;
	private String email;
	private String[] hobby;
	private String type;
	private String description;
	
	//存储异常错误
	private Map<String,String> errors = new HashMap<String,String>();
	
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
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
	
	public boolean validate(){
		//省略判断的过程
		return true;
	}
	
}
