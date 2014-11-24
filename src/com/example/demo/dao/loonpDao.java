package com.example.demo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.demo.entity.Loonp;
import com.example.demo.service.DBTools;

public class loonpDao {
	private DBTools dbTools;
	private SQLiteDatabase sqLiteDatabase;
	
	
	public loonpDao(Context context){
		dbTools = new DBTools(context);
		sqLiteDatabase = dbTools.getWritableDatabase();
	}
	
	public void insert(Loonp loonp){
		String sql = "insert into loonp("
				+ "Id,"
				+ "createTime,"
				+ "startTime,"
				+ "endTime,"
				+ "createUserId,"
				+ "totalTime,"
				+ "totalM,"
				+ "totalclimbM,"
				+ "status) values (?,?,?,?,?,?,?,?,?);";
		sqLiteDatabase.execSQL(
				sql,
				new Object[]{
						loonp.getId(),
						loonp.getCreateTime(),
						loonp.getStartTime(),
						loonp.getEndTime(),
						loonp.getCreateUserId(),
						loonp.getTotalTime(),
						loonp.getTotalM(),
						loonp.getTotalclimbM(),
						0
						}
				);
	}
	
	public void close(){
		sqLiteDatabase.close();
	}
}
