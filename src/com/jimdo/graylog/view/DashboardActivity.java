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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jimdo.graylog.R;
import com.jimdo.graylog.model.Dashboard;
import com.jimdo.graylog.model.ResponseDeserializer;
import com.jimdo.graylog.net.Request;
import com.jimdo.graylog.net.UrlBuilder;

/**
 * Shows the dashboard
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class DashboardActivity extends Activity implements Runnable {
	public static final String TAG = "Graylog/DashboardActivity";
	
	private ProgressDialog dialog;
	private String baseUrl;
	
	private TextView message;
	private TextView status;
	private TextView footer;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        
        this.baseUrl = getIntent().getExtras().getString("baseUrl");
        this.message = (TextView) findViewById(R.id.message);
        this.status  = (TextView) findViewById(R.id.status);
        this.footer  = (TextView) findViewById(R.id.footer);
        
        dialog = ProgressDialog.show(this, "Loading..", "Loading dashboard from server...", true, false);
        
        new Thread(this).start();
    }
    
    /**
     * Update the view with the given data
     * 
     * @param dashboard
     */
    public void update(Dashboard dashboard)
    {
    	message.setText(dashboard.getLastMessage());
    	status.setText(dashboard.getMessages() + " messages in the last " + dashboard.getTimeSpan() + " minutes");
    	footer.setText("Don't play with your phone. Fix it!");
    }
    
    /**
     * Will be called by the update thread on failure
     */
    public void updateFailed()
    {
    	Toast.makeText(this, "Loading dashboard failed.", Toast.LENGTH_SHORT);
    }
    
    /**
     * Thread for loading dashboard
     */
    public void run()
    {
    	Log.d(TAG, "Loading dashboard...");

    	try {
    		UrlBuilder builder = new UrlBuilder(baseUrl);
    		Request request = new Request();
    		String response = request.execute(builder.getDashboardUrl());
    		
    		Message msg = new Message();
    		Bundle bundle = new Bundle();
    		bundle.putString("response", response);
    		msg.setData(bundle);
    		handler.sendMessage(msg);
    		
    	} catch(Exception e) {
    		Log.d(TAG, "Exception: " + e.getMessage());
    	}
    	
    	dialog.dismiss();
    }
    
    private Handler handler = new Handler() {
    	public void handleMessage(Message msg) {
    		try {
    			String response = msg.getData().getString("response");
    			Log.d(TAG, "Will deserialize string: " + response);
    			Dashboard dashboard = ResponseDeserializer.deserializeDashboard(response);
    			update(dashboard);
    		} catch (Exception e) {
    			Log.d(TAG, "Exception: " + e.getMessage());
    			updateFailed();
    		}
    	}
    };
}
