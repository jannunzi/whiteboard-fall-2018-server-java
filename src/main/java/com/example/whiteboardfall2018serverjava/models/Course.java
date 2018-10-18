package com.example.whiteboardfall2018serverjava.models;

public class Course {
	  private int id = (int)(Math.random() * Integer.MAX_VALUE);
	  private String title;
	  public Course(String title) {
		  this.title = title;
	  }
	public Course(int i, String string) {
		id = i; title = string;
	}
	public Course() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	  
}
