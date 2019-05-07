package com.yx.demo.dto;

import java.math.BigDecimal;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CarDTO extends AbstractObject {
	
	private Long id;
	
	private String brand;
	
	private BigDecimal price;

}
