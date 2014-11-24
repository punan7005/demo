package com.example.demo.entity;

import android.R.integer;

public class Loonp {
	
	private String Id;
	private String createTime;
	private String startTime;
	private String endTime;
	private String createUserId;
	private Integer totalTime;
	private Integer totalM;
	private Integer totalclimbM;	
	private Integer status;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	public Integer getTotalM() {
		return totalM;
	}
	public void setTotalM(Integer totalM) {
		this.totalM = totalM;
	}
	public Integer getTotalclimbM() {
		return totalclimbM;
	}
	public void setTotalclimbM(Integer totalclimbM) {
		this.totalclimbM = totalclimbM;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
