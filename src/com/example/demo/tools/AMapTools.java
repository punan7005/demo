package com.example.demo.tools;

import java.util.List;

import android.util.Log;

import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;
import com.example.demo.entity.LoonpCondition;

public class AMapTools {
	
	private List<LoonpCondition> list;
	/** 
	 * ����˵�� :���캯��
	 * @author  joker 
	 * ����ʱ�䣺
	 * <p>@param LoonpCondition list</p>
	 */
	public AMapTools(List<LoonpCondition> listConditions){
		list = listConditions;
	}
	/** 
	 * ����˵�� :��ȡ���г̾���
	 * @author  joker 
	 * ����ʱ�䣺
	 * <p>@param LoonpCondition list</p>
	 */
	public float getTotalMeter(){
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
	
	/** 
	 * ����˵�� :��ȡ���г�ʱ��
	 * @author  joker 
	 * ����ʱ�䣺
	 * <p>@param LoonpCondition list</p>
	 */
	public long getTotalTime(){
		long totalTime = 0;
		long tempTime = 0;
		
		for(int i = 0; i < list.size(); i++){
			if(i != 0){
				tempTime = list.get(i - 1).getCurrMS() - list.get(i).getCurrMS();
				totalTime = totalTime + tempTime;
			}
		}
		totalTime = totalTime / 1000 / 60;
		
		return totalTime;
	}
	
	/** 
	 * ����˵�� :��ȡ���г�ʱ��
	 * @author  joker 
	 * ����ʱ�䣺
	 * <p>@param LoonpCondition list</p>
	 */
	public float getTotalAV(){
		float totalTime = Float.valueOf(getTotalTime());
		float totalMeter = getTotalMeter();
		
		float totalAV = (totalMeter / 1000 ) / (totalTime / 60);
		//0.093
		//0.06
		return totalAV;
	}
}
