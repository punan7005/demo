package com.example.demo.entity;

import android.R.integer;

public class Loonp {
	
	private String Id;
	private String createTime;
	private String endTiem;
	private String createUserId;
	private integer totalTime;
	private integer totalM;
	private integer totalclimbM;
	private LoonpCondition loonpCondition;
	
	
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
	public String getEndTiem() {
		return endTiem;
	}
	public void setEndTiem(String endTiem) {
		this.endTiem = endTiem;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public integer getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(integer totalTime) {
		this.totalTime = totalTime;
	}
	public integer getTotalM() {
		return totalM;
	}
	public void setTotalM(integer totalM) {
		this.totalM = totalM;
	}
	public integer getTotalclimbM() {
		return totalclimbM;
	}
	public void setTotalclimbM(integer totalclimbM) {
		this.totalclimbM = totalclimbM;
	}
	public LoonpCondition getLoonpCondition() {
		return loonpCondition;
	}
	public void setLoonpCondition(LoonpCondition loonpCondition) {
		this.loonpCondition = loonpCondition;
	}
}
