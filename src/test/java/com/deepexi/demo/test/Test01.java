package com.deepexi.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deepexi.demo.domain.CarVO;
import com.deepexi.demo.domain.HouseVO;
import com.deepexi.demo.domain.PersonDO;
import com.deepexi.demo.domain.PersonDTO;
import com.deepexi.demo.domain.PersonVO;

/**
 * 单个对象克隆（浅度克隆）测试，直接调用对象的clone()方法
 * @author yangxi
 *
 */
public class Test01 {
	
	
	public static void main(String[] args) {
		
		// 单个对象clone
		// 原始对象
		PersonVO personVO = getPersonVO();
		System.out.println("原始对象personVO:" + personVO);
		
		// PersonVO clone 成 DTO
		PersonDTO personDTO = personVO.clone(PersonDTO.class);
		System.out.println("DTO对象personDTO:" + personDTO);
		
//		// DTO clone 成 Domain类
//		PersonDO person = personDTO.clone(PersonDO.class);
//		System.out.println("Domain对象person:" + person);
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
