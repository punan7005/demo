package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.R.string;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.webkit.WebChromeClient.CustomViewCallback;

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
				+ "loonpId,"
				+ "currMS"
				+ ") values (?,?,?,?,?,?);"
				;
		sqLiteDatabase.execSQL(sql, new Object[]{
				loonpCondition.getCreateTime(),
				loonpCondition.getCurrAltitude(),
				loonpCondition.getCurrLatitude(),
				loonpCondition.getCurrLongitude(),
				loonpCondition.getLoonpId(),
				loonpCondition.getCurrMS()
		});
		Log.i("Insert one", "... ...");
		Log.i("插入时间", loonpCondition.getCreateTime());
        Log.i("插入海拔", String.valueOf(loonpCondition.getCurrAltitude()));
        Log.i("插入维度", String.valueOf(loonpCondition.getCurrLatitude()));
        Log.i("插入精度", String.valueOf(loonpCondition.getCurrLongitude()));
	}
	
	public List<LoonpCondition> findByMap(Map<String, Object> message){
		String sql ="select * from loonpCondition where loonpId = ?";
		List<LoonpCondition> lcList = new ArrayList<LoonpCondition>();
		Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{message.get("loonpId").toString()});
		if(cursor.moveToFirst()){
//			for(int i = 0; i < cursor.getCount(); i++){
//				cursor.move(i);
//				LoonpCondition lc = new LoonpCondition();
//				lc.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
//				lc.setCurrAltitude(cursor.getDouble(cursor.getColumnIndex("currAltitude")));
//				lc.setCurrLatitude(cursor.getDouble(cursor.getColumnIndex("currLongitude")));
//				lcList.add(lc);
//			}
			while(cursor.moveToNext()){
				LoonpCondition lc = new LoonpCondition();
				lc.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
				lc.setCurrAltitude(cursor.getDouble(cursor.getColumnIndex("currAltitude")));
				lc.setCurrLatitude(cursor.getDouble(cursor.getColumnIndex("currLatitude")));
				lc.setCurrLongitude(cursor.getDouble(cursor.getColumnIndex("currLongitude")));
				lc.setCurrMS(cursor.getLong(cursor.getColumnIndex("currMS")));
				lcList.add(lc);
			}
		}
		return lcList;
	}
	
	public void close(){
		sqLiteDatabase.close();
	}
}
