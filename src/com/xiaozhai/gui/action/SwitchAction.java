package com.xiaozhai.gui.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.xiaozhai.gui.module.*;
import com.xiaozhai.gui.pane.*;

import javax.swing.*;

/*
 * 这个类是小菜单的监听事件
 */
public class SwitchAction extends MouseAdapter {
    private JPanel pane;
    @Override
    public void mouseClicked(MouseEvent e) {

        FoldableMenuItem menuItem = FoldableMenuItem.getFoldableMenuItem();

        //将原有的面板删掉
        MainPanel.getMainPanel().removeAll();
        //接下来根据按钮事件来添加功能面板
        if (e.getSource() == menuItem.addBook){
            pane = AddBookPanel.getAddBookPanel();//添加图书
        }
        if (e.getSource() == menuItem.returnBook){
            pane = ReturnBookPanel.getReturnBookPanel();//归还图书
        }
        if (e.getSource() == menuItem.queryBook){
            pane = QueryBookPanel.getQueryBookPanel();//查询图书
        }
        if (e.getSource() == menuItem.enrollCard){
            pane = EnrollCardPanel.getEnrollCardPanel();//用户注册
        }
        if (e.getSource() == menuItem.delCard){
            pane = RemoveCardPanel.getInstance();//删除用户
        }
        if (e.getSource() == menuItem.libraryInfo){
            pane = LibraryInfoPanel.getInstance();//制作信息
        }
        if (e.getSource() == menuItem.lendQuery){
            pane = EventPanel.getEventPanel();//所有事件
        }
        if (e.getSource() == menuItem.reviseCode){
            pane=ReviseCodePanel.getReviseCodePane();//修改密码
        }
        //将新panel添加近MainPanel里
        MainPanel.getMainPanel().add(pane);
        //更新界面
        MainPanel.getMainPanel().updateUI();
    }
}
