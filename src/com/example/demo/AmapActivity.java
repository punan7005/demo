package com.example.demo;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapOptions;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.LocationSource.OnLocationChangedListener;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.demo.dao.LoonpConditionDao;
import com.example.demo.entity.LoonpCondition;
import com.example.demo.service.DBTools;

import android.app.Activity;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AmapActivity extends Activity implements LocationSource, AMapLocationListener{
	
	private MapView mapView;
	private AMap aMap;
	private OnLocationChangedListener mListener;
    private LocationManagerProxy mAMapLocationManager;
    
    private TextView getAltitude;
    private TextView getLatitude;
    private TextView getLongitude;
    
    private LoonpCondition loonpCondition;
    private LoonpConditionDao loonpConditionDao;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map_amap);
        //运行高德地图
        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        getAltitude = (TextView)findViewById(R.id.getAltitude);
        getLatitude = (TextView)findViewById(R.id.getLatitude);
        getLongitude = (TextView)findViewById(R.id.getLongitude);
        //初始化数据库连接
        loonpConditionDao = new LoonpConditionDao(this);
        this.init();
	}
	
	/**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }
    
    private void setUpMap() {
        aMap.setLocationSource(this);// 设置定位监听
//        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式：定位（AMap.LOCATION_TYPE_LOCATE）、跟随（AMap.LOCATION_TYPE_MAP_FOLLOW）
        // 地图根据面向方向旋转（AMap.LOCATION_TYPE_MAP_ROTATE）三种模式
//        aMap.setMyLocationType(AMap.);
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
    }

    
    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation.getAMapException().getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                Log.i("当前海拔：", String.valueOf(amapLocation.getAltitude()));
                Log.i("当前纬度：", String.valueOf(amapLocation.getLatitude()));
                Log.i("当前经度：", String.valueOf(amapLocation.getLongitude()));
                MarkerOptions mo = new MarkerOptions();
                LatLng ll = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                if(amapLocation != null){
                	getAltitude.setText("海拔：" + String.valueOf(amapLocation.getAltitude()));
                	getLatitude.setText("纬度：" + String.valueOf(amapLocation.getLatitude()));
                	getLongitude.setText("经度：" + String.valueOf(amapLocation.getLongitude()));
                }else{
                	getAltitude.setText("null");
                	getLatitude.setText("null");
                	getLongitude.setText("null");
                }
                mo.position(ll);
                aMap.addMarker(mo);
                //插入数据库记录坐标点
                loonpCondition = new LoonpCondition();
                loonpCondition.setCreateTime(DateUtils.getDateTime());
                loonpCondition.setCurrAltitude(amapLocation.getAltitude());
                loonpCondition.setCurrLatitude(amapLocation.getLatitude());
                loonpCondition.setCurrLongitude(amapLocation.getLongitude());
                loonpConditionDao.insert(loonpCondition);
            }
        }
    }
    

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	/**
     * 激活定位
     */
	@Override
	public void activate(OnLocationChangedListener listener) {
		// TODO Auto-generated method stub
		mListener = listener;
        if (mAMapLocationManager == null) {
            mAMapLocationManager = LocationManagerProxy.getInstance(this);
            //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
            //在定位结束后，在合适的生命周期调用destroy()方法     
            //其中如果间隔时间为-1，则定位只定一次
//            mAMapLocationManager.requestLocationData(
//            		LocationProviderProxy.AMapNetwork, 6000, 1, this);
            mAMapLocationManager.requestLocationData(
            		LocationManagerProxy.GPS_PROVIDER, 6000, 1, this);
        }
	}

	/**
     * 停止定位
     */
    @Override
	public void deactivate() {
		// TODO Auto-generated method stub
		mListener = null;
        if (mAMapLocationManager != null) {
            mAMapLocationManager.removeUpdates(this);
            mAMapLocationManager.destroy();
        }
        mAMapLocationManager = null;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}
}
