package com.xiaozhai.util;

import com.xiaozhai.entity.user.User;
import com.xiaozhai.service.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class PanelUtil {
    //设置窗口居中显示
    public static void SetCenter(Window window){
        //定义一个工具包
        Toolkit kit = Toolkit.getDefaultToolkit();
        //得到屏幕的大小
        Dimension screenSize = kit.getScreenSize();
        //得到屏幕的宽和高
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        //获得当前的窗口的宽和高
        int windowWidth = window.getWidth();
        int windowHeight = window.getHeight();

        //设置窗口的位置，使它居中
        window.setLocation((screenWidth-windowWidth)/2 , (screenHeight-windowHeight)/2);
    }

    // 判断输入框是否为空
    public static boolean isNull(JTextField input){
        return input.getText().equals("");
    }

    // 通过数据库来验证输入的用户信息
    public static User getThisUser(String userName,String password){
        List<User> users = UserService.list();
        User iUser=null;
        for (User user: users){
            if (userName.equals(user.getUserName())
                    &&password.equals(user.getPassword())){
                iUser = user;
                break;
            }
        }
        return iUser;
    }

    //判断该用户名是否重名
    public static boolean isSameName(String userName){
        List<User> users = UserService.list();
        for(User user:users){
            if(userName.equals(user.getUserName())){
                return true;
            }
        }
        return false;
    }
}