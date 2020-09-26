package com.robot.check;

/**
 * 检测输入规范。
 *
 * @author 张宝旭
 * 2020/8/1
 */
public class CheckInputFormat {
    /**
     * 判断银行卡号格式输入是否正确
     *
     * @param cardNo 银行卡号
     * @return 如果输入正确，则返回true
     */
    public boolean checkCardNoFormat(String cardNo) {
        boolean matches = cardNo.matches("");
        return true;
    }

    /**
     * 检查身份证号格式是否输入正确
     * @param identity 身份证号
     * @return 如果输入正确，则返回true
     */
    public boolean checkIdentityFormat(String identity) {
        String regular = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)" +
                "\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        return identity.matches(regular);
    }

    /**
     * 检查手机号格式是否输入正确
     * @param phone 手机号
     * @return 如果输入正确，则返回true
     */
    public boolean checkPhoneFormat(String phone) {
        String regular = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";
        return phone.matches(regular);
    }

    /**
     * 检查密码格式是否输入正确
     * @param password 密码
     * @return 如果输入正确，则返回true
     */
    public boolean checkPasswordFormat(String password) {
        String regular = "^\\d{6}$";
        return password.matches(regular);
    }

    /**
     * 检查输入的金额格式是否正确
     * @param money 金额
     * @return 如果输入正确，则返回true
     */
    public boolean checkMoneyFormat(double money) {
        return money >= 0;
    }
}
