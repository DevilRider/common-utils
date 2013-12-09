/*
 * ArithmeticUtil.java
 * Copyright: Copyright joo (c) 2013
 * org: JavaEE Object-Oriented
 */
package org.joo.common.utils.math;

import java.math.BigDecimal;

/**
 * 四则运算类（加减乘除）
 * <p>
 * 提供精确的浮点数运算，包括加减乘除和四舍五入。
 * </p>
 * 
 * @author Core.Yang
 * @time 2013-12-9
 */
public class ArithmeticUtil {

	/** 默认除法运算精度 10 */
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 
	 * 提供精确的加法运算。
	 * 
	 * @param val1
	 *            被加数
	 * 
	 * @param val2
	 *            加数
	 * 
	 * @return 两个参数的和
	 * 
	 */
	public static Double plus(double val1, double val2) {
		BigDecimal b1 = new BigDecimal(Double.toString(val1));
		BigDecimal b2 = new BigDecimal(Double.toString(val2));
		return b1.add(b2).doubleValue();

	}

	/**
	 * 
	 * 提供精确的减法运算。
	 * 
	 * @param val1
	 *            被减数
	 * 
	 * @param val2
	 *            减数
	 * 
	 * @return 两个参数的差
	 * 
	 */
	public static double subtract(double val1, double val2) {
		BigDecimal b1 = new BigDecimal(Double.toString(val1));
		BigDecimal b2 = new BigDecimal(Double.toString(val2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 
	 * 提供精确的乘法运算。
	 * 
	 * @param val1
	 *            被乘数
	 * 
	 * @param val2
	 *            乘数
	 * 
	 * @return 两个参数的积
	 * 
	 */
	public static double multiply(double val1, double val2) {
		BigDecimal b1 = new BigDecimal(Double.toString(val1));
		BigDecimal b2 = new BigDecimal(Double.toString(val2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param dividend
	 *            被除数
	 * @param divisor
	 *            除数
	 * @return 两个参数的商
	 */
	public static double divide(double dividend, double divisor) {
		return divide(dividend, divisor, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param dividend
	 *            被除数
	 * @param divisor
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double divide(double dividend, double divisor, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(dividend));
		BigDecimal b2 = new BigDecimal(Double.toString(divisor));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param val
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double val, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(val));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}
	
	/**
	 * 获取绝对值
	 * 
	 * @param val
	 *            数值
	 * @return 取绝对值
	 */
	public static double abs(double val) {
		return Math.abs(val);
	}
}
