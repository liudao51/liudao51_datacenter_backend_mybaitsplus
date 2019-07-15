package com.liudao51.datacenter.common.util;

import java.util.Collection;

/**
 * class: 字符串工具类
 * <p>
 * Created by jewel on 2019/4/19.
 */
public class StringX {
    /**
     * function: 检测对象字符串是否为空(null,"","null")
     *
     * @param val
     * @return boolean
     */
    public static boolean isEmpty(Object val) {
        return (val == null || "".equals(val) || "null".equals(val));
    }

    /**
     * function: 去除首尾指定字符
     *
     * @param val      string
     * @param trimChar 指定的字符
     * @return
     */
    public static String trim(String val, String trimChar) {
        boolean beginIndexFlag = true;
        boolean endIndexFlag = true;

        do {
            int beginIndex = val.indexOf(trimChar) == 0 ? 1 : 0;
            int endIndex = val.lastIndexOf(trimChar) + 1 == val.length() ? val.lastIndexOf(trimChar) : val.length();

            val = val.substring(beginIndex, endIndex);
            beginIndexFlag = (val.indexOf(trimChar) == 0);
            endIndexFlag = (val.lastIndexOf(trimChar) + 1 == val.length());
        } while (beginIndexFlag || endIndexFlag);

        return val;
    }

    /**
     * function: 根据分隔符来拆分字符串成数组
     *
     * @param val
     * @param separator
     * @return
     */
    public static String[] split(String val, String separator) throws Exception {
        return val.split(separator);
    }

    /**
     * function: 把Collection转换成以分隔符separator分隔的字符串
     *
     * @param list
     * @param separator
     * @return
     */
    public static String join(Collection list, String separator) throws Exception {
        if (list.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Object item : list) {
            sb.append(item).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * function: 去掉字符串分隔符字段两边的字符
     * 如：把"'AMII','kids'", 转换为 "AMII,kids"
     *
     * @param val
     * @param separator
     * @param trimChar
     * @return String
     * @throws Exception
     */
    public static String trimFieldChar(String val, String separator, String trimChar) throws Exception {

        if (!(val == null || "".equals(val) || "null".equals(val))) {

            String[] valArr = val.split(separator);
            for (int i = 0; i < valArr.length; i++) {
                valArr[i] = trim(valArr[i], trimChar);
            }

            Collection list = java.util.Arrays.asList(valArr);
            val = join(list, separator);
        }

        return val;
    }

    /**
     * function: URL字符串解码
     *
     * @param val
     * @return
     * @throws Exception
     */
    public static String urlDecode(String val) throws Exception {
        return java.net.URLDecoder.decode(val, "utf-8");
    }

    /**
     * function: 当取不到值时返回默认值
     *
     * @param val        Object
     * @param defaultVal String
     * @return String
     */
    public static String getString(Object val, String defaultVal) {
        return !isEmpty(val) ? String.valueOf(val) : defaultVal;
    }
}
