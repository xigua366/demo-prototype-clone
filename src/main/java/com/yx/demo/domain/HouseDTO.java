package com.yx.demo.domain;

import com.yx.demo.core.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 房子
 * @author yangxi
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HouseDTO extends AbstractObject {
	
	private Long id;
	
	private Integer area;
	
	private String color;
	
	

}
