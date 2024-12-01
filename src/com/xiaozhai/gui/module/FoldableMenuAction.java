package com.xiaozhai.gui.module;

import java.awt.event.*;
import javax.swing.*;

/*
 * 该类之作用如下
 * 当点击根按钮时，将之前显示出来的自按钮移除
 * 将新子按钮显示出来
 */
public class FoldableMenuAction extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent e) {

        for( JButton button: FoldableMenuBar.getFoldableMenuBar().temp){
            FoldableMenuBar.getFoldableMenuBar().remove(button);
        }
        //将菜单清空
        FoldableMenuBar.getFoldableMenuBar().temp.clear();

        //复原菜单
        FoldableMenuBar.getFoldableMenuBar().temp.addAll(FoldableMenuBar.getFoldableMenuBar().buttons);
        if (e.getSource() == FoldableMenuBar.getFoldableMenuBar().BookManage){
            //为图书管理添加菜单项
            FoldableMenuBar.getFoldableMenuBar().temp.add(1,FoldableMenuItem.getFoldableMenuItem().queryBook);
            FoldableMenuBar.getFoldableMenuBar().temp.add(2,FoldableMenuItem.getFoldableMenuItem().returnBook);
            FoldableMenuBar.getFoldableMenuBar().temp.add(3,FoldableMenuItem.getFoldableMenuItem().addBook);
        }
        if (e.getSource() == FoldableMenuBar.getFoldableMenuBar().libraryCard){
            //为借书卡管理添加菜单项
            FoldableMenuBar.getFoldableMenuBar().temp.add(2,FoldableMenuItem.getFoldableMenuItem().enrollCard);
            FoldableMenuBar.getFoldableMenuBar().temp.add(3,FoldableMenuItem.getFoldableMenuItem().delCard);
        }
        if(e.getSource() == FoldableMenuBar.getFoldableMenuBar().library){
            //为图书馆管理添加菜单项
            FoldableMenuBar.getFoldableMenuBar().temp.add(3,FoldableMenuItem.getFoldableMenuItem().libraryInfo);

        }
        if(e.getSource() == FoldableMenuBar.getFoldableMenuBar().query){
            //为查询管理添加菜单项lendQuery
            FoldableMenuBar.getFoldableMenuBar().temp.add(4,FoldableMenuItem.getFoldableMenuItem().lendQuery);
        }
        if(e.getSource() == FoldableMenuBar.getFoldableMenuBar().system){
            ////为系统设置添加菜单项
            FoldableMenuBar.getFoldableMenuBar().temp.add(5,FoldableMenuItem.getFoldableMenuItem().reviseCode);
        }
        //生成新菜单
        int i=0;
        for (JButton button: FoldableMenuBar.getFoldableMenuBar().temp) {
            FoldableMenuBar.getFoldableMenuBar().add(button,i++);
        }
        //更新窗口
        FoldableMenuBar.getFoldableMenuBar().updateUI();
    }

}