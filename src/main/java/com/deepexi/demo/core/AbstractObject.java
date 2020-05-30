package com.deepexi.demo.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

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
	 * @param cloneDirection 深入克隆的方向，具体赋值参见CloneDirection.java常量类
	 * @return 目标对象实例
	 */
	public <T> T clone(Class<T> clazz, Integer cloneDirection) {
		try {

			// 先完成基本字段的浅克隆
			T target = clazz.newInstance();
			BeanCopierUtils.copyProperties(this, target);

			// 完成内部的AbstractObject、List<AbstractObject>类型字段的深度克隆
			Class<?> thisClazz = this.getClass();
			List<Field> fieldList = listField(null, thisClazz);

			for (Field field : fieldList) {
				field.setAccessible(true);

				// 如果判断某个字段是List类型的
				if (!Collection.class.isAssignableFrom(field.getType())) {
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
						targetField = getTargetClazzField(field, clazz);
					} catch(NoSuchFieldException e) {
						continue;
					}
					if(targetField != null) {
						Class<?> cloneTargetClazz = targetField.getType();
						AbstractObject clonedObj = (AbstractObject) sourceObj.clone(cloneTargetClazz, cloneDirection);
						// 获取设置克隆好的对象的方法名称
						String name = field.getName();
						String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
						// getMethod()方法可以获取当前类与父类中所有的public方法
						Method setFieldMethod = clazz.getMethod(setMethodName, targetField.getType());
						setFieldMethod.invoke(target, clonedObj);
					}
				} else {
					Collection<?> list = (Collection<?>) field.get(this);
					if (list == null || list.size() == 0) {
						continue;
					}

					// 获取List集合中的泛型类型
					Field targetField = null;
					try {
						targetField = getTargetClazzField(field, clazz);
					} catch(NoSuchFieldException e) {
						continue;
					}
					if(targetField != null) {
						Class<?> cloneTargetClazz = getListGenericType(targetField);
						// 获取要克隆的目标类型
						//Class<?> cloneTargetClazz = getCloneTargetClazz(listGenericClazz, cloneDirection);
						// 将list集合克隆到目标list集合中去
						Collection clonedList = (Collection) field.get(this).getClass().newInstance();
						cloneList(list, clonedList, cloneTargetClazz, cloneDirection);

						// 获取设置克隆好的list的方法名称
						String name = field.getName();
						String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
						// getMethod()方法可以获取当前类与父类中所有的public方法
						Method setFieldMethod = clazz.getMethod(setMethodName, targetField.getType());
						setFieldMethod.invoke(target, clonedList);
					}
				}

			}

			return target;
		} catch (Exception e) {
			throw new RuntimeException("error", e);
		}
	}

	/**
	 * 递归获取当前类以及父类中的字段
	 * @param fieldList
	 * @param thisClazz 原始类Class对象
	 * @return
	 */
	public List<Field> listField(List<Field> fieldList, Class<?> thisClazz) {
		if(fieldList == null) {
			fieldList = new ArrayList<>(Arrays.asList(thisClazz.getDeclaredFields()));
		} else {
			fieldList.addAll(Arrays.asList(thisClazz.getDeclaredFields()));
		}

		if(!thisClazz.getSuperclass().getTypeName().equals(AbstractObject.class.getTypeName())) {
			listField(fieldList, thisClazz.getSuperclass());
		}
		return fieldList;
	}

	/**
	 * 如果目标有继承父类需要递归获取目标字段
	 * @param field
	 * @param clazz
	 * @return
	 * @throws NoSuchFieldException
	 */
	private Field getTargetClazzField(Field field, Class<?> clazz) throws NoSuchFieldException{
		Field targetField = null;
		try {
			targetField = clazz.getDeclaredField(field.getName());
		} catch(NoSuchFieldException e) {
			// 目标类有可能没有继承AbstractObject类
			if(clazz.getSuperclass() != null) {
				String targetSuperClazzTypeName = clazz.getSuperclass().getTypeName();
				if(targetSuperClazzTypeName.equals(Object.class.getTypeName()) && !targetSuperClazzTypeName.equals(AbstractObject.class.getTypeName())) {
					// 递归
					targetField = getTargetClazzField(field, clazz.getSuperclass());
				}
			}

			if(targetField == null) {
				throw e;
			}

		}
		return targetField;

	}

	/**
	 * 将一个List克隆到另外一个List
	 *
	 * @param sourceList
	 * @param targetList
	 * @param cloneTargetClazz
	 * @param cloneDirection
	 */
	private void cloneList(Collection sourceList, Collection targetList, Class cloneTargetClazz, Integer cloneDirection) {
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
	 * 浅度克隆时原对象List属性的处理
	 *
	 * @param target
	 * @return
	 */
	private <T> T getTarget(T target) throws Exception {
		Class<?> targetClazz = target.getClass();
		Field[] fields = targetClazz.getDeclaredFields();
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

			String name = field.getName();
			String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

			Method setFieldMethod = targetClazz.getMethod(setMethodName, field.getType());
			setFieldMethod.invoke(target, new ArrayList());
		}

		return target;
	}

}
