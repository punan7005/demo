package com.example.demo.entity;

public class LoonpCondition {
	
	private String createTime;
	private Double currAltitude;
	private Double currLatitude;
	private Double currLongitude;
	private String loonpId;
	
	public String getLoonpId() {
		return loonpId;
	}
	public void setLoonpId(String loonpId) {
		this.loonpId = loonpId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Double getCurrAltitude() {
		return currAltitude;
	}
	public void setCurrAltitude(Double currAltitude) {
		this.currAltitude = currAltitude;
	}
	public Double getCurrLatitude() {
		return currLatitude;
	}
	public void setCurrLatitude(Double currLatitude) {
		this.currLatitude = currLatitude;
	}
	public Double getCurrLongitude() {
		return currLongitude;
	}
	public void setCurrLongitude(Double currLongitude) {
		this.currLongitude = currLongitude;
	}
	
	
}
