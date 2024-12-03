package com.xiaozhai.gui.frame;

import com.xiaozhai.entity.user.User;
import com.xiaozhai.gui.module.FoldableMenuBar;
import com.xiaozhai.gui.pane.MainPanel;
import com.xiaozhai.util.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame mainFrame = new MainFrame();

    private Container c = this.getContentPane();
    public JMenu util;

    public static MainFrame getInstance(){
        return mainFrame;
    }
    private MainFrame(){
        init();
    }
    private void init(){
        String power = null;
        switch (Login.getIuser().getPower()){
            case User.USER: power = "用户";break;
            case User.MANAGER: power = "管理员";break;
        }
        String moreTitle = power+" "+Login.getIuser().getUserName();

        this.setTitle("阅览室图书管理系统 "+moreTitle);
        this.setLayout(new BorderLayout());
        this.addFoldableMenuBar();//加入菜单栏面板
        this.addContentPanel();//加入内容面板
        this.setSize(800,500);
        PanelUtil.SetCenter(this);//使此窗口于屏幕中央显现
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    //增加折叠菜单栏
    private void addFoldableMenuBar(){
        c.add(FoldableMenuBar.getFoldableMenuBar(),BorderLayout.WEST);
    }
    //增加内容面板
    private void addContentPanel(){
        c.add(MainPanel.getMainPanel());
    }

}
