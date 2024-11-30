package com.xiaozhai.startup;

import com.xiaozhai.gui.frame.Login;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Bootstartup {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                Login.getInstance();
            }
        });
    }
}
