package com.yx.demo.test;

import java.util.ArrayList;
import java.util.List;

import com.yx.demo.common.CloneDirection;
import com.yx.demo.domain.StudentDO;
import com.yx.demo.domain.TestObjDO;
import com.yx.demo.domain.TestObjDTO;

/**
 * 基本数据类型的深度拷贝测试
 * @author yangxi
 *
 */
public class Test07 {
	
	public static void main(String[] args) {
		TestObjDO testObj = getTestObj();
		System.out.println(testObj);
		
		TestObjDTO testObjDTO = testObj.clone(TestObjDTO.class, CloneDirection.OPPOSITE);
		
		System.out.println(testObjDTO);
	}
	
	public static TestObjDO getTestObj() {
		TestObjDO testObj = new TestObjDO();
		
		
		StudentDO student = new StudentDO();
		
		
//		private byte bb;
		byte a = 'a';
		student.setBb(a);
//		
//		private long id;
		student.setId(1);
//		
//		private int age;
		student.setAge(1);
//		
//		private short clazz;
		short c = 1;
		student.setClazz(c);
		
		
//		
//		private double grade;
		student.setGrade(1.1);
//		
//		private float flo;
		float f = 1;
		student.setFlo(f);
//		
//		private char chr;
		char cd = 'a';
		student.setChr(cd);
//		
//		private boolean bool;
		student.setBool(false);
		
		StudentDO subStudent = new StudentDO();
		subStudent.setId(2);
		subStudent.setAge(20);
		
		student.setSubStudent(subStudent);
		
		testObj.setStudent(student);
		
		List<StudentDO> students = new ArrayList<>();
		students.add(student);
		students.add(student);
		
		// testObj.setStudents(students);
		return testObj;
	}

}
