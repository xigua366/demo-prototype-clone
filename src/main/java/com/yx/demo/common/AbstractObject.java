package com.yx.demo.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础POJO类
 * 
 * @author yangxi
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AbstractObject {

	/**
	 * 浅度克隆
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> T clone(Class<T> clazz) {
		try {
			T target = clazz.newInstance();
			BeanCopierUtils.copyProperties(this, target);
			return target;
		} catch (Exception e) {
			throw new RuntimeException("error", e);
		}

	}

	/**
	 * 浅度克隆
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> T clone(T target) {
		BeanCopierUtils.copyProperties(this, target);
		return target;
	}

	/**
	 * 深度克隆
	 * 
	 * @param clazz
	 * @param direction
	 * @return
	 * @throws Exception
	 */
	public <T> T clone(Class<T> clazz, Integer cloneDirection) {
		try {

			// 先完成基本字段的浅克隆
			T target = clazz.newInstance();
			BeanCopierUtils.copyProperties(this, target);

			// 完成所有List类型的深度克隆
			Class<?> thisClazz = this.getClass();

			Field[] fields = thisClazz.getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);

				// 如果判断某个字段是List类型的
				if (field.getType() != List.class) {
					continue;
				}

				List<?> list = (List<?>) field.get(this);
				if (list == null || list.size() == 0) {
					continue;
				}

				// 获取List集合中的泛型类型
				Class<?> listGenericClazz = getListGenericType(field);
				// 获取要克隆的目标类型
				Class<?> cloneTargetClazz = getCloneTargetClazz(listGenericClazz, cloneDirection);
				// 将list集合克隆到目标list集合中去
				List clonedList = new ArrayList();
				cloneList(list, clonedList, cloneTargetClazz, cloneDirection);

				// 获取设置克隆好的list的方法名称
				Method setFieldMethod = getSetCloneListFieldMethodName(field, clazz);
				setFieldMethod.invoke(target, clonedList);
			}

			return target;
		} catch (Exception e) {
			throw new RuntimeException("error", e);
		}
	}

	/**
	 * 将一个list克隆到另外一个list
	 * 
	 * @param sourceList
	 * @param targetList
	 * @param cloneTargetClazz
	 * @param cloneDirection
	 * @throws Exception
	 */
	private void cloneList(List sourceList, List targetList, Class cloneTargetClazz, Integer cloneDirection) {
		for (Object object : sourceList) {
			AbstractObject targetObject = (AbstractObject) object;
			AbstractObject clonedObject = (AbstractObject) targetObject.clone(cloneTargetClazz, cloneDirection);
			targetList.add(clonedObject);
		}
	}

	/**
	 * 获取list集合的泛型类型
	 * 
	 * @param field
	 * @return
	 * @throws Exception
	 */
	private Class<?> getListGenericType(Field field) {
		Type genericType = field.getGenericType();
		if (genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			return (Class<?>) parameterizedType.getActualTypeArguments()[0];
		}
		return null;
	}

	/**
	 * 获取目标类名
	 * 
	 * @param className
	 * @param cloneDirection
	 * @return
	 * @throws Exception
	 */
	private Class<?> getCloneTargetClazz(Class<?> clazz, Integer cloneDirection) {
		try {
			String cloneTargetClassName = null;

			// ReflectionDTO
			String className = clazz.getName();

			if (cloneDirection.equals(CloneDirection.FORWARD)) {
				if (className.endsWith(DomainType.VO)) {
					cloneTargetClassName = className.substring(0, className.length() - 2).replace(".vo", ".dto") + "DTO";
				} else if (className.endsWith(DomainType.DTO)) {
					cloneTargetClassName = className.substring(0, className.length() - 3).replace(".dto", ".domain");
				}
			}

			if (cloneDirection.equals(CloneDirection.OPPOSITE)) {
				if (!className.endsWith(DomainType.DTO) && !className.endsWith(DomainType.VO)) {
					cloneTargetClassName = className.substring(0, className.length()).replace(".domain", ".dto") + "DTO";
				} else if (className.endsWith(DomainType.DTO)) {
					cloneTargetClassName = className.substring(0, className.length() - 3).replace(".dto", ".vo") + "VO";
				}
			}

			return Class.forName(cloneTargetClassName);
		} catch (Exception e) {
			throw new RuntimeException("error", e);
		}

	}

	/**
	 * 获取设置克隆好的list的方法名称
	 * 
	 * @param className
	 * @param cloneDirection
	 * @return
	 * @throws Exception
	 */
	private Method getSetCloneListFieldMethodName(Field field, Class<?> clazz) {
		String name = field.getName();
		String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

		Method setFieldMethod = null;

		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getName().equals(setMethodName)) {
				setFieldMethod = method;
				break;
			}
		}

		return setFieldMethod;
	}

}
