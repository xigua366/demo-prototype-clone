package com.deepexi.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deepexi.demo.core.CloneDirection;
import com.deepexi.demo.core.ObjectCloneUtils;
import com.deepexi.demo.domain.CarVO;
import com.deepexi.demo.domain.HouseVO;
import com.deepexi.demo.domain.PersonDO;
import com.deepexi.demo.domain.PersonDTO;
import com.deepexi.demo.domain.PersonVO;
import com.github.pagehelper.Page;

/**
 * 测试 com.github.pagehelper.Page 分页对象克隆，正向深度克隆
 * 使用ObjectCloneUtils.convertList()工具类方法
 * 
 * @author yangxi
 *
 */
public class Test07 {

	public static void main(String[] args) {

		// 单个对象clone
		// 原始对象
		Page<PersonVO> personVOs = getPersonVO();
		System.out.println("原始对象personVOs:" + personVOs);

		// PersonVO clone 成 DTO
		Page<PersonDTO> personDTOs = ObjectCloneUtils.convertPage(personVOs, PersonDTO.class, CloneDirection.FORWARD);
		System.out.println("DTO对象personDTOs:" + personDTOs);

		// DTO clone 成 Domain类
		Page<PersonDO> persons = ObjectCloneUtils.convertPage(personDTOs, PersonDO.class, CloneDirection.FORWARD);
		System.out.println("Domain对象person:" + persons);
	}

	public static Page<PersonVO> getPersonVO() {
		Page<PersonVO> page = new Page<PersonVO>();
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

		page.add(personVO);

		personVO = new PersonVO();
		personVO.setId(2L);
		personVO.setName("lisi");
		personVO.setAge(30);
		personVO.setBirthday(new Date());

		carList = new ArrayList<>();
		car01 = new CarVO();
		car01.setId(1L);
		car01.setBrand("猎豹");
		car01.setPrice(new BigDecimal("1020000"));
		carList.add(car01);

		car02 = new CarVO();
		car02.setId(2L);
		car02.setBrand("奔驰");
		car02.setPrice(new BigDecimal("11000000"));
		carList.add(car02);

		personVO.setCars(carList);

		houseVO01 = new HouseVO();
		houseVO01.setId(20L);
		houseVO01.setColor("red");
		houseVO01.setArea(120);
		personVO.setHouse(houseVO01);
		
		// 分页信息
		page.setTotal(100);
		page.setPageSize(1);
		page.setPageNum(10);

		page.add(personVO);

		return page;
	}

}
