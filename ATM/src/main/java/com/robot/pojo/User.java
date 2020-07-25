package com.robot.pojo;

/**
 * 用户类。
 *
 * @author 张宝旭
 */
public class User {
    /**
     * 银行卡号
     */
    private String cardNo;
    /**
     * 银行卡密码
     */
    private String password;
    /**
     * 身份证号
     */
    private String  identity;
    /**
     * 姓名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 余额
     */
    private double balance;

    public User() {
    }

    public User(String cardNo, String password, String identity, String username, String phone, double balance) {
        this.cardNo = cardNo;
        this.password = password;
        this.identity = identity;
        this.username = username;
        this.phone = phone;
        this.balance = balance;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "cardNo='" + cardNo + '\'' +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", balance=" + balance +
                '}';
    }
}
