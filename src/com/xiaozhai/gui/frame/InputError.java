package com.xiaozhai.gui.frame;

import com.xiaozhai.util.PanelUtil;

import javax.swing.*;
import java.awt.*;

public class InputError extends JDialog {
    JLabel str;
    public InputError(Frame index,String title,boolean modal){
        //设置父类, 三个参数分别为父窗口、标题、是否阻塞与父窗口交互
        super(index,title,modal);

        str = new JLabel("账号密码输入错误！");
        str.setFont(new Font("宋体",Font.BOLD,16));

        this.setLayout(null);
        str.setBounds(20,10,160,50);
        this.setSize(200,100);
        this.add(str);
        //设置窗口居中显示
        PanelUtil.SetCenter(this);
        //设置窗口不可改变大小
        this.setResizable(false);
        this.setVisible(true);
    }
}
