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
        MainPanel.getMainPanel().getContentPane().removeAll();
        //接下来根据按钮事件来添加功能面板
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().addBook){
            MainPanel.getMainPanel().setTitle("正在添加图书...");
            pane = AddBookPanel.getAddBookPanel();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().returnBook){
            MainPanel.getMainPanel().setTitle("归还图书");
            pane = ReturnBookPanel.getReturnBookPanel();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().queryBook){
            MainPanel.getMainPanel().setTitle("查询图书");
            pane = QueryBookPanel.getQueryBookPanel();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().enrollCard){
            MainPanel.getMainPanel().setTitle("用户注册");
            pane = EnrollCardPanel.getEnrollCardPanel();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().delCard){
            MainPanel.getMainPanel().setTitle("删除用户(只能管理员操作)");
            pane = RemoveCardPanel.getInstance();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().libraryInfo){
            MainPanel.getMainPanel().setTitle("制作信息");
            pane = LibraryInfoPanel.getInstance();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().lendQuery){
            MainPanel.getMainPanel().setTitle("所有事件");
            pane = EventPanel.getEventPanel();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().reviseCode){
            MainPanel.getMainPanel().setTitle("修改密码");
            pane=ReviseCodePanel.getReviseCodePane();
        }
        MainPanel.getMainPanel().getLayeredPane().add(pane);
        MainPanel.getMainPanel().getContentPane().add(pane);
        MainPanel.getMainPanel().updateUI();
    }
}
