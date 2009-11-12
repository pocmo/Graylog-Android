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
package com.jimdo.graylog;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.util.Log;

import com.jimdo.graylog.model.LogEntry;
import com.jimdo.graylog.model.LogMessage;

/**
 * Adapter for the List in LogsActivity
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class LogListAdapter extends BaseAdapter {
	private ArrayList<LogMessage> messages = new ArrayList<LogMessage>();
	
	public LogListAdapter()
	{
		// Some dummy data
		for (int i = 0; i < 5; i++) {
			LogEntry entry = new LogEntry();
			entry.setMessage("Mama i'm broken (" + i + ")!");
			entry.setId(1);
			entry.setFromHost("www" + (20 + i));
			entry.setReceivedAt("2009-09-08T12:39:17+02:00");
			
			LogMessage message = new LogMessage();
			message.setLogEntry(entry);
			messages.add(message);
		}
		
		Log.d("LogListAdapter", "Requesting......");
		
		new Request().execute();
	}
	
	public int getCount()
	{
		return messages.size();
	}
	
	public Object getItem(int location)
	{
		return location;
	}
	
	public long getItemId(int location)
	{
		return location;
	}
	
	/**
	 * Get View of an list element
	 */
	public View getView(int location, View v, ViewGroup p)
	{
		LogMessage message = messages.get(location);
		
		LayoutInflater inflater = (LayoutInflater) p.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.message, null);

		TextView tv;
		
		tv = (TextView) v.findViewById(R.id.text);
		tv.setText(message.getText().length() > 140 ? message.getText().substring(0, 139) : message.getText());
		
		tv = (TextView) v.findViewById(R.id.footer);
		tv.setText(message.getRelativeTime() + " from " + message.getHost());

		return v;
	}

}
