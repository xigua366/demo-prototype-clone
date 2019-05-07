package com.yx.demo.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yx.demo.domain.Person;
import com.yx.demo.dto.PersonDTO;
import com.yx.demo.util.ObjectUtils;
import com.yx.demo.vo.HouseVO;
import com.yx.demo.vo.PersonVO;

/**
 * 集合对象克隆（浅度克隆）测试，使用ObjectUtils.convertList() 工具类方法
 * @author yangxi
 *
 */
public class Test02 {
	
	
	public static void main(String[] args) {
		
		// 单个对象clone
		// 原始对象
		List<PersonVO> personVOs = getPersonVO();
		System.out.println("原始对象personVOs:" + personVOs);
		
		// PersonVO clone 成 DTO
		List<PersonDTO> personDTOs = ObjectUtils.convertList(personVOs, PersonDTO.class);
		System.out.println("DTO对象personDTOs:" + personDTOs);
		
		// DTO clone 成 Domain类
		List<Person> persons = ObjectUtils.convertList(personDTOs, Person.class);
		System.out.println("Domain对象person:" + persons);
	}
	
	
	public static List<PersonVO> getPersonVO() {
		List<PersonVO> list = new ArrayList<PersonVO>();
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
		
		list.add(personVO);
		
		personVO = new PersonVO();
		personVO.setId(2L);
		personVO.setName("lisi");
		personVO.setAge(30);
		personVO.setBirthday(new Date());
		
		houseList = new ArrayList<>();
		houseVO01 = new HouseVO();
		houseVO01.setId(22L);
		houseVO01.setColor("black");
		houseVO01.setArea(140);
		houseList.add(houseVO01);
		
		houseVO02 = new HouseVO();
		houseVO02.setId(23L);
		houseVO02.setColor("blue");
		houseVO02.setArea(135);
		houseList.add(houseVO02);
		// personVO.setHouses(houseList);
		list.add(personVO);
		
		return list;
	}

}
