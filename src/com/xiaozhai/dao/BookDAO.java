package com.xiaozhai.dao;
import com.xiaozhai.entity.Book;
import com.xiaozhai.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    //获取书的总数
    public int getTotal() {
        int total = 0;
        String sql = "select count(*) from book";
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    //添加书籍
    public boolean add(Book book) {
        String sql = "insert into " +
                "book(BookName,AuthorName,BookNumber,BooksType,LanguageType,bookConcern,remark) " +
                "values(?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthorName());
            ps.setInt(3, book.getBookNumber());
            ps.setString(4,book.getBooksType());
            ps.setString(5,book.getLanguageType());
            ps.setString(6,book.getBookConcern());
            ps.setString(7,book.getRemark());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                book.setId(id);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //更新书籍
    public void update(Book book) {
        String sql = "update book " +
                "set BookName= ?, AuthorName = ? , BookNumber = ?, booksType=? , LanguageType = ?," +
                " bookConcern = ?, remark=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthorName());
            ps.setInt(3, book.getBookNumber());
            ps.setString(4,book.getBooksType());
            ps.setString(5,book.getLanguageType());
            ps.setString(6,book.getBookConcern());
            ps.setString(7,book.getRemark());
            ps.setInt(8,book.getId());
            //执行SQL语句
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除书籍
    public boolean delete(int id) {
        String sql = "delete from book where id = " + id;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            s.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取图书
    public Book get(String name) {
        String sql = "select * from book where bookName= ?";
        Book book = null;
        try (Connection c = DBUtil.getConnection(); PreparedStatement s = c.prepareStatement(sql);) {
            s.setString(1, name);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                book = new Book();
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setRemark(remark);
                book.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    //获取所有书籍信息
    public List<Book> list() {
        return list(0, Short.MAX_VALUE);
    }

    //获取某一范围书籍信息
    public List<Book> list(int start, int count) {
        List<Book> books = new ArrayList<>();
        String sql = "select * from book order by id desc limit ?,? ";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return books;
    }
    //按照书名查找信息
    public List<Book> queryFromName(String bookN){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where bookName like '%"+bookN+"%'";
        Book book;
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //按照id查
    public List<Book> queryFromId(String no){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where id like '%"+no+"%'";
        Book book;
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //按照作者名查
    public List<Book> queryFromAuthorName(String authorN){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where authorName like '%"+authorN+"%'";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Book book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //按照书的类型查询
    public List<Book> queryFromBooksType(String booksTy){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where booksType like '%"+booksTy+"%'";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Book book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //按照书的语言查询
    public List<Book> queryFromLanguageType(String languageTy){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where languageType like '%"+languageTy+"%'";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Book book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //按照书的出版社查
    public List<Book> queryFromBookConcern(String bookCon){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where bookConcern like '%"+bookCon+"%'";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Book book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
