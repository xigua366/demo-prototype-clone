package com.yx.demo.vo;

import com.yx.demo.common.AbstractObject;

/**
 * 房子
 * @author yangxi
 *
 */
public class HouseVO extends AbstractObject {
	
	private Long id;
	
	private Integer area;
	
	private String color;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", area=" + area + ", color=" + color + "]";
	}
	
	

}
