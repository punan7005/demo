package com.example.demo;

import java.util.Iterator;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GpsActivity extends Activity{

	private LocationManager locationManager;
	private static final String TAG="GpsActivity";
	private TextView gpsInfo;
	private Location location;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gps);
        
        gpsInfo = (TextView)this.findViewById(R.id.textViewforps);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        locationManager.setTestProviderEnabled("gps", true);
        //Ϊ��ȡ����λ����Ϣʱ���ò�ѯ����
        String bestProvider = locationManager.getBestProvider(getCriteria(), true);
        
        //��������ò�ѯҪ��getLastKnownLocation�������˵Ĳ���ΪLocationManager.GPS_PROVIDER
        this.location = locationManager.getLastKnownLocation(bestProvider);
        //����״̬
        locationManager.addGpsStatusListener(listener);
        updateView(location);
        //�󶨼�������4������    
        //����1���豸����GPS_PROVIDER��NETWORK_PROVIDER����
        //����2��λ����Ϣ�������ڣ���λ����    
        //����3��λ�ñ仯��С���룺��λ�þ���仯������ֵʱ��������λ����Ϣ    
        //����4������    
        //��ע������2��3���������3��Ϊ0�����Բ���3Ϊ׼������3Ϊ0����ͨ��ʱ������ʱ���£�����Ϊ0������ʱˢ��   
        
        // 1�����һ�Σ�����Сλ�Ʊ仯����1�׸���һ�Σ�
        //ע�⣺�˴�����׼ȷ�ȷǳ��ͣ��Ƽ���service��������һ��Thread����run��sleep(10000);Ȼ��ִ��handler.sendMessage(),����λ��
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
	}
	
	//λ�ü���
    private LocationListener locationListener=new LocationListener() {
        
        /**
         * λ����Ϣ�仯ʱ����
         */
        public void onLocationChanged(Location location) {
        	GpsActivity.this.location = location;
        	updateView(location);
            Log.i(TAG, "ʱ�䣺"+location.getTime()); 
            Log.i(TAG, "���ȣ�"+location.getLongitude()); 
            Log.i(TAG, "γ�ȣ�"+location.getLatitude()); 
            Log.i(TAG, "���Σ�"+location.getAltitude()); 
        }
        
        /**
         * GPS״̬�仯ʱ����
         */
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
            //GPS״̬Ϊ�ɼ�ʱ
            case LocationProvider.AVAILABLE:
                Log.i(TAG, "��ǰGPS״̬Ϊ�ɼ�״̬");
                break;
            //GPS״̬Ϊ��������ʱ
            case LocationProvider.OUT_OF_SERVICE:
                Log.i(TAG, "��ǰGPS״̬Ϊ��������״̬");
                break;
            //GPS״̬Ϊ��ͣ����ʱ
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.i(TAG, "��ǰGPS״̬Ϊ��ͣ����״̬");
                break;
            }
        }
    
        /**
         * GPS����ʱ����
         */
        public void onProviderEnabled(String provider) {
            Location location=locationManager.getLastKnownLocation(provider);
            updateView(location);
        }
    
        /**
         * GPS����ʱ����
         */
        public void onProviderDisabled(String provider) {
        	updateView(null);
        }

    
    };
	
	//״̬����
    GpsStatus.Listener listener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {
            switch (event) {
            //��һ�ζ�λ
            case GpsStatus.GPS_EVENT_FIRST_FIX:
                Log.i(TAG, "��һ�ζ�λ");
                break;
            //����״̬�ı�
            case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                Log.i(TAG, "����״̬�ı�");
                //��ȡ��ǰ״̬
                GpsStatus gpsStatus=locationManager.getGpsStatus(null);
                //��ȡ���ǿ�����Ĭ�����ֵ
                int maxSatellites = gpsStatus.getMaxSatellites();
                //����һ�������������������� 
                Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
                int count = 0;     
                while (iters.hasNext() && count <= maxSatellites) {     
                    GpsSatellite s = iters.next();     
                    count++;     
                }   
                System.out.println("��������"+count+"������");
                break;
            //��λ����
            case GpsStatus.GPS_EVENT_STARTED:
                Log.i(TAG, "��λ����");
                break;
            //��λ����
            case GpsStatus.GPS_EVENT_STOPPED:
                Log.i(TAG, "��λ����");
                break;
            }
        };
    };
    
    /**
     * ʵʱ�����ı�����
     * 
     * @param location
     */
    private void updateView(Location location){
        if(location!=null){
            gpsInfo.setText("�豸λ����Ϣ\n\n���ȣ�");
            gpsInfo.append(String.valueOf(location.getLongitude()));
            gpsInfo.append("\nγ�ȣ�");
            gpsInfo.append(String.valueOf(location.getLatitude()));
            gpsInfo.append("\n���Σ�");
            gpsInfo.append(String.valueOf(location.getAltitude()));
            gpsInfo.setTextColor(android.graphics.Color.RED);
        }else{
            //���EditText����
        	 gpsInfo.setText("location is null");
        	 gpsInfo.setTextColor(android.graphics.Color.RED);
        }
    }
    
    
    /**
     * ���ز�ѯ����
     * @return
     */
    private Criteria getCriteria(){
        Criteria criteria=new Criteria();
        //���ö�λ��ȷ�� Criteria.ACCURACY_COARSE�Ƚϴ��ԣ�Criteria.ACCURACY_FINE��ȽϾ�ϸ 
        criteria.setAccuracy(Criteria.ACCURACY_FINE);    
        //�����Ƿ�Ҫ���ٶ�
        criteria.setSpeedRequired(false);
        // �����Ƿ�������Ӫ���շ�  
        criteria.setCostAllowed(false);
        //�����Ƿ���Ҫ��λ��Ϣ
        criteria.setBearingRequired(false);
        //�����Ƿ���Ҫ������Ϣ
        criteria.setAltitudeRequired(true);
        // ���öԵ�Դ������  
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }
    
    @Override
    protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
     locationManager.removeUpdates(locationListener);
    }
}
