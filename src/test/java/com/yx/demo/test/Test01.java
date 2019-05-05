package com.yx.demo.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yx.demo.common.CloneDirection;
import com.yx.demo.domain.Person;
import com.yx.demo.dto.HouseDTO;
import com.yx.demo.dto.PersonDTO;
import com.yx.demo.vo.HouseVO;
import com.yx.demo.vo.PersonVO;

/**
 * 单个对象克隆测试  直接调用对象的clone()方法
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
		PersonDTO personDTO = personVO.clone(PersonDTO.class, CloneDirection.FORWARD);
		System.out.println("DTO对象personDTO:" + personDTO);
		
		for(HouseDTO orderItem : personDTO.getHouses()) {
			System.out.println(orderItem.getClass());
		}
		
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
		
		List<HouseVO> houseList = new ArrayList<>();
		HouseVO houseVO01 = new HouseVO();
		houseVO01.setId(20L);
		houseVO01.setColor("red");
		houseVO01.setArea(120);
		houseList.add(houseVO01);
		
		HouseVO houseVO02 = new HouseVO();
		houseVO02.setId(21L);
		houseVO02.setColor("blue");
		houseVO02.setArea(130);
		houseList.add(houseVO02);
		personVO.setHouses(houseList);
		
		personVO.setHouse(houseVO02);
		
		return personVO;
	}

}
