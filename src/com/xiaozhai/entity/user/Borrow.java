package com.xiaozhai.entity.user;

public class Borrow {
    //借阅id
    private int id;
    //书名
    private String bookName;
    //数量
    private int nums;

    public Borrow() {}
    public Borrow(int id, String bookName, int nums) {
        this.id = id;
        this.bookName = bookName;
        this.nums = nums;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public int getNums() {
        return nums;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", nums=" + nums +
                '}';
    }
}
