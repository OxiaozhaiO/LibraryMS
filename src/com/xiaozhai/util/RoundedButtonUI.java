package com.xiaozhai.util;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RoundedButtonUI extends BasicButtonUI {
    private int arcWidth = 20;
    private int arcHeight = 20;

    public RoundedButtonUI() {}

    public RoundedButtonUI(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();
        Graphics2D g2d = (Graphics2D) g.create();

        // 根据按钮状态设置背景颜色
        if (model.isPressed()) {
            g2d.setColor(button.getBackground().darker()); // 按下时颜色变暗
        } else if (model.isRollover()) {
            g2d.setColor(button.getBackground().brighter()); // 悬停时颜色变亮
        } else if(model.isArmed()) {

        } else {
            g2d.setColor(button.getBackground()); // 默认背景颜色
        }

        // 绘制圆角背景
        g2d.fillRoundRect(0, 0, button.getWidth(), button.getHeight(), arcWidth, arcHeight);

        // 绘制按钮文字
        super.paint(g2d, c);

        g2d.dispose();
    }
}
