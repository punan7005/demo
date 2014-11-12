package com.example.demo;

import com.amap.api.location.LocationManagerProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource.OnLocationChangedListener;
import com.amap.api.maps2d.MapView;

import android.app.Activity;
import android.os.Bundle;

public class AmapActivity extends Activity{
	
	private MapView mapView;
	private AMap aMap;
	private OnLocationChangedListener mListener;
    private LocationManagerProxy mAMapLocationManager;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map_amap);
        //���иߵµ�ͼ
        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        this.init();
	}
	
	/**
     * ��ʼ��AMap����
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }
 
    /**
     * ����������д
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
 
    /**
     * ����������д
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
     
    /**
     * ����������д
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
 
    /**
     * ����������д
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
