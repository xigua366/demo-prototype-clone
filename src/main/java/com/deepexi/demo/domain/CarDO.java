package com.deepexi.demo.domain;

import java.math.BigDecimal;

import com.deepexi.demo.core.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CarDO extends AbstractObject {
	
	private Long id;
	
	private String brand;
	
	private BigDecimal price;

}
