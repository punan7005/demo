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
import com.example.demo.dao.LoonpDao;
import com.example.demo.entity.Loonp;
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
    private LoonpDao loonpDao;
    private String loonpId;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map_amap);
        //���иߵµ�ͼ
        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        getAltitude = (TextView)findViewById(R.id.getAltitude);
        getLatitude = (TextView)findViewById(R.id.getLatitude);
        getLongitude = (TextView)findViewById(R.id.getLongitude);
        //��ʼ�����ݿ�����
        loonpConditionDao = new LoonpConditionDao(this);
        loonpDao = new LoonpDao(this);
        Loonp loonp = new Loonp();
        loonp.setId(UuidFactory.getUuid());
        loonp.setStartTime(DateUtils.getDateTime());
        loonp.setCreateTime(DateUtils.getDateTime());
        loonpDao.insert(loonp);
        loonpId = loonpDao.findLastOneId();
        this.init();
	}
	
	/**
     * ��ʼ��AMap����
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }
    
    private void setUpMap() {
        aMap.setLocationSource(this);// ���ö�λ����
//        aMap.getUiSettings().setMyLocationButtonEnabled(true);// ����Ĭ�϶�λ��ť�Ƿ���ʾ
        aMap.setMyLocationEnabled(true);// ����Ϊtrue��ʾ��ʾ��λ�㲢�ɴ�����λ��false��ʾ���ض�λ�㲢���ɴ�����λ��Ĭ����false
        // ���ö�λ������Ϊ��λģʽ����λ��AMap.LOCATION_TYPE_LOCATE�������棨AMap.LOCATION_TYPE_MAP_FOLLOW��
        // ��ͼ������������ת��AMap.LOCATION_TYPE_MAP_ROTATE������ģʽ
//        aMap.setMyLocationType(AMap.);
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
        loonpConditionDao.close();
        loonpDao.close();
    }

    
    /**
     * ��λ�ɹ���ص�����
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation.getAMapException().getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// ��ʾϵͳС����
                Log.i("��ǰ���Σ�", String.valueOf(amapLocation.getAltitude()));
                Log.i("��ǰγ�ȣ�", String.valueOf(amapLocation.getLatitude()));
                Log.i("��ǰ���ȣ�", String.valueOf(amapLocation.getLongitude()));
                MarkerOptions mo = new MarkerOptions();
                LatLng ll = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                if(amapLocation != null){
                	getAltitude.setText("���Σ�" + String.valueOf(amapLocation.getAltitude()));
                	getLatitude.setText("γ�ȣ�" + String.valueOf(amapLocation.getLatitude()));
                	getLongitude.setText("���ȣ�" + String.valueOf(amapLocation.getLongitude()));
                }else{
                	getAltitude.setText("null");
                	getLatitude.setText("null");
                	getLongitude.setText("null");
                }
                mo.position(ll);
                aMap.addMarker(mo);
                //�������ݿ��¼�����
                loonpCondition = new LoonpCondition();
                loonpCondition.setCreateTime(DateUtils.getDateTime());
                loonpCondition.setCurrAltitude(amapLocation.getAltitude());
                loonpCondition.setCurrLatitude(amapLocation.getLatitude());
                loonpCondition.setCurrLongitude(amapLocation.getLongitude());
                loonpCondition.setLoonpId(loonpId);
                loonpCondition.setCurrMS(System.currentTimeMillis());
                Log.i("����ʱ��", loonpCondition.getCreateTime());
                Log.i("���뺣��", String.valueOf(loonpCondition.getCurrAltitude()));
                Log.i("����ά��", String.valueOf(loonpCondition.getCurrLatitude()));
                Log.i("���뾫��", String.valueOf(loonpCondition.getCurrLongitude()));
                Log.i("������id", loonpCondition.getLoonpId());
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
     * ���λ
     */
	@Override
	public void activate(OnLocationChangedListener listener) {
		// TODO Auto-generated method stub
		mListener = listener;
        if (mAMapLocationManager == null) {
            mAMapLocationManager = LocationManagerProxy.getInstance(this);
            //�˷���Ϊÿ���̶�ʱ��ᷢ��һ�ζ�λ����Ϊ�˼��ٵ������Ļ������������ģ�
            //ע�����ú��ʵĶ�λʱ��ļ���������ں���ʱ�����removeUpdates()������ȡ����λ����
            //�ڶ�λ�������ں��ʵ��������ڵ���destroy()����     
            //����������ʱ��Ϊ-1����λֻ��һ��
            mAMapLocationManager.requestLocationData(
            		LocationProviderProxy.AMapNetwork, 6000, 1, this);
//            mAMapLocationManager.requestLocationData(
//            		LocationManagerProxy.GPS_PROVIDER, 6000, 1, this);
        }
	}

	/**
     * ֹͣ��λ
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
