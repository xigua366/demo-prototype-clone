package com.yx.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yx.demo.common.CloneDirection;
import com.yx.demo.domain.Person;
import com.yx.demo.dto.PersonDTO;
import com.yx.demo.vo.CarVO;
import com.yx.demo.vo.HouseVO;
import com.yx.demo.vo.PersonVO;

/**
 * 单个对象克隆（深度克隆，正向）测试，直接调用对象的clone()方法
 * @author yangxi
 *
 */
public class Test03 {
	
	
	public static void main(String[] args) {
		
		// 单个对象clone
		// 原始对象
		PersonVO personVO = getPersonVO();
		System.out.println("原始对象personVO:" + personVO);
		
		// PersonVO clone 成 DTO
		PersonDTO personDTO = personVO.clone(PersonDTO.class, CloneDirection.FORWARD);
		System.out.println("DTO对象personDTO:" + personDTO);
		
		// DTO clone 成 Domain类
		Person person = personDTO.clone(Person.class, CloneDirection.FORWARD);
		System.out.println("Domain对象person:" + person);
	}
	
	
	public static PersonVO getPersonVO() {
		PersonVO personVO = new PersonVO();
		personVO.setId(1L);
		personVO.setName("zhangsan");
		personVO.setAge(20);
		personVO.setBirthday(new Date());
		
		List<CarVO> carList = new ArrayList<>();
		CarVO car01 = new CarVO();
		car01.setId(1L);
		car01.setBrand("宝马");
		car01.setPrice(new BigDecimal("1000000"));
		carList.add(car01);
		
		CarVO car02 = new CarVO();
		car02.setId(2L);
		car02.setBrand("路虎");
		car02.setPrice(new BigDecimal("10000000"));
		carList.add(car02);
		
		personVO.setCars(carList);
		
		HouseVO houseVO01 = new HouseVO();
		houseVO01.setId(20L);
		houseVO01.setColor("red");
		houseVO01.setArea(120);
		personVO.setHouse(houseVO01);
		
		return personVO;
	}

}
