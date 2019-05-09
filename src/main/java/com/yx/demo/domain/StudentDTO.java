package com.yx.demo.domain;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentDTO extends AbstractObject {
	
	private byte bb;
	
	private long id;
	
	private int age;
	
	private short clazz;
	
	private double grade;
	
	private float flo;
	
	private char chr;
	
	private boolean bool;
	
	
	private StudentDTO subStudent;
	

}
