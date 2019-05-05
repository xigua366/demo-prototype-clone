package com.yx.demo.dto;

import java.util.Date;
import java.util.List;

import com.yx.demo.common.AbstractObject;

public class PersonDTO extends AbstractObject {

	private Long id;

	private String name;

	private Integer age;

	private Date birthday;
	
	private HouseDTO house;
	
	/**
	 * 每个人有多套房子
	 */
	private List<HouseDTO> houses;

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

	public HouseDTO getHouse() {
		return house;
	}

	public void setHouse(HouseDTO house) {
		this.house = house;
	}

	public List<HouseDTO> getHouses() {
		return houses;
	}

	public void setHouses(List<HouseDTO> houses) {
		this.houses = houses;
	}

	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", name=" + name + ", age=" + age + ", birthday=" + birthday + ", house=" + house
				+ ", houses=" + houses + "]";
	}

	
	
}
