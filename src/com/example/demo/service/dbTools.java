package com.example.demo.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBTools extends SQLiteOpenHelper{

	
	private static final String DB_NAME = "testdb.db"; //���ݿ�����
    private static final int version = 2; //���ݿ�汾
    
    
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
		 		+ "loonpId TEXT,"
		 		+ "currMS INTEGER"
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
	     Log.i("�����ɹ���","");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
