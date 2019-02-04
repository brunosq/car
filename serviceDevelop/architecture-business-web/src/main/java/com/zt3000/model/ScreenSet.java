package com.zt3000.model;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/**
 * 大屏配置实体
 * @author qzg
 *
 */
public class ScreenSet {
	
	public Integer id;
	
	public Integer type;
	
	public Date startTime;
	
	public String typeDesc;
	
	public Double ratio;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
