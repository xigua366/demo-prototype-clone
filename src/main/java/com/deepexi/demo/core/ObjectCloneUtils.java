package com.deepexi.demo.core;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * 对象Clone工具类
 * @author yangxi
 *
 */
public class ObjectCloneUtils {

	/**
	 * 转换集合
	 * @param sourceList 源集合
	 * @param targetClazz 目标集合元素类型 
	 * @return 转换后的集合
	 */
	public static <T> List<T> convertList(
			List<? extends AbstractObject> sourceList, Class<T> targetClazz) {
		if(sourceList == null) {
			return null;
		}
		
		List<T> targetList = new ArrayList<T>(); 
		for(AbstractObject sourceObject : sourceList) {
			targetList.add(sourceObject.clone(targetClazz));    
		}
		
		return targetList;
	}
	
	/**
	 * 转换Mybatis分页对象-浅克隆
	 * @param sourceList 源集合
	 * @param targetClazz 目标集合元素类型 
	 * @return 转换后的集合
	 */
	public static <T> Page<T> convertPage(
			Page<? extends AbstractObject> sourcePage, Class<T> targetClazz) {
		if(sourcePage == null) {
			return null;
		}
		
		Page<T> targetPage = new Page<T>(); 
		for(AbstractObject sourceObject : sourcePage) {
			targetPage.add(sourceObject.clone(targetClazz));    
		}
		
		return targetPage;
	}
	
	/**
	 * 转换集合-深度克隆
	 * @param sourceList 源集合
	 * @param targetClazz 目标集合元素类型 
	 * @return 转换后的集合
	 */
	public static <T> List<T> convertList(List<? extends AbstractObject> sourceList, 
			Class<T> targetClazz, Integer cloneDirection) {
		if(sourceList == null) {
			return null;
		}
		
		List<T> targetList = new ArrayList<T>(); 
		for(AbstractObject sourceObject : sourceList) {
			targetList.add(sourceObject.clone(targetClazz, cloneDirection));      
		}
		
		return targetList;
	}
	
	/**
	 * 转换Mybatis分页对象-深度克隆
	 * @param sourceList 源集合
	 * @param targetClazz 目标集合元素类型 
	 * @return 转换后的集合
	 */
	public static <T> Page<T> convertPage(Page<? extends AbstractObject> sourcePage, 
			Class<T> targetClazz, Integer cloneDirection) {
		if(sourcePage == null) {
			return null;
		}
		
		Page<T> targetPage = new Page<>(sourcePage.getPageNum(), sourcePage.getPageSize(), sourcePage.isCount());
		targetPage.setStartRow(sourcePage.getStartRow());
		targetPage.setEndRow(sourcePage.getEndRow());
		targetPage.setTotal(sourcePage.getTotal());
		targetPage.setPages(sourcePage.getPages());
		targetPage.setReasonable(sourcePage.getReasonable());
		targetPage.setPageSizeZero(sourcePage.getPageSizeZero());
		targetPage.setCountColumn(sourcePage.getCountColumn());
		targetPage.setOrderBy(sourcePage.getOrderBy());
		targetPage.setOrderByOnly(sourcePage.isOrderByOnly());
		
		for(AbstractObject sourceObject : sourcePage) {
			targetPage.add(sourceObject.clone(targetClazz, cloneDirection));      
		}
		
		return targetPage;
	}
	
}
