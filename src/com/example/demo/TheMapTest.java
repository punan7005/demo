package com.example.demo;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.demo.service.LocationApplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TheMapTest extends Activity{
	
	MapView mapView = null;
	private LocationClient mLocationClient;
	private String sb;
	private String tempcoor="gcj02";
	private LocationMode tempMode = LocationMode.Hight_Accuracy;
	private BaiduMap baiduMap;
	private TextView textView;
	
	public double xCode;
	public double yCode;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_main_map);
		mapView = (MapView)findViewById(R.id.bmapView);
		
		mLocationClient = ((LocationApplication)getApplication()).mLocationClient;
		textView = (TextView)this.findViewById(R.id.textViewformap);
		((LocationApplication)getApplication()).mLocationResult = textView;
		
		
		this.InitLocation();
		mLocationClient.start();
		
		xCode = ((LocationApplication)getApplication()).getxCode();
		yCode = ((LocationApplication)getApplication()).getyCode();
		baiduMap = mapView.getMap();
		baiduMap.setMyLocationEnabled(true);
		
		
		
//		System.out.println(sb);
// 		MyLocationData myLocationData = new MyLocationData.Builder().accuracy(location.getRadius()).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
//		baiduMap.setMyLocationData(myLocationData);
//		LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
//		System.out.println(location.getLatitude());
//		System.out.println(location.getLongitude());
//		BitmapDescriptor bitmap = BitmapDescriptorFactory  
//			    .fromResource(R.drawable.ic_launcher);  
//		OverlayOptions option = new MarkerOptions()  
//	    .position(point)  
//	    .icon(bitmap);  
//		baiduMap.addOverlay(option);
		
	}
	
	@Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
        mapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
        mapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
        mapView.onPause();  
        }  
    
    private void InitLocation(){
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(tempMode);//���ö�λģʽ
		option.setCoorType(tempcoor);//���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		int span=1000;
		option.setScanSpan(span);//���÷���λ����ļ��
		mLocationClient.setLocOption(option);
	}
}
