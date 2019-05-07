package com.yx.demo.domain;

import java.math.BigDecimal;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Car extends AbstractObject {
	
	private Long id;
	
	private String brand;
	
	private BigDecimal price;

}
