package com.xiaozhai.gui.pane;

import com.xiaozhai.entity.Event;
import com.xiaozhai.service.EventService;
import com.xiaozhai.util.StyleUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EventPanel extends JPanel {
    JButton reList = new JButton("刷新");
    JTextArea events = new JTextArea();
    JScrollPane scroll = new JScrollPane(events);
    private static EventPanel instance = new EventPanel();
    public static EventPanel getEventPanel() {
        return instance;
    }
    private EventPanel() {
        events.setEditable(false);
        events.setBounds(0,0, 450, 600);
        reList.setBounds(500, 200, 100, 50);
        this.setLayout(null);
        this.setEvents();
        this.add(events);
        this.add(reList);
        StyleUtil.BorderStyle(reList);
        reList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEvents();
            }
        });
    }
    public void setEvents() {
        events.setText("");
        List<Event> list = EventService.list();
        for(Event event : list) {
            String text = event.toString();
            events.append(text+"\n");
        }
    }
}
