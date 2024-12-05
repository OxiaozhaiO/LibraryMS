package com.xiaozhai.util;

import javax.swing.*;
import java.awt.*;

public class StyleUtil {
    private StyleUtil(){}
    public static void BorderStyle(JTextField ... textComponents){
        for (JTextField textComponent:textComponents) {
            textComponent.setOpaque(false);
            textComponent.setBorder(new RoundBorder());
        }
    }
    public static void BorderStyle(JButton... buttons){
        for (JButton button:buttons) {
            button.setContentAreaFilled(false); // 禁用默认背景绘制
            button.setFocusPainted(false); // 去掉焦点框
            button.setUI(new RoundedButtonUI()); // 设置圆角绘制
            button.setBorder(new RoundBorder()); // 自定义边框
            button.setBackground(new Color(255,255,255));
        }
    }
    public static void BorderStyle(boolean flag, JButton... buttons){
        for (JButton button:buttons) {
            button.setContentAreaFilled(false); // 禁用默认背景绘制
            button.setFocusPainted(false); // 去掉焦点框
            button.setUI(new RoundedButtonUI()); // 设置圆角绘制
            button.setBorder(new RoundBorder()); // 自定义边框
            button.setBackground(new Color(0xD7D7D7));
            button.setForeground(new Color(0x858585));
            button.setFont(new Font("黑体", Font.PLAIN, 16));
        }
    }
}
