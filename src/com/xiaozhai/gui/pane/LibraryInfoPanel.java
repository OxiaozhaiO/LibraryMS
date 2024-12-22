package com.xiaozhai.gui.pane;

import javax.swing.*;
import java.awt.*;

public class LibraryInfoPanel extends JPanel {
    private static LibraryInfoPanel instance = new LibraryInfoPanel();
    public static LibraryInfoPanel getInstance() {
        return instance;
    }
    private LibraryInfoPanel() {
        this.setLayout(new GridLayout());
        this.addBackground();
    }
    private void addBackground(){
        ImageIcon icon = new ImageIcon("./src/img/bg2.png"); //背景图片
        Image img = icon.getImage().getScaledInstance(650, 500, Image.SCALE_FAST); // 图像缩放为适合Frame大小
        JLabel jlabel = new JLabel(new ImageIcon(img));
        jlabel.setBounds(0, 0, 650, 450);
        this.add(jlabel);
    }
}
