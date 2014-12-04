package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demo.entity.Loonp;
import com.example.demo.service.DBTools;

public class LoonpDao {
	private DBTools dbTools;
	private SQLiteDatabase sqLiteDatabase;
	
	
	public LoonpDao(Context context){
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
	
	public String findLastOneId(){
		String sql = "select * from loonp limit 1 offset (select count(*) - 1  from loonp) ";
		Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
		Loonp loonp = new Loonp();
		while(cursor.moveToNext()){
			loonp.setId(cursor.getString(cursor.getColumnIndex("Id")));
		}
		return loonp.getId();
	}
	
	public List<Loonp> findAll(){
		String sql = "select * from loonp";
		List<Loonp> loonpList = new ArrayList<Loonp>(); 
		Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
				while(cursor.moveToNext()){
					Loonp loonp = new Loonp();
					loonp.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
					loonp.setId(cursor.getString(cursor.getColumnIndex("Id")));
					loonpList.add(loonp);
				}
		return loonpList;
	}
	
	public void close(){
		sqLiteDatabase.close();
	}
}
