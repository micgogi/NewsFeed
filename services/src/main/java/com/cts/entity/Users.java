package com.cts.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userId;
	
	  @NotBlank(message="username cannot be empty")
	    @Size(min=2, max = 20,message="username should have atleast 2 character and maximum 20 characters")
    private String username;

	    @NaturalId
	    @NotBlank()
	    @Size(max = 50)
	    @Email()
    private String email;
	    @NotBlank()
	    @Size(min=6,message="password should be minimum 6 character")
    private String password;
	 @JsonProperty
	 @NotNull
    private boolean status=true;
   
	int role;
	@JsonIgnore
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "user_articles", 
	    	joinColumns = @JoinColumn(name = "user_id"), 
	    	inverseJoinColumns = @JoinColumn(name = "articles_id"))
	List<Articles> articleList = new ArrayList<Articles>();
	public Users(){
		
	}
	
	public Users(String username, String email, String password, int role,boolean status) {
		System.out.println("in cons");
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.status=status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", status=" + status + ", role=" + role + "]";
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return userId;
	}
	public void setId(int userId) {
		this.userId = userId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		System.out.println("set email");
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Articles> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<Articles> articleList) {
		this.articleList = articleList;
	}
	
    @Override
    public boolean equals(Object object){
    	Users user=(Users) object;
    	if(this.getEmail().equals(user.getEmail())){
    		return true;
    	}
    	if(this.getUsername().equals(user.getUsername())){
    		return true;
    	}
    	return false;
    }
}
