package com.example.demo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demo.entity.LoonpCondition;
import com.example.demo.service.DBTools;

public class LoonpConditionDao {
	private DBTools dbTools;
	private SQLiteDatabase sqLiteDatabase;
	
	
	public LoonpConditionDao(Context context){
		dbTools = new DBTools(context);
		sqLiteDatabase = dbTools.getWritableDatabase();
	}
	
	public void insert(LoonpCondition loonpCondition){
		String sql = "insert into loonpCondition("
				+ "createTime,"
				+ "currAltitude,"
				+ "currLatitude,"
				+ "currLongitude,"
				+ "loonpId) values (?,?,?,?,?);"
				;
		sqLiteDatabase.execSQL(sql, new Object[]{
				loonpCondition.getCreateTime(),
				loonpCondition.getCurrAltitude(),
				loonpCondition.getCurrLatitude(),
				loonpCondition.getCurrLongitude(),
				loonpCondition.getLoonpId(),
		});
		Log.i("Insert one", "... ...");
	}
}
