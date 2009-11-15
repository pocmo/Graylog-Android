/*
Graylog - Android Client

Copyright 2009 Sebastian Kaspari

This file is part of Graylog (Android Client).

Graylog (Android Client) is free software: you can redistribute it
and/or modify it under the terms of the GNU General Public License
as published by the Free Software Foundation, either version 3 of
the License, or (at your option) any later version.

Graylog (Android Client) is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Graylog (Android Client). If not, see <http://www.gnu.org/licenses/>.
*/
package com.jimdo.graylog.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jimdo.graylog.R;
import com.jimdo.graylog.net.Request;
import com.jimdo.graylog.net.UrlBuilder;

/**
 * The Login Activity - Everything start's here...
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class LoginActivity extends Activity implements OnClickListener, Runnable {
	public static final String TAG = "Graylog/LoginActivity";
	
	private EditText baseUrl;
	private EditText username;
	private EditText password;
	
	private ProgressDialog dialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        this.baseUrl  = (EditText) findViewById(R.id.baseUrl);
        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);

        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(this);
    }
	
	/**
	 * Options Menu
	 * 
	 * @param menu 
	 */
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.login, menu);
	    return true;
	}
    
    /**
     * On options item selected
     * 
     * @param item
     */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.settings:
				startActivity(new Intent(LoginActivity.this, SettingsActivity.class));
				break;
			case R.id.about:
				startActivity(new Intent(LoginActivity.this, AboutActivity.class));
				break;
			case R.id.exit:
				this.finish();
				break;
		}
		return true;
	}

	/**
	 * OnClickListener..
	 */
	public void onClick(View v)
	{
		Log.d(TAG, "Login...");
		
		String url = baseUrl.getText().toString(); 
		
		if (URLUtil.isValidUrl(url)) {
			dialog = ProgressDialog.show(this, "Login..", "Trying to connect to server...", true, false);
			new Thread(this).start();
		} else {
			Toast.makeText(this, "Invalid Url '" + url + "'", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * Thread for login
	 */
	public void run()
	{
		String baseUrl  = this.baseUrl.getText().toString();
		String username = this.username.getText().toString();
		String password = this.password.getText().toString();
		
		UrlBuilder builder = new UrlBuilder(baseUrl);
		Request request = new Request();
		String response = request.execute(builder.getPingUrl());
		
		if (response != null && response.equals("Graylog/Pong")) {
			// Login successful
			handler.sendEmptyMessage(1);
		} else {
			// Login failed
			handler.sendEmptyMessage(0);
		}
		
		dialog.dismiss();
	}
	
	/**
	 * Will be called by the login marvinthread on failure
	 */
	public void loginFailed()
	{
		String baseUrl  = this.baseUrl.getText().toString();
		Toast.makeText(this, "Can't connect to server '" + baseUrl + "'", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Handler for login thread
	 */
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
				intent.putExtra("baseUrl", baseUrl.getText().toString());
				startActivity(intent);
			} else {
				loginFailed();
			}
		}
	};
}
