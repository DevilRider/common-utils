/*
 * NumberUtil.java
 * Copyright: Copyright joo (c) 2013
 * org: JavaEE Object-Oriented
 */
package org.joo.common.utils.math;

import java.text.NumberFormat;

import org.joo.common.utils.string.StringUtil;

/**
 * 数字工具类
 * @author Core.Yang 
 * @time 2013-12-9
 */
public class NumberUtil {
	
	/** 默认保留小数点位数 */
	private static int DEF_SCALE = 2;
	
	/**
	 * 将数据转换成 Double
	 * @param val
	 * @return double 值
	 */
	public static Double convert2Double(Object val){
		String value = StringUtil.trimNull(val);
		if(StringUtil.isEmpty(value)){
			return null;
		}else{
			return Double.parseDouble(value);
		}
	}
	
	
	/**
     * 格式化显示小数
     * 
     * @param val
     *            需要显示的数值
     * 
     * @param scale
     *            小数点后保留几位
     * 
     * @return 格式化的结果
     */
    public static String formatNumber(Double val, int scale) {
        String rtnVal = "";
        java.text.NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(scale);
        format.setMinimumFractionDigits(scale);
        format.setGroupingUsed(false);
        if(val != null) {
            rtnVal = format.format(val);
        }
        return rtnVal;

    }

    /**
     * 格式化显示小数（两位有效数字的小数）
     * 
     * @param val
     *            需要显示的数值
     * 
     * 
     * @return 格式化的结果
     */
    public static String formatNumber(Double val) {
        return formatNumber(val, DEF_SCALE);

    }

    /**
     * 格式化显示小数
     * 
     * @param val
     *            需要显示的数值
     * 
     * 
     * @return 格式化的结果
     */
    public static String formatNumber(String val) {
        Double dobject = null;
        if(StringUtil.isEmpty(val)) {
            return "";
        }
        try {
            dobject = convert2Double(val);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return formatNumber(dobject, DEF_SCALE);

    }

    /**
     * 格式化显示小数
     * 
     * @param val
     *            需要显示的数值
     * 
     * 
     * @return 格式化的结果
     */
    public static String formatNumber(String val, int scale) {
        Double dobject = null;
        if(StringUtil.isEmpty(val)) {
            return "";
        }
        try {
        	dobject = convert2Double(val);
        } catch (Exception e) {
             e.printStackTrace();
            return "";
        }
        return formatNumber(dobject, scale);
    }
}
