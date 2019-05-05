package com.yx.demo.vo;

import java.util.Date;
import java.util.List;

import com.yx.demo.common.AbstractObject;

public class PersonVO extends AbstractObject {

	private Long id;

	private String name;

	private Integer age;

	private Date birthday;
	
	/**
	 * 每个人有多套房子
	 */
	private List<HouseVO> houses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<HouseVO> getHouses() {
		return houses;
	}

	public void setHouses(List<HouseVO> houses) {
		this.houses = houses;
	}

	@Override
	public String toString() {
		return "PersonVO [id=" + id + ", name=" + name + ", age=" + age + ", birthday=" + birthday + ", houses="
				+ houses + "]";
	}

	

	

}
