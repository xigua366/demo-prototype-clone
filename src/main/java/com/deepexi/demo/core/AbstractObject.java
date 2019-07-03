package com.deepexi.demo.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 基础POJO类
 * 
 * @author yangxi
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class AbstractObject {

	/**
	 * 浅度克隆
	 * 
	 * @param clazz 目标对象的Class类型
	 * @return 目标对象实例
	 */
	public <T> T clone(Class<T> clazz) {
		try {
			T target = clazz.newInstance();
			BeanCopierUtils.copyProperties(this, target);

			return getTarget(target);
		} catch (Exception e) {
			throw new RuntimeException("error", e);
		}

	}

	/**
	 * 浅度克隆
	 * 
	 * @param target 目标对象实例
	 * @return 目标对象实例
	 */
	public <T> T clone(T target) {
		try {
			BeanCopierUtils.copyProperties(this, target);
			return getTarget(target);
		} catch (Exception e) {
			throw new RuntimeException("error", e);
		}
	}

	/**
	 * 深度克隆
	 * 
	 * @param clazz 目标对象的Class类型
	 * @param direction 深入克隆的方向，具体赋值参见CloneDirection.java常量类
	 * @return 目标对象实例
	 */
	public <T> T clone(Class<T> clazz, Integer cloneDirection) {
		try {

			// 先完成基本字段的浅克隆
			T target = clazz.newInstance();
			BeanCopierUtils.copyProperties(this, target);

			// 完成内部的AbstractObject、List<ObjectObject>类型字段的深度克隆
			Class<?> thisClazz = this.getClass();
			Field[] fields = thisClazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);

				// 如果判断某个字段是List类型的
				if (field.getType() != List.class) {
					Class<?> sourceFeildClazz = field.getType();
					if (sourceFeildClazz == String.class || sourceFeildClazz == Long.class
							|| sourceFeildClazz.toString().equals("long") || field.getType() == Integer.class
							|| sourceFeildClazz.toString().equals("int") || sourceFeildClazz == Short.class
							|| sourceFeildClazz.toString().equals("short") || sourceFeildClazz == Double.class
							|| sourceFeildClazz.toString().equals("double") || sourceFeildClazz == Float.class
							|| sourceFeildClazz.toString().equals("float") || sourceFeildClazz == BigDecimal.class
							|| sourceFeildClazz == Boolean.class || sourceFeildClazz.toString().equals("boolean")
							|| sourceFeildClazz == Date.class || sourceFeildClazz == Character.class
							|| sourceFeildClazz.toString().equals("char") || sourceFeildClazz == Byte.class
							|| sourceFeildClazz.toString().equals("byte") || sourceFeildClazz == java.sql.Date.class) {
						continue;
					}
					// 判断某个字段是否AbstractObject类型的
					try {
						if (!(field.getType().newInstance() instanceof AbstractObject)) {
							continue;
						}
					} catch (Exception e) {
						if (e instanceof InstantiationException) {
							continue;
						}
						throw new RuntimeException("error", e);
					}
					AbstractObject sourceObj = (AbstractObject) (field.get(this));
					if (sourceObj == null) {
						continue;
					}

					// 获取要克隆的目标类型
					//Class<?> cloneTargetClazz = getCloneTargetClazz(field.getType(), cloneDirection);
					Field targetField = null;
					try {
						targetField = clazz.getDeclaredField(field.getName());
					} catch(NoSuchFieldException e) {
						continue;
					}
					Class<?> cloneTargetClazz = targetField.getType();
					AbstractObject clonedObj = (AbstractObject) sourceObj.clone(cloneTargetClazz, cloneDirection);
					// 获取设置克隆好的对象的方法名称
					Method setFieldMethod = getSetCloneFieldMethodName(field, clazz);
					setFieldMethod.invoke(target, clonedObj);

				} else {
					List<?> list = (List<?>) field.get(this);
					if (list == null || list.size() == 0) {
						continue;
					}

					// 获取List集合中的泛型类型
					Field targetField = null;
					try {
						clazz.getDeclaredField(field.getName());
					} catch(NoSuchFieldException e) {
						continue;
					}
					Class<?> cloneTargetClazz = getListGenericType(targetField);
					// 获取要克隆的目标类型
					//Class<?> cloneTargetClazz = getCloneTargetClazz(listGenericClazz, cloneDirection);
					// 将list集合克隆到目标list集合中去
					List clonedList = new ArrayList();
					cloneList(list, clonedList, cloneTargetClazz, cloneDirection);

					// 获取设置克隆好的list的方法名称
					Method setFieldMethod = getSetCloneFieldMethodName(field, clazz);
					setFieldMethod.invoke(target, clonedList);
				}

			}

			return target;
		} catch (Exception e) {
			throw new RuntimeException("error", e);
		}
	}

	/**
	 * 将一个List克隆到另外一个List
	 * 
	 * @param sourceList
	 * @param targetList
	 * @param cloneTargetClazz
	 * @param cloneDirection
	 */
	private void cloneList(List sourceList, List targetList, Class cloneTargetClazz, Integer cloneDirection) {
		for (Object object : sourceList) {
			if(object instanceof AbstractObject) {
				AbstractObject targetObject = (AbstractObject) object;
				AbstractObject clonedObject = (AbstractObject) targetObject.clone(cloneTargetClazz, cloneDirection);
				targetList.add(clonedObject);
			} else {
				// 非List<? extends AbstractObject>类型的集合字段，直接复用原对象的字段值
				targetList.add(object);
			}
		}
	}

	/**
	 * 获取List集合的泛型类型
	 * 
	 * @param field
	 * @return
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
	 */
	@SuppressWarnings("unused")
	private Class<?> getCloneTargetClazz(Class<?> clazz, Integer cloneDirection) {
		try {
			String cloneTargetClassName = null;

			// ReflectionDTO
			String className = clazz.getName();

			// 正向克隆
			if (cloneDirection.equals(CloneDirection.FORWARD)) {
				if (className.endsWith(DomainType.VO)) {
					cloneTargetClassName = className.substring(0, className.length() - 2)
							+ "DTO";
				} else if (className.endsWith(DomainType.DTO)) {
					cloneTargetClassName = className.substring(0, className.length() - 3) + "DO";
				} else {
					// 可能存在List<Long>这样的非List<? extends AbstractObject>类型的集合类型字段
					cloneTargetClassName = className;
				}
			}

			// 反向克隆
			if (cloneDirection.equals(CloneDirection.OPPOSITE)) {
				if (className.endsWith(DomainType.DO)) {
					cloneTargetClassName = className.substring(0, className.length() - 2)
							+ "DTO";
				} else if (className.endsWith(DomainType.DTO)) {
					cloneTargetClassName = className.substring(0, className.length() - 3) + "VO";
				} else {
					// 可能存在List<Long>这样的非List<? extends AbstractObject>类型的集合类型字段
					cloneTargetClassName = className;
				}
			}

			return Class.forName(cloneTargetClassName);
		} catch (Exception e) {
			throw new RuntimeException("error", e);
		}

	}

	/**
	 * 获取设置克隆好的对象或List属性的方法名称
	 * 
	 * @param field
	 * @param clazz
	 * @return
	 */
	private Method getSetCloneFieldMethodName(Field field, Class<?> clazz) {
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

	/**
	 * 浅度克隆时原对象List属性的处理
	 * 
	 * @param target
	 * @return
	 */
	private <T> T getTarget(T target) throws Exception {
		Class<?> thisClazz = target.getClass();
		Field[] fields = thisClazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);

			// 如果判断某个字段是List类型的
			if (field.getType() != List.class) {
				continue;
			}
			List<?> list = (List<?>) field.get(target);
			if (list == null || list.size() == 0) {
				continue;
			}
			
			// 进一步判断是否List<? extends AbstractObject>类型的集合类型
			List targetList = new ArrayList();
			for (Object object : list) {
				if(!(object instanceof AbstractObject)) {
					// 非List<? extends AbstractObject>类型的集合字段，直接复用原对象的字段值
					targetList.add(object);
				}
				// List<? extends AbstractObject>类型的集合字段，在浅克隆时不处理，默认设置目标对象为new ArrayList();
			}
			
			Method setFieldMethod = getSetCloneFieldMethodName(field, target.getClass());
			setFieldMethod.invoke(target, targetList);
		}

		return target;
	}

}
