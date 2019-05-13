package com.deepexi.demo.domain;

import java.util.Date;
import java.util.List;

import com.deepexi.demo.core.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonDTO extends AbstractObject {

	private Long id;

	private String name;

	private Integer age;

	private Date birthday;
	
	/**
	 * 每个人只有一套房子
	 */
	private HouseDTO house;
	
	/**
	 * 每个人有多辆车
	 */
	private List<CarDTO> cars;

	
	
}
