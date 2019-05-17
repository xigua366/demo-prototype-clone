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
	 * 转换集合-浅克隆
	 * @param sourceList 源集合
	 * @param targetClazz 目标集合元素类型 
	 * @return 转换后的集合
	 */
	public static <T> List<T> convertList(
			List<? extends AbstractObject> sourceList, Class<T> targetClazz) {
		if(sourceList == null) {
			return null;
		}
		
		// 判断是否分页组件
		if(sourceList instanceof Page) {
			Page<T> targetPage = getTargetPage(sourceList);
			for(AbstractObject sourceObject : sourceList) {
				targetPage.add(sourceObject.clone(targetClazz));    
			}
			return targetPage;
		}
		
		List<T> targetList = new ArrayList<T>(); 
		for(AbstractObject sourceObject : sourceList) {
			targetList.add(sourceObject.clone(targetClazz));    
		}
		
		return targetList;
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
		
		// 判断是否分页组件
		if(sourceList instanceof Page) {
			Page<T> targetPage = getTargetPage(sourceList);
			for(AbstractObject sourceObject : sourceList) {
				targetPage.add(sourceObject.clone(targetClazz, cloneDirection));      
			}
			
			return targetPage;
		}
		
		List<T> targetList = new ArrayList<T>(); 
		for(AbstractObject sourceObject : sourceList) {
			targetList.add(sourceObject.clone(targetClazz, cloneDirection));      
		}
		
		return targetList;
	}
	
	/**
	 * 获取转换后的Page对象
	 * @param <T>
	 * @param sourceList
	 * @return
	 */
	private static <T> Page<T> getTargetPage(List<? extends AbstractObject> sourceList) {
		Page<? extends AbstractObject> tempPage = (Page<? extends AbstractObject>) sourceList;
		Page<T> targetPage = new Page<>(tempPage.getPageNum(), tempPage.getPageSize(), tempPage.isCount());
		targetPage.setStartRow(tempPage.getStartRow());
		targetPage.setEndRow(tempPage.getEndRow());
		targetPage.setTotal(tempPage.getTotal());
		targetPage.setPages(tempPage.getPages());
		targetPage.setReasonable(tempPage.getReasonable());
		targetPage.setPageSizeZero(tempPage.getPageSizeZero());
		targetPage.setCountColumn(tempPage.getCountColumn());
		targetPage.setOrderBy(tempPage.getOrderBy());
		targetPage.setOrderByOnly(tempPage.isOrderByOnly());
		return targetPage;
	}
	
}
