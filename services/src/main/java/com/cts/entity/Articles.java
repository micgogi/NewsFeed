package com.cts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Articles {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
  private int articleId;
  private String title;
  private String description;
  private String imageURL;
  private String author;
  private String publishedAt;
  public Articles(){
	  
  }
  
  
public Articles(String title, String description, String imageURL, String author, String publishedAt) {

	this.title = title;
	this.description = description;
	this.imageURL = imageURL;
	this.author = author;
	this.publishedAt = publishedAt;
}
public int getId() {
	return articleId;
}
public void setId(int articleId) {
	this.articleId = articleId;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImageURL() {
	return imageURL;
}
public void setImageURL(String imageURL) {
	this.imageURL = imageURL;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getPublishedAt() {
	return publishedAt;
}
public void setPublishedAt(String publishedAt) {
	this.publishedAt = publishedAt;
}
  
  
  
}
