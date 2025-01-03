package com.xiaozhai.service;

import com.xiaozhai.dao.BookDAO;
import com.xiaozhai.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final static BookDAO dao = new BookDAO();
    public static int getTotal(){
        return dao.getTotal();
    }
    //增加
    public static boolean add(Book book){
        if(dao.get(book.getBookName()) != null){
            return false;
        }
        return dao.add(book);
    }
    //修改
    public static void update(Book book){
        dao.update(book);
    }
    //删除
    public static boolean delete(int id){
        return dao.delete(id);
    }
    //获取
    public static Book get(String name){
        return dao.get(name);
    }
    //查询
    public static List<Book> list(){
        return dao.list();
    }
    //分页查询
    public static List<Book> list(int start, int count){
        return dao.list(start, count);
    }
    public static List<Book> queryBook(String Query){
        List<Book> temp = new ArrayList<>();
        temp.addAll(dao.queryFromAuthorName(Query));
        temp.addAll(dao.queryFromBookConcern(Query));
        temp.addAll(dao.queryFromBooksType(Query));
        temp.addAll(dao.queryFromId(Query));
        temp.addAll(dao.queryFromLanguageType(Query));
        temp.addAll(dao.queryFromName(Query));
        List<Book> books = new ArrayList<>();
        for (Book book : temp){
            if(!books.contains(book)){//如果不包含book的话，就添加book
                books.add(book);
            }
        }
        return books;
    }
}
