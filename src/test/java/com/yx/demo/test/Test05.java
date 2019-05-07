package com.yx.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yx.demo.common.CloneDirection;
import com.yx.demo.domain.Car;
import com.yx.demo.domain.House;
import com.yx.demo.domain.Person;
import com.yx.demo.dto.PersonDTO;
import com.yx.demo.util.ObjectCloneUtils;
import com.yx.demo.vo.PersonVO;

/**
 * 集合对象克隆（深度克隆，正向）测试，使用ObjectUtils.convertList() 工具类方法
 * @author yangxi
 *
 */
public class Test05 {
	
	
	public static void main(String[] args) {
		
		// 单个对象clone
		// 原始对象
		List<Person> persons = getPerson();
		System.out.println("原始对象persons:" + persons);
		
		// Person clone 成 DTO
		List<PersonDTO> personDTOs = ObjectCloneUtils.convertList(persons, PersonDTO.class, CloneDirection.OPPOSITE);
		System.out.println("DTO对象personDTOs:" + personDTOs);
		
		// DTO clone 成 VO类
		List<PersonVO> personVOs = ObjectCloneUtils.convertList(personDTOs, PersonVO.class, CloneDirection.OPPOSITE);
		System.out.println("VO对象personVO:" + personVOs);
	}
	
	
	public static List<Person> getPerson() {
		List<Person> list = new ArrayList<Person>();
		Person person = new Person();
		person.setId(1L);
		person.setName("zhangsan");
		person.setAge(20);
		person.setBirthday(new Date());
		
		List<Car> carList = new ArrayList<>();
		Car car01 = new Car();
		car01.setId(1L);
		car01.setBrand("宝马");
		car01.setPrice(new BigDecimal("1000000"));
		carList.add(car01);
		
		Car car02 = new Car();
		car02.setId(2L);
		car02.setBrand("路虎");
		car02.setPrice(new BigDecimal("10000000"));
		carList.add(car02);
		
		person.setCars(carList);
		
		House house01 = new House();
		house01.setId(20L);
		house01.setColor("red");
		house01.setArea(120);
		person.setHouse(house01);
		
		list.add(person);
		
		person = new Person();
		person.setId(2L);
		person.setName("lisi");
		person.setAge(30);
		person.setBirthday(new Date());
		
		carList = new ArrayList<>();
		car01 = new Car();
		car01.setId(1L);
		car01.setBrand("猎豹");
		car01.setPrice(new BigDecimal("1020000"));
		carList.add(car01);
		
		car02 = new Car();
		car02.setId(2L);
		car02.setBrand("奔驰");
		car02.setPrice(new BigDecimal("11000000"));
		carList.add(car02);
		
		person.setCars(carList);
		
		house01 = new House();
		house01.setId(20L);
		house01.setColor("red");
		house01.setArea(120);
		person.setHouse(house01);
		
		list.add(person);
		
		return list;
	}

}
