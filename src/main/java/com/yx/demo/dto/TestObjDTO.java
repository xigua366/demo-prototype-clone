package com.yx.demo.dto;

import java.util.List;

import com.yx.demo.common.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestObjDTO extends AbstractObject {
	
	private long id;
	
	private StudentDTO student;
	
	// private List<StudentDTO> students;

}
