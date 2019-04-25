package com.cts.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class KeywordSearch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
	@NotNull
  private String keyword;
	@NotNull
  private String username;
  @CreationTimestamp
  private LocalDateTime localTime;
  
  public KeywordSearch(){
	  
  }
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getKeyword() {
	return keyword;
}
public void setKeyword(String keyword) {
	this.keyword = keyword;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public LocalDateTime getLocalTime() {
	return localTime;
}
public void setLocalTime(LocalDateTime localTime) {
	this.localTime = localTime;
}
public KeywordSearch(String keyword, String username) {

	this.keyword = keyword;
	this.username = username;
}


  
  
}
