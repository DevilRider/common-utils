/*
 * StringUtil.java
 * Copyright: Copyright joo (c) 2013
 * org: JavaEE Object-Oriented
 */
package org.joo.common.utils.string;

import java.nio.charset.Charset;
import java.util.Locale;

/**
 * StringUtil.java
 * 
 * @author Core.Yang
 * @time 2013-11-26
 */
public class StringUtil {

	/**
	 * 在展示的时候，可能需要去掉字符串中的html标签，只展示无格式的字符，并且在字符的长度超一定值时使用......代替超出部分
	 * 
	 * @param input
	 *            : 输入需要展示的字符串
	 * @param length
	 *            : 该字符串的最大长度，如果超过长度时，添加.....
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		String str = input.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll(
				"</[a-zA-Z]+[1-9]?>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "......";
		}
		return str;
	}

	/**
	 * 若字符串为空（参见isEmpty方法），返回""；<br>
	 * 若不为空，则返回自身
	 * 
	 * @param str
	 * @return
	 */
	public static String killNull(String str) {
		if (isEmpty(str)) {
			return "";
		}
		return str;
	}

	/**
	 * 判断字符是否为空，为空则返回true<br>
	 * 为空的条件：null、""、"null"
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str.trim())
				|| "'null'".equalsIgnoreCase(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 处理空值
	 * 
	 * @param s
	 *            字符类型
	 * @return String
	 */
	public static String trimNull(String s) {
		if (null == s) {
			return "";
		} else {
			return s.trim();
		}
	}

	/**
	 * 处理空值
	 * 
	 * @param o
	 *            任意对象
	 * @return String
	 */
	public static String trimNull(Object o) {
		if (null == o) {
			return "";
		} else {
			return o.toString();
		}
	}

	/**
	 * 字符串第一个字符大写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstCharToUpperCase(String str) {
		if (!isEmpty(str)) {
			char firstChar = str.charAt(0);
			return String.valueOf(firstChar).toUpperCase(Locale.ENGLISH) + str.substring(1);
		}
		return str;
	}

	/**
	 * 字符串第一个字符转为小写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstCharToLowerCase(String str) {
		if (!isEmpty(str)) {
			char firstChar = str.charAt(0);
			return String.valueOf(firstChar).toLowerCase(Locale.ENGLISH) + str.substring(1);
		}
		return str;
	}

	public static byte[] getBytesByCharset(String str, Charset charset) {
		if (!isEmpty(str)) {
			return str.getBytes(charset);
		}
		return null;
	}

	/**
	 * 获取findStr在src中出现的次数
	 * 
	 * @param src
	 * @param findStr
	 * @return
	 */
	public static int getTimes(String src, String findStr) {
		int times = 0;
		if (isEmpty(src) || isEmpty(findStr)) {
			return times;
		}
		while (src.indexOf(findStr) >= 0) {
			int findStrLength = findStr.length();
			int index = src.indexOf(findStr);
			src = src.substring(index + findStrLength);
			times++;
		}
		return times;
	}

	/**
	 * 获取换行符
	 * 
	 * @return
	 */
	public static String getNewLine() {
		return System.getProperty("line.separator");
	}

	/**
	 * 获取UTF8编码的字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getUTF8Bytes(String str) {
		return getBytesByCharset(str, Charset.forName("UTF-8"));
	}

	/**
	 * 获取GBK编码的字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getGBKBytes(String str) {
		return getBytesByCharset(str, Charset.forName("GBK"));
	}

	/**
	 * 获取ISO-8859-1编码的字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getISO8859Bytes(String str) {
		return getBytesByCharset(str, Charset.forName("ISO-8859-1"));
	}
}
