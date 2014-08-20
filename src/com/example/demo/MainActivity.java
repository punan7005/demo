package com.example.demo;

import org.apache.http.client.UserTokenHandler;

import com.example.demo.service.LoginService;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private String userName;
	private String passWord;
	private LoginService loginService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      获取按钮控件
        final Button submit = (Button)this.findViewById(R.id.submit);
//      获取文本控件
        final EditText userNameEditText = (EditText)this.findViewById(R.id.userName);
        final EditText passWordEditText = (EditText)this.findViewById(R.id.passWord);
//      提交操作
        submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userName = userNameEditText.getText().toString();
				passWord = passWordEditText.getText().toString();
				System.out.println(userName);
				System.out.println(passWord);
//				loginService = new LoginService();
//				boolean flag = loginService.login(userName, passWord);
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, TheForwardTest.class);
				startActivity(intent);
			}
		});
    }
}
