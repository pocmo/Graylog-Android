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
import android.widget.TextView;
import android.widget.Toast;

import com.jimdo.graylog.R;
import com.jimdo.graylog.model.Dashboard;
import com.jimdo.graylog.model.LogMessage;
import com.jimdo.graylog.model.Priority;
import com.jimdo.graylog.model.ResponseDeserializer;
import com.jimdo.graylog.net.Request;
import com.jimdo.graylog.net.UrlBuilder;

/**
 * Shows the dashboard
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class DashboardActivity extends Activity implements OnClickListener, Runnable {
	public static final String TAG = "Graylog/DashboardActivity";
	
	private ProgressDialog dialog;

	private TextView message;
	private TextView status;
	private TextView footer;
	private TextView priority;
	
	private Dashboard dashboard;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        
        this.message  = (TextView) findViewById(R.id.message);
        this.status   = (TextView) findViewById(R.id.status);
        this.footer   = (TextView) findViewById(R.id.footer);
        this.priority = (TextView) findViewById(R.id.priority);
        
        this.message.setOnClickListener(this);
        findViewById(R.id.messagesButton).setOnClickListener(this);
        findViewById(R.id.categoriesButton).setOnClickListener(this);
        
        this.refresh();
    }

    /**
     * Refresh dashboard, reload from server
     */
    public void refresh()
    {
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
    	this.dashboard = dashboard;
    	
    	LogMessage lastMessage = dashboard.getLastMessage();
    	String text = lastMessage.getText();
    	
    	message.setText(text.length() > 140 ? text.substring(0, 140) + " [..]" : text);
    	status.setText(dashboard.getMessages() + " messages in the last " + dashboard.getTimeSpan() + " minutes");
    	footer.setText(lastMessage.getRelativeTime() + " from " + lastMessage.getHost());
    	priority.setText(Priority.getReadable(lastMessage.getPriority()));
    }
    
    /**
     * OnClickListener ...
     */
    public void onClick(View v)
    {
    	switch (v.getId()) {
			case R.id.message:
				if (dashboard != null) {
					Intent intent = new Intent(DashboardActivity.this, InspectActivity.class);
					intent.putExtra("message", dashboard.getLastMessage());
					startActivity(intent);
				}
				break;
    		case R.id.messagesButton:
    			startActivity(new Intent(DashboardActivity.this, MessagesActivity.class));
    			break;
    		case R.id.categoriesButton:
    			startActivity(new Intent(DashboardActivity.this, CategoriesActivity.class));
    			break;
    	}
    }
    
	/**
	 * Options Menu
	 * 
	 * @param menu 
	 */
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.dashboard, menu);

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
			case R.id.refresh:
				this.refresh();
				break;
			case R.id.logout:
				this.finish();
				break;
		}
		return true;
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
    		String response = Request.getInstance().execute(UrlBuilder.getInstance().getDashboardUrl());
    		
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
    			Dashboard dashboard = ResponseDeserializer.deserializeDashboard(response);
    			update(dashboard);
    		} catch (Exception e) {
    			Log.d(TAG, "Exception: " + e.getMessage());
    			updateFailed();
    		}
    	}
    };
}
