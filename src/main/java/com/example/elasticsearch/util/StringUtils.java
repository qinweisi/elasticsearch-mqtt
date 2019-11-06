package com.example.elasticsearch.util;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author zhangrui
 * @version 1.0
 * @时间 2016年9月22日
 * @描述
 */
public class StringUtils {

    /**
     * 判断是否是int类型
     *
     * @param value
     * @title isInt
     * @author zhangrui 2016年9月21日
     * @description
     */
    public static boolean isInt(String value) {
        if (isEmpty(value))
            return false;
        for (int i = value.length(); --i >= 0; ) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.trim().equals("") || str.length() < 0);
    }

    /**
     * 得到一个随机的UUID，并去掉中间"-"
     *
     * @return
     * @title generatorUUID
     * @author zhangrui 2016年9月22日
     * @description
     */
    public static String generatorUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    /**
     * 生成n位数字随机数返回字符串类型
     *
     * @param n
     * @return
     * @title generatorIntToString
     * @author zhangrui 2016年12月5日
     * @description
     */
    public static String generatorIntToString(int n) {
        String str = "";
        Random ra = new Random();
        for (int i = 0; i < n; i++) {
            str += ra.nextInt(10);
        }
        return str;
    }

    /**
     * 生成8位数的 字母 大小随机 当做授权码
     *
     * @return
     */
    public static String randomNumber() {
        Random r = new Random();
        String code = "";
        for (int i = 0; i < 8; ++i) {
            int temp = r.nextInt(52);
            char x = (char) (temp < 26 ? temp + 97 : (temp % 26) + 65);
            code += x;
        }
        return code;
    }

    public static boolean isAccount(String account) {
        String regex = "^[A-Za-z0-9_]+$";
        Matcher matcher = Pattern.compile(regex).matcher(account);
        return matcher.find();
    }

    /**
     * 方法四
     *
     * @param string
     * @param sub
     */
    public static int subStringCount(String string, String sub) {
        int number = 0;

        int len = sub.length();
        int index = 0;
        for (int i = 0; i < string.length(); i = len + index) {
            if ((index = string.indexOf(sub, i)) > -1)
                number++;
            else
                break;
        }
        return number;
    }

    public static void main(String args[]) {
        System.out.print(StringUtils.isAccount("1_2-3131"));
    }


}
