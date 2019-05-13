package com.deepexi.demo.domain;

import com.deepexi.demo.core.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 房子
 * @author yangxi
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HouseDO extends AbstractObject {
	
	private Long id;
	
	private Integer area;
	
	private String color;
	
	

}
