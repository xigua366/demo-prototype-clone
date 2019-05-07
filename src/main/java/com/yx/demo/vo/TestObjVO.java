package com.yx.demo.vo;

import java.util.List;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestObjVO extends AbstractObject {
	
	private long id;
	
	private StudentVO student;
	
	// private List<StudentVO> students;
	
	

}
