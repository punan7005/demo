package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.demo.dao.LoonpConditionDao;
import com.example.demo.entity.LoonpCondition;
import com.example.demo.service.DBTools;

public class AmapActivityForHistory extends Activity{
	
	private MapView mapView;
    private AMap aMap;
    
    private LoonpCondition loonpCondition;
    private LoonpConditionDao loonpConditionDao;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // R ��Ҫ���ð�import com.amapv2.apis.R;
        setContentView(R.layout.activity_main_map_amap_history);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// ����Ҫд
        loonpConditionDao =new LoonpConditionDao(this);
        init();
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("createTime", "2014-11-25");
        List<LoonpCondition> lcList = loonpConditionDao.findByMap(message);
        for(int i = 0; i < lcList.size(); i++){
        	loonpCondition = lcList.get(i);
        	MarkerOptions mo = new MarkerOptions();
        	LatLng ll = new LatLng(loonpCondition.getCurrLatitude(), loonpCondition.getCurrLongitude());
        	mo.position(ll);
        	aMap.addMarker(mo);
        }
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
