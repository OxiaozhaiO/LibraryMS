package com.xiaozhai.util;

import javax.swing.*;
import java.awt.*;

public class StyleUtil {
    private StyleUtil(){}
    public static void buttonStyle(JButton... buttons){
        //是按钮变为透明
        for (JButton button:buttons) {
            button.setContentAreaFilled(false);
            button.setBorder(new RoundBorder());
        }
    }
    public static void BorderStyle(JTextField ... textComponents){
        for (JTextField textComponent:textComponents) {
            textComponent.setOpaque(false);
            textComponent.setBorder(new RoundBorder());
        }
    }
    public static void BorderStyle(JButton... buttons){
        for (JButton button:buttons) {
            button.setBackground(new Color(255, 255, 255));
            button.setBorder(new RoundBorder());
        }
    }

}
