package com.deepexi.demo.domain;

import java.util.List;

import com.deepexi.demo.core.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentVO extends AbstractObject {
	
	private Long id;
	
	private String name;
	
	private Integer age;
	
	private List<Long> ids;
	
	
	

}
