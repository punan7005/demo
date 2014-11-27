package com.example.demo.tools;

import java.util.List;

import android.util.Log;

import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;
import com.example.demo.entity.LoonpCondition;

public class AMapTools {
	public static Float getTotalMeter(List<LoonpCondition> list){
		LatLng startLatLng = null;
		LatLng endLatLng = null;
		float totalMeter = 0;
		
		
		for(int i = 0; i < list.size(); i++){
			if(i != 0){
				startLatLng = new LatLng(list.get(i - 1).getCurrLatitude(), list.get(i - 1).getCurrLongitude());
				endLatLng = new LatLng(list.get(i).getCurrLatitude(), list.get(i).getCurrLongitude());
				totalMeter = totalMeter + AMapUtils.calculateLineDistance(startLatLng, endLatLng); 
			}
		}
		Log.i("totalMeter", String.valueOf(totalMeter));
		return totalMeter;
	}
}
