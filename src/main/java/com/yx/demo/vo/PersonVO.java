package com.yx.demo.vo;

import java.util.Date;
import java.util.List;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonVO extends AbstractObject {

	private Long id;

	private String name;

	private Integer age;

	private Date birthday;
	
	/**
	 * 每个人有一套房子
	 */
	private HouseVO house;
	
	/**
	 * 每个人有多辆车
	 */
	private List<CarVO> cars;
	
	

	

}
