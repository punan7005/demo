package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.demo.dao.LoonpConditionDao;
import com.example.demo.entity.LoonpCondition;
import com.example.demo.service.DBTools;
import com.example.demo.tools.AMapTools;

public class AmapActivityForHistory extends Activity{
	
	private MapView mapView;
    private AMap aMap;
    
    private LoonpCondition loonpCondition;
    private LoonpConditionDao loonpConditionDao;
    
    private String Id;
    private LatLng startLatLng;
    private LatLng endlatLatLng;
    private Float totleM;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // R 需要引用包import com.amapv2.apis.R;
        setContentView(R.layout.activity_main_map_amap_history);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 必须要写
        loonpConditionDao =new LoonpConditionDao(this);
        init();
        
        //接受传递数据
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String strContentString = bundle.getString("fromMain");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Id = bundle.getString("Id");
        
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("loonpId", Id);
        List<LoonpCondition> lcList = loonpConditionDao.findByMap(message);
        for(int i = 0; i < lcList.size(); i++){
        	loonpCondition = lcList.get(i);
        	MarkerOptions mo = new MarkerOptions();
        	LatLng ll = new LatLng(loonpCondition.getCurrLatitude(), loonpCondition.getCurrLongitude());
        	mo.position(ll);
        	aMap.addMarker(mo); 
        }
        
        final TextView totalMeter = (TextView)findViewById(R.id.getTotalMeter);
        final TextView totalTime = (TextView)findViewById(R.id.getTotalTime);
        final TextView totalAV = (TextView)findViewById(R.id.getAV);
        
        AMapTools atTools = new AMapTools(lcList);
        totalMeter.setText("总行程：" + atTools.getTotalMeter() + "米");
        totalTime.setText("总用时" + atTools.getTotalTime() + "分");
        totalAV.setText("平均" + atTools.getTotalAV() + "km/h");
    }
 
    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }
 
    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
 
    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
     
    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
 
    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        loonpConditionDao.close();
    }
}
