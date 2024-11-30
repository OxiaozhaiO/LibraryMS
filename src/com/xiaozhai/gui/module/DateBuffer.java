package com.xiaozhai.gui.module;

import com.xiaozhai.entity.Book;
import com.xiaozhai.service.BookService;

import java.util.List;
import java.util.Vector;

public class DateBuffer {
    private List<Book> books = new Vector<>();
    private DateBuffer(){
        addEle(BookService.list());
    }
    private static DateBuffer dateBuffer = new DateBuffer();
    public static DateBuffer getDateBuffer(){
        return dateBuffer;
    }
    private void addEle(List<Book> books){
        this.books.addAll(books);
    }
    public List<Book> getBooks(){
        return this.books;
    }
}
