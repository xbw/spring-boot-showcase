package com.xbw.spring.boot.project.pojo;


public class Book {

  private int bookId;
  private String name;
  
  public Book(){
    
  }
  
  public Book(int bookId,String name){
    this.bookId = bookId;
    this.name = name;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  
}