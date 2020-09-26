package com.robot.bank;

import com.robot.check.CheckInputFormat;
import com.robot.dao.UserMapper;
import com.robot.pojo.User;
import com.robot.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 模拟银行ATM
 *
 * @author 张宝旭
 */
public class Bank {

    /**
     * 存储用户的数组
     */
    private final User[] users = new User[10];

    /**
     * 用户总数量
     */
    private int userCounts;

    /**
     * 登录状态
     */
    private boolean status = false;

    CheckInputFormat check = new CheckInputFormat();


    public Bank() {
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.showMenu();
    }

    /**
     * 初始化用户
     */
    public void initial() {
        User A = new User();
        User B = new User();
        A.setUsername("老大");
        B.setUsername("老二");
        A.setIdentity("211421199901018888");
        B.setIdentity("211421199901028888");
        A.setPhone("15188880001");
        B.setPhone("15188880002");
        A.setCardNo("6250008888001");
        B.setCardNo("6520008888002");
        A.setPassword("125001");
        B.setPassword("125002");
        A.setBalance(10000);
        B.setBalance(20000);
        users[0] = A;
        users[1] = B;
        userCounts = 2;
    }

    /**
     * 登录主界面
     */
    public void showMenu() {
        User user = null;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----------【登录界面】----------");
            System.out.println("---------请输入银行卡号---------");
            String cardNo = scanner.next();
            if (check.checkCardNoFormat(cardNo)) {
                System.out.println("----------请输入密码-----------");
                String password = scanner.next();
                if (check.checkPasswordFormat(password)) {
                    System.out.println("登录中......");
                    user = login(cardNo, password);
                } else {
                    System.out.println("密码格式输入错误");
                    continue;
                }
            } else {
                System.out.println("银行卡号格式输入错误");
                continue;
            }

            if (user != null) {
                System.out.println("-----------登录成功！----------");
                System.out.println("-----尊敬的 " + user.getUsername() + "用户，欢迎您！----");
                System.out.println("======================================================");
                status = true;
                ATMSystem(user);
            } else {
                System.out.println("银行卡号或密码错误，请重新输入！");
            }
        }
    }

    /**
     * ATM系统
     * @param user 进入系统的用户
     */
    public void ATMSystem(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================欢迎进入ATM机系统====================");
        System.out.println("---1:存款  2:取款  3:转账  4:查询余额  5:修改密码  0:退出----");

        while (status) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("输入存款的金额");
                    double saveMoney = scanner.nextDouble();
                    if (check.checkMoneyFormat(saveMoney)) {
                        if (save(user, saveMoney)) {
                            System.out.println("存款成功");
                        } else {
                            System.out.println("存款失败");
                        }
                    } else {
                        System.out.println("金额输入错误！");
                    }
                    break;
                case 2:
                    System.out.println("输入取款的金额");
                    double takeMoney = scanner.nextDouble();
                    if (check.checkMoneyFormat(takeMoney)) {
                        if (withDraw(user, takeMoney)) {
                            System.out.println("取款成功");
                        } else {
                            System.out.println("取款失败，余额不足！");
                        }
                    } else {
                        System.out.println("金额输入错误！");
                    }

                    break;
                case 3:
                    System.out.println("输入转账对方的银行卡号");
                    String yourCardNo = scanner.next();
                    if (!check.checkCardNoFormat(yourCardNo)) {
                        System.out.println("银行卡号格式错误！");
                        break;
                    }
                    User userTarget = getUserByCardNo(yourCardNo);
                    if (userTarget != null) {
                        System.out.println("请输入转账金额");
                        double transMoney = scanner.nextDouble();
                        if (trans(user, userTarget, transMoney)) {
                            System.out.println("转账成功");
                        } else {
                            System.out.println("转账失败");
                        }
                    } else {
                        System.out.println("银行卡号输入有误");
                    }
                    break;
                case 4:
                    double balance = query(user);
                    System.out.println("余额: " + balance);
                    break;
                case 5:
                    System.out.println("请输入旧密码");
                    while (true) {
                        String oldPassword = scanner.next();
                        if (user.getPassword().equals(oldPassword)) {
                            System.out.println("请输入新密码");
                            String newPassword = scanner.next();
                            if (modifyPassword(user, newPassword)) {
                                System.out.println("修改成功！");
                            } else {
                                System.out.println("修改失败！");
                            }
                            break;
                        } else {
                            System.out.println("密码输入错误,请重新输入");
                        }
                    }
                    break;
                case 0:
                    System.out.println("退出成功！欢迎下次再来！");
                    status = false;
                    break;
                default:
                    System.out.println("输入错误，重新输入！");
                    break;
            }
        }
    }

    /**
     * 登录
     * @param cardNo 银行卡号
     * @param password 密码
     * @return 返回 user (如果查到了返回user，如果没查到返回user为null)
     */
    public User login(String cardNo, String password) {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String, String> map = new HashMap<String, String>();
        map.put("cardNo", cardNo);
        map.put("password", password);

        User user = userMapper.selectUser(map);

        return user;
    }

    /**
     * 存款
     * @param user 要存款的用户
     * @param money 存储的金额
     */
    public boolean save(User user, double money) {
        user.setBalance(user.getBalance() + money);

        // 存储到数据库
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        int result = userMapper.save(user);
        if (result >= 0) {
            sqlSession.commit();
            sqlSession.close();
            return true;
        }
        sqlSession.rollback();
        sqlSession.close();
        return false;
    }

    /**
     * 取款
     * @param user 要取款的用户
     * @param money 取出的金额
     */
    public boolean withDraw(User user, double money) {
        if (user.getBalance() >= money) {
            user.setBalance(user.getBalance() - money);
            // 存储到数据库
            SqlSession sqlSession = MybatisUtils.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            int result = userMapper.save(user);
            if (result >= 0) {
                sqlSession.commit();
                sqlSession.close();
                return true;
            } else {
                sqlSession.rollback();
                sqlSession.close();
            }
        }
        return false;
    }

    /**
     * 转账
     * @param userSource 甲方
     * @param userTarget 乙方
     * @param transferAmount 转账金额
     * @return 如果转账成功，则返回true，否则返回false
     */
    public boolean trans(User userSource, User userTarget, double transferAmount) {
        if (userSource.getBalance() >= transferAmount) {
            userSource.setBalance(userSource.getBalance() - transferAmount);
            userTarget.setBalance(userTarget.getBalance() + transferAmount);

            SqlSession sqlSession = MybatisUtils.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            int resultSource = userMapper.save(userSource);
            int resultTarget = userMapper.save(userTarget);

            if (resultSource >= 0 || resultTarget >= 0) {
                sqlSession.commit();
                sqlSession.close();
                return true;
            } else {
                sqlSession.rollback();
                sqlSession.close();
            }
        }
        return false;
    }

    /**
     * 查询余额
     * @param user 要查询的用户
     * @return 返回查询到的余额
     */
    public double query(User user) {
        return user.getBalance();
    }

    /**
     * 修改密码
     * @param user 修改密码的用户
     * @param newPassword 新密码
     */
    public boolean modifyPassword(User user, String newPassword) {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String, String> map = new HashMap<String, String>();
        map.put("password", newPassword);
        map.put("cardNo", user.getCardNo());
        int result = userMapper.modifyPassword(map);

        if (result >= 0) {
            sqlSession.commit();
            sqlSession.close();
            return true;
        }
        sqlSession.rollback();
        sqlSession.close();
        return false;
    }

    /**
     * 根据银行卡号获得用户
     * @param cardNo 银行卡号
     * @return 查找成功，则返回用户
     */
    public User getUserByCardNo(String cardNo) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.getUserByCardNo(cardNo);
        sqlSession.close();

        return user;
    }
}
