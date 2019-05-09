package com.yx.demo.domain;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestObjDO extends AbstractObject {
	
	private long id;
	
	private StudentDO student;
	

}
