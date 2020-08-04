package dev.yusov.patterns.proxy;

public interface Bank {
    public void setUserMoney(User user, double money);
    public int getUserMoney(User user);
}
