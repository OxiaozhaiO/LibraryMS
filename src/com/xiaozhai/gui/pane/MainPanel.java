package com.xiaozhai.gui.pane;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{
    private static MainPanel mainPanel = new MainPanel();
    private Container c;

    public static MainPanel getMainPanel(){
        return mainPanel;
    }
    private MainPanel(){
//        this.setTitle("欢迎登录");
//        c = this.getContentPane();
        this.setLayout(new GridLayout());
        this.addBackground();//添加背景
        this.setVisible(true);
    }
    private void addBackground(){
        //背景图片
        ImageIcon icon = new ImageIcon("./src/img/bg.png");
        Image img = icon.getImage().getScaledInstance(650, 500, Image.SCALE_FAST); // 图像缩放为适合Frame大小
        JLabel jlabel = new JLabel(new ImageIcon(img));
        jlabel.setBounds(0, 0, 650, 450);
        this.add(jlabel);
    }

}
