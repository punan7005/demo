package com.example.demo.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBTools extends SQLiteOpenHelper{

	
	private static final String DB_NAME = "testdb.db"; //数据库名称
    private static final int version = 2; //数据库版本
    
    
	public DBTools(Context context) {
		super(context, DB_NAME, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 String sqltable0 = "create table loonpCondition("
		 		+ "createTime TEXT, "
		 		+ "currAltitude REAL, "
		 		+ "currLatitude REAL,"
		 		+ "currLongitude REAL,"
		 		+ "loonpId TEXT"
		 		+ ");"
		 		;
		 String sqltable1 = "create table loonp("
		 		+ "Id TEXT, "
		 		+ "createTime TEXT, "
		 		+ "startTime TEXT, "
		 		+ "endTime TEXT, "
		 		+ "createUserId TEXT, "
		 		+ "totalTime REAL, "
		 		+ "totalM REAL, "
		 		+ "totalclimbM REAL, "
		 		+ "status INTEGER "
		 		+ ");"
		 		;
	     db.execSQL(sqltable0);
	     db.execSQL(sqltable1);
	     Log.i("创建成功了","");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
