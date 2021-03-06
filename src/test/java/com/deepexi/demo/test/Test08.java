package com.deepexi.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deepexi.demo.core.CloneDirection;
import com.deepexi.demo.core.ObjectCloneUtils;
import com.deepexi.demo.domain.CarDO;
import com.deepexi.demo.domain.HouseDO;
import com.deepexi.demo.domain.PersonDO;
import com.deepexi.demo.domain.PersonDTO;
import com.deepexi.demo.domain.PersonVO;
import com.github.pagehelper.Page;

/**
 * 测试 com.github.pagehelper.Page 分页对象克隆，反向深度克隆
 * 使用ObjectCloneUtils.convertPage()工具类方法
 * 
 * @author yangxi
 *
 */
public class Test08 {

	public static void main(String[] args) {

		// com.github.pagehelper.Page分页集合clone
		// 原始对象
		List<PersonDO> persons = getPerson();
		System.out.println("原始对象persons:" + persons);

		// Person clone 成 DTO
		List<PersonDTO> personDTOs = ObjectCloneUtils.convertList(persons, PersonDTO.class, CloneDirection.OPPOSITE);
		System.out.println("DTO对象personDTOs:" + personDTOs);

//		// DTO clone 成 VO类
//		List<PersonVO> personVOs = ObjectCloneUtils.convertList(personDTOs, PersonVO.class, CloneDirection.OPPOSITE);
//		System.out.println("VO对象personVO:" + personVOs);
	}

	public static Page<PersonDO> getPerson() {
		Page<PersonDO> page = new Page<PersonDO>();
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

		page.add(person);

		person = new PersonDO();
		person.setId(2L);
		person.setName("lisi");
		person.setAge(30);
		person.setBirthday(new Date());

		carList = new ArrayList<>();
		car01 = new CarDO();
		car01.setId(1L);
		car01.setBrand("猎豹");
		car01.setPrice(new BigDecimal("1020000"));
		carList.add(car01);

		car02 = new CarDO();
		car02.setId(2L);
		car02.setBrand("奔驰");
		car02.setPrice(new BigDecimal("11000000"));
		carList.add(car02);

		person.setCars(carList);

		house01 = new HouseDO();
		house01.setId(20L);
		house01.setColor("red");
		house01.setArea(120);
		person.setHouse(house01);

		// 分页信息
		page.setTotal(100);
		page.setPageSize(1);
		page.setPageNum(10);
		
		page.add(person);

		return page;
	}

}
