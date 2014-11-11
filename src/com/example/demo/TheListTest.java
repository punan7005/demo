package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TheListTest extends Activity{
	
	private ListView listView;
	private List<String> testlistdata = new ArrayList<String>();
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		for(int i = 0 ; i < 100; i++){
			testlistdata.add("我是测试的list: " + i);
		}
		listView = new ListView(this);
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, testlistdata));
		setContentView(listView);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(testlistdata.get(arg2).equals("我是测试的list: 2")){
					System.out.println("你大爷");
				}
			}
		});
	}
	
	
}
