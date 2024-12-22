/*
 * 该类之作用如下
 * 建子功能之按钮，使其有监听、透明
 */
package com.xiaozhai.gui.module;

import com.xiaozhai.util.StyleUtil;
import com.xiaozhai.gui.action.*;
import javax.swing.*;

public class FoldableMenuItem {

    //定义小菜单
    public JButton addBook = new JButton("添加图书");
    public JButton queryBook = new JButton("借阅/查询图书");
    public JButton returnBook = new JButton("归还图书");
    public JButton enrollCard = new JButton("借书证注册");
    public JButton delCard = new JButton("借书人删除");
    public JButton libraryInfo = new JButton("图书馆信息介绍");
    public JButton lendQuery = new JButton("查询已借出图书");
    public JButton reviseCode = new JButton("修改密码");

    private static FoldableMenuItem menuItem = new FoldableMenuItem();
    public static FoldableMenuItem getFoldableMenuItem(){
        return menuItem;
    }
    private FoldableMenuItem(){
        //添加样式
        this.buttonStyle();
        //给每个按钮添加事件
        this.addAction(addBook,queryBook,returnBook,enrollCard,delCard, libraryInfo,lendQuery,reviseCode);
    }
    private void buttonStyle(){
        //使按钮透明
        StyleUtil.BorderStyle(addBook,queryBook,returnBook,enrollCard,delCard, libraryInfo,lendQuery,reviseCode);
    }

    private void addAction(JButton ... buttons){
        SwitchAction switchAction =new SwitchAction();
        for ( JButton button: buttons) {
            button.addMouseListener(switchAction);
        }
    }
}
