package com.yx.demo.domain;

import java.util.Date;
import java.util.List;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonDO extends AbstractObject {
	
	private Long id;
	
	private String name;
	
	private Integer age;
	
	private Date birthday;
	
	/**
	 * 每个人只有一套房子
	 */
	private HouseDO house;
	
	/**
	 * 每个人有多辆车子
	 */
	private List<CarDO> cars;
	
	

}
