/*
 * Combination.java
 * Copyright: Copyright joo (c) 2013
 * org: JavaEE Object-Oriented
 */
package org.joo.common.utils.math;

import java.util.ArrayList;
import java.util.List;



/**
 * 组合实现
 * 
 * @author L.Yang
 * @time 2013-10-24
 */
public class Combination {
	/**
	 * 待组合数据
	 */
	private String[] args;

	/**
	 * 组合结果
	 */
	private List<String[]> resultList;
	
	/**
	 * 构造方法
	 * @param args
	 */
	public Combination(String[] args) {
		this.args = args;
	}

	/**
	 * 从组合中取出数据（组合元素 以n为基准做组合）
	 * @param n 一组数据个数
	 */
	public List<String[]> fetchCombination(int n) {
		String[] result = new String[n];
		resultList = new ArrayList<String[]>();
		combinate(0, 1, result, n);
		return resultList;
	}

	/**
	 * 组合数据
	 * @param head 
	 * @param index
	 * @param result 
	 * @param n
	 */
	private void combinate(int head, int index, String[] result, int n) {
		for (int i = head; i < args.length + index - n; i++) {
			if (index <= n) {
				result[index - 1] = args[i];
				combinate(i + 1, index + 1, result, n);
			} else {
				resultList.add(result.clone());
				return ;
			}
		}
	}
}