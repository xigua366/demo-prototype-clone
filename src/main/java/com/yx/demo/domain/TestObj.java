package com.yx.demo.domain;

import java.util.List;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestObj extends AbstractObject {
	
	private long id;
	
	private Student student;
	
	private List<Student> students;

}
