package com.xiaozhai.util;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RoundedButtonUI extends BasicButtonUI {
    private int arcWidth = 20;
    private int arcHeight = 20;

    public RoundedButtonUI() {}

    @Override
    public void paint(Graphics g, JComponent c) {
        //将容器c抽象成一个按钮
        AbstractButton button = (AbstractButton) c;
        //获取c的模型
        ButtonModel model = button.getModel();
        //转换为g2d
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
        //释放资源
        g2d.dispose();
    }
}
