package com.example.demo.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class dbTools extends SQLiteOpenHelper{

	
	private static final String DB_NAME = "testdb.db"; //数据库名称
    private static final int version = 1; //数据库版本
    
    
	public dbTools(Context context) {
		super(context, DB_NAME, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 String sql = "create table user("
		 		+ "createTime varchar(20) not null , "
		 		+ "currLatitude varchar(60) not null "
		 		+ "currLatitude varchar(60) not null "
		 		+ ");";          
	     db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
