package com.xiaozhai.service;

import com.xiaozhai.dao.UserDAO;
import com.xiaozhai.entity.user.User;

import java.util.List;

public class UserService {
    private static UserDAO dao = new UserDAO();
    public static int getTotal(){
        return dao.getTotal();
    }
    //增加
    public static void add(User user){
        dao.add(user);
    }
    //修改
    public static void update(User user){
        dao.update(user);
    }
    //删除
    public static boolean delete(int id){
        return dao.delete(id);
    }
    public static boolean delete(String name){
        return dao.delete(name);
    }
    //获取
    public static User get(int id){
        return dao.get(id);
    }
    //查询
    public static List<User> list(){
        return dao.list();
    }
    //分页查询
    public static List<User> list(int start, int count){
        return dao.list(start, count);
    }
}
