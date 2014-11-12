package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TheForwardTest extends Activity {
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_two);
		
		final Button getlistButton = (Button)this.findViewById(R.id.getlist);
		
		final Button getMapButton = (Button)this.findViewById(R.id.getMap);
		
		final Button getGpsButton = (Button)this.findViewById(R.id.getGPS);
		
		final Button getAmapButton = (Button)this.findViewById(R.id.getAmap);
		
		getlistButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(TheForwardTest.this, TheListTest.class);
				startActivity(intent);
			}
		});
		
		getMapButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(TheForwardTest.this, TheMapTest.class);
				startActivity(intent);
			}
		});
		
		getGpsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(TheForwardTest.this, GpsActivity.class);
				startActivity(intent);
			}
		});
		
		getAmapButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(TheForwardTest.this, AmapActivity.class);
				startActivity(intent);
			}
		});
		
	}
}
