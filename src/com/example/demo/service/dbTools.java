package com.example.demo.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class dbTools extends SQLiteOpenHelper{

	
	private static final String DB_NAME = "testdb.db"; //���ݿ�����
    private static final int version = 1; //���ݿ�汾
    
    
	public dbTools(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DB_NAME, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 String sql = "create table user(username varchar(20) not null , password varchar(60) not null );";          
	     db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
