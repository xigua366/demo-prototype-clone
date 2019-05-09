package com.yx.demo.domain;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestObjDTO extends AbstractObject {
	
	private long id;
	
	private StudentDTO student;
	

}
