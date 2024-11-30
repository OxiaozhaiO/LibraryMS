package com.xiaozhai.gui.module;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
/*
 * 此类设立根按钮，并将其添加监听，传进按钮list里
 */
public class FoldableMenuBar extends JPanel{
    protected JButton BookManage = new JButton("图书管理");
    protected JButton libraryCard = new JButton("借书证管理");
    protected JButton library = new JButton("图书馆管理");
    protected JButton query = new JButton("查询管理");
    protected JButton system = new JButton("系统设置");
    protected ArrayList<JButton> buttons = new ArrayList<>();
    protected ArrayList<JButton> temp=new ArrayList<>();
    private static FoldableMenuBar foldableMenuBar = new FoldableMenuBar();
    public static FoldableMenuBar getFoldableMenuBar(){
        return foldableMenuBar;
    }
    private FoldableMenuBar(){
        this.setLayout(new GridLayout(11,1));
        this.setPreferredSize(new Dimension(150, 600));

        this.add(BookManage,0); buttons.add(BookManage);
        this.add(libraryCard,1); buttons.add(libraryCard);
        this.add(library,2); buttons.add(library);
        this.add(query,3); buttons.add(query);
        this.add(system,4); buttons.add(system);
        this.addListener();
    }

    private void addListener(){
        FoldableMenuAction action = new FoldableMenuAction();
        BookManage.addMouseListener(action);
        libraryCard.addMouseListener(action);
        library.addMouseListener(action);
        query.addMouseListener(action);
        system.addMouseListener(action);
    }
}

