package com.emre.entity;

import org.springframework.data.annotation.Id;
public class Book { 
	@Id
	private Integer id;
	private String title;
	private Integer noOfPages;
	private String writer;
	private String category;


	public Book(Integer id, String title, Integer noOfPages, String writer, String category){
		this.id = id;
		this.title=title;
		this.noOfPages=noOfPages;
		this.writer = writer;
		this.category = category;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(Integer noOfPages) {
		this.noOfPages = noOfPages;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
    public String toString() {
	   return id + " - " + title + " - " + noOfPages + " - " + writer + " - " + category;
    }	
} 
