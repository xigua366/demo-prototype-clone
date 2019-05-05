package com.yx.demo.vo;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 房子
 * @author yangxi
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HouseVO extends AbstractObject {
	
	private Long id;
	
	private Integer area;
	
	private String color;

	
	
	

}
