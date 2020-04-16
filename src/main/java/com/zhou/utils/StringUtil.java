package com.zhou.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Description:String工具类
 * 
 * @author: YSL
 * @email: songlin.yang@downjoy.com
 * @create at: 2018年7月27日 下午1:16:45
 */
public class StringUtil {

    /**
     * @Description:判断字符串是否为null或 ''
     * 
     * @Params: str 要判断的字符串
     * @return: boolean 为null 或 ''返回true
     * @author: YSL
     * @email: songlin.yang@downjoy.com
     * @create at: 2018年7月27日 下午1:18:23
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Description:去掉最后一个逗号
     * 
     * @Params: str 源字符串
     * @return: String
     * @author: YSL
     * @email: songlin.yang@downjoy.com
     * @create at: 2018年7月31日 下午6:12:43
     */
    public static String replaceLastComma(String str) {
        if (!isEmpty(str)) {
            String s = str.substring(str.length() - 1);
            if (",".equals(s)) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    /**
     * @Description: 对字符串进行编码
     * 
     * @Params: src 要编码的字符串 encoding 字符编码(utf-8/gbk等)
     * @return: String 编码后的字符串
     * @author: YSL
     * @email: songlin.yang@downjoy.com
     * @create at: 2018年8月21日 上午9:37:51
     */
    public static String encode(String src, String encoding) {
        if (isEmpty(encoding)) {
            encoding = "UTF-8";// 默认编码
        }
        try {
            return URLEncoder.encode(src, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 对字符串进行解码
     * 
     * @Params: src 要解码的字符串 encoding 字符编码(utf-8/gbk等)
     * @return: String 解码后的字符串
     * @author: YSL
     * @email: songlin.yang@downjoy.com
     * @create at: 2018年8月21日 上午9:41:20
     */
    public static String decode(String src, String encoding) {
        if (isEmpty(encoding)) {
            encoding = "UTF-8";// 默认编码
        }
        try {
            return URLDecoder.decode(src, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
