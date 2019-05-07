package com.yx.demo.vo;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentVO extends AbstractObject {
	
	private byte bb;
	
	private long id;
	
	private int age;
	
	private short clazz;
	
	private double grade;
	
	private float flo;
	
	private char chr;
	
	private boolean bool;
	
	
	
	

}
