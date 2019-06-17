package com.deepexi.demo.test;

import java.util.ArrayList;
import java.util.List;

import com.deepexi.demo.core.CloneDirection;
import com.deepexi.demo.domain.StudentDTO;
import com.deepexi.demo.domain.StudentVO;

/**
 * 测试 List<Long> 基本数据类型的集合类型克隆，深度克隆
 * 
 * @author yangxi
 *
 */
public class Test10 {
	
	public static void main(String[] args) {
		
		StudentDTO student = new StudentDTO();
		student.setId(1L);
		student.setName("name");
		student.setAge(10);
		
		List<Long> ids = new ArrayList<Long>();
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);
		
		student.setIds(ids);
		
		StudentVO targetStudent = student.clone(StudentVO.class, CloneDirection.OPPOSITE);
		System.out.println(targetStudent);
		
	}

}
