package com.yx.demo.domain;

import java.util.Date;
import java.util.List;

import com.yx.demo.common.AbstractObject;

public class Person extends AbstractObject {
	
	private Long id;
	
	private String name;
	
	private Integer age;
	
	private Date birthday;
	
	private House house;
	
	/**
	 * 每个人有多套房子
	 */
	private List<House> houses;

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

	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", birthday=" + birthday + ", house=" + house
				+ ", houses=" + houses + "]";
	}
	
	
	

}
