package com.example.demo.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class LoginService {
	public boolean login(String userName, String passWord){
		boolean flag = false;
		String requestURL = "http://10.0.2.2:8080/blue_giant/tologin.shtml";
		HttpClient client = new DefaultHttpClient();
		HttpPost request;
		try {
			request = new HttpPost(new URI(requestURL));
			HttpResponse response=client.execute(request);
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity httpEntity = response.getEntity();
				if(httpEntity != null){
					flag = true;
				}
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
}
