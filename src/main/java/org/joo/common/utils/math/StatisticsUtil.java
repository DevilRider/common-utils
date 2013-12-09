/*
 * StatisticsUtil.java
 * Copyright: Copyright joo (c) 2013
 * org: JavaEE Object-Oriented
 */
package org.joo.common.utils.math;


/**
 * 统计计算相关类
 * 
 * @author Core.Yang
 * @time 2013-12-9
 */
public class StatisticsUtil {

	/** 百分率值 100 */
	private static double RATE_VAL = 100;

	/**
	 * 计算数组中数据的最大值
	 * 
	 * @param datas
	 *            存放数据的数组
	 * @return 最大值
	 */
	public static Object max(Object[] datas) {
		if (datas == null) {
			return null;
		}
		Object maxValue = null;
		double max = Double.MIN_VALUE;
		try {
			for (int i = 0; i < datas.length; i++) {
				if (datas[i] == null) {
					continue;
				} else {
					Double tmp = NumberUtil.convert2Double(datas[i]);
					max = (max < tmp) ? tmp : max;
				}

			}
			maxValue = (max == Double.MIN_VALUE) ? null : max;
			return maxValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算数组中数据的最小值
	 * 
	 * @param datas
	 *            存放数据的数组
	 * @return 最小值
	 */
	public static Object min(Object[] datas) {
		Object minValue = null;
		double min = Double.MAX_VALUE;
		try {
			for (int i = 0; i < datas.length; i++) {
				if (datas[i] == null) {
					continue;
				}
				Double tmp = NumberUtil.convert2Double(datas[i]);
				min = (min > tmp) ? tmp : min;
			}
			minValue = (min == Double.MAX_VALUE) ? null : min;
			return minValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算数组中数据的平均值
	 * 
	 * @param datas
	 *            存放数据的数组，null－不参与计算，零－参与计算。
	 * @return 平均值
	 */
	public static Object avg(Object[] datas) {
		if (datas == null) {
			return null;
		}
		Object avgValue = null;
		int count = 0;
		double sum = 0d;
		try {
			for (int i = 0; i < datas.length; i++) {
				if (datas[i] == null) {// 如果是空则跳过
					continue;
				}
				sum = ArithmeticUtil.plus(sum, 
						NumberUtil.convert2Double(datas[i]));
				count++;
			}
			avgValue = (count == 0) ? null : ArithmeticUtil.divide(sum, count);
			return avgValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算数组中数据的和
	 * 
	 * @param datas
	 *            存放数据的数组
	 * @return 平均值
	 */
	public static Object sum(Object[] datas) {
		if (datas == null) {
			return null;
		}
		Object sumValue = null;
		double sum = 0d;
		int num = 0;
		try {
			for (int i = 0; i < datas.length; i++) {
				if (datas[i] == null) {// 如果是空则跳过,但要计数
					num++;
					continue;
				}
				sum = ArithmeticUtil.plus(sum, 
						NumberUtil.convert2Double(datas[i]));
			}
			// 如果元素均为 null 则返回 null
			sumValue = (num == datas.length) ? null : sum;
			return sumValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算增长率 公式：增长率 = (value1 - value2)/value2 * 100
	 * 
	 * @param value1 
	 * @param value2
	 * @return 增长率
	 */
	public static Object getRiseRate(Object value1, Object value2) {
		try {
			Object rate = null;
			if (value1 == null || value2 == null) {
				return rate;
			}
			Double denominator = NumberUtil.convert2Double(value2);
			if (denominator == 0.0) {
				return rate;
			}
			
			double result = 0d;
			Double val1 = NumberUtil.convert2Double(value1);
			double numerator = ArithmeticUtil.subtract(val1, denominator);
			result = ArithmeticUtil.divide(numerator, denominator) * RATE_VAL;
			
			result = (Double.isNaN(result) || Double.isInfinite(result)) ? null : result;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算数组中的各个元素的增长率 公式：增长率=(value1 - value2)/value2 * 100
	 * 
	 * @param value1
	 * @param value2
	 * @return 增长率数组 
	 */
	public static Object[] getRiseRate(Object[] value1, Object[] value2) {
		Object[] result = null;
		try {
			if ((value1 == null || value2 == null)
					|| (value1.length != value2.length)) {
				return result;
			}
			result = new Double[value1.length];
			for (int i = 0; i < value1.length; i++) {
				result[i] = getRiseRate(value1[i], value2[i]);
			}
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算数组中的各个元素的贡献率 <br>
	 * <p> 公式：贡献率= (value1 - value2)/(sum(valueArray1) - sum(ValueArray2))*100</p>
	 * 
	 * @param values1
	 * @param values2
	 * @return 贡献率数组
	 */
	public static Object[] getContributeRate(Object[] values1, Object[] values2) {
		try {
			Object[] deviation = null;
			Double[] result = null;
			// 先进行差值的计算调用getDifference()方法
			deviation = getDeviations(values1, values2);// 数组中，每个元素的差值
			if (deviation == null) {
				return null;
			}
			
			// 整体的差值，即数组value1中的各个元素相加与value2中的各个元素相加的差值
			Object sumValue1 = sum(values1);// value1的和
			Object sumValue2 = sum(values2);// value2的和
			Object sumDifference = getDeviation(sumValue1, sumValue2);// 求两个数组和的差
			double denominator = NumberUtil.convert2Double(sumDifference);
			if (sumDifference == null || denominator == 0.0) {
				return null;
			}
			
			result = new Double[deviation.length];
			for (int i = 0; i < deviation.length; i++) {
				if (deviation[i] == null) {
					result[i] = null;
					continue;
				}
				double numerator = NumberUtil.convert2Double(deviation[i]);
				result[i] = ArithmeticUtil.divide(numerator, denominator) * RATE_VAL;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 计算数组差 计算两个数组相应位置的数据差，返回数值差组成的数组<br>
	 *  value1 - value2
	 * @param values1
	 *            -被减数
	 * @param values2
	 *            -减数
	 * @return 数组中每个元素的差值
	 */
	public static Object[] getDeviations(Object[] values1, Object[] values2) {
		Object[] result = null;
		if ((values1 == null || values2 == null)
				|| (values1.length != values2.length)) {
			return result;
		}
		result = new Double[values1.length];
		for (int i = 0; i < values1.length; i++) {
			 result[i] = getDeviation(values1[i], values2[i]);
		}
		return result;
	}
	
	/**
	 * 计算数组差 计算两个数据差，返回数值差<br>
	 *  value1 - value2
	 * @param value1
	 *            -被减数
	 * @param value2
	 *            -减数
	 * @return 差值
	 */
	public static Object getDeviation(Object value1, Object value2) {
		if (value1 == null || value2 == null) {
			return null;
		}
		Object result = null;
		double tmp = ArithmeticUtil.subtract(
				NumberUtil.convert2Double(value1),
				NumberUtil.convert2Double(value2));
		result = (Double.isNaN(tmp) || Double.isInfinite(tmp)) ? null : tmp;
		return result;
	}
}
