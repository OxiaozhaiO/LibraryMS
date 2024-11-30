package com.xiaozhai.service;

import com.xiaozhai.dao.BorrowDAO;
import com.xiaozhai.entity.user.Borrow;

import java.util.List;

public class BorrowService {
    private static final BorrowDAO dao = new BorrowDAO();
    public static boolean add(String book_name) {
        return dao.add(book_name);
    }
    public static boolean delete(int id) {
        return dao.delete(id);
    }
    public static Borrow get(String book_name) {
        return dao.get(book_name);
    }
    public static List<Borrow> getAll() {
        return dao.list();
    }
    public static boolean update(String book_name) {
        return dao.addNum(book_name);
    }
}
