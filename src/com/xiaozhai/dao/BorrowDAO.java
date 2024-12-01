package com.xiaozhai.dao;

import com.xiaozhai.entity.Event;
import com.xiaozhai.entity.user.Borrow;
import com.xiaozhai.gui.frame.Login;
import com.xiaozhai.service.EventService;
import com.xiaozhai.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {
    private String name = Login.getIuser().getUserName();
    private String tableName = name+"_borrow";
    public int getTotal() {
        int total = 0;
        String sql = "select count(*) from "+tableName;//将表改为用户_borrow
        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            s.executeUpdate(sql);
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        }catch (SQLException e) { }
        return total;
    }

    public boolean add(String book_name) {
        String sql = "insert into "+tableName+" (book_name, nums) values(?,?)";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, book_name);
            ps.setInt(2,1);
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "delete from "+tableName+" where id = ?";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Borrow get(String book_name) {
        String sql = "select * from "+tableName+" where book_name = ?";
        Borrow borrow = null;
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,book_name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                borrow = new Borrow();
                borrow.setId(rs.getInt(1));
                borrow.setBookName(rs.getString(2));
                borrow.setNums(rs.getInt(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return borrow;
    }
    public boolean addNum(String book_name){
        String sql = "update "+tableName+" set nums = ? where book_name = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, get(book_name).getNums()+1);
            ps.setString(2, book_name);
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Borrow> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Borrow> list(int start, int count) {
        List<Borrow> borrowList= new ArrayList<>();

        String sql = "select * from "+tableName+" order by id desc limit ?,? ";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Borrow borrow = new Borrow();
                borrow.setId(rs.getInt(1));
                borrow.setBookName(rs.getString(2));
                borrow.setNums(rs.getInt(3));
                borrowList.add(borrow);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return borrowList;
    }
    public boolean update(Borrow borrow) {
        String sql = "update "+tableName+" set book_name = ?, nums = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, borrow.getBookName());
            ps.setInt(2, borrow.getNums());
            ps.setInt(3, borrow.getId());
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean returnBooks(List<String> bookName) {
        for(String str:bookName){
            Borrow borrow = get(str);
            if(borrow==null){ return false; }
            if(borrow.getNums()>1) {
                borrow.setNums(borrow.getNums() - 1);
                update(borrow);
            }else {
                delete(borrow.getId());
            }
            EventService.add(new Event(Login.getIuser().getUserName(),"还了",str,new Date(System.currentTimeMillis())));
        }
        return true;
    }
}
