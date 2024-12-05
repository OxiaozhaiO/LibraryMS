package com.xiaozhai.gui.pane;

import com.xiaozhai.entity.Book;
import com.xiaozhai.entity.Event;
import com.xiaozhai.entity.user.Borrow;
import com.xiaozhai.gui.frame.Login;
import com.xiaozhai.service.BookService;
import com.xiaozhai.service.BorrowService;
import com.xiaozhai.service.EventService;
import com.xiaozhai.util.StyleUtil;

import java.awt.event.*;
import java.sql.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class QueryBookPanel extends JPanel {
    public JTextField searchBox = new JTextField(15);
    public JButton search = new JButton("点击搜索");
    JTable table;
    DefaultTableModel taM;
    final Object[] titleWithCheckbox = {"选择", "书籍名称", "书籍作者", "库存数", "书籍类型", "语种", "出版社", "备注"};
    public JButton update = new JButton("跳到页面");
    public JButton previous = new JButton("上一页");
    public JTextField pageNum = new JTextField("1",5);
    public JButton next = new JButton("下一页");
    public JButton borrow = new JButton("借阅");
    private List<Book> books;

    public static QueryBookPanel getQueryBookPanel(){
        return queryBookPanel;
    }
    private static QueryBookPanel queryBookPanel = new QueryBookPanel();
    private QueryBookPanel(){
        this.setLayout(new FlowLayout());
        this.add(searchBox);
        this.add(search);
        this.addTable();

        this.setSize(632,428);
        this.setVisible(true);
        StyleUtil.BorderStyle(search, update, previous, next, borrow);
    }
    private void addTable() {
        // 初始化表格模型，默认每行的第一列为 false (未选中)
        taM = new DefaultTableModel(this.dateInfo(this.getPageDate()), titleWithCheckbox) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // 第一列是 Boolean 类型，用于显示复选框
                if (columnIndex == 0) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // 仅第一列 (复选框) 可编辑
                return column == 0;
            }
        };

        table = new JTable(taM);
        table.setPreferredScrollableViewportSize(new Dimension(600, 275));
        table.setRowHeight(25);

        // 表格容器
        JPanel dateTable = new JPanel();
        dateTable.setLayout(new GridLayout(0, 1));
        dateTable.setPreferredSize(new Dimension(600, 280));
        dateTable.add(new JScrollPane(table));

        // 添加表格及分页组件
        this.add(dateTable);
        this.add(previous);
        this.add(pageNum);
        this.add(update);
        this.add(next);
        this.add(borrow);

        this.addListener(); // 添加事件
        this.updateUI();
        StyleUtil.BorderStyle(search, update, previous, next, borrow);
   }

    /**
     * 构造带复选框的表格数据
     */
    private Object[][] dateInfo(List<Book> books) {
        Object[][] date = new Object[10][8]; // 增加一列用于复选框
        this.books = books;

        for (int i = 0; i < 10; i++) {
            if (i >= books.size()) {
                break;
            }
            Book book = books.get(i);
            date[books.size()-1-i][0] = false; // 默认复选框未选中
            date[books.size()-1-i][1] = book.getBookName();
            date[books.size()-1-i][2] = book.getAuthorName();
            date[books.size()-1-i][3] = book.getBookNumber();
            date[books.size()-1-i][4] = book.getBooksType();
            date[books.size()-1-i][5] = book.getLanguageType();
            date[books.size()-1-i][6] = book.getBookConcern();
            date[books.size()-1-i][7] = book.getRemark();
        }
        return date;
    }


    /**
     * 为当前的页面组件添加监听事件
     */
    private void addListener(){
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String search = searchBox.getText();
                taM.setRowCount(0);
                Object[][] date = QueryBookPanel.this.dateInfo(BookService.queryBook(search));
                for (int i=0;i<date.length;i++){
                    taM.addRow(date[i]);
                }
                QueryBookPanel.this.table.updateUI();
            }
        });
        previous.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int page = Integer.valueOf(pageNum.getText());
                if(page!=1){
                    page--;
                    pageNum.setText(""+page);
                    taM.setRowCount(0);
                    Object[][] date = QueryBookPanel.this.dateInfo(getPageDate());
                    for (int i=0;i<date.length;i++){
                        taM.addRow(date[i]);
                    }
                    QueryBookPanel.this.table.updateUI();
                }
            }
        });
        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int page = Integer.valueOf(pageNum.getText());
                if((page<=(BookService.getTotal()/10.0+1))&&(page!=1)){

                    taM.setRowCount(0);
                    Object[][] date = QueryBookPanel.this.dateInfo(getPageDate());
                    for (int i=0;i<date.length;i++){
                        taM.addRow(date[i]);
                    }
                    QueryBookPanel.this.table.updateUI();
                }
            }
        });
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int page = Integer.valueOf(pageNum.getText());
                if((page+1)<=(BookService.getTotal()/10.0+1)){
                    page++;
                    pageNum.setText(""+page);
                    taM.setRowCount(0);
                    Object[][] date = QueryBookPanel.this.dateInfo(getPageDate());
                    for (int i=0;i<date.length;i++){
                        taM.addRow(date[i]);
                    }
                    QueryBookPanel.this.table.updateUI();
                }
            }
        });
        borrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for(int i = 0; i < table.getRowCount(); i++){
                    Boolean isSelected = (Boolean) table.getValueAt(i, 0);
                    if (isSelected != null && isSelected) {
                        BorrowBook(i+1); //借书，并刷新表格
                    }
                }
            }
        });
    }

    /**
     * 实现分页查询
     * @return 当前页面的查询数据
     */
    private List<Book> getPageDate(){
        int page = Integer.valueOf(pageNum.getText());
        return  BookService.list((page-1)*9,page*9);
    }
    private void BorrowBook(int id){
        Book book = BookService.get(id);
        if(book==null){
            JOptionPane.showMessageDialog(new JFrame(), "这是空的");
            return;
        }
        //更新书的数量
        book.setBookNumber(book.getBookNumber()-1);
        BookService.update(book);
        //增加事件
        EventService.add(new Event(Login.getIuser().getUserName(),"借了", book.getBookName(), new Date(System.currentTimeMillis())));
        //用户表增加数据
        Borrow borrow = BorrowService.get(book.getBookName());
        if(borrow != null){
            System.out.println("same");
            BorrowService.update(book.getBookName());
        }else{
            BorrowService.add(book.getBookName());
        }
        //表格刷新
        refreshTable();
    }
    private void refreshTable() {
        taM.setRowCount(0); // 清空表格数据
        Object[][] date = dateInfo(getPageDate()); // 从数据库加载新数据
        for (Object[] row : date) {
            taM.addRow(row);
        }
        table.updateUI(); // 更新表格显示
    }

}
