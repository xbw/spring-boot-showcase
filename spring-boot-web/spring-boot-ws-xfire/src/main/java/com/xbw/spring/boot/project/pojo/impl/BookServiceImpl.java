package com.xbw.spring.boot.project.pojo.impl;

import com.xbw.spring.boot.project.pojo.Book;
import com.xbw.spring.boot.project.pojo.BookService;

public class BookServiceImpl implements BookService {

    public Book getBookById(int id) {
        Book book = new Book(id, "book");
        return book;
    }

}