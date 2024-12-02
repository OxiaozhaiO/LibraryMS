package com.xiaozhai.gui.pane;


import com.xiaozhai.entity.user.Borrow;
import com.xiaozhai.service.BorrowService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookPanel extends JPanel {

    private static ReturnBookPanel instance = new ReturnBookPanel();

    public static ReturnBookPanel getReturnBookPanel() {
        return instance;
    }

    private JTable table;
    private DefaultTableModel tableModel;
    private List<Boolean> selectedBooks; // 记录复选框的状态

    private ReturnBookPanel() {
        this.setLayout(null);
        // 初始化数据
        selectedBooks = new ArrayList<>();

        // 创建表格
        tableModel = new DefaultTableModel(new String[]{"选择", "书名", "所借数量"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // 第 0 列为 Boolean 类型，用于显示复选框
                if (columnIndex == 0) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // 仅允许修改复选框列
                return column == 0;
            }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        // 添加还书按钮
        JButton returnButton = new JButton("还书"), reButton = new JButton("刷新");
        returnButton.addActionListener(e -> handleReturnBooks());
        reButton.addActionListener(e -> handleReButton());
        returnButton.setBounds(200,420, 100, 30);
        reButton.setBounds(310,420, 100, 30);
        scrollPane.setBounds(0,0, 700,400);
        this.add(scrollPane);
        this.add(returnButton);
        this.add(reButton);

        // 加载数据
        loadData();
    }

    private void loadData() {
        List<Borrow> books = BorrowService.getAll();

        // 清空现有数据
        tableModel.setRowCount(0);
        selectedBooks.clear();

        // 添加新数据
        for (int i = books.size()-1; i >= 0; i--) {
            tableModel.addRow(new Object[]{false, books.get(i).getBookName(),books.get(i).getNums()});
            selectedBooks.add(false); // 初始化为未选中
        }
    }

    private void handleReturnBooks() {
        List<String> selectedBookNames = new ArrayList<>();

        // 遍历表格数据，收集选中的书籍
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tableModel.getValueAt(i, 0);
            if (isSelected) {
                String bookName = (String) tableModel.getValueAt(i, 1);
                selectedBookNames.add(bookName);
            }
        }
        if (selectedBookNames.isEmpty()) {
            JOptionPane.showMessageDialog(this, "没有选中任何书");
            return;
        }

        // 调用服务层执行还书操作（假设 BorrowService 提供 returnBooks 方法）
        boolean success = BorrowService.returnBooks(selectedBookNames);

        if (success) {
            JOptionPane.showMessageDialog(this, "归还成功: " + selectedBookNames);
            // 刷新列表
            loadData();
        } else {
            JOptionPane.showMessageDialog(this, "归还失败，请重试！");
        }
    }
    private void handleReButton() {
        loadData();
    }
}
