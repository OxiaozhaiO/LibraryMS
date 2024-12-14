package com.xiaozhai.gui.pane;

import com.xiaozhai.entity.Book;
import com.xiaozhai.service.BookService;
import com.xiaozhai.service.BorrowService;
import com.xiaozhai.util.StyleUtil;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QueryBookPanel extends JPanel {
    public JTextField searchBox = new JTextField(15);
    public JButton search = new JButton("点击搜索");
    JTable table;
    DefaultTableModel taM;
    final Object[] titleWithCheckbox = {"选择", "书籍名称", "书籍作者", "库存数", "书籍类型", "语种", "出版社", "备注"};
    public JButton borrow = new JButton("借阅");
    public JButton refresh = new JButton("刷新");
    private List<Book> books;

    private static QueryBookPanel queryBookPanel = new QueryBookPanel();

    public static QueryBookPanel getQueryBookPanel(){
        return queryBookPanel;
    }

    private QueryBookPanel(){
        this.setLayout(null);
        this.add(searchBox);
        this.add(search);
        this.add(refresh);
        this.addTable();
        this.add(borrow);

        searchBox.setBounds(10, 10, 150, 25);
        search.setBounds(170, 10, 100, 25);
        refresh.setBounds(350, 350, 100, 30);
        borrow.setBounds(200, 350, 100, 30);

        StyleUtil.BorderStyle(search, borrow, refresh);

        this.setSize(632, 428);
        this.setVisible(true);
    }

    private void addTable() {
        taM = new DefaultTableModel(this.dateInfo(BookService.list()), titleWithCheckbox) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // 第一列为复选框
                if (columnIndex == 0) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // 只有第一列（复选框）可以编辑
                return column == 0;
            }
        };

        table = new JTable(taM);
        table.setPreferredScrollableViewportSize(new Dimension(600, 275));  // 可以设置表格的初始大小
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table); // 启用滚动条
        scrollPane.setBounds(10, 45, 600, 275);
        this.add(scrollPane);

        this.addListener();
    }

    /**
     * 构建带复选框的表格数据
     */
    private Object[][] dateInfo(List<Book> books) {
        Object[][] data = new Object[books.size()][8];  // 8列（包括复选框列）
        this.books = books;

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i][0] = false; // 默认复选框未选中
            data[i][1] = book.getBookName();
            data[i][2] = book.getAuthorName();
            data[i][3] = book.getBookNumber();
            data[i][4] = book.getBooksType();
            data[i][5] = book.getLanguageType();
            data[i][6] = book.getBookConcern();
            data[i][7] = book.getRemark();
        }
        return data;
    }

    private void addListener() {
        // 搜索按钮点击事件
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchText = searchBox.getText();
                taM.setRowCount(0);  // 清空表格
                Object[][] data = QueryBookPanel.this.dateInfo(BookService.queryBook(searchText));
                for (Object[] row : data) {
                    taM.addRow(row);
                }
                table.updateUI();  // 更新表格显示
            }
        });

        // 借阅按钮点击事件
        borrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<String> selectedBooks = new ArrayList<>();
                // 遍历表格，获取所有选中的书籍
                for (int i = 0; i < table.getRowCount(); i++) {
                    Boolean isSelected = (Boolean) table.getValueAt(i, 0);
                    if (isSelected != null && isSelected) {
                        String bookName = (String) table.getValueAt(i, 1);
                        selectedBooks.add(bookName);
                    }
                }

                if (selectedBooks.isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "没有选中任何书籍！");
                    return;
                }

                // 处理借书逻辑
                for (String bookName : selectedBooks) {
                    borrowBook(bookName);
                }
            }
        });
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 刷新时重新加载所有数据
                taM.setRowCount(0);  // 清空表格
                List<Book> bookList = BookService.list();  // 获取所有书籍数据
                Object[][] data = QueryBookPanel.this.dateInfo(bookList);  // 生成新的数据模型
                for (Object[] row : data) {
                    taM.addRow(row);
                }
                table.updateUI();  // 更新表格显示
            }
        });
    }

    private void borrowBook(String bookName) {
        Book book = BookService.get(bookName);
        if (book != null && book.getBookNumber() > 0) {
            // 库存大于 0，则借书
            book.setBookNumber(book.getBookNumber() - 1);
            BookService.update(book);

            // 增加借书记录
            BorrowService.add(bookName);

            JOptionPane.showMessageDialog(this, "成功借阅: " + bookName);
            updateTableData();  // 借书后刷新表格
        } else {
            JOptionPane.showMessageDialog(this, "库存不足，无法借阅: " + bookName);
        }
    }

    // 更新表格数据
    private void updateTableData() {
        taM.setRowCount(0);  // 清空表格数据
        List<Book> pageData = BookService.list();  // 直接获取所有数据，不分页
        Object[][] data = dateInfo(pageData);
        for (Object[] row : data) {
            taM.addRow(row);
        }
        table.updateUI();  // 更新表格显示
    }
}


