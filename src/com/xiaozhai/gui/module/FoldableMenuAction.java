package com.xiaozhai.gui.module;

import java.awt.event.*;
import javax.swing.*;

/*
 * 这个类是大菜单action
 */
public class FoldableMenuAction extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent e) {
        //将菜单面板和菜单项目获取
        FoldableMenuBar menuBar = FoldableMenuBar.getFoldableMenuBar();
        FoldableMenuItem menuItem = FoldableMenuItem.getFoldableMenuItem();

        //删除按钮
        for (JButton button : menuBar.temp) {
            menuBar.remove(button);
        }
        //清空temp
        menuBar.temp.clear();

        //将大菜单添加进去
        menuBar.temp.addAll(menuBar.buttons);
        if (e.getSource() == menuBar.BookManage){
            //为图书管理添加菜单项
            menuBar.temp.add(1,menuItem.queryBook);
            menuBar.temp.add(2,menuItem.returnBook);
            menuBar.temp.add(3,menuItem.addBook);
        }
        if (e.getSource() == menuBar.libraryCard){
            //为借书卡管理添加菜单项
            menuBar.temp.add(2,menuItem.enrollCard);
            menuBar.temp.add(3,menuItem.delCard);
        }
        if(e.getSource() == menuBar.library){
            //为图书馆管理添加菜单项
            menuBar.temp.add(3,menuItem.libraryInfo);

        }
        if(e.getSource() == menuBar.query){
            //为查询管理添加菜单项lendQuery
            menuBar.temp.add(4,menuItem.lendQuery);
        }
        if(e.getSource() == menuBar.system){
            ////为系统设置添加菜单项
            menuBar.temp.add(5,menuItem.reviseCode);
        }
        //生成新菜单
        int i=0;
        for (JButton button: menuBar.temp) {
            menuBar.add(button,i++);
        }
        //更新窗口
        menuBar.updateUI();
    }

}