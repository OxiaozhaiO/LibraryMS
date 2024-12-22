package com.xiaozhai.entity.user;

public interface UserPower {
    //定义级别
    final static int MANAGER=10;
    final static int USER=1;
    //获取级别
    public int getPower();
}
