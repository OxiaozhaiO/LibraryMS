package com.xiaozhai.gui.pane;

import com.xiaozhai.entity.user.User;
import com.xiaozhai.gui.frame.Login;
import com.xiaozhai.service.UserService;
import com.xiaozhai.util.StyleUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RemoveCardPanel extends JPanel {

    List<User> userList = null;
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> list = null;
    JButton button = new JButton("删除");
    JButton relistButton = new JButton("刷新");
    private static RemoveCardPanel instance = new RemoveCardPanel();
    public static RemoveCardPanel getInstance() {
        return instance;
    }
    private RemoveCardPanel() {
        if(Login.getIuser().getPower() != 1) addUsers();
        this.setLayout(null);

        // 初始化按钮
        button.setBounds(400, 200, 80, 40);
        relistButton.setBounds(400, 150, 80, 40);
        this.add(button);
        this.add(relistButton);

        // 初始化列表并添加到面板
        list = new JList<>(model);
        list.setBounds(100, 100, 100, 300);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(list);
        StyleUtil.BorderStyle(relistButton, button);
        // 添加按钮事件监听器
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Login.getIuser().getPower() == 1) {
                    JOptionPane.showMessageDialog(new JFrame(), "普通用户不可操作");
                    return;
                }
                String userName = list.getSelectedValue();
                if (userName != null) {
                    System.out.println(userName);
                    UserService.delete(userName);
                    addUsers(); // 仅更新模型
                }
            }
        });

        relistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Login.getIuser().getPower() == 1) {
                    JOptionPane.showMessageDialog(new JFrame(), "普通用户不可操作");
                    return;
                }
                addUsers(); // 仅更新模型
            }
        });
    }

    public void addUsers() {
        model.clear();
        userList = UserService.list();
        for (User user : userList) {
            if (user.getPower() == 10) continue; // 只允许添加普通用户，不可显示管理员账户
            model.addElement(user.getUserName());
        }
    }
}
