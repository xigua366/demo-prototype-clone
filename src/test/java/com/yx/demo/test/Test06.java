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
import com.yx.demo.vo.PersonVO;

/**
 * 单个对象克隆（深度克隆，正向）测试，直接调用对象的clone()方法
 * @author yangxi
 *
 */
public class Test06 {
	
	
	public static void main(String[] args) {
		
		// 单个对象clone
		// 原始对象
		Person person = getPerson();
		System.out.println("原始对象person:" + person);
		
		// Person clone 成 DTO
		PersonDTO personDTO = person.clone(PersonDTO.class, CloneDirection.OPPOSITE);
		System.out.println("DTO对象personDTO:" + personDTO);
		
		// DTO clone 成 VO类
		PersonVO personVO = personDTO.clone(PersonVO.class, CloneDirection.OPPOSITE);
		System.out.println("Domain对象personVO:" + personVO);
	}
	
	
	public static Person getPerson() {
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
		
		return person;
	}

}
