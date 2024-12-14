package com.xiaozhai.gui.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.xiaozhai.gui.module.*;
import com.xiaozhai.gui.pane.*;

import javax.swing.*;

/*
 * 该类之作用如下
 * 点击按钮时，将前一panel删除，将新panel添加
 */
public class SwitchAction extends MouseAdapter {
    private JPanel pane;
    @Override
    public void mouseClicked(MouseEvent e) {
        //将原有的面板删掉
        MainPanel.getMainPanel().removeAll();
        //接下来根据按钮事件来添加功能面板
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().addBook){
            pane = AddBookPanel.getAddBookPanel();//添加图书
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().returnBook){
            pane = ReturnBookPanel.getReturnBookPanel();//归还图书
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().queryBook){
            pane = QueryBookPanel.getQueryBookPanel();//查询图书
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().enrollCard){
            pane = EnrollCardPanel.getEnrollCardPanel();//用户注册
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().delCard){
            pane = RemoveCardPanel.getInstance();//删除用户
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().libraryInfo){
            pane = LibraryInfoPanel.getInstance();//制作信息
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().lendQuery){
            pane = EventPanel.getEventPanel();//所有事件
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().reviseCode){
            pane=ReviseCodePanel.getReviseCodePane();//修改密码
        }
        MainPanel.getMainPanel().add(pane);
        MainPanel.getMainPanel().add(pane);
        MainPanel.getMainPanel().updateUI();
    }
}
