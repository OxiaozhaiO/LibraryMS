package com.xiaozhai.gui.pane;

import javax.swing.*;

public class LibraryInfoPanel extends JPanel {
    private static LibraryInfoPanel instance = new LibraryInfoPanel();
    public static LibraryInfoPanel getInstance() {
        return instance;
    }
    private LibraryInfoPanel() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.append("library info:\n");
    }
}
