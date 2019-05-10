package com.yx.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yx.demo.core.CloneDirection;
import com.yx.demo.domain.CarDO;
import com.yx.demo.domain.HouseDO;
import com.yx.demo.domain.PersonDO;
import com.yx.demo.domain.PersonDTO;
import com.yx.demo.domain.PersonVO;

/**
 * 单个对象克隆（深度克隆，反向）测试，直接调用对象的clone()方法
 * @author yangxi
 *
 */
public class Test04 {
	
	
	public static void main(String[] args) {
		
		// 单个对象clone
		// 原始对象
		PersonDO person = getPerson();
		System.out.println("原始对象person:" + person);
		
		// Person clone 成 DTO
		PersonDTO personDTO = person.clone(PersonDTO.class, CloneDirection.OPPOSITE);
		System.out.println("DTO对象personDTO:" + personDTO);
		
		// DTO clone 成 VO类
		PersonVO personVO = personDTO.clone(PersonVO.class, CloneDirection.OPPOSITE);
		System.out.println("Domain对象personVO:" + personVO);
	}
	
	
	public static PersonDO getPerson() {
		PersonDO person = new PersonDO();
		person.setId(1L);
		person.setName("zhangsan");
		person.setAge(20);
		person.setBirthday(new Date());
		
		List<CarDO> carList = new ArrayList<>();
		CarDO car01 = new CarDO();
		car01.setId(1L);
		car01.setBrand("宝马");
		car01.setPrice(new BigDecimal("1000000"));
		carList.add(car01);
		
		CarDO car02 = new CarDO();
		car02.setId(2L);
		car02.setBrand("路虎");
		car02.setPrice(new BigDecimal("10000000"));
		carList.add(car02);
		
		person.setCars(carList);
		
		HouseDO house01 = new HouseDO();
		house01.setId(20L);
		house01.setColor("red");
		house01.setArea(120);
		person.setHouse(house01);
		
		return person;
	}

}
