package com.xiaozhai.service;

import com.xiaozhai.dao.EventDAO;
import com.xiaozhai.entity.Event;

import java.util.List;

public class EventService {
    private final static EventDAO dao = new EventDAO();
    public static int getTotal() {
        return dao.getTotal();
    }
    public static void add(Event event) {
        dao.add(event);
    }
    public static boolean delete(int id) {
        return dao.delete(id);
    }
    public static Event get(int id) {
        return dao.get(id);
    }
    public static List<Event> list() {
        return dao.list();
    }
    public static List<Event> list(int offset, int limit) {
        return dao.list(offset, limit);
    }
}
