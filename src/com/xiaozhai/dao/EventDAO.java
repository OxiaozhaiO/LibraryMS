package com.xiaozhai.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.xiaozhai.entity.Event;
import com.xiaozhai.util.DBUtil;

public class EventDAO {
    public int getTotal() {
        int total = 0;
        String sql = "select count(*) from event";
        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement()){
            s.execute(sql);
            ResultSet rs = s.getResultSet();
            while(rs.next()) {
                total = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    public void add(Event event) {
        String sql = "insert into event (username, BorR, book, time) values (?, ?, ?, ?)";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, event.getUsername());
            ps.setString(2, event.getBorR());
            ps.setString(3, event.getBook());
            ps.setDate(4, event.getTime());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean delete(int id) {
        String sql = "delete from event where id = ?";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Event get(int id) {
        Event event = new Event();
        String sql = "select * from event where id = ?";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                event.setId(rs.getInt(1));
                event.setUsername(rs.getString(2));
                event.setBorR(rs.getString(3));
                event.setBook(rs.getString(4));
                event.setTime(rs.getDate(5));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return event;
    }

    public List<Event> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Event> list(int start, int count) {
        List<Event> list = new ArrayList<Event>();
        String sql = "select * from event limit ?,?";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt(1));
                event.setUsername(rs.getString(2));
                event.setBorR(rs.getString(3));
                event.setBook(rs.getString(4));
                event.setTime(rs.getDate(5));
                list.add(event);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }
}
