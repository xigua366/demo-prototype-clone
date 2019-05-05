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
	
	private HouseVO house;
	
	/**
	 * 每个人有多套房子
	 */
	private List<HouseVO> houses;

	

}
