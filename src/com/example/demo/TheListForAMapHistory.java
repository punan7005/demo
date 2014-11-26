package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dao.LoonpDao;
import com.example.demo.entity.Loonp;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TheListForAMapHistory extends Activity{
	
	private ListView listView;
	private List<String> testlistdata = new ArrayList<String>();
	
	private List<Loonp> loonpList;
	private LoonpDao loonpDao;
	
	private String Id;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
//		for(int i = 0 ; i < 100; i++){
//			testlistdata.add("ÎÒÊÇ²âÊÔµÄlist: " + i);
//		}
		loonpDao = new LoonpDao(this);
		loonpList = loonpDao.findAll();
		for(int i = 0 ; i < loonpList.size(); i++){
			testlistdata.add(loonpList.get(i).getCreateTime());
		}
		listView = new ListView(this);
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, testlistdata));
		setContentView(listView);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				for(int i = 0 ; i < loonpList.size(); i++){
					if(testlistdata.get(arg2).equals(loonpList.get(i).getCreateTime())){
						Id = loonpList.get(i).getId();
					}
				}
				Intent intent = new Intent();
				intent.putExtra("Id", Id);
				intent.setClass(TheListForAMapHistory.this, AmapActivityForHistory.class);
				startActivity(intent);
			}
		});
	}
	
	
}
